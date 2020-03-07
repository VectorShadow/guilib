package resources.glyph;

import resources.continuum.Continuum;
import resources.glyph.ascii.ContinuumGlyph;
import resources.glyph.ascii.SimpleGlyph;
import resources.glyph.image.ContinuumImageGlyph;
import resources.glyph.image.ImageGlyph;
import resources.continuum.Pair;
import resources.glyph.image.ImageManager;
import resources.glyph.image.SimpleImageGlyph;
import resources.render.OutputMode;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Builder pattern for Glyphs.
 *
 * Call GlyphBuilder.buildGlyph().setDefaults(b,f,s).build() for SimpleGlyphs.
 * Include addFoo(...) any number of times between setDefaults and build for ContinuumGlyphs.
 * Call GlyphBuilder.buildGlyph().setDefaults(b,p,s,t).build(r,c) for SimpleImageGlyphs.
 * Include addFoo(...) any number of times between setDefaults and build for ContinuumImageGlyphs.
 */
public class GlyphBuilder {
    private int imageRow = -1;
    private int imageCol = -1;
    private Color bg0;
    private Color fp0;
    private Color fs0;
    private Color ft0;
    private char sym0;
    private ArrayList<Pair<Color>> bg;
    private ArrayList<Pair<Color>> fp;
    private ArrayList<Pair<Color>> fs;
    private ArrayList<Pair<Color>> ft;
    private ArrayList<Pair<Character>> sym;

    private GlyphBuilder() {
        bg = new ArrayList<>();
        fp = new ArrayList<>();
        fs = new ArrayList<>();
        ft = new ArrayList<>();
        sym = new ArrayList<>();
    }

    public static GlyphBuilder buildGlyph(){
        return new GlyphBuilder();
    }

    /**
     * Set the default properties for this glyph.
     * This is all the information required to build a SimpleGlyph.
     * ContinuumGlyphs will use the defaults as the base values for each Continuum.
     * ImageGlyphs will use all provided values, and derive non-provided values as necessary -
     * for example, an ImageGlyph with defaults for primary and secondary but not tertiary will simply
     * use the secondary value as the tertiary(that is, it will map both secondary and tertiary fields
     * from the source image to a single color).
     * ImageGlyphs use the provided symbol for two purposes - first, to generate an ASCII-style glyph if
     * no corresponding source image can be found by the ImageManager, and second, to indicate WordBreak
     * status if set in a Zone which requires this information.
     */
    public GlyphBuilder setDefaults(Color defaultBackground, Color defaultForeground, char defaultSymbol){
        return setDefaults(defaultBackground, defaultForeground, defaultForeground, defaultSymbol);
    }
    GlyphBuilder setDefaults(
            Color defaultBackground,
            Color defaultPrimary,
            Color defaultSecondary,
            char defaultSymbol
    ){
        return setDefaults(defaultBackground, defaultPrimary, defaultSecondary, defaultSecondary, defaultSymbol);
    }
    GlyphBuilder setDefaults(
            Color defaultBackground,
            Color defaultPrimary,
            Color defaultSecondary,
            Color defaultTertiary,
            char defaultSymbol
    ){
        bg0 = defaultBackground;
        fp0 = defaultPrimary;
        fs0 = defaultSecondary;
        ft0 = defaultTertiary;
        sym0 = defaultSymbol;
        return this;
    }

    public GlyphBuilder addBackgroundColor(Pair<Color> bc) {
        bg.add(bc);
        return this;
    }

    public GlyphBuilder addForegroundColor(Pair<Color> fc) {
        fp.add(fc);
        return this;
    }
    private GlyphBuilder addPrimaryColor(Pair<Color> pc) {
        return addForegroundColor(pc);
    }
    private GlyphBuilder addSecondaryColor(Pair<Color> sc) {
        fs.add(sc);
        return this;
    }
    private GlyphBuilder addTertiaryColor(Pair<Color> tc) {
        fs.add(tc);
        return this;
    }

    public GlyphBuilder addSymbol(Pair<Character> sym) {
        this.sym.add(sym);
        return this;
    }
    private GlyphBuilder setImageRowAndColumn(int row, int col) {
        imageRow = row;
        imageCol = col;
        return this;
    }
    public GlyphBuilder readProtoGlyph(ProtoGlyph pg, boolean vague) {
        setDefaults(pg.background, pg.primary, pg.secondary, pg.tertiary, vague ? pg.vagueSymbol : pg.visibleSymbol);
        for (Pair<Color> bg : pg.backgrounds) addBackgroundColor(bg);
        for (Pair<Color> pri : pg.primaries) addBackgroundColor(pri);
        for (Pair<Color> sec : pg.secondaries) addBackgroundColor(sec);
        for (Pair<Color> ter : pg.tertiaries) addBackgroundColor(ter);
        for (Pair<Character> sym : vague ? pg.vagueSymbols : pg.visibleSymbols) addSymbol(sym);
        setImageRowAndColumn(vague ? pg.vagueImageRow : pg.visibleImageRow, vague ? pg.vagueImageCol : pg.visibleImageCol);
        return this;
    }
    private ArrayList<Pair<Color>> compressColors(double insertedProbability, ArrayList<Pair<Color>> originalList, ArrayList<Pair<Color>> updatedList) {
        double scale = 1.0 - insertedProbability;
        for (Pair<Color> cp : originalList) {
            double newProbability = cp.probability * scale + insertedProbability;
            if (newProbability <= 0.0 || newProbability > 1.0) throw new IllegalStateException("Invalid percentage: %" + newProbability * 100);
            updatedList.add(new Pair<>(newProbability, cp.element));
        }
        return updatedList;
    }
    public GlyphBuilder addStatusColors(ArrayList<Pair<Color>> cpl) {
        if (!cpl.isEmpty()) {
            ArrayList<Pair<Color>> statusBackground = new ArrayList<>(cpl);
            bg = compressColors(cpl.get(cpl.size() - 1).probability, bg, statusBackground);
        }
        return this;
    }
    public GlyphBuilder addReflection(Pair<Color> reflect) {
        ArrayList<Pair<Color>> reflectionForeground = new ArrayList<>();
        reflectionForeground.add(reflect);
        fp = compressColors(reflect.probability, fp, reflectionForeground);
        return this;
    }

    /**
     * Complete the building of an ASCII-only Glyph.
     */
    public Glyph build(){
        if (bg.size() == 1 && fp.size() == 1 && sym.size() == 1){
            return new SimpleGlyph(bg0, fp0, sym0);
        }
        return new ContinuumGlyph(
                new Continuum<>(bg0, bg),
                new Continuum<>(fp0, fp),
                new Continuum<>(sym0, sym)
        );
    }

    /**
     * Complete the building of an ImageGlyph.
     * If no graphics have been loaded for the RenderContext corresponding to the specified OutputMode
     * (in fullscreen), or that tileset does not contain an image at the specified row and column,
     * an ASCII-only glyph will be built instead.
     *
     * Note that the tilesets corresponding to fullscreen and windowed RenderContexts for all Output Modes
     * must be maintained in parallel, or undefined behavior can occur.
     */
    public Glyph build(OutputMode mode){
        if (!ImageManager.exists(imageRow, imageCol, mode.generateContext(true))) return build();
        ImageGlyph ig;
        if (bg.size() == 1 && fp.size() == 1 && fs.size() <= 1 && ft.size() <= 1){
            ig = new SimpleImageGlyph(bg0, fp0, fs0, ft0, imageRow, imageCol);
        }
        else {
            ig = new ContinuumImageGlyph(
                    new Continuum<>(bg0, bg),
                    new Continuum<>(fp0, fp),
                    new Continuum<>(fs0, fs),
                    new Continuum<>(ft0, ft),
                    imageRow,
                    imageCol
            );
        }
        ig.setWordBreak(sym0);
        return ig;
    }
}

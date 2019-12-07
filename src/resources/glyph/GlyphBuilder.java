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
    Color bg0;
    Color fp0;
    Color fs0;
    Color ft0;
    char sym0;
    ArrayList<Pair<Color>> bg;
    ArrayList<Pair<Color>> fp;
    ArrayList<Pair<Color>> fs;
    ArrayList<Pair<Color>> ft;
    ArrayList<Pair<Character>> sym;

    private GlyphBuilder() {
        bg = new ArrayList<>();
        fp = new ArrayList<>();
        fs = new ArrayList<>();
        ft = new ArrayList<>();
        sym = new ArrayList<>();
    }

    private static GlyphBuilder buildGlyph(){
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
    private GlyphBuilder setDefaults(Color defaultBackground, Color defaultForeground, char defaultSymbol){
        return setDefaults(defaultBackground, defaultForeground, defaultForeground, defaultSymbol);
    }
    private GlyphBuilder setDefaults(
            Color defaultBackground,
            Color defaultPrimary,
            Color defaultSecondary,
            char defaultSymbol
    ){
        return setDefaults(defaultBackground, defaultPrimary, defaultSecondary, defaultSecondary, defaultSymbol);
    }
    private GlyphBuilder setDefaults(
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

    private GlyphBuilder addBackgroundColor(Pair<Color> bc) {
        bg.add(bc);
        return this;
    }

    private GlyphBuilder addForegroundColor(Pair<Color> fc) {
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

    private GlyphBuilder addSymbol(Pair<Character> sym) {
        this.sym.add(sym);
        return this;
    }

    /**
     * Complete the building of an ASCII-only Glyph.
     */
    private Glyph build(){
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
    private Glyph build(int row, int col, OutputMode mode){
        if (!ImageManager.exists(row, col, mode.generateContext(true))) return build();
        ImageGlyph ig;
        if (bg.size() == 1 && fp.size() == 1 && fs.size() <= 1 && ft.size() <= 1){
            ig = new SimpleImageGlyph(bg0, fp0, fs0, ft0, row, col);
        }
        else {
            ig = new ContinuumImageGlyph(
                    new Continuum<>(bg0, bg),
                    new Continuum<>(fp0, fp),
                    new Continuum<>(fs0, fs),
                    new Continuum<>(ft0, ft),
                    row,
                    col
            );
        }
        ig.setWordBreak(sym0);
        return ig;
    }
    private static GlyphBuilder partialBuild(ProtoGlyph protoGlyph) {
        GlyphBuilder gb = GlyphBuilder.buildGlyph().setDefaults(
                protoGlyph.baseBackground,
                protoGlyph.basePrimary,
                protoGlyph.baseSecondary,
                protoGlyph.baseTertiary,
                protoGlyph.baseSymbol);
        for (Pair<Character> symPair : protoGlyph.symbols) gb.addSymbol(symPair);
        for (Pair<Color> bgPair : protoGlyph.backgrounds) gb.addBackgroundColor(bgPair);
        for (Pair<Color> pPair : protoGlyph.primaries) gb.addPrimaryColor(pPair);
        for (Pair<Color> sPair : protoGlyph.secondaries) gb.addSecondaryColor(sPair);
        for (Pair<Color> tPair : protoGlyph.tertiaries) gb.addTertiaryColor(tPair);
        return gb;
    }
    public static Glyph build(ProtoGlyph protoGlyph) {
        return partialBuild(protoGlyph).build();
    }
    public static Glyph build(ProtoGlyph protoGlyph, OutputMode mode) {
        if (protoGlyph.allowGraphics())
            return partialBuild(protoGlyph).build(protoGlyph.imageRow, protoGlyph.imageCol, mode);
        else return build(protoGlyph);
    }

}

package resources.glyph;

import resources.continuum.Continuum;
import resources.glyph.ascii.ContinuumGlyph;
import resources.glyph.ascii.SimpleGlyph;
import resources.glyph.image.ContinuumImageGlyph;
import resources.glyph.image.ImageGlyph;
import resources.continuum.Pair;
import resources.glyph.image.ImageManager;
import resources.glyph.image.SimpleImageGlyph;

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

    public static GlyphBuilder buildGlyph(){
        return new GlyphBuilder();
    }

    public GlyphBuilder setDefaults(Color defaultBackground, Color defaultForeground, char defaultSymbol){
        return setDefaults(defaultBackground, defaultForeground, defaultForeground, defaultSymbol);
    }
    public GlyphBuilder setDefaults(
            Color defaultBackground,
            Color defaultPrimary,
            Color defaultSecondary,
            char defaultSymbol
    ){
        return setDefaults(defaultBackground, defaultPrimary, defaultSecondary, defaultSecondary, defaultSymbol);
    }
    public GlyphBuilder setDefaults(
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
    public GlyphBuilder addPrimaryColor(Pair<Color> pc) {
        return addForegroundColor(pc);
    }
    public GlyphBuilder addSecondaryColor(Pair<Color> sc) {
        fs.add(sc);
        return this;
    }
    public GlyphBuilder addTertiaryColor(Pair<Color> tc) {
        fs.add(tc);
        return this;
    }

    public GlyphBuilder addSymbol(Pair<Character> sym) {
        this.sym.add(sym);
        return this;
    }
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
    public Glyph build(int row, int col){
        if (!ImageManager.exists(row, col)) return build();
        if (bg.size() == 1 && fp.size() == 1 && fs.size() <= 1 && ft.size() <= 1){
            return new SimpleImageGlyph(bg0, fp0, fs0, ft0, row, col);
        }
        return new ContinuumImageGlyph(
                new Continuum<>(bg0, bg),
                new Continuum<>(fp0, fp),
                new Continuum<>(fs0, fs),
                new Continuum<>(ft0, ft),
                row,
                col
        );
    }

}

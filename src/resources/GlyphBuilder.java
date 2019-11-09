package resources;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Builder pattern for Glyphs.
 *
 * Call GlyphBuilder.buildGlyph().setDefaults(...).build() for Simple Glyphs.
 * Include addFoo(...) any number of times between setDefaults and build for Continuum Glyphs.
 * Call GlyphBuilder.buildGlyph().build(bufferedImage) for ImageGlyphs.
 */
public class GlyphBuilder {
    Color b0;
    Color f0;
    char s0;
    ArrayList<Pair<Color>> b;
    ArrayList<Pair<Color>> f;
    ArrayList<Pair<Character>> s;

    private GlyphBuilder() {
        b = new ArrayList<>();
        f = new ArrayList<>();
        s = new ArrayList<>();
    }

    public static GlyphBuilder buildGlyph(){
        return new GlyphBuilder();
    }

    public GlyphBuilder setDefaults(Color defaultBackground, Color defaultForeground, char defaultSymbol){
        b0 = defaultBackground;
        f0 = defaultForeground;
        s0 = defaultSymbol;
        return this;
    }

    public GlyphBuilder addBackgroundColor(Pair<Color> bc) {
        b.add(bc);
        return this;
    }

    public GlyphBuilder addForegroundColor(Pair<Color> fc) {
        f.add(fc);
        return this;
    }

    public GlyphBuilder addSymbol(Pair<Character> sym) {
        s.add(sym);
        return this;
    }
    public Glyph build(){
        if (b.size() == 1 && f.size() == 1 && s.size() == 1){
            return new SimpleGlyph(b0, f0, s0);
        }
        return new ContinuumGlyph(
                new Continuum<Color>(b0, b),
                new Continuum<Color>(f0, f),
                new Continuum<Character>(s0, s)
        );
    }
    public Glyph build(BufferedImage bi){
        return new ImageGlyph(bi);
    }

}

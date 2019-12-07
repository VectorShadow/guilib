package resources.glyph;

import resources.continuum.Pair;
import resources.glyph.ProtoGlyph;

import java.awt.*;

public class ProtoGlyphBuilder {
    private ProtoGlyph protoGlyph;

    private ProtoGlyphBuilder(){

    }
    public static ProtoGlyphBuilder setDefaults(char sym, Color bg, Color fg) {
        return setDefaults(sym, bg, fg, fg);
    }
    public static ProtoGlyphBuilder setDefaults(char sym, Color bg, Color p, Color s) {
        return setDefaults(sym, bg, p, s, s);
    }
    public static ProtoGlyphBuilder setDefaults(char sym, Color bg, Color p, Color s, Color t) {
        ProtoGlyphBuilder pgb = new ProtoGlyphBuilder();
        pgb.protoGlyph = new ProtoGlyph(sym, bg, p, s, t);
        return pgb;
    }
    public ProtoGlyphBuilder setSourceCoordinates(int r, int c) {
        protoGlyph.setSourceCoordinates(r, c);
        return this;
    }
    public ProtoGlyphBuilder addSymbol(Pair<Character> symPair) {
        protoGlyph.addSymbol(symPair);
        return this;
    }
    public ProtoGlyphBuilder addBackground(Pair<Color> bgPair) {
        protoGlyph.addBackground(bgPair);
        return this;
    }
    public ProtoGlyphBuilder addPrimary(Pair<Color> pPair) {
        protoGlyph.addPrimary(pPair);
        return this;
    }
    public ProtoGlyphBuilder addSecondary(Pair<Color> sPair) {
        protoGlyph.addSecondary(sPair);
        return this;
    }
    public ProtoGlyphBuilder addTertiary(Pair<Color> tPair) {
        protoGlyph.addTertiary(tPair);
        return this;
    }
    public ProtoGlyph build() {
        return protoGlyph;
    }
}

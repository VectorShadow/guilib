package resources.glyph;

import resources.continuum.Pair;

import java.awt.*;

public class ProtoGlyphBuilder {
    private ProtoGlyph protoGlyph;

    private ProtoGlyphBuilder(){

    }

    public static ProtoGlyphBuilder setDefaults(Color bg, Color fg, char vis) {
        return setDefaults(bg, fg, vis, vis);
    }

    public static ProtoGlyphBuilder setDefaults(Color bg, Color fg, char vis, char vague) {
        return setDefaults(bg, fg, fg, vis, vague);
    }

    public static ProtoGlyphBuilder setDefaults(Color bg, Color pri, Color sec, char vis) {
        return setDefaults(bg, pri, sec, vis, vis);
    }

    public static ProtoGlyphBuilder setDefaults(Color bg, Color pri, Color sec, char vis, char vague) {
        return setDefaults(bg, pri, sec, sec, vis, vague);
    }

    public static ProtoGlyphBuilder setDefaults(Color bg, Color pri, Color sec, Color ter, char vis) {
        return setDefaults(bg, pri, sec, ter, vis, vis);
    }
    public static ProtoGlyphBuilder setDefaults(Color bg, Color pri, Color sec, Color ter, char vis, char vague) {
        ProtoGlyphBuilder pgb = new ProtoGlyphBuilder();
        pgb.protoGlyph = new ProtoGlyph(bg, pri, sec, ter, vis, vague);
        return pgb;
    }
    public ProtoGlyphBuilder setVisibleSourceCoordinates(int r, int c) {
        protoGlyph.setVisibleSourceCoordinates(r, c);
        return this;
    }
    public ProtoGlyphBuilder setVagueSourceCoordinates(int r, int c) {
        protoGlyph.setVagueSourceCoordinates(r, c);
        return this;
    }
    public ProtoGlyphBuilder addVisibleSymbol(Pair<Character> symPair) {
        protoGlyph.addVisibleSymbol(symPair);
        return this;
    }
    public ProtoGlyphBuilder addVagueSymbol(Pair<Character> symPair) {
        protoGlyph.addVagueSymbol(symPair);
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

package resources.glyph;

import resources.continuum.Pair;

import java.awt.*;
import java.util.ArrayList;

public class ProtoGlyph {
    //image row or col < 0 cannot be rendered from external source
    int imageRow = -1;
    int imageCol = -1;
    char baseSymbol;
    ArrayList<Pair<Character>> symbols = new ArrayList<>();
    Color baseBackground;
    ArrayList<Pair<Color>> backgrounds = new ArrayList<>();
    Color basePrimary;
    ArrayList<Pair<Color>> primaries = new ArrayList<>();
    Color baseSecondary;
    ArrayList<Pair<Color>> secondaries = new ArrayList<>();
    Color baseTertiary;
    ArrayList<Pair<Color>> tertiaries = new ArrayList<>();

    ProtoGlyph(char baseSymbol, Color baseBackground, Color baseForeground) {
        this(baseSymbol, baseBackground, baseForeground, baseForeground);
    }
    ProtoGlyph(char baseSymbol, Color baseBackground, Color basePrimary, Color baseSecondary) {
        this(baseSymbol, baseBackground, basePrimary, baseSecondary, baseSecondary);
    }
    ProtoGlyph(char baseSymbol, Color baseBackground, Color basePrimary, Color baseSecondary, Color baseTertiary) {
        this.baseSymbol = baseSymbol;
        this.baseBackground = baseBackground;
        this.basePrimary = basePrimary;
        this.baseSecondary = baseSecondary;
        this.baseTertiary = baseTertiary;
    }
    void setSourceCoordinates(int r, int c) {
        imageRow = r;
        imageCol = c;
    }
    void addSymbol(Pair<Character> symPair) {
        symbols.add(symPair);
    }
    void addBackground(Pair<Color> bgPair) {
        backgrounds.add(bgPair);
    }
    void addPrimary(Pair<Color> pPair) {
        primaries.add(pPair);
    }
    void addSecondary(Pair<Color> sPair) {
        secondaries.add(sPair);
    }
    void addTertiary(Pair<Color> tPair) {
        tertiaries.add(tPair);
    }
    public ProtoGlyph insertSymbol(Pair<Character> symPair) {
        if (symbols.isEmpty()) {
            symbols.add(symPair);
            return this;
        }
        //todo - collect symbol percentages, add new symbol, scale back down to 1.0, rebuild
        return null; //for now
    }
    //todo - add all other types
    //todo - rework GlyphBuilder if/as necessary to make use of ProtoGlyphs
}

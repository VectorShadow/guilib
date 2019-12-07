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

    public boolean allowGraphics() {
        return imageRow >= 0 && imageCol >= 0;
    }

    /**
     * insertFoo methods treat Pairs differently than a standard continuum.
     * Because building a continuum requires a pre-existing arraylist of pairs,
     * the pairs in that list are expected to be ordered by cumulative probability, not particular probability.
     * That is, if two elements on the continuum are each to have a 30% chance of being selected,
     * they must be provided as .3, then .3 + .3, or .6, to work as intended.
     * Because insert adds a new value, without knowledge of existing values, the probability on the inserted
     * pair must be the particular probability of that element occurring. As a result, these methods must
     * compress the existing probabilities to allow insertion.
     * @param symPair
     * @return
     */
    public ProtoGlyph insertSymbol(Pair<Character> symPair) {
        ProtoGlyph protoGlyph = clone();
        if (symPair.probability >= 1.0) {
            protoGlyph.baseSymbol = symPair.element; //replace the base symbol
            symbols = new ArrayList<>(); //remove any additional symbols
        } else if (symbols.isEmpty()) {
            protoGlyph.addSymbol(symPair);
        } else {
            ArrayList<Pair<Character>> compressedSymbols = new ArrayList<>();
            compressedSymbols.add(symPair);
            for (Pair<Character> pair : symbols) {
                Pair<Character> compressed = pair.compress(symPair.probability);
                if (compressed.probability < 1.0) compressedSymbols.add(compressed);
            }
            protoGlyph.symbols = compressedSymbols;
        }
        return protoGlyph;
    }
    public ProtoGlyph insertBackground(Pair<Color> bgPair) {
        ProtoGlyph protoGlyph = clone();
        if (bgPair.probability >= 1.0) {
            protoGlyph.baseBackground = bgPair.element; //replace the base symbol
            backgrounds = new ArrayList<>(); //remove any additional symbols
        } else if (backgrounds.isEmpty()) {
            protoGlyph.addBackground(bgPair);
        } else protoGlyph.backgrounds = compress(backgrounds, bgPair);
        return protoGlyph;
    }
    public ProtoGlyph insertPrimary(Pair<Color> pPair) {
        ProtoGlyph protoGlyph = clone();
        if (pPair.probability >= 1.0) {
            protoGlyph.basePrimary = pPair.element; //replace the base symbol
            primaries = new ArrayList<>(); //remove any additional symbols
        } else if (primaries.isEmpty()) {
            protoGlyph.addPrimary(pPair);
        } else protoGlyph.primaries = compress(primaries, pPair);
        return protoGlyph;
    }
    public ProtoGlyph insertSecondary(Pair<Color> sPair) {
        ProtoGlyph protoGlyph = clone();
        if (sPair.probability >= 1.0) {
            protoGlyph.baseSecondary = sPair.element; //replace the base symbol
            secondaries = new ArrayList<>(); //remove any additional symbols
        } else if (secondaries.isEmpty()) {
            protoGlyph.addSecondary(sPair);
        } else protoGlyph.secondaries = compress(secondaries, sPair);
        return protoGlyph;
    }
    public ProtoGlyph insertTertiary(Pair<Color> tPair) {
        ProtoGlyph protoGlyph = clone();
        if (tPair.probability >= 1.0) {
            protoGlyph.baseTertiary = tPair.element; //replace the base symbol
            tertiaries = new ArrayList<>(); //remove any additional symbols
        } else if (tertiaries.isEmpty()) {
            protoGlyph.addTertiary(tPair);
        } else protoGlyph.tertiaries = compress(tertiaries, tPair);
        return protoGlyph;
    }
    private ArrayList<Pair<Color>> compress(ArrayList<Pair<Color>> colorPairArrayList, Pair<Color> insertedColorPair) {
        ArrayList<Pair<Color>> compressedColorPairs = new ArrayList<>();
        compressedColorPairs.add(insertedColorPair);
        for (Pair<Color> pair : colorPairArrayList) {
            Pair<Color> compressedColorPair = pair.compress(insertedColorPair.probability);
            if (compressedColorPair.probability < 1.0) compressedColorPairs.add(compressedColorPair);
        }
        return compressedColorPairs;
    }
    public ProtoGlyph clone() {
        ProtoGlyph cpg = new ProtoGlyph(baseSymbol, baseBackground, basePrimary, baseSecondary, baseTertiary);
        for (Pair<Character> characterPair : symbols) cpg.addSymbol(characterPair.clone());
        for (Pair<Color> bgPair : backgrounds) cpg.addBackground(bgPair.clone());
        for (Pair<Color> pPair : primaries) cpg.addPrimary(pPair.clone());
        for (Pair<Color> sPair : secondaries) cpg.addSecondary(sPair.clone());
        for (Pair<Color> tPair : tertiaries) cpg.addTertiary(tPair.clone());
        return cpg;
    }
}

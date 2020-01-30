package resources.glyph;

import resources.chroma.Chroma;
import resources.continuum.Pair;

import java.awt.*;
import java.util.ArrayList;

public class ProtoGlyph {
    //image row or col < 0 cannot be rendered from external source
    int visibleImageRow = -1;
    int visibleImageCol = -1;
    int vagueImageRow = -1;
    int vagueImageCol = -1;
    char visibleSymbol;
    char vagueSymbol;
    Color background, primary, secondary, tertiary;
    ArrayList<Pair<Character>> visibleSymbols = new ArrayList<>();
    ArrayList<Pair<Character>> vagueSymbols = new ArrayList<>();
    ArrayList<Pair<Color>> backgrounds = new ArrayList<>();
    ArrayList<Pair<Color>> primaries = new ArrayList<>();
    ArrayList<Pair<Color>> secondaries = new ArrayList<>();
    ArrayList<Pair<Color>> tertiaries = new ArrayList<>();

    ProtoGlyph(Color b, Color f, char vis) {
        this(b, f, vis, vis);
    }

    ProtoGlyph(Color b, Color f, char vis, char vague) {
        this(b, f, f, vis, vague);
    }

    ProtoGlyph(Color b, Color p, Color s, char vis) {
        this(b, p, s, vis, vis);
    }

    ProtoGlyph(Color b, Color p, Color s, char vis, char vague) {
        this(b, p, s, s, vis, vague);
    }

    ProtoGlyph(Color b, Color p, Color s, Color t, char vis) {
        this(b, p, s, t, vis, vis);
    }

    ProtoGlyph(Color b, Color p, Color s, Color t, char vis, char vague) {
        background = b;
        primary = p;
        secondary = s;
        tertiary = t;
        visibleSymbol = vis;
        vagueSymbol = vague;
    }

    void setVisibleSourceCoordinates(int r, int c) {
        visibleImageRow = r;
        visibleImageCol = c;
    }
    void setVagueSourceCoordinates(int r, int c) {
        vagueImageRow = r;
        vagueImageCol = c;
    }
    void addVisibleSymbol(Pair<Character> symPair) {
        visibleSymbols.add(symPair);
    }
    void addVagueSymbol(Pair<Character> symPair) {
        vagueSymbols.add(symPair);
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

    public char getSymbol(boolean vague) {
        return vague ? vagueSymbol : visibleSymbol;
    }

    public ArrayList<Pair<Character>> getSymbols(boolean vague) {
        return vague ? vagueSymbols : visibleSymbols;
    }

    public ArrayList<Pair<Color>> getBackgrounds() {
        return backgrounds;
    }

    public ArrayList<Pair<Color>> getPrimaries() {
        return primaries;
    }

    public ArrayList<Pair<Color>> getSecondaries() {
        return secondaries;
    }

    public ArrayList<Pair<Color>> getTertiaries() {
        return tertiaries;
    }

    public int getVisibleImageCol() {
        return visibleImageCol;
    }

    public int getVisibleImageRow() {
        return visibleImageRow;
    }

    public boolean allowGraphics() {
        return visibleImageRow >= 0 && visibleImageCol >= 0;
    }

    public ProtoGlyph dim() {
        ProtoGlyph dim = new ProtoGlyph(
                Chroma.dim(background),
                Chroma.dim(primary),
                Chroma.dim(secondary),
                Chroma.dim(tertiary),
                visibleSymbol,
                vagueSymbol
        );
        dim.vagueImageRow = vagueImageRow;
        dim.vagueImageCol = vagueImageCol;
        dim.visibleImageRow = visibleImageRow;
        dim.visibleImageCol = visibleImageCol;
        dim.vagueSymbols = new ArrayList<>(vagueSymbols);
        dim.visibleSymbols = new ArrayList<>(visibleSymbols);
        for (Pair<Color> bg : backgrounds) dim.backgrounds.add(new Pair<>(bg.probability, Chroma.dim(bg.element)));
        for (Pair<Color> pri : primaries) dim.primaries.add(new Pair<>(pri.probability, Chroma.dim(pri.element)));
        for (Pair<Color> sec : secondaries) dim.secondaries.add(new Pair<>(sec.probability, Chroma.dim(sec.element)));
        for (Pair<Color> ter : tertiaries) dim.tertiaries.add(new Pair<>(ter.probability, Chroma.dim(ter.element)));
        return dim;
    }
    public ProtoGlyph dark() {
        ProtoGlyph dark = new ProtoGlyph(
                Chroma.dark(background),
                Chroma.dark(primary),
                Chroma.dark(secondary),
                Chroma.dark(tertiary),
                visibleSymbol,
                vagueSymbol
        );
        dark.vagueImageRow = vagueImageRow;
        dark.vagueImageCol = vagueImageCol;
        dark.visibleImageRow = visibleImageRow;
        dark.visibleImageCol = visibleImageCol;
        dark.vagueSymbols = new ArrayList<>(vagueSymbols);
        dark.visibleSymbols = new ArrayList<>(visibleSymbols);
        for (Pair<Color> bg : backgrounds) dark.backgrounds.add(new Pair<>(bg.probability, Chroma.dark(bg.element)));
        for (Pair<Color> pri : primaries) dark.primaries.add(new Pair<>(pri.probability, Chroma.dark(pri.element)));
        for (Pair<Color> sec : secondaries) dark.secondaries.add(new Pair<>(sec.probability, Chroma.dark(sec.element)));
        for (Pair<Color> ter : tertiaries) dark.tertiaries.add(new Pair<>(ter.probability, Chroma.dark(ter.element)));
        return dark;
    }
    public ProtoGlyph ultra() {
        ProtoGlyph ultra = new ProtoGlyph(
                Chroma.ultra(background),
                Chroma.ultra(primary),
                Chroma.ultra(secondary),
                Chroma.ultra(tertiary),
                visibleSymbol,
                vagueSymbol
        );
        ultra.vagueImageRow = vagueImageRow;
        ultra.vagueImageCol = vagueImageCol;
        ultra.visibleImageRow = visibleImageRow;
        ultra.visibleImageCol = visibleImageCol;
        ultra.vagueSymbols = new ArrayList<>(vagueSymbols);
        ultra.visibleSymbols = new ArrayList<>(visibleSymbols);
        for (Pair<Color> bg : backgrounds) ultra.backgrounds.add(new Pair<>(bg.probability, Chroma.ultra(bg.element)));
        for (Pair<Color> pri : primaries) ultra.primaries.add(new Pair<>(pri.probability, Chroma.ultra(pri.element)));
        for (Pair<Color> sec : secondaries) ultra.secondaries.add(new Pair<>(sec.probability, Chroma.ultra(sec.element)));
        for (Pair<Color> ter : tertiaries) ultra.tertiaries.add(new Pair<>(ter.probability, Chroma.ultra(ter.element)));
        return ultra;
    }
}

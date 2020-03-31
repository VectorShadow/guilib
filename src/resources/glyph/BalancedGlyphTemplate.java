package resources.glyph;

import resources.continuum.Continuum;
import resources.continuum.Pair;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public class BalancedGlyphTemplate implements Serializable {
    private final ArrayList<Character> symbols;
    private final ArrayList<Color> backgroundColors;
    private final ArrayList<Color> foregroundColors;
    private final ArrayList<Color> secondaryColors;
    private final ArrayList<Color> tertiaryColors;
    private final int imageRow;
    private final int imageCol;

    public BalancedGlyphTemplate(
            ArrayList<Character> s,
            ArrayList<Color> b,
            ArrayList<Color> f
    ) {
        this(s, b, f, -1, -1);
    }
    public BalancedGlyphTemplate(
            ArrayList<Character> s,
            ArrayList<Color> b,
            ArrayList<Color> f,
            int r,
            int c
    ) {
        this(s, b, f, f, r, c);
    }
    public BalancedGlyphTemplate(
            ArrayList<Character> s,
            ArrayList<Color> b,
            ArrayList<Color> f,
            ArrayList<Color> f2,
            int r,
            int c
    ) {
        this(s, b, f, f2, f2, r, c);
    }
    public BalancedGlyphTemplate(
            ArrayList<Character> s,
            ArrayList<Color> b,
            ArrayList<Color> f,
            ArrayList<Color> f2,
            ArrayList<Color> f3,
            int r,
            int c
    ) {
        symbols = s;
        backgroundColors = b;
        foregroundColors = f;
        secondaryColors = f2;
        tertiaryColors = f3;
        imageRow = r;
        imageCol = c;
    }

    private BalancedGlyphTemplate(BalancedGlyphTemplate bgt) {
        ArrayList<Character> clonedSymbols = new ArrayList<>();
        ArrayList<Color> clonedBackgrounds = new ArrayList<>();
        ArrayList<Color> clonedForegrounds = new ArrayList<>();
        ArrayList<Color> clonedSecondaries = new ArrayList<>();
        ArrayList<Color> clonedTertiaries = new ArrayList<>();
        for (char c : bgt.symbols) clonedSymbols.add(c);
        for (Color b : bgt.backgroundColors) clonedBackgrounds.add(new Color(b.getRed(), b.getGreen(), b.getBlue()));
        for (Color f : bgt.foregroundColors) clonedForegrounds.add(new Color(f.getRed(), f.getGreen(), f.getBlue()));
        for (Color s : bgt.secondaryColors) clonedSecondaries.add(new Color(s.getRed(), s.getGreen(), s.getBlue()));
        for (Color t : bgt.tertiaryColors) clonedTertiaries.add(new Color(t.getRed(), t.getGreen(), t.getBlue()));
        symbols = clonedSymbols;
        backgroundColors = clonedBackgrounds;
        foregroundColors = clonedForegrounds;
        secondaryColors = clonedSecondaries;
        tertiaryColors = clonedTertiaries;
        imageRow = bgt.imageRow;
        imageCol = bgt.imageCol;
    }

    public ArrayList<Character> getSymbols() {
        return symbols;
    }

    public ArrayList<Color> getBackgroundColors() {
        return backgroundColors;
    }

    public ArrayList<Color> getForegroundColors() {
        return foregroundColors;
    }
    public ArrayList<Color> getSecondaryColors() {
        return secondaryColors;
    }
    public ArrayList<Color> getTertiaryColors() {
        return tertiaryColors;
    }

    public Character getBaseSymbol() {
        return symbols.get(0);
    }

    public Color getBaseBackgroundColor() {
        return backgroundColors.get(0);
    }

    public Color getBaseForegroundColor() {
        return foregroundColors.get(0);
    }
    public Color getBaseSecondaryColor() {
        return secondaryColors.get(0);
    }
    public Color getBaseTertiaryColor() {
        return tertiaryColors.get(0);
    }


    public int getImageRow() {
        return imageRow;
    }

    public int getImageCol() {
        return imageCol;
    }

    /**
     * Apply this BalancedGlyphTemplate to a String to return a GlyphString with the same characteristics.
     */
    public GlyphString apply(String s) {
        Continuum<Color> bgc = new Continuum<>(backgroundColors);
        Continuum<Color> fgc = new Continuum<>(foregroundColors);
        return new GlyphString(s, getBaseBackgroundColor(), getBaseForegroundColor(), bgc.getPairList(), fgc.getPairList());
    }

    /**
     * @return a GlyphBuilder which contains the basic elements of this BalancedGlyphTemplate, and can then be
     * further updated or built as is.
     */
    public GlyphBuilder partialBalancedGlyph(){
        GlyphBuilder gb =
                GlyphBuilder
                        .buildGlyph()
                        .setDefaults(
                                getBaseBackgroundColor(),
                                getBaseForegroundColor(),
                                getBaseSecondaryColor(),
                                getBaseTertiaryColor(),
                                getBaseSymbol()
                        );
        Continuum<Color> bgc = new Continuum<>(backgroundColors);
        Continuum<Color> fgc = new Continuum<>(foregroundColors);
        Continuum<Color> sgc = new Continuum<>(secondaryColors);
        Continuum<Color> tgc = new Continuum<>(tertiaryColors);
        Continuum<Character> sc = new Continuum<>(symbols);
        ArrayList<Pair<Color>> bgpl = bgc.getPairList();
        ArrayList<Pair<Color>> fgpl = fgc.getPairList();
        ArrayList<Pair<Color>> sgpl = sgc.getPairList();
        ArrayList<Pair<Color>> tgpl = tgc.getPairList();
        ArrayList<Pair<Character>> spl = sc.getPairList();
        for (Pair<Color> bcp : bgpl) gb.addBackgroundColor(bcp);
        for (Pair<Color> fcp : fgpl) gb.addForegroundColor(fcp);
        for (Pair<Color> scp : sgpl) gb.addSecondaryColor(scp);
        for (Pair<Color> tcp : tgpl) gb.addTertiaryColor(tcp);
        for (Pair<Character> sp : spl) gb.addSymbol(sp);
        gb.setImageRowAndColumn(imageRow, imageCol);
        return gb;
    }

    @Override
    public BalancedGlyphTemplate clone() {
        return new BalancedGlyphTemplate(this);
    }
}

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

    public BalancedGlyphTemplate(
            ArrayList<Character> s,
            ArrayList<Color> b,
            ArrayList<Color> f
    ) {
        symbols = s;
        backgroundColors = b;
        foregroundColors = f;
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

    public Character getBaseSymbol() {
        return symbols.get(0);
    }

    public Color getBaseBackgroundColor() {
        return backgroundColors.get(0);
    }

    public Color getBaseForegroundColor() {
        return foregroundColors.get(0);
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
                                getBaseSymbol()
                        );
        Continuum<Color> bgc = new Continuum<>(backgroundColors);
        Continuum<Color> fgc = new Continuum<>(foregroundColors);
        Continuum<Character> sc = new Continuum<>(symbols);
        ArrayList<Pair<Color>> bgpl = bgc.getPairList();
        ArrayList<Pair<Color>> fgpl = fgc.getPairList();
        ArrayList<Pair<Character>> spl = sc.getPairList();
        for (Pair<Color> bcp : bgpl) gb.addBackgroundColor(bcp);
        for (Pair<Color> fcp : fgpl) gb.addForegroundColor(fcp);
        for (Pair<Character> sp : spl) gb.addSymbol(sp);
        return gb;
    }
}

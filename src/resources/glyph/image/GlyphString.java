package resources.glyph.image;

import resources.continuum.Pair;
import resources.glyph.Glyph;
import resources.glyph.GlyphBuilder;
import resources.glyph.ProtoGlyphBuilder;

import java.awt.*;
import java.util.ArrayList;

/**
 * Support the List-based concept of GlyphStrings.
 */
public class GlyphString {
    ArrayList<Glyph> glyphString;
    String text;
    Color defaultBackground;
    Color defaultForeground;
    ArrayList<Pair<Color>> additionalBackgrounds;
    ArrayList<Pair<Color>> additionalForegrounds;

    public GlyphString(String t, Color dbg, Color dfg, ArrayList<Pair<Color>> abg, ArrayList<Pair<Color>> afg) {
        text = t;
        defaultBackground = dbg;
        defaultForeground = dfg;
        additionalBackgrounds = abg;
        additionalForegrounds = afg;
        glyphString = build(text, defaultBackground, defaultForeground, additionalBackgrounds, additionalForegrounds);
    }
    public GlyphString(String t, Color dbg, Color dfg) {
        this(t, dbg, dfg, new ArrayList<>(), new ArrayList<>());
    }

    /**
     * Concatenation constructor.
     * Creates a combined list of glyphs without construction data.
     * Used for combining glyphstrings with different data into a single string that retains the
     * individual glyph properties of its components.
     * Calls to subGlyphString will fail.
     */
    public GlyphString(GlyphString base, GlyphString appended) {
        glyphString = new ArrayList<>();
        for (Glyph g : base.asList()) glyphString.add(g);
        for (Glyph g : appended.asList()) glyphString.add(g);
    }
    public int size() {
        return glyphString.size();
    }
    public ArrayList<Glyph> asList(){
        return glyphString;
    }
    public GlyphString subGlyphString(int startInclusive, int endExclusive) {
        String s0 = "";
        for (Glyph g : this.asList()) s0 += g.getBaseChar();
        String s1 = s0.substring(startInclusive, endExclusive);
        return new GlyphString(s1, defaultBackground, defaultForeground, additionalBackgrounds, additionalForegrounds);
    }

    private static ArrayList<Glyph> build(
            String text,
            Color defaultBackground,
            Color defaultForeground,
            ArrayList<Pair<Color>> additionalBackgrounds,
            ArrayList<Pair<Color>> additionalForegrounds){
        ArrayList<Glyph> glyphString = new ArrayList<>();
        for (char c : text.toCharArray()) {
            GlyphBuilder gb = GlyphBuilder.buildGlyph();
            gb.setDefaults(defaultBackground, defaultForeground, c);
            for (Pair<Color> bp : additionalBackgrounds) {
                gb.addBackgroundColor(bp);
            }
            for (Pair<Color> fp : additionalForegrounds) {
                gb.addBackgroundColor(fp);
            }
            glyphString.add(gb.build());
        }
        return glyphString;
    }
    private static ArrayList<Glyph> build(
            String text,
            Color defaultBackground,
            Color defaultForeground
    ) {
        return build(
                text,
                defaultBackground,
                defaultForeground,
                new ArrayList<>(),
                new ArrayList<>()
        );
    }
}

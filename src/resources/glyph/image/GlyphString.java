package resources.glyph.image;

import resources.continuum.Pair;
import resources.glyph.Glyph;
import resources.glyph.GlyphBuilder;
import resources.glyph.ProtoGlyphBuilder;

import java.awt.*;
import java.util.ArrayList;

public class GlyphString {
    public static ArrayList<Glyph> build(
            String text,
            Color defaultBackground,
            Color defaultForeground,
            ArrayList<Pair<Color>> additionalBackgrounds,
            ArrayList<Pair<Color>> additionalForegrounds){
        ArrayList<Glyph> glyphString = new ArrayList<>();
        for (char c : text.toCharArray()) {
            ProtoGlyphBuilder pgb = ProtoGlyphBuilder.setDefaults(
                    c,
                    defaultBackground,
                    defaultForeground
            );
            for (Pair<Color> bp : additionalBackgrounds) {
                pgb.addBackground(bp);
            }
            for (Pair<Color> fp : additionalForegrounds) {
                pgb.addBackground(fp);
            }
            glyphString.add(
                    GlyphBuilder.build(
                        pgb.build()
                    )
            );
        }
        return glyphString;
    }
    public static ArrayList<Glyph> build(
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

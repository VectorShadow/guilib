package resources.chroma;

import resources.glyph.Glyph;
import resources.glyph.ascii.SimpleGlyph;

import java.awt.*;

public class ChromaSet {
    private final Color background;
    private final Color foreground;
    private final Color highlight;

    public ChromaSet(Color b, Color f, Color h) {
        background = b;
        foreground = f;
        highlight = h;
    }

    public Color getBackground() {
        return background;
    }

    public Color getForeground() {
        return foreground;
    }

    public Color getHighlight() {
        return highlight;
    }
    public Color subdueForeground() {
        return Chroma.dim(foreground);
    }
    public Glyph emptyGlyph() {
        return new SimpleGlyph(background, foreground, ' ');
    }
}

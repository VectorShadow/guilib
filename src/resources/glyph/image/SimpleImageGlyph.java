package resources.glyph.image;

import java.awt.*;

public class SimpleImageGlyph extends ImageGlyph {
    private Color background;
    private Color primary;
    private Color secondary;
    private Color tertiary;

    public SimpleImageGlyph(Color bg, Color p, Color s, Color t, int r, int c) {
        background = bg;
        primary = p;
        secondary = s;
        tertiary = t;
        row = r;
        col = c;
    }

    @Override
    protected Color getBackground() {
        return background;
    }

    @Override
    protected Color getPrimary() {
        return primary;
    }

    @Override
    protected Color getSecondary() {
        return secondary;
    }

    @Override
    protected Color getTertiary() {
        return tertiary;
    }

    @Override
    public Color getBaseColor() {
        return background;
    }

    @Override
    public Color getFaceColor() {
        return primary;
    }
}

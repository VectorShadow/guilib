package resources.glyph.image;

import resources.continuum.Continuum;

import java.awt.*;

public class ContinuumImageGlyph extends ImageGlyph {

    Continuum<Color> background;
    Continuum<Color> primary;
    Continuum<Color> secondary;
    Continuum<Color> tertiary;

    public ContinuumImageGlyph(Continuum bg, Continuum p, Continuum s, Continuum t, int r, int c) {
        background = bg;
        primary = p;
        secondary = s;
        tertiary = t;
        row = r;
        col = c;
    }

    @Override
    protected Color getBackground() {
        return background.getValue(RNG);
    }

    @Override
    protected Color getPrimary() {
        return primary.getValue(RNG);
    }

    @Override
    protected Color getSecondary() {
        return secondary.getValue(RNG);
    }

    @Override
    protected Color getTertiary() {
        return tertiary.getValue(RNG);
    }

    @Override
    public Color getBaseColor() {
        return background.getBase();
    }
}

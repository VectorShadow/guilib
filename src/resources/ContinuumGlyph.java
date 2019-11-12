package resources;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * A complex Glyph which renders non-deterministically from a pre-set selection of possible colors and symbols.
 */
public class ContinuumGlyph extends ASCIIImage implements Glyph{
    Continuum<Color> background;
    Continuum<Color> foreground;
    Continuum<Character> symbol;

    ContinuumGlyph(Continuum<Color> b, Continuum<Color> f, Continuum<Character> s) {
        background = b;
        foreground = f;
        symbol = s;
    }

    @Override
    protected Color getBackground() {
        return background.getValue(RNG);
    }

    @Override
    protected Color getForeground() {
        return foreground.getValue(RNG);
    }

    @Override
    protected char getSymbol() {
        return symbol.getValue(RNG);
    }

    @Override
    public BufferedImage getImage() {
        return render();
    }
}

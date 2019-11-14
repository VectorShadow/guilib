package resources;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * A Glyph is an object used to display to the screen.
 * It renders a colored character on a colored background.
 */

public interface Glyph {
    enum WordBreak{
        NO_BREAK,
        SPACE,
        TAB,
        RETURN
    }
    SimpleGlyph EMPTY_GLYPH = new SimpleGlyph(Color.BLACK, Color.WHITE, ' ');

    Random RNG = new Random();
    Color getBaseColor();
    BufferedImage getImage();
    WordBreak checkBreak();
}

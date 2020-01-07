package resources.glyph;

import resources.glyph.ascii.SimpleGlyph;

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
        RETURN;

        public static WordBreak evaluate(char c) {
            switch (c) {
                case ' ': return WordBreak.SPACE;
                case '\t': return WordBreak.TAB;
                case '\n': return WordBreak.RETURN;
                default: return WordBreak.NO_BREAK;
            }
        }
    }
    SimpleGlyph EMPTY_GLYPH = new SimpleGlyph(Color.BLACK, Color.WHITE, ' ');

    Random RNG = new Random();
    char getBaseChar();
    Color getBaseColor();
    Color getFaceColor();
    BufferedImage getImage();
    WordBreak checkBreak();
}

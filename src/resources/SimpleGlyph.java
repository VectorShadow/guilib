package resources;

import java.awt.*;
import java.awt.image.BufferedImage;


/**
 * A simply Glyph which renders deterministically.
 */
public class SimpleGlyph extends ASCIIImage implements Glyph {

    Color background;
    Color foreground;
    char symbol;

    SimpleGlyph(Color b, Color f, char s){
        background = b;
        foreground = f;
        symbol = s;
    }

    @Override
    protected Color getBackground() {
        return background;
    }

    @Override
    protected Color getForeground() {
        return foreground;
    }

    @Override
    protected char getSymbol() {
        return symbol;
    }

    @Override
    public Color getBaseColor() {
        return background;
    }

    @Override
    public BufferedImage getImage() {
        return render();
    }

    @Override
    public WordBreak checkBreak() {
        switch (symbol) {
            case ' ': return WordBreak.SPACE;
            case '\t': return WordBreak.TAB;
            case '\n': return WordBreak.RETURN;
            default: return WordBreak.NO_BREAK;
        }
    }
}

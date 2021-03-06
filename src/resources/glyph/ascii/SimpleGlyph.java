package resources.glyph.ascii;

import resources.glyph.Glyph;

import java.awt.*;
import java.awt.image.BufferedImage;


/**
 * A simply Glyph which renders deterministically.
 */
public class SimpleGlyph extends ASCIIImage implements Glyph {

    Color background;
    Color foreground;
    char symbol;

    public SimpleGlyph(Color b, Color f, char s){
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
    public char getBaseChar() {
        return getSymbol();
    }

    @Override
    public Color getBaseColor() {
        return background;
    }

    @Override
    public Color getFaceColor() {
        return foreground;
    }

    @Override
    public BufferedImage getImage() {
        return render();
    }

    @Override
    public WordBreak checkBreak() {
        return WordBreak.evaluate(symbol);
    }
}

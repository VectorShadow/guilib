package resources;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * A Glyph is an object used to display to the screen.
 * It renders a colored character on a colored background.
 */

public interface Glyph {
    Random RNG = new Random();
    BufferedImage getImage(boolean fullScreen);
}

package resources.chroma;

import java.awt.*;

/**
 * Provide a basic, easily distinguishable color scheme.
 * Permits 15 basic colors, and 2 additional gradients of 14 of those(exluding Black),
 * for a total of 43 distinct colors.
 */
public class Chroma {
    public static final Color WHITE = new Color(255, 255, 255);
    public static final Color GREY = new Color(127, 127, 127);
    public static final Color BLACK = new Color(0, 0, 0);

    public static final Color RED = new Color(255, 0, 0);
    public static final Color GREEN = new Color(0, 255, 0);
    public static final Color BLUE = new Color(0, 0, 255);

    public static final Color YELLOW = new Color(255, 255, 0);
    public static final Color MAGENTA = new Color(255, 0, 255);
    public static final Color CYAN = new Color(0, 255, 255);

    public static final Color ORANGE = new Color(255, 127, 0);
    public static final Color CRIMSON = new Color(255, 0, 127);

    public static final Color SLIME = new Color(127, 255, 0);
    public static final Color AQUA = new Color(0, 255, 127);

    public static final Color VIOLET = new Color(127, 0, 255);
    public static final Color TURQUOISE = new Color(0, 127, 255);

    public static final Color ULTRARED = new Color(255, 127, 127);
    public static final Color ULTRAGREEN = new Color(127, 255, 127);
    public static final Color ULTRABLUE = new Color(127, 127, 255);
    public static final Color ULTRAYELLOW = new Color(255, 255, 127);
    public static final Color ULTRAMAGENTA = new Color(255, 127, 255);
    public static final Color ULTRACYAN = new Color(127, 255, 255);

    private static final double DIM_FACTOR = 2.0 / 3.0;
    private static final double DARK_FACTOR = 1.0 / 3.0;

    private static int dim(int rgbValue) {
        return (int)((double)rgbValue * DIM_FACTOR);
    }
    private static int dark(int rgbValue) {
        return (int)((double)rgbValue * DARK_FACTOR);
    }

    public static Color dim(Color c) {
        return new Color(dim(c.getRed()), dim(c.getGreen()), dim(c.getBlue()));
    }
    public static Color dark(Color c) {
        return new Color(dark(c.getRed()), dark(c.getGreen()), dark(c.getBlue()));
    }
    public static Color ultra(Color c) {
        if (c.equals(RED)) return ULTRARED;
        if (c.equals(GREEN)) return ULTRAGREEN;
        if (c.equals(BLUE)) return ULTRABLUE;
        if (c.equals(YELLOW)) return ULTRAYELLOW;
        if (c.equals(MAGENTA)) return ULTRAMAGENTA;
        if (c.equals(CYAN)) return ULTRACYAN;
        return BLACK;
    }
}
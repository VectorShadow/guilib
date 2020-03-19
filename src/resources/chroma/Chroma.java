package resources.chroma;

import java.awt.*;

/**
 * Provide a basic, easily distinguishable color scheme.
 * Permits 15 basic colors, and 2 additional gradients of 14 of those(excluding Black),
 * for a total of 43 distinct colors.
 * Added 14 new colors including metallic and elemental.
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

    public static final Color BLOOD_RED = new Color(159, 31, 15);
    public static final Color VENOM_GREEN = new Color(15, 159, 31);
    public static final Color ICHOR_BLUE = new Color(31, 15, 159);

    public static final Color BROWN = new Color(87, 63, 39);
    public static final Color VIRIDIAN = new Color(39, 87, 63);
    public static final Color ANTIAMBER = new Color(63, 39, 87);

    public static final Color METALLIC_IRON = new Color(71, 63, 67);
    public static final Color METALLIC_BRONZE = new Color(191, 95, 47);
    public static final Color METALLIC_SILVER = new Color(143, 175, 191);
    public static final Color METALLIC_GOLD = new Color(207, 175, 79);
    public static final Color METALLIC_PLATINUM = new Color(143, 255, 215);

    public static final Color ELEMENTAL_FLAME = new Color(255, 47, 31);
    public static final Color ELEMENTAL_FROST = new Color(104, 143, 255);
    public static final Color ELEMENTAL_LIGHTNING = new Color(231, 207, 79);
    public static final Color ELEMENTAL_ACID = new Color(47, 95, 31);

    public static final Color BEIGE = new Color(191, 143, 95);
    public static final Color COPPER = new Color(63, 143, 95);
    public static final Color COBALT = new Color(71, 95, 143);
    public static final Color RUST = new Color(191, 95, 63);

    public static final Color FLESH = new Color(191, 127, 127);


    private static final double DIM_FACTOR = 2.0 / 5.0;
    private static final double DARK_FACTOR = 1.0 / 5.0;

    private static int dim(int rgbValue) {
        return (int)((double)rgbValue * DIM_FACTOR);
    }
    private static int dark(int rgbValue) {
        return (int)((double)rgbValue * DARK_FACTOR);
    }

    public static Color dim(Color c) {
        return c == null ? null : new Color(dim(c.getRed()), dim(c.getGreen()), dim(c.getBlue()));
    }
    public static Color dark(Color c) {
        return c == null ? null : new Color(dark(c.getRed()), dark(c.getGreen()), dark(c.getBlue()));
    }
    public static Color ultra(Color c) {
        if (c.equals(RED) || c.equals(ORANGE) || c.equals(CRIMSON)) return ULTRARED;
        if (c.equals(GREEN) || c.equals(SLIME) || c.equals(AQUA)) return ULTRAGREEN;
        if (c.equals(BLUE) || c.equals(VIOLET) || c.equals(TURQUOISE)) return ULTRABLUE;
        if (c.equals(YELLOW)) return ULTRAYELLOW;
        if (c.equals(MAGENTA)) return ULTRAMAGENTA;
        if (c.equals(CYAN)) return ULTRACYAN;
        return BLACK;
    }
}
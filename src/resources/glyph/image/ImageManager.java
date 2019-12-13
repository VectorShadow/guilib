package resources.glyph.image;

import resources.render.RenderContext;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * Manages graphical rendering.
 * Load tilesets by calling loadGraphics for each render context to be supported, with the corresponding graphics file.
 */
public class ImageManager {

    private static HashMap<RenderContext, BufferedImage> tilesets = new HashMap<>();

    public static void loadGraphics(RenderContext rc, File file) throws IOException {
        tilesets.put(rc, ImageIO.read(file));
    }
    public static boolean hasGraphics() {
        return !tilesets.isEmpty();
    }

    public static BufferedImage imageAt(int row, int col, RenderContext rc) {
        if (!tilesets.containsKey(rc))
            throw new IllegalArgumentException("Graphics for RenderContext " + rc + " have not been loaded.");
        Dimension size = rc.imageSize();
        BufferedImage src = tilesets.get(rc);
        BufferedImage out = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_ARGB);
        for (int i = 0; i < size.height; ++i) {
            for (int j = 0; j < size.width; ++j) {
                int rgb = (tilesets.get(rc)).getRGB(j + (col * size.width), i + (row * size.height));
                out.setRGB(j, i, rgb);
            }
        }
        return out;
    }
    public static boolean exists(int row, int col, RenderContext rc) {
        if (!tilesets.containsKey(rc)) return false;
        int h = rc.imageSize().height;
        int w = rc.imageSize().width;
        return h * row + h <= (tilesets.get(rc)).getHeight() && w * col + w <= (tilesets.get(rc)).getWidth();
    }
}

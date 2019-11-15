package resources.glyph.image;

import resources.render.RenderContext;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Manages graphical rendering.
 * todo - handle multiple output modes! right now we assume we can only render tiles.
 * todo - maybe adopt a similar approach to Renderer? static but extensible?
 */
public class ImageManager {

    //hack - assume tiles only
    private static BufferedImage imageMapFullscreen = null;
    private static BufferedImage imageMapWindowed = null;

    //hack - assume tiles only
    public static void loadGraphics(File fs, File win) throws IOException {
        imageMapFullscreen = ImageIO.read(fs);
        imageMapWindowed = ImageIO.read(win);
    }

    //todo - this has the right approach to non-tile modes, taking a render context, but still uses the other hacks
    public static BufferedImage imageAt(int row, int col, RenderContext rc) {
        Dimension size = rc.imageSize();
        BufferedImage bi = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_ARGB);
        for (int i = 0; i < size.height; ++i) {
            for (int j = 0; j < size.width; ++j) {
                int rgb = (rc.isFullScreen() ? imageMapFullscreen : imageMapWindowed)
                        .getRGB(j + (col * size.width), i + (row * size.height));
                bi.setRGB(j, i, rgb);
            }
        }
        return bi;
    }
    //megahack - assume tiles only
    public static boolean exists(int row, int col) {
        return 32 * row + 32 <= imageMapFullscreen.getHeight() &&
                32 * col + 32 <= imageMapFullscreen.getWidth() &&
                16 * row + 16 <= imageMapWindowed.getHeight() &&
                16 * col + 16 <= imageMapWindowed.getWidth();
    }
}

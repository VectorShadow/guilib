package resources;

import java.awt.image.BufferedImage;

public class ImageGlyph implements Glyph {

    private BufferedImage image;

    ImageGlyph(BufferedImage bi) {
        image = bi;
    }

    @Override
    public BufferedImage getImage(boolean isTile, boolean fullScreen) {
        return image; //todo - upscale the image if fullScreen
    }
}

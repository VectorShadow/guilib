package resources;

import java.awt.image.BufferedImage;

public class ImageGlyph implements Glyph {

    private BufferedImage image;

    ImageGlyph(BufferedImage bi) {
        image = bi;
    }

    @Override
    public BufferedImage getImage() {
        return image;
    }
}

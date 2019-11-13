package resources;

import java.awt.image.BufferedImage;

public class ImageGlyph implements Glyph {

    private BufferedImage image;

    ImageGlyph(BufferedImage bi) {
        image = bi;
    }

    @Override
    public BufferedImage getImage() {
        return image; //todo - upscale the image if fullScreen
    }

    @Override
    public WordBreak checkBreak() {
        //todo - handle images which might represent chars? until then, assume no break
        return WordBreak.NO_BREAK;
    }
}

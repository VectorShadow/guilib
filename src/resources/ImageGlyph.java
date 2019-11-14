package resources;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageGlyph implements Glyph {

    private BufferedImage image;
    //todo - image recoloration to match Simple and Continuum Glyphs - backgroud, primary, secondary, tertiary

    ImageGlyph(BufferedImage bi) {
        image = bi;
    }

    @Override
    public Color getBaseColor() {
        //todo - once we update these to work like Simple/Continuum Glyphs, there will be a background color or continuum with base... return this
        throw new UnsupportedOperationException();
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

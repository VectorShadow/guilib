package resources.glyph.image;

import resources.render.Renderer;
import resources.glyph.Glyph;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class ImageGlyph implements Glyph {

    final static int BACKGROUND = Color.WHITE.getRGB();
    final static int PRIMARY = Color.RED.getRGB();
    final static int SECONDARY = Color.GREEN.getRGB();
    final static int TERTIARY = Color.BLUE.getRGB();

    protected int row;
    protected int col;

    protected abstract Color getBackground();

    protected abstract Color getPrimary();
    protected abstract Color getSecondary();
    protected abstract Color getTertiary();

    @Override
    public BufferedImage getImage() {
        BufferedImage base = ImageManager.imageAt(row, col, Renderer.getRenderContext());
        BufferedImage recolor = new BufferedImage(base.getWidth(), base.getHeight(), base.getType());
        int bg = getBackground().getRGB();
        int p = getPrimary().getRGB();
        int s = getSecondary().getRGB();
        int t = getTertiary().getRGB();
        for (int i = 0; i < base.getHeight(); ++i) {
            for (int j = 0; j < base.getWidth(); ++j) {
                int rgb = base.getRGB(j, i);
                recolor.setRGB(j, i, rgb == BACKGROUND ? bg : rgb == PRIMARY ? p : rgb == SECONDARY ? s : rgb == TERTIARY ? t : rgb);
            }
        }
        return recolor;
    }

    @Override
    public WordBreak checkBreak() {
        //todo - handle images which might represent chars? until then, assume no break
        return WordBreak.NO_BREAK;
    }
}

package resources;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Zone {
    int rowOrigin, colOrigin, numRows, numCols;
    private OutputMode mode;
    private GlyphMap glyphMap;

    public Zone(int ro, int co, int nr, int nc, OutputMode om) {
        rowOrigin = ro;
        colOrigin = co;
        numRows = nr;
        numCols = nc;
        mode = om;
        glyphMap = new GlyphMap(
                Renderer.countUnits(getDimension(), om).height,
                Renderer.countUnits(getDimension(), om).width
        );
    }
    public Dimension getDimension() {
        return new Dimension(numCols - colOrigin, numRows - rowOrigin);
    }
    public void draw(boolean fullScreen, BufferedImage paneImage) {
        RenderContext rc = mode.generateContext(fullScreen);
        Renderer.setRenderContext(rc);
       // BufferedImage lg = new BufferedImage(getDimension().width, getDimension().height, BufferedImage.TYPE_INT_ARGB);
        BufferedImage unitImage;
        for (int i = 0; i < glyphMap.rows; ++i) {
            for (int j = 0; j < glyphMap.cols; ++j) {
                unitImage = glyphMap.getGlyph(i, j).getImage();
                for (int k = 0; k < unitImage.getHeight(); ++k) {
                    for (int l = 0; l < unitImage.getWidth(); ++l) {
                        paneImage.setRGB(
                                colOrigin + (j * unitImage.getWidth() + l),
                                rowOrigin + (i * unitImage.getHeight() + k),
                                unitImage.getRGB(l, k)
                        );
                    }
                }
            }
        }
    }
    //todo - lots of methods for conversions
}

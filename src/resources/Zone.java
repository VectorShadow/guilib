package resources;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Zone {
    int rowOrigin, colOrigin, numRows, numCols;
    private OutputMode mode;
    private GlyphMap glyphMap;

    public Zone(
            double verticalOriginPct,
            double verticalSizePct,
            double horizontalOriginPct,
            double horizontalSizePct,
            OutputMode om
    ) {
        Dimension pixels = Renderer.countPixels();
        rowOrigin = (int)(verticalOriginPct * pixels.getHeight());
        colOrigin = (int)(horizontalOriginPct * pixels.getWidth());
        numRows = (int)(verticalSizePct * pixels.getHeight());
        numCols = (int)(horizontalSizePct * pixels.getWidth());
        mode = om;
        glyphMap = new GlyphMap(
                Renderer.countUnits(getDimension(), om).height,
                Renderer.countUnits(getDimension(), om).width
        );
    }
    public Dimension getDimension() {
        return new Dimension(numCols, numRows);
    }
    public void draw(boolean fullScreen, BufferedImage paneImage) {
        RenderContext rc = mode.generateContext(fullScreen);
        Renderer.setRenderContext(rc);
        BufferedImage unitImage;
        int pixelRow, pixelCol;
        for (int i = 0; i < glyphMap.rows; ++i) {
            for (int j = 0; j < glyphMap.cols; ++j) {
                unitImage = glyphMap.getGlyph(i, j).getImage();
                for (int k = 0; k < unitImage.getHeight(); ++k) {
                    for (int l = 0; l < unitImage.getWidth(); ++l) {
                        pixelCol = (colOrigin / (fullScreen ? 1 : 2)) + (j * unitImage.getWidth() + l);
                        pixelRow = (rowOrigin / (fullScreen ? 1 : 2))+ (i * unitImage.getHeight() + k);
                        if (pixelCol >= paneImage.getWidth() || pixelRow >= paneImage.getHeight() || pixelCol < 0 || pixelRow < 0) continue;
                        paneImage.setRGB(
                                pixelCol,
                                pixelRow,
                                unitImage.getRGB(l, k)
                        );
                    }
                }
            }
        }
    }
    public void print(int row, int col, Glyph g) {
        glyphMap.setGlyph(row, col, g);
    }
    public void clear() {
        glyphMap.initialize();
    }
    //todo - lots of methods for conversions
}

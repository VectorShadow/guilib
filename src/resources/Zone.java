package resources;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

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
                zoneRows(),
                zoneCols()
        );
    }
    public Dimension getDimension() {
        return new Dimension(numCols, numRows);
    }
    public int zoneRows() {
        return Renderer.countUnits(getDimension(), mode).height;
    }
    public int zoneCols() {
        return Renderer.countUnits(getDimension(), mode).width;
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
    public void print(int row, int col, ArrayList<Glyph> g) {
        ArrayList<Glyph> glyphString = g;
        ArrayList<Glyph> remainder;
        ArrayList<Glyph> nextWord;
        Glyph nextGlyph;
        Glyph.WordBreak wordBreak = Glyph.WordBreak.NO_BREAK;
        int totalLines = 1;
        int stringIndex;
        Point printCursor = new Point(col, row);
        validate(printCursor);
        boolean done = false;
        do {
            //reset temporary values
            stringIndex = 0;
            remainder = new ArrayList<>();
            nextWord = new ArrayList<>();
            //get the next word
            do {
                //don't go past the end of the input
                if (stringIndex >= glyphString.size()) {
                    done = true;
                    break;
                }
                nextGlyph = glyphString.get(stringIndex++);
                wordBreak = nextGlyph.checkBreak();
                //if we have a valid symbol, append it to the word we are building
                if (wordBreak == Glyph.WordBreak.NO_BREAK) nextWord.add(nextGlyph);
            } while (wordBreak == Glyph.WordBreak.NO_BREAK);
            //collect the remainder of the input string and recycle it
            while (stringIndex < glyphString.size()) remainder.add(glyphString.get(stringIndex++));
            glyphString = remainder;
            //if there's nothing left to recycle, we're done
            if (glyphString.size() == 0) done = true;
            //check that the next word can fit within this Zone
            if (!fitWord(nextWord.size(), 0)) break;
            //check that the next word can fit on what's left of this line
            if (!fitWord(nextWord.size(), printCursor.x)) {
                //move to the next line
                printCursor = lineReturn(printCursor);
                //check that this line is valid for this zone's GSP
                if (!validLine(printCursor.y, totalLines++)) break;
                //ignore any pending wordbreaks
                wordBreak = Glyph.WordBreak.NO_BREAK;
            }
            //print each glyph in the current word, moving the cursor accordingly
            for (Glyph glyph : nextWord) {
                print(printCursor.y, printCursor.x, glyph);
                printCursor = advanceCursor(printCursor, 1);
            }
            //move the cursor according to the last word break
            switch (wordBreak) {
                case NO_BREAK: break;
                case SPACE: printCursor = advanceCursor(printCursor, 1); break;
                case TAB: printCursor = advanceCursor(printCursor, mode.getGlyphStringProtocol().getTabLength()); break;
                case RETURN: printCursor = lineReturn(printCursor); totalLines++; break;
                default: throw new IllegalStateException("Unhandled Word Break case: " + wordBreak);
            }
            //check that the cursor is still on a valid line
            if (!validLine(printCursor.y, totalLines)) done = true;
        } while (!done);
    }
    /**
     * Ensure the provided origin is valid under this zone's GSP.
     */
    private void validate(Point p) {
        int border = mode.getGlyphStringProtocol().getBorderSize();
        if (p.x < border || p.y < border || p.x >= zoneCols() - border || p.y >= zoneRows() - border)
            throw new IllegalArgumentException("Point " + p + " is not a valid output coordinate for this zone. " +
                    "Zone bounds: (" + border + ", " + border + ") -> (" + (zoneCols() - border) + ", "
                    + (zoneRows() - border) + ").");
    }
    /**
     * Move the print cursor the specified number of places forward.
     */
    private Point advanceCursor(Point cursor, int places) {
        return new Point(cursor.x + places, cursor.y);
    }
    /**
     * Move the print cursor to the appropriate point on the next line.
     */
    private Point lineReturn(Point at) {
        GlyphStringProtocol gsp = mode.getGlyphStringProtocol();
        return new Point(gsp.getBorderSize(), at.y + 1);
    }

    /**
     * Check if a word of specified length will fit onto a single row of the GlyphMap if started at specified origin.
     */
    private boolean fitWord(int length, int origin) {
        GlyphStringProtocol gsp = mode.getGlyphStringProtocol();
        if (origin < gsp.getBorderSize()) origin = gsp.getBorderSize();
        return origin + length <= zoneCols() - gsp.getBorderSize();
    }

    /**
     * Check if a line is valid for printing under the current GSP
     */
    private boolean validLine(int outputLine, int totalLines) {
        GlyphStringProtocol gsp = mode.getGlyphStringProtocol();
        return outputLine < zoneRows() - gsp.getBorderSize()
                && totalLines <= gsp.getMaxLines();
    }
    public void clear() {
        glyphMap.initialize();
    }
    //todo - lots of methods for conversions
}

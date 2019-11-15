package resources.glyph;

/**
 * Defines how a Zone handles Glyph string printing.
 */
public class GlyphStringProtocol {

    private final int borderSize;
    private final int maxLength;
    private final int maxLines;
    private final int tabLength;

    public GlyphStringProtocol(int borderSize, int maxLength, int maxLines, int tabLength) {
        this.borderSize = borderSize;
        this.maxLength = maxLength;
        this.maxLines = maxLines;
        this.tabLength = tabLength;
    }

    public int getBorderSize() {
        return borderSize;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public int getMaxLines() {
        return maxLines;
    }

    public int getTabLength() {
        return tabLength;
    }
}

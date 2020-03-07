package resources.glyph;

public class GlyphMap {
    final int rows, cols;
    Glyph[][] map;
    Glyph background = Glyph.EMPTY_GLYPH;
    Glyph border = null;

    public GlyphMap(int r, int c){
        rows = r;
        cols = c;
        map = new Glyph[rows][cols];
        initialize();
    }
    public void initialize() {
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if ((i == 0 || j == 0 || i == rows - 1 || j == cols - 1) && border != null)
                    setGlyph(i, j, border);
                else
                    setGlyph(i, j, background);
            }
        }
    }
    public void setGlyph(int r, int c, Glyph g) {
        map[r][c] = g;
    }
    public Glyph getGlyph(int r, int c) {
        return map[r][c];
    }
    public void setBackground(Glyph g) {
        background = g;
        initialize();
    }
    public void setBorder(Glyph g) {
        border = g;
        initialize();
    }

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }

    public Glyph getBackground() {
        return background;
    }

    public Glyph getBorder() {
        return border;
    }
}

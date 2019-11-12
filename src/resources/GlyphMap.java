package resources;

public class GlyphMap {
    final int rows, cols;
    Glyph[][] map;
    public GlyphMap(int r, int c){
        rows = r;
        cols = c;
        map = new Glyph[rows][cols];
        initialize();
    }
    public void initialize() {
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                setGlyph(i, j, Glyph.EMPTY_GLYPH);
            }
        }
    }
    public void setGlyph(int r, int c, Glyph g) {
        map[r][c] = g;
    }
    public Glyph getGlyph(int r, int c) {
        return map[r][c];
    }
}

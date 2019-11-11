package resources;

public class Zone {
    int rowOrigin, colOrigin, numRows, numCols;
    private OutputMode mode;

    public Zone(int ro, int co, int nr, int nc, OutputMode om) {
        rowOrigin = ro;
        colOrigin = co;
        numRows = nr;
        numCols = nc;
        mode = om;
    }
    //todo - lots of methods for conversions
}

package contract;

import resources.Glyph;
import resources.OutputMode;
import resources.RenderContext;

import java.util.ArrayList;

/**
 * Defines the contract for a GUI. Every sort of GUI should conform to this contract.
 */
public interface Gui {
    /**
     * Admin methods.
     *
     */
    //terminate the GUI environment
    void close();
    //set the GUI to fullscreen or windowed
    void setFullScreen(boolean fullScreen);
    /**
     * Info methods.
     *
     */
    //todo - here() - return a triple specifying the zone, row, and column given a pixel coordinate
    /**
     * Setup methods.
     *
     */
    //add a zone with the specified parameters
    void addZone(int rowOrigin, int colOrigin, int numRows, int numCols, OutputMode om);
    //stop displaying the specified Zone
    void hideZone(int zoneID);
    //remove the specified Zone
    void removeZone(int zoneID);
    //resume displaying the specified Zone
    void showZone(int zoneID);
    /**
     * Output methods.
     * Clear removes Glyphs from the screen.
     * Print adds Glyphs to the screen.
     * Redraw paints the screen with an image derived from all added Glyphs
     */
    //clear the entire screen
    void clear();
    //clear a specific Zone
    void clear(int zone);
    //set a Glyph on the screen
    void print(int row, int col, Glyph g);
    //set a String of Glyphs on the screen
    void print(int row, int col, ArrayList<Glyph> g);
    //set a Glyph in a specific Zone
    void print(int zone, int row, int col, Glyph g);
    //set a String of Glyphs in a specific Zone
    void print(int zone, int row, int col, ArrayList<Glyph> g);
    //draw all currently set Glyphs to the monitor
    void redraw();
}

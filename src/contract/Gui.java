package contract;

import contract.input.InputDialog;
import contract.menu.Menu;
import resources.chroma.ChromaSet;
import resources.glyph.Glyph;
import resources.glyph.GlyphString;
import resources.render.OutputMode;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.WindowListener;

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
    //toggle the fullscreen/windowed mode of the gui
    void toggleFullScreen();
    /**
     * Info methods.
     *
     */
    //todo - here() - return a triple specifying the zone, row, and column given a pixel coordinate
    /**
     * Setup methods.
     *
     */
    //add a io.display channel
    public int addChannel(OutputMode om);
    //add a zone with the specified parameters, and return the number of zones now
    public int addZone(
            int channelID,
            double verticalOriginPct,
            double verticalSizePct,
            double horizontalOriginPct,
            double horizontalSizePct,
            OutputMode om);
    //remove the specified Zone
    void removeZone(int channelID, int zoneID);
    //set the background glyph for the main Zone of the specified Channel
    void setBackground(int channelID, Glyph g);
    //set the background glyph for the specified Zone
    void setBackground(int channelID, int zoneID, Glyph g);
    //set the border glyph for the main Zone of the specified Channel
    void setBorder(int channelID, Glyph g);
    //set the border glyph for the specified Zone
    void setBorder(int channelID, int zoneID, Glyph g);
    //add a KeyListener to this GUI
    void addKeyListener(KeyListener kl);
    //add a WindowListener to this GUI
    void addWindowListener(WindowListener wl);
    /**
     * Output methods.
     * Clear removes Glyphs from the screen.
     * Print adds Glyphs to the screen.
     * Redraw paints the screen with an image derived from all added Glyphs
     */
    //return the active channel
    int activeChannel();
    //change the active channel
    void changeChannel(int newChannelID);
    //clear the entire screen of the current display channel
    void clear();
    //clear a specific Zone within the current display channel
    void clear(int zone);
    //set a Glyph on the screen within the current display channel
    void print(int row, int col, Glyph g);
    //set a String of Glyphs on the screen within the current display channel,
    // and return the row and column of the last Glyph
    Point print(int row, int col, GlyphString gs);
    //set a Glyph in a specific Zone within the current display channel
    void print(int zone, int row, int col, Glyph g);
    //set a String of Glyphs in a specific Zone within the current display channel,
    // and return the row and column of the last Glyph
    Point print(int zone, int row, int col, GlyphString gs);
    //set a String of Glyphs aligned to the center of the screen within the current display channel
    Point printCentered(int row, GlyphString gs);
    //set a String of Glyphs aligned to the center of the zone within the current display channel
    Point printCentered(int zone, int row, GlyphString gs);
    //draw all currently set Glyphs within the current io.display channel to the monitor
    void redraw();
    //set an icon image for the windowed frame
    void setIcon(String pathToIconImage);
    //set a title for the windowed frame
    void setTitle(String title);
    //print a centered menu within the current display channel
    void printMenu(int row, Menu menu, ChromaSet cs);
    //print a centered menu in the specified zone within the current display channel
    void printMenu(int zone, int row, Menu menu, ChromaSet cs);
    //print a centered input dialog within the current display channel
    void printDialog(InputDialog dialog);
    //print a centered input dialog in the specified zone within the current display channel
    void printDialog(int zone, InputDialog dialog);
    /**
     * Information methods.
     */
    //get the number of rows in the gui
    int countRows();
    //get the number of columns in the gui
    int countColumns();
    //get the number of rows in the specified zone
    int countRows(int zone);
    //get the number of columns in the specified zone
    int countColumns(int zone);
    //get the row index for a certain percentage of the screen
    int rowAtPercent(double percent);
    //get the column index for a certain percentage of the screen
    int colAtPercent(double percent);
    //get the row index for a certain percentage of the zone
    int rowAtPercent(int zone, double percent);
    //get the column index for a certain percentage of the zone
    int colAtPercent(int zone, double percent);
    //get the number of channels
    int countChannels();
    //get the number of zones in the specified channel
    int countZones(int channelId);
    //return the value of the maximum column which can be displayed to in the current channel and zone
    int maxCol();
    //return the value of the minimum column which can be displayed to in the current channel and zone
    int minCol();
    //return the value of the maximum row which can be displayed to in the current channel and zone
    int maxRow();
    //return the value of the minimum row which can be displayed to in the current channel and zone
    int minRow();
    //return the value of the maximum column which can be displayed to in the current channel and specified zone
    int maxCol(int zoneID);
    //return the value of the minimum column which can be displayed to in the current channel and specified zone
    int minCol(int zoneID);
    //return the value of the maximum row which can be displayed to in the current channel and specified zone
    int maxRow(int zoneID);
    //return the value of the minimum row which can be displayed to in the current channel and specified zone
    int minRow(int zoneID);
    //return the main zone index of the glyph just outside the specified zone - row or col index, before or after zone
    int from(int channelID, int zoneID, boolean row, boolean before);
}

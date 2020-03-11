package contract;

import resources.glyph.Glyph;
import resources.glyph.GlyphString;
import resources.render.OutputMode;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Channel {
    private Zone mainZone;
    private ArrayList<Zone> zones;

    public Channel(OutputMode mainZoneMode) {
        mainZone = new Zone(
                0.0,
                1.0,
                0.0,
                1.0,
                mainZoneMode
        );
        zones = new ArrayList<>();
    }

    public OutputMode mainOutputMode() {
        return mainZone.outputMode();
    }

    public int addZone(Zone z){
        zones.add(z);
        return zones.size() - 1;
    }
    public void removeZone(int zoneID){
        zones.remove(zoneID);
    }
    public void setBackground(Glyph g){
        mainZone.setBackground(g);
    }
    public void setBackground(int zoneID, Glyph g){
        zones.get(zoneID).setBackground(g);
    }
    public void setBorder(Glyph g){
        mainZone.setBorder(g);
    }
    public void setBorder(int zoneID, Glyph g){
        zones.get(zoneID).setBorder(g);
    }
    public void clear() {
        mainZone.clear();
        for (Zone z : zones) z.clear();
    }

    public void clear(int zone) {
        zones.get(zone).clear();
    }

    public void print(int row, int col, Glyph g) {
        mainZone.print(row, col, g);
    }

    public Point print(int row, int col, GlyphString gs) {
        return mainZone.print(row, col, gs);
    }

    public void print(int zone, int row, int col, Glyph g) {
        zones.get(zone).print(row, col, g);
    }

    public Point print(int zone, int row, int col, GlyphString gs) {
        return zones.get(zone).print(row, col, gs);
    }

    public void printCentered(int row, GlyphString gs) {
        int c = mainZone.zoneCols();
        int l = gs.size();
        //don't print more than 1 line - cut the input short instead
        if (l >= c - 2) print(row, 1, gs.subGlyphString(0, c - 2));
        else print(row, c / 2 - l / 2, gs);
    }
    public void printCentered(int zone, int row, GlyphString gs) {
        Zone z = zones.get(zone);
        int c = z.zoneCols();
        int l = gs.size();
        //don't print more than 1 line - cut the input short instead
        if (l >= c - 2) print(zone, row, 1, gs.subGlyphString(0, c - 2));
        else print(zone, row, c / 2 - l / 2, gs);
    }
    public void draw(boolean fullScreen, BufferedImage bufferedImage) {
        mainZone.draw(fullScreen, true, bufferedImage);
        for (Zone z : zones) {
            z.draw(fullScreen, false, bufferedImage);
        }
    }
    public int countRows() {
        return mainZone.zoneRows();
    }
    public int countColumns() {
        return mainZone.zoneCols();
    }
    public int countRows(int zone) {
        return zones.get(zone).zoneRows();
    }
    public int countColumns(int zone) {
        return zones.get(zone).zoneCols();
    }
    private void checkPercent(double percent) {
        if (percent < 0.0 || percent > 1.0) throw new IllegalArgumentException("Percent " + percent + " out of bounds.");
    }
    public int rowAtPercent(double percent) {
        checkPercent(percent);
        return (int)(percent * countRows());
    }
    public int colAtPercent(double percent) {
        checkPercent(percent);
        return (int)(percent * countColumns());
    }
    public int rowAtPercent(int zone, double percent) {
        return (int)(percent * countRows(zone));
    }
    public int colAtPercent(int zone, double percent) {
        checkPercent(percent);
        return (int)(percent * countColumns(zone));
    }
    public int countZones() {
        return zones.size();
    }

    public int maxCol(){
        return mainZone.maxCol();
    }
    public int minCol(){
        return mainZone.minCol();
    }
    public int maxRow(){
        return mainZone.maxRow();
    }
    public int minRow(){
        return mainZone.minRow();
    }
    public int maxCol(int zoneID){
        return zones.get(zoneID).maxCol();
    }
    public int minCol(int zoneID){
        return zones.get(zoneID).minCol();
    }
    public int maxRow(int zoneID){
        return zones.get(zoneID).maxRow();
    }
    public int minRow(int zoneID){
        return zones.get(zoneID).minRow();
    }
}

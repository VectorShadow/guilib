//package resources;
//
//import java.awt.*;
//
//public abstract class DynamicSize {
//
//    private static final int DEFAULT_FULL_SCREEN_TILE_SIZE = 32;
//    private static final int DEFAULT_WINDOWED_TILE_SIZE = DEFAULT_FULL_SCREEN_TILE_SIZE / 2;
//    private static final int DEFAULT_FULL_SCREEN_TEXT_WIDTH = 18;
//    private static final int DEFAULT_WINDOWED_TEXT_WIDTH = DEFAULT_FULL_SCREEN_TEXT_WIDTH / 2;
//
//    protected boolean fullScreen;
//    protected RenderContext renderContext;
//
//    protected DisplayMode getDisplayMode() {
//        return GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode();
//    }
//    public static int getTileSize(boolean fullscreen){
//        return fullscreen ? DEFAULT_FULL_SCREEN_TILE_SIZE : DEFAULT_WINDOWED_TILE_SIZE;
//    }
//    public static int getTextWidth(boolean fullscreen) {
//        return fullscreen ? DEFAULT_FULL_SCREEN_TEXT_WIDTH : DEFAULT_WINDOWED_TEXT_WIDTH;
//    }
//    public static int getTextHeight(boolean fullscreen) {
//        return getTileSize(fullscreen);
//    }
//    public int getPixelHeight() {
//        return getDisplayMode().getHeight();
//    }
//    public int getPixelWidth() {
//        return getDisplayMode().getWidth();
//    }
//    public static int countTileRows() {
//        return getPixelHeight() / getTileSize(true);
//    }
//    public static int countTileColumns() {
//        return getPixelWidth() / getTileSize(true);
//    }
//}

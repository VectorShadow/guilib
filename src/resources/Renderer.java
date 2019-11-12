package resources;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Renderer extends JLabel {
    private RenderContext renderContext;
    private static RenderContext staticRenderContext;
    private static ArrayList<Renderer> activeRenderers = new ArrayList<>();

    private Renderer(RenderContext rc){
        renderContext = rc;
        setSize(rc.imageSize());
        setFont(rc.imageFont());
        setHorizontalAlignment(SwingConstants.CENTER);
        setOpaque(true);
    }
    public static void setRenderContext(RenderContext rc) {
        staticRenderContext = rc;
    }
    private BufferedImage renderImage(Color background, Color foreground, char symbol) {
        setBackground(background);
        setForeground(foreground);
        setText("" + symbol);
        BufferedImage bi = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        paint(bi.getGraphics());
        return bi;
    }
    public static BufferedImage getImage(Color b, Color f, char s){
        for (Renderer r : activeRenderers) {
            if (r.renderContext.equals(staticRenderContext)) return r.renderImage(b, f, s);
        }
        Renderer r = new Renderer(staticRenderContext);
        activeRenderers.add(r);
        return r.renderImage(b, f, s);
    }

    /**
     * Get the size of the display in pixels.
     */
    public static Dimension countPixels() {
        DisplayMode dm = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode();
        return new Dimension(dm.getWidth(), dm.getHeight());
    }

    /**
     * Get the size of this Renderer - this is define by the imageSize of its renderContext.
     */
    public Dimension getSize(){
        return renderContext.imageSize();
    }
    /**
     * Count the number of units for the given OutputMode which would completely fill the display.
     */
    public static Dimension countUnits(OutputMode om) {
        return countUnits(countPixels(), om);
    }

    /**
     * Count the number of units for the given OutputMode which would completely fill the zone.
     */
    public static Dimension countUnits(Dimension zone, OutputMode om) {
        return new Dimension(
                zone.width / om.generateContext(true).imageSize().width,
                zone.height / om.generateContext(true).imageSize().height
        );
    }
}

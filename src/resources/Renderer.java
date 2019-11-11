package resources;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Renderer extends JLabel {
    private static Renderer tileRenderer;
    private static Renderer textRenderer;

    private Renderer(){
        setHorizontalAlignment(SwingConstants.CENTER);
        setOpaque(true);
    }
    public static void initialize(boolean fullScreen) {
        tileRenderer = new Renderer();
        textRenderer = new Renderer();
        tileRenderer.setSize(DynamicSize.getTileSize(fullScreen), DynamicSize.getTileSize(fullScreen));
        textRenderer.setSize(DynamicSize.getTextWidth(fullScreen), DynamicSize.getTileSize(fullScreen));
        //todo - adjust style and size
        tileRenderer.setFont(new Font(Font.DIALOG, Font.PLAIN, DynamicSize.getTileSize(fullScreen) - 2));
        textRenderer.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, DynamicSize.getTileSize(fullScreen) - 2));
    }
    public static BufferedImage getImage(boolean isTile, Color background, Color foreground, char symbol) {
        Renderer r = isTile ? tileRenderer : textRenderer;
        r.setBackground(background);
        r.setForeground(foreground);
        r.setText("" + symbol);
        BufferedImage bi = new BufferedImage(r.getWidth(), r.getHeight(), BufferedImage.TYPE_INT_RGB);
        r.paint(bi.getGraphics());
        return bi;
    }
}

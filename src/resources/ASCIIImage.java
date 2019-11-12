package resources;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class ASCIIImage {
    protected abstract Color getBackground();
    protected abstract Color getForeground();
    protected abstract char getSymbol();
    protected BufferedImage render(){
        return Renderer.getImage(getBackground(), getForeground(), getSymbol());
    }
}

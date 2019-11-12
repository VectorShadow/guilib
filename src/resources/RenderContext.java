package resources;

import java.awt.*;

/**
 * Wrap an int value to be parsed by DynamicSize and Renderer.
 */
public abstract class RenderContext {
    /**
     * Unique id for this RenderContext.
     */
    final int ID;

    protected RenderContext(int id){
        ID = id;
    }

    /**
     * These should implement a switch on ID.
     */
    public abstract Dimension imageSize();
    public abstract Font imageFont();
    public abstract OutputMode outputMode();

    @Override
    public boolean equals(Object o) {
        return o instanceof RenderContext && ID == ((RenderContext) o).ID;
    }
}

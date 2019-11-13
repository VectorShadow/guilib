package resources;

public interface OutputMode {
    RenderContext generateContext(boolean fullScreen);
    GlyphStringProtocol getGlyphStringProtocol();
}

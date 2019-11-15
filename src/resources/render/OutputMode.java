package resources.render;

import resources.glyph.GlyphStringProtocol;

public interface OutputMode {
    RenderContext generateContext(boolean fullScreen);
    GlyphStringProtocol getGlyphStringProtocol();
}

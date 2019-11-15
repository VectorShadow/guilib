package resources.render;

import resources.glyph.GlyphStringProtocol;

public interface OutputMode {
    char LIMIT_BREAK = '~';
    RenderContext generateContext(boolean fullScreen);
    GlyphStringProtocol getGlyphStringProtocol();
}

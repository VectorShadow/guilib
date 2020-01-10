package contract.input;

import resources.glyph.image.GlyphString;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class InputDialog implements Iterable<GlyphString> {

    public static class FieldAsGlyphStringIterator implements Iterator<GlyphString> {

        final InputDialog inputDialog;
        int cursor = -1;

        FieldAsGlyphStringIterator(InputDialog id) {
            inputDialog = id;
        }

        @Override
        public boolean hasNext() {
            return indexOfNextEnabledField() >= 0;
        }

        @Override
        public GlyphString next() {
            cursor = indexOfNextEnabledField();
            InputDialogField idf = inputDialog.fields.get(cursor++);
            return new GlyphString(
                new GlyphString(
                    idf.getPrompt(),
                    inputDialog.promptBackground,
                    inputDialog.promptForeground
                ),
                new GlyphString(
                    idf.getResponse(),
                    inputDialog.responseBackground,
                    inputDialog.responseForeground
                )
            );
        }
        private int indexOfNextEnabledField() {
            for (int i = cursor; i < inputDialog.fields.size(); ++i) {
                if (inputDialog.fields.get(i).isEnabled()) return i;
            }
            return -1;
        }
    }

    private final String title;
    private Color promptBackground;
    private Color promptForeground;
    private Color responseBackground;
    private Color responseForeground;
    private ArrayList<InputDialogField> fields;

    InputDialog(String title) {
        this.title = title;
        fields = new ArrayList<>();
    }
    void setColors(Color pb, Color pf, Color rb, Color rf) {
        promptBackground = pb;
        promptForeground = pf;
        responseBackground = rb;
        responseForeground = rf;
    }
    void addInputDialogField(InputDialogField idf) {
        fields.add(idf);
    }
    public GlyphString glyphStringTitle() {
        return new GlyphString(title, promptBackground, promptForeground);
    }

    @Override
    public Iterator<GlyphString> iterator() {
        return new FieldAsGlyphStringIterator(this);
    }
}

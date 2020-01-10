package contract.input;

import resources.glyph.image.GlyphString;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class InputDialog implements Iterable<GlyphString> {

    public static class FieldAsGlyphStringIterator implements Iterator<GlyphString> {

        final InputDialog inputDialog;
        int cursor = 0;

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
                    cursor - 1 == inputDialog.activeFieldIndex ? inputDialog.promptHighlight : inputDialog.promptForeground
                ),
                new GlyphString(
                    idf.displayResponse(),
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
    private Color promptHighlight;
    private Color responseBackground;
    private Color responseForeground;
    private ArrayList<InputDialogField> fields;

    private int activeFieldIndex = 0;

    InputDialog(String title) {
        this.title = title;
        fields = new ArrayList<>();
    }
    void setColors(Color pb, Color pf, Color ph, Color rb, Color rf) {
        promptBackground = pb;
        promptForeground = pf;
        promptHighlight = ph;
        responseBackground = rb;
        responseForeground = rf;
    }
    int addInputDialogField(InputDialogField idf) {
        fields.add(idf);
        return fields.size() - 1;
    }
    public void setEnabledField(int fieldIndex, boolean enabled) {
        fields.get(fieldIndex).setEnabled(enabled);
    }
    public GlyphString glyphStringTitle() {
        return new GlyphString(title, promptBackground, promptHighlight);
    }
    public void appendToActiveField(char c) {
        fields.get(activeFieldIndex).appendResponse(c);
    }
    public void undoFromActiveField(char c) {
        fields.get(activeFieldIndex).undoResponse();
    }
    public void advanceActiveField() {
        do {
            activeFieldIndex++;
            if (activeFieldIndex >= fields.size()) activeFieldIndex = 0;
        } while (!fields.get(activeFieldIndex).isEnabled());
    }
    public void regressActiveField() {
        do {
            activeFieldIndex--;
            if (activeFieldIndex < 0) activeFieldIndex = fields.size() - 1;
        } while (!fields.get(activeFieldIndex).isEnabled());
    }
    public void resetActiveField() {
        activeFieldIndex = 0;
    }
    private boolean isActiveFieldLastEnabled() {
        for (int i = activeFieldIndex + 1; i < fields.size(); ++i) {
            if (fields.get(i).isEnabled()) return false;
        }
        return true;
    }

    @Override
    public Iterator<GlyphString> iterator() {
        return new FieldAsGlyphStringIterator(this);
    }
    @Override
    public InputDialog clone() {
        InputDialogBuilder idb = InputDialogBuilder.setTitle(title);
        idb.setColors(promptBackground, promptForeground, promptHighlight, responseBackground, responseForeground);
        for (InputDialogField field : fields) idb.addInputDialogField(field);
        return idb.build();
    }
}

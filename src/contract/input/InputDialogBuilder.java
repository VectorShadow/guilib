package contract.input;

import java.awt.*;

public class InputDialogBuilder {
    private InputDialog inputDialog;

    private InputDialogBuilder(String title){
        inputDialog = new InputDialog(title);
    }

    public static InputDialogBuilder setTitle(String title) {
        return new InputDialogBuilder(title);
    }

    public InputDialogBuilder setColors(
            Color promptBackground,
            Color promptForeground,
            Color promptHighlight,
            Color responseBackground,
            Color responseForeground
    ) {
        inputDialog.setColors(promptBackground, promptForeground, promptHighlight, responseBackground, responseForeground);
        return this;
    }
    public InputDialogBuilder addInputDialogField(
            String prompt,
            int maxResponseLength,
            boolean hideResponse,
            boolean defaultEnabled
    ) {
        int fieldIndex = inputDialog.addInputDialogField(new InputDialogField(prompt, maxResponseLength, hideResponse));
        inputDialog.setEnabledField(fieldIndex, defaultEnabled);
        return this;
    }
    public InputDialogBuilder addInputDialogField(InputDialogField idf) {
        inputDialog.addInputDialogField(idf);
        return this;
    }
    public InputDialog build() {
        return inputDialog;
    }
}

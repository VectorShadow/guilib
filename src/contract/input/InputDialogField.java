package contract.input;

public class InputDialogField {
    private final String prompt;
    private String response;
    private final int maxResponseLength;
    private final boolean hidden;
    private boolean enabled;

    InputDialogField(String prompt, int maxResponseLength, boolean hidden) {
        this.prompt = prompt;
        this.response = "";
        this.maxResponseLength = maxResponseLength;
        this.hidden = hidden;
        this.enabled = true;
    }
    public void appendResponse(char c) {
        if (response.length() < maxResponseLength) response += c;
    }
    public void undoResponse() {
        if (response.length() > 0) response = response.substring(0, response.length() - 1);
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public boolean isHidden() {
        return hidden;
    }

    public int getMaxResponseLength() {
        return maxResponseLength;
    }

    public String getPrompt() {
        return prompt;
    }

    public String getResponse() {
        return response;
    }
}

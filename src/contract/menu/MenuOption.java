package contract.menu;

public class MenuOption {
    private final String name;
    private boolean enabled;

    public MenuOption(String optionName) {
        this(optionName, true);
    }
    public MenuOption(String optionName, boolean enable) {
        name = optionName;
        setEnabled(enable);
    }
    void setEnabled(boolean enable) {
        enabled = enable;
    }
    public String getName() {
        return name;
    }
    public boolean isEnabled() {
        return enabled;
    }
}

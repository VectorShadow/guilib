package contract.menu;

import java.util.ArrayList;

public class MenuBuilder {

    private Menu menu;

    private MenuBuilder(String title) {
        menu = new Menu(title);
    }
    public static MenuBuilder newMenu(String title) {
        return new MenuBuilder(title);
    }
    public MenuBuilder addOption(MenuOption menuOption) {
        menu.addOption(menuOption);
        return this;
    }
    public MenuBuilder setMenuHandler(MenuHandler menuHandler) {
        menu.setMenuHandler(menuHandler);
        return this;
    }
    public Menu build() {
        if (menu.enabledCount <= 0) throw new IllegalStateException("Menu may not be built with no enabled options.");
        if (menu.menuHandler == null) throw new IllegalStateException("Menu may not be built without a MenuHandler.");
        ArrayList<MenuOption> menuOptions = menu.getOptions();
        for (int i = 0; i < menuOptions.size(); ++i) {
            if (menuOptions.get(i).isEnabled()) { //find the first enabled option and set it as the selected option
                menu.setSelectedOptionIndex(i);
                break;
            }
        }
        return menu;
    }
}

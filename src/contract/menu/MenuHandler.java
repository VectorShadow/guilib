package contract.menu;

import contract.Gui;

/**
 * Implementation defined.
 */
public interface MenuHandler {
    Menu getMenu();
    void initialize();
    void handleExit();
    void handleSelection(int selectedIndex);
    void printMenu(Gui gui);
}

package contract.menu;

import contract.Gui;

/**
 * Implementation defined.
 */
public interface MenuHandler {
    void handleExit();
    void handleSelection(int selectedIndex);
    void printMenu(Gui gui);
}

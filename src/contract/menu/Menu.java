package contract.menu;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Framework for implementing generic menus.
 */
public class Menu implements Iterable<MenuOption>{

    public static class MenuOptionIterator implements Iterator<MenuOption> {

        final Menu menu;
        int cursor = 0;

        MenuOptionIterator(Menu m) {
            menu = m;
        }

        @Override
        public boolean hasNext() {
            return cursor < menu.size();
        }

        @Override
        public MenuOption next() {
            return menu.getOption(cursor++);
        }
    }

    @Override
    public Iterator<MenuOption> iterator() {
        return new MenuOptionIterator(this);
    }

    private String title;
    private ArrayList<MenuOption> options;
    private int selectedOption = -1;
    int enabledCount = 0;
    MenuHandler menuHandler = null;

    Menu(String title) {
        this.title = title;
    }

    MenuOption getOption(int index) {
        return options.get(index);
    }
    void addOption(MenuOption mo) {
        options.add(mo);
        if (mo.isEnabled()) ++enabledCount;
    }
    void setMenuHandler(MenuHandler mh) {
        menuHandler = mh;
    }
    void setSelectedOption(int option){
        if (option < 0 || option >= options.size())
            throw new IllegalArgumentException("Option " + option + " out of range: 0-" +  (options.size() - 1));
    }
    ArrayList<MenuOption> getOptions() {
        return options;
    }

    public String getTitle() {
        return title;
    }
    public void setEnabled(int optionIndex, boolean enabled){
        if (enabledCount <= 1 && !enabled) return; //don't disable the last option
        enabledCount = enabled ? enabledCount + 1 : enabledCount - 1; //update our count
        options.get(optionIndex).setEnabled(enabled); //update the option;
        if (selectedOption == optionIndex && !enabled) advance(); //never disable the selected option
    }
    public void advance(){
        if (enabledCount <= 1) return; //nothing to do
        do {
            ++selectedOption; //update the selected index
            if (selectedOption >= options.size()) selectedOption = 0; //loop back to the top
        } while (!options.get(selectedOption).isEnabled()); //don't stop on a disabled option
    }
    public void regress(){
        if (enabledCount <= 1) return; //nothing to do
        do {
            --selectedOption; //update the selected index
            if (selectedOption < 0) selectedOption = options.size() - 1; //loop back to the bottom
        } while (!options.get(selectedOption).isEnabled()); //don't stop on a disabled option
    }
    public int size() {
        return options.size();
    }
}

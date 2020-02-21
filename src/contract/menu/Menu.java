package contract.menu;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Framework for implementing generic implementation.menus.
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
    private ArrayList<MenuOption> options = new ArrayList<>();
    private int selectedOptionIndex = -1;
    int enabledCount = 0;

    Menu(String title) {
        this.title = title;
    }

    MenuOption getOption(int index) {
        return options.get(index);
    }
    public String getOptionName(int index) {return getOption(index).getName();}
    void addOption(MenuOption mo) {
        options.add(mo);
        if (mo.isEnabled()) ++enabledCount;
    }
    void setSelectedOptionIndex(int optionIndex){
        if (optionIndex < 0 || optionIndex >= options.size())
            throw new IllegalArgumentException("Option " + optionIndex + " out of range: 0-" +  (options.size() - 1));
        selectedOptionIndex = optionIndex;
    }
    ArrayList<MenuOption> getOptions() {
        return options;
    }

    public String getTitle() {
        return title;
    }
    public boolean isEnabled(int optionIndex) {
        return options.get(optionIndex).isEnabled();
    }
    public void setEnabled(int optionIndex, boolean enabled){
        if (enabledCount <= 1 && !enabled) return; //don't disable the last option
        enabledCount = enabled ? enabledCount + 1 : enabledCount - 1; //update our count
        options.get(optionIndex).setEnabled(enabled); //update the option;
        if (selectedOptionIndex == optionIndex && !enabled) advance(); //never disable the selected option
    }
    public void advance(){
        if (enabledCount <= 1) return; //nothing to do
        do {
            ++selectedOptionIndex; //update the selected index
            if (selectedOptionIndex >= options.size()) selectedOptionIndex = 0; //loop back to the top
        } while (!options.get(selectedOptionIndex).isEnabled()); //don't stop on a disabled option
    }
    public void regress(){
        if (enabledCount <= 1) return; //nothing to do
        do {
            --selectedOptionIndex; //update the selected index
            if (selectedOptionIndex < 0) selectedOptionIndex = options.size() - 1; //loop back to the bottom
        } while (!options.get(selectedOptionIndex).isEnabled()); //don't stop on a disabled option
    }
    public int size() {
        return options.size();
    }
    public int getSelectedOptionIndex() {
        return selectedOptionIndex;
    }
}

package com.coursework.ui.cli;

import com.coursework.core.Settings;
import com.coursework.ui.UserInterface;

public class ConsoleUI implements UserInterface<String> {

    private ConsoleMenus cm;

    public ConsoleUI(Settings settings) {
        cm = new ConsoleMenus(settings);
    }

    public String mainMenu() {
        return cm.getMessage("menu");
    }

    public String settings() {
        return cm.getMessage("settings");
    }

    public String gameplay(String ... keyword) {
        return cm.getMessage(keyword[0]);
    }

    public String win() {
        return cm.getMessage("winMessage");
    }

    public String lose() {
        return cm.getMessage("loseMessage");
    }

    public String goodbye() {
        return cm.getMessage("goodbye");
    }
}

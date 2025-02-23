package com.coursework.ui.cli;

import com.coursework.core.GameMessages;
import com.coursework.core.Settings;
import com.coursework.ui.UserInterface;
import com.coursework.ui.UserInterfaceGoodbye;
import com.coursework.ui.UserInterfaceWL;

public class ConsoleUI implements UserInterface<String>, UserInterfaceWL<String>, UserInterfaceGoodbye<String> {

    private GameMessages gm;

    public ConsoleUI(Settings settings) {
        gm = new GameMessages(settings);
    }

    public String mainMenu() {
        return gm.getMessage("menu");
    }

    public String settings() {
        return gm.getMessage("settings");
    }

    public String gameplay(String ... keyword) {
        return gm.getMessage(keyword[0]);
    }

    public String win() {
        return gm.getMessage("winMessage");
    }

    public String lose() {
        return gm.getMessage("loseMessage");
    }

    public String goodbye() {
        return gm.getMessage("goodbye");
    }
}

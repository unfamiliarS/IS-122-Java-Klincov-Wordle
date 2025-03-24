package com.coursework.console.cli;

import com.coursework.core.UserInterface;
import com.coursework.core.UserInterfaceGoodbye;
import com.coursework.core.UserInterfaceWL;
import com.coursework.core.impl.GameMessagesFactory;
import com.coursework.core.impl.Settings;

public class ConsoleUI implements UserInterface<String>, UserInterfaceWL<String>, UserInterfaceGoodbye<String> {

    private GameMessagesFactory gm;

    public ConsoleUI(Settings settings) {
        gm = new GameMessagesFactory(settings);
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

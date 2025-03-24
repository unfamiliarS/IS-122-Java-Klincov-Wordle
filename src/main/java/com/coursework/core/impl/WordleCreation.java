package com.coursework.core.impl;

import com.coursework.console.ConsoleWordle;
import com.coursework.core.Factory;
// import com.coursework.gui.GUIWordle;

public class WordleCreation implements Factory<Wordle> { 

    private final Settings settings;

    public WordleCreation(Settings settings) {
        this.settings = settings;
    }

    public Wordle create() {
        String uiType = settings.getUIType();
        if (uiType.equals("gui")) {
            // return new GUIWordle(settings);
        } else if (uiType.equals("console")) {
            return new ConsoleWordle(settings);
        }
        throw new IllegalArgumentException("Неизвестный тип интерфейса: " + uiType);
    }
}

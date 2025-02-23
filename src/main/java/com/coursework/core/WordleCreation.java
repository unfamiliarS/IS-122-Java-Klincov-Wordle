package com.coursework.core;

import com.coursework.console.ConsoleWordle;

public class WordleCreation implements Factory<Wordle> { 

    private final Settings settings;

    public WordleCreation(Settings settings) {
        this.settings = settings;
    }

    public Wordle create() {
        String uiType = settings.getUIType();
        if (uiType.equals("uiType")) {
            // return new GUIWordle();
            return null;
        } else if (uiType.equals("console")) {
            return new ConsoleWordle(settings);
        }
        throw new IllegalArgumentException("Неизвестный тип интерфейса: " + uiType);
    }
}

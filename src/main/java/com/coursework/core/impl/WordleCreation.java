package com.coursework.core.impl;

import com.coursework.console.ConsoleWordle;
import com.coursework.core.Factory;

public class WordleCreation implements Factory<Wordle> { 

    private final Settings settings;

    public WordleCreation(Settings settings) {
        this.settings = settings;
    }

    public Wordle create() {
        return new ConsoleWordle(settings);
    }
}

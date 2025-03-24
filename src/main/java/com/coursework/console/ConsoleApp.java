package com.coursework.console;

import com.coursework.core.impl.Settings;
import com.coursework.core.impl.Wordle;
import com.coursework.core.impl.WordleCreation;

public class ConsoleApp {
    public static void main(String[] args) throws Exception {
        Wordle wordle = new WordleCreation(new Settings()).create();
        wordle.play();
    }
}

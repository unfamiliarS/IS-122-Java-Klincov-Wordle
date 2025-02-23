package com.coursework;

import com.coursework.core.Settings;
import com.coursework.core.Wordle;
import com.coursework.core.WordleCreation;

public class App {
    public static void main(String[] args) throws Exception {
        Wordle wordle = new WordleCreation(new Settings()).create();
        wordle.game();
    }
}

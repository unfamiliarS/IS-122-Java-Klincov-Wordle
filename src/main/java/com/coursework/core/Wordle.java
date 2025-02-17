package com.coursework.core;

import com.coursework.ui.UserInterface;

public abstract class Wordle<UIType> {

    protected Settings settings;
    protected UserInterface<UIType> ui;

    public Wordle() {
        settings = new Settings();
    }

    public abstract void game();
}
package com.coursework.gui;

import com.coursework.core.impl.Settings;
import com.coursework.core.impl.Wordle;

public class GUIWordle extends Wordle {

    private GUI gui;

    public GUIWordle(Settings settings) {
        super(settings);
        gui = new GUI();
    }

    public void play() {
        GUI.launchGUI();
    }

    protected String getGameplayMesg(String keyword) {
        return  " ";
    }
}

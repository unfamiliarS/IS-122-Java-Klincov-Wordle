package com.coursework.core.impl;

import com.coursework.core.GameMessages;
import com.coursework.core.LanguageChangeListener;
import com.coursework.core.enums.Languages;

public class GameMessagesFactory implements LanguageChangeListener {
    
    private GameMessages gameMessages;

    public GameMessagesFactory(Settings settings) {
        this.gameMessages = createGameMessages(settings.getLanguage());
        settings.setLanguageChangeListener(this);
    }

    public void onLanguageChanged(Languages newLanguage) {
        this.gameMessages = createGameMessages(newLanguage);
    }

    private GameMessages createGameMessages(Languages language) {
        switch (language) {
            case ENG:
                return new EnglishGameMessages();
            case RUS:
                return new RussianGameMessages();
            default:
                throw new IllegalArgumentException("Unsupported language: " + language);
        }
    }

    public String getMessage(String key) {
        return gameMessages.getMessage(key);
    }

}

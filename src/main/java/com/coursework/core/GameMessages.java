package com.coursework.core;

import java.util.Map;

public abstract class GameMessages {

    protected Map<String, String> messages;

    public GameMessages() {
        messages = loadMessages();
    }
    
    public String getMessage(String key) {
        return messages.get(key);
    }

    protected abstract Map<String, String> loadMessages();
}

package com.coursework.core.impl;

import java.util.HashMap;
import java.util.Map;

import com.coursework.core.GameMessages;

public final class EnglishGameMessages extends GameMessages {

    public EnglishGameMessages() {
        super();
    }

    protected Map<String, String> loadMessages() {
        Map<String, String> messages = new HashMap<>();
        
        messages.put("menu", "\n=============================\n__________ Wordle ___________\n\n\t1. Play\n\t2. Languages\n\t3. Exit\n\n=============================\nEnter: ");
        messages.put("settings", "\n=============================\n\n\teng - English\n\trus - Russian\n\n=============================\nEnter: ");
        messages.put("startingPhrase", "\n\n\n\n\nGuess the word.\n ");
        messages.put("attemptsCnt", "Tries last: ");
        messages.put("invalidLength", "Invalid word length (should be ");
        messages.put("invalidChars", "Only English letters are allowed.\n");
        messages.put("wordNotFound", "There is no such word in the dictionary. Perhaps it does not exist.\n");
        messages.put("incorrectAttempt", "Oups... didn't guess\n");
        messages.put("winMessage", "\nYey, you won! The hidden word was ");
        messages.put("loseMessage", "You're loose.\nThe answer was the word ");
        messages.put("replayPrompt", "\nDo you want to continue? (y/n)\n");
        messages.put("goodbye", "\nBye...");

        return messages;
    }

}

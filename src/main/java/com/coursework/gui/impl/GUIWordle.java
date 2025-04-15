package com.coursework.gui.impl;

import java.io.IOException;
import com.coursework.core.Wordle;

public class GUIWordle extends Wordle {

    private WordSubmissionListener listener;
    private int currentAttempt = 0;

    public GUIWordle() throws IOException {
        super();
    }

    public interface WordSubmissionListener {
        void onWordSubmitted(String[] comparisonResult);
        void onInvalidWord(String word);
        void onGameOver(boolean won, String answer);
    }

    public void setWordSubmissionListener(WordSubmissionListener listener) {
        this.listener = listener;
    }

    public boolean submitWord(String word) {
        if (currentAttempt >= attempts) {
            return false;
        }

        word = word.toLowerCase();
        
        try {
            if (!allWordsDictionary.isTheWordInTheDictionary(word)) {
                if (listener != null) {
                    listener.onInvalidWord(word);
                }
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        userWords[currentAttempt] = word;
        String[] result = comparingWords(word, answer);
        
        if (listener != null) {
            listener.onWordSubmitted(result);
        }

        boolean won = word.equals(answer);
        currentAttempt++;

        if (won || currentAttempt >= attempts) {
            if (listener != null) {
                listener.onGameOver(won, answer);
            }
        }

        return true;
    }
}
package com.coursework.core;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.coursework.core.impl.Dictionary;
import com.coursework.core.impl.Settings;

public abstract class Wordle {

    protected Settings settings;
    protected String answer;
    protected Dictionary allWordsDictionary;
    protected int attempts;
    protected int wordLength;

    public Wordle() throws IOException {
        settings = Settings.getInstance();
        answer = settings.getAnswerDictionary().getRandomWord().toLowerCase();
        System.out.println("Answer word: " + answer);
        allWordsDictionary = settings.getWordDictionary();
        attempts = settings.getAttempts();
        wordLength = settings.getWordLength();
    }

    protected String[] comparingWords(String userWord, String answerWord) {
        int wordLen = userWord.length();
    
        String green = "\u001B[32m";
        String yellow = "\u001B[33m";
        String reset = "\u001B[0m";
    
        Map<Character, Integer> remainingLetters = new HashMap<>();
        for (char c : answerWord.toCharArray()) {
            remainingLetters.put(c, remainingLetters.getOrDefault(c, 0) + 1);
        }
    
        String[] coloredResult = new String[wordLen];
    
        // firstly look at green match
        for (int i = 0; i < wordLen; i++) {
            char userChar = userWord.charAt(i);
            char answerChar = answerWord.charAt(i);
    
            if (userChar == answerChar) {
                coloredResult[i] = green + userChar + reset;
                remainingLetters.put(userChar, remainingLetters.get(userChar) - 1);
            } else {
                coloredResult[i] = String.valueOf(userChar);
            }
        }
    
        // secondly look at yellow match
        for (int i = 0; i < wordLen; i++) {
            char userChar = userWord.charAt(i);
    
            if (coloredResult[i].startsWith(green)) {
                continue;
            }
    
            if (remainingLetters.getOrDefault(userChar, 0) > 0) {
                coloredResult[i] = yellow + userChar + reset;
            }
        }
    
        return coloredResult;
    }

}

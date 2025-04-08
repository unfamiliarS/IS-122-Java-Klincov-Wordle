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
        protected String[] userWords;

        public Wordle(Settings settings) throws IOException {
            this.settings = settings;
            answer = settings.getAnswerDictionary().getRandomWord().toLowerCase();
            allWordsDictionary = settings.getWordDictionary();
            attempts = settings.getAttempts();
            userWords = new String[attempts];
        }

        public abstract void play();

        protected String comparingWords(String userWord, String answerWord) {
            int wordLen = userWord.length();
            var result = new StringBuilder();
        
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
        
            for (String s : coloredResult) {
                result.append(s);
            }
        
            return result.toString();
        }

        // protected abstract String getGameplayMesg(String keyword);
    }

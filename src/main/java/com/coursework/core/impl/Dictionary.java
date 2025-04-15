package com.coursework.core.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Dictionary {

    private final String path;

    public Dictionary(String path) {
        this.path = path;
    }

    public String getRandomWord() throws IOException {

        int lineCount = 0;
        try (var in = getClass().getResourceAsStream(path);
            var br = new BufferedReader(new InputStreamReader(in))) {

            while (br.readLine() != null) {
                lineCount++;
            }
        }    
            var random = new Random();
            int randomLineNumber = random.nextInt(lineCount) + 1;

        try (var in = getClass().getResourceAsStream(path);
            var br = new BufferedReader(new InputStreamReader(in))) {

            lineCount = 0;
            String resultString;
            while ((resultString = br.readLine()) != null) {
                if (++lineCount == randomLineNumber)
                    return resultString;
            }

            return null;
        }
    }

    public boolean isTheWordInTheDictionary(String word) throws IOException {
        String wordFromDict;
        try (var in = getClass().getResourceAsStream(path);
            var br = new BufferedReader(new InputStreamReader(in))) {
            while ((wordFromDict = br.readLine()) != null) {
                if (wordFromDict.equals(word))
                    return true;
            }
            return false;
        }
    }

}

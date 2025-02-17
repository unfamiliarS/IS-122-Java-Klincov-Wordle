package com.coursework.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.coursework.core.enums.*;

public class Settings {

    private static final String DEFAULT_PROPERTIES_FILE_PATH = "/wordle.properties";
    private static final String PROPERTIES_FILE_PATH = System.getenv("HOME") + "/.config/wordle/wordle.properties";

    private Properties properties;
    private Dictionary answerDictionary;
    private Dictionary wordDictionary;
    private LanguageChangeListener listener;

    public Settings() {

        properties = new Properties();
        File propertiesFile = new File(PROPERTIES_FILE_PATH);

        if (propertiesFile.exists() && propertiesFile.canRead()) {
            try (var in = new BufferedReader(new FileReader(propertiesFile))) {
                properties.load(in);    
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } else {
            try (InputStream in = getClass().getResourceAsStream(DEFAULT_PROPERTIES_FILE_PATH)) {
                properties.load(in);    
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        setAnswerDictionary(LanguageManager.getAnswerWordsDictionary(getLanguage()));
        setWordDictionary(LanguageManager.getAllWordsDictionary(getLanguage()));
        
    }

    public void saveProperties() throws IOException {
        File configDir = new File(PROPERTIES_FILE_PATH).getParentFile();
        if (!configDir.exists() && !configDir.mkdirs()) {
            throw new IOException("Не удалось создать директорию для файла конфигурации: " + configDir.getAbsolutePath());
        }

        try (var writer = new BufferedWriter(new FileWriter(PROPERTIES_FILE_PATH))) {
            properties.store(writer, "Main properties");
        }
    }

    public Settings setLanguage(Languages language) {
        properties.setProperty("language", language.name());
        setAnswerDictionary(LanguageManager.getAnswerWordsDictionary(language));
        setWordDictionary(LanguageManager.getAllWordsDictionary(language));
        if(this.listener != null)
            notifyLanguageChange(language);
        return this;
    }

    private Settings setAnswerDictionary(Dictionary answerDictionary) {
        this.answerDictionary = answerDictionary;
        return this;
    }

    private Settings setWordDictionary(Dictionary wordDictionary) {
        this.wordDictionary = wordDictionary;
        return this;
    }
    
    private void notifyLanguageChange(Languages newLanguage) {
        listener.onLanguageChanged(newLanguage);
    }
    
    public void setLanguageChangeListener(LanguageChangeListener listener) {
        this.listener = listener;
    }

    public Settings setDifficulty(Difficulties difficulty) {
        properties.setProperty("difficulty", difficulty.name());
        return this;
    }

    public Settings setWordLength(int wordLength) {
        properties.setProperty("word_length", Integer.toString(wordLength));
        return this;
    }

    public Settings setAttempts(int attempts) {
        properties.setProperty("attempts", Integer.toString(attempts));
        return this;
    }


    public Languages getLanguage() {
        return Languages.valueOf(properties.getProperty("language").toUpperCase());
    }

    public Dictionary getAnswerDictionary() {
        return answerDictionary;
    }

    public Dictionary getWordDictionary() {
        return wordDictionary;
    }

    public Difficulties getDifficulty() {
        return Difficulties.valueOf(properties.getProperty("difficulty").toUpperCase());
    }
    
    public int getWordLength() {
        return Integer.parseInt(properties.getProperty("word_length"));
    }

    public int getAttempts() {
        return Integer.parseInt(properties.getProperty("attempts"));
    }
}

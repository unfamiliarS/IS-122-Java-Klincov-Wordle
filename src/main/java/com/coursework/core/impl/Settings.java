package com.coursework.core.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.coursework.core.enums.*;
import com.coursework.core.impl.languages.LanguageManager;
import com.coursework.core.LanguageChangeListener;

public class Settings {

    // Singleton
    private static Settings instance;

    private static final String DEFAULT_PROPERTIES_FILE_PATH = "/com/coursework/wordle.properties";
    private static final String PROPERTIES_FILE_PATH = getConfigFilePath();

    private Properties properties;
    private Dictionary answerDictionary;
    private Dictionary wordDictionary;
    private List<LanguageChangeListener> languageListeners;

    private static String getConfigFilePath() {
        String os = System.getProperty("os.name").toLowerCase();
        String configDir = "linux";
        
        if (os.contains("win")) {
            configDir = System.getenv("LOCALAPPDATA") + "\\Wordle\\wordle.properties";
        } else if (os.contains("linux")) {
            configDir = System.getenv("HOME") + "/.config/wordle/wordle.properties";
        }
        
        return configDir;
    }
    
    private Settings() {

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
        
        languageListeners = new ArrayList<>();
        System.out.println("The Settings creation is complete");

    }

    public static Settings getInstance() {
        if (instance == null) {
            instance = new Settings();
        }
        return instance;
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
        try {
            saveProperties();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setAnswerDictionary(LanguageManager.getAnswerWordsDictionary(language));
        setWordDictionary(LanguageManager.getAllWordsDictionary(language));
        notifyLanguageChanged(language);
        return this;
    }

    private void notifyLanguageChanged(Languages newLanguage) {
        languageListeners.forEach(listener -> listener.onLanguageChanged(newLanguage));
    }

    private Settings setAnswerDictionary(Dictionary answerDictionary) {
        this.answerDictionary = answerDictionary;
        return this;
    }

    private Settings setWordDictionary(Dictionary wordDictionary) {
        this.wordDictionary = wordDictionary;
        return this;
    }
    
    public void addLanguageChangeListener(LanguageChangeListener listener) {
        if (!languageListeners.contains(listener)) {
            languageListeners.add(listener);
        }
    }

    public void removeLanguageChangeListener(LanguageChangeListener listener) {
        languageListeners.remove(listener);
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

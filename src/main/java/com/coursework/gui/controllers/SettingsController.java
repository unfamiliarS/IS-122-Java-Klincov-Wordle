package com.coursework.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

import com.coursework.core.enums.Languages;
import com.coursework.core.impl.Settings;
import com.coursework.core.impl.languages.LanguageManager;
import com.coursework.core.impl.languages.Localizable;
import com.coursework.gui.impl.SceneLoader;

public class SettingsController implements Localizable {

    @FXML private Label mainSettingsLabel;
    @FXML private Label languageLabel;
    @FXML private Button russianButton;
    @FXML private Button englishButton;
    @FXML private Button backButton;
    
    private SceneLoader sceneLoader;
    private Settings settings;

    public void init(LanguageManager LanguageManager) {
        sceneLoader = new SceneLoader();
        settings = Settings.getInstance();
        LanguageManager.registerLocalizable("settings", this);
        updateText(LanguageManager);
    }

    @Override
    public void updateText(LanguageManager LanguageManager) {
        mainSettingsLabel.setText(LanguageManager.getText("settings.mainLabel"));
        languageLabel.setText(LanguageManager.getText("settings.languageLabel"));
        russianButton.setText(LanguageManager.getText("settings.russian"));
        englishButton.setText(LanguageManager.getText("settings.english"));
        backButton.setText(LanguageManager.getText("settings.back"));
    }

    @FXML
    private void handleBackButton() throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(sceneLoader.mainMenu());
    }

    @FXML
    private void handleRussianButton() {
        settings.setLanguage(Languages.RUS);
    }

    @FXML
    private void handleEnglishButton() {
        settings.setLanguage(Languages.ENG);
    }
}
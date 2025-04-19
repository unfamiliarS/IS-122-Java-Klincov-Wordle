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

    public void init(LanguageManager languageManager, SceneLoader sceneLoader) {
        this.sceneLoader = sceneLoader;
        settings = Settings.getInstance();
        languageManager.registerLocalizable("settings", this);
        updateText(languageManager);
        System.out.println("Init MenuController");
    }

    @Override
    public void updateText(LanguageManager languageManager) {
        mainSettingsLabel.setText(languageManager.getText("settings.mainLabel"));
        languageLabel.setText(languageManager.getText("settings.languageLabel"));
        russianButton.setText(languageManager.getText("settings.russian"));
        englishButton.setText(languageManager.getText("settings.english"));
        backButton.setText(languageManager.getText("settings.back"));
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
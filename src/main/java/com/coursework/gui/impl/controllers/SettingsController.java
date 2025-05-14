package com.coursework.gui.impl.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

import com.coursework.core.enums.Languages;
import com.coursework.core.impl.Settings;
import com.coursework.core.impl.languages.LanguageManager;
import com.coursework.core.impl.languages.Localizable;

public class SettingsController implements Localizable {

    @FXML private Label mainSettingsLabel;
    @FXML private Button russianButton;
    @FXML private Button englishButton;
    @FXML private Button closeButton;
    
    private Settings settings;

    public void init(LanguageManager languageManager) {
        settings = Settings.getInstance();
        languageManager.registerLocalizable("settings", this);
        updateText(languageManager);
    }

    @Override
    public void updateText(LanguageManager languageManager) {
        mainSettingsLabel.setText(languageManager.getText("settings.titleLabel"));
        russianButton.setText(languageManager.getText("settings.russianButton"));
        englishButton.setText(languageManager.getText("settings.englishButton"));
    }

    @FXML
    private void handleCloseButton() throws IOException {
        Stage popup = (Stage) closeButton.getScene().getWindow();
        popup.close();
    }

    @FXML
    private void handleCloseButtonKey(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ESCAPE) {
            ((Stage) closeButton.getScene().getWindow()).close();
        }
    }

    @FXML
    private void handleLanguageButton(ActionEvent event) throws Exception {
        String buttonId = ((Node) event.getSource()).getId();
        switch (buttonId) {
            case "russianButton" -> settings.setLanguage(Languages.RUS);
            case "englishButton" -> settings.setLanguage(Languages.ENG);
            default -> throw new Exception("Language change error");
        }
    }

}
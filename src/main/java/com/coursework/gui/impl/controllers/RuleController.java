package com.coursework.gui.impl.controllers;

import java.io.IOException;

import com.coursework.core.impl.Settings;
import com.coursework.core.impl.languages.LanguageManager;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class RuleController {

    @FXML private Button closeButton;

    // public void init(LanguageManager languageManager) {
    //     languageManager.registerLocalizable("settings", this);
    //     updateText(languageManager);
    //     System.out.println("Init SettingsController");
    // }

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
}

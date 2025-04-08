package com.coursework.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

import com.coursework.gui.impl.SceneLoader;

public class SettingsController {

    @FXML private Button russianButton;
    @FXML private Button englishButton;
    @FXML private Button backButton;

    @FXML
    private void handleBackButton() throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        Scene scene = new SceneLoader().mainMenu();
        stage.setScene(scene);
    }

    @FXML
    private void handleRussianButton() {
    
    }

    @FXML
    private void handleEnglishButton() {
    
    }
}
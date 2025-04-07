package com.coursework.gui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    @FXML private Button playButton;
    @FXML private Button settingsButton;
    @FXML private Button exitButton;
    @FXML private Button rulesButton;

    @FXML
    private void handlePlayButton() throws IOException {
        switchScene("/com/coursework/fxmls/game.fxml");
    }

    @FXML
    private void handleSettingsButton() throws IOException {
        switchScene("/com/coursework/fxmls/settings.fxml");
    }

    @FXML
    private void handleExitButton() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    private void switchScene(String fxmlPath) throws IOException {
        Stage stage = (Stage) playButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
        stage.setScene(new Scene(root));
    }
}
package com.coursework.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

import com.coursework.gui.impl.SceneLoader;

public class MenuController {

    @FXML private Button playButton;
    @FXML private Button settingsButton;
    @FXML private Button exitButton;
    @FXML private Button rulesButton;

    @FXML
    private void handlePlayButton() throws IOException {
        Stage stage = (Stage) playButton.getScene().getWindow();
        stage.setScene(new SceneLoader().gameplay());
    }

    @FXML
    private void handleSettingsButton() throws IOException {
        Stage stage = (Stage) settingsButton.getScene().getWindow();
        stage.setScene(new SceneLoader().settings());
    }

    @FXML
    private void handleExitButton() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

}
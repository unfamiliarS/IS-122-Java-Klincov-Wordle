package com.coursework.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

import com.coursework.core.impl.languages.LanguageManager;
import com.coursework.core.impl.languages.Localizable;
import com.coursework.gui.impl.SceneLoader;

public class MenuController implements Localizable {

    @FXML private Button playButton;
    @FXML private Button settingsButton;
    @FXML private Button exitButton;
    @FXML private Button rulesButton;
    
    private SceneLoader sceneLoader;

    public void init(LanguageManager LanguageManager) {
        sceneLoader = new SceneLoader();
        LanguageManager.registerLocalizable("menu", this);
        updateText(LanguageManager);
    }

    @Override
    public void updateText(LanguageManager LanguageManager) {
        playButton.setText(LanguageManager.getText("menu.play"));
        settingsButton.setText(LanguageManager.getText("menu.settings"));
        exitButton.setText(LanguageManager.getText("menu.exit"));
        rulesButton.setText(LanguageManager.getText("menu.rules"));
    }

    @FXML
    private void handlePlayButton() throws IOException {
        Stage stage = (Stage) playButton.getScene().getWindow();
        stage.setScene(sceneLoader.gameplay());
    }

    @FXML
    private void handleSettingsButton() throws IOException {
        Stage stage = (Stage) settingsButton.getScene().getWindow();
        stage.setScene(sceneLoader.settings());
    }

    @FXML
    private void handleExitButton() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
}
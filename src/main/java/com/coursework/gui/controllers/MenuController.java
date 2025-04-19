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

    public void init(LanguageManager languageManager, SceneLoader sceneLoader) {
        this.sceneLoader = sceneLoader;
        languageManager.registerLocalizable("menu", this);
        System.out.println("Init MenuController");
    }

    @Override
    public void updateText(LanguageManager languageManager) {
        playButton.setText(languageManager.getText("menu.play"));
        settingsButton.setText(languageManager.getText("menu.settings"));
        exitButton.setText(languageManager.getText("menu.exit"));
        rulesButton.setText(languageManager.getText("menu.rules"));
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
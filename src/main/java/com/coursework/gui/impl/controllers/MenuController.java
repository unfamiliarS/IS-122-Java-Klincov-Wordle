package com.coursework.gui.impl.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

import com.coursework.core.impl.languages.LanguageManager;
import com.coursework.core.impl.languages.Localizable;
import com.coursework.gui.impl.SceneLoader;
import com.coursework.gui.impl.Animations;

public class MenuController implements Localizable {

    @FXML private AnchorPane gamePane;
    @FXML private Button playButton;
    @FXML private Button languagesButton;
    @FXML private Button rulesButton;
    @FXML private Button exitButton;
    
    private SceneLoader sceneLoader;

    @FXML
    public void initialize() {
        Animations.animateAllStrawberries(gamePane);
    }

    public void init(LanguageManager languageManager, SceneLoader sceneLoader) {
        this.sceneLoader = sceneLoader;
        languageManager.registerLocalizable("menu", this);
    }

    @Override
    public void updateText(LanguageManager languageManager) {
        playButton.setText(languageManager.getText("menu.playButton"));
        rulesButton.setText(languageManager.getText("menu.rulesButton"));
        languagesButton.setText(languageManager.getText("menu.settingsButton"));
        exitButton.setText(languageManager.getText("menu.exitButton"));
    }
    
    @FXML
    private void handlePlayButton() throws IOException {
        Stage stage = (Stage) playButton.getScene().getWindow();
        Scene gameScene = sceneLoader.gameplay();
        Stage gameStage = new Stage();
        gameStage.initStyle(StageStyle.UNDECORATED);
        gameStage.setScene(gameScene);
        gameStage.setFullScreen(true);
        gameStage.setFullScreenExitHint("");
        gameStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        gameStage.show();
        stage.close();
    }

    @FXML
    private void handleRulesButton() throws IOException {
        Stage ownerStage = (Stage) rulesButton.getScene().getWindow();
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.WINDOW_MODAL);
        popupStage.initStyle(StageStyle.TRANSPARENT);
        popupStage.initOwner(ownerStage);
        popupStage.setScene(sceneLoader.rule());
        popupStage.setResizable(false);
        popupStage.showAndWait();
    }

    @FXML
    private void handleLanguagesButton() throws IOException {
        Stage ownerStage = (Stage) languagesButton.getScene().getWindow();
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.WINDOW_MODAL);
        popupStage.initStyle(StageStyle.TRANSPARENT);
        popupStage.initOwner(ownerStage);
        popupStage.setScene(sceneLoader.settings());
        popupStage.setResizable(false);
        popupStage.showAndWait();
    }

    @FXML
    private void handleExitButton() {
        Platform.exit();
    }

    @FXML
    private void handleESCPress(KeyEvent event) {
        if (event.getCode() == KeyCode.ESCAPE) {
            Platform.exit();
        }
    }
}
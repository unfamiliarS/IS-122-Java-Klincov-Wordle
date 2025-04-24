package com.coursework.gui.impl.controllers;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

import com.coursework.core.impl.languages.LanguageManager;
import com.coursework.core.impl.languages.Localizable;
import com.coursework.gui.impl.SceneLoader;
import com.coursework.gui.impl.StrawberryAnimator;

public class MenuController implements Localizable {

    @FXML private AnchorPane gamePane;
    @FXML private Button playButton;
    @FXML private Button languagesButton;
    @FXML private Button rulesButton;
    @FXML private Button exitButton;
    
    private SceneLoader sceneLoader;

    @FXML
    public void initialize() {
        StrawberryAnimator.animateAllStrawberries(gamePane);
    }

    public void init(LanguageManager languageManager, SceneLoader sceneLoader) {
        this.sceneLoader = sceneLoader;
        languageManager.registerLocalizable("menu", this);
        System.out.println("Init MenuController");
    }

    @Override
    public void updateText(LanguageManager languageManager) {
        playButton.setText(languageManager.getText("menu.play"));
        languagesButton.setText(languageManager.getText("menu.settings"));
        exitButton.setText(languageManager.getText("menu.exit"));
    }
    
    @FXML
    private void handlePlayButton() throws IOException {
        Stage stage = (Stage) playButton.getScene().getWindow();
        stage.setScene(sceneLoader.gameplay());
    }

    @FXML
    private void handleRulesButton() throws IOException {
    }

    @FXML
    private void handleLanguagesButton() throws IOException {
        Stage stage = (Stage) languagesButton.getScene().getWindow();
        stage.setScene(sceneLoader.settings());
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
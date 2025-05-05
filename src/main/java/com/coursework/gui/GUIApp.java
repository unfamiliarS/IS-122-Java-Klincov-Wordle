package com.coursework.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

import com.coursework.gui.impl.SceneLoader;

public class GUIApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        SceneLoader sceneLoader = new SceneLoader();
        Scene scene = sceneLoader.mainMenu();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.setFullScreen(true);
        stage.setFullScreenExitHint("");
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
package com.coursework.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import com.coursework.core.impl.languages.LanguageManager;
import com.coursework.gui.impl.SceneLoader;

public class GUIApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // Инициализация LanguageManager (загрузит настройки и тексты)
        // LanguageManager LanguageManager = LanguageManager.getInstance();
        
        SceneLoader sceneLoader = new SceneLoader();
        Scene scene = sceneLoader.mainMenu();
        
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
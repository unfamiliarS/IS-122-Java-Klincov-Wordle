package com.coursework.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUIApp extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Wordle");
        primaryStage.setScene(mainMenu());
        primaryStage.show();
    }

    public Scene mainMenu() {
        return loadFXML("/fxmls/menu.fxml");
    }

    public Scene settings() {
        return loadFXML("settings.fxml");
    }

    public Scene gameplay(String... keyword) {
        return loadFXML("gameplay.fxml");
    }

    private Scene loadFXML(String fxmlFile) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        try {
            return new Scene(loader.load());
        } catch (IOException e) {
            return null;
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}

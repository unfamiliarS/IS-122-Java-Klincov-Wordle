package com.coursework.gui;

import java.io.IOException;

import com.coursework.core.UserInterface;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUI extends Application implements UserInterface<Scene> {

    private Stage primaryStage;

    public GUI() {
        primaryStage = new Stage();
    }

    public static void launchGUI() {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Wordle");
        primaryStage.setScene(mainMenu());
        primaryStage.show();
    }

    public Scene mainMenu() {
        return loadFXML("menu.fxml");
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
}
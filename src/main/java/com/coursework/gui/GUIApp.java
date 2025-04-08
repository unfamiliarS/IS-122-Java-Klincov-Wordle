package com.coursework.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import com.coursework.gui.impl.SceneLoader;

public class GUIApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new SceneLoader().mainMenu();
        stage.setTitle("Wordle");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

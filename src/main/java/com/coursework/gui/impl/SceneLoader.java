package com.coursework.gui.impl;

import java.io.IOException;

import com.coursework.core.UserInterface;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class SceneLoader implements UserInterface<Scene> {

    public Scene mainMenu() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/coursework/fxmls/menu.fxml"));
        return new Scene(root);
    }

    public Scene settings() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/coursework/fxmls/settings.fxml"));
        return new Scene(root);
    }

    public Scene gameplay(String... keyword) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/coursework/fxmls/game.fxml"));
        return new Scene(root);
    }
}

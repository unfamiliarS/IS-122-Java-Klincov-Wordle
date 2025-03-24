package com.coursework.gui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.IOException;

public class GameController {

    @FXML private Button backButton;

    @FXML
    private void handleBackButton() throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/com/coursework/fxmls/menu.fxml"));
        stage.setScene(new Scene(root));
    }
}

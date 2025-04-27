package com.coursework.gui.impl.controllers;

import java.util.HashMap;
import java.util.Map;

import com.coursework.gui.impl.StrawberryAnimator;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PopupController {

    @FXML
    private Label titleLabel;
    @FXML
    private Label theWordWasLabel;
    @FXML
    private Label answerLabel;
    @FXML
    private Button mainMenuButton;
    @FXML
    private Button playAgainButton;
    @FXML
    private AnchorPane mainPane;

    private final Map<String, Runnable> afterHideActions = new HashMap<>();

    @FXML
    public void initialize() {
        StrawberryAnimator.animateAllStrawberries(mainPane);
    }

    public void setAfterHideAction(String afterHideActionName, Runnable afterHideAction) {
        afterHideActions.put(afterHideActionName, afterHideAction);
    }

    @FXML
    private void handlePlayAgainButton() {
        Stage popup = (Stage) playAgainButton.getScene().getWindow();
        if (afterHideActions.containsKey("restart")) {
            afterHideActions.get("restart").run();
        }
        popup.close();
    }

    @FXML
    private void handleMainMenuButton() {
        Stage popup = (Stage) mainMenuButton.getScene().getWindow();
        if (afterHideActions.containsKey("menu")) {
            afterHideActions.get("menu").run();
        }
        popup.close();
    }
}

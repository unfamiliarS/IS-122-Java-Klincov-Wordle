package com.coursework.gui.impl.controllers;

import java.util.HashMap;
import java.util.Map;

import com.coursework.core.impl.languages.LanguageManager;
import com.coursework.core.impl.languages.Localizable;
import com.coursework.gui.impl.Animations;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class WLPopupController implements Localizable {

    @FXML
    private Label titleLabel;
    private String WorL;
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

    private String answer;

    private final Map<String, Runnable> afterHideActions = new HashMap<>();

    @FXML
    public void initialize() {
        Animations.animateAllStrawberries(mainPane);
    }

    public void init(String WorL, LanguageManager languageManager, String answer) {
        this.WorL = WorL;
        this.answer = answer;
        languageManager.registerLocalizable("WLpopup", this);
        updateText(languageManager);
    }

    public void updateText(LanguageManager languageManager) {
        titleLabel.setText(WorL.equals("win") ? languageManager.getText("gameplay.popup.winTitleLabel") 
                                                    : languageManager.getText("gameplay.popup.loseTitleLabel"));
        theWordWasLabel.setText(languageManager.getText("gameplay.popup.subtitleLabel"));
        answerLabel.setText(answer);
        playAgainButton.setText(languageManager.getText("gameplay.popup.restartButton"));
        mainMenuButton.setText(languageManager.getText("gameplay.popup.menuButton"));
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

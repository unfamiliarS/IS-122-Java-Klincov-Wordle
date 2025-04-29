package com.coursework.gui.impl.controllers;

import java.io.IOException;

import com.coursework.core.impl.languages.LanguageManager;
import com.coursework.core.impl.languages.Localizable;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class RuleController implements Localizable {

    @FXML private Button closeButton;
    @FXML private Label titleLabel;
    @FXML private Label firstRule;
    @FXML private Label secondRule;
    @FXML private Label thirdRule;
    @FXML private Label exampleTitleLabel;
    @FXML private Label exampleLetter1;
    @FXML private Label exampleLetter2;
    @FXML private Label exampleLetter3;
    @FXML private Label exampleLetter4;
    @FXML private Label exampleLetter5;
    @FXML private Label firstExampleExplanation;
    @FXML private Label secondExampleExplanation;
    @FXML private Label thirdExampleExplanation;

    public void init(LanguageManager languageManager) {
        languageManager.registerLocalizable("rules", this);
        updateText(languageManager);
        System.out.println("Init RuleController");
    }

    public void updateText(LanguageManager languageManager) {
        titleLabel.setText(languageManager.getText("rule.titleLabel"));
        exampleTitleLabel.setText(languageManager.getText("rule.exampleTitleLabel"));
        firstRule.setText(languageManager.getText("rule.firstRule"));
        secondRule.setText(languageManager.getText("rule.secondRule"));
        thirdRule.setText(languageManager.getText("rule.thirdRule"));
        exampleLetter1.setText(languageManager.getText("rule.exampleLetter1"));
        exampleLetter2.setText(languageManager.getText("rule.exampleLetter2"));
        exampleLetter3.setText(languageManager.getText("rule.exampleLetter3"));
        exampleLetter4.setText(languageManager.getText("rule.exampleLetter4"));
        exampleLetter5.setText(languageManager.getText("rule.exampleLetter5"));
        firstExampleExplanation.setText(languageManager.getText("rule.firstExampleExplanation"));
        secondExampleExplanation.setText(languageManager.getText("rule.secondExampleExplanation"));
        thirdExampleExplanation.setText(languageManager.getText("rule.thirdExampleExplanation"));        
    }

    @FXML
    private void handleCloseButton() throws IOException {
        Stage popup = (Stage) closeButton.getScene().getWindow();
        popup.close();
    }

    @FXML
    private void handleCloseButtonKey(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ESCAPE) {
            ((Stage) closeButton.getScene().getWindow()).close();
        }
    }

}

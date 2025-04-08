package com.coursework.gui.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.coursework.core.impl.Settings;
import com.coursework.gui.impl.GUIWordle;
import com.coursework.gui.impl.SceneLoader;

public class GameController implements GUIWordle.WordSubmissionListener {

    @FXML private VBox labelRows;
    @FXML private HBox firstRow;
    @FXML private HBox secondRow;
    @FXML private HBox thirdRow;
    @FXML private Button backButton;
    
    private List<HBox> letterRows;
    private int currentRow = 0;
    private int currentLetterIndex = 0;

    private Settings settings;
    private GUIWordle wordleSession;
    private int wordLength;
    private int maxAttempts;

    @FXML
    public void initialize() {
        try {
            settings = new Settings();
            wordLength = settings.getWordLength();
            maxAttempts = settings.getAttempts();
            wordleSession = new GUIWordle(settings);
            wordleSession.setWordSubmissionListener(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        letterRows = new ArrayList<>();
        for (int i = 0; i < maxAttempts; i++)
            letterRows.add((HBox) labelRows.getChildren().get(i));
        
        backButton.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                setupKeyboard();
            }
        });
    }

    private void setupKeyboard() {
        // Обработка физической клавиатуры
        backButton.getScene().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            backButton.getScene().getFocusOwner().requestFocus();
            handleKeyPress(event);
        });
        
        // Обработка виртуальной клавиатуры
        for (HBox row : List.of(firstRow, secondRow, thirdRow)) {
            for (Node node : row.getChildren()) {
                if (node instanceof Button) {
                    Button button = (Button) node;
                    button.setFocusTraversable(false);
                    button.setOnAction(event -> {
                        handleButtonPress(button.getText());
                        button.getParent().requestFocus();
                    });
                }
            }
        }
    }

    private void handleKeyPress(KeyEvent event) {
        String keyText = event.getText().toUpperCase();
        
        if (keyText.matches("[A-Z]")) {
            handleButtonPress(keyText);
        } else {
            switch (event.getCode()) {
                case ENTER:
                    handleButtonPress("ENTER");
                    break;
                case BACK_SPACE:
                    handleButtonPress("⌫");
                    break;
                default:
                    break;
            }
        }
        event.consume();
    }

    private void handleButtonPress(String key) {
        if (currentRow >= maxAttempts) return;

        HBox currentLetterRow = letterRows.get(currentRow);
        
        if (key.matches("[A-Z]") && currentLetterIndex < wordLength) {
            // Добавление буквы
            Label label = (Label) currentLetterRow.getChildren().get(currentLetterIndex);
            label.setText(key);
            currentLetterIndex++;
        } else if (key.equals("⌫") && currentLetterIndex > 0) {
            // Удаление буквы
            currentLetterIndex--;
            Label label = (Label) currentLetterRow.getChildren().get(currentLetterIndex);
            label.setText(" ");
        } else if (key.equals("ENTER") && currentLetterIndex == wordLength) {
            // Обработка ввода слова
            processWord();
        }
    }

    private void processWord() {
        HBox currentLetterRow = letterRows.get(currentRow);
        StringBuilder word = new StringBuilder();
        
        for (int i = 0; i < wordLength; i++) {
            Label label = (Label) currentLetterRow.getChildren().get(i);
            word.append(label.getText());
        }
        
        if (!wordleSession.submitWord(word.toString().toLowerCase())) {
            clearCurrentRow();
        }
    }
    
    private void clearCurrentRow() {
        HBox currentLetterRow = letterRows.get(currentRow);
        for (Node node : currentLetterRow.getChildren()) {
            if (node instanceof Label) {
                ((Label) node).setText(" ");
            }
        }
        currentLetterIndex = 0;
    }
    
    @Override
    public void onWordSubmitted(String comparisonResult) {
        HBox currentLetterRow = letterRows.get(currentRow);
        colorLetters(currentLetterRow, comparisonResult);
        
        currentRow++;
        currentLetterIndex = 0;
    }
    
    @Override
    public void onInvalidWord(String word) {
        showAlert("Ошибка", "Слово \"" + word + "\" не найдено в словаре");
        clearCurrentRow();
    }

    @Override
    public void onGameOver(boolean won, String answer) {
        String message = won ? "Поздравляем! Вы угадали слово!" 
                           : "Игра окончена. Загаданное слово: " + answer;
        showAlert("Игра окончена", message);
    }

    private void colorLetters(HBox row, String result) {
        for (int i = 0; i < wordLength; i++) {
            Label label = (Label) row.getChildren().get(i);
            String c = label.getText().toLowerCase();
            
            if (result.contains("\u001B[32m" + c)) {
                label.setStyle("-fx-background-color: #6aaa64; -fx-text-fill: white;");
            } else if (result.contains("\u001B[33m" + c)) {
                label.setStyle("-fx-background-color: #c9b458; -fx-text-fill: white;");
            } else {
                label.setStyle("-fx-background-color: #acb2b6; -fx-text-fill: white;");
            }
        }
    }

    private void showAlert(String title, String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(title);
            alert.setHeaderText(message);
            alert.showAndWait();
        });
    }

    @FXML
    private void handleBackButton() throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(new SceneLoader().mainMenu());
    }
}
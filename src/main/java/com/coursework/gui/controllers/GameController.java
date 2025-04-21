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

import com.coursework.core.Wordle;
import com.coursework.core.enums.Languages;
import com.coursework.gui.impl.SceneLoader;

public class GameController extends Wordle {

    @FXML private VBox keyboardContainer;
    @FXML private VBox letterLabelsRows;
    @FXML private HBox firstRow;
    @FXML private HBox secondRow;
    @FXML private HBox thirdRow;
    @FXML private Button backButton;
    
    private VBox currentKeyboard;
    private List<HBox> letterRows;
    private int currentRow = 0;
    private int currentLetterIndex = 0;
    private int currentAttempt = 0;

    public GameController() throws IOException {
        super();
    }
    
    @FXML
    public void initialize() {
        // init Keyboard
        settings.addLanguageChangeListener(newLanguage -> updateKeyboard(newLanguage));
        updateKeyboard(settings.getLanguage());
        System.out.println("Init Keyboard");
        
        letterRows = new ArrayList<>();
        for (int i = 0; i < attempts; i++)
            letterRows.add((HBox) letterLabelsRows.getChildren().get(i));
        
        // this listener will be triggered whenever the scene associated with backButton changes.
        backButton.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                setupKeyboard();
            }
        });
        System.out.println("Init GameController");
    }

    private void updateKeyboard(Languages lang) {
        String keyboardId = lang.name().toLowerCase() + "Keyboard";
        Node targetKeyboard = null;
    
        for (Node node : keyboardContainer.getChildren()) {
            if (keyboardId.equals(node.getId())) {
                targetKeyboard = node;
            }
            node.setVisible(false);
            node.setManaged(false);
        }
        if (targetKeyboard == null) {
            throw new IllegalStateException("Keyboard not found for language: " + lang);
        }
        targetKeyboard.setVisible(true);
        targetKeyboard.setManaged(true);
        currentKeyboard = (VBox) targetKeyboard;
    }

    private void setupKeyboard() {
        // handling the physical keyboard
        backButton.getScene().addEventFilter(KeyEvent.KEY_PRESSED, this::handleKeyPress);
        
        // handling the virtual keyboard 
        for (Node row : currentKeyboard.getChildren()) {
            if (row instanceof HBox keyboardRow) {
                for (Node node : keyboardRow.getChildren()) {
                    if (node instanceof Button button) {
                        button.setFocusTraversable(false);
                        button.setOnAction(event -> {
                            handleButtonPress(button.getId());
                            // button.getParent().requestFocus();
                        });
                    }
                }
            }
        }
    }

    // translation into button id for physical keyboard
    private void handleKeyPress(KeyEvent event) {
        String keyId = switch (event.getCode()) {
            case ENTER -> "ENTER_BTN";
            case BACK_SPACE -> "DELETE_BTN";
            default -> event.getText().toUpperCase().matches("[A-ZА-Я]") 
                        ? "KEY_" + event.getText().toUpperCase() 
                        : null;
        };
        
        if (keyId != null) {
            handleButtonPress(keyId);
        }
        event.consume();
    }

    private void handleButtonPress(String buttonId) {
        if (currentRow >= attempts) return;
    
        HBox currentLetterRow = letterRows.get(currentRow);
        
        if (buttonId.startsWith("KEY_")) {
            String letter = buttonId.substring(4);
            if (currentLetterIndex < wordLength) {
                var label = (Label) currentLetterRow.getChildren().get(currentLetterIndex);
                label.setText(letter);
                currentLetterIndex++;
            }
        } 
        else if (buttonId.equals("DELETE_BTN") && currentLetterIndex > 0) {
            currentLetterIndex--;
            var label = (Label) currentLetterRow.getChildren().get(currentLetterIndex);
            label.setText(" ");
        } 
        else if (buttonId.equals("ENTER_BTN") && currentLetterIndex == wordLength) {
            processWord();
        }
    }

    private void processWord() {
        HBox currentLetterRow = letterRows.get(currentRow);
        var word = new StringBuilder();
        
        for (int i = 0; i < wordLength; i++) {
            var label = (Label) currentLetterRow.getChildren().get(i);
            word.append(label.getText());
        }
        submitWord(word.toString());
    }
   
    public boolean submitWord(String word) {
        if (currentAttempt >= attempts) {
            return false;
        }

        word = word.toLowerCase();
        
        try {
            if (!allWordsDictionary.isTheWordInTheDictionary(word)) {
                onInvalidWord(word);
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] result = comparingWords(word, answer);
        
        onWordSubmitted(result);

        boolean won = word.equals(answer);
        currentAttempt++;

        if (won || currentAttempt >= attempts) 
            onGameOver(won);

        return true;
    }

    public void onWordSubmitted(String[] comparisonResult) {
        HBox currentLetterRow = letterRows.get(currentRow);
        colorLetters(currentLetterRow, comparisonResult);
        
        currentRow++;
        currentLetterIndex = 0;
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
    
    public void onInvalidWord(String word) {
        showAlert("Ошибка", "Слово \"" + word + "\" не найдено в словаре");
        clearCurrentRow();
    }

    public void onGameOver(boolean won) {
        String message = won ? "Поздравляем! Вы угадали слово!" 
                           : "Игра окончена. Загаданное слово: " + answer;
        showAlert("Игра окончена", message);
        backButton.getScene().removeEventFilter(KeyEvent.KEY_PRESSED, this::handleKeyPress);
    }

    private void colorLetters(HBox row, String[] coloredResult) {
        for (int i = 0; i < wordLength && i < row.getChildren().size(); i++) {
            Label label = (Label) row.getChildren().get(i);
            String coloredChar = coloredResult[i];
            String text = coloredChar.replaceAll("\u001B\\[[0-9;]*m", "").toUpperCase();
            
            if (coloredChar.contains("\u001B[32m")) {
                label.setStyle("-fx-background-color: #6aaa64; -fx-text-fill: white; " +
                              "-fx-border-color: #6aaa64; -fx-border-width: 2px;");
                colorKeyboardButton(text, "#6aaa64");
            } 
            else if (coloredChar.contains("\u001B[33m")) {
                label.setStyle("-fx-background-color: #c9b458; -fx-text-fill: white; " +
                              "-fx-border-color: #c9b458; -fx-border-width: 2px;");
                colorKeyboardButton(text, "#c9b458");
            } 
            else {
                label.setStyle("-fx-background-color: #787c7e; -fx-text-fill: white; " +
                              "-fx-border-color: #787c7e; -fx-border-width: 2px;");
                colorKeyboardButton(text, "#787c7e");
            }
            
            label.setText(text);
        }
    }
    
    private void colorKeyboardButton(String letter, String color) {
        searchAndColorButtonInKeyboard(currentKeyboard, letter, color);
    }
    
    private void searchAndColorButtonInKeyboard(VBox keyboard, String letter, String color) {
        keyboard.getChildren().stream()
            .filter(row -> row instanceof HBox)
            .flatMap(row -> ((HBox) row).getChildren().stream())
            .filter(node -> node instanceof Button)
            .map(node -> (Button) node)
            .filter(button -> button.getText().equalsIgnoreCase(letter))
            .forEach(button -> {
                String currentStyle = button.getStyle();
                if (!currentStyle.contains("#6aaa64")) {
                    button.setStyle(currentStyle + " -fx-background-color: " + color + ";");
                }
            });
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
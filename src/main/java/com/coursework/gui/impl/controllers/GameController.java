package com.coursework.gui.impl.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.coursework.core.Wordle;
import com.coursework.core.enums.Languages;
import com.coursework.core.impl.languages.LanguageManager;
import com.coursework.core.impl.languages.Localizable;
import com.coursework.gui.impl.SceneLoader;

public class GameController extends Wordle implements Localizable {

    @FXML private VBox keyboardContainer;
    @FXML private VBox letterLabelsRows;
    @FXML private HBox firstRow;
    @FXML private HBox secondRow;
    @FXML private HBox thirdRow;
    @FXML private Button backButton;
    
    private SceneLoader sceneLoader;
    private LanguageManager languageManager;

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

    public void init(LanguageManager languageManager, SceneLoader sceneLoader) {
        this.languageManager = languageManager;    
        this.sceneLoader = sceneLoader;
        languageManager.registerLocalizable("gameplay", this);
        updateText(languageManager);
    }

    @Override
    public void updateText(LanguageManager languageManager) {
        backButton.setText(languageManager.getText("gameplay.menuButton"));
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
            case ESCAPE -> "ESCAPE";
            default -> event.getText().toUpperCase().matches("[A-ZА-Я]") 
                        ? "KEY_" + event.getText().toUpperCase() 
                        : null;
        };
        
        if (keyId != null) {
            if (keyId.equals("ESCAPE")) 
                Platform.exit();
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
                onInvalidWord();
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
    
    public void onInvalidWord() {
        animateError(currentRow);
    
        PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
        pause.setOnFinished(event -> clearCurrentRow());
        pause.play();
    }

    private void animateError(int rowIndex) {
        HBox currentLetterRow = letterRows.get(rowIndex);
        for (Node node : currentLetterRow.getChildren()) {
            if (node instanceof Label) {
                Label label = (Label) node;
                Timeline timeline = new Timeline(
                    new KeyFrame(Duration.millis(0), new KeyValue(label.rotateProperty(), 0)),
                    new KeyFrame(Duration.millis(50), new KeyValue(label.rotateProperty(), 5)),
                    new KeyFrame(Duration.millis(100), new KeyValue(label.rotateProperty(), 0)),
                    new KeyFrame(Duration.millis(175), new KeyValue(label.rotateProperty(), -5)),
                    new KeyFrame(Duration.millis(250), new KeyValue(label.rotateProperty(), 0))
                );
                timeline.setCycleCount(2);
                timeline.play();
            }
        }
    }

    public void onGameOver(boolean won) {
        if (won) {
            showWinMessage();
            return;
        }
        showLoseMessage();
    }

    private void colorLetters(HBox row, String[] coloredResult) {
        for (int i = 0; i < wordLength && i < row.getChildren().size(); i++) {
            Label label = (Label) row.getChildren().get(i);
            String coloredChar = coloredResult[i];
            String text = coloredChar.replaceAll("\u001B\\[[0-9;]*m", "").toUpperCase();
            
            if (coloredChar.contains("\u001B[32m")) {
                label.getStyleClass().add("user-word-green");
                searchAndColorButtonInKeyboard(currentKeyboard, text, "keyboard-button-green");
            } else if (coloredChar.contains("\u001B[33m")) {
                label.getStyleClass().add("user-word-yellow");
                searchAndColorButtonInKeyboard(currentKeyboard, text, "keyboard-button-yellow");
            } else {
                label.getStyleClass().add("user-word-pink");
                searchAndColorButtonInKeyboard(currentKeyboard, text, "keyboard-button-pink");
            }
            
            label.setText(text);            
        }
    }
    
    private void searchAndColorButtonInKeyboard(VBox keyboard, String letter, String colorClass) {
        keyboard.getChildren().stream()
            .filter(row -> row instanceof HBox)
            .flatMap(row -> ((HBox) row).getChildren().stream())
            .filter(node -> node instanceof Button)
            .map(node -> (Button) node)
            .filter(button -> button.getText().equalsIgnoreCase(letter))
            .forEach(button -> {
                if (!button.getStyleClass().contains("keyboard-button-green")) {
                    button.getStyleClass().clear();
                    button.getStyleClass().add(colorClass);
                }
            });
    }

    private void showWinMessage() {
        try {
            FXMLLoader loader = sceneLoader.win();
            Parent root = loader.load();
            WLPopupController controller = loader.getController();
            controller.init("win", languageManager, answer);
            controller.setAfterHideAction("menu", this::handleBackButton);
            controller.setAfterHideAction("restart", this::restartGame);
    
            Stage popupStage = new Stage();
            popupStage.initModality(Modality.WINDOW_MODAL);
            popupStage.initStyle(StageStyle.TRANSPARENT);
            popupStage.initOwner(backButton.getScene().getWindow());
            popupStage.setScene(new Scene(root, Color.TRANSPARENT));
            popupStage.setResizable(false);
            popupStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showLoseMessage() {
        try {
            FXMLLoader loader = sceneLoader.lose();
            Parent root = loader.load();
            WLPopupController controller = loader.getController();
            controller.init("lose", languageManager, answer);
            controller.setAfterHideAction("menu", this::handleBackButton);
            controller.setAfterHideAction("restart", this::restartGame);
    
            Stage popupStage = new Stage();
            popupStage.initModality(Modality.WINDOW_MODAL);
            popupStage.initStyle(StageStyle.TRANSPARENT);
            popupStage.initOwner(backButton.getScene().getWindow());
            popupStage.setScene(new Scene(root, Color.TRANSPARENT));
            popupStage.setResizable(false);
            popupStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void restartGame() {
        currentRow = 0;
        currentLetterIndex = 0;
        currentAttempt = 0;
    
        for (HBox row : letterRows) {
            for (Node node : row.getChildren()) {
                if (node instanceof Label) {
                    ((Label) node).setText(" ");
                    ((Label) node).getStyleClass().clear();
                    ((Label) node).getStyleClass().add("user-word-cells");
                }
            }
        }
    
        for (Node row : currentKeyboard.getChildren()) {
            if (row instanceof HBox keyboardRow) {
                for (Node node : keyboardRow.getChildren()) {
                    if (node instanceof Button button) {
                        String buttonID = button.getId();
                        if (buttonID.equals("ENTER_BTN") || buttonID.equals("DELETE_BTN"))
                            continue;
                        button.getStyleClass().clear();
                        button.getStyleClass().add("keyboard");
                    }
                }
            }
        }
    
        try {
            answer = settings.getAnswerDictionary().getRandomWord().toLowerCase();
            System.out.println("New answer word: " + answer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void handleBackButton() {
        try {
            Stage stage = (Stage) backButton.getScene().getWindow();
            Scene menuScene = sceneLoader.mainMenu();
            Stage gameStage = new Stage();
            gameStage.initStyle(StageStyle.UNDECORATED);
            gameStage.setScene(menuScene);
            gameStage.setFullScreen(true);
            gameStage.setFullScreenExitHint("");
            gameStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
            gameStage.show();
            stage.close();
        } catch (IOException e) {
            e.printStackTrace();
            Platform.exit();
        }
    }
}
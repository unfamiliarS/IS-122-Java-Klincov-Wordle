package com.coursework.gui.impl;

import java.io.IOException;

import com.coursework.core.UserInterface;
import com.coursework.core.UserInterfaceWL;
import com.coursework.core.impl.languages.LanguageManager;
import com.coursework.gui.impl.controllers.GameController;
import com.coursework.gui.impl.controllers.MenuController;
import com.coursework.gui.impl.controllers.SettingsController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class SceneLoader implements UserInterface<Scene>, UserInterfaceWL<FXMLLoader> {

    private final LanguageManager languageManager;

    public SceneLoader() {
        this.languageManager = LanguageManager.getInstance();
        System.out.println("Create SceneLoader");
    }

    public Scene mainMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/coursework/fxmls/menu.fxml"));
        Parent root = loader.load();

        MenuController controller = loader.getController();
        controller.init(languageManager, this);
        
        return new Scene(root);
    }

    public Scene settings() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/coursework/fxmls/settings.fxml"));
        Parent root = loader.load();
        
        SettingsController controller = loader.getController();
        controller.init(languageManager, this);
        
        return new Scene(root);
    }

    public Scene gameplay(String... keyword) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/coursework/fxmls/game.fxml"));
        Parent root = loader.load();
        
        GameController controller = loader.getController();
        controller.init(languageManager, this);
        
        return new Scene(root);
    }

    public FXMLLoader win() throws IOException {
        return new FXMLLoader(getClass().getResource("/com/coursework/fxmls/win.fxml"));
    }

    @Override
    public FXMLLoader lose() throws IOException {
        return new FXMLLoader(getClass().getResource("/com/coursework/fxmls/lose.fxml"));
    }
}
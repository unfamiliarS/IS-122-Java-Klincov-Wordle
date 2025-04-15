package com.coursework.gui.impl;

import java.io.IOException;

import com.coursework.core.UserInterface;
import com.coursework.core.impl.languages.LanguageManager;
import com.coursework.gui.controllers.GameController;
import com.coursework.gui.controllers.MenuController;
import com.coursework.gui.controllers.SettingsController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class SceneLoader implements UserInterface<Scene> {

    private final LanguageManager languageManager;

    public SceneLoader() {
        this.languageManager = LanguageManager.getInstance();
    }

    public Scene mainMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/coursework/fxmls/menu.fxml"));
        Parent root = loader.load();
        
        // Получаем контроллер и передаем ему LanguageManager
        MenuController controller = loader.getController();
        controller.init(languageManager);
        
        return new Scene(root);
    }

    public Scene settings() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/coursework/fxmls/settings.fxml"));
        Parent root = loader.load();
        
        SettingsController controller = loader.getController();
        controller.init(languageManager);
        
        return new Scene(root);
    }

    public Scene gameplay(String... keyword) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/coursework/fxmls/game.fxml"));
        Parent root = loader.load();
        
        // GameController controller = loader.getController();
        // controller.init(LanguageManager, keyword);
        
        return new Scene(root);
    }
}
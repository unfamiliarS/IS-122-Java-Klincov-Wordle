package com.coursework.ui.cli;

import java.util.HashMap;
import java.util.Map;

import com.coursework.core.LanguageChangeListener;
import com.coursework.core.Settings;
import com.coursework.core.enums.Languages;

public class ConsoleMenus implements LanguageChangeListener {

    private Map<String, String> messages;

    public ConsoleMenus(Settings settings) {
        this.messages = loadMessages(settings.getLanguage());
        settings.setLanguageChangeListener(this);
    }

    public void onLanguageChanged(Languages newLanguage) {
        this.messages = loadMessages(newLanguage);
    }

    public Map<String, String> loadMessages(Languages language) {
        Map<String, String> messages = new HashMap<>();

        switch (language) {
            case ENG:
                messages.put("menu", "\n=============================\n__________ Wordle ___________\n\n\t1. Play\n\t2. Languages\n\t3. Exit\n\n=============================\nEnter: ");
                messages.put("settings", "\n=============================\n\n\teng - English\n\trus - Russian\n\n=============================\nEnter: ");
                messages.put("startingPhrase", "\n\n\n\n\nGuess the word.\n ");
                messages.put("attemptsCnt", "Tries last: ");
                messages.put("invalidLength", "Invalid word length (should be ");
                messages.put("invalidChars", "Only English letters are allowed.\n");
                messages.put("wordNotFound", "There is no such word in the dictionary. Perhaps it does not exist.\n");
                messages.put("incorrectAttempt", "Oups... didn't guess\n");
                messages.put("winMessage", "\nYey, you won! The hidden word was ");
                messages.put("loseMessage", "You're loose.\nThe answer was the word ");
                messages.put("replayPrompt", "\nDo you want to continue? (y/n)\n");
                messages.put("goodbye", "\nBye...");
                break;
            case RUS:
                messages.put("menu", "\n=============================\n__________ Wordle ___________\n\n\t1. Играть\n\t2. Язык\n\t3. Выход\n\n=============================\nВвод: ");
                messages.put("settings", "\n=============================\n\n\teng - Английский\n\trus - Русский\n\n=============================\nВвод: ");
                messages.put("startingPhrase", "\n\n\n\n\nОтгадай слово\n ");
                messages.put("attemptsCnt", "Попыток осталось: ");
                messages.put("invalidLength", "Неверная длина слова (должно быть ");
                messages.put("invalidChars", "Допускается ввод только русских букв (без 'ё').\n");
                messages.put("wordNotFound", "Такого слова нет в словаре. Возможно его не существует.\n");
                messages.put("incorrectAttempt", "Упс... не угадал\n\n");
                messages.put("winMessage", "\nУра, ты выиграл! Загаданное слово было ");
                messages.put("loseMessage", "Ты проиграл.\nОтветом было слово ");
                messages.put("replayPrompt", "\nХочешь продолжить? (д/н)\n");
                messages.put("goodbye", "\nПока...");
                break;
            default:
                throw new EnumConstantNotPresentException(Languages.class, language.name());
        }

        return messages;
    }

    public String getMessage(String key) {
        return messages.get(key);
    }
}

package com.coursework.core.impl;

import java.util.HashMap;
import java.util.Map;

import com.coursework.core.GameMessages;

public final class RussianGameMessages extends GameMessages{

    public RussianGameMessages() {
        super();
    }

    protected Map<String, String> loadMessages() {
        Map<String, String> messages = new HashMap<>();
                
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

        return messages;
    }

}

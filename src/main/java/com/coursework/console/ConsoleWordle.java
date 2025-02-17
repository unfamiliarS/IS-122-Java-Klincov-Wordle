package com.coursework.console;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.coursework.core.Dictionary;
import com.coursework.core.Wordle;
import com.coursework.core.enums.Languages;
import com.coursework.ui.cli.ConsoleUI;

public class ConsoleWordle extends Wordle<String> {

    public ConsoleWordle() {
        super();
        super.ui = new ConsoleUI(settings);
    }

    public static void play() {
        new ConsoleWordle().game();
    }

    @Override
    public void game() {

        try (var scanner = new Scanner(new InputStreamReader(System.in));
             var pw = new PrintWriter(new OutputStreamWriter(System.out), true)) {
                
            while (true) {
                try {

                    pw.print(ui.mainMenu());
                    pw.flush();
                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    switch (choice) {
                        case 1:
                            gameplay(scanner, pw);
                            break;

                        case 2:
                            settings(scanner, pw);
                            break;

                        case 3:
                            pw.println(ui.goodbye());
                            System.exit(0);

                        default:
                            break;
                    }

                } catch (InputMismatchException e) {
                    scanner.nextLine();
                } catch (IOException e) {
                    pw.println(e.getMessage());
                }
            }
        }
    }

    private void gameplay(Scanner scanner, PrintWriter pw) throws IOException {

        int attempts = settings.getAttempts();
        String[] userWord = new String[attempts];
        String answer = settings.getAnswerDictionary().getRandomWord();
        Dictionary allWordsDictionary = settings.getWordDictionary();
        boolean isWin = false;

        pw.println(getGameplayMesg("startingPhrase"));

        while (attempts != 0) {
            pw.println(getGameplayMesg("attemptsCnt") + attempts);
            for (int i = settings.getAttempts()-1; i > (attempts-1); i--) {
                pw.println("\t" + userWord[i]);
            }
            pw.print(": ");
            pw.flush();
            userWord[attempts - 1] = scanner.nextLine().toLowerCase();
            String enteredWord = userWord[attempts - 1];

            if (enteredWord.length() != settings.getWordLength()) {
                pw.println(getGameplayMesg("invalidLength") + settings.getWordLength() + ").\n");

            } else if (!checkCorrectSymbols(enteredWord)) {
                pw.println(getGameplayMesg("invalidChars"));

            } else if (!allWordsDictionary.isTheWordInTheDictionary(enteredWord)) {
                pw.println(getGameplayMesg("wordNotFound"));
                
            } else if (!enteredWord.equals(answer)) {
                userWord[attempts - 1] = comparingWords(enteredWord, answer);
                pw.println(getGameplayMesg("incorrectAttempt"));
                attempts--;

            } else {
                isWin = true;
                String greenAnswer = comparingWords(enteredWord, answer);
                pw.println(ui.win() + greenAnswer);
                pw.println(getGameplayMesg("replayPrompt"));
                String replayOrNot;
                do {
                    replayOrNot = scanner.nextLine();
                } while (!replayOrNot.matches("[yдnн]"));

                if (replayOrNot.matches("[yд]")) {
                    break;
                } else {
                    pw.println(ui.goodbye());
                    System.exit(0);
                }
            }
        }

        if (!isWin)
            pw.println(ui.lose() + '\'' + answer + '\'');
    }

    private void settings(Scanner scanner, PrintWriter pw) {

        String choice;
        boolean validChoice = false;
    
        do {
            pw.print(ui.settings());
            pw.flush();
            choice = scanner.nextLine().toUpperCase();
    
            try {
                Languages selectedLanguage = Languages.valueOf(choice);
                validChoice = true;
                if (selectedLanguage != settings.getLanguage()) {
                    settings.setLanguage(selectedLanguage);
                    settings.saveProperties();
                }
            } catch (IllegalArgumentException e) {
            } catch (IOException e) {
                e.getStackTrace();
            }

        } while (!validChoice);
    }

    private boolean checkCorrectSymbols(String word) {
        String regex = null;
        Languages language = settings.getLanguage();
        switch (language) {
            case ENG:
                regex = "[a-z]*";
                break;
            case RUS:
                regex = "[а-я]*";
                break;
            default:
                throw new EnumConstantNotPresentException(Languages.class, language.name());
        }
        
        return word.matches(regex);
    }

    private String comparingWords(String userWord, String answerWord) {
        int wordLen = userWord.length();
        var result = new StringBuilder();

        String green = "\u001B[32m";
        String yellow = "\u001B[33m";
        String reset = "\u001B[0m";
    
        outer: for (int i = 0; i < wordLen; i++) {
            if (userWord.charAt(i) == answerWord.charAt(i)) {
                result.append(green).append(userWord.charAt(i)).append(reset);
                continue;
            }

            for (int j = 0; j < wordLen; j++) {
                if (answerWord.charAt(j) == userWord.charAt(i)) {
                    result.append(yellow).append(userWord.charAt(i)).append(reset);
                    continue outer;
                }
            }

            result.append(userWord.charAt(i));
        }
    
        return result.toString();
    }

    private String getGameplayMesg(String keyword) {
        String[] array = { keyword };
        return ui.gameplay(array);
    }
}
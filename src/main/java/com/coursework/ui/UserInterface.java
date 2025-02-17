package com.coursework.ui;

public interface UserInterface<T> {
    T mainMenu();
    T settings();
    T gameplay(String ... keyword);
    T win();
    T lose();

    default T goodbye() {
        // Implement in a subclass if needed
        return null;
    };
}

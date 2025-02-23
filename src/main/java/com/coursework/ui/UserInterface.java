package com.coursework.ui;

public interface UserInterface<T> {
    T mainMenu();
    T settings();
    T gameplay(String ... keyword);
}

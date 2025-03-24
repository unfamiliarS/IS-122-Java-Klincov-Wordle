package com.coursework.core;

public interface UserInterface<T> {
    T mainMenu();
    T settings();
    T gameplay(String ... keyword);
}

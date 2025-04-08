package com.coursework.core;

import java.io.IOException;

public interface UserInterface<T> {
    T mainMenu() throws IOException;
    T settings() throws IOException;
    T gameplay(String ... keyword) throws IOException;
}

package com.coursework.core;

import java.io.IOException;

public interface UserInterfaceWL<T> {
    T win() throws IOException;
    T lose() throws IOException;
}

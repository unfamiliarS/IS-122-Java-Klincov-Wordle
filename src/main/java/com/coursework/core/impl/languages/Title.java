package com.coursework.core.impl.languages;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class Title {
    @XmlElement(name = "error")
    private String error;
    
    @XmlElement(name = "gameOver")
    private String gameOver;
    
    public String getError() { return error; }
    public String getGameOver() { return gameOver; }
}

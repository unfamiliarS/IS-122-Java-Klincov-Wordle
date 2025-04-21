package com.coursework.core.impl.languages;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class Alerts {
    @XmlElement(name = "invalidWord")
    private String invalidWord;
    
    @XmlElement(name = "gameOver")
    private GameOver gameOver;
    
    @XmlElement(name = "title")
    private Title title;
    
    public String getInvalidWord() { return invalidWord; }
    public GameOver getGameOver() { return gameOver; }
    public Title getTitle() { return title; }
}

package com.coursework.core.impl.languages;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class GameOver {
    @XmlElement(name = "won")
    private String won;
    
    @XmlElement(name = "lost")
    private String lost;
    
    public String getWon() { return won; }
    public String getLost() { return lost; }
}

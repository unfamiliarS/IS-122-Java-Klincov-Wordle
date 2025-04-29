package com.coursework.core.impl.languages;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class Gameplay {

    @XmlElement(name = "buttons")
    private Buttons buttons;
    @XmlElement(name = "popup")
    private Popup popup;
    
    public Buttons getButtons() { 
        return buttons; 
    }
    
    public Popup getPopup() {
        return popup;
    }
}
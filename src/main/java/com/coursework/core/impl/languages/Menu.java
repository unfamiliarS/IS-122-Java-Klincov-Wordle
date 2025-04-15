package com.coursework.core.impl.languages;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class Menu {
    @XmlElement
    private Buttons buttons;
    
    public Buttons getButtons() { return buttons; }
}

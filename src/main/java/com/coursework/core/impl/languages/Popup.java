package com.coursework.core.impl.languages;

import jakarta.xml.bind.annotation.XmlElement;

public class Popup {

    @XmlElement(name = "labels")
    private Labels labels;
    @XmlElement(name = "buttons")
    private Buttons buttons;

    public Labels getLabels() {
        return labels;
    }

    public Buttons getButtons() { 
        return buttons; 
    }
}

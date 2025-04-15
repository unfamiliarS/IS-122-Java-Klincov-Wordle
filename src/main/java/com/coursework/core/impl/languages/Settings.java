package com.coursework.core.impl.languages;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class Settings {
    @XmlElement
    private Labels labels;
    @XmlElement
    private Buttons buttons;
    
    public Labels getLabels() { return labels; }
    public Buttons getButtons() { return buttons; }
}

package com.coursework.core.impl.languages;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class Gameplay {
    @XmlElement(name = "buttons")
    private Buttons buttons;
    
    @XmlElement(name = "alerts")
    private Alerts alerts;
    
    public Buttons getButtons() { return buttons; }
    public Alerts getAlerts() { return alerts; }
}
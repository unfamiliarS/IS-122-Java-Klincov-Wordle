package com.coursework.core.impl.languages;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class Labels {
    @XmlElement(name = "mainLabel")
    private String mainLabel;
    @XmlElement(name = "language")
    private String language;
    
    public String getMainLabel() { return mainLabel; }
    public String getLanguage() { return language; }
}

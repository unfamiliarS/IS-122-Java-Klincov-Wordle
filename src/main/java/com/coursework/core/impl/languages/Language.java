package com.coursework.core.impl.languages;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class Language {

    @XmlAttribute
    private String id;
    @XmlElement
    private Menu menu;
    @XmlElement
    private Settings settings;
    @XmlElement
    private Gameplay gameplay;
    @XmlElement
    private Rule rule;
    
    public String getId() { return id; }
    public Menu getMenu() { return menu; }
    public Settings getSettings() { return settings; }
    public Gameplay getGameplay() { return gameplay; }
    public Rule getRule() { return rule; }
    
}

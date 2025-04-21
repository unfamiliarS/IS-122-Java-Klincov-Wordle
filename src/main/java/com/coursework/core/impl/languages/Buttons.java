package com.coursework.core.impl.languages;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class Buttons {
    @XmlElement(name = "play")
    private String play;
    @XmlElement(name = "settings")
    private String settings;
    @XmlElement(name = "exit")
    private String exit;
    @XmlElement(name = "rules")
    private String rules;
    @XmlElement(name = "russian")
    private String russian;
    @XmlElement(name = "english")
    private String english;
    @XmlElement(name = "menu")
    private String menu;
    @XmlElement(name = "back")
    private String back;
    
    public String getPlay() { return play; }
    public String getSettings() { return settings; }
    public String getExit() { return exit; }
    public String getRules() { return rules; }
    public String getRussian() { return russian; }
    public String getEnglish() { return english; }
    public String getMenu() { return menu; }
    public String getBack() { return back; }
}

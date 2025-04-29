package com.coursework.core.impl.languages;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class Buttons {
    @XmlElement(name = "playButton")
    private String playButton;
    @XmlElement(name = "rulesButton")
    private String rulesButton;
    @XmlElement(name = "settingsButton")
    private String settingsButton;
    @XmlElement(name = "exitButton")
    private String exitButton;
    @XmlElement(name = "russianButton")
    private String russianButton;
    @XmlElement(name = "englishButton")
    private String englishButton;
    @XmlElement(name = "restartButton")
    private String restartButton;
    @XmlElement(name = "menuButton")
    private String menuButton;
    
    public String getPlayButton() { return playButton; }
    public String getSettingsButton() { return settingsButton; }
    public String getExitButton() { return exitButton; }
    public String getRulesButton() { return rulesButton; }
    public String getRussianButton() { return russianButton; }
    public String getEnglishButton() { return englishButton; }
    public String getRestartButton() { return restartButton; }
    public String getMenuButton() { return menuButton; }
}

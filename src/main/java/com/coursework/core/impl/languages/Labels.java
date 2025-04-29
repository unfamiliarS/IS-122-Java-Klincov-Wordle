package com.coursework.core.impl.languages;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class Labels {
    
    @XmlElement(name = "titleLabel")
    private String titleLabel;
    @XmlElement(name = "subtitleLabel")
    private String subtitleLabel;
    @XmlElement(name = "winTitleLabel")
    private String winTitleLabel;
    @XmlElement(name = "loseTitleLabel")
    private String loseTitleLabel;
    @XmlElement(name = "firstRule")
    private String firstRule;
    @XmlElement(name = "secondRule")
    private String secondRule;
    @XmlElement(name = "thirdRule")
    private String thirdRule;
    @XmlElement(name = "exampleTitleLabel")
    private String exampleTitleLabel;
    @XmlElement(name = "exampleLetter1")
    private String exampleLetter1;
    @XmlElement(name = "exampleLetter2")
    private String exampleLetter2;
    @XmlElement(name = "exampleLetter3")
    private String exampleLetter3;
    @XmlElement(name = "exampleLetter4")
    private String exampleLetter4;
    @XmlElement(name = "exampleLetter5")
    private String exampleLetter5;
    @XmlElement(name = "firstExampleExplanation")
    private String firstExampleExplanation;
    @XmlElement(name = "secondExampleExplanation")
    private String secondExampleExplanation;
    @XmlElement(name = "thirdExampleExplanation")
    private String thirdExampleExplanation;

    public String getTitleLabel() {
        return titleLabel;
    }

    public String getSubtitleLabel() {
        return subtitleLabel;
    }

    public String getWinTitleLabel() {
        return winTitleLabel;
    }

    public String getLoseTitleLabel() {
        return loseTitleLabel;
    }

    public String getFirstRule() {
        return firstRule;
    }

    public String getSecondRule() {
        return secondRule;
    }

    public String getThirdRule() {
        return thirdRule;
    }

    public String getExampleTitleLabel() {
        return exampleTitleLabel;
    }

    public String getExampleLetter1() {
        return exampleLetter1;
    }

    public String getExampleLetter2() {
        return exampleLetter2;
    }

    public String getExampleLetter3() {
        return exampleLetter3;
    }

    public String getExampleLetter4() {
        return exampleLetter4;
    }

    public String getExampleLetter5() {
        return exampleLetter5;
    }

    public String getFirstExampleExplanation() {
        return firstExampleExplanation;
    }

    public String getSecondExampleExplanation() {
        return secondExampleExplanation;
    }

    public String getThirdExampleExplanation() {
        return thirdExampleExplanation;
    }
}

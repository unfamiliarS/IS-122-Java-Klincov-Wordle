package com.coursework.core.impl.languages;

import jakarta.xml.bind.annotation.XmlElement;

public class Rule {

    @XmlElement(name = "labels")
    private Labels labels;

    public Labels getLabels() {
        return labels;
    }
}

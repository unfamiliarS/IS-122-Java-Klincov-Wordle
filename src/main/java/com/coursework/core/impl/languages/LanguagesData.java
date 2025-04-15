package com.coursework.core.impl.languages;

import jakarta.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "languages")
@XmlAccessorType(XmlAccessType.FIELD)
public class LanguagesData {
    @XmlElement(name = "language")
    private List<Language> languages;

    public Language getLanguage(String id) {
        for (Language language : languages) {
            if (language.getId().equals(id)) {
                return language;
            }
        }
        return null;
    }
}

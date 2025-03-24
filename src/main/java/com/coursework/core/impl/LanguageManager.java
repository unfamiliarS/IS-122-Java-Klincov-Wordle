package com.coursework.core.impl;

import com.coursework.core.enums.Dictionaries;
import com.coursework.core.enums.Languages;

public class LanguageManager {

    public static Dictionary getAnswerWordsDictionary(Languages lang) {
        switch (lang) {
            case ENG:
                return new Dictionary(Dictionaries.ENG_DICT_ANSWER.getPath());        
            case RUS:
                return new Dictionary(Dictionaries.RUS_DICT_ANSWER.getPath());
            default:
                throw new EnumConstantNotPresentException(Languages.class, lang.name());
        }
    }

    public static Dictionary getAllWordsDictionary(Languages lang) {
        switch (lang) {
            case ENG:
                return new Dictionary(Dictionaries.ENG_DICT.getPath());        
            case RUS:
                return new Dictionary(Dictionaries.RUS_DICT.getPath());
            default:
                throw new EnumConstantNotPresentException(Languages.class, lang.name());
        }
    }
}

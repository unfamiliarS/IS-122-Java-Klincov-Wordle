package com.coursework.core.enums;

public enum Dictionaries {
    ENG_DICT_ANSWER("/com/coursework/dictionaries/eng_dict_answ.txt"),
    ENG_DICT("/com/coursework/dictionaries/eng_dict.txt"),
    RUS_DICT_ANSWER("/com/coursework/dictionaries/rus_dict_answ.txt"),
    RUS_DICT("/com/coursework/dictionaries/rus_dict.txt");

    private String path;

    Dictionaries(String path) { this.path = path; }   
    
    public String getPath() { return path; }
}

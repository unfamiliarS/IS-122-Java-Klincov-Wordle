package com.coursework.core.enums;

public enum Dictionaries {
    ENG_DICT_ANSWER("/dictionaries/eng_dict_answ.txt"),
    ENG_DICT("/dictionaries/eng_dict.txt"),
    RUS_DICT_ANSWER("/dictionaries/rus_dict_answ.txt"),
    RUS_DICT("/dictionaries/rus_dict.txt");

    private String path;

    Dictionaries(String path) { this.path = path; }   
    
    public String getPath() { return path; }
}

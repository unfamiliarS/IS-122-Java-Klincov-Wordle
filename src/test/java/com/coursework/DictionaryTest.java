package com.coursework;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.coursework.core.impl.Dictionary;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class DictionaryTest {

    private static final String TEST_DICT_PATH = "/com/coursework/test_dict.txt";
    private static Dictionary dictionary;

    @BeforeAll
    static void setUp() throws IOException {
        Path testDictPath = Paths.get("src", "test", "resources", TEST_DICT_PATH.substring(1));
        Files.createDirectories(testDictPath.getParent());
        
        Files.write(testDictPath, 
            "apple\nmelon\ngrape\npeach\nlemon\nmango".getBytes());
        
        dictionary = new Dictionary(TEST_DICT_PATH);
    }

    @Test
    void getRandomWord_returns5LetterWordFromDictionary() throws IOException {
        for (int i = 0; i < 100; i++) {
            String word = dictionary.getRandomWord();
            assertNotNull(word);
            assertEquals(5, word.length(), 
                "Word '" + word + "' should have 5 letters");
        }
    }

    @Test
    void isTheWordInTheDictionary_returnsTrueForExisting5LetterWord() throws IOException {
        assertTrue(dictionary.isTheWordInTheDictionary("apple"));
        assertTrue(dictionary.isTheWordInTheDictionary("melon"));
        assertTrue(dictionary.isTheWordInTheDictionary("grape"));
        assertTrue(dictionary.isTheWordInTheDictionary("peach"));
    }

    @Test
    void isTheWordInTheDictionary_returnsFalseForNon5LetterWords() throws IOException {
        assertFalse(dictionary.isTheWordInTheDictionary("pear"));    
        assertFalse(dictionary.isTheWordInTheDictionary("banana")); 
        assertFalse(dictionary.isTheWordInTheDictionary(""));        
        assertFalse(dictionary.isTheWordInTheDictionary("a"));   
    }

    @Test
    void isTheWordInTheDictionary_returnsFalseForNonExisting5LetterWord() throws IOException {
        assertFalse(dictionary.isTheWordInTheDictionary("abcde"));
        assertFalse(dictionary.isTheWordInTheDictionary("plane"));
        assertFalse(dictionary.isTheWordInTheDictionary("cloud"));
    }

    @Test
    void isTheWordInTheDictionary_isCaseSensitive() throws IOException {
        assertFalse(dictionary.isTheWordInTheDictionary("Apple"));
        assertFalse(dictionary.isTheWordInTheDictionary("MELON"));
    }

}
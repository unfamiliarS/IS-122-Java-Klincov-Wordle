package com.coursework.core.impl.languages;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;

import com.coursework.core.LanguageChangeListener;
import com.coursework.core.enums.Dictionaries;
import com.coursework.core.enums.Languages;
import com.coursework.core.impl.Dictionary;
import com.coursework.core.impl.Settings;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class LanguageManager implements LanguageChangeListener {
    
    private static final String XML_FILE_PATH = "/com/coursework/game_messages.xml";
    private static LanguageManager instance;
    private final Settings settings;
    private LanguagesData languagesData;
    private final Map<String, Function<Language, String>> textProviders = new HashMap<>();
    private final Map<String, Localizable> localizableComponents = new HashMap<>();

    private LanguageManager() {
        settings = Settings.getInstance();
        settings.setLanguageChangeListener(this);
        loadTexts();
        registerDefaultTextProviders();
        System.out.println("Create LanguageManager");
    }

    public static LanguageManager getInstance() {
        if (instance == null) {
            instance = new LanguageManager();
        }
        return instance;
    }

    private void loadTexts() {
        try (InputStream is = getClass().getResourceAsStream(XML_FILE_PATH)) {
            if (is == null) {
                throw new RuntimeException("XML file not found");
            }
            
            JAXBContext jaxbContext = JAXBContext.newInstance(LanguagesData.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            languagesData = (LanguagesData) jaxbUnmarshaller.unmarshal(is);
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void registerDefaultTextProviders() {
        // menu
        registerTextProvider("menu.play", l -> l.getMenu().getButtons().getPlay());
        registerTextProvider("menu.settings", l -> l.getMenu().getButtons().getSettings());
        registerTextProvider("menu.exit", l -> l.getMenu().getButtons().getExit());
        registerTextProvider("menu.rules", l -> l.getMenu().getButtons().getRules());
        
        // settings
        registerTextProvider("settings.mainLabel", l -> l.getSettings().getLabels().getMainLabel());
        registerTextProvider("settings.languageLabel", l -> l.getSettings().getLabels().getLanguage());
        registerTextProvider("settings.russian", l -> l.getSettings().getButtons().getRussian());
        registerTextProvider("settings.english", l -> l.getSettings().getButtons().getEnglish());
        registerTextProvider("settings.back", l -> l.getSettings().getButtons().getBack());

        // gameplay
    }

    public void registerTextProvider(String textId, Function<Language, String> provider) {
        textProviders.put(textId, provider);
    }

    public String getText(String textId) {
        Languages currentLanguage = settings.getLanguage();
        Language languageData = languagesData.getLanguage(currentLanguage.name().toLowerCase());
        
        Function<Language, String> provider = textProviders.get(textId);
        if (provider != null && languageData != null) {
            return provider.apply(languageData);
        }
        return "[" + textId + "]";
    }

    public void registerLocalizable(String id, Localizable component) {
        localizableComponents.put(id, component);
        component.updateText(this);
    }

    @Override
    public void onLanguageChanged(Languages newLanguage) {
        localizableComponents.values().forEach(component -> component.updateText(this));
    }

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
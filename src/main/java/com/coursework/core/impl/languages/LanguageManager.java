package com.coursework.core.impl.languages;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;

import com.coursework.core.enums.Dictionaries;
import com.coursework.core.enums.Languages;
import com.coursework.core.impl.Dictionary;
import com.coursework.core.impl.Settings;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class LanguageManager {
    
    private static final String XML_FILE_PATH = "/com/coursework/game_messages.xml";
    private static LanguageManager instance;
    private final Settings settings;
    private LanguagesData languagesData;
    private final Map<String, Function<Language, String>> textProviders = new HashMap<>();
    private final Map<String, Localizable> localizableComponents = new HashMap<>();

    private LanguageManager() {
        settings = Settings.getInstance();
        settings.addLanguageChangeListener(newLanguage -> 
            localizableComponents.values().forEach(component -> component.updateText(this)));
        loadTexts();
        registerDefaultTextProviders();
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
        registerTextProvider("menu.playButton", l -> l.getMenu().getButtons().getPlayButton());
        registerTextProvider("menu.rulesButton", l -> l.getMenu().getButtons().getRulesButton());
        registerTextProvider("menu.settingsButton", l -> l.getMenu().getButtons().getSettingsButton());
        registerTextProvider("menu.exitButton", l -> l.getMenu().getButtons().getExitButton());
        // rule
        registerTextProvider("rule.titleLabel", l -> l.getRule().getLabels().getTitleLabel());
        registerTextProvider("rule.firstRule", l -> l.getRule().getLabels().getFirstRule());
        registerTextProvider("rule.secondRule", l -> l.getRule().getLabels().getSecondRule());
        registerTextProvider("rule.thirdRule", l -> l.getRule().getLabels().getThirdRule());
        registerTextProvider("rule.exampleTitleLabel", l -> l.getRule().getLabels().getExampleTitleLabel());
        registerTextProvider("rule.exampleLetter1", l -> l.getRule().getLabels().getExampleLetter1());
        registerTextProvider("rule.exampleLetter2", l -> l.getRule().getLabels().getExampleLetter2());
        registerTextProvider("rule.exampleLetter3", l -> l.getRule().getLabels().getExampleLetter3());
        registerTextProvider("rule.exampleLetter4", l -> l.getRule().getLabels().getExampleLetter4());
        registerTextProvider("rule.exampleLetter5", l -> l.getRule().getLabels().getExampleLetter5());
        registerTextProvider("rule.firstExampleExplanation", l -> l.getRule().getLabels().getFirstExampleExplanation());
        registerTextProvider("rule.secondExampleExplanation", l -> l.getRule().getLabels().getSecondExampleExplanation());
        registerTextProvider("rule.thirdExampleExplanation", l -> l.getRule().getLabels().getThirdExampleExplanation());
        // settings
        registerTextProvider("settings.titleLabel", l -> l.getSettings().getLabels().getTitleLabel());
        registerTextProvider("settings.russianButton", l -> l.getSettings().getButtons().getRussianButton());
        registerTextProvider("settings.englishButton", l -> l.getSettings().getButtons().getEnglishButton());
        // gameplay
        registerTextProvider("gameplay.popup.winTitleLabel", l -> l.getGameplay().getPopup().getLabels().getWinTitleLabel());
        registerTextProvider("gameplay.popup.loseTitleLabel", l -> l.getGameplay().getPopup().getLabels().getLoseTitleLabel());
        registerTextProvider("gameplay.popup.subtitleLabel", l -> l.getGameplay().getPopup().getLabels().getSubtitleLabel());
        registerTextProvider("gameplay.popup.restartButton", l -> l.getGameplay().getPopup().getButtons().getRestartButton());
        registerTextProvider("gameplay.popup.menuButton", l -> l.getGameplay().getPopup().getButtons().getMenuButton());
        registerTextProvider("gameplay.menuButton", l -> l.getGameplay().getButtons().getMenuButton());
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

    public static Dictionary getAnswerWordsDictionary(Languages lang) {
        return switch (lang) {
            case ENG -> new Dictionary(Dictionaries.ENG_DICT_ANSWER.getPath());
            case RUS -> new Dictionary(Dictionaries.RUS_DICT_ANSWER.getPath());
            default -> throw new EnumConstantNotPresentException(Languages.class, lang.name());
        };
    }

    public static Dictionary getAllWordsDictionary(Languages lang) {
        return switch (lang) {
            case ENG -> new Dictionary(Dictionaries.ENG_DICT.getPath());        
            case RUS -> new Dictionary(Dictionaries.RUS_DICT.getPath());
            default -> throw new EnumConstantNotPresentException(Languages.class, lang.name());
        };
    }
}
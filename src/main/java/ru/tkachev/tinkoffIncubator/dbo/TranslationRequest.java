package ru.tkachev.tinkoffIncubator.dbo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TranslationRequest {

    private String sourceLanguage;
    private String targetLanguage;
    private String text;

}

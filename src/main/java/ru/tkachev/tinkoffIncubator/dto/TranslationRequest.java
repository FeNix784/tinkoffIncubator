package ru.tkachev.tinkoffIncubator.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TranslationRequest {

    private String sourceLanguage;
    private String targetLanguage;
    private String text;

}

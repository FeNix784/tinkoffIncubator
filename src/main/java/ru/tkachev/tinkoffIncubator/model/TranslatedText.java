package ru.tkachev.tinkoffIncubator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.concurrent.ConcurrentHashMap;

@Getter
@AllArgsConstructor
public class TranslatedText {

    private String translatedText;
    private ConcurrentHashMap<String, String> words;

}

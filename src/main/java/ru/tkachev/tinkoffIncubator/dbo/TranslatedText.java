package ru.tkachev.tinkoffIncubator.dbo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Set;

@Getter
@AllArgsConstructor
public class TranslatedText {

    private String translatedText;
    private Set<HashMap<String, String>> words;

}

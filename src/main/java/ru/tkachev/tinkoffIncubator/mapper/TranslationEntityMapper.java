package ru.tkachev.tinkoffIncubator.mapper;

import ru.tkachev.tinkoffIncubator.entity.RequestEntity;
import ru.tkachev.tinkoffIncubator.entity.TranslationEntity;

public class TranslationEntityMapper {

    public TranslationEntity toTranslationEntity(RequestEntity requestEntity, String word, String translatedWord) {
        return TranslationEntity.builder()
                .request(requestEntity)
                .word(word)
                .translatedWord(translatedWord)
                .build();
    }
}

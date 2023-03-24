package ru.tkachev.tinkoffIncubator.mapper;

import ru.tkachev.tinkoffIncubator.dto.TranslationRequestDTO;
import ru.tkachev.tinkoffIncubator.dto.YandexAPIRequestDTO;

public class RequestMapper {

    public YandexAPIRequestDTO toYandexAPIRequest(TranslationRequestDTO translationRequestDTO) {
        return YandexAPIRequestDTO.builder()
                .sourceLanguageCode(translationRequestDTO.getSourceLanguage())
                .targetLanguageCode(translationRequestDTO.getTargetLanguage())
                .texts(translationRequestDTO.getText().split(" "))
                .build();
    }
}

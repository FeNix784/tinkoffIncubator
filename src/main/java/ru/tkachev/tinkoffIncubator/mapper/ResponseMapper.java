package ru.tkachev.tinkoffIncubator.mapper;

import ru.tkachev.tinkoffIncubator.dto.TranslatedResponseDTO;
import ru.tkachev.tinkoffIncubator.dto.YandexAPIResponseDTO;

public class ResponseMapper {

    public TranslatedResponseDTO toTranslatedResponse(YandexAPIResponseDTO yandexAPIResponseDTO) {

        StringBuilder stringBuilder = new StringBuilder();
        yandexAPIResponseDTO.getTranslations().forEach(word -> stringBuilder.append(word.get("text")).append(" "));

        return TranslatedResponseDTO.builder()
                .translatedText(stringBuilder.toString().trim())
                .build();
    }
}

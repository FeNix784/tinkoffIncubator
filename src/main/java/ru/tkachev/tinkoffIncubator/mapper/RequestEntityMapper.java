package ru.tkachev.tinkoffIncubator.mapper;

import ru.tkachev.tinkoffIncubator.dto.TranslatedResponseDTO;
import ru.tkachev.tinkoffIncubator.dto.TranslationRequestDTO;
import ru.tkachev.tinkoffIncubator.entity.RequestEntity;

public class RequestEntityMapper {

    public RequestEntity toRequestEntity(TranslationRequestDTO translationRequestDTO, TranslatedResponseDTO translatedResponseDTO) {
        return RequestEntity.builder()
                .inputData(translationRequestDTO.getText())
                .outputData(translatedResponseDTO.getTranslatedText())
                .sourceLanguage(translationRequestDTO.getSourceLanguage())
                .targetLanguage(translationRequestDTO.getTargetLanguage())
                .requestTime(translationRequestDTO.getRequestTime())
                .IPAddress(translationRequestDTO.getIPAddress())
                .build();
    }
}

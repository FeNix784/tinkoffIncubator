package ru.tkachev.tinkoffIncubator.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tkachev.tinkoffIncubator.dto.TranslatedResponseDTO;
import ru.tkachev.tinkoffIncubator.dto.TranslationRequestDTO;
import ru.tkachev.tinkoffIncubator.dto.YandexAPIRequestDTO;
import ru.tkachev.tinkoffIncubator.dto.YandexAPIResponseDTO;
import ru.tkachev.tinkoffIncubator.mapper.RequestMapper;
import ru.tkachev.tinkoffIncubator.mapper.RequestEntityMapper;
import ru.tkachev.tinkoffIncubator.mapper.ResponseMapper;
import ru.tkachev.tinkoffIncubator.entity.RequestEntity;
import ru.tkachev.tinkoffIncubator.mapper.TranslationEntityMapper;
import ru.tkachev.tinkoffIncubator.repository.RequestRepository;
import ru.tkachev.tinkoffIncubator.repository.TranslationRepository;

@Service
@AllArgsConstructor
public class TranslationService {

    private final TranslatorService translatorService;
    private final RequestRepository requestRepository;
    private final TranslationRepository translationRepository;

    public TranslatedResponseDTO translate(TranslationRequestDTO translationRequestDTO) {

        YandexAPIRequestDTO yandexAPIRequestDTO = new RequestMapper().toYandexAPIRequest(translationRequestDTO);

        YandexAPIResponseDTO yandexAPIResponseDTO = translatorService.translate(yandexAPIRequestDTO);

        TranslatedResponseDTO translatedResponseDTO = new ResponseMapper().toTranslatedResponse(yandexAPIResponseDTO);

        RequestEntity requestEntity = new RequestEntityMapper().toRequestEntity(translationRequestDTO, translatedResponseDTO);
        requestRepository.save(requestEntity);

        for (int i = 0; i < yandexAPIResponseDTO.getTranslations().size(); i++) {
            translationRepository.save(new TranslationEntityMapper().toTranslationEntity(requestEntity,
                    yandexAPIRequestDTO.getTexts()[i], yandexAPIResponseDTO.getTranslations().get(i).get("text")));
        }

        return translatedResponseDTO;
    }
}
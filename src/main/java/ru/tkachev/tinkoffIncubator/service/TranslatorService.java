package ru.tkachev.tinkoffIncubator.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.tkachev.tinkoffIncubator.dto.YandexAPIRequestDTO;
import ru.tkachev.tinkoffIncubator.dto.YandexAPIResponseDTO;

@Service
@RequiredArgsConstructor
public class TranslatorService {

    @Value("${apiKey}")
    private String apiKey;

    @Value("${apiUrl}")
    private String apiUrl;

    private final RestTemplate restTemplate;

    public YandexAPIResponseDTO translate(YandexAPIRequestDTO yandexApiRequest) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", String.format("Api-Key %s", apiKey));

        HttpEntity<YandexAPIRequestDTO> entity = new HttpEntity<>(yandexApiRequest, headers);

        return restTemplate.exchange(apiUrl, HttpMethod.POST, entity, YandexAPIResponseDTO.class).getBody();
    }
}

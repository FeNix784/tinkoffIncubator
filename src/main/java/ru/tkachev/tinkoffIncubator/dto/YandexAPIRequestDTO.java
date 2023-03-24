package ru.tkachev.tinkoffIncubator.dto;

import lombok.*;

@Data
@Setter
@Getter
@Builder
@AllArgsConstructor
public class YandexAPIRequestDTO {

    private String sourceLanguageCode;
    private String targetLanguageCode;
    private String[] texts;

}

package ru.tkachev.tinkoffIncubator.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class TranslatedResponseDTO {

    private String translatedText;

}

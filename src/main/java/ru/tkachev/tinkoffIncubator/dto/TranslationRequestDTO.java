package ru.tkachev.tinkoffIncubator.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TranslationRequestDTO {

    private String sourceLanguage;
    private String targetLanguage;
    private String text;
    private String IPAddress;
    private Timestamp requestTime;

}

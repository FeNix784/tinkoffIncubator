package ru.tkachev.tinkoffIncubator.dto;

import lombok.*;

import java.util.List;
import java.util.Map;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class YandexAPIResponseDTO {

    List<Map<String, String>> translations;
}

package ru.tkachev.tinkoffIncubator.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tkachev.tinkoffIncubator.dto.TranslatedResponseDTO;
import ru.tkachev.tinkoffIncubator.dto.TranslationRequestDTO;
import ru.tkachev.tinkoffIncubator.service.TranslationService;

import java.sql.Timestamp;
import java.util.Date;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/translate")
@AllArgsConstructor
public class TranslationController {

    private final TranslationService translationService;

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<TranslatedResponseDTO> translate(@RequestBody TranslationRequestDTO translationRequestDTO, HttpServletRequest httpRequest) {

        translationRequestDTO.setIPAddress(httpRequest.getRemoteAddr());
        translationRequestDTO.setRequestTime(new Timestamp(new Date().getTime()));

        TranslatedResponseDTO translatedText = translationService.translate(translationRequestDTO);

        return ResponseEntity.ok(translatedText);
    }
}

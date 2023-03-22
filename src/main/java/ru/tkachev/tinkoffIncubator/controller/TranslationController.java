package ru.tkachev.tinkoffIncubator.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tkachev.tinkoffIncubator.model.TranslatedText;
import ru.tkachev.tinkoffIncubator.model.TranslationRequest;
import ru.tkachev.tinkoffIncubator.service.TranslationService;

import java.sql.Timestamp;
import java.util.Date;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/translate")
public class TranslationController {

    private final TranslationService translationService;

    public TranslationController(TranslationService translationService) {
        this.translationService = translationService;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<TranslatedText> translate(@RequestBody TranslationRequest request, HttpServletRequest httpRequest) {
        TranslatedText translatedText = translationService.translate(request, new Timestamp(new Date().getTime()), httpRequest.getRemoteAddr());
        return ResponseEntity.ok(translatedText);
    }
}

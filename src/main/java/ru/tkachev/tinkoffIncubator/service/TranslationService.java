package ru.tkachev.tinkoffIncubator.service;

import org.springframework.stereotype.Service;
import ru.tkachev.tinkoffIncubator.entity.Request;
import ru.tkachev.tinkoffIncubator.entity.Translation;
import ru.tkachev.tinkoffIncubator.model.TranslatedText;
import ru.tkachev.tinkoffIncubator.model.TranslationRequest;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.concurrent.*;

@Service
public class TranslationService {

    private final TranslatorService translatorService;

    public TranslationService(TranslatorService translatorService) {
        this.translatorService = translatorService;
    }

    public TranslatedText translate(TranslationRequest request, Timestamp requestTime, String IPAddress) {

        String sourceText = request.getText();
        String sourceLanguage = request.getSourceLanguage();
        String targetLanguage = request.getTargetLanguage();
        String[] sourceWords = sourceText.split(" ");

        JDBCService jdbcService = new JDBCService();
        Request userRequest = new Request(sourceText, "", sourceLanguage, targetLanguage, requestTime, IPAddress);
        Long requestID = jdbcService.insertRequest(userRequest);
        userRequest.setId(requestID);

        ConcurrentHashMap<String, String> translatedText = new ConcurrentHashMap<>();
        String[] translatedWords = new String[sourceWords.length];

        ExecutorService executor = Executors.newFixedThreadPool(10);
        CountDownLatch cdl = new CountDownLatch(sourceWords.length);

        for (int i = 0; i < sourceWords.length; i++) {

            int finalI = i;

            Runnable task = () -> {

                String translatedWord = translatorService.translate(sourceWords[finalI], sourceLanguage, targetLanguage);
                translatedWords[finalI] = translatedWord;
                translatedText.put(sourceWords[finalI], translatedWord);

                Translation translation = new Translation(userRequest, sourceWords[finalI], translatedWord);
                jdbcService.insertTranslation(translation);

                cdl.countDown();

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            };

            executor.submit(task);
        }

        executor.shutdown();

        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String outputText = String.join(" ", translatedWords);
        userRequest.setOutputData(outputText);

        jdbcService.updateRequest(userRequest);

        return new TranslatedText(outputText, translatedText);
    }
}
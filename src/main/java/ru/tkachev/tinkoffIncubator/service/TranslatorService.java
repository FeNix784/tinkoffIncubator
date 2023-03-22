package ru.tkachev.tinkoffIncubator.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class TranslatorService {

    @Value("${apiKey}")
    private String apiKey;

    public String translate(String word, String sourceLanguage, String targetLanguage) {

        Map<String, String> parameters = new HashMap<>();
        parameters.put("sourceLanguageCode", sourceLanguage);
        parameters.put("targetLanguageCode", targetLanguage);
        parameters.put("texts", word);

        HttpURLConnection connection = null;

        try {
            URL url = new URL("https://translate.api.cloud.yandex.net/translate/v2/translate");

            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Api-Key AQVNzfHFB6ltZSvzt-qFxfZTSkCN5oBSur5XpA2X");
            connection.setRequestProperty("Content-Type", "application/json");

            connection.setDoOutput(true);

            try (OutputStream os = connection.getOutputStream()) {
                String parametersJSON = new JSONObject(parameters).toString();
                byte[] input = parametersJSON.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            StringBuilder response = new StringBuilder();

            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
            }

            JSONObject jsonObject = new JSONObject(response.toString());
            JSONArray translations = jsonObject.getJSONArray("translations");
            JSONObject translatedObject = translations.getJSONObject(0);

            return translatedObject.getString("text");

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            if (connection != null) {
                connection.disconnect();
            }

        }
        return "";
    }
}

package ru.tkachev.tinkoffIncubator.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition="TEXT")
    private String inputData;

    @Column(columnDefinition="TEXT")
    private String outputData;

    private String sourceLanguage;

    private String targetLanguage;

    private Timestamp requestTime;

    private String IPAddress;

    public Request(String inputData, String outputData, String sourceLanguage, String targetLanguage, Timestamp requestTime, String IPAddress) {
        this.inputData = inputData;
        this.outputData = outputData;
        this.sourceLanguage = sourceLanguage;
        this.targetLanguage = targetLanguage;
        this.requestTime = requestTime;
        this.IPAddress = IPAddress;
    }

}

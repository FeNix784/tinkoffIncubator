package ru.tkachev.tinkoffIncubator.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestEntity {

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

}

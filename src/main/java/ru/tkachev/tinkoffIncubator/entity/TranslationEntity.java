package ru.tkachev.tinkoffIncubator.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TranslationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "request_id", referencedColumnName = "id")
    private RequestEntity request;

    private String word;

    private String translatedWord;

}

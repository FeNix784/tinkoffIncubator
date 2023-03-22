package ru.tkachev.tinkoffIncubator.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
public class Translation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "request_id", referencedColumnName = "id")
    private Request request;

    private String word;

    private String translatedWord;

    public Translation(Request request, String word, String translatedWord) {
        this.request = request;
        this.word = word;
        this.translatedWord = translatedWord;
    }

}

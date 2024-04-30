package com.ihrsachin.quizservice.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Entity
@RequiredArgsConstructor
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String title;

    @ElementCollection
    private List<Integer> questionIds;

    public Quiz(String title, List<Integer> idsList) {
        this.title = title;
        this.questionIds = idsList;
    }
}

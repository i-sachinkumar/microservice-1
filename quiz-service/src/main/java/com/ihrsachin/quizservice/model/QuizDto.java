package com.ihrsachin.quizservice.model;


import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class QuizDto {
    String title;
    String categoryName;
    int numOfQuestions;
}

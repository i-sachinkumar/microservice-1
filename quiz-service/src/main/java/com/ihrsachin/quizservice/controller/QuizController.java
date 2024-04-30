package com.ihrsachin.quizservice.controller;


import com.ihrsachin.quizservice.model.QuestionWrapper;
import com.ihrsachin.quizservice.model.Quiz;
import com.ihrsachin.quizservice.model.QuizDto;
import com.ihrsachin.quizservice.model.Response;
import com.ihrsachin.quizservice.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("quiz")
public class QuizController {
    private final QuizService quizService;

    @GetMapping("allQuiz")
    public ResponseEntity<List<Quiz>> getAllQuiz(){
        return quizService.getAllQuiz();
    }

    @GetMapping("id/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable int id){
        return quizService.getQuiz(id);
    }

    @PostMapping("createQuiz")
    public ResponseEntity<Quiz> createQuiz(@RequestBody QuizDto quizDto){
        return quizService.createQuiz(quizDto.getTitle(),  quizDto.getCategoryName(), quizDto.getNumOfQuestions());
    }

    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
        return quizService.getScore(responses);
    }
}

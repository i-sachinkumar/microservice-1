package com.ihrsachin.quizservice.service;


import com.ihrsachin.quizservice.feign.QuizInterface;
import com.ihrsachin.quizservice.model.QuestionWrapper;
import com.ihrsachin.quizservice.model.Quiz;
import com.ihrsachin.quizservice.model.Response;
import com.ihrsachin.quizservice.repo.QuizRepo;
import com.netflix.discovery.EurekaClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepo quizRepo;
    private final QuizInterface quizInterface;

    public ResponseEntity<List<Quiz>> getAllQuiz() {
        return new ResponseEntity<>(quizRepo.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Quiz> createQuiz(String title, String categoryName, int numOfQuestion) {
        List<Integer> idsList = getQsIdsForQuiz(categoryName, numOfQuestion);
        Quiz quiz = new Quiz(title, idsList);
        System.out.println("quiz: " + quiz);
        quizRepo.save(quiz);
        return new ResponseEntity<>(quiz, HttpStatus.CREATED);
    }


    public ResponseEntity<List<QuestionWrapper>> getQuiz(int id) {
         Quiz quiz = quizRepo.findById(id).get();
         return quizInterface.getQuestionsFromId(quiz.getQuestionIds());
    }


    public ResponseEntity<Integer> getScore(List<Response> responses) {
        return quizInterface.getScore(responses);
    }





    //// Helper functions
    public List<Integer> getQsIdsForQuiz(String categoryName, int numOfQuestion){
        return quizInterface.getQuestionsForQuiz(categoryName, numOfQuestion).getBody();
    }


}

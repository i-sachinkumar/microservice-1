package com.example.questionservice.repo;

import com.example.questionservice.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Integer> {

    List<Question> findByCategory(String category);

    List<Question> findByDifficultyLevel(String difficultyLevel);

//    @Query(value = "SELECT q.id FROM question q Where q.category=:category ORDER BY RANDOM() LIMIT :numQ", nativeQuery = true)
//    List<Integer> findRandomQuestionsByCategory(String category, int numQ);

    @Query(value = "SELECT q.id FROM question q WHERE q.category = ?1 ORDER BY RAND() LIMIT ?2", nativeQuery = true)
    List<Integer> findRandomQuestionsByCategory(String category, int numQ);

}

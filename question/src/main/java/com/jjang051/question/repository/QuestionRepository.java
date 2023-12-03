package com.jjang051.question.repository;

import com.jjang051.question.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question,Integer> {
    Question findBySubject(String subject);
}

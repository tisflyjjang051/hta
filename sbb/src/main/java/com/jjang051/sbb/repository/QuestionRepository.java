package com.jjang051.sbb.repository;

import com.jjang051.sbb.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question,Integer> {
}

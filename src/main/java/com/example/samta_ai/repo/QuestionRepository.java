package com.example.samta_ai.repo;

import com.example.samta_ai.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question,Long> {
}

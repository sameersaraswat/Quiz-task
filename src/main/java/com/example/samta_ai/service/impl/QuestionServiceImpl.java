package com.example.samta_ai.service.impl;

import com.example.samta_ai.model.Question;
import com.example.samta_ai.repo.QuestionRepository;
import com.example.samta_ai.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;


    @Override
    public List<Question> getQuestions() {
        return new LinkedList<>(this.questionRepository.findAll());
    }

    @Override
    public Question getQuestionById(Long quesId) {
        return this.questionRepository.getById(quesId);
    }

    @Override
    public void saveQuestion(List<Question> question) {
        this.questionRepository.saveAll(question);
    }
}

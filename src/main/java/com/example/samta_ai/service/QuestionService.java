package com.example.samta_ai.service;

import com.example.samta_ai.model.Question;

import java.util.List;

public interface QuestionService {

    public List<Question> getQuestions();

    public Question getQuestionById(Long quesId);

    public void saveQuestion(List<Question> question);
}

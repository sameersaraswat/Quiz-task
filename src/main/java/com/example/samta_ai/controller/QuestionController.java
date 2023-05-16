package com.example.samta_ai.controller;

import com.example.samta_ai.model.Question;
import com.example.samta_ai.service.QuestionService;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.http.*;
import java.util.*;

@RestController
@RequestMapping("random/")
@CrossOrigin
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    private int currentQuestionIdx;

    public QuestionController() {
        this.currentQuestionIdx = 0;
    }

    @GetMapping("/")
    public void getRandomQuestions() throws IOException, InterruptedException {

        String url = "https://jservice.io/api/random";

        for (int i = 0;i < 5;i++) {
            var request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
            var client = HttpClient.newBuilder().build();
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                RestTemplate restTemplate = new RestTemplate();
                Question[] responseString = restTemplate.getForObject(url, Question[].class);

                // saving the data
                this.questionService.saveQuestion(Arrays.asList(responseString));
            }
        }
    }

    @GetMapping("/play")
    public ResponseEntity<?> getAllQuestions() {
        return ResponseEntity.ok(this.questionService.getQuestions());
    }

    @PostMapping("/next")
    public ResponseEntity<?> nextQuestion(@RequestParam Long quesId,@RequestParam String answer) {
        Map<String,Object> response = new LinkedHashMap<>();

        Question question = this.questionService.getQuestionById(quesId);

        response.put("correct_answer",question.getAnswer());

        List<Question> allQuestions = this.questionService.getQuestions();

        if (this.currentQuestionIdx < allQuestions.size() - 1) {
            this.currentQuestionIdx++;
            Question nextQuestion= allQuestions.get(this.currentQuestionIdx);
            response.put("next_question",nextQuestion);
        }
        else
            response.put("next_question","No More Questions");

        return ResponseEntity.ok(response);
    }
}

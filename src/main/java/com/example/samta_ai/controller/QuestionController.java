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

        for (int i = 0;i < 5;i++) {

            String url = "https://jservice.io/api/random";

            var request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();

            var client = HttpClient.newBuilder().build();

            var response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println(response.statusCode());
            System.out.println(response);
            System.out.println(response.body());
            System.out.println(response.body().getClass());


//            ObjectMapper objectMapper = new ObjectMapper();
//
//            objectMapper.registerModule(new JavaTimeModule());
//
            TypeReference<List<com.example.samta_ai.model.Question>> typeReference = new TypeReference<List<com.example.samta_ai.model.Question>>() {
            };
//            List<com.example.samta_ai.model.Question> ques = objectMapper.readValue(response.body(), typeReference);

//            this.questionService.saveQuestion(ques);

            RestTemplate restTemplate = new RestTemplate();

            Question[] responseString = restTemplate.getForObject(url, Question[].class);

//            MyData data = objectMapper.readValue(responseString, MyData.class);
//
//            objectMapper.registerModule(new JavaTimeModule());
//
//            List<Question> q =  objectMapper.readValue(responseString,typeReference);
            System.out.println(responseString);

//            this.questionService.saveQuestion(q);
            this.questionService.saveQuestion(Arrays.asList(responseString));




        }
    }

    @GetMapping("/play")
    public ResponseEntity<?> getAllQuestions() {
        return ResponseEntity.ok(this.questionService.getQuestions());
    }

    @PostMapping("/next")
    public ResponseEntity<?> nextQuestion(@RequestParam Long quesId,@RequestParam String answer) {

        Map<String,Object> hm = new LinkedHashMap<>();

        Question question = this.questionService.getQuestionById(quesId);

        hm.put("correct_answer",question.getAnswer());

        List<Question> allQuestions = this.questionService.getQuestions();

        if (this.currentQuestionIdx < allQuestions.size() - 1) {
            this.currentQuestionIdx++;
            Question nextQuestion= allQuestions.get(this.currentQuestionIdx);
            hm.put("next_question",nextQuestion);
        }
        else
            hm.put("next_question","No More Questions");
        return ResponseEntity.ok(hm);
    }
}

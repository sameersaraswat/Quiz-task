package com.example.samta_ai.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.Date;

@Entity
@Table(name = "question")
public class Question {

    @Id
    private Long id;

    private String question;

    private String answer;

    private String value;

    private OffsetDateTime airdate;

    private OffsetDateTime created_at;

    private OffsetDateTime updated_at;

    public String getInvalid_count() {
        return invalid_count;
    }

    public void setInvalid_count(String invalid_count) {
        this.invalid_count = invalid_count;
    }

    private String game_id;

    private String invalid_count;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="category_id")
    private Category category;


    public Question() {
    }

    public Question(Long id, String question, String answer, String value, OffsetDateTime airdate, OffsetDateTime created_at, OffsetDateTime updated_at, String game_id, Category category) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.value = value;
        this.airdate = airdate;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.game_id = game_id;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public OffsetDateTime getAirdate() {
        return airdate;
    }

    public void setAirdate(OffsetDateTime airdate) {
        this.airdate = airdate;
    }

    public OffsetDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(OffsetDateTime created_at) {
        this.created_at = created_at;
    }

    public OffsetDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(OffsetDateTime updated_at) {
        this.updated_at = updated_at;
    }

    public String getGame_id() {
        return game_id;
    }

    public void setGame_id(String game_id) {
        this.game_id = game_id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


}

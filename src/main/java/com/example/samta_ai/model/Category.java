package com.example.samta_ai.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.Set;

@Entity
@Table(name = "category")
public class Category {

    @Id
    private Long id;

    private String title;

    private OffsetDateTime created_at;

    private OffsetDateTime updated_at;

    private String clues_count;

    @OneToMany(mappedBy = "category",fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Question> question;

    public Category() {
    }

    public Category(Long id, String title, OffsetDateTime created_at, OffsetDateTime updated_at, String clues_count, Set<Question> question) {
        super();
        this.id = id;
        this.title = title;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.clues_count = clues_count;
        this.question = question;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getClues_count() {
        return clues_count;
    }

    public void setClues_count(String clues_count) {
        this.clues_count = clues_count;
    }

    public Set<Question> getQuestion() {
        return question;
    }

    public void setQuestion(Set<Question> question) {
        this.question = question;
    }
}

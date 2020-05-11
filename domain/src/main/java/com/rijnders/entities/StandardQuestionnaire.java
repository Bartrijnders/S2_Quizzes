package com.rijnders.entities;

import com.rijnders.entityinterfaces.Questionnair;
import com.rijnders.entityinterfaces.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StandardQuestionnaire implements Questionnair {

    private final UUID id;
    private String name;
    private List<StandardQuestion> questions;
    private final User author;

    public StandardQuestionnaire(UUID id, String name, List<StandardQuestion> questions, User author) {
        this.id = id;
        this.name = name;
        this.questions = questions;
        this.author = author;
    }

    public StandardQuestionnaire(UUID id, String name, User author) {
        this.id = id;
        this.name = name;
        this.author = author;
    }

    public StandardQuestionnaire(String name, User author) {
        this.name = name;
        this.author = author;
        this.questions = new ArrayList<>();
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<StandardQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(List<StandardQuestion> questions) {
        this.questions = questions;
    }

    public User getAuthor() {
        return author;
    }
}

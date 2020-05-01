package com.rijnders.entityinterfaces;

import com.rijnders.entities.StandardQuestion;

import java.util.List;
import java.util.UUID;

public interface Questionnair {
    UUID getId();

    String getName();

    void setName(String name);

    List<StandardQuestion> getQuestions();

    void setQuestions(List<StandardQuestion> questions);

    User getAuthor();
}

package com.example.arclight.models.quiz;

import java.util.List;

public class DescriptionToImageQuestion //show image select title
{
    private String description;
    private Long id;
    private List<AnswerChoice> choices;

    public DescriptionToImageQuestion(){}
    public DescriptionToImageQuestion(Long id, String description, List<AnswerChoice> choices) {
        this.description = description;
        this.id = id;
        this.choices = choices;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<AnswerChoice> getChoices() {
        return choices;
    }

    public void setChoices(List<AnswerChoice> choices) {
        this.choices = choices;
    }

}

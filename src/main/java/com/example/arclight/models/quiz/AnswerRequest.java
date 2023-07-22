package com.example.arclight.models.quiz;

public class AnswerRequest
{
    private Long id;
    private Long ChoiceId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChoiceId() {
        return ChoiceId;
    }

    public void setChoiceId(Long choiceId) {
        ChoiceId = choiceId;
    }
}

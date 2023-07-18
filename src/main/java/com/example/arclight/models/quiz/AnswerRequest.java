package com.example.arclight.models.quiz;

import com.example.arclight.entities.datatypes.QuestionType;

public class AnswerRequest
{
    private Long id;
    private QuestionType type;
    private Long ChoiceId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public QuestionType getType() {
        return type;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }

    public Long getChoiceId() {
        return ChoiceId;
    }

    public void setChoiceId(Long choiceId) {
        ChoiceId = choiceId;
    }
}

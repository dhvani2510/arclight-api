package com.example.arclight.models.quiz;

import java.util.List;

public class QuizSubmitRequest
{
    private List<AnswerRequest> answers;

    public List<AnswerRequest> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerRequest> answers) {
        this.answers = answers;
    }
}

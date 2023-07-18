package com.example.arclight.models.quiz;

import com.example.arclight.entities.datatypes.QuestionType;

import java.util.List;

public class ImageToTitleQuestion //show image select title
{
    private String image;
    private Long id;

    //@Enumerated(EnumType.STRING)
    private QuestionType  type;
    private List<AnswerChoice> choices;

    public  ImageToTitleQuestion(){}
    public ImageToTitleQuestion(Long id, QuestionType type, String image, List<AnswerChoice> choices) {
        this.image = image;
        this.id = id;
        this.type= type;
        this.choices = choices;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public QuestionType getType() {
        return type;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }
}

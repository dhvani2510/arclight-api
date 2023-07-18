package com.example.arclight.models.quiz;

import com.example.arclight.entities.Quiz;
import com.example.arclight.entities.datatypes.Category;

import java.util.List;

public class QuizResponse
{
    private Long id;
    private String title;
    private  String description;
    //@Enumerated(EnumType.STRING)
    private Category category;

    public List<ImageToTitleQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(List<ImageToTitleQuestion> questions) {
        this.questions = questions;
    }

    private List<ImageToTitleQuestion> questions;
    private  int durationInMinutes;

    public  QuizResponse(Quiz quiz){
        this.id= quiz.getId();
        this.title = quiz.getTitle();
        this.description = quiz.getDescription();
        this.category = quiz.getCategory();
        this.durationInMinutes = quiz.getDurationInMinutes();
    }

    public  QuizResponse(Quiz quiz, List<ImageToTitleQuestion> questions){
        this.id= quiz.getId();
        this.title = quiz.getTitle();
        this.description = quiz.getDescription();
        this.category = quiz.getCategory();
        this.durationInMinutes = quiz.getDurationInMinutes();
        this.questions=questions;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

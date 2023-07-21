package com.example.arclight.models.quiz;

import com.example.arclight.entities.datatypes.Category;

import java.util.List;

public class QuizResponse
{
    private Long id;
    private String title;
    private  String description;
    //@Enumerated(EnumType.STRING)
    private Category category;

    public List<DescriptionToImageQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(List<DescriptionToImageQuestion> questions) {
        this.questions = questions;
    }

    private List<DescriptionToImageQuestion> questions;
    private  int durationInMinutes;


    public  QuizResponse(List<DescriptionToImageQuestion> questions){
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

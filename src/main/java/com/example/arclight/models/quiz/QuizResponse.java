package com.example.arclight.models.quiz;

import com.example.arclight.entities.Quiz;
import com.example.arclight.entities.datatypes.Category;

public class QuizResponse
{
    private Long id;
    private String title;
    private  String description;
    //@Enumerated(EnumType.STRING)
    private Category category;
    private  double questionsCount; //Score
    private  int durationInMinutes;

    public  QuizResponse(Quiz quiz){
        this.id= quiz.getId();
        this.title = quiz.getTitle();
        this.description = quiz.getDescription();
        this.category = quiz.getCategory();
        this.questionsCount=0;
        this.durationInMinutes = quiz.getDurationInMinutes();
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

    public double getQuestionsCount() {
        return questionsCount;
    }

    public void setQuestionsCount(double questionsCount) {
        this.questionsCount = questionsCount;
    }
}

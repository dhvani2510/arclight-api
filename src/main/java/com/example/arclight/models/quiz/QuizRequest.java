package com.example.arclight.models.quiz;

import com.example.arclight.entities.datatypes.Category;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class QuizRequest
{
    private String title;
    private  String description;
    //@Enumerated(EnumType.STRING)
    private Category category;
    private  int durationInMinutes;

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
}

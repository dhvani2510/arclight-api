package com.example.arclight.models;

import com.example.arclight.entities.Translation;

public class TitleDescription
{
    private String title;
    private  String description;

    public TitleDescription(String title, String description) {
        this.title = title;
        this.description = description;
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
}

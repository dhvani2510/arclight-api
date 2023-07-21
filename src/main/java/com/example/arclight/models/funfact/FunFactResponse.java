package com.example.arclight.models.funfact;

import com.example.arclight.entities.FunFact;
import com.example.arclight.entities.datatypes.LanguageOption;

public class FunFactResponse
{
    private  Long id;
    private  String title;
    private String description;
    private  String image;

    public FunFactResponse(FunFact funfact, LanguageOption languageOption){
        if(funfact==null)
            return;
        this.id = funfact.getId();
        this.title = funfact.getTitle().translate(languageOption);
        this.description = funfact.getDescription().translate(languageOption);
        if(funfact.getImage()!=null)
            this.image = funfact.getImage().getUrl();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
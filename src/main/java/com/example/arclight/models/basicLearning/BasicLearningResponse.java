package com.example.arclight.models.basicLearning;

import com.example.arclight.entities.BasicLearning;
import com.example.arclight.entities.datatypes.Category;
import com.example.arclight.entities.datatypes.LanguageOption;
import com.example.arclight.shared.exceptions.ArclightException;

public class BasicLearningResponse
{
    private  Long id;
    private  String title;
    private String description;
    private  String image;
    //@Enumerated(EnumType.STRING)
    private Category category;

    public BasicLearningResponse(Long id, String title, String description, String image, Category category) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
        this.category= category;
    }

    public BasicLearningResponse(BasicLearning basicLearning, LanguageOption languageOption){
        if(basicLearning==null)
          return;
        this.id = basicLearning.getId();
        this.title = basicLearning.getTitle().translate(languageOption);
        this.description = basicLearning.getDescription().translate(languageOption);
        this.image = basicLearning.getImage().translate(languageOption);
        this.category= category;
    }
}

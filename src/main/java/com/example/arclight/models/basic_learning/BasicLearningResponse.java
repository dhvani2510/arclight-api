package com.example.arclight.models.basic_learning;

import com.example.arclight.entities.BasicLearning;
import com.example.arclight.entities.datatypes.Category;
import com.example.arclight.entities.datatypes.LanguageOption;
import com.example.arclight.models.TitleDescription;
import com.example.arclight.shared.exceptions.ArclightException;
import com.example.arclight.shared.helpers.StringHelper;

public class BasicLearningResponse
{
    private  Long id;
    private  String title;
    private String description;
    private  String image;
    //@Enumerated(EnumType.STRING)
    private Category category;
    private TitleDescription secondaryLanguage;
//    public BasicLearningResponse(Long id, String title, String description, String image, Category category) {
//        this.id = id;
//        this.title = title;
//        this.description = description;
//        this.image = image;
//        this.category= category;
//    }

    public BasicLearningResponse(BasicLearning basicLearning, LanguageOption languageOption){
        if(basicLearning==null)
            return;
        this.id = basicLearning.getId();
        this.title = basicLearning.getTitle().getEnglish();
        this.description = basicLearning.getDescription().getEnglish();
        if(basicLearning.getImage()!=null)
            this.image = basicLearning.getImage().getUrl();
        this.category= basicLearning.getCategory();

        var title= basicLearning.getTitle().translate(languageOption);
        var description = basicLearning.getDescription().translate(languageOption);
       if( !(StringHelper.StringIsNullOrEmpty(title)|| StringHelper.StringIsNullOrEmpty(description)))
        this.secondaryLanguage= new TitleDescription(title, description);
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public TitleDescription getSecondaryLanguage() {
        return secondaryLanguage;
    }

    public void setSecondaryLanguage(TitleDescription secondaryLanguage) {
        this.secondaryLanguage = secondaryLanguage;
    }
}
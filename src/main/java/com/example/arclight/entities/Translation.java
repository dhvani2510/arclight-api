package com.example.arclight.entities;

import com.example.arclight.entities.datatypes.LanguageOption;
import com.example.arclight.models.translation.TranslationRequest;
import jakarta.persistence.*;

@Entity
@Table
public class Translation extends  BaseEntity
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private   String english;
    private   String hindi;
    private   String french;

    public Translation(String english, String hindi, String french) {
        this.english = english;
        this.hindi = hindi;
        this.french = french;
    }
    public void Set(TranslationRequest translationRequest) {
      this.english=translationRequest.getEnglish();
      this.french= translationRequest.getFrench();
      this.hindi= translationRequest.getHindi();
    }


    public Translation() {

    }

    public String translate(LanguageOption languageOption) {
        return  languageOption== LanguageOption.Hindi? hindi:
                languageOption== LanguageOption.French? french:
                        english;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getHindi() {
        return hindi;
    }

    public void setHindi(String hindi) {
        this.hindi = hindi;
    }

    public String getFrench() {
        return french;
    }

    public void setFrench(String french) {
        this.french = french;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

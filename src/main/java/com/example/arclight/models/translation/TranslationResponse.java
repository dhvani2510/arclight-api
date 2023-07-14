package com.example.arclight.models.translation;

import com.example.arclight.entities.Translation;

public class TranslationResponse
{
    private Long Id;
    private  String English;
    private  String Hindi;
    private  String French;

    public TranslationResponse(Long id, String english, String hindi, String french) {
        Id = id;
        English = english;
        Hindi = hindi;
        French = french;
    }
    public  TranslationResponse(Translation translation){
        Id= translation.getId();
        English=translation.getEnglish();
        Hindi=translation.getHindi();
        French=translation.getFrench();
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getEnglish() {
        return English;
    }

    public void setEnglish(String english) {
        English = english;
    }

    public String getHindi() {
        return Hindi;
    }

    public void setHindi(String hindi) {
        Hindi = hindi;
    }

    public String getFrench() {
        return French;
    }

    public void setFrench(String french) {
        French = french;
    }
}

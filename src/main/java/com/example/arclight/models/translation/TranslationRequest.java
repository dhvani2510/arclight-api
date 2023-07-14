package com.example.arclight.models.translation;

public class TranslationRequest
{
    private  String English;
    private  String Hindi;
    private  String French;

    public TranslationRequest(String english, String hindi, String french) {
        English = english;
        Hindi = hindi;
        French = french;
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

package com.example.arclight.models.files;

import com.example.arclight.shared.helpers.StringHelper;

public class FileVersionResponse
{
    private  Long Id;
    private  String english;
    private  String hindi;
    private  String french;

    public FileVersionResponse(Long id, Long englishId, Long hindiId, Long frenchId) {
        Id = id;
        this.english = StringHelper.GetFileUrl(englishId);
        this.hindi = StringHelper.GetFileUrl(hindiId);
        this.french = StringHelper.GetFileUrl(frenchId);
    }


    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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
}

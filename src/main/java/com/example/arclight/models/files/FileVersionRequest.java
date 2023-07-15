package com.example.arclight.models.files;

import org.springframework.web.multipart.MultipartFile;

public class FileVersionRequest
{
    private MultipartFile english;
    private MultipartFile hindi;
    private MultipartFile french;

    public MultipartFile getEnglish() {
        return english;
    }

    public void setEnglish(MultipartFile english) {
        this.english = english;
    }

    public MultipartFile getHindi() {
        return hindi;
    }

    public void setHindi(MultipartFile hindi) {
        this.hindi = hindi;
    }

    public MultipartFile getFrench() {
        return french;
    }

    public void setFrench(MultipartFile french) {
        this.french = french;
    }
}

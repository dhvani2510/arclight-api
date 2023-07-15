package com.example.arclight.models.users;

import com.example.arclight.entities.datatypes.LanguageOption;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public class ProfileRequest
{
    private  String firstName;
    private  String lastName;
    private MultipartFile image;
    private LocalDate birthDate;
    private LanguageOption secondaryLanguage;

   public ProfileRequest(String firstName, String lastName, MultipartFile image, LocalDate birthDay)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.image = image;
        this.birthDate = birthDay;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public LanguageOption getSecondaryLanguage() {
        return secondaryLanguage;
    }

    public void setSecondaryLanguage(LanguageOption secondaryLanguage) {
        this.secondaryLanguage = secondaryLanguage;
    }
}

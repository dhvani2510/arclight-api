package com.example.arclight.models.users;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public class ProfileRequest
{
    private  String firstName;
    private  String lastName;
    private MultipartFile image; // TODO later, to ignore now, it is not working properly
    private LocalDate birthDay;

    public ProfileRequest(String firstName, String lastName, LocalDate birthDay)
    //public ProfileRequest(String firstName, String lastName, MultipartFile picture, LocalDate birthDay)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        //this.picture = picture;
        this.birthDay = birthDay;
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

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}

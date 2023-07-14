package com.example.arclight.models.users;

import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public class ProfileResponse
{
    private  String firstName;
    private  String lastName;
    private String picture; // TODO later, to ignore now, it is not working properly
    private LocalDate birthDay;

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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public ProfileResponse(String firstName, String lastName, String picture, LocalDate birthDay) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.picture = picture;
        this.birthDay = birthDay;
    }
}

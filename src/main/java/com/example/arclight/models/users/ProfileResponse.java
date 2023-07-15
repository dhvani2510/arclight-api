package com.example.arclight.models.users;

import java.time.LocalDate;

public class ProfileResponse
{
    private  String firstName;
    private  String lastName;
    private String image;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public ProfileResponse(String firstName, String lastName, String image, LocalDate birthDay) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.image = image;
        this.birthDay = birthDay;
    }
}

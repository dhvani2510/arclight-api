package com.example.arclight.models;

import com.example.arclight.shared.helpers.StringHelper;

import java.time.LocalDate;
import java.time.Period;

public class UserModel {
    public Long id;
    public  String firstName;
    public  String lastName;
    public LocalDate birthDay;
    public  String email;

    private String image;

    public Integer age;

    public UserModel(Long id, String name, String surname, LocalDate birthDay, String email, Integer age, Long imageId) {
        this.id = id;
        this.firstName= name;
        this.lastName= surname;
        this.email=email;
        this.birthDay= birthDay;
        this.age = age==null? getAge(birthDay): age;
        this.image= StringHelper.GetFileUrl(imageId);
    }

    private  Integer getAge(LocalDate birthDay){

        if(birthDay==null)
            return null;
        Period age= Period.between(birthDay, LocalDate.now());
        return age.getYears();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

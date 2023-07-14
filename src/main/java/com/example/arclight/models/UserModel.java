package com.example.arclight.models;

import com.example.arclight.entities.User;
import com.example.arclight.entities.datatypes.LanguageOption;

import java.time.LocalDate;
import java.time.Period;

public class UserModel {
    public Long id;
    public  String firstName;
    public  String lastName;
    public LocalDate birthDay;
    public  String email;
    public LanguageOption secondaryLangage;

    //public File Image;

    public Integer age;

    public UserModel(Long id, String name, String surname, LocalDate birthDay, String email, Integer age) {
        this.id = id;
        this.firstName= name;
        this.lastName= surname;
        this.email=email;
        this.birthDay= birthDay;
        this.age = age==null? getAge(birthDay): age;
    }

    public UserModel(User user){
        this.id = user.Id;
        this.firstName= user.firstName;
        this.lastName= user.lastName;
        this.email=user.email;
        if(user.birthDay!=null)
        this.birthDay=user.birthDay;
        this.age = user.getAge()==null? getAge(birthDay): age;
        this.secondaryLangage= user.secondaryLanguage;
    }

    private  Integer getAge(LocalDate birthDay){

        if(birthDay==null)
            return null;
        Period age= Period.between(birthDay, LocalDate.now());
        return age.getYears();
    }
}

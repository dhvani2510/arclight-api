package com.example.arclight.models;

import java.time.LocalDate;
import java.time.Period;

public class UserModel {
    public Long id;
    public  String firstName;
    public  String lastName;
    public LocalDate birthDay;
    public  String email;

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

    private  Integer getAge(LocalDate birthDay){

        if(birthDay==null)
            return null;
        Period age= Period.between(birthDay, LocalDate.now());
        return age.getYears();
    }
}

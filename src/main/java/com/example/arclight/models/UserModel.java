package com.example.arclight.models;

import java.time.LocalDate;

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
        this.age = age;
    }
}

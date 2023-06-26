package com.example.arclight.models;

import java.time.LocalDate;

public class UserModel {
    public Long Id;
    public  String Name;
    public  String Surname;
    public LocalDate BirthDay;
    public  String Email;

    //public File Image;

    public Integer Age;

    public UserModel(Long id, String name, String surname, LocalDate birthDay, String email, Integer age) {
        Id = id;
        Name = name;
        Surname = surname;
        BirthDay = birthDay;
        Email = email;
        Age = age;
    }
}

package com.example.arclight.models;

public class UserContext
{
    private  Long Id;
    private  String Name;
    private  String Surname;
    private  String Email;

    public UserContext(Long id, String name, String surname, String email) {
        Id = id;
        Name = name;
        Surname = surname;
        Email = email;
    }
}

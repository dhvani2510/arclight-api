package com.example.arclight.models.auth;

public class RegisterResponse {
    private  Long Id;
    private  String Name;
    private  String Surname;
    private  String Email;

    public RegisterResponse(Long id, String name, String surname, String email) {
        Id = id;
        Name = name;
        Surname = surname;
        Email = email;
    }

    public Long getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getSurname() {
        return Surname;
    }

    public String getEmail() {
        return Email;
    }
}

package com.example.arclight.models.auth;

public class RegisterRequest {
    private  String Name;
    private  String Surname;
    private  String Email;
    private  String Password;

    public RegisterRequest(String name, String surname, String email, String password) {
        Name = name;
        Surname = surname;
        Email = email;
        Password = password;
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

    public String getPassword() {
        return Password;
    }
}

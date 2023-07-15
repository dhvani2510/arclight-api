package com.example.arclight.models.auth;

public class RegisterResponse {
    private  Long Id;
    private  String firstName;
    private  String lastName;
    private  String Email;

    public RegisterResponse(Long id, String name, String surname, String email) {
        Id = id;
        firstName = name;
        lastName = surname;
        Email = email;
    }

    public Long getId() {
        return Id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return Email;
    }
}

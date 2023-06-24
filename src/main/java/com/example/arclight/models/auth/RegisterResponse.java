package com.example.arclight.models.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class RegisterResponse {
    private  Long Id;
    private  String Name;
    private  String Surname;
    private  String Email;
}

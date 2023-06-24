package com.example.arclight.controllers;

import com.example.arclight.models.auth.AuthenticationRequest;
import com.example.arclight.models.auth.AuthenticationResponse;
import com.example.arclight.models.auth.RegisterRequest;
import com.example.arclight.models.auth.RegisterResponse;
import com.example.arclight.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private  final UserService  userService; //authenticationService

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> Register(@RequestBody RegisterRequest registerRequest){

        var response=userService.register(registerRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> Authentication(@RequestBody AuthenticationRequest authenticationRequest){
        var response=userService.authenticate(authenticationRequest);
        return ResponseEntity.ok(response);
    }
}

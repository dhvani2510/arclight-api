package com.example.arclight.services;

import com.example.arclight.configurations.JwtService;
import com.example.arclight.entities.User;
import com.example.arclight.entities.datatypes.Role;
import com.example.arclight.models.auth.AuthenticationRequest;
import com.example.arclight.models.auth.AuthenticationResponse;
import com.example.arclight.models.auth.RegisterRequest;
import com.example.arclight.models.auth.RegisterResponse;
import com.example.arclight.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@RequiredArgsConstructor
public class UserService {
    private  final UserRepository userRepository;
    private  final JwtService jwtService;
    private  final AuthenticationManager authenticationManager;

    @Autowired
    public  UserService(UserRepository userRepository, JwtService jwtService, AuthenticationManager authenticationManager){
        this.userRepository=userRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }
    public List<User> GetUsers(){

        List<User> users= userRepository.findAll();
        return users;
    }

    public RegisterResponse register(RegisterRequest registerRequest)
    {   // Log
        // Check if auto mapper exist in Java
        //TODO validations
        var user= User.builder()
                .Name(registerRequest.getName())
                .Surname(registerRequest.getSurname())
                .Email(registerRequest.getEmail())
                .Password(registerRequest.getPassword()) // convert this?
                .Role(Role.User)
                .build();

        userRepository.save(user);

        return  RegisterResponse.builder()
                .Id(user.Id)
                .Email(user.Email)
                .Name(user.Name)
                .Surname(user.Surname)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest)
    {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
        authenticationRequest.getEmail(),
              authenticationRequest.getPassword()
      ));

      var user= userRepository.findByEmail(authenticationRequest.getEmail())
              .orElseThrow(); // CustomException

        var jwtToken= jwtService.GenerateToken(null, user);
        return AuthenticationResponse.builder()
                .Token(jwtToken)
                .build();

    }
}

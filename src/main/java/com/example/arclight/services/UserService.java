package com.example.arclight.services;

import com.example.arclight.entities.User;
import com.example.arclight.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private  final UserRepository userRepository;

    @Autowired
    public  UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    public List<User> GetUsers(){

        List<User> users= userRepository.findAll();
        return users;
    }

}

package com.example.arclight.controllers;

import com.example.arclight.entities.User;
import com.example.arclight.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/users")
public class UserController {
    private  final  UserService userService;
    @Autowired
    public  UserController(UserService userService){
        this.userService= userService;
    }

    @GetMapping
    public  List<User> GetUsers(){ // TODO return userModel from Service
        var users=  userService.GetUsers();
        return users;
    }

    @PostMapping
    public  User RegisterUser(@RequestBody User user){ // TODO use UserInput Model

        return new User();
    }
}

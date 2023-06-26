package com.example.arclight.controllers;

import com.example.arclight.entities.User;
import com.example.arclight.models.ResponseModel;
import com.example.arclight.models.UserModel;
import com.example.arclight.services.UserService;
import com.example.arclight.shared.exceptions.ArclightException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ResponseModel> GetUsers()
    {
        var users=  userService.GetUsers();
        return ResponseModel.Ok("Users fetched", users);
    }

    @GetMapping("me")
    public ResponseEntity<ResponseModel> Me()
    {
        try{
            var user= userService.GetUser();
            return ResponseModel.Ok("My user profile fetched", user);
        }
        catch (ArclightException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
            return new ResponseEntity("Error occured", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

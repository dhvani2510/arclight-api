package com.example.arclight.controllers;

import com.example.arclight.entities.User;
import com.example.arclight.models.ResponseModel;
import com.example.arclight.models.UserModel;
import com.example.arclight.models.users.ProfileRequest;
import com.example.arclight.services.UserService;
import com.example.arclight.shared.exceptions.ArclightException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/users")
public class UserController {
    private  final  UserService userService;
    private static final Logger logger= LoggerFactory.getLogger(UserController.class);
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
    public ResponseEntity<ResponseModel> GetMyProfile()
    {
        try{
            var user= userService.GetUser();
            return ResponseModel.Ok("My user profile fetched", user);
        }
        catch (ArclightException e){
            logger.error(e.getMessage(),e);
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
            logger.error(e.getMessage(),e);
            return new ResponseEntity("Error occured", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(
            path = "profile",
            method = RequestMethod.POST
            //, consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
     //@ModelAttribute //@RequestParam("file")MultipartFile file // TODO implement the picture part, upload is complex
    public ResponseEntity<ResponseModel> UpdateProfile(@RequestBody ProfileRequest profileRequest)
    {
        try{
            var user= userService.UpdateProfile(profileRequest);
            return ResponseModel.Ok("Profile updated successfully", user);
        }
        catch (ArclightException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
            return new ResponseEntity("Error occured", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("{id}")
    public ResponseEntity<ResponseModel> GetProfile( @PathVariable Long id) //@RequestParam Long id
    {
        try{
            var user= userService.GetProfile(id);
            return ResponseModel.Ok("User profile fetched", user);
        }
        catch (ArclightException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
            return new ResponseEntity("Error occured", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

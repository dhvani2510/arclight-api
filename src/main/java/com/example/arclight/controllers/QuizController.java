package com.example.arclight.controllers;

import com.example.arclight.models.ResponseModel;
import com.example.arclight.models.quiz.QuizRequest;
import com.example.arclight.models.translation.TranslationRequest;
import com.example.arclight.services.QuizService;
import com.example.arclight.shared.exceptions.ArclightException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="api/v1/quizzes")
public class QuizController
{
    private  final QuizService quizService;
    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }


    @GetMapping
    public ResponseEntity<ResponseModel> Get()
    {
        var quizzes=  quizService.Get();
        return ResponseModel.Ok("Quizzes fetched", quizzes);
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseModel> Get(@PathVariable Long id)
    {
        try{
            var quiz= quizService.Get(id);
            return ResponseModel.Ok("Quiz fetched", quiz);
        }
        catch (ArclightException e){
            return  ResponseModel.Fail(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
            return  ResponseModel.Fail("Error occured", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<ResponseModel> Create(@RequestBody QuizRequest quizRequest)
    {
        try{
            var translation= quizService.Create(quizRequest);
            return ResponseModel.Ok("Quiz created successfully", translation);
        }
        catch (ArclightException e){
            return  ResponseModel.Fail(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
            return  ResponseModel.Fail("Error occured", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<ResponseModel> Update(@RequestBody QuizRequest quizRequest, @PathVariable Long id)
    {
        try{
            var translation= quizService.Update(id,quizRequest);
            return ResponseModel.Ok("Quiz updated successfully", translation);
        }
        catch (ArclightException e){
            return  ResponseModel.Fail(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
            return  ResponseModel.Fail("Error occured", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

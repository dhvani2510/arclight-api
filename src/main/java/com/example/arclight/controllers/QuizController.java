package com.example.arclight.controllers;

import com.example.arclight.models.ResponseModel;
import com.example.arclight.models.quiz.QuizRequest;
import com.example.arclight.models.quiz.AnswerRequest;
import com.example.arclight.models.quiz.QuizSubmitRequest;
import com.example.arclight.services.QuizService;
import com.example.arclight.shared.exceptions.ArclightException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="api/v1/quiz")
public class QuizController
{
    private  final QuizService quizService;
    private static final Logger logger= LoggerFactory.getLogger(QuizController.class);
    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping
    public ResponseEntity<ResponseModel> Get()
    {
        try{
            var quiz= quizService.Get();
            return ResponseModel.Ok("Quiz fetched", quiz);
        }
        catch (ArclightException e){
            logger.error(e.getMessage(),e);
            return  ResponseModel.Fail(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
            logger.error(e.getMessage(),e);
            return  ResponseModel.Fail("Error occured", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("submit")
    public ResponseEntity<ResponseModel> Submit(@RequestBody QuizSubmitRequest quizSubmitRequest)
    {
        try{
            var scoreResponse= quizService.Submit(quizSubmitRequest);
            return ResponseModel.Ok("Quiz submitted successfully", scoreResponse);
        }
        catch (ArclightException e){
            logger.error(e.getMessage(),e);
            return  ResponseModel.Fail(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
            logger.error(e.getMessage(),e);
            return  ResponseModel.Fail("Error occured", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("scores")
    public ResponseEntity<ResponseModel> Score()
    {
        try{
            var scores= quizService.GetScores();
            return ResponseModel.Ok("Scores fetched", scores);
        }
        catch (ArclightException e){
            logger.error(e.getMessage(),e);
            return  ResponseModel.Fail(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
            logger.error(e.getMessage(),e);
            return  ResponseModel.Fail("Error occured", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

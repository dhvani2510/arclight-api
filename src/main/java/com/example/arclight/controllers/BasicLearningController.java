package com.example.arclight.controllers;

import com.example.arclight.entities.datatypes.LanguageOption;
import com.example.arclight.models.ResponseModel;
import com.example.arclight.models.basicLearning.BasicLearningRequest;
import com.example.arclight.models.translation.TranslationRequest;
import com.example.arclight.services.BasicLearningService;
import com.example.arclight.services.TranslationService;
import com.example.arclight.services.UserService;
import com.example.arclight.shared.exceptions.ArclightException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="api/v1/basic-learning")
public class BasicLearningController
{
    private  final BasicLearningService basicLearningService;
    private static final Logger logger= LoggerFactory.getLogger(BasicLearningController.class);
    @Autowired
    public BasicLearningController(BasicLearningService basicLearningService) {
        this.basicLearningService = basicLearningService;
    }

    @GetMapping
    public ResponseEntity<ResponseModel> GetBasicLearnings()
    {
        try{
            var basicLarnings=  basicLearningService.GetAll();
            return ResponseModel.Ok("Basic learnings fetched", basicLarnings);
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

    @GetMapping("{id}")
    public ResponseEntity<ResponseModel> GetBasicLearning(@PathVariable Long id)
    {
        try{
            var translation= basicLearningService.GetTranslation(id);
            return ResponseModel.Ok("Basic learning fetched", translation);
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

    @PostMapping
    public ResponseEntity<ResponseModel> Create(@RequestBody BasicLearningRequest basicLearningRequest)
    {
        try{
            var basicLearning= basicLearningService.Create(basicLearningRequest);
            return ResponseModel.Ok("Basic learning created successfully", basicLearning);
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

//    @PutMapping("{id}")
//    public ResponseEntity<ResponseModel> Update(@RequestBody BasicLearningRequest bais,@PathVariable Long id)
//    {
//        try{
//            var translation= translationService.UpdateTranslation(id,translationRequest);
//            return ResponseModel.Ok("Translation updated successfully", translation);
//        }
//        catch (ArclightException e){
//            return  ResponseModel.Fail(e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//        catch (Exception e){
//            return  ResponseModel.Fail("Error occured", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @DeleteMapping("{id}")
//    public ResponseEntity<ResponseModel> Delete(@PathVariable Long id)
//    {
//        try{
//            translationService.Delete(id);
//            return ResponseModel.Ok("Translation deleted successfully", id);
//        }
//        catch (ArclightException e){
//            return  ResponseModel.Fail(e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//        catch (Exception e){
//            return  ResponseModel.Fail("Error occured", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
}

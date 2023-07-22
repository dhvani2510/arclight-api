package com.example.arclight.controllers;

import com.example.arclight.models.ResponseModel;
import com.example.arclight.models.funfact.FunFactRequest;
import com.example.arclight.services.FunFactService;
import com.example.arclight.shared.exceptions.ArclightException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="api/v1/funfact")
public class FunFactController
{
    private  final FunFactService funFactService;
    private static final Logger logger= LoggerFactory.getLogger(FunFactController.class);
    @Autowired
    public FunFactController(FunFactService funFactService) {
        this.funFactService = funFactService;
    }

    @GetMapping()
    public ResponseEntity<ResponseModel> GetRandom()
    {
        try{
            var funFact=  funFactService.GetRandom();
            return funFact==null? ResponseModel.Ok("No fun fact found"):
                    ResponseModel.Ok("Fun Fact fetched", funFact);
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
    public ResponseEntity<ResponseModel> GetFunFact(@PathVariable Long id)
    {
        try{
            var translation= funFactService.GetById(id);
            return ResponseModel.Ok("Fun Fact fetched", translation);
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
    public ResponseEntity<ResponseModel> Create(@RequestBody FunFactRequest funFactRequest)
    {
        try{
            var funFact= funFactService.Create(funFactRequest);
            return ResponseModel.Ok("Fun fact created successfully", funFact);
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

    @PutMapping("{id}")
    public ResponseEntity<ResponseModel> Update(@RequestBody FunFactRequest funFactRequest,@PathVariable Long id)
    {
        try{
            var translation= funFactService.Update(id,funFactRequest);
            return ResponseModel.Ok("Fun Fact updated successfully", translation);
        }
        catch (ArclightException e){
            return  ResponseModel.Fail(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
            return  ResponseModel.Fail("Error occured", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
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
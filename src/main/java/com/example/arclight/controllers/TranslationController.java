package com.example.arclight.controllers;
import com.example.arclight.models.ResponseModel;
import com.example.arclight.models.translation.TranslationRequest;
import com.example.arclight.services.TranslationService;
import com.example.arclight.shared.exceptions.ArclightException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="api/v1/translations")
public class TranslationController {

    private  final TranslationService translationService;
    @Autowired
    public TranslationController(TranslationService translationService) {
        this.translationService = translationService;
    }

    @GetMapping
    public ResponseEntity<ResponseModel> GetTranslations()
    {
        var translations=  translationService.GetTranslations();
        return ResponseModel.Ok("Translations fetched", translations);
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseModel> GetTranslation(@PathVariable Long id)
    {
        try{
            var translation= translationService.GetTranslation(id);
            return ResponseModel.Ok("Translation fetched", translation);
        }
        catch (ArclightException e){
            return  ResponseModel.Fail(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
            return  ResponseModel.Fail("Error occured", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<ResponseModel> CreateTranslation(@RequestBody TranslationRequest translationRequest)
    {
        try{
            var translation= translationService.CreateTranslation(translationRequest);
            return ResponseModel.Ok("Translation created successfully", translation);
        }
        catch (ArclightException e){
            return  ResponseModel.Fail(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
            return  ResponseModel.Fail("Error occured", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<ResponseModel> UpdateTranslation(@RequestBody TranslationRequest translationRequest,@PathVariable Long id)
    {
        try{
            var translation= translationService.UpdateTranslation(id,translationRequest);
            return ResponseModel.Ok("Translation updated successfully", translation);
        }
        catch (ArclightException e){
            return  ResponseModel.Fail(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
            return  ResponseModel.Fail("Error occured", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseModel> Delete(@PathVariable Long id)
    {
        try{
            translationService.Delete(id);
            return ResponseModel.Ok("Translation deleted successfully", id);
        }
        catch (ArclightException e){
            return  ResponseModel.Fail(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
            return  ResponseModel.Fail("Error occured", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

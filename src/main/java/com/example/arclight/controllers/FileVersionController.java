package com.example.arclight.controllers;

import com.example.arclight.models.ResponseModel;
import com.example.arclight.models.files.FileVersionRequest;
import com.example.arclight.models.users.ProfileRequest;
import com.example.arclight.services.FileService;
import com.example.arclight.shared.exceptions.ArclightException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;

@RestController
@RequestMapping(path="api/v1/file-versions")
public class FileVersionController {

    private  final FileService fileService;
    private static final Logger logger= LoggerFactory.getLogger(FileVersionController.class);
    @Autowired
    public FileVersionController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseModel> Get(@PathVariable Long id) //TO Download replace inline with attachment
    {
        try{
            var fileversion= fileService.GetVersion(id);
            return ResponseModel.Ok("File version fetched", fileversion);
        }
        catch (ArclightException e){
            return  ResponseModel.Fail(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
            return  ResponseModel.Fail("Error occured", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<ResponseModel> Create(@ModelAttribute FileVersionRequest fileVersionRequest)
    {
        try{
            var version= fileService.AddVersion(fileVersionRequest);
            return ResponseModel.Ok("Version created successfully", version);
        }
        catch (ArclightException e){
            logger.error(e.getMessage(),e);
            return ResponseModel.Fail(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
            logger.error(e.getMessage(),e);
            return  ResponseModel.Fail("Error occured", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(
            path = "{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<ResponseModel> Update(@ModelAttribute FileVersionRequest fileVersionRequest, @PathVariable Long id)
    {
        try{
            var version= fileService.UpdateVersion(id,fileVersionRequest);
            return ResponseModel.Ok("Version updated successfully", version);
        }
        catch (ArclightException e){
            logger.error(e.getMessage(),e);
            return ResponseModel.Fail(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
            logger.error(e.getMessage(),e);
            return  ResponseModel.Fail("Error occured", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

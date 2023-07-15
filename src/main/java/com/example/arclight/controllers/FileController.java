package com.example.arclight.controllers;

import com.example.arclight.models.ResponseModel;
import com.example.arclight.services.FileService;
import com.example.arclight.shared.exceptions.ArclightException;
import jdk.jfr.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

@RestController
@RequestMapping(path="api/v1/files")
public class FileController {

    private  final FileService fileService;
    private static final Logger logger= LoggerFactory.getLogger(FileController.class);
    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Resource> Get(@PathVariable Long id) //TO Download replace inline with attachment
    {
        try{

            var file= fileService.Get(id);

            InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(file.getBytes()));

            var contentType=MediaType.parseMediaType(file.getContentType());
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getName() + "\"")
                    .contentType( contentType)
                    //.contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
        }
        catch (Exception e){
            logger.error(e.getMessage(),e);
            return new ResponseEntity("Error occured", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

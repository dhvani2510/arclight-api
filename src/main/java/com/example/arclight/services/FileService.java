package com.example.arclight.services;

import com.example.arclight.entities.File;
import com.example.arclight.models.files.FileResponse;
import com.example.arclight.repositories.FileRepository;
import org.hibernate.boot.archive.spi.ArchiveException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileService
{
    private static final Logger logger= LoggerFactory.getLogger(TranslationService.class);
    private  final FileRepository fileRepository;

    @Autowired
    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public FileResponse Get(Long id){
        logger.info("User is getting file {id}",id);

        var file= fileRepository.findById(id).orElseThrow(()-> new ArchiveException("File not found"));

        return new FileResponse(file);
    }

//    public  FileResponse Upload(MultipartFile multipartFile) throws IOException {
//        logger.info("User is uploading file {}", multipartFile.getName());
//
//       var file= new File(multipartFile.getName(),multipartFile.getContentType(),multipartFile.getBytes(),)
//    }

}

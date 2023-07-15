package com.example.arclight.services;

import com.example.arclight.entities.File;
import com.example.arclight.entities.FileVersion;
import com.example.arclight.models.files.FileResponse;
import com.example.arclight.models.files.FileVersionRequest;
import com.example.arclight.models.files.FileVersionResponse;
import com.example.arclight.repositories.FileRepository;
import com.example.arclight.repositories.FileVersionRepository;
import com.example.arclight.shared.exceptions.ArclightException;
import org.hibernate.boot.archive.spi.ArchiveException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FileService
{
    private static final Logger logger= LoggerFactory.getLogger(TranslationService.class);
    private  final FileRepository fileRepository;
    private  final FileVersionRepository fileVersionRepository ;

    @Autowired
    public FileService(FileRepository fileRepository, FileVersionRepository fileVersionRepository) {
        this.fileRepository = fileRepository;
        this.fileVersionRepository = fileVersionRepository;
    }

    public FileResponse GetData(Long id){
        logger.info("User is getting file {id}",id);

        var file= fileRepository.findById(id).orElseThrow(()-> new ArchiveException("File not found"));

        return new FileResponse(file);
    }

    public File Get(Long id){
        logger.info("User is getting file {}",id);

        return fileRepository.findById(id).orElseThrow(()-> new ArchiveException("File not found"));
    }

    public  FileResponse UploadFile(MultipartFile multipartFile) throws ArclightException, IOException {
        var file =Upload(multipartFile);
        return  new FileResponse(file);
    }
    public File Upload(MultipartFile multipartFile) throws IOException, ArclightException {
        logger.info("User is uploading file {}", multipartFile.getName());

        if(multipartFile==null)
            throw  new ArclightException("File is empty");
        var file= new File(multipartFile.getOriginalFilename(),multipartFile.getContentType(),multipartFile.getBytes(),multipartFile.getSize());
        fileRepository.save(file);
        return  file;
    }


    public FileVersionResponse AddVersion(FileVersionRequest fileVersionRequest) throws ArclightException, IOException {
        logger.info("User is uploading files versions");

        if(fileVersionRequest.getEnglish()==null
                && fileVersionRequest.getHindi()==null && fileVersionRequest.getFrench()==null)
            throw new ArclightException("Files are empty");

        var files =List.of(
                new File(fileVersionRequest.getEnglish().getName(),
                       fileVersionRequest.getEnglish().getContentType(),
                       fileVersionRequest.getEnglish().getBytes(),
                       fileVersionRequest.getEnglish().getSize()
                ),
                new File(fileVersionRequest.getHindi().getName(),
                        fileVersionRequest.getHindi().getContentType(),
                        fileVersionRequest.getHindi().getBytes(),
                        fileVersionRequest.getHindi().getSize()
                ),
                new File(fileVersionRequest.getFrench().getName(),
                        fileVersionRequest.getFrench().getContentType(),
                        fileVersionRequest.getFrench().getBytes(),
                        fileVersionRequest.getFrench().getSize()
                )
        );


        fileRepository.saveAll(files);

        var fileVersion= new FileVersion(files.get(0), files.get(1), files.get(1));
        fileVersionRepository.save(fileVersion);
        return new FileVersionResponse(fileVersion.getId(),
                fileVersion.getEnglish().getId(),
                fileVersion.getHindi().getId(),
                fileVersion.getFrench().getId());
    }
}

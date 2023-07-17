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
        //Get user context?
        var existingFile= fileRepository.findByNameAndContentTypeAndSizeInBytes(multipartFile.getOriginalFilename()
                ,multipartFile.getContentType(), multipartFile.getSize());
        if(existingFile!=null){
            logger.info("File {} already exists", existingFile.getId());
            return existingFile;
        }
        var file= new File(multipartFile.getOriginalFilename(),multipartFile.getContentType(),multipartFile.getBytes(),multipartFile.getSize());
        fileRepository.save(file);
        return  file;
    }


    public  FileVersionResponse GetVersion(Long id) throws ArclightException {
        logger.info("User is getting files versions");
        var fileVersion= fileVersionRepository.findById(id)
                .orElseThrow(()->  new ArclightException("File versions not found"));
        return new FileVersionResponse(fileVersion);
    }
    public FileVersionResponse AddVersion(FileVersionRequest fileVersionRequest) throws ArclightException, IOException {
        logger.info("User is uploading files versions");

        if(fileVersionRequest.getEnglish()==null
                || fileVersionRequest.getHindi()==null || fileVersionRequest.getFrench()==null)
            throw new ArclightException("Files are empty");

        var english= Upload(fileVersionRequest.getEnglish());
        var hindi= Upload(fileVersionRequest.getHindi());
        var french= Upload(fileVersionRequest.getFrench());

        var fileVersion= new FileVersion(english, hindi, french);
        fileVersionRepository.save(fileVersion);
        logger.info("File version added successfully");
        return new FileVersionResponse(fileVersion);
    }

    public FileVersionResponse UpdateVersion(Long id, FileVersionRequest fileVersionRequest) throws ArclightException, IOException {
        logger.info("User is updating files version {}", id);

        if(fileVersionRequest.getEnglish()==null
                && fileVersionRequest.getHindi()==null && fileVersionRequest.getFrench()==null)
            throw new ArclightException("Files are empty");

        var fileVersion= fileVersionRepository.findById(id)
                .orElseThrow(()-> new ArclightException("File version not found"));

        var english= Upload(fileVersionRequest.getEnglish());
        var hindi= Upload(fileVersionRequest.getHindi());
        var french= Upload(fileVersionRequest.getFrench());

         //TODO delete the old files?
        if(english!=null)
            fileRepository.save(english);
        if(hindi!=null)
            fileRepository.save(hindi);
        if(french!=null)
            fileRepository.save(french);

        fileVersion.Update(english, hindi, french);
        fileVersionRepository.save(fileVersion);
        logger.info("File version updated successfully");
        return new FileVersionResponse(fileVersion);
    }
}

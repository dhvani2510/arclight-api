package com.example.arclight.services;

import com.example.arclight.entities.BasicLearning;
import com.example.arclight.entities.User;
import com.example.arclight.entities.datatypes.Category;
import com.example.arclight.entities.datatypes.LanguageOption;
import com.example.arclight.models.basic_learning.BasicLearningRequest;
import com.example.arclight.models.basic_learning.BasicLearningResponse;
import com.example.arclight.repositories.BasicLearningRepository;
import com.example.arclight.repositories.FileRepository;
import com.example.arclight.repositories.TranslationRepository;
import com.example.arclight.shared.exceptions.ArclightException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasicLearningService
{
    private  final BasicLearningRepository basicLearningRepository;
    private  final TranslationRepository translationRepository;

    private  final FileRepository fileRepository;
    private static final Logger logger= LoggerFactory.getLogger(BasicLearningService.class);

    @Autowired
    public  BasicLearningService(BasicLearningRepository basicLearningRepository, TranslationRepository translationRepository, FileRepository fileRepository)
    {
        this.basicLearningRepository = basicLearningRepository;
        this.translationRepository = translationRepository;
        this.fileRepository = fileRepository;
    }

    public List<BasicLearningResponse> GetBycategory(Category category) throws ArclightException {
        var languageOption= GetSecondaryLangauage();
        List<BasicLearning> basicLearnings= basicLearningRepository.findByCategory(category);
        return basicLearnings.stream()
                .map(u -> new BasicLearningResponse(u,languageOption))
                .toList();
    }


    public BasicLearningResponse GetById(Long id) throws ArclightException {
        logger.info("Getting basic learning {}", id);
        var basicLearning= basicLearningRepository.findById(id)
                .orElseThrow(()-> new ArclightException("Basic learning not found"));
        var languageOption= GetSecondaryLangauage();
        return  new BasicLearningResponse(basicLearning, languageOption);
    }

    public BasicLearningResponse Create(BasicLearningRequest basicLearningRequest) throws ArclightException {
        logger.info("User adding basic learning with titleId {}, descriotion id {}, category {}"
                , basicLearningRequest.getTitleId(),basicLearningRequest.getDescriptionId(),
                basicLearningRequest.getCategory());

        ValidateBasicLearningRequest(basicLearningRequest);
        var title= translationRepository.findById(basicLearningRequest.getTitleId())
                .orElseThrow(()->new ArclightException("Title not found") );

        var description= translationRepository.findById(basicLearningRequest.getDescriptionId())
                .orElseThrow(()-> new ArclightException("Description not found"));

        var basicLearning=basicLearningRepository.findByTitle_IdAndDescription_IdAndCategory(
                basicLearningRequest.getTitleId(),basicLearningRequest.getDescriptionId(),
                basicLearningRequest.getCategory());

        if(basicLearning!=null){
            logger.error("Basic Learning already exists");
            throw  new ArclightException("Basic learning already exists");
        }

        var image= fileRepository.findById(basicLearningRequest.getImageId())
                .orElseThrow(()->new ArclightException("File not found"));

        basicLearning= new BasicLearning(title, description, basicLearningRequest.getCategory(), image);

        basicLearningRepository.save(basicLearning);
        var languageOption= GetSecondaryLangauage();
        return  new BasicLearningResponse(basicLearning, languageOption);
    }

    public BasicLearningResponse Update(Long id,BasicLearningRequest basicLearningRequest) throws ArclightException {
        logger.info("User is updating basic learning {} with titleId {}, descriotion id {}, category {}"
               ,id , basicLearningRequest.getTitleId(),basicLearningRequest.getDescriptionId(),
                basicLearningRequest.getCategory());

        ValidateBasicLearningRequest(basicLearningRequest);
        var title= translationRepository.findById(basicLearningRequest.getTitleId())
                .orElseThrow(()-> new ArclightException("Title not found"));

        var description= translationRepository.findById(basicLearningRequest.getDescriptionId())
                .orElseThrow(()->new ArclightException("Description not found") );

        var basicLearning=basicLearningRepository.findByTitle_IdAndDescription_IdAndCategory(
                basicLearningRequest.getTitleId(),basicLearningRequest.getDescriptionId(),
                basicLearningRequest.getCategory());

        if(basicLearning!=null && basicLearning.getId()!= id){
            logger.error("Basic Learning already exists");
            throw  new ArclightException("Basic learning already exists");
        }

        var updatedBasicLearning= basicLearningRepository.findById(id)
                .orElseThrow(()-> new ArclightException("Basic learning not found"));

        var image= fileRepository.findById(basicLearningRequest.getImageId())
                .orElseThrow(()->new ArclightException("File not found"));

        updatedBasicLearning.update(title,description,basicLearningRequest.getCategory(),image);

        basicLearningRepository.save(updatedBasicLearning);
        var languageOption= GetSecondaryLangauage();
        logger.info("Basic learning updated successfully");
        return new BasicLearningResponse(updatedBasicLearning, languageOption);
    }
    private LanguageOption GetSecondaryLangauage() throws ArclightException {

        var auth= SecurityContextHolder.getContext().getAuthentication();
        if(!auth.isAuthenticated()){ // toggle this
            var details= auth.getDetails();
            logger.error("User {} is not authenticated", details);
            throw  new ArclightException("user is not authenticated");
        }

        var u= (User)auth.getPrincipal();
        return u.getSecondaryLanguage();
    }
    private  void ValidateBasicLearningRequest(BasicLearningRequest basicLearningRequest) throws ArclightException {
        if(basicLearningRequest.getCategory()==null)
            throw  new ArclightException("Category is empty");
        if(basicLearningRequest.getTitleId()==null)
            throw  new ArclightException("Title id is empty");
        if(basicLearningRequest.getDescriptionId()==null)
            throw new ArclightException("Description id  is empty");
    }

}
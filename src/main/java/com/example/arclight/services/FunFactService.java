package com.example.arclight.services;

import com.example.arclight.entities.FunFact;
import com.example.arclight.entities.User;
import com.example.arclight.entities.datatypes.Category;
import com.example.arclight.entities.datatypes.LanguageOption;
import com.example.arclight.models.funfact.FunFactRequest;
import com.example.arclight.models.funfact.FunFactResponse;
import com.example.arclight.repositories.FunFactRepository;
import com.example.arclight.repositories.FileVersionRepository;
import com.example.arclight.repositories.TranslationRepository;
import com.example.arclight.shared.exceptions.ArclightException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FunFactService
{
    private  final FunFactRepository funFactRepository;
    private  final TranslationRepository translationRepository;

    private  final FileVersionRepository fileVersionRepository;
    private static final Logger logger= LoggerFactory.getLogger(FunFactService.class);

    @Autowired
    public  FunFactService(FunFactRepository funFactRepository, TranslationRepository translationRepository, FileVersionRepository fileVersionRepository)
    {
        this.funFactRepository = funFactRepository;
        this.translationRepository = translationRepository;
        this.fileVersionRepository = fileVersionRepository;
    }

    public List<FunFactResponse> GetBycategory(Category category) throws ArclightException {
        var languageOption= GetSecondaryLangauage();
        List<FunFact> FunFacts= funFactRepository.findByCategory(category);
        List<FunFactResponse> result = FunFacts.stream()
                .map(u -> new FunFactResponse(u,languageOption))
                .toList();
        return result;
    }


    public FunFactResponse GetById(Long id) throws ArclightException {
        logger.info("Getting Fun fact {}", id);
        var FunFact= funFactRepository.findById(id)
                .orElseThrow(()-> new ArclightException("Fun Fact not found"));
        var languageOption= GetSecondaryLangauage();
        return  new FunFactResponse(FunFact, languageOption);
    }

    public FunFactResponse Create(FunFactRequest FunFactRequest) throws ArclightException {
        logger.info("User adding fun fact with titleId {}, descriotion id {}, category {}"
                , FunFactRequest.getTitleId(),FunFactRequest.getDescriptionId(),
                FunFactRequest.getCategory());

        ValidateFunFactRequest(FunFactRequest);
        var title= translationRepository.getById(FunFactRequest.getTitleId());
        if(title==null)
            throw  new ArclightException("Title not found");

        var description= translationRepository.getById(FunFactRequest.getDescriptionId());
        if(description==null)
            throw  new ArclightException("Description not found");

        var FunFact=funFactRepository.findByTitle_IdAndDescription_IdAndCategory(
                FunFactRequest.getTitleId(),FunFactRequest.getDescriptionId(),
                FunFactRequest.getCategory());

        if(FunFact!=null){
            logger.error("Fun Fact already exists");
            throw  new ArclightException("Fun Fact already exists");
        }

        var fileVersion= fileVersionRepository.findById(FunFactRequest.getImageVersionId())
                .orElseThrow(()->new ArclightException("File version not found"));
        //TODO add the file version implementation
        FunFact= new FunFact(title, description, FunFactRequest.getCategory(), fileVersion);

        funFactRepository.save(FunFact);
        var languageOption= GetSecondaryLangauage();
        return  new FunFactResponse(FunFact, languageOption);
    }

    public FunFactResponse Update(Long id,FunFactRequest FunFactRequest) throws ArclightException {
        logger.info("User is updating Fun Fact {} with titleId {}, descriotion id {}, category {}"
               ,id , FunFactRequest.getTitleId(),FunFactRequest.getDescriptionId(),
                FunFactRequest.getCategory());

        ValidateFunFactRequest(FunFactRequest);
        var title= translationRepository.getById(FunFactRequest.getTitleId());
        if(title==null)
            throw  new ArclightException("Title not found");

        var description= translationRepository.getById(FunFactRequest.getDescriptionId());
        if(description==null)
            throw  new ArclightException("Description not found");

        var FunFact=funFactRepository.findByTitle_IdAndDescription_IdAndCategory(
                FunFactRequest.getTitleId(),FunFactRequest.getDescriptionId(),
                FunFactRequest.getCategory());

        if(FunFact!=null){
            logger.error("Fun Fact Learning already exists");
            throw  new ArclightException("Fun Fact already exists");
        }

        var updatedFunFact= funFactRepository.findById(id)
                .orElseThrow(()-> new ArclightException("Fun Fact not found"));

        //TODO add the file version implementation
        updatedFunFact.update(title,description,FunFactRequest.getCategory(),null);

        funFactRepository.save(updatedFunFact);
        var languageOption= GetSecondaryLangauage();
        logger.info("Fun Fact updated successfully");
        return new FunFactResponse(updatedFunFact, languageOption);
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
    private  void ValidateFunFactRequest(FunFactRequest FunFactRequest) throws ArclightException {
        if(FunFactRequest.getCategory()==null)
            throw  new ArclightException("Category is empty");
        if(FunFactRequest.getTitleId()==null)
            throw  new ArclightException("Title id is empty");
        if(FunFactRequest.getDescriptionId()==null)
            throw new ArclightException("Description id  is empty");
    }

}
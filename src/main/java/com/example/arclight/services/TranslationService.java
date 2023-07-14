package com.example.arclight.services;

import com.example.arclight.entities.Translation;
import com.example.arclight.entities.User;
import com.example.arclight.models.UserModel;
import com.example.arclight.models.translation.TranslationRequest;
import com.example.arclight.models.translation.TranslationResponse;
import com.example.arclight.repositories.TranslationRepository;
import com.example.arclight.shared.exceptions.ArclightException;
import com.example.arclight.shared.helpers.StringHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TranslationService
{
    private  final TranslationRepository translationRepository;

    private static final Logger logger= LoggerFactory.getLogger(TranslationService.class);
    @Autowired
    public  TranslationService(TranslationRepository translationRepository){

        this.translationRepository = translationRepository;
    }

    public List<TranslationResponse> GetTranslations(){
        List<Translation> translations= translationRepository.findAll();
        List<TranslationResponse> result = translations.stream()
                .map(u -> new TranslationResponse(u))
                .toList();
        return result;
    }

    public  TranslationResponse GetTranslation(Long id) throws ArclightException {
        logger.info("Getting translation {}", id);
        var translation= translationRepository.findById(id)
                .orElseThrow(()-> new ArclightException("Translation not found"));
        return  new TranslationResponse(translation);
    }

    public  TranslationResponse CreateTranslation(TranslationRequest translationRequest) throws ArclightException {
        logger.info("User adding translation with english {}, hindi {}, french {}"
                , translationRequest.getEnglish(),translationRequest.getHindi(), translationRequest.getFrench());

        ValidateTranslationRequest(translationRequest);

        var translation= translationRepository.findByEnglishAndHindiAndFrench(translationRequest.getEnglish(),translationRequest.getHindi(),translationRequest.getFrench());
        if(translation!=null){
            logger.error("Translation already exists");
            throw  new ArclightException("Translation already exists");
        }

        translation= new Translation(translationRequest.getEnglish(),translationRequest.getHindi(),translationRequest.getFrench());

        translationRepository.save(translation);
        return  new TranslationResponse(translation);
    }

    public  TranslationResponse UpdateTranslation(Long id,TranslationRequest translationRequest) throws ArclightException {
        logger.info("User updating translation {} with english {}, hindi {}, french {}"
            ,id, translationRequest.getEnglish(),translationRequest.getHindi(), translationRequest.getFrench());

        ValidateTranslationRequest(translationRequest);

        var translation= translationRepository.findById(id)
                .orElseThrow(()-> new ArclightException("Translation not find"));

        translation.Set(translationRequest);
        translationRepository.save(translation);
        return new TranslationResponse(translation);
    }

    public  void Delete(Long id) throws ArclightException {
        logger.info("User deleting translation {}",id);
        var translation= translationRepository.findById(id)
                .orElseThrow(()-> new ArclightException("Translation not find"));
        //translation.setDeletedAt(LocalDateTime.now()) ; translationRepository.save(translation);
        translationRepository.delete(translation);
        logger.info("Translation deleted successfully");
    }

    private  void ValidateTranslationRequest(TranslationRequest translationRequest) throws ArclightException {
        if(StringHelper.StringIsNullOrEmpty(translationRequest.getEnglish()))
            throw  new ArclightException("English is empty");
        if(StringHelper.StringIsNullOrEmpty(translationRequest.getHindi()))
            throw  new ArclightException("Hindi is empty");
        if(StringHelper.StringIsNullOrEmpty(translationRequest.getFrench()))
            throw new ArclightException("French is empty");
    }
}

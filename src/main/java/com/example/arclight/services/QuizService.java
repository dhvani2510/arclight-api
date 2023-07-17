package com.example.arclight.services;

import com.example.arclight.entities.Quiz;
import com.example.arclight.models.quiz.QuizRequest;
import com.example.arclight.models.quiz.QuizResponse;
import com.example.arclight.models.translation.TranslationResponse;
import com.example.arclight.repositories.QuizRepository;
import com.example.arclight.shared.exceptions.ArclightException;
import com.example.arclight.shared.helpers.StringHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService
{
    private  final QuizRepository quizRepository;

    private static final Logger logger= LoggerFactory.getLogger(QuizRepository.class);

    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public List<QuizResponse> Get()
    {
        logger.info("User is getting all quizzes");

        var quizzes= quizRepository.findAll();

        List<QuizResponse> result = quizzes.stream()
                .map(u -> new QuizResponse(u))
                .toList();
        return result;
    }

    public QuizResponse Get(Long id) throws ArclightException {
        logger.info("User is getting quiz {}",id);

        var quiz= quizRepository.findById(id)
                .orElseThrow(()-> new ArclightException("Quiz not found"));

        return new QuizResponse(quiz);
    }
    public QuizResponse Create(QuizRequest quizRequest) throws ArclightException {
        logger.info("User is creating a quiz with title {} and category {}", quizRequest.getTitle(), quizRequest.getCategory());

        QuizRequestValidation(quizRequest);

        var quiz= new Quiz(quizRequest);
        quizRepository.save(quiz);
       logger.info("Quiz {} created successfully", quiz.getId());
        return new QuizResponse(quiz);
    }

    public QuizResponse Update(Long id, QuizRequest quizRequest) throws ArclightException {
        logger.info("User is updating quiz {} with title {} and category {}"
                , id, quizRequest.getTitle(), quizRequest.getCategory());

        QuizRequestValidation(quizRequest);

        var quiz= quizRepository.findById(id)
                .orElseThrow(()-> new ArclightException("Quiz not found"));

        quiz.Update(quizRequest);
        quizRepository.save(quiz);
        logger.info("Quiz {} updated successfully", quiz.getId());
        return new QuizResponse(quiz);
    }

    private  void QuizRequestValidation(QuizRequest quizRequest) throws ArclightException {
        if(StringHelper.StringIsNullOrEmpty(quizRequest.getTitle()))
            throw  new ArclightException("Title is empty");
        if(StringHelper.StringIsNullOrEmpty(quizRequest.getDescription()))
            throw  new ArclightException("Description is empty");
        if(quizRequest.getCategory()==null)
            throw new ArclightException("Category is empty");
        if(quizRequest.getDurationInMinutes()==0)
            throw  new ArclightException("Duration cannot be zero");
    }
}

package com.example.arclight.services;

import com.example.arclight.entities.Score;
import com.example.arclight.models.quiz.*;
import com.example.arclight.repositories.BasicLearningRepository;
import com.example.arclight.repositories.ScoreRepository;
import com.example.arclight.shared.exceptions.ArclightException;
import com.example.arclight.shared.helpers.StringHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class QuizService
{
    private  final BasicLearningRepository basicLearningRepository;
    private  final ScoreRepository scoreRepository;
    private  final  BasicLearningService basicLearningService;
    private  final  UserService userService;

    private static final Logger logger= LoggerFactory.getLogger(QuizService.class);

    public QuizService(BasicLearningService basicLearningService, BasicLearningRepository basicLearningRepository, ScoreRepository scoreRepository, UserService userService) {
        this.basicLearningRepository = basicLearningRepository;
        this.basicLearningService = basicLearningService;
        this.scoreRepository = scoreRepository;
        this.userService = userService;
    }


    public List<DescriptionToImageQuestion> Get() throws ArclightException {
        logger.info("User is getting quiz");

        var questions= GenerateDescriptionToImageMatchQuestions();
        logger.info("{} questions generated successfully", questions.stream().count());
        return questions;
    }


    public  ScoreResponse Submit(QuizSubmitRequest quizSubmitRequest) throws ArclightException {
        logger.info("User is submitting quiz");

        logger.info("Computing the score");

        var answers =quizSubmitRequest.getAnswers();

        int total=answers.size();
        int mark=0;
        for(AnswerRequest answerRequest: answers){
            var basicLearning= basicLearningRepository.findById(answerRequest.getId());

            if(basicLearning!=null && basicLearning.get().getTitle().getId()== answerRequest.getChoiceId())
            {
                mark++;
            }
        }

        var user= userService.GetUserInstance();

        var score= new Score(user,mark,total);

        scoreRepository.save(score);

        return new ScoreResponse(score);
    }

    public  List<ScoreResponse> GetScores() throws ArclightException {
      logger.info("User is getting scores");
      var user= userService.GetUserContext();
      var scores= scoreRepository.findByStudent_Id(user.id);

        return scores.stream().map(s-> new ScoreResponse(s)).toList();
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

    private  List<DescriptionToImageQuestion> GenerateDescriptionToImageMatchQuestions() throws ArclightException {

          logger.info("Generating the quiz");

        List<DescriptionToImageQuestion> questions = new ArrayList<>();
            var basicLearnings= basicLearningRepository.findRandom10Rows();
            var count=basicLearnings.stream().count();
        if(count<2)
            return questions;

        for(var basicLearning: basicLearnings){
               var description= basicLearning.getDescription().getEnglish();
               if(!StringHelper.StringIsNullOrEmpty(description)){

                    var shuffleList= basicLearningRepository.findByCategory(basicLearning.getCategory());
                   // Shuffle the list
                   Collections.shuffle(shuffleList);

                   // Get the first four elements
                   var randomElements = shuffleList.stream().count()<4?
                           shuffleList.subList(0,(int)count):
                           shuffleList.subList(0, 4);

                   var choicesI = randomElements.stream()
                           .map(u-> new AnswerChoice(u.getId(),u.getImage().getUrl())).toList();
                   var choices= new ArrayList<>(choicesI);
                   var answer=new AnswerChoice(basicLearning.getId(),basicLearning.getImage().getUrl());
                   if(!containsChoiceWithId(choices, answer.getId())){
                       choices.remove(0);
                       choices.add(answer);
                       Collections.shuffle(choices);
                   }

                   var question= new DescriptionToImageQuestion(basicLearning.getId(),description,choices);
                   questions.add(question);
               }

            }
            return questions;
    }

    public static boolean containsChoiceWithId(List<AnswerChoice> list, Long id) {
        for (AnswerChoice choice : list) {
            if (choice.getId() == id) {
                return true;
            }
        }
        return false;
    }
}

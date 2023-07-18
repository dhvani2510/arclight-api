package com.example.arclight.services;

import com.example.arclight.entities.Quiz;
import com.example.arclight.entities.Score;
import com.example.arclight.entities.datatypes.Category;
import com.example.arclight.entities.datatypes.QuestionType;
import com.example.arclight.models.quiz.*;
import com.example.arclight.repositories.BasicLearningRepository;
import com.example.arclight.repositories.QuizRepository;
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
    private  final QuizRepository quizRepository;
    private  final BasicLearningRepository basicLearningRepository;
    private  final ScoreRepository scoreRepository;
    private  final  BasicLearningService basicLearningService;
    private  final  UserService userService;

    private static final Logger logger= LoggerFactory.getLogger(QuizRepository.class);

    public QuizService(QuizRepository quizRepository, BasicLearningService basicLearningService, BasicLearningRepository basicLearningRepository, ScoreRepository scoreRepository, UserService userService) {
        this.quizRepository = quizRepository;
        this.basicLearningRepository = basicLearningRepository;
        this.basicLearningService = basicLearningService;
        this.scoreRepository = scoreRepository;
        this.userService = userService;
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
        var questions= GenerateImageToTitleMatchQuestions(quiz.getCategory());
        logger.info("{} questions generated successfully", questions.stream().count());
        return new QuizResponse(quiz,questions);
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

    public  ScoreResponse Submit(Long id, QuizSubmitRequest quizSubmitRequest) throws ArclightException {
        logger.info("User is submitting quiz");

        logger.info("Computing the score");

        var answers =quizSubmitRequest.getAnswers();

        int total=answers.size();
        int mark=0;
        for(AnswerRequest answerRequest: answers){

            if(answerRequest.getType()==QuestionType.BasicLearning){
                var basicLearning= basicLearningRepository.findById(answerRequest.getId());

                if(basicLearning!=null && basicLearning.get().getTitle().getId()== answerRequest.getChoiceId())
                {
                    mark++;
                }
            }
        }

        var quiz= quizRepository.findById(id)
                .orElseThrow(()-> new ArclightException("Quiz not found"));
        var user= userService.GetUserInstance();

        var score= new Score(quiz,user,mark,total);

        scoreRepository.save(score);

        return new ScoreResponse(score);
    }

    public  List<ScoreResponse> GetScores() throws ArclightException {
      logger.info("User is getting scores");
      var user= userService.GetUserContext();
      var scores= scoreRepository.findByStudent_Id(user.id);

      var result= scores.stream().map(s-> new ScoreResponse(s)).toList();
      return result;
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

    private  List<ImageToTitleQuestion> GenerateImageToTitleMatchQuestions(Category category) throws ArclightException {

          logger.info("Generating the quiz");

        List<ImageToTitleQuestion> questions = new ArrayList<>();
            var basicLearnings= basicLearningService.GetBycategory(category);
            var count=basicLearnings.stream().count();
        if(count<2)
            return questions;

             var shuffleList= new ArrayList<>(basicLearnings); //basicLearnings.stream().col;
        //List<BasicLearning> myList = new ArrayList<>(basicLearnings);


        for(var basicLearning: basicLearnings){
               var image= basicLearning.getImage();
               if(!StringHelper.StringIsNullOrEmpty(image)){


                   // Shuffle the list
                   Collections.shuffle(shuffleList);

                   // Get the first four elements
                   var randomElements = count<4?
                           shuffleList.subList(0,(int)count):
                           shuffleList.subList(0, 4);

                   var choicesI = randomElements.stream()
                           .map(u-> new AnswerChoice(u.getId(),u.getTitle())).toList();
                   var choices= new ArrayList<>(choicesI);
                   var answer=new AnswerChoice(basicLearning.getId(),basicLearning.getTitle());
                   if(!containsChoiceWithId(choices, answer.getId())){
                       choices.add(answer);
                       Collections.shuffle(choices);
                   }

                   var question= new ImageToTitleQuestion(basicLearning.getId()
                           , QuestionType.BasicLearning,image,choices);
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

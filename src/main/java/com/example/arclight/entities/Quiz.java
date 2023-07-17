package com.example.arclight.entities;
import com.example.arclight.entities.datatypes.Category;
import com.example.arclight.models.quiz.QuizRequest;
import jakarta.persistence.*;

@Entity
@Table
public class Quiz extends  BaseEntity
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String title;

    private  String description;
    @Enumerated(EnumType.STRING)
    private Category category;

    //public List<Question> Questions;

    private  int durationInMinutes;

    public  Quiz(){}

    public void Update(QuizRequest quizRequest) {
        this.title = quizRequest.getTitle();
        this.description = quizRequest.getDescription();
        this.category = quizRequest.getCategory();
        this.durationInMinutes = quizRequest.getDurationInMinutes();
    }
    public Quiz(QuizRequest quizRequest) {
        this.title = quizRequest.getTitle();
        this.description = quizRequest.getDescription();
        this.category = quizRequest.getCategory();
        this.durationInMinutes = quizRequest.getDurationInMinutes();
    }
    public Quiz(Long id, String title, String description, Category category, int durationInMinutes) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.durationInMinutes = durationInMinutes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }
}

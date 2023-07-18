package com.example.arclight.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;

@Entity
@Table
public class Score extends  BaseEntity
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long Id;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(unique = false)
    private  Quiz quiz;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(unique = false)
    private User student;
    private double mark;
    private double total;

    public  Score(){}

    public Score(Quiz quiz, User student, double mark, double total) {
        this.quiz = quiz;
        this.student = student;
        this.mark = mark;
        this.total = total;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}

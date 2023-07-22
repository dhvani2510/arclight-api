package com.example.arclight.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table
public class Score extends  BaseEntity
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long Id;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(unique = false)
    private User student;
    private double mark;
    private double total;

    public  Score(){}

    public Score(User student, double mark, double total) {
        this.student = student;
        this.mark = mark;
        this.total = total;
        this.setCreatedAt(LocalDateTime.now());
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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

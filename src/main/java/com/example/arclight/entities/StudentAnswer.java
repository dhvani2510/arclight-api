package com.example.arclight.entities;

import jakarta.persistence.*;

@Entity
@Table
public class StudentAnswer extends  BaseEntity
{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    public Long Id;

    @OneToOne(cascade = CascadeType.ALL)
    public User Student;
    @OneToOne(cascade = CascadeType.ALL)
    public  Answer Answer; // for multiple choice
    //public  string Answer;// Simple string answer
}

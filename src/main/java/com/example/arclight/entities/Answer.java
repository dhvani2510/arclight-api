package com.example.arclight.entities;

import jakarta.persistence.*;

@Entity
@Table
public class Answer extends  BaseEntity
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Long Id;
    @OneToOne(cascade = CascadeType.PERSIST)
    public  Question Question; // QuestionId

    @OneToOne(cascade = CascadeType.PERSIST)
    public  Translation Solution;// Result
}

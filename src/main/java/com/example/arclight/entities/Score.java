package com.example.arclight.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;

@Entity
@Table
public class Score  extends  BaseEntity
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Long Id;

    @OneToOne(cascade = CascadeType.PERSIST)
    public  Quiz Quiz;
    @OneToOne(cascade = CascadeType.PERSIST)
    public  User Student;
    public  double Grade;
}

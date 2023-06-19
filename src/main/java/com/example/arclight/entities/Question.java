package com.example.arclight.entities;

import jakarta.persistence.*;

@Entity
@Table
public class Question extends  BaseEntity
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Long Id;
    @OneToOne(cascade = CascadeType.PERSIST)
    public  Translation Question;

    @OneToOne(cascade = CascadeType.PERSIST)
    public  Quiz Quiz;
    public  double Mark; // might be percentage

    @OneToOne(cascade = CascadeType.PERSIST)
    public  FileVersion Image;
    @OneToOne(cascade = CascadeType.PERSIST)
    public  FileVersion Audio;
}

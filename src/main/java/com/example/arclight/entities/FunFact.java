package com.example.arclight.entities;

import com.example.arclight.entities.datatypes.Category;

import jakarta.persistence.*;

@Entity
@Table
public class FunFact  extends BaseEntity
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Long Id;
    @OneToOne(cascade = CascadeType.PERSIST)
    public  Translation Description;
    public Category Category;
    @OneToOne(cascade = CascadeType.PERSIST)
    public  FileVersion Image;
    @OneToOne(cascade = CascadeType.PERSIST)
    public  FileVersion Audio;
}



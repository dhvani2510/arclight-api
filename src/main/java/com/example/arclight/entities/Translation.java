package com.example.arclight.entities;

import jakarta.persistence.*;

@Entity
@Table
public class Translation extends  BaseEntity
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Long Id;
    public  String English;
    public  String Hindi;
    public  String French;
}

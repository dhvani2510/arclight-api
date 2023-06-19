package com.example.arclight.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Set;

@Entity
@Table
public class User extends  BaseEntity
{ //Student
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Long Id;
    public  String Name;
    public  String Surname;
    public LocalDate BirthDay;
    public  String Email;

    public  String Password; // Hashed

    @OneToOne(cascade = CascadeType.PERSIST)
    public  File Image;

    @OneToOne(cascade = CascadeType.PERSIST)
    public  Language SecondaryLanguage;
    @Transient
    public Integer Age;

//    @ManyToMany(mappedBy = "UserLanguages")
//    Set<Language> Languages;

    public  User(String name, String surname, String email, LocalDate birthDay){

        this.Name= name;
        this.Surname= surname;
        this.Email=email;
        this.BirthDay= birthDay;
        this.Age=getAge();
        this.CreatedAt= LocalDateTime.now();
    }
//    public  User(String name, String surname, String email, LocalDate birthDay){
//
//        this.Name= name;
//        this.Surname= surname;
//        this.Email=email;
//        this.BirthDay= birthDay;
//        this.Age=getAge();
//    }

    public User() {
    }

    public Integer getAge(){
        return Period.between(this.BirthDay,LocalDate.now()).getYears();
    }
}

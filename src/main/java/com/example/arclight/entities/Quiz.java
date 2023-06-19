package com.example.arclight.entities;
import com.example.arclight.entities.datatypes.Category;
import jakarta.persistence.*;

@Entity
@Table
public class Quiz extends  BaseEntity
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Long Id;
    @OneToOne(cascade = CascadeType.PERSIST)
    public Translation Title;
    @OneToOne(cascade = CascadeType.PERSIST)
    public  Translation Description;
    public Category Category;

    //public List<Question> Questions;
    public  double Total; //Score
}

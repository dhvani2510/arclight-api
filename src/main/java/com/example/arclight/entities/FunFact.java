package com.example.arclight.entities;

import com.example.arclight.entities.datatypes.Category;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table
public class FunFact extends BaseEntity
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Long Id;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Translation title;
    @OneToOne(cascade = CascadeType.PERSIST)
    public  Translation description;
    @OneToOne(cascade = CascadeType.PERSIST, optional = true)
    @JoinColumn(unique = false)
    private File image;
  
    public  FunFact(){}
    public FunFact(Translation title, Translation description, File image) {
    this.title = title;
    this.description = description;
    this.image = image;
    this.setCreatedAt(LocalDateTime.now());
   }

    public void  update(Translation title, Translation description, File image) {
        this.title = title;
        this.description = description;
        this.image = image;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Translation getTitle() {
        return title;
    }

    public void setTitle(Translation title) {
        this.title = title;
    }

    public Translation getDescription() {
        return description;
    }

    public void setDescription(Translation description) {
        this.description = description;
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }
}



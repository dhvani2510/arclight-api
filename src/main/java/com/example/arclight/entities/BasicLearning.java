package com.example.arclight.entities;

import com.example.arclight.entities.datatypes.Category;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table
public class BasicLearning extends  BaseEntity
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long Id;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Translation title;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Translation description;
    @Enumerated(EnumType.STRING)
    private Category category;
    @ManyToOne(cascade = CascadeType.PERSIST, optional = true)
    @JoinColumn(unique = false)
    private File image;

    public  BasicLearning(){}
    public BasicLearning(Translation title, Translation description, Category category, File image) {
    this.title = title;
    this.description = description;
    this.category = category;
    this.image = image;
    this.setCreatedAt(LocalDateTime.now());
   }

    public void  update(Translation title, Translation description, Category category, File image) {
        this.title = title;
        this.description = description;
        this.category = category;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }
}

package com.example.arclight.entities;

import com.example.arclight.entities.datatypes.Category;
import com.example.arclight.entities.datatypes.LanguageOption;
import com.example.arclight.models.basicLearning.BasicLearningRequest;
import jakarta.persistence.*;

@Entity
@Table
public class BasicLearning extends  BaseEntity
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long Id;

    @OneToOne(cascade = CascadeType.PERSIST)
    private  Translation title;
    @OneToOne(cascade = CascadeType.PERSIST)
    private  Translation description;
    @Enumerated(EnumType.STRING)
    private Category category;
    @OneToOne(cascade = CascadeType.PERSIST)
    private   FileVersion image;

    public BasicLearning(Translation title, Translation description, Category category, FileVersion image) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.image = image;
    }

    public BasicLearning() {

    }

    public Long getTitleId() {
        return this.title.getId();
    }

    public Long getDescriptionId() {
        return this.description.getId();
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

    public FileVersion getImage() {
        return image;
    }

    public void setImage(FileVersion image) {
        this.image = image;
    }
}

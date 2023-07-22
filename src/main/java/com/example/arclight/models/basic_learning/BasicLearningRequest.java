package com.example.arclight.models.basic_learning;

import com.example.arclight.entities.datatypes.Category;

public class BasicLearningRequest
{
    private Long titleId;
    private Long descriptionId;

    private Long imageId;// TODO to be implemeneted later
    private Category category;

    public Long getTitleId() {
        return titleId;
    }

    public void setTitleId(Long titleId) {
        this.titleId = titleId;
    }

    public Long getDescriptionId() {
        return descriptionId;
    }

    public void setDescriptionId(Long descriptionId) {
        this.descriptionId = descriptionId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }
}
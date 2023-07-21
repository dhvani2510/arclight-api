package com.example.arclight.models.funfact;

import com.example.arclight.entities.datatypes.Category;

public class FunFactRequest
{
    private Long titleId;
    private Long descriptionId;

    private Long imageId;

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

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }
}
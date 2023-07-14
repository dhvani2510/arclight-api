package com.example.arclight.repositories;

import com.example.arclight.entities.BasicLearning;
import com.example.arclight.entities.Translation;
import com.example.arclight.entities.User;
import com.example.arclight.entities.datatypes.Category;
import jakarta.persistence.Basic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BasicLearningRepository extends JpaRepository<BasicLearning,Long>
{
    //Optional<BasicLearning> findById(Long id);

     List<BasicLearning> findByCategory(Category category);
     BasicLearning findByTitle_IdAndDescription_IdAndCategory(Long titleId, Long descriptionId, Category category);
    //BasicLearning findByTitleIdAndDescriptionIdAndCategory(Long titleId, Long descriptionId, Category category);
}

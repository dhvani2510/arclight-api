package com.example.arclight.repositories;

import com.example.arclight.entities.BasicLearning;
import com.example.arclight.entities.File;
import com.example.arclight.entities.datatypes.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BasicLearningRepository extends JpaRepository<BasicLearning,Long>
{
    //Optional<BasicLearning> findById(Long id);

    List<BasicLearning> findByCategory(Category category);
    BasicLearning findByTitle_IdAndDescription_IdAndCategory(Long titleId, Long descriptionId, Category category);
    //BasicLearning findByTitleIdAndDescriptionIdAndCategory(Long titleId, Long descriptionId, Category category);
}

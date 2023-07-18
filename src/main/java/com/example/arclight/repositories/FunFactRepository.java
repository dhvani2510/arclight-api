package com.example.arclight.repositories;

import com.example.arclight.entities.FunFact;
import com.example.arclight.entities.datatypes.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FunFactRepository extends JpaRepository<FunFact,Long>
{
    //Optional<BasicLearning> findById(Long id);

    List<FunFact> findByCategory(Category category);
    FunFact findByTitle_IdAndDescription_IdAndCategory(Long titleId, Long descriptionId, Category category);
    //BasicLearning findByTitleIdAndDescriptionIdAndCategory(Long titleId, Long descriptionId, Category category);
}
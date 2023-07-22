package com.example.arclight.repositories;

import com.example.arclight.entities.BasicLearning;
import com.example.arclight.entities.FunFact;
import com.example.arclight.entities.datatypes.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface FunFactRepository extends JpaRepository<FunFact,Long>
{
    @Query(value = "SELECT * FROM fun_fact ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Optional<FunFact> findRandom();
    FunFact findByTitle_IdAndDescription_Id(Long titleId, Long descriptionId);
}
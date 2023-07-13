package com.example.arclight.repositories;

import com.example.arclight.entities.BasicLearning;
import com.example.arclight.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BasicLearningRepository extends JpaRepository<BasicLearning,Long>
{
    Optional<BasicLearning> findById(Long id);
}

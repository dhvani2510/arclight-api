package com.example.arclight.repositories;

import com.example.arclight.entities.BasicLearning;
import com.example.arclight.entities.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<Quiz,Long>
{
}

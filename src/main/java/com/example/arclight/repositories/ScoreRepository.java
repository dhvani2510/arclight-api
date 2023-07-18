package com.example.arclight.repositories;

import com.example.arclight.entities.Quiz;
import com.example.arclight.entities.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreRepository  extends JpaRepository<Score,Long>
{
    List<Score> findByStudent_Id(Long userId);
}

package com.example.arclight.repositories;

import com.example.arclight.entities.Translation;
import com.example.arclight.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TranslationRepository extends JpaRepository<Translation,Long>
{
    Translation findByEnglishAndHindiAndFrench(String english, String hindi, String french); //Optional
    Translation findByEnglish(String english);
}

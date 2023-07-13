package com.example.arclight.services;

import com.example.arclight.repositories.BasicLearningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasicLearningService
{
    private  final BasicLearningRepository basicLearningRepository;

    @Autowired
    public   BasicLearningService(BasicLearningRepository basicLearningRepository)
    {
        this.basicLearningRepository = basicLearningRepository;
    }

    public  Lis

}

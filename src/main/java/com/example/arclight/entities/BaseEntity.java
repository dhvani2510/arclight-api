package com.example.arclight.entities;

import jakarta.annotation.Nullable;
import jakarta.persistence.MappedSuperclass;

import java.time.LocalDateTime;

@MappedSuperclass

public abstract class BaseEntity {

     //public UUID Id;
     protected LocalDateTime CreatedAt;
     @Nullable
     protected LocalDateTime DeletedAt;
     protected Long CreatorId;

//     @SequenceGenerator(
//             name = "language_sequence",
//             sequenceName = "language_sequence",
//             allocationSize = 1
//     )
//     @GeneratedValue(
//             strategy = GenerationType.SEQUENCE,
//             generator = "language_sequence"
//     )
}

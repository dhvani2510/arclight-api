package com.example.arclight.entities;

import jakarta.annotation.Nullable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.SequenceGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
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

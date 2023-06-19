package com.example.arclight.entities;

import com.example.arclight.entities.datatypes.LanguageOption;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table
public class Language  extends  BaseEntity
{
    @Id
//    @SequenceGenerator(
//            name = "language_sequence",
//            sequenceName = "language_sequence",
//            allocationSize = 1
//    )
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "language_sequence"
//    )
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Long Id;
    public LanguageOption Name;

//    @ManyToMany(mappedBy = "UserLanguages")
//    Set<User> Users;
}

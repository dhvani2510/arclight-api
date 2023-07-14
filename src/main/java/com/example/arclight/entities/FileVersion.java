package com.example.arclight.entities;

import com.example.arclight.entities.datatypes.LanguageOption;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;

@Entity
@Table
public class FileVersion extends BaseEntity
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Long Id;

    @OneToOne(cascade = CascadeType.PERSIST)
    private File english;
    @OneToOne(cascade = CascadeType.PERSIST)
    private File hindi;
    @OneToOne(cascade = CascadeType.PERSIST)
    private File french;


    public String translate(LanguageOption languageOption) {
      return "/api/v1/files/3";
        // Get Url //TODO to implement
//        return  languageOption== LanguageOption.Hindi? hindi:
//                languageOption== LanguageOption.French? french:
//                        english;
    }
}

package com.example.arclight.entities;

import com.example.arclight.entities.datatypes.LanguageOption;
import jakarta.persistence.*;

@Entity
@Table//(name = "student_languages")
public class StudentLanguage extends  BaseEntity
{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    public Long Id;

    @OneToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "UserId", referencedColumnName = "id")
    private User User;
    @Enumerated(EnumType.STRING)
    private LanguageOption Language;

//    @OneToMany(cascade = CascadeType.ALL, targetEntity = Report.class)
//    @JoinColumn(name = "to_user_id")
//    private List<Report> reportReceivedList;
}

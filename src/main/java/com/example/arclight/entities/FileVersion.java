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
    private Long id;

    @OneToOne(cascade = CascadeType.PERSIST)
    private File english;
    @OneToOne(cascade = CascadeType.PERSIST)
    private File hindi;
    @OneToOne(cascade = CascadeType.PERSIST)
    private File french;

    public  FileVersion(){}
    public FileVersion(File english, File hindi, File french){
        this.english=english;
        this.hindi=hindi;
        this.french=french;
    }

    public String translate(LanguageOption languageOption) {
        if(this==null)
            return null;
        return  languageOption== LanguageOption.Hindi? hindi.getUrl():
               languageOption== LanguageOption.French? french.getUrl():
                        english.getUrl();
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public File getEnglish() {
        return english;
    }

    public void setEnglish(File english) {
        this.english = english;
    }

    public File getHindi() {
        return hindi;
    }

    public void setHindi(File hindi) {
        this.hindi = hindi;
    }

    public File getFrench() {
        return french;
    }

    public void setFrench(File french) {
        this.french = french;
    }
}

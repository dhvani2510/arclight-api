package com.example.arclight.entities;

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
    public File English;
    @OneToOne(cascade = CascadeType.PERSIST)
    public File Hindi;
    @OneToOne(cascade = CascadeType.PERSIST)
    public File French;
}

package com.example.arclight.entities;

import jakarta.persistence.*;

@Entity
@Table
public class File extends  BaseEntity
{
    @Id
//    @SequenceGenerator(
//            name = "file_sequence",
//            sequenceName = "file_sequence",
//            allocationSize = 1
//    )
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "file_sequence"
//    )
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Long Id;
    public String Name ;
    public String ContentType ;// Image, Video
    public byte[] Bytes ;
    public Long SizeInBytes ;

    public  File(){

    }
}

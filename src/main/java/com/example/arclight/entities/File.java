package com.example.arclight.entities;

import jakarta.persistence.*;

@Entity
@Table
public class File extends  BaseEntity
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long Id;
    private String name ;
    private String contentType ;// Image, Audio, Video
    private byte[] bytes ;
    private Long sizeInBytes ;

//    public  File(){
//
//    }

    public File(String name, String contentType, byte[] bytes, Long sizeInBytes) {
        this.name = name;
        this.contentType = contentType;
        this.bytes = bytes;
        this.sizeInBytes = sizeInBytes;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public Long getSizeInBytes() {
        return sizeInBytes;
    }

    public void setSizeInBytes(Long sizeInBytes) {
        this.sizeInBytes = sizeInBytes;
    }
}

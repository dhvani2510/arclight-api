package com.example.arclight.models.files;

import com.example.arclight.entities.File;

public class FileResponse
{
    private Long id;
    private  String name;
    private  String url;

    public FileResponse(Long id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }
    public  FileResponse(File file){
        id= file.Id;
        name=file.Name;
        url= "/api/v1/files/" + file.Id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

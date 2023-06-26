package com.example.arclight.models;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseModel
{
    private String message;
    private Object data;

    public ResponseModel(String _message, Object _data) {
        message = _message;
        data = _data;
    }

    public ResponseModel(String _message) {
        message = _message;
    }
    public  static ResponseEntity<ResponseModel> Ok(String _message){
        var response= new ResponseModel(_message,null);
        return new ResponseEntity(response, HttpStatus.OK);
    }
    public  static ResponseEntity<ResponseModel> Ok(String _message, Object _data){
        var response= new ResponseModel(_message,_data);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    public static ResponseEntity<ResponseModel> Fail(String _message,  HttpStatus status){
        if(status==HttpStatus.OK)
            return null;
        var response= new ResponseModel(_message,null);
        return new ResponseEntity(response, status);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

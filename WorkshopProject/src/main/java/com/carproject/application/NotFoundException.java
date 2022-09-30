package com.carproject.application;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message){
        super(message);
    }
}

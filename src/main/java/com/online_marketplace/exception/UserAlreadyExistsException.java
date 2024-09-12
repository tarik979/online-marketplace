package com.online_marketplace.exception;

public class UserAlreadyExistsException extends Exception{
    public UserAlreadyExistsException(String message){
        super(message);
    }
}

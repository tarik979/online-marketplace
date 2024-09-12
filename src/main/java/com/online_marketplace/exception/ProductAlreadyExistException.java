package com.online_marketplace.exception;

public class ProductAlreadyExistException extends RuntimeException{
    public ProductAlreadyExistException(String message){
        super(message);
    }
}

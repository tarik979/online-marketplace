package com.online_marketplace.exception;

public class OrderDetailsNotFoundException extends RuntimeException{
    public OrderDetailsNotFoundException(String message){
        super(message);
    }

}

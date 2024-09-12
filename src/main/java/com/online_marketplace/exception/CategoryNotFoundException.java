package com.online_marketplace.exception;

public class CategoryNotFoundException extends RuntimeException{
   public CategoryNotFoundException(String message){
        super(message);
   }
}

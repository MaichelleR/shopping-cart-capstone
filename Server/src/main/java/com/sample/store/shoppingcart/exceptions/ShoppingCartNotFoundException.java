package com.sample.store.shoppingcart.exceptions;

public class ShoppingCartNotFoundException extends RuntimeException{
    public ShoppingCartNotFoundException(String message){
        super(message);
    }
}

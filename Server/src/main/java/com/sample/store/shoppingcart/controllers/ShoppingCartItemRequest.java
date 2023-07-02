package com.sample.store.shoppingcart.controllers;

public class ShoppingCartItemRequest {

    private String sku;
    private int quantity;

    public ShoppingCartItemRequest(String sku, int quantity) {
        this.sku = sku;
        this.quantity = quantity;
    }

    public ShoppingCartItemRequest() {

    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


}

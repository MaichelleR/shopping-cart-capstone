package com.sample.store.shoppingcart.controllers;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartResponse {

    List<CartRow> cartRows = new ArrayList<>();
    private String totalPrice;

    public ShoppingCartResponse(List<CartRow> cartRows, String totalPrice) {
        this.cartRows = cartRows;
        this.totalPrice = totalPrice;
    }

    public List<CartRow> getCartRows() {
        return cartRows;
    }

    public void setCartRows(List<CartRow> cartRows) {
        this.cartRows = cartRows;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }
}

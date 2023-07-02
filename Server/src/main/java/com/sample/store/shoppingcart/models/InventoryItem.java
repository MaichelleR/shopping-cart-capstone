package com.sample.store.shoppingcart.models;

import jakarta.persistence.*;

import javax.annotation.processing.Generated;

@Entity
@Table(name = "INVENTORY_ITEM")
public class InventoryItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String sku;
    private String name;
    private String description;
    private double price;
    private String url;

    public InventoryItem(){

    }

    public InventoryItem(String id, String name, String description, double price, String url) {
        this.sku = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

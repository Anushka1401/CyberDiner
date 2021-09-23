package com.example.cyberdiner.model;

public class CartList {

    String name;
    String price;
    Integer imageUrl;
    String qty;

    public CartList(String name, String price, Integer imageUrl, String qty) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
        this.qty = qty;
    }


    public String getQuantity() {
        return qty;
    }

    public void setQuantity(String qty) {
        this.qty = qty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Integer imageUrl) {
        this.imageUrl = imageUrl;
    }
}


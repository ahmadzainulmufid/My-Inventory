package com.example.coba;

public class Item {
    private String name;
    private String code;
    private String type;
    private String price;
    private String stock;
    private String date;
    private String imageURL;

    public Item() {
        // Diperlukan konstruktor kosong untuk Firebase Realtime Database
    }

    public Item(String name, String code, String type, String price, String stock, String date, String imageURL) {
        this.name = name;
        this.code = code;
        this.type = type;
        this.price = price;
        this.stock = stock;
        this.date = date;
        this.imageURL = imageURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
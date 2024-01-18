package com.example.coba;

public class DataClass {
    private String dataName;
    private String dataCode;
    private String dataType;
    private String dataPrice;
    private String dataStock;
    private String dataDate;
    private String dataImage;
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDataName() {
        return dataName;
    }

    public String getDataCode() {
        return dataCode;
    }

    public String getDataType() {
        return dataType;
    }

    public String getDataPrice() {
        return dataPrice;
    }

    public String getDataStock() {
        return dataStock;
    }

    public String getDataDate() {
        return dataDate;
    }

    public String getDataImage() {
        return dataImage;
    }

    public DataClass(String dataName, String dataCode, String dataType, String dataPrice, String dataStock, String dataDate, String dataImage) {
        this.dataName = dataName;
        this.dataCode = dataCode;
        this.dataType = dataType;
        this.dataPrice = dataPrice;
        this.dataStock = dataStock;
        this.dataDate = dataDate;
        this.dataImage = dataImage;
    }

    public DataClass() {

    }

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
    }
}

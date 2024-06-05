package com.DIY.Detissue.payload.response;

public class ProductResponse {
    private int id;
    private String name;
    private String shortDesc;
    private String fullDesc;
    private String image;
    private long priceMax;
    private long priceMin;
    private String category;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getFullDesc() {
        return fullDesc;
    }

    public void setFullDesc(String fullDesc) {
        this.fullDesc = fullDesc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getPriceMax() {
        return priceMax;
    }

    public void setPriceMax(long priceMax) {
        this.priceMax = priceMax;
    }

    public long getPriceMin() {
        return priceMin;
    }

    public void setPriceMin(long priceMin) {
        this.priceMin = priceMin;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
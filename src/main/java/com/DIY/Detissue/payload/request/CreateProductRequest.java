package com.DIY.Detissue.payload.request;

import java.util.List;

public class CreateProductRequest {
    private int id;
    private String name;
    private String shortDesc;
    private String fullDesc;
    private int categoryId;
    private List<ProductSizeRequest> productSizes;

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

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public List<ProductSizeRequest> getProductSizes() {
        return productSizes;
    }

    public void setProductSizes(List<ProductSizeRequest> productSizes) {
        this.productSizes = productSizes;
    }
}
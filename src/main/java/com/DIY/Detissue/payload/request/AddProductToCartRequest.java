package com.DIY.Detissue.payload.request;

import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class AddProductToCartRequest {
    private int userId;
    private int productId;
    @NotNull
    @Min(value = 1, message = "Quantity must be greater than 0")
    private int quantity;
    private int attributOptionsId;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getAttributOptionsId() {
        return attributOptionsId;
    }

    public void setAttributOptionsId(int attributOptionsId) {
        this.attributOptionsId = attributOptionsId;
    }
}

package com.DIY.Detissue.payload.response;

public class OrderStatusResponse {
    private String status;

    private int id;

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }
}

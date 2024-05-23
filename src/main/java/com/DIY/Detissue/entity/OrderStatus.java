package com.DIY.Detissue.entity;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "order_status", schema = "detissue")
public class OrderStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "status", length = 50)
    private String status;

    @OneToMany(mappedBy = "orderStatus")
    private Set<com.DIY.Detissue.entity.ShopOrder> shopOrders = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<com.DIY.Detissue.entity.ShopOrder> getShopOrders() {
        return shopOrders;
    }

    public void setShopOrders(Set<com.DIY.Detissue.entity.ShopOrder> shopOrders) {
        this.shopOrders = shopOrders;
    }

}
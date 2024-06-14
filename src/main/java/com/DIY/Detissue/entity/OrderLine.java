package com.DIY.Detissue.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "order_line", schema = "detissue")
public class OrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_order_id")
    private com.DIY.Detissue.entity.ShopOrder shopOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_skus_id")
    private com.DIY.Detissue.entity.ProductSkus productSkus;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price", precision = 10, scale = 2)
    private long price;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public com.DIY.Detissue.entity.ShopOrder getShopOrder() {
        return shopOrder;
    }

    public void setShopOrder(com.DIY.Detissue.entity.ShopOrder shopOrder) {
        this.shopOrder = shopOrder;
    }

    public com.DIY.Detissue.entity.ProductSkus getProductSkus() {
        return productSkus;
    }

    public void setProductSkus(com.DIY.Detissue.entity.ProductSkus productSkus) {
        this.productSkus = productSkus;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

}
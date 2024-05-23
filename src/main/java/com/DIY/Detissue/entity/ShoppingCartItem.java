package com.DIY.Detissue.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "shopping_cart_item", schema = "detissue")
public class ShoppingCartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private com.DIY.Detissue.entity.Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_skus_id")
    private com.DIY.Detissue.entity.ProductSkus productSkus;

    @Column(name = "quantity")
    private Integer quantity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public com.DIY.Detissue.entity.Cart getCart() {
        return cart;
    }

    public void setCart(com.DIY.Detissue.entity.Cart cart) {
        this.cart = cart;
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

}
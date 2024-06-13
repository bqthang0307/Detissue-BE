package com.DIY.Detissue.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "product_skus", schema = "detissue")
public class ProductSkus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private com.DIY.Detissue.entity.Product product;

    @Column(name = "stock_quantity")
    private Integer stockQuantity;

    @OneToMany(mappedBy = "productSkus")
    private Set<com.DIY.Detissue.entity.Image> images = new LinkedHashSet<>();

    @OneToMany(mappedBy = "productSkus")
    private Set<com.DIY.Detissue.entity.OrderLine> orderLines = new LinkedHashSet<>();

    @OneToMany(mappedBy = "productSkus")
    private Set<com.DIY.Detissue.entity.Review> reviews = new LinkedHashSet<>();

    @OneToMany(mappedBy = "productSkus")
    private Set<com.DIY.Detissue.entity.ShoppingCartItem> shoppingCartItems = new LinkedHashSet<>();

    @Column(name = "price")
    private Long price;

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public com.DIY.Detissue.entity.Product getProduct() {
        return product;
    }

    public void setProduct(com.DIY.Detissue.entity.Product product) {
        this.product = product;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Set<com.DIY.Detissue.entity.Image> getImages() {
        return images;
    }

    public void setImages(Set<com.DIY.Detissue.entity.Image> images) {
        this.images = images;
    }

    public Set<com.DIY.Detissue.entity.OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(Set<com.DIY.Detissue.entity.OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    public Set<com.DIY.Detissue.entity.Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<com.DIY.Detissue.entity.Review> reviews) {
        this.reviews = reviews;
    }

    public Set<com.DIY.Detissue.entity.ShoppingCartItem> getShoppingCartItems() {
        return shoppingCartItems;
    }

    public void setShoppingCartItems(Set<com.DIY.Detissue.entity.ShoppingCartItem> shoppingCartItems) {
        this.shoppingCartItems = shoppingCartItems;
    }

}
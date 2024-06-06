package com.DIY.Detissue.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_wishlist", schema = "detissue")
public class UserWishlist {
    @EmbeddedId
    private UserWishlistId id;

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @MapsId("productId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public UserWishlistId getId() {
        return id;
    }

    public void setId(UserWishlistId id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}
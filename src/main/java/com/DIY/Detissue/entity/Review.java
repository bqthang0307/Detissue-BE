package com.DIY.Detissue.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "review", schema = "detissue")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "rating")
    private Integer rating;

    @Lob
    @Column(name = "time_created")
    private String timeCreated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_skus_id")
    private com.DIY.Detissue.entity.ProductSkus productSkus;

    @Lob
    @Column(name = "reviews")
    private String reviews;

    public String getReviews() {
        return reviews;
    }

    public void setReviews(String reviews) {
        this.reviews = reviews;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(String timeCreated) {
        this.timeCreated = timeCreated;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public com.DIY.Detissue.entity.ProductSkus getProductSkus() {
        return productSkus;
    }

    public void setProductSkus(com.DIY.Detissue.entity.ProductSkus productSkus) {
        this.productSkus = productSkus;
    }

}
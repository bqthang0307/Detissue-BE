package com.DIY.Detissue.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "promotion_category", schema = "detissue")
public class PromotionCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private com.DIY.Detissue.entity.Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "promotion_id")
    private com.DIY.Detissue.entity.Promotion promotion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public com.DIY.Detissue.entity.Category getCategory() {
        return category;
    }

    public void setCategory(com.DIY.Detissue.entity.Category category) {
        this.category = category;
    }

    public com.DIY.Detissue.entity.Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(com.DIY.Detissue.entity.Promotion promotion) {
        this.promotion = promotion;
    }

}
package com.DIY.Detissue.entity;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "category", schema = "detissue")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_category_id")
    private com.DIY.Detissue.entity.Category parentCategory;

    @Column(name = "name", length = 100)
    private String name;

    @OneToMany(mappedBy = "category")
    private Set<com.DIY.Detissue.entity.Attribute> attributes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "parentCategory")
    private Set<com.DIY.Detissue.entity.Category> categories = new LinkedHashSet<>();

    @OneToMany(mappedBy = "category")
    private Set<com.DIY.Detissue.entity.CategoryTag> categoryTags = new LinkedHashSet<>();

    @OneToMany(mappedBy = "category")
    private Set<com.DIY.Detissue.entity.Product> products = new LinkedHashSet<>();

    @OneToMany(mappedBy = "category")
    private Set<com.DIY.Detissue.entity.PromotionCategory> promotionCategories = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public com.DIY.Detissue.entity.Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(com.DIY.Detissue.entity.Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<com.DIY.Detissue.entity.Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(Set<com.DIY.Detissue.entity.Attribute> attributes) {
        this.attributes = attributes;
    }

    public Set<com.DIY.Detissue.entity.Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<com.DIY.Detissue.entity.Category> categories) {
        this.categories = categories;
    }

    public Set<com.DIY.Detissue.entity.CategoryTag> getCategoryTags() {
        return categoryTags;
    }

    public void setCategoryTags(Set<com.DIY.Detissue.entity.CategoryTag> categoryTags) {
        this.categoryTags = categoryTags;
    }

    public Set<com.DIY.Detissue.entity.Product> getProducts() {
        return products;
    }

    public void setProducts(Set<com.DIY.Detissue.entity.Product> products) {
        this.products = products;
    }

    public Set<com.DIY.Detissue.entity.PromotionCategory> getPromotionCategories() {
        return promotionCategories;
    }

    public void setPromotionCategories(Set<com.DIY.Detissue.entity.PromotionCategory> promotionCategories) {
        this.promotionCategories = promotionCategories;
    }

}
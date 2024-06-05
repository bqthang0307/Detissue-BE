package com.DIY.Detissue.entity;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "product", schema = "detissue")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Lob
    @Column(name = "short_desc")
    private String shortDesc;

    @Lob
    @Column(name = "full_desc")
    private String fullDesc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private com.DIY.Detissue.entity.Category category;

    @OneToMany(mappedBy = "product")
    private Set<com.DIY.Detissue.entity.ProductSkus> productSkuses = new LinkedHashSet<>();

    @Column(name = "image")
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getFullDesc() {
        return fullDesc;
    }

    public void setFullDesc(String fullDesc) {
        this.fullDesc = fullDesc;
    }

    public com.DIY.Detissue.entity.Category getCategory() {
        return category;
    }

    public void setCategory(com.DIY.Detissue.entity.Category category) {
        this.category = category;
    }

    public Set<com.DIY.Detissue.entity.ProductSkus> getProductSkuses() {
        return productSkuses;
    }

    public void setProductSkuses(Set<com.DIY.Detissue.entity.ProductSkus> productSkuses) {
        this.productSkuses = productSkuses;
    }

}
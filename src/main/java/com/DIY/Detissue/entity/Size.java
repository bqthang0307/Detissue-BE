package com.DIY.Detissue.entity;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "size", schema = "detissue")
public class Size {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "size")
    private Set<ProductSkus> productSkuses = new LinkedHashSet<>();

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

    public Set<ProductSkus> getProductSkuses() {
        return productSkuses;
    }

    public void setProductSkuses(Set<ProductSkus> productSkuses) {
        this.productSkuses = productSkuses;
    }

}
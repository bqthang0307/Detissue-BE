package com.DIY.Detissue.entity;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "attributes", schema = "detissue")
public class Attribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", length = 100)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private com.DIY.Detissue.entity.Category category;

    @OneToMany(mappedBy = "attribute")
    private Set<com.DIY.Detissue.entity.AttributeOption> attributeOptions = new LinkedHashSet<>();

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

    public com.DIY.Detissue.entity.Category getCategory() {
        return category;
    }

    public void setCategory(com.DIY.Detissue.entity.Category category) {
        this.category = category;
    }

    public Set<com.DIY.Detissue.entity.AttributeOption> getAttributeOptions() {
        return attributeOptions;
    }

    public void setAttributeOptions(Set<com.DIY.Detissue.entity.AttributeOption> attributeOptions) {
        this.attributeOptions = attributeOptions;
    }

}
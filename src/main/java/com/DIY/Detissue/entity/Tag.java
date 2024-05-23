package com.DIY.Detissue.entity;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "tag", schema = "detissue")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", length = 100)
    private String name;

    @OneToMany(mappedBy = "tag")
    private Set<com.DIY.Detissue.entity.BlogTag> blogTags = new LinkedHashSet<>();

    @OneToMany(mappedBy = "tag")
    private Set<com.DIY.Detissue.entity.CategoryTag> categoryTags = new LinkedHashSet<>();

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

    public Set<com.DIY.Detissue.entity.BlogTag> getBlogTags() {
        return blogTags;
    }

    public void setBlogTags(Set<com.DIY.Detissue.entity.BlogTag> blogTags) {
        this.blogTags = blogTags;
    }

    public Set<com.DIY.Detissue.entity.CategoryTag> getCategoryTags() {
        return categoryTags;
    }

    public void setCategoryTags(Set<com.DIY.Detissue.entity.CategoryTag> categoryTags) {
        this.categoryTags = categoryTags;
    }

}
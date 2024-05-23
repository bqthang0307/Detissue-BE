package com.DIY.Detissue.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "category_tag", schema = "detissue")
public class CategoryTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id")
    private com.DIY.Detissue.entity.Tag tag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private com.DIY.Detissue.entity.Category category;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public com.DIY.Detissue.entity.Tag getTag() {
        return tag;
    }

    public void setTag(com.DIY.Detissue.entity.Tag tag) {
        this.tag = tag;
    }

    public com.DIY.Detissue.entity.Category getCategory() {
        return category;
    }

    public void setCategory(com.DIY.Detissue.entity.Category category) {
        this.category = category;
    }

}
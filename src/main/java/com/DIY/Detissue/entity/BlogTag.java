package com.DIY.Detissue.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "blog_tag", schema = "detissue")
public class BlogTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "blog_id")
    private Integer blogId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id")
    private com.DIY.Detissue.entity.Tag tag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    public com.DIY.Detissue.entity.Tag getTag() {
        return tag;
    }

    public void setTag(com.DIY.Detissue.entity.Tag tag) {
        this.tag = tag;
    }

}
package com.DIY.Detissue.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "image", schema = "detissue")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "source")
    private String source;

    @Column(name = "detail")
    private String detail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_skus_id")
    private com.DIY.Detissue.entity.ProductSkus productSkus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public com.DIY.Detissue.entity.ProductSkus getProductSkus() {
        return productSkus;
    }

    public void setProductSkus(com.DIY.Detissue.entity.ProductSkus productSkus) {
        this.productSkus = productSkus;
    }

}
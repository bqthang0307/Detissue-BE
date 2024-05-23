package com.DIY.Detissue.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "attribute_option_skus", schema = "detissue")
public class AttributeOptionSkus {
    @EmbeddedId
    private AttributeOptionSkusId id;

    @MapsId("skusId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "skus_id", nullable = false)
    private com.DIY.Detissue.entity.ProductSkus skus;

    @MapsId("attributeOptionId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "attribute_option_id", nullable = false)
    private com.DIY.Detissue.entity.AttributeOption attributeOption;

    public AttributeOptionSkusId getId() {
        return id;
    }

    public void setId(AttributeOptionSkusId id) {
        this.id = id;
    }

    public com.DIY.Detissue.entity.ProductSkus getSkus() {
        return skus;
    }

    public void setSkus(com.DIY.Detissue.entity.ProductSkus skus) {
        this.skus = skus;
    }

    public com.DIY.Detissue.entity.AttributeOption getAttributeOption() {
        return attributeOption;
    }

    public void setAttributeOption(com.DIY.Detissue.entity.AttributeOption attributeOption) {
        this.attributeOption = attributeOption;
    }

}
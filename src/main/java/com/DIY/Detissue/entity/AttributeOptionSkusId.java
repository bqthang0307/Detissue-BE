package com.DIY.Detissue.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AttributeOptionSkusId implements Serializable {
    private static final long serialVersionUID = -735279984349035774L;
    @Column(name = "skus_id", nullable = false)
    private Integer skusId;

    @Column(name = "attribute_option_id", nullable = false)
    private Integer attributeOptionId;

    public Integer getSkusId() {
        return skusId;
    }

    public void setSkusId(Integer skusId) {
        this.skusId = skusId;
    }

    public Integer getAttributeOptionId() {
        return attributeOptionId;
    }

    public void setAttributeOptionId(Integer attributeOptionId) {
        this.attributeOptionId = attributeOptionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AttributeOptionSkusId entity = (AttributeOptionSkusId) o;
        return Objects.equals(this.skusId, entity.skusId) &&
                Objects.equals(this.attributeOptionId, entity.attributeOptionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(skusId, attributeOptionId);
    }

}
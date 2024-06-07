package com.DIY.Detissue.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OrderLineAttributesOptionId implements Serializable {
    private static final long serialVersionUID = 2776210670177000700L;
    @Column(name = "order_line_id", nullable = false)
    private Integer orderLineId;

    @Column(name = "attribute_options_id", nullable = false)
    private Integer attributeOptionsId;

    public Integer getOrderLineId() {
        return orderLineId;
    }

    public void setOrderLineId(Integer orderLineId) {
        this.orderLineId = orderLineId;
    }

    public Integer getAttributeOptionsId() {
        return attributeOptionsId;
    }

    public void setAttributeOptionsId(Integer attributeOptionsId) {
        this.attributeOptionsId = attributeOptionsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        OrderLineAttributesOptionId entity = (OrderLineAttributesOptionId) o;
        return Objects.equals(this.attributeOptionsId, entity.attributeOptionsId) &&
                Objects.equals(this.orderLineId, entity.orderLineId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attributeOptionsId, orderLineId);
    }

}
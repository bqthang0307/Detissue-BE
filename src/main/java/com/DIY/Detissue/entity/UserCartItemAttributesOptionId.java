package com.DIY.Detissue.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserCartItemAttributesOptionId implements Serializable {
    private static final long serialVersionUID = -3757295269185901984L;
    @Column(name = "shopping_cart_item_id", nullable = false)
    private Integer shoppingCartItemId;

    @Column(name = "attribute_options_id", nullable = false)
    private Integer attributeOptionsId;

    public Integer getShoppingCartItemId() {
        return shoppingCartItemId;
    }

    public void setShoppingCartItemId(Integer shoppingCartItemId) {
        this.shoppingCartItemId = shoppingCartItemId;
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
        UserCartItemAttributesOptionId entity = (UserCartItemAttributesOptionId) o;
        return Objects.equals(this.attributeOptionsId, entity.attributeOptionsId) &&
                Objects.equals(this.shoppingCartItemId, entity.shoppingCartItemId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attributeOptionsId, shoppingCartItemId);
    }

}
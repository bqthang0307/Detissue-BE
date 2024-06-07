package com.DIY.Detissue.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_cart_item_attributes_options", schema = "detissue")
public class UserCartItemAttributesOption {
    @EmbeddedId
    private UserCartItemAttributesOptionId id;

    @MapsId("shoppingCartItemId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "shopping_cart_item_id", nullable = false)
    private ShoppingCartItem shoppingCartItem;

    @MapsId("attributeOptionsId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "attribute_options_id", nullable = false)
    private AttributeOption attributeOptions;

    public UserCartItemAttributesOptionId getId() {
        return id;
    }

    public void setId(UserCartItemAttributesOptionId id) {
        this.id = id;
    }

    public ShoppingCartItem getShoppingCartItem() {
        return shoppingCartItem;
    }

    public void setShoppingCartItem(ShoppingCartItem shoppingCartItem) {
        this.shoppingCartItem = shoppingCartItem;
    }

    public AttributeOption getAttributeOptions() {
        return attributeOptions;
    }

    public void setAttributeOptions(AttributeOption attributeOptions) {
        this.attributeOptions = attributeOptions;
    }

}
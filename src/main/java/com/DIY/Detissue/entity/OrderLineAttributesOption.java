package com.DIY.Detissue.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "order_line_attributes_options", schema = "detissue")
public class OrderLineAttributesOption {
    @EmbeddedId
    private OrderLineAttributesOptionId id;

    @MapsId("orderLineId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_line_id", nullable = false)
    private OrderLine orderLine;

    @MapsId("attributeOptionsId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "attribute_options_id", nullable = false)
    private AttributeOption attributeOptions;

    public OrderLineAttributesOptionId getId() {
        return id;
    }

    public void setId(OrderLineAttributesOptionId id) {
        this.id = id;
    }

    public OrderLine getOrderLine() {
        return orderLine;
    }

    public void setOrderLine(OrderLine orderLine) {
        this.orderLine = orderLine;
    }

    public AttributeOption getAttributeOptions() {
        return attributeOptions;
    }

    public void setAttributeOptions(AttributeOption attributeOptions) {
        this.attributeOptions = attributeOptions;
    }

}
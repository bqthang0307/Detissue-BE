package com.DIY.Detissue.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "shop_order", schema = "detissue")
public class ShopOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "order_date")
    private Instant orderDate;

    @Column(name = "order_total", precision = 10, scale = 2)
    private BigDecimal orderTotal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_method_id")
    private com.DIY.Detissue.entity.PaymentMethod paymentMethod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_status_id")
    private com.DIY.Detissue.entity.OrderStatus orderStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shiping_address_id")
    private com.DIY.Detissue.entity.Address shipingAddress;

    @OneToMany(mappedBy = "shopOrder")
    private Set<com.DIY.Detissue.entity.OrderLine> orderLines = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Instant getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Instant orderDate) {
        this.orderDate = orderDate;
    }

    public BigDecimal getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(BigDecimal orderTotal) {
        this.orderTotal = orderTotal;
    }

    public com.DIY.Detissue.entity.PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(com.DIY.Detissue.entity.PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public com.DIY.Detissue.entity.OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(com.DIY.Detissue.entity.OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public com.DIY.Detissue.entity.Address getShipingAddress() {
        return shipingAddress;
    }

    public void setShipingAddress(com.DIY.Detissue.entity.Address shipingAddress) {
        this.shipingAddress = shipingAddress;
    }

    public Set<com.DIY.Detissue.entity.OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(Set<com.DIY.Detissue.entity.OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

}
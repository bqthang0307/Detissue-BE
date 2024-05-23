package com.DIY.Detissue.entity;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "payment_method", schema = "detissue")
public class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_type_id")
    private com.DIY.Detissue.entity.PaymentType paymentType;

    @OneToMany(mappedBy = "paymentMethod")
    private Set<com.DIY.Detissue.entity.ShopOrder> shopOrders = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public com.DIY.Detissue.entity.PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(com.DIY.Detissue.entity.PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public Set<com.DIY.Detissue.entity.ShopOrder> getShopOrders() {
        return shopOrders;
    }

    public void setShopOrders(Set<com.DIY.Detissue.entity.ShopOrder> shopOrders) {
        this.shopOrders = shopOrders;
    }

}
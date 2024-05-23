package com.DIY.Detissue.entity;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "payment_type", schema = "detissue")
public class PaymentType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "value", length = 50)
    private String value;

    @OneToMany(mappedBy = "paymentType")
    private Set<com.DIY.Detissue.entity.PaymentMethod> paymentMethods = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Set<com.DIY.Detissue.entity.PaymentMethod> getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(Set<com.DIY.Detissue.entity.PaymentMethod> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

}
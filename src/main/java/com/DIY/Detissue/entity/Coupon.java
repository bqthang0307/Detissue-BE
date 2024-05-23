package com.DIY.Detissue.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "coupon", schema = "detissue")
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "code")
    private String code;

    @Column(name = "type")
    private String type;

    @Column(name = "discount_rate", precision = 10)
    private BigDecimal discountRate;

    @OneToMany(mappedBy = "coupon")
    private Set<com.DIY.Detissue.entity.UserCoupon> userCoupons = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(BigDecimal discountRate) {
        this.discountRate = discountRate;
    }

    public Set<com.DIY.Detissue.entity.UserCoupon> getUserCoupons() {
        return userCoupons;
    }

    public void setUserCoupons(Set<com.DIY.Detissue.entity.UserCoupon> userCoupons) {
        this.userCoupons = userCoupons;
    }

}
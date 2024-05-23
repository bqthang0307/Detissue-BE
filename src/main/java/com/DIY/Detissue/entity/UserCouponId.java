package com.DIY.Detissue.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserCouponId implements Serializable {
    private static final long serialVersionUID = 7759447432170022813L;
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "coupon_id", nullable = false)
    private Integer couponId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserCouponId entity = (UserCouponId) o;
        return Objects.equals(this.couponId, entity.couponId) &&
                Objects.equals(this.userId, entity.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(couponId, userId);
    }

}
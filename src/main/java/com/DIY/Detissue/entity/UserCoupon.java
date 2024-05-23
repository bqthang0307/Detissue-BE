package com.DIY.Detissue.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_coupon", schema = "detissue")
public class UserCoupon {
    @EmbeddedId
    private UserCouponId id;

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @MapsId("couponId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "coupon_id", nullable = false)
    private com.DIY.Detissue.entity.Coupon coupon;

    @Column(name = "is_used")
    private Boolean isUsed;

    public UserCouponId getId() {
        return id;
    }

    public void setId(UserCouponId id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public com.DIY.Detissue.entity.Coupon getCoupon() {
        return coupon;
    }

    public void setCoupon(com.DIY.Detissue.entity.Coupon coupon) {
        this.coupon = coupon;
    }

    public Boolean getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(Boolean isUsed) {
        this.isUsed = isUsed;
    }

}
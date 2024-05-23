package com.DIY.Detissue.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_address", schema = "detissue")
public class UserAddress {
    @EmbeddedId
    private UserAddressId id;

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @MapsId("addressId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "address_id", nullable = false)
    private com.DIY.Detissue.entity.Address address;

    @Column(name = "is_default")
    private Boolean isDefault;

    public UserAddressId getId() {
        return id;
    }

    public void setId(UserAddressId id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public com.DIY.Detissue.entity.Address getAddress() {
        return address;
    }

    public void setAddress(com.DIY.Detissue.entity.Address address) {
        this.address = address;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

}
package com.DIY.Detissue.entity;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "address", schema = "detissue")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "street_address")
    private String streetAddress;

    @Column(name = "town_city")
    private String townCity;

    @Column(name = "state_country", length = 100)
    private String stateCountry;

    @Column(name = "company")
    private String company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private com.DIY.Detissue.entity.Country country;

    @Column(name = "payment_method_id")
    private Integer paymentMethodId;

    @OneToMany(mappedBy = "shipingAddress")
    private Set<com.DIY.Detissue.entity.ShopOrder> shopOrders = new LinkedHashSet<>();

    @OneToMany(mappedBy = "address")
    private Set<com.DIY.Detissue.entity.UserAddress> userAddresses = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getTownCity() {
        return townCity;
    }

    public void setTownCity(String townCity) {
        this.townCity = townCity;
    }

    public String getStateCountry() {
        return stateCountry;
    }

    public void setStateCountry(String stateCountry) {
        this.stateCountry = stateCountry;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public com.DIY.Detissue.entity.Country getCountry() {
        return country;
    }

    public void setCountry(com.DIY.Detissue.entity.Country country) {
        this.country = country;
    }

    public Integer getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(Integer paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public Set<com.DIY.Detissue.entity.ShopOrder> getShopOrders() {
        return shopOrders;
    }

    public void setShopOrders(Set<com.DIY.Detissue.entity.ShopOrder> shopOrders) {
        this.shopOrders = shopOrders;
    }

    public Set<com.DIY.Detissue.entity.UserAddress> getUserAddresses() {
        return userAddresses;
    }

    public void setUserAddresses(Set<com.DIY.Detissue.entity.UserAddress> userAddresses) {
        this.userAddresses = userAddresses;
    }

}
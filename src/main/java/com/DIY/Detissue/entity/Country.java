package com.DIY.Detissue.entity;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "country", schema = "detissue")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "shipping_price", precision = 10, scale = 2)
    private long shippingPrice;

    @OneToMany(mappedBy = "country")
    private Set<com.DIY.Detissue.entity.Address> addresses = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getShippingPrice() {
        return shippingPrice;
    }

    public void setShippingPrice(long shippingPrice) {
        this.shippingPrice = shippingPrice;
    }

    public Set<com.DIY.Detissue.entity.Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<com.DIY.Detissue.entity.Address> addresses) {
        this.addresses = addresses;
    }

}
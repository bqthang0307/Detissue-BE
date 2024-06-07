package com.DIY.Detissue.payload.response;

public class AddressResponse {
    private int id;
    private String street_address;
    private String town_city;
    private String state_country;
    private String country;
    private boolean isDefault;

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    public AddressResponse() {
    }

    public int getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStreet_address() {
        return street_address;
    }

    public void setStreet_address(String street_address) {
        this.street_address = street_address;
    }

    public String getTown_city() {
        return town_city;
    }

    public void setTown_city(String town_city) {
        this.town_city = town_city;
    }

    public String getState_country() {
        return state_country;
    }

    public void setState_country(String state_country) {
        this.state_country = state_country;
    }
}

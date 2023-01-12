package com.niit.jap.domain;

public class Address {
    private String addressType;
    private String streetName;
    private String city;
    private String pinCode;

    public Address() {
    }

    public Address(String addressType, String streetName, String city, String pinCode) {
        this.addressType = addressType;
        this.streetName = streetName;
        this.city = city;
        this.pinCode = pinCode;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }
}

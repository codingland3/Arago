package com.example.arago.Model;

public class Order {
    private String name;
    private String phone;
    private String address;
    private String errortype;

    public Order() {
    }

    public Order(String name, String phone, String address, String errortype) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.errortype = errortype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getErrortype() {
        return errortype;
    }

    public void setErrortype(String errortype) {
        this.errortype = errortype;
    }
}

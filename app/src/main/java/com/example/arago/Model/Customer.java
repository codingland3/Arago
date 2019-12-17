package com.example.arago.Model;

public class Customer {

    private int customer_images;
    private String customer_id, customer_name, customer_email, customer_pass, customer_address, customer_phone;

    public Customer() {

    }

    public Customer(int customer_images, String customer_id, String customer_name, String customer_email, String customer_pass, String customer_address, String customer_phone) {
        this.customer_images = customer_images;
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.customer_email = customer_email;
        this.customer_pass = customer_pass;
        this.customer_address = customer_address;
        this.customer_phone = customer_phone;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public int getCustomer_images() {
        return customer_images;
    }

    public void setCustomer_images(int customer_images) {
        this.customer_images = customer_images;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_email() {
        return customer_email;
    }

    public void setCustomer_email(String customer_email) {
        this.customer_email = customer_email;
    }

    public String getCustomer_pass() {
        return customer_pass;
    }

    public void setCustomer_pass(String customer_pass) {
        this.customer_pass = customer_pass;
    }

    public String getCustomer_address() {
        return customer_address;
    }

    public void setCustomer_address(String customer_address) {
        this.customer_address = customer_address;
    }

    public String getCustomer_phone() {
        return customer_phone;
    }

    public void setCustomer_phone(String customer_phone) {
        this.customer_phone = customer_phone;
    }
}

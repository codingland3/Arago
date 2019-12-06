package com.example.arago.Model;

import java.util.Date;

public class Bill {

    private Double bill_price;
    private int bill_id;
    private String bill_address, bill_errortype, bill_phone, bill_status;
    private Date bill_datetime;

    public Bill() {
    }

    public Bill(Double bill_price, int bill_id, String bill_address, String bill_errortype, String bill_phone, String bill_status, Date bill_datetime) {
        this.bill_price = bill_price;
        this.bill_id = bill_id;
        this.bill_address = bill_address;
        this.bill_errortype = bill_errortype;
        this.bill_phone = bill_phone;
        this.bill_status = bill_status;
        this.bill_datetime = bill_datetime;
    }

    public Double getBill_price() {
        return bill_price;
    }

    public void setBill_price(Double bill_price) {
        this.bill_price = bill_price;
    }

    public int getBill_id() {
        return bill_id;
    }

    public void setBill_id(int bill_id) {
        this.bill_id = bill_id;
    }

    public String getBill_address() {
        return bill_address;
    }

    public void setBill_address(String bill_address) {
        this.bill_address = bill_address;
    }

    public String getBill_errortype() {
        return bill_errortype;
    }

    public void setBill_errortype(String bill_errortype) {
        this.bill_errortype = bill_errortype;
    }

    public String getBill_phone() {
        return bill_phone;
    }

    public void setBill_phone(String bill_phone) {
        this.bill_phone = bill_phone;
    }

    public String getBill_status() {
        return bill_status;
    }

    public void setBill_status(String bill_status) {
        this.bill_status = bill_status;
    }

    public Date getBill_datetime() {
        return bill_datetime;
    }

    public void setBill_datetime(Date bill_datetime) {
        this.bill_datetime = bill_datetime;
    }
}

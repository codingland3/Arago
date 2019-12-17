package com.example.arago.Model;

public class PartnerHistory {

//    private int history_id;
    private String history_customer_name, history_customer_phone, history_customer_address, history_datetime, history_errortype, history_service_name, history_price;

    public PartnerHistory(){

    }

    public PartnerHistory( String history_service_name, String history_datetime, String history_customer_name, String history_customer_phone, String history_customer_address, String history_errortype, String history_price) {
        this.history_service_name = history_service_name;
        this.history_datetime = history_datetime;
        this.history_customer_name = history_customer_name;
        this.history_customer_phone = history_customer_phone;
        this.history_customer_address = history_customer_address;
        this.history_errortype = history_errortype;
        this.history_price = history_price;
    }

    public String getHistory_customer_name() {
        return history_customer_name;
    }

    public void setHistory_customer_name(String history_customer_name) {
        this.history_customer_name = history_customer_name;
    }

    public String getHistory_customer_phone() {
        return history_customer_phone;
    }

    public void setHistory_customer_phone(String history_customer_phone) {
        this.history_customer_phone = history_customer_phone;
    }

    public String getHistory_customer_address() {
        return history_customer_address;
    }

    public void setHistory_customer_address(String history_customer_address) {
        this.history_customer_address = history_customer_address;
    }

    public String getHistory_datetime() {
        return history_datetime;
    }

    public void setHistory_datetime(String history_datetime) {
        this.history_datetime = history_datetime;
    }

    public String getHistory_errortype() {
        return history_errortype;
    }

    public void setHistory_errortype(String history_errortype) {
        this.history_errortype = history_errortype;
    }

    public String getHistory_service_name() {
        return history_service_name;
    }

    public void setHistory_service_name(String history_service_name) {
        this.history_service_name = history_service_name;
    }

    public String getHistory_price() {
        return history_price;
    }

    public void setHistory_price(String history_price) {
        this.history_price = history_price;
    }
}

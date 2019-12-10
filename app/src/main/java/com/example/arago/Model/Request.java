package com.example.arago.Model;

import java.util.List;

public class Request {

    private int request_id, request_description_img;
    private String request_customer_name, request_customer_phone, request_customer_address, request_datetime, request_errortype, request_service_name;

    public Request(String customer_phone, String customer_name, String s, List<Order> cart) {
    }

    public Request( int request_description_img, String request_customer_name, String request_customer_phone, String request_customer_address, String request_datetime, String request_errortype, String request_service_name) {
        this.request_description_img = request_description_img;
        this.request_customer_name = request_customer_name;
        this.request_customer_phone = request_customer_phone;
        this.request_customer_address = request_customer_address;
        this.request_datetime = request_datetime;
        this.request_errortype = request_errortype;
        this.request_service_name = request_service_name;
    }

    public int getRequest_id() {
        return request_id;
    }

    public int getRequest_description_img() {
        return request_description_img;
    }

    public void setRequest_description_img(int request_description_img) {
        this.request_description_img = request_description_img;
    }

    public String getRequest_customer_name() {
        return request_customer_name;
    }

    public void setRequest_customer_name(String request_customer_name) {
        this.request_customer_name = request_customer_name;
    }

    public String getRequest_customer_phone() {
        return request_customer_phone;
    }

    public void setRequest_customer_phone(String request_customer_phone) {
        this.request_customer_phone = request_customer_phone;
    }

    public String getRequest_customer_address() {
        return request_customer_address;
    }

    public void setRequest_customer_address(String request_customer_address) {
        this.request_customer_address = request_customer_address;
    }

    public String getRequest_datetime() {
        return request_datetime;
    }

    public void setRequest_datetime(String request_datetime) {
        this.request_datetime = request_datetime;
    }

    public String getRequest_errortype() {
        return request_errortype;
    }

    public void setRequest_errortype(String request_errortype) {
        this.request_errortype = request_errortype;
    }

    public String getRequest_service_name() {
        return request_service_name;
    }

    public void setRequest_service_name(String request_service_name) {
        this.request_service_name = request_service_name;
    }
}

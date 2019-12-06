package com.example.arago.Model;

import java.util.Date;

public class Request {

    private int request_id, request_service_id;
    private String request_servicename, request_errortype, request_brand;
    private Date request_datetime;

    public Request() {
    }

    public Request(int request_id, int request_service_id, String request_errortype, String request_brand, Date request_datetime) {
        this.request_id = request_id;
        this.request_service_id = request_service_id;
        this.request_errortype = request_errortype;
        this.request_brand = request_brand;
        this.request_datetime = request_datetime;
    }

    public int getRequest_id() {
        return request_id;
    }

    public void setRequest_id(int request_id) {
        this.request_id = request_id;
    }

    public int getRequest_service_id() {
        return request_service_id;
    }

    public void setRequest_service_id(int request_service_id) {
        this.request_service_id = request_service_id;
    }

    public String getRequest_errortype() {
        return request_errortype;
    }

    public void setRequest_errortype(String request_errortype) {
        this.request_errortype = request_errortype;
    }

    public String getRequest_brand() {
        return request_brand;
    }

    public void setRequest_brand(String request_brand) {
        this.request_brand = request_brand;
    }

    public Date getRequest_datetime() {
        return request_datetime;
    }

    public void setRequest_datetime(Date request_datetime) {
        this.request_datetime = request_datetime;
    }
}

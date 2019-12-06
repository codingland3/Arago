package com.example.arago.Model;

public class Service {

    private int service_images;
    private String service_id, service_name;

    public Service(int service_images, String service_id, String service_name) {
        this.service_images = service_images;
        this.service_id = service_id;
        this.service_name = service_name;
    }

    public Service() {
    }

    public int getService_images() {
        return service_images;
    }

    public void setService_images(int service_images) {
        this.service_images = service_images;
    }

    public String getService_id() {
        return service_id;
    }

    public void setService_id(String service_id) {
        this.service_id = service_id;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }
}

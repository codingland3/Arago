package com.example.arago.USER.Model;

import java.util.Date;

public class Partner {

    private int partner_images, partner_id;
    private String partner_name, partner_email, partner_pass, partner_address, partner_phone, partner_sex, partner_cmnd;
    private Date partner_birthday;

    public Partner() {
    }

    public Partner(int partner_images, int partner_id, String partner_name, String partner_email, String partner_pass, String partner_address, String partner_phone, String partner_sex, String partner_cmnd, Date partner_birthday) {
        this.partner_images = partner_images;
        this.partner_id = partner_id;
        this.partner_name = partner_name;
        this.partner_email = partner_email;
        this.partner_pass = partner_pass;
        this.partner_address = partner_address;
        this.partner_phone = partner_phone;
        this.partner_sex = partner_sex;
        this.partner_cmnd = partner_cmnd;
        this.partner_birthday = partner_birthday;
    }

    public int getPartner_images() {
        return partner_images;
    }

    public void setPartner_images(int partner_images) {
        this.partner_images = partner_images;
    }

    public int getPartner_id() {
        return partner_id;
    }

    public void setPartner_id(int partner_id) {
        this.partner_id = partner_id;
    }

    public String getPartner_name() {
        return partner_name;
    }

    public void setPartner_name(String partner_name) {
        this.partner_name = partner_name;
    }

    public String getPartner_email() {
        return partner_email;
    }

    public void setPartner_email(String partner_email) {
        this.partner_email = partner_email;
    }

    public String getPartner_pass() {
        return partner_pass;
    }

    public void setPartner_pass(String partner_pass) {
        this.partner_pass = partner_pass;
    }

    public String getPartner_address() {
        return partner_address;
    }

    public void setPartner_address(String partner_address) {
        this.partner_address = partner_address;
    }

    public String getPartner_phone() {
        return partner_phone;
    }

    public void setPartner_phone(String partner_phone) {
        this.partner_phone = partner_phone;
    }

    public String getPartner_sex() {
        return partner_sex;
    }

    public void setPartner_sex(String partner_sex) {
        this.partner_sex = partner_sex;
    }

    public String getPartner_cmnd() {
        return partner_cmnd;
    }

    public void setPartner_cmnd(String partner_cmnd) {
        this.partner_cmnd = partner_cmnd;
    }

    public Date getPartner_birthday() {
        return partner_birthday;
    }

    public void setPartner_birthday(Date partner_birthday) {
        this.partner_birthday = partner_birthday;
    }
}

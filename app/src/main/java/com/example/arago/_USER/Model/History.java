package com.example.arago._USER.Model;

public class History {

    String Name, Date, Price;

    public History(String name, String date, String price) {
        Name = name;
        Date = date;
        Price = price;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }
}

package com.example.arago._USER.Model;

public class History {

    private String Name, Date, Price, CustomerID;

    public History() {
    }

    public History(String name, String date, String price, String customerID) {
        Name = name;
        Date = date;
        Price = price;
        CustomerID = customerID;
    }

    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String customerID) {
        CustomerID = customerID;
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

package com.example.fyp;

public class maintain {
    private String name;
    private String address;
    private String phone;
    private String id;
    private String time;
    private String daydate;
    private String month;
    private String order;
    private String amount;

    public maintain(){

    }

    public maintain(String name, String address, String phone, String id, String time, String daydate, String month, String order, String amount) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.id = id;
        this.time = time;
        this.daydate = daydate;
        this.month = month;
        this.order=order;
        this.amount=amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDaydate() {
        return daydate;
    }

    public void setDaydate(String daydate) {
        this.daydate = daydate;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getOrder() { return order; }

    public void setOrder(String order) {this.order=order;}

    public String getAmount() {return amount;}

    public void setAmount(String amount) {this.amount=amount;}

}


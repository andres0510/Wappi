package com.wappi.test.dto;

import java.util.List;

public class Client {

    private String user;
    private String password;
    private List<Coupon> coupons;
    private List<Order> orders;

    public Client(){
        user = "andres";
        password = "12345678";
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public List<Coupon> getCoupons() {
        return coupons;
    }

    public List<Order> getOrders() {
        return orders;
    }

}

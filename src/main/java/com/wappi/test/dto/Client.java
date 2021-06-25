package com.wappi.test.dto;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public class Client {

    private String user;
    private String password;
    private Coupon coupon;
    private List<Order> orders;

    public Client(){
        Faker faker = new Faker();
        user = faker.lorem().characters(6, 20);
        password = faker.lorem().characters(8, 14);
        coupon = new Coupon(faker.number().digits(10));
        orders = new ArrayList<>();
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    public void addOrder(Order order) {
        this.orders.add(order);
    }

}

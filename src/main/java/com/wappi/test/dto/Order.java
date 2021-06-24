package com.wappi.test.dto;

import java.util.Objects;

public class Order {

    private String date;
    private String description;
    private String price;
    private String delivery;
    private String shop;
    private String address;
    private boolean couponUsed;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isCouponUsed() {
        return couponUsed;
    }

    public void setCouponUsed(boolean couponUsed) {
        this.couponUsed = couponUsed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return couponUsed == order.couponUsed &&
                Objects.equals(date, order.date) &&
                Objects.equals(description, order.description) &&
                Objects.equals(price, order.price) &&
                Objects.equals(delivery, order.delivery) &&
                Objects.equals(shop, order.shop) &&
                Objects.equals(address, order.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, description, price, delivery, shop, address, couponUsed);
    }
}

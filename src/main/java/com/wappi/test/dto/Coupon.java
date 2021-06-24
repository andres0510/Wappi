package com.wappi.test.dto;

public class Coupon {

    private String code;
    private String expireDate;
    private String description;
    private int uses;

    public Coupon(String code, String expireDate, String description, int uses) {
        this.code = code;
        this.expireDate = expireDate;
        this.description = description;
        this.uses = uses;
    }

    public String getCode() {
        return code;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public String getDescription() {
        return description;
    }

    public int getUses() {
        return uses;
    }

    public void setUses(int uses) {
        this.uses = uses;
    }

}

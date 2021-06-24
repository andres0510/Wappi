package com.wappi.test.dto;

import com.wappi.test.helpers.Dictionary;
import com.wappi.test.helpers.PropertiesFile;

public class Coupon {

    private String code;
    private String expireDate;
    private String description;
    private int uses;

    public Coupon(){
    }

    public Coupon(String code){
        this.code = code;
        PropertiesFile properties = new PropertiesFile(Dictionary.PropertiesPath.DEFAULT);
        this.uses = Integer.parseInt(properties.getProperty("defult.coupon.uses"));
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUses() {
        return uses;
    }

    public void setUses(int uses) {
        this.uses = uses;
    }

}

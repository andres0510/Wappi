package com.wappi.test.page;

import com.wappi.test.action.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PageCoupons {

    public PageCoupons () {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    //----------------------------------------------------------------------------------------------------------------->
    //---------- LOCATORS --------------------------------------------------------------------------------------------->
    //----------------------------------------------------------------------------------------------------------------->

    @FindBy(xpath = "//tr[contains(@id,'coupon-')]")
    private List<WebElement> rowCoupons;

    @FindBy(xpath = "//tr[contains(@id,'coupon-')]/td[1]")
    private List<WebElement> columnCouponCodes;

    @FindBy(xpath = "//tr[contains(@id,'coupon-')]/td[2]")
    private List<WebElement> columnCouponExpireDates;

    @FindBy(xpath = "//tr[contains(@id,'coupon-')]/td[3]")
    private List<WebElement> columnCouponDescriptions;

    @FindBy(xpath = "//tr[contains(@id,'coupon-')]/td[4]")
    private List<WebElement> columnCouponUses;

    //----------------------------------------------------------------------------------------------------------------->
    //---------- FUNCTIONS -------------------------------------------------------------------------------------------->
    //----------------------------------------------------------------------------------------------------------------->

    public int getCouponsCount(){
        return rowCoupons.size();
    }

    public String getCouponCode(int index) {
        return DriverManager.getText(columnCouponCodes.get(index));
    }

    public String getCouponExpireDate(int index) {
        return DriverManager.getText(columnCouponExpireDates.get(index));
    }

    public String getCouponDescription(int index) {
        return DriverManager.getText(columnCouponDescriptions.get(index));
    }

    public String getCouponUses(int index) {
        return DriverManager.getText(columnCouponUses.get(index));
    }

}

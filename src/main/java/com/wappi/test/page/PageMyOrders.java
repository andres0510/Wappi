package com.wappi.test.page;

import com.wappi.test.action.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PageMyOrders {

    public PageMyOrders () {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    //----------------------------------------------------------------------------------------------------------------->
    //---------- LOCATORS --------------------------------------------------------------------------------------------->
    //----------------------------------------------------------------------------------------------------------------->

    @FindBy(xpath = "//tr[contains(@id,'order-')]")
    private List<WebElement> rowOrders;

    @FindBy(xpath = "//tr[contains(@id,'order-')]/td[1]")
    private List<WebElement> columnOrderDates;

    @FindBy(xpath = "//tr[contains(@id,'order-')]/td[2]")
    private List<WebElement> columnOrderDescriptions;

    @FindBy(xpath = "//tr[contains(@id,'order-')]/td[3]")
    private List<WebElement> columnOrderPrices;

    @FindBy(xpath = "//tr[contains(@id,'order-')]/td[4]")
    private List<WebElement> columnOrderDeliveries;

    @FindBy(xpath = "//tr[contains(@id,'order-')]/td[5]")
    private List<WebElement> columnOrderStores;

    @FindBy(xpath = "//tr[contains(@id,'order-')]/td[6]")
    private List<WebElement> columnOrderAddress;

    @FindBy(xpath = "//tr[contains(@id,'order-')]/td[7]")
    private List<WebElement> columnOrderCoupons;

    //----------------------------------------------------------------------------------------------------------------->
    //---------- FUNCTIONS -------------------------------------------------------------------------------------------->
    //----------------------------------------------------------------------------------------------------------------->

    public int getOrdersCount(){
        return rowOrders.size();
    }

    public String getOrderDateAtIndex(int index) {
        return DriverManager.getText(columnOrderDates.get(index));
    }

    public String getOrderDescriptionAtIndex(int index) {
        return DriverManager.getText(columnOrderDescriptions.get(index));
    }

    public String getOrderPriceAtIndex(int index) {
        return DriverManager.getText(columnOrderPrices.get(index));
    }

    public String getOrderDeliveryAtIndex(int index) {
        return DriverManager.getText(columnOrderDeliveries.get(index));
    }

    public String getOrderStoreAtIndex(int index) {
        return DriverManager.getText(columnOrderStores.get(index));
    }

    public String getOrderAddressAtIndex(int index) {
        return DriverManager.getText(columnOrderAddress.get(index));
    }

    public String getOrderCouponUsedAtIndex(int index) {
        return DriverManager.getText(columnOrderCoupons.get(index));
    }

}

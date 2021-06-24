package com.wappi.test.page;

import com.wappi.test.action.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PageHome {

    public PageHome () {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    //----------------------------------------------------------------------------------------------------------------->
    //---------- LOCATORS --------------------------------------------------------------------------------------------->
    //----------------------------------------------------------------------------------------------------------------->

    @FindBy(xpath = "//li/a[text()='Inicio']")
    private WebElement tabHome;

    @FindBy(xpath = "//li/a[text()='Cupones']")
    private WebElement tabCoupons;

    @FindBy(xpath = "//li/a[text()='Mis pedidos']")
    private WebElement tabMyOrders;

    @FindBy(xpath = "//li/a[text()='Información personal']")
    private WebElement tabPersonalInfo;

    @FindBy(id = "welcome-coupon")
    private WebElement buttonWelcomeCoupon;

    @FindBy(xpath = "//*[@id='coupon-modal']//*[contains(@class,'close')]")
    private WebElement buttonCloseCouponModal;

    @FindBy(id = "coupon-code")
    private WebElement labelCouponCode;

    @FindBy(xpath = "//tr[contains(@id,'offer-')]")
    private List<WebElement> rowOffers;

    @FindBy(xpath = "//tr[contains(@id,'offer-')]/td[1]")
    private List<WebElement> columnOfferDates;

    @FindBy(xpath = "//tr[contains(@id,'offer-')]/td[2]")
    private List<WebElement> columnOfferDescriptions;

    @FindBy(xpath = "//tr[contains(@id,'offer-')]/td[3]")
    private List<WebElement> columnOfferPrices;

    @FindBy(xpath = "//tr[contains(@id, 'offer-')]/td[4]")
    private List<WebElement> columnOfferDeliveries;

    @FindBy(xpath = "//tr[contains(@id, 'offer-')]/td[5]")
    private List<WebElement> columnOfferStores;

    @FindBy(xpath = "//tr[contains(@id, 'offer-')]/td[6]")
    private List<WebElement> columnOfferAddress;

    @FindBy(xpath = "//tr[contains(@id,'offer-')]//button[text()='Pedir']")
    private List<WebElement> columnOfferOrderButtons;

    @FindBy(xpath = "//*[@id='offer-modal']//*[contains(@class,'offer-description')]")
    private WebElement labelModalOfferDescription;

    @FindBy(xpath = "//*[@id='offer-modal']//*[contains(@class,'offer-price')]")
    private WebElement labelModalOfferPrice;

    @FindBy(xpath = "//*[@id='offer-modal']//*[contains(@class,'material-icons') and text()='date_range']/parent::*")
    private WebElement labelModalOfferDate;

    @FindBy(xpath = "//*[@id='offer-modal']//*[contains(@class,'material-icons') and text()='local_shipping']/parent::*")
    private WebElement labelModalOfferDelivery;

    @FindBy(xpath = "//*[@id='offer-modal']//*[contains(@class,'material-icons') and text()='business']/parent::*")
    private WebElement labelModalOfferStore;

    @FindBy(xpath = "//*[@id='offer-modal']//*[contains(@class,'material-icons') and text()='location_on']/parent::*")
    private WebElement labelModalOfferAddress;

    @FindBy(id = "coupon")
    private WebElement inputCoupon;

    @FindBy(id = "e-coupon")
    private WebElement labelCouponError;

    @FindBy(id = "order-confirm")
    private WebElement buttonConfirmOrder;

    @FindBy(xpath = "//*[@id='offer-modal']//*[contains(@class,'close')]")
    private WebElement buttonCloseBuyingModal;

    @FindBy(id = "confirmation-modal")
    private WebElement modalConfirmation;

    @FindBy(xpath = "//*[@id='confirmation-modal']//*[contains(@class,'close')]")
    private WebElement buttonCloseConfirmationModal;


    //----------------------------------------------------------------------------------------------------------------->
    //---------- FUNCTIONS -------------------------------------------------------------------------------------------->
    //----------------------------------------------------------------------------------------------------------------->

    public void clickTabHome() {
        DriverManager.click(tabHome);
    }

    public void clickTabCoupons() {
        DriverManager.click(tabCoupons);
    }

    public void clickTabMyOrders() {
        DriverManager.click(tabMyOrders);
    }

    public void clickTabPersonalInfo() {
        DriverManager.click(tabPersonalInfo);
    }

    public void clickGetWelcomeCoupon() {
        DriverManager.click(buttonWelcomeCoupon);
    }

    public boolean isVisibleButtonWelcomeCupoun(){
        return DriverManager.isVisible(buttonWelcomeCoupon);
    }

    public String getCouponCode(){
        return DriverManager.getText(labelCouponCode);
    }

    public void clickCloseCouponModal(){
        DriverManager.click(buttonCloseCouponModal);
    }

    public int getOffersCount(){
        return rowOffers.size();
    }

    public String getOfferDateAtIndex(int index) {
        return DriverManager.getText(columnOfferDates.get(index));
    }

    public String getOfferDescriptionAtIndex(int index) {
        return DriverManager.getText(columnOfferDescriptions.get(index));
    }

    public String getOfferPriceAtIndex(int index) {
        return DriverManager.getText(columnOfferPrices.get(index));
    }

    public String getOfferDeliveryAtIndex(int index) {
        return DriverManager.getText(columnOfferDeliveries.get(index));
    }

    public String getOfferStoreAtIndex(int index) {
        return DriverManager.getText(columnOfferStores.get(index));
    }

    public String getOfferAddressAtIndex(int index) {
        return DriverManager.getText(columnOfferAddress.get(index));
    }

    public void clickOrderAtIndex(int index) {
        DriverManager.click(columnOfferOrderButtons.get(index));
    }

    public String getOfferDescriptionInModal() {
        return DriverManager.getText(labelModalOfferDescription);
    }

    public String getOfferPriceInModal() {
        return DriverManager.getText(labelModalOfferPrice);
    }

    public String getOfferDateInModal() {
        return DriverManager.getText(labelModalOfferDate);
    }

    public String getOfferDeliveryInModal() {
        return DriverManager.getText(labelModalOfferDelivery);
    }

    public String getOfferStoreInModal() {
        return DriverManager.getText(labelModalOfferStore);
    }

    public String getOfferAddressInModal() {
        return DriverManager.getText(labelModalOfferAddress);
    }

    public void writeCoupon(String coupon) {
        DriverManager.sendText(inputCoupon, coupon);
    }

    public boolean isVisibleCouponError() {
        return DriverManager.isVisible(labelCouponError);
    }

    public void clickConfirmOrder() {
        DriverManager.click(buttonConfirmOrder);
    }

    public void clickCloseOfferModal() {
        DriverManager.click(buttonCloseBuyingModal);
    }

    public boolean isVisibleConfirmationModal() {
        return DriverManager.isVisible(modalConfirmation);
    }

    public void clickCloseConfirmationModal(){
        DriverManager.click(buttonCloseConfirmationModal);
    }

}

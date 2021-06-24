package com.wappi.test.page;

import com.wappi.test.action.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

    @FindBy(xpath = "//li/a[text()='Mis pedidos']]")
    private WebElement tabMyOrders;

    @FindBy(xpath = "//li/a[text()='Información personal']")
    private WebElement tabPersonalInfo;

    //----------------------------------------------------------------------------------------------------------------->
    //---------- FUNCTIONS -------------------------------------------------------------------------------------------->
    //----------------------------------------------------------------------------------------------------------------->

    public void clickTabPersonalInfo(){
        DriverManager.click(tabPersonalInfo);
    }

}

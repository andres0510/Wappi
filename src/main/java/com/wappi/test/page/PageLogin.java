package com.wappi.test.page;

import com.wappi.test.action.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageLogin {

    public PageLogin () {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    //----------------------------------------------------------------------------------------------------------------->
    //---------- LOCATORS --------------------------------------------------------------------------------------------->
    //----------------------------------------------------------------------------------------------------------------->

    @FindBy(id = "username")
    private WebElement inputUser;

    @FindBy(id = "password")
    private WebElement inputPassword;

    @FindBy(id = "button-login")
    private WebElement buttonLogin;

    //----------------------------------------------------------------------------------------------------------------->
    //---------- FUNCTIONS -------------------------------------------------------------------------------------------->
    //----------------------------------------------------------------------------------------------------------------->

    public void writeUser(String user) {
        DriverManager.sendText(inputUser, user);
    }

    public void writePassword(String password) {
        DriverManager.sendText(inputPassword, password);
    }

    public void clickLoginButton() {
        DriverManager.click(buttonLogin);
    }

}

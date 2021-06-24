package com.wappi.test.page;

import com.wappi.test.action.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PagePersonalInfo {

    public PagePersonalInfo () {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    //----------------------------------------------------------------------------------------------------------------->
    //---------- LOCATORS --------------------------------------------------------------------------------------------->
    //----------------------------------------------------------------------------------------------------------------->

    @FindBy(xpath = "//input[@id='image']")
    private WebElement inputAvatar;

    @FindBy(id = "name")
    private WebElement inputName;

    @FindBy(id = "lastName")
    private WebElement inputLastname;

    @FindBy(id = "bornDate")
    private WebElement inputBirthdate;

    @FindBy(id = "country")
    private WebElement selectCountry;

    @FindBy(id = "M")
    private WebElement radioMaleGender;

    @FindBy(id = "F")
    private WebElement radioFemaleGender;

    @FindBy(id = "save-profile")
    private WebElement buttonSave;

    @FindBy(id = "confirmation-modal")
    private WebElement modalConfirmation;

    @FindBy(id = "e-image")
    private WebElement labelImageError;

    @FindBy(id = "e-name")
    private WebElement labelNameError;

    @FindBy(id = "e-lastName")
    private WebElement labelLastnameError;

    @FindBy(id = "e-bornDate")
    private WebElement labelBirthdateError;

    @FindBy(id = "e-country")
    private WebElement labelCountryError;

    //----------------------------------------------------------------------------------------------------------------->
    //---------- FUNCTIONS -------------------------------------------------------------------------------------------->
    //----------------------------------------------------------------------------------------------------------------->

    public void uploadAvatar(String imagePath) {
        DriverManager.sendText(inputAvatar, imagePath);
    }

    public void writeFirstName(String name) {
        deleteFirstName();
        DriverManager.sendText(inputName, name);
    }

    public void deleteFirstName() {
        DriverManager.clearText(inputName);
    }

    public void writeLastName(String lastname) {
        deleteLastName();
        DriverManager.sendText(inputLastname, lastname);
    }

    public void deleteLastName() {
        DriverManager.clearText(inputLastname);
    }

    public void writeBirthdate(String birthdate) {
        deleteBirthdate();
        DriverManager.sendText(inputBirthdate, birthdate);
    }

    public void deleteBirthdate(){
        DriverManager.clearText(inputBirthdate);
    }

    public void chooseCountry(String country) {
        DriverManager.selectOption(selectCountry, country);
    }

    public void chooseMaleGender() {
        DriverManager.click(radioMaleGender);
    }

    public void chooseFemaleGender() {
        DriverManager.click(radioFemaleGender);
    }

    public void clickSaveButton() {
        DriverManager.click(buttonSave);
    }

    public boolean isVisibleConfirmationModal() {
        return DriverManager.isVisible(modalConfirmation);
    }

    public boolean isVisibleAvatarError() {
        return DriverManager.isVisible(labelImageError);
    }

    public boolean isVisibleNameError() {
        return DriverManager.isVisible(labelNameError);
    }

    public boolean isVisibleLastnameError() {
        return DriverManager.isVisible(labelLastnameError);
    }

    public boolean isVisibleBirthdateError() {
        return DriverManager.isVisible(labelBirthdateError);
    }

    public boolean isVisibleCountryError() {
        return DriverManager.isVisible(labelCountryError);
    }

}

package com.wappi.test.controller;

import com.github.javafaker.Faker;
import com.wappi.test.action.DriverManager;
import com.wappi.test.dto.Client;
import com.wappi.test.helpers.Dictionary;
import com.wappi.test.helpers.PropertiesFile;
import com.wappi.test.helpers.Report;
import com.wappi.test.helpers.Utilities;
import com.wappi.test.page.PageHome;
import com.wappi.test.page.PageLogin;
import com.wappi.test.page.PagePersonalInfo;

public class WappiController {

    private WappiController(){}

    //----------------------------------------------------------------------------------------------------------------->
    //---------- INIT & LOGIN ----------------------------------------------------------------------------------------->
    //----------------------------------------------------------------------------------------------------------------->

    public static void openWappi() {
        PropertiesFile properties = new PropertiesFile(Dictionary.PropertiesPath.DEFAULT);
        String wappiUrl = properties.getProperty("wappi.url");
        DriverManager.open(wappiUrl);
        Report.reportInfo(String.format(Dictionary.Messages.SUCCESS, "Open Wappi app"));
    }

    public static void login(Client client){
        PageLogin pageLogin = new PageLogin();
        pageLogin.writeUser(client.getUser());
        pageLogin.writePassword(client.getPassword());
        pageLogin.clickLoginButton();
        Report.reportInfo(String.format(Dictionary.Messages.SUCCESS, "Login into Wappi"));
    }

    //----------------------------------------------------------------------------------------------------------------->
    //---------- PERSONAL INFO ---------------------------------------------------------------------------------------->
    //----------------------------------------------------------------------------------------------------------------->

    public static void updateCompletePersonalInfo() {
        goToPersonalInfoView();
        PagePersonalInfo pagePersonalInfo = new PagePersonalInfo();
        fillPersonalInfo(pagePersonalInfo, true);
        pagePersonalInfo.clickSaveButton();
        Report.reportInfo(String.format(Dictionary.Messages.SUCCESS, "Save new personal info"));
    }

    public static void updateUncompletePersonalInfo(String missingInfo) {
        goToPersonalInfoView();
        PagePersonalInfo pagePersonalInfo = new PagePersonalInfo();
        boolean uploadAvatar = !(missingInfo.equals(Dictionary.MissingInfo.AVATAR));
        fillPersonalInfo(pagePersonalInfo, uploadAvatar);
        deleteInfo(pagePersonalInfo, missingInfo);
        pagePersonalInfo.clickSaveButton();
        Report.reportInfo(String.format(Dictionary.Messages.SUCCESS, "Tried updating personal info"));
    }

    private static void goToPersonalInfoView() {
        PageHome pageHome = new PageHome();
        pageHome.clickTabPersonalInfo();
        Report.reportInfo(String.format(Dictionary.Messages.SUCCESS, "Enter to personal info view"));
    }

    private static void fillPersonalInfo(PagePersonalInfo pagePersonalInfo, boolean uploadAvatar){
        Faker faker = new Faker();
        if (uploadAvatar) {
            pagePersonalInfo.uploadAvatar(Dictionary.FilesPath.AVATAR);
        }
        pagePersonalInfo.writeFirstName(faker.name().firstName());
        pagePersonalInfo.writeLastName(faker.name().lastName());
        pagePersonalInfo.writeBirthdate(Utilities.getRandomBirthdate(Dictionary.DateFormat.DD_MM_YYYY));
        String country = (String) Utilities.getRandomValueIntoList(Dictionary.Country.getCountries());
        pagePersonalInfo.chooseCountry(country);
        String gender = (String) Utilities.getRandomValueIntoList(Dictionary.Gender.getGenders());
        if (gender.equals(Dictionary.Gender.MASCULINO)) {
            pagePersonalInfo.chooseMaleGender();
        } else {
            pagePersonalInfo.chooseFemaleGender();
        }
        Report.reportInfo(String.format(Dictionary.Messages.SUCCESS, "Fill personal info form"));
    }

    private static void deleteInfo(PagePersonalInfo pagePersonalInfo, String missingInfo) {
        switch (missingInfo) {
            case Dictionary.MissingInfo.AVATAR:
                break;
            case Dictionary.MissingInfo.FIRSTNAME:
                pagePersonalInfo.deleteFirstName();
                break;
            case Dictionary.MissingInfo.LASTNAME:
                pagePersonalInfo.deleteLastName();
                break;
            case Dictionary.MissingInfo.BIRTHDATE:
                pagePersonalInfo.deleteBirthdate();
                break;
            case Dictionary.MissingInfo.COUNTRY:
                pagePersonalInfo.chooseCountry(Dictionary.Country.DEFAULT_COUNTRY_OPTION);
                break;
            default:
                Report.reportFail(String.format("Missing info '%s' not defined", missingInfo));
                break;
        }
        goToPersonalInfoView();
        Report.reportInfo(String.format(Dictionary.Messages.SUCCESS, "Delete missing personal info from form"));
    }

    public static boolean validateConfirmationModal() {
        PagePersonalInfo pagePersonalInfo = new PagePersonalInfo();
        return pagePersonalInfo.isVisibleConfirmationModal();
    }

    public static boolean validateMissingInfoWarning(String missingInfo) {
        PagePersonalInfo pagePersonalInfo = new PagePersonalInfo();
        switch (missingInfo) {
            case Dictionary.MissingInfo.AVATAR:
                return pagePersonalInfo.isVisibleAvatarError();
            case Dictionary.MissingInfo.FIRSTNAME:
                return pagePersonalInfo.isVisibleNameError();
            case Dictionary.MissingInfo.LASTNAME:
                return pagePersonalInfo.isVisibleLastnameError();
            case Dictionary.MissingInfo.BIRTHDATE:
                return pagePersonalInfo.isVisibleBirthdateError();
            case Dictionary.MissingInfo.COUNTRY:
                return pagePersonalInfo.isVisibleCountryError();
            default:
                Report.reportFail(String.format("Missing info '%s' not defined", missingInfo));
                return false;
        }
    }

    //----------------------------------------------------------------------------------------------------------------->
    //----------------------------------------------------------------------------------------------------------------->
    //----------------------------------------------------------------------------------------------------------------->

    public static void wait2() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

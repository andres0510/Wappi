package com.wappi.test.controller;

import com.github.javafaker.Faker;
import com.wappi.test.action.DriverManager;
import com.wappi.test.dto.Client;
import com.wappi.test.dto.Coupon;
import com.wappi.test.dto.Order;
import com.wappi.test.helpers.Dictionary;
import com.wappi.test.helpers.PropertiesFile;
import com.wappi.test.helpers.Report;
import com.wappi.test.helpers.Utilities;
import com.wappi.test.page.*;

import java.util.ArrayList;
import java.util.List;

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

    public static boolean validateConfirmationModalForInfoUpdate() {
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
    //---------- SHOPPING --------------------------------------------------------------------------------------------->
    //----------------------------------------------------------------------------------------------------------------->

    public static Order buyArticle(boolean useCoupon, Coupon coupon) {
        PageHome pageHome = new PageHome();
        Order tableOrder = selectRandomOffer(pageHome);
        Report.reportInfo(String.format(Dictionary.Messages.SUCCESS, "Select random offer from table"));
        tableOrder.setCouponUsed(useCoupon);
        Order modalOrder = getOfferInfoFromModal(pageHome);
        modalOrder.setCouponUsed(useCoupon);
        Report.reportInfo(String.format(Dictionary.Messages.SUCCESS, "Get offer info from modal"));
        DataController.compareOrders(tableOrder, modalOrder);
        Report.reportInfo(String.format(Dictionary.Messages.SUCCESS, "Compare table offer vs modal offer"));
        if (useCoupon) {
            pageHome.writeCoupon(coupon.getCode());
        }
        pageHome.clickConfirmOrder();
        return modalOrder;
    }

    private static void goToHomeView() {
        PageHome pageHome = new PageHome();
        pageHome.clickTabHome();
        Report.reportInfo(String.format(Dictionary.Messages.SUCCESS, "Enter to home view"));
    }

    private static Order selectRandomOffer(PageHome pageHome) {
        Order order = new Order();
        int offersCount = pageHome.getOffersCount();
        if (offersCount>0) {
            int index = Utilities.selectRandomValue(0, offersCount);
            order.setDate(pageHome.getOfferDateAtIndex(index));
            order.setDescription(pageHome.getOfferDescriptionAtIndex(index));
            order.setPrice(pageHome.getOfferPriceAtIndex(index));
            order.setDelivery(pageHome.getOfferDeliveryAtIndex(index));
            order.setShop(pageHome.getOfferStoreAtIndex(index));
            order.setAddress(pageHome.getOfferAddressAtIndex(index));
            pageHome.clickOrderAtIndex(index);
        } else {
            Report.reportFail("There are not offers to order");
        }
        return order;
    }

    private static Order getOfferInfoFromModal(PageHome pageHome) {
        Order order = new Order();
        order.setDescription(pageHome.getOfferDescriptionInModal());
        order.setPrice(pageHome.getOfferPriceInModal());
        String date = pageHome.getOfferDateInModal().split("\n")[1];
        order.setDate(date);
        String delivery = pageHome.getOfferDeliveryInModal().split("\n")[1];
        order.setDelivery(delivery);
        String shop = pageHome.getOfferStoreInModal().split("\n")[1];
        order.setShop(shop);
        String address = pageHome.getOfferAddressInModal().split("\n")[1];
        order.setAddress(address);
        return order;
    }

    public static boolean validateConfirmationModalForOrder(){
        PageHome pageHome = new PageHome();
        boolean modalVisible = pageHome.isVisibleConfirmationModal();
        pageHome.clickCloseConfirmationModal();
        return modalVisible;
    }

    public static void validateErrorBuyingArticle(){
        PageHome pageHome = new PageHome();
        if (!pageHome.isVisibleCouponError()) {
            Report.reportFail("Coupon warning was not displayed");
        }
        pageHome.clickCloseOfferModal();
    }

    //----------------------------------------------------------------------------------------------------------------->
    //---------- HISTORY ---------------------------------------------------------------------------------------------->
    //----------------------------------------------------------------------------------------------------------------->

    public static List<Order> getMyOrders() {
        PageHome pageHome = new PageHome();
        pageHome.clickTabMyOrders();
        Report.reportInfo(String.format(Dictionary.Messages.SUCCESS, "Enter to my orders view"));
        PageMyOrders pageMyOrders = new PageMyOrders();
        int ordersCount = pageMyOrders.getOrdersCount();
        List<Order> orders = new ArrayList<>();
        for (int i=0; i<ordersCount; i++) {
            orders.add(getOrderAtIndex(pageMyOrders, i));
        }
        Report.reportInfo(String.format(Dictionary.Messages.SUCCESS, "Get orders history"));
        goToHomeView();
        return orders;
    }

    private static Order getOrderAtIndex(PageMyOrders pageMyOrders, int index) {
        Order order = new Order();
        order.setDate(pageMyOrders.getOrderDateAtIndex(index));
        order.setDescription(pageMyOrders.getOrderDescriptionAtIndex(index));
        order.setPrice(pageMyOrders.getOrderPriceAtIndex(index));
        order.setDelivery(pageMyOrders.getOrderDeliveryAtIndex(index));
        order.setShop(pageMyOrders.getOrderStoreAtIndex(index));
        order.setAddress(pageMyOrders.getOrderAddressAtIndex(index));
        String couponUsed = pageMyOrders.getOrderCouponUsedAtIndex(index);
        order.setCouponUsed(couponUsed.equalsIgnoreCase("Si"));
        return order;
    }

    //----------------------------------------------------------------------------------------------------------------->
    //---------- COUPONS ---------------------------------------------------------------------------------------------->
    //----------------------------------------------------------------------------------------------------------------->

    public static Coupon claimWelcomeCoupon(){
        PageHome pageHome = new PageHome();
        pageHome.clickGetWelcomeCoupon();
        String couponCode = pageHome.getCouponCode();
        pageHome.clickCloseCouponModal();
        Report.reportInfo(String.format(Dictionary.Messages.SUCCESS, "Claim welcome coupon"));
        return new Coupon(couponCode);
    }

    public static List<Coupon> getMyCoupons() {
        goToCouponsView();
        PageCoupons pageCoupons = new PageCoupons();
        int couponsCount = pageCoupons.getCouponsCount();
        List<Coupon> coupons = new ArrayList<>();
        for (int i=0; i<couponsCount; i++) {
            coupons.add(getCouponAtIndex(pageCoupons, i));
        }
        Report.reportInfo(String.format(Dictionary.Messages.SUCCESS, "Get coupons list"));
        goToHomeView();
        return coupons;
    }

    public static boolean validateNoNewCupons(){
        PageHome pageHome = new PageHome();
        return pageHome.isVisibleButtonWelcomeCupoun();
    }

    private static void goToCouponsView() {
        PageHome pageHome = new PageHome();
        pageHome.clickTabCoupons();
        Report.reportInfo(String.format(Dictionary.Messages.SUCCESS, "Enter to coupons view"));
    }

    private static Coupon getCouponAtIndex(PageCoupons pageCoupons, int index) {
        Coupon coupon = new Coupon();
        coupon.setCode(pageCoupons.getCouponCode(index));
        coupon.setExpireDate(pageCoupons.getCouponExpireDate(index));
        coupon.setDescription(pageCoupons.getCouponDescription(index));
        int uses = Integer.parseInt(pageCoupons.getCouponUses(index));
        coupon.setUses(uses);
        return coupon;
    }

}

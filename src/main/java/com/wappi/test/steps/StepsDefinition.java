package com.wappi.test.steps;

import com.wappi.test.action.DriverManager;
import com.wappi.test.controller.DataController;
import com.wappi.test.controller.WappiController;
import com.wappi.test.dto.Client;
import com.wappi.test.dto.Coupon;
import com.wappi.test.dto.Order;
import com.wappi.test.helpers.Dictionary;
import com.wappi.test.helpers.Report;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.util.List;

public class StepsDefinition {

    private Client testClient;

    //----------------------------------------------------------------------------------------------------------------->
    //---------- BEFORE & AFTER --------------------------------------------------------------------------------------->
    //----------------------------------------------------------------------------------------------------------------->

    @Before
    public static void setUp(){
        Report.initReport();
        DriverManager.initDriverManager();
    }

    @After
    public static void tearDown(){
        DriverManager.finish();
    }

    //----------------------------------------------------------------------------------------------------------------->
    //---------- STEPS ------------------------------------------------------------------------------------------------>
    //----------------------------------------------------------------------------------------------------------------->

    @Given("^an user from Wappi app$")
    public void anUserFromWappiApp(){
        testClient = new Client();
    }

    @When("^I make the login$")
    public void iMakeTheLogin(){
        WappiController.openWappi();
        WappiController.login(testClient);
    }

    @And("^update user personal info$")
    public void updateUserPersonalInfo(){
        WappiController.updateCompletePersonalInfo();
    }

    @And("^update all personal info except (.*?)$")
    public void updateAllPersonalInfoExcept(String missingInfo) {
        WappiController.updateUncompletePersonalInfo(missingInfo);
    }

    @Then("^the new information is saved$")
    public void theNewInformationIsSaved(){
        Assert.assertTrue(
            Dictionary.Messages.CONFIRMATION_MODAL_NOT_VISIBLE,
            WappiController.validateConfirmationModalForInfoUpdate()
        );
    }

    @Then("^an alert is displayed for (.*?) input$")
    public void anAlertIsDisplayedForInput(String missingInfo) {
        Assert.assertTrue(
            "Warning not visible for missing property",
            WappiController.validateMissingInfoWarning(missingInfo)
        );
        Assert.assertFalse(
            Dictionary.Messages.CONFIRMATION_MODAL_VISIBLE,
            WappiController.validateConfirmationModalForInfoUpdate()
        );
    }

    @And("^I purchase 1 article without using coupon$")
    public void iPurchaseOneArticleWithoutUsingCoupon() {
        Order order = WappiController.buyArticle(false, testClient.getCoupon());
        testClient.addOrder(order);
    }

    @Then("^the order is saved$")
    public void theOrderIsSaved() {
        Assert.assertTrue(
            Dictionary.Messages.CONFIRMATION_MODAL_NOT_VISIBLE,
            WappiController.validateConfirmationModalForOrder()
        );
        List<Order> ordersHistory = WappiController.getMyOrders();
        DataController.compareOrdersList(testClient.getOrders(), ordersHistory);
    }

    @And("^claim the welcome coupon$")
    public void claimTheWelcomeCoupon(){
        Coupon coupon = WappiController.claimWelcomeCoupon();
        Assert.assertFalse(
            "Welcome coupon can be claimed just once",
            WappiController.validateNoNewCupons()
        );
        List<Coupon> couponList = WappiController.getMyCoupons();
        DataController.validateWelcomeCoupon(coupon, couponList);
        testClient.setCoupon(couponList.get(0));
    }

    @And("^I purchase 1 article using the coupon$")
    public void iPurchaseOneArticleUsingCoupon(){
        Order order = WappiController.buyArticle(true, testClient.getCoupon());
        testClient.addOrder(order);
        DataController.updateCouponUses(testClient.getCoupon());
    }

    @And("^the coupon uses are updated$")
    public void theCouponUsesAreUpdated(){
        List<Coupon> couponList = WappiController.getMyCoupons();
        DataController.validateCouponsUpdated(testClient.getCoupon(), couponList);
    }

    @And("^I try to purchase 1 article using the coupon$")
    public void iTryToPurchaseOneArticleUsingTheCoupon(){
        WappiController.buyArticle(true, testClient.getCoupon());
    }

    @And("^an alert is displayed saying the coupon is invalid$")
    public void anAlertIsDisplayedSayingTheCouponIsInvald() {
        WappiController.validateErrorBuyingArticle();
    }

    @And("^the order is not saved$")
    public void theOrderIsNotSaved() {
        List<Order> ordersHistory = WappiController.getMyOrders();
        DataController.compareOrdersList(testClient.getOrders(), ordersHistory);
    }

    @And("^I can not claim a new coupon$")
    public void iCanNotClaimANewCoupon(){
        Assert.assertFalse(
            "Welcome coupon can be claimed just once",
            WappiController.validateNoNewCupons()
        );
    }

}

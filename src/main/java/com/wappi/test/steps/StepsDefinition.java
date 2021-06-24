package com.wappi.test.steps;

import com.wappi.test.action.DriverManager;
import com.wappi.test.controller.WappiController;
import com.wappi.test.dto.Client;
import com.wappi.test.helpers.Dictionary;
import com.wappi.test.helpers.PropertiesFile;
import com.wappi.test.helpers.Report;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

public class StepsDefinition {

    Client testClient;

    private static PropertiesFile properties;

    //----------------------------------------------------------------------------------------------------------------->
    //---------- BEFORE & AFTER --------------------------------------------------------------------------------------->
    //----------------------------------------------------------------------------------------------------------------->

    @Before
    public static void setUp(){
        Report.initReport();
        properties = new PropertiesFile(Dictionary.PropertiesPath.DEFAULT);
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
            "Confirmation modal not visible",
            WappiController.validateConfirmationModal()
        );
    }

    @Then("^an alert is displayed for (.*?) input$")
    public void anAlertIsDisplayedForInput(String missingInfo) {
        Assert.assertTrue(
            "Warning not visible for missing property",
            WappiController.validateMissingInfoWarning(missingInfo)
        );
        Assert.assertFalse(
            "Confirmation modal is visible",
            WappiController.validateConfirmationModal()
        );
    }

}

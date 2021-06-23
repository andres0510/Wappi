package com.wappi.test.steps;

import com.wappi.test.action.DriverManager;
import com.wappi.test.helpers.Dictionary;
import com.wappi.test.helpers.PropertiesFile;
import com.wappi.test.helpers.Report;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepsPrueba {

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
        DriverManager.close();
    }

    //----------------------------------------------------------------------------------------------------------------->
    //---------- STEPS ------------------------------------------------------------------------------------------------>
    //----------------------------------------------------------------------------------------------------------------->

    @Given("precondition")
    public void precondition(){
    }

    @When("steps")
    public void steps(){
    }

    @Then("postcondition")
    public void postcondition(){
    }

}

package com.wappi.test.action;

import com.wappi.test.helpers.Report;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Wait {

    private static WebDriverWait wait;
    private static final int DEFAULT_TIMEOUT = 30;
    private static final String ERROR_MSG = "Error waiting for element: %s";

    private Wait(){}

    //----------------------------------------------------------------------------------------------------------------->
    //---------- OBJECT INITIATION ------------------------------------------------------------------------------------>
    //----------------------------------------------------------------------------------------------------------------->

    public static void initWait(WebDriver driver) {
        wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
    }

    public static void forElementClickable(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            Report.reportFail(String.format(ERROR_MSG, e.getMessage()));
        }
    }

    public static void forElementVisible(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            Report.reportFail(String.format(ERROR_MSG, e.getMessage()));
        }
    }

}

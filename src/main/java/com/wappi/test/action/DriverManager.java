package com.wappi.test.action;

import com.wappi.test.helpers.Dictionary;
import com.wappi.test.helpers.PropertiesFile;
import com.wappi.test.helpers.Report;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class DriverManager {

    private static WebDriver driver;

    private DriverManager(){}

    //----------------------------------------------------------------------------------------------------------------->
    //---------- OBJECT INITIATION ------------------------------------------------------------------------------------>
    //----------------------------------------------------------------------------------------------------------------->

    public static void initDriverManager(){
        setDriverProperty();
        driver = new ChromeDriver();
        Wait.initWait(driver);
    }

    //----------------------------------------------------------------------------------------------------------------->
    //---------- GETTER ----------------------------------------------------------------------------------------------->
    //----------------------------------------------------------------------------------------------------------------->

    public static WebDriver getDriver(){
        return driver;
    }

    //----------------------------------------------------------------------------------------------------------------->
    //---------- DRIVER CONFIGURATION --------------------------------------------------------------------------------->
    //----------------------------------------------------------------------------------------------------------------->

    private static void setDriverProperty(){
        PropertiesFile properties = new PropertiesFile(Dictionary.PropertiesPath.DEFAULT);
        String osName = System.getProperty("os.name").toLowerCase();
        String driverPath = "";
        if (osName.contains(Dictionary.OSName.WINDOWS)) {
            driverPath = properties.getProperty("windows.driver.path");
        } else if (osName.contains(Dictionary.OSName.LINUX_1) || osName.contains(Dictionary.OSName.LINUX_2)) {
            driverPath = properties.getProperty("linux.driver.path");
        } else if (osName.contains(Dictionary.OSName.MAC_OSX)) {
            driverPath = properties.getProperty("mac.driver.path");
        } else {
            Report.reportFail(String.format("Driver not found for current OS: %s", osName));
        }
        System.setProperty("webdriver.chrome.driver", driverPath);
    }

    //----------------------------------------------------------------------------------------------------------------->
    //---------- ACTIONS ---------------------------------------------------------------------------------------------->
    //----------------------------------------------------------------------------------------------------------------->

    public static void open(String url) {
        driver.get(url);
        driver.manage().window().maximize();
    }

    public static void finish() {
        try {
            driver.close();
            driver.quit();
        } catch (Exception e) {
            Report.reportFail(String.format("Error closing browser: %s", e.getMessage()));
        }
    }

    public static void click(WebElement element) {
        Wait.forElementClickable(element);
        element.click();
    }

    public static String getText(WebElement element) {
        Wait.forElementVisible(element);
        return element.getText();
    }

    public static void sendText(WebElement element, String text) {
        Wait.forElementVisible(element);
        element.sendKeys(text);
    }

    public static void clearText(WebElement element) {
        Wait.forElementVisible(element);
        element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        element.sendKeys(Keys.DELETE);
    }

    public static void selectOption(WebElement element, String option) {
        Wait.forElementVisible(element);
        Select select = new Select(element);
        select.selectByVisibleText(option);
    }

    public static boolean isVisible(WebElement element) {
        try {
            Wait.forElementDisplayed(element);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}

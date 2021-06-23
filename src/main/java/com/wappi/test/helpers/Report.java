package com.wappi.test.helpers;

import org.junit.Assert;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Report {

    private static Logger log;

    private Report() { }

    //----------------------------------------------------------------------------------------------------------------->
    //---------- OBJECT INITIATION ------------------------------------------------------------------------------------>
    //----------------------------------------------------------------------------------------------------------------->

    public static void initReport(){
        try {
            String logName = String.format("log/log%s.txt", Utilities.getCurrentDate(Dictionary.DateFormat.YYYY_MM_DD_HH_MM_SS));
            log = Logger.getLogger(logName);
            FileHandler file = new FileHandler(logName);
            SimpleFormatter formatter = new SimpleFormatter();
            file.setFormatter(formatter);
            log.addHandler(file);
        } catch (IOException e) {
            Assert.fail();
        }
    }

    //----------------------------------------------------------------------------------------------------------------->
    //---------- FUNCTIONS -------------------------------------------------------------------------------------------->
    //----------------------------------------------------------------------------------------------------------------->

    public static void reportInfo(String message) {
        log.log(Level.INFO, message);
    }

    public static void reportFail(String message) {
        log.log(Level.SEVERE, message);
        Assert.fail(String.format("Error found: %s", message));
    }

}
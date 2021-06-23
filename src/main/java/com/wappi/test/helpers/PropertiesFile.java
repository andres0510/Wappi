package com.wappi.test.helpers;

import java.io.*;
import java.util.Properties;

public class PropertiesFile {

    private Properties properties;

    public PropertiesFile (String propertiesPath) {
        try {
            this.properties = new Properties();
            properties.load(new FileInputStream(new File(propertiesPath)));
        } catch (IOException e) {
            Report.reportFail(e.getMessage());
        }
    }

    public String getProperty(String key) {
        return properties.get(key).toString();
    }

}

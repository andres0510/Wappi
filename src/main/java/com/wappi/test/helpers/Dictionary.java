package com.wappi.test.helpers;

public class Dictionary {

    private Dictionary(){}

    public static class PropertiesPath {
        private PropertiesPath(){}
        public static final String DEFAULT = "src/main/resources/properties/configuration.properties";
    }

    public static class OSName {
        private OSName(){}
        public static final String WINDOWS = "windows";
        public static final String LINUX_1 = "nix";
        public static final String LINUX_2 = "nux";
        public static final String MAC_OSX = "mac";
    }

    public static class DateFormat {
        private DateFormat(){}
        public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH-mm-ss";
    }

    public static void main(String[] args) {
        try {
            Report.initReport();
            Report.reportInfo("Hola");
            Thread.sleep(5000);
            Report.reportInfo("2");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

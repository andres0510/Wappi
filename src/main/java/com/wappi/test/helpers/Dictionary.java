package com.wappi.test.helpers;

import java.util.Arrays;
import java.util.List;

public class Dictionary {

    private Dictionary(){}

    public static class PropertiesPath {
        private PropertiesPath(){}
        public static final String DEFAULT = "src/main/resources/properties/configuration.properties";
    }

    public static class FilesPath {
        private FilesPath(){}
        public static final String AVATAR = String.format("%s\\src\\main\\resources\\files\\wappi.png", System.getProperty("user.dir"));
    }

    public static class Messages {
        private Messages(){}
        public static final String SUCCESS = "[SUCCESS] - %s";
        public static final String INFO = "[INFO] - %s";
        public static final String FAIL = "[FAIL] - %s";
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
        public static final String DD_MM_YYYY = "dd/MM/yyyy";
    }

    public static class Country {
        private Country(){}
        public static final String DEFAULT_COUNTRY_OPTION = "Seleccionar";
        private static final List<String> countries = Arrays.asList("Argentina", "Colombia", "Estados Unidos");
        public static List<String> getCountries(){ return countries; }
    }

    public static class Gender {
        private Gender(){}
        public static final String MASCULINO = "Masculino";
        public static final String FEMENINO = "Femenino";
        private static final List<String> genders = Arrays.asList(MASCULINO, FEMENINO);
        public static List<String> getGenders(){ return genders; }
    }

    public static class MissingInfo {
        private MissingInfo(){}
        public static final String AVATAR = "avatar";
        public static final String FIRSTNAME = "firstname";
        public static final String LASTNAME = "lastname";
        public static final String BIRTHDATE = "birthdate";
        public static final String COUNTRY = "country";
    }

}

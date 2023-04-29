package com.ip13;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Util {
    private static final int millis = 1000;

    public static void timeout() {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ex) {
            System.out.println("Exception is caught");
        }
    }

    public static String getUsername() {
        try (InputStream input = new FileInputStream("src/test/resources/passwords.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            return prop.getProperty("username");
        } catch (
                IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String getPassword() {
        try (InputStream input = new FileInputStream("src/test/resources/passwords.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            return prop.getProperty("password");
        } catch (
                IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}

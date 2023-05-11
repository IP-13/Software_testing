package com.ip13.cucumber;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Util {
    private static WebDriver chromeDriver;

    private static final int millis = 1000;

    public static void timeout() {
        if (millis == 0) {
            return;
        }
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


    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/java/drivers/chromedriver");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        chromeDriver = new ChromeDriver(chromeOptions);
        chromeDriver.manage().window().maximize();
        Util.timeout();
    }


    @After
    public void setDown() {
        chromeDriver.close();
    }


    public static WebDriver getChromeDriver() {
        return chromeDriver;
    }
}

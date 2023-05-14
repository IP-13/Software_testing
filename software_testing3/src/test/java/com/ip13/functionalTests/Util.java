package com.ip13.functionalTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static java.util.Objects.isNull;

public class Util {
    private static final int millis = 300;


    public static void setupDrivers() {
        if (isNull(System.getProperty("webdriver.chrome.driver"))) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/drivers/chromedriver");
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/drivers/geckodriver");
        }
    }


    public static List<WebDriver> initDrivers() {
        try (InputStream input = new FileInputStream("src/test/resources/passwords.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            String driverName = prop.getProperty("driverName");
            List<WebDriver> drivers = new ArrayList<>();

            if (driverName.equalsIgnoreCase("chrome")) {
                drivers.add(new ChromeDriver());
            } else if (driverName.equalsIgnoreCase("firefox")) {
                drivers.add(new FirefoxDriver());
            } else if (driverName.equalsIgnoreCase("all")) {
                drivers.add(new ChromeDriver());
                drivers.add(new FirefoxDriver());
            } else {
                throw new RuntimeException("Wrong driver name to setup");
            }

            return drivers;
        } catch (IOException ex) {
            throw new RuntimeException("No property for drivers setup");
        }
    }


    public static void closeDrivers(List<WebDriver> drivers) {
        drivers.forEach(WebDriver::close);
    }


    public static void timeout() {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ex) {
            System.out.println("Exception is caught");
        }
    }


    public static void timeout(int millis) {
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
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }


    public static String getPassword() {
        try (InputStream input = new FileInputStream("src/test/resources/passwords.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            return prop.getProperty("password");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }


    public static void openFandom(List<WebDriver> drivers) {
        drivers.forEach(driver ->
        {
            driver.manage().window().maximize();
            driver.get("https://www.fandom.com/");
        });
    }


    public static WebElement findElement(WebDriver driver, By by) {
        return new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(by));
    }


    public static void signInToFandom(WebDriver driver) {
        driver.findElement(By.xpath("/html/body//div[@class='feed-header']//a[@href='https://auth.fandom.com/signin?source=mw']")).click();
        driver.findElement(By.xpath("/html/body/div/main/div/div/div/form/section/div/div/input[@id='identifier']")).sendKeys(getUsername());
        driver.findElement(By.xpath("/html/body/div/main/div/div/div/form/section/div/div/div/input[@id='password']")).sendKeys(getPassword());
        driver.findElement(By.xpath("/html//div[@id='__next']//div[@class='login_formWrapper__IMoai']/form[1]/section/div[@class='Submit_buttonWrapper__33HZ0']")).click();
    }
}


package com.ip13.functionalTests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;


public class SignInTest {
    private static List<WebDriver> drivers;
    private final String username = Util.getUsername();
    private final String password = Util.getPassword();


    @BeforeAll
    public static void init() {
        Util.setupDrivers();
        drivers = Util.initDrivers();
    }


    @Test
    public void testSignIn() {
        openFandomToSignIn();
        clickSignInOnTheMainPage();
        enterUsername();
        enterPassword();
        clickSignInButton();
        goToProfile();
        checkUsernameInProfile();
    }


    @AfterAll
    public static void close() {
        Util.closeDrivers(drivers);
    }


    public void openFandomToSignIn() {
        Util.openFandom(drivers);
        Util.timeout();
    }


    public void clickSignInOnTheMainPage() {
        String xpath = "/html/body/div[5]/div[1]/div[1]/div/a[1]";
        drivers.forEach(driver -> Util.findElement(driver, By.xpath(xpath)).click());
        Util.timeout();
    }


    public void enterUsername() {
        String xpath = "//*[@id=\"identifier\"]";
        drivers.forEach(driver -> Util.findElement(driver, By.xpath(xpath)).sendKeys(username));
        Util.timeout();
    }


    public void enterPassword() {
        String xpath = "//*[@id=\"password\"]";
        drivers.forEach(driver -> Util.findElement(driver, By.xpath(xpath)).sendKeys(password));
        Util.timeout();
    }


    public void clickSignInButton() {
        String xpath = "/html/body/div[1]/main/div/div[2]/div/form[1]/section/div[3]/button";
        drivers.forEach(driver -> Util.findElement(driver, By.xpath(xpath)).click());
        Util.timeout();
    }


    public void goToProfile() {
        String xpathHover = "/html/body/div[2]/div[2]/div/div[1]/div";
        String xpath = "/html/body/div[2]/div[2]/div/div[2]/ul/li[1]/a";

        drivers.forEach(driver ->
        {
            Util.findElement(driver, By.xpath(xpathHover)).click();
            Util.findElement(driver, By.xpath(xpath)).click();
        });
        Util.timeout();
    }


    public void checkUsernameInProfile() {
        String xpath = "//*[@id=\"userProfileApp\"]/div/section/div[2]/div/div[1]/h1";
        drivers.forEach(driver -> {
            String actualUsername = Util.findElement(driver, By.xpath(xpath)).getText();
            Assertions.assertEquals(username, actualUsername);
        });
        Util.timeout();
    }
}

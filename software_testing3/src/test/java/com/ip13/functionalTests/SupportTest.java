package com.ip13.functionalTests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class SupportTest {
    private static List<WebDriver> drivers;


    @BeforeAll
    public static void init() {
        Util.setupDrivers();
        drivers = Util.initDrivers();
    }


    @Test
    public void testSupport() {
        openFandomToWriteToSupport();
        clickSupportLinkInTheFooterOfTheMainPage();
        clickContactUsInTheHeaderOfTheSupportPage();
//        chooseIDontSeeAnOptionForMyIssue();
    }


    @AfterAll
    public static void close() {
        Util.closeDrivers(drivers);
    }


    public void openFandomToWriteToSupport() {
        Util.openFandom(drivers);
        Util.timeout();
    }


    public void clickSupportLinkInTheFooterOfTheMainPage() {
        String xpath = "/html/body/footer/div[1]/div[3]/section[1]/ul/li[2]/a";
        drivers.forEach(driver -> Util.findElement(driver, By.xpath(xpath)).click());
        Util.timeout();
    }


    public void clickContactUsInTheHeaderOfTheSupportPage() {
        String xpath = "//*[@id=\"user-nav\"]/ul/a";
        drivers.forEach(driver -> Util.findElement(driver, By.xpath(xpath)).click());
        Util.timeout();
    }


//    public void chooseIDontSeeAnOptionForMyIssue() {
//        String xpath1 = "//*[@id=\"new_request\"]/div/a";
//        String xpath2 = "//*[@id=\"_vur1dzyxh\"]";
//        drivers.forEach(driver -> Util.findElement(driver, By.xpath(xpath1)).click());
//        drivers.forEach(driver -> Util.findElement(driver, By.xpath(xpath2)).click());
//        Util.timeout();
//    }
//
//
//    public void makeSureYouHaveBeenThrownOutOfTheSiteForUsingAutomationTools() {
//
//    }
}



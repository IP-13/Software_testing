package com.ip13.functionalTests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class SearchTest {
    private static List<WebDriver> drivers;
    private static final String request = "avatar";

    @BeforeAll
    public static void init() {
        Util.setupDrivers();
        drivers = Util.initDrivers();
    }


    @Test
    public void testSearch() {
        openFandomToSearchSomethingCool();
        enterInSearchLine();
        clickSearchButton();
        assertYouSeeInfoAboutYourRequest();
    }


    @AfterAll
    public static void close() {
        Util.closeDrivers(drivers);
    }


    public void openFandomToSearchSomethingCool() {
        Util.openFandom(drivers);
        Util.timeout();
    }


    public void enterInSearchLine() {
        String searchLineXpath = "/html/body/div[5]/div[1]/form[1]/div/label/span";
        String searchLineInputXpath = "/html/body/div[5]/div[1]/form[1]/div/label/input";
        drivers.forEach(driver -> {
            Util.findElement(driver, By.xpath(searchLineXpath)).click();
            Util.findElement(driver, By.xpath(searchLineInputXpath)).sendKeys(request);
        });
        Util.timeout();
    }


    public void clickSearchButton() {
        String xpath = "/html/body/div[5]/div[1]/form[1]/div/button";
        drivers.forEach(driver -> Util.findElement(driver, By.xpath(xpath)).click());
        Util.timeout();
    }


    public void assertYouSeeInfoAboutYourRequest() {
        String xpath = "/html/body/section/div[1]/header/h1";
        drivers.forEach(driver ->
        {
            String searchResult = Util.findElement(driver, By.xpath(xpath)).getText();
            Assertions.assertEquals("Search Results for " + request, searchResult);
        });
    }
}

package com.ip13.functionalTests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class ChangeLanguageTest {
    private static List<WebDriver> drivers;


    @BeforeAll
    public static void init() {
        Util.setupDrivers();
        drivers = Util.initDrivers();
    }


    @Test
    public void testChangeLanguage() {
        openFandomToChangeLanguage();
        clickHelpInTheFooterOfTheMainPage();
        clickOnToggleToSeeAvailableLanguages();
        chooseRussianInDropDownList();
        assertRussianLanguageChosen();
    }


    @AfterAll
    public static void close() {
        Util.closeDrivers(drivers);
    }

    public void openFandomToChangeLanguage() {
        Util.openFandom(drivers);
        Util.timeout();
    }


    public void clickHelpInTheFooterOfTheMainPage() {
        String xpath = "/html/body/footer/div[1]/div[3]/section[1]/ul/li[3]/a";
        drivers.forEach(driver -> Util.findElement(driver, By.xpath(xpath)).click());
        Util.timeout();
    }


    public void clickOnToggleToSeeAvailableLanguages() {
        String xpath = "/html/body/div[4]/div[3]/div[2]/main/div[2]/div[1]/div[2]/div/div[1]";
        drivers.forEach(driver -> Util.findElement(driver, By.xpath(xpath)).click());
        Util.timeout();
    }


    public void chooseRussianInDropDownList() {
        String xpath = "/html/body/div[4]/div[3]/div[2]/main/div[2]/div[1]/div[2]/div/div[2]/ul/li[13]/a";
        drivers.forEach(driver -> Util.findElement(driver, By.xpath(xpath)).click());
        Util.timeout();
    }


    public void assertRussianLanguageChosen() {
        String xpath = "/html/body/div[4]/div[3]/div[2]/main/div[2]/div[1]/div[2]/div/div[1]";
        drivers.forEach(driver -> {
            String pageLanguage = Util.findElement(driver, By.xpath(xpath)).getText();
            Assertions.assertEquals("русский", pageLanguage);
        });
        Util.timeout();
    }
}

package com.ip13.functionalTests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class FollowUsTest {
    private static List<WebDriver> drivers;


    @BeforeAll
    public static void init() {
        Util.setupDrivers();
        drivers = Util.initDrivers();
    }


    @Test
    public void testFollowUs() {
        openFandomToFindLinksToSocialMedia();
        clickYoutubeIcon();
        checkCurrUrl();
    }


    @AfterAll
    public static void close() {
        Util.closeDrivers(drivers);
    }


    public void openFandomToFindLinksToSocialMedia() {
        Util.openFandom(drivers);
        Util.timeout();
    }


    public void clickYoutubeIcon() {
        String xpath = "//html/body/footer/div[1]/div[3]/section[3]/ul/li[3]/a";
        drivers.forEach(driver -> Util.findElement(driver, By.xpath(xpath)).click());
        Util.timeout();
    }


    public void checkCurrUrl() {
        drivers.forEach(driver -> Assertions.assertEquals(driver.getCurrentUrl(), "https://www.youtube.com/fandomentertainment"));
        Util.timeout();
    }
}

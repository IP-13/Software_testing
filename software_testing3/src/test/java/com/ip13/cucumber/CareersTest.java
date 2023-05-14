package com.ip13.cucumber;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;


public class CareersTest {
    private static List<WebDriver> drivers;

    
    @BeforeAll
    public static void init() {
        Util.setupDrivers();
        drivers = Util.initDrivers();
    }


    @Test
    public void testFindCareer() {
        openFandomToFindCareer();
        clickCareerLinkInTheFooterOfThePage();
        enterSomethingInSearchPositionField();
        assertNoResultsFound();
    }


    @AfterAll
    public static void close() {
        Util.closeDrivers(drivers);
    }


    public void openFandomToFindCareer() {
        Util.openFandom(drivers);
        Util.timeout();
    }


    public void clickCareerLinkInTheFooterOfThePage() {
        String xpath = "/html/body/footer/div[1]/div[2]/section/ul/li[3]/a";
        drivers.parallelStream().forEach(driver -> Util.findElement(driver, By.xpath(xpath)).click());
        Util.timeout();
    }


    public void enterSomethingInSearchPositionField() {
        String xpath = "//*[@id=\"search-input\"]";
        drivers.parallelStream().forEach(driver -> Util.findElement(driver, By.xpath(xpath)).sendKeys("This search will show no results"));
        Util.timeout();
    }


    public void assertNoResultsFound() {
        String xpath = "//*[@id=\"search-none\"]";
        drivers.parallelStream().forEach(driver ->
                {
                    String result = Util.findElement(driver, By.xpath("//*[@id=\"search-none\"]")).getText();
                    Assertions.assertEquals(result, "No results found");
                }
        );
        Util.timeout();
    }
}

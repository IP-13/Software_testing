package com.ip13.functionalTests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;


public class StartWikiTest {
    private static List<WebDriver> drivers;
    private final String username = Util.getUsername();
    private final String password = Util.getPassword();
    private final String wikiName = "The Real GhostBusters fans2";
    private final String communityDescription = "This is community dedicated to animated tv series \"The Real GhostBusters\", which was translated from 1986 to 1991 and consists of 7 seasons and 140 episodes.";


    @BeforeAll
    public static void init() {
        Util.setupDrivers();
        drivers = Util.initDrivers();
    }


    @Test
    public void testSupport() {
        openFandomToCreateWiki();
        signInToCreateNewWiki();
        clickStartWiki();
        nameCommunity();
        selectRussianLanguageForMyCommunity();
        clickNextCommunityInfo();
        describeCommunity();
        chooseHubForYourCommunity();
        clickNextCreateMyCommunity();
    }


    @AfterAll
    public static void close() {
        Util.closeDrivers(drivers);
    }


    public void openFandomToCreateWiki() {
        Util.openFandom(drivers);
        Util.timeout();
    }


    public void signInToCreateNewWiki() {
        drivers.forEach(Util::signInToFandom);
        Util.timeout();
    }


    public void clickStartWiki() {
        String xpath = "/html/body/div[2]/div[1]/nav/div/a[7]/div[2]";
        drivers.forEach(driver -> Util.findElement(driver, By.xpath(xpath)).click());
        Util.timeout();
    }


    public void nameCommunity() {
        String xpath = "//*[@id=\"wds_input_1\"]";
        drivers.forEach(driver -> Util.findElement(driver, By.xpath(xpath)).sendKeys(wikiName));
        Util.timeout();
    }


    public void selectRussianLanguageForMyCommunity() {
        String toggleXpath = "//*[@id=\"create-new-wiki\"]/div/div[2]/div[3]/div[2]/div/div/div/div[1]/div";
        String russianLanguageOptionXpath = "//*[@id=\"react-select-2-option-0-8\"]";
        drivers.forEach(driver -> {
            Util.findElement(driver, By.xpath(toggleXpath)).click();
            Util.findElement(driver, By.xpath(russianLanguageOptionXpath)).click();
        });
        Util.timeout();
    }


    public void clickNextCommunityInfo() {
        String xpath = "//*[@id=\"create-new-wiki\"]/div/div[2]/button";
        drivers.forEach(driver -> Util.findElement(driver, By.xpath(xpath)).click());
        Util.timeout();
    }


    public void describeCommunity() {
        String xpath = "//*[@id=\"wds_input_4\"]";
        drivers.forEach(driver -> Util.findElement(driver, By.xpath(xpath)).sendKeys(communityDescription));
        Util.timeout();
    }


    public void chooseHubForYourCommunity() {
        String toggleXpath = "//*[@id=\"react-select-3-placeholder\"]";
        String tvHubOptionXpath = "//*[@id=\"react-select-3-option-5\"]";
        drivers.forEach(driver -> {
            Util.findElement(driver, By.xpath(toggleXpath)).click();
            Util.findElement(driver, By.xpath(tvHubOptionXpath)).click();
        });
        Util.timeout(10000);
    }


    public void clickNextCreateMyCommunity() {
        String xpath = "/html//div[@id='create-new-wiki']//div[@class='create-new-wiki__actions create-new-wiki__description-actions']/button[2]";
        drivers.forEach(driver -> {
            Util.findElement(driver, By.xpath(xpath)).click();

            String redirectForSimilarWikiURL = "https://createnewwiki.fandom.com/wiki/Special:CreateNewWiki";
            String optionXpath = "/html/body/div[2]/div[3]/div/div[2]/form/div[3]/div/label";
            String nextCreateMyCommunityButton = "/html/body/div[2]/div[3]/div/div[2]/div[2]/button[2]";

            if (driver.getCurrentUrl().equals(redirectForSimilarWikiURL)) {
                Util.findElement(driver, By.xpath(optionXpath)).click();
                Util.findElement(driver, By.xpath(nextCreateMyCommunityButton)).click();
            }
        });
        Util.timeout(100000);
    }


    public void assertYouHaveCreateWiki() {

    }
}

package com.ip13.cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;


public class SingInTest {
    private final WebDriver chromeDriver = Util.getChromeDriver();
    private final String username = Util.getUsername();
    private final String password = Util.getPassword();


    @Given("I open fandom to sign in")
    public void i_open_fandom() {
        chromeDriver.get("https://www.fandom.com/");
        Util.timeout();
    }


    @And("I click sign in on the main page")
    public void i_click_sign_in_on_the_main_page() {
        chromeDriver.findElement(By.xpath("/html/body//div[@class='feed-header']//a[@href='https://auth.fandom.com/signin?source=mw']")).click();
        Util.timeout();
    }


    @And("I enter username")
    public void iEnterUsername() {
        chromeDriver.findElement(By.xpath("/html/body/div/main/div/div/div/form/section/div/div/input[@id='identifier']")).sendKeys(username);
        Util.timeout();
    }


    @And("I enter password")
    public void iEnterPassword() {
        chromeDriver.findElement(By.xpath("/html/body/div/main/div/div/div/form/section/div/div/div/input[@id='password']")).sendKeys(password);
        Util.timeout();
    }


    @And("I click sign in button")
    public void iClickSignInButton() {
        chromeDriver.findElement(By.xpath("/html//div[@id='__next']//div[@class='login_formWrapper__IMoai']/form[1]/section/div[@class='Submit_buttonWrapper__33HZ0']")).click();
        Util.timeout();
    }


    @And("I go to profile")
    public void goToProfile() {
        String xpath = "/html/body/div[@class='global-navigation logged-in']/div[@class='global-navigation__bottom']/div/div[@class='wds-dropdown__content']/ul//a[@href='http://community.fandom.com/wiki/User:Softwate-testing3']";
        new WebDriverWait(chromeDriver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))).click();
        Util.timeout();
    }


    @Then("I should see my name")
    public void checkUsernameInProfile() {
        assertEquals(chromeDriver.findElement(By.xpath("/html//div[@id='userProfileApp']//section[@class='user-identity-box']//h1[.='Softwate-testing3']")).getText(), username);
        Util.timeout();
    }
}

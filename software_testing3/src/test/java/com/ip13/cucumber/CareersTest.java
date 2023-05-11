package com.ip13.cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;

public class CareersTest {
    private final WebDriver chromeDriver = Util.getChromeDriver();


    @Given("I open fandom to check careers")
    public void i_open_fandom() {
        chromeDriver.get("https://www.fandom.com/");
        Util.timeout();
    }


    @And("I click careers link in the footer of the main page")
    public void i_click_careers_link_in_the_footer_of_the_main_page() {
        chromeDriver.findElement(By.xpath("/html//footer/div[@class='wds-global-footer__main']/div[2]/section/ul[@class='wds-global-footer__links-list']//a[@href='https://www.fandom.com/careers']")).click();
        Util.timeout();
    }


    @And("I enter something in search open positions field")
    public void i_enter_in_search_open_positions_field() {
        chromeDriver.findElement(By.xpath("//*[@id=\"search-input\"]")).sendKeys("It won't be recognized");
        Util.timeout();
    }

    @Then("I should see no results found")
    public void i_should_see() {
        String result = chromeDriver.findElement(By.xpath("//*[@id=\"search-none\"]")).getText();
        assertEquals(result, "No results found");
        Util.timeout();
    }
}

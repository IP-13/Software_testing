//package com.ip13.cucumber;
//
//import io.cucumber.java.en.And;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//
//import static org.junit.Assert.assertEquals;
//
//public class SearchTest {
//    private final WebDriver chromeDriver = Util.getChromeDriver();
//
//
//    @Given("I open fandom to search info about Avatar")
//    public void i_open_fandom_to_search_info_about_avatar() {
//        chromeDriver.get("https://www.fandom.com/");
//        Util.timeout();
//    }
//
//
//    @And("I enter in search line Avatar")
//    public void i_enter_in_search_line_avatar() {
//        chromeDriver.findElement(By.xpath("/html/body/div[5]/div[1]/form[1]/div/label/span")).click();
//        chromeDriver.findElement(By.xpath("/html/body/div[5]/div[1]/form[1]/div/label/input")).sendKeys("Avatar");
//        Util.timeout();
//    }
//
//
//    @And("I click search button")
//    public void i_click_search_button() {
//        chromeDriver.findElement(By.xpath("/html/body/div[5]/div[1]/form[1]/div/button")).click();
//        Util.timeout();
//    }
//
//
//    @Then("I should see available information about Avatar")
//    public void i_should_see_available_information_about_avatar() {
//        assertEquals(chromeDriver.findElement(By.xpath("/html/body/section/div[1]/header/h1")).getText(), "Search Results for avatar");
//    }
//}

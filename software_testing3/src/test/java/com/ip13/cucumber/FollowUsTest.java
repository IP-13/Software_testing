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
//public class FollowUsTest {
//    private final WebDriver chromeDriver = Util.getChromeDriver();
//
//
//    @Given("I open fandom to find links to their social media accounts")
//    public void i_open_fandom_to_find_links_to_their_social_media_accounts() {
//        chromeDriver.get("https://www.fandom.com/");
//        Util.timeout();
//    }
//
//
//    @And("I click youtube icon")
//    public void i_click_youtube_icon() {
//        chromeDriver.findElement(By.xpath("//html/body/footer/div[1]/div[3]/section[3]/ul/li[3]/a")).click();
//        Util.timeout();
//    }
//
//
//    @Then("I should be redirected to their youtube channel")
//    public void i_should_be_redirected_to_their_youtube_channel() {
//        assertEquals(chromeDriver.getCurrentUrl(), "https://www.youtube.com/fandomentertainment");
//        Util.timeout();
//    }
//}

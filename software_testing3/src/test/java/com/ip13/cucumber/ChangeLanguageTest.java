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
//public class ChangeLanguageTest {
//    private final WebDriver chromeDriver = Util.getChromeDriver();
//
//
//    @Given("I open fandom to change language")
//    public void i_open_fandom_to_change_language() {
//        chromeDriver.get("https://www.fandom.com/");
//        Util.timeout();
//    }
//
//
//    @And("I click help in the footer of the main page")
//    public void i_click_help_in_the_footer_of_the_main_page() {
//        chromeDriver.findElement(By.xpath("/html/body/footer/div[1]/div[3]/section[1]/ul/li[3]/a")).click();
//        Util.timeout();
//    }
//
//
//    @And("I click on toggle to see available languages")
//    public void i_click_on_toggle_to_see_available_languages() {
//        chromeDriver.findElement(By.xpath("/html/body/div[4]/div[3]/div[2]/main/div[2]/div[1]/div[2]/div/div[1]")).click();
//        Util.timeout();
//    }
//
//
//    @And("I choose russian in drop-down list")
//    public void i_choose_russian_in_drop_down_list() {
//        chromeDriver.findElement(By.xpath("/html/body/div[4]/div[3]/div[2]/main/div[2]/div[1]/div[2]/div/div[2]/ul/li[13]/a")).click();
//        Util.timeout();
//    }
//
//
//    @Then("I should see site in russian")
//    public void i_should_see_site_in_russian() {
//        String pageLanguage = chromeDriver.findElement(By.xpath("/html/body/div[4]/div[3]/div[2]/main/div[2]/div[1]/div[2]/div/div[1]")).getText();
//        assertEquals(pageLanguage, "русский");
//        Util.timeout();
//    }
//
//}

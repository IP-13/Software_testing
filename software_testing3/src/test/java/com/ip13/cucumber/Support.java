//package com.ip13.cucumber;
//
//import io.cucumber.java.en.And;
//import io.cucumber.java.en.Given;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//
//public class Support {
//    private final WebDriver chromeDriver = Util.getChromeDriver();
//
//
//    @Given("I enter fandom to contact support")
//    public void i_enter_fandom_to_contact_support() {
//        chromeDriver.get("https://www.fandom.com/");
//        Util.timeout();
//    }
//
//
//    @And("I click support in the footer of the main page")
//    public void i_click_support_in_the_footer_of_the_main_page() {
//        chromeDriver.findElement(By.xpath("/html/body/footer/div[1]/div[3]/section[1]/ul/li[2]/a")).click();
//        Util.timeout();
//    }
//
//    @And("I click Contact Us in the header of support page")
//    public void i_click_concact_us_in_the_header_of_support_page() {
//        chromeDriver.findElement(By.xpath("//*[@id=\"user-nav\"]/ul/a")).click();
//        Util.timeout();
//    }
//
//    @And("I choose I don't see an option for my issue")
//    public void i_choose_i_don_t_see_an_option_for_my_issue() {
//        chromeDriver.findElement(By.xpath("//*[@id=\"new_request\"]/div/a")).click();
//        chromeDriver.findElement(By.xpath("//*[@id=\"_vur1dzyxh\"]")).click();
//        Util.timeout();
//    }
//
//
//
//
//
//}
//
//

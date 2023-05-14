//package com.ip13.cucumber;
//
//import io.cucumber.java.en.And;
//import io.cucumber.java.en.Given;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import java.time.Duration;
//
//public class StartWikiTest {
//    private final WebDriver chromeDriver = Util.getChromeDriver();
//    private final String username = Util.getUsername();
//    private final String password = Util.getPassword();
//    private final String wikiName = "The Real GhostBusters fans2";
//
//
//    @Given("I open fandom to start my new Wiki about The Real GhostBusters")
//    public void i_open_fandom_to_start_my_new_wiki_about_the_real_ghost_busters() {
//        chromeDriver.get("https://www.fandom.com/");
//        Util.timeout();
//    }
//
//
//    @And("I sign in to create a new wiki")
//    public void i_sign_in_to_create_a_new_wiki() {
//        chromeDriver.findElement(By.xpath("/html/body//div[@class='feed-header']//a[@href='https://auth.fandom.com/signin?source=mw']")).click();
//        chromeDriver.findElement(By.xpath("/html/body/div/main/div/div/div/form/section/div/div/input[@id='identifier']")).sendKeys(username);
//        chromeDriver.findElement(By.xpath("/html/body/div/main/div/div/div/form/section/div/div/div/input[@id='password']")).sendKeys(password);
//        chromeDriver.findElement(By.xpath("/html//div[@id='__next']//div[@class='login_formWrapper__IMoai']/form[1]/section/div[@class='Submit_buttonWrapper__33HZ0']")).click();
//        Util.timeout();
//    }
//
//
//    @And("I click start a Wiki")
//    public void i_click_start_a_wiki() {
//        new WebDriverWait(chromeDriver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[1]/nav/div/a[7]/div[2]"))).click();
//        Util.timeout();
//    }
//
//
//    @And("I name my community")
//    public void i_name_my_community() {
//        new WebDriverWait(chromeDriver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"wds_input_1\"]"))).sendKeys(wikiName);
//        Util.timeout();
//    }
//
//
//    @And("I select russian language for my community")
//    public void i_select_russian_language_for_my_community() {
//        new WebDriverWait(chromeDriver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"create-new-wiki\"]/div/div[2]/div[3]/div[2]/div/div/div/div[1]/div"))).click();
//        new WebDriverWait(chromeDriver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"react-select-2-option-0-8\"]"))).click();
//        Util.timeout();
//    }
//
//
//    @And("I click next: community info")
//    public void i_click_next_community_info() {
//        new WebDriverWait(chromeDriver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"create-new-wiki\"]/div/div[2]/button"))).click();
//        Util.timeout();
//    }
//
//
//    @And("I describe my community")
//    public void i_describe_my_community() {
//        String communityDescription = "This is community dedicated to animated tv series \"The Real GhostBusters\", which was translated from 1986 to 1991 and consists of 7 seasons and 140 episodes.";
//        new WebDriverWait(chromeDriver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"wds_input_4\"]"))).sendKeys(communityDescription);
//        Util.timeout();
//    }
//
//
//    @And("I choose a TV hub for my community")
//    public void i_choose_a_tv_hub_for_my_community() {
//        new WebDriverWait(chromeDriver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"react-select-3-placeholder\"]"))).click();
//        new WebDriverWait(chromeDriver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"react-select-3-option-5\"]"))).click();
//        Util.timeout(5000);
//    }
//
//
//    @And("I click next: create my community")
//    public void i_click_next_create_my_community() {
//        new WebDriverWait(chromeDriver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(By.xpath("/html//div[@id='create-new-wiki']//div[@class='create-new-wiki__actions create-new-wiki__description-actions']/button[2]"))).click();
//        Util.timeout(10000);
//
//        // TODO update test for case when fandom ask you because exists wiki with similar thematic
//    }
//
//}

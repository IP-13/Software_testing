package com.ip13;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.*;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"classpath:features/signIn.feature"},
        glue = {"com.ip13"}
)
public class SingInTest {
    private WebDriver driver;
    private final String username = Util.getUsername();
    private final String password = Util.getPassword();


    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/java/drivers/chromedriver");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);

        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();

        Util.timeout();
    }


    @After
    public void setDown() {
        driver.close();
    }


    @Given("I open fandom")
    public void i_open_fandom() {
        driver.get("https://www.fandom.com/");
        Util.timeout();
    }


    @And("I click sign in on the main page")
    public void i_click_sign_in_on_the_main_page() {
        driver.findElement(By.xpath("/html/body//div[@class='feed-header']//a[@href='https://auth.fandom.com/signin']")).click();
        Util.timeout();
    }


    @And("I enter username")
    public void iEnterUsername() {
        driver.findElement(By.xpath("/html/body/div/main/div/div/div/form/section/div/div/input[@id='identifier']")).sendKeys(username);
        Util.timeout();
    }


    @And("I enter password")
    public void iEnterPassword() {
        driver.findElement(By.xpath("/html/body/div/main/div/div/div/form/section/div/div/div/input[@id='password']")).sendKeys(password);
        Util.timeout();
    }


    @And("I click sign in button")
    public void iClickSignInButton() {
        driver.findElement(By.xpath("/html//div[@id='__next']//div[@class='login_formWrapper__IMoai']/form[1]/section/div[@class='Submit_buttonWrapper__33HZ0']")).click();
        Util.timeout();
    }


    @Then("I should enter the system")
    public void iShouldEnterTheSystem() {
        String xpath = "/html/body/div[@class='global-navigation logged-in']/div[@class='global-navigation__bottom']/div/div[@class='wds-dropdown__content']/ul//a[@href='http://community.fandom.com/wiki/User:Softwate-testing3']";
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))).click();
        assertEquals(driver.findElement(By.xpath("/html//div[@id='userProfileApp']//section[@class='user-identity-box']//h1[.='Softwate-testing3']")).getText(), username);
        Util.timeout();
    }

}

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;

public class LoginForm {
    WebDriver driver;
    HomePage HomePageObj;
    By Login_UserNameField = By.xpath("//input[@id='loginusername']");
    By Login_PasswordField = By.xpath("//input[@id='loginpassword']");
    By Login_LoginButton = By.xpath("//button[@onclick='logIn()']");

    public LoginForm(WebDriver driver) {
        this.driver = driver;
    }
    public void fillLoginForm(String Username, String Password) {
        waitFor(Login_UserNameField);
        System.out.println("********** Login UserName is : " + Username);
        driver.findElement(Login_UserNameField).sendKeys(Username);
        System.out.println("********** Login Password is : " + Password);
        driver.findElement(Login_PasswordField).sendKeys(Password);
        driver.findElement(Login_LoginButton).click();
    }
    public void verifyLogin(String Username, String Password){
        HomePageObj = new HomePage(driver);
        waitFor(HomePageObj.WelcomArea);
        String WelcomeAreaText = driver.findElement(HomePageObj.WelcomArea).getText();
        System.out.println("_________Welcome Area Text is : " + WelcomeAreaText);
        Assert.assertTrue(WelcomeAreaText.contains(Username));
    }
    private void waitFor(By element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }
}

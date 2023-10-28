package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;

public class SignUpForm {
    WebDriver driver;
    By SignUp_UserNameField = By.xpath("//input[@id='sign-username']");
    By SignUp_PasswordField = By.xpath("//input[@id='sign-password']");
    By SignUp_SignUpButton = By.xpath("//button[@onclick='register()']");
    String SignUpSuccessMessage = "Sign up successful.";
    public SignUpForm(WebDriver driver) {
        this.driver = driver;
    }

    public void fillSignUpForm(String Username, String Password) {
        waitFor(SignUp_SignUpButton);
        System.out.println("********** SingUp UserName is : " + Username);
        driver.findElement(SignUp_UserNameField).sendKeys(Username);
        System.out.println("********** SingUp Password is : " + Password);
        driver.findElement(SignUp_PasswordField).sendKeys(Password);
        driver.findElement(SignUp_SignUpButton).click();
    }

    public void verifySignUp() {
        WebDriverWait waitAlert = new WebDriverWait(driver, Duration.ofSeconds(5));
        waitAlert.until(ExpectedConditions.alertIsPresent());
        // Switch the driver context to the alert
        Alert alertDialog = driver.switchTo().alert();
        // Get the alert text
        String alertText = alertDialog.getText();
        // Click the OK button on the alert.
        Assert.assertEquals(alertText,SignUpSuccessMessage);
        alertDialog.accept();
    }

    private void waitFor(By element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }
}

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;

public class PlaceOrderForm {
    WebDriver driver;

    By NameField = By.xpath("//input[@id='name']");
    By CountryField = By.xpath("//input[@id='country']");
    By CityField = By.xpath("//input[@id='city']");
    By CardField = By.xpath("//input[@id='card']");
    By MonthField = By.xpath("//input[@id='month']");
    By YearField = By.xpath("//input[@id='year']");
    By PurchaseButton = By.xpath("//button[@onclick='purchaseOrder()']");
    By ConfirmOrderButton = By.xpath("//button[contains(text(),'OK')]");
    By ConfirmPurchaseMessageArea = By.xpath("//div[@class='sweet-alert  showSweetAlert visible']//h2");
    public String ConfirmOrderMessage = "Thank you for your purchase!";
    public PlaceOrderForm(WebDriver driver){
        this.driver = driver;
    }

    public void FillPlaceOrderForm(String Name, String Country, String City , String CardNo, String Month, String Year){
        waitFor(PurchaseButton);
        driver.findElement(NameField).sendKeys(Name);
        driver.findElement(CountryField).sendKeys(Country);
        driver.findElement(CityField).sendKeys(City);
        driver.findElement(CardField).sendKeys(CardNo);
        driver.findElement(MonthField).sendKeys(Month);
        driver.findElement(YearField).sendKeys(Year);
    }
    public void verifyCompletingPurchasingOrder(){
        driver.findElement(PurchaseButton).click();
        waitFor(ConfirmOrderButton);
        String ActualPurchaseMessage = driver.findElement(ConfirmPurchaseMessageArea).getText();
        Assert.assertEquals(ActualPurchaseMessage,ConfirmOrderMessage);
        driver.findElement(ConfirmOrderButton).click();

    }

    private void waitFor(By element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }
}

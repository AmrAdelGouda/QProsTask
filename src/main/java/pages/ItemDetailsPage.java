package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;

public class ItemDetailsPage {
    WebDriver driver;
    By AddToCartButton = By.xpath("//a[contains(text(),'Add to cart')]");
    By ItemNameTitle = By.xpath("//h2[@class='name']");
    String ItemName = "";
    String AddToCartSuccessMessage = "Product added.";
    public ItemDetailsPage(WebDriver driver){
        this.driver = driver;
    }
    public String getItemName(){
        ItemName = driver.findElement(ItemNameTitle).getText();
        return ItemName;
    }

    public void addItemToCart(){
        waitFor(AddToCartButton);
        driver.findElement(AddToCartButton).click();
        WebDriverWait waitAlert = new WebDriverWait(driver, Duration.ofSeconds(5));
        waitAlert.until(ExpectedConditions.alertIsPresent());
        Alert alertDialog = driver.switchTo().alert();
        String AlertText = alertDialog.getText();
        Assert.assertEquals(AlertText,AddToCartSuccessMessage);
        alertDialog.accept();
    }
    public void VerifyAddingItemToCart(){
        String ItemName = getItemName();
        System.out.println("Name of Item that Added to Cart : " + ItemName);
        HomePage HomePageObj = new HomePage(driver);
        HomePageObj.openCartPage();
        Assert.assertTrue(driver.findElement(By.xpath("//td[contains(text(),'"+ItemName+"')]"))
                .isDisplayed(),"Item Not Added to The Cart");
    }

    private void waitFor(By element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }
}

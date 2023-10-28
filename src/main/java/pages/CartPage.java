package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CartPage {
    WebDriver driver;
    By FirstItemInCart = By.xpath("//tr[@class='success'][1]");
    By PlaceOrderButton = By.xpath("//button[contains(text(),'Place Order')]");
    public CartPage(WebDriver driver){
        this.driver = driver;
    }
    public void deleteItemFromCartByName (String nameOfItem){
        waitFor(FirstItemInCart);
        driver.findElement(By.xpath("(//td[contains(text(),'"+nameOfItem+"')]//following-sibling::td/a[contains(text(),'Delete')])[1]")).click();
        WebDriverWait waitelement = new WebDriverWait(driver, Duration.ofSeconds(5));
        waitelement.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("(//td[contains(text(),'"+nameOfItem+"')])[1]"))));
    }
    public void openPlaceOrderForm(){
        waitFor(PlaceOrderButton);
        driver.findElement(PlaceOrderButton).click();

    }
    private void waitFor(By element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }
}

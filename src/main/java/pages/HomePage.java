package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;
import java.util.List;
import java.util.Random;

public class HomePage {
    WebDriver driver;
    ItemDetailsPage ItemDetailsPageObj;
    By SignUpButton = By.xpath("//a[@id='signin2']");
    By LoginButton = By.xpath("//a[@id='login2']");
    By LogoutButton = By.xpath("//a[@id='logout2']");
    By WelcomArea = By.xpath("//a[@id='nameofuser']");
    By CartButton = By.xpath("//a[@id='cartur']");
    By HometButton = By.xpath("//a[contains(text(),'Home')]");
    By firstItem = By.xpath("//div[@id='tbodyid']/div[1]");
    By ListCategories = By.xpath("//div[@class='list-group']/a");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void openSignUpForm() {
        waitFor(SignUpButton);
        driver.findElement(SignUpButton).click();
    }

    public void openLoginForm() {
        waitFor(LoginButton);
        driver.findElement(LoginButton).click();
    }

    public void openHomePage() {
        waitFor(HometButton);
        driver.findElement(HometButton).click();

    }
    public void openCartPage() {
        waitFor(CartButton);
        driver.findElement(CartButton).click();

    }
    public void signOut(){
        waitFor(LogoutButton);
        driver.findElement(LogoutButton).click();
    }

    public void openAllCategories() throws InterruptedException {
        waitFor(ListCategories);

        List<WebElement> ListOfCategories = driver.findElements(ListCategories);
        int SizeOfList = ListOfCategories.size();
        String[] categories = new String[SizeOfList];


        for (int i = 0; i < SizeOfList; i++) {
            System.out.println("Category \"" + ListOfCategories.get(i).getText() + "\" to be Checked ");
            categories[i] = ListOfCategories.get(i).getText();
        }

        for (int i = 0; i < SizeOfList; i++) {
            waitFor(By.xpath("//a[contains(text(),'" + categories[i] + "')]"));
            driver.findElement(By.xpath("//a[contains(text(),'" + categories[i] + "')]")).click();
            Thread.sleep(2000);
            List<WebElement> ListItems = driver.findElements(By.xpath("//div[@id='tbodyid']/div"));
            if (ListItems.size() > 0) {
                System.out.println("1st Page of list of  \"" + categories[i] + "\" has " + ListItems.size() + " Items");
            } else {
                System.out.println("List of  \"" + categories[i] + "\" is Empty");
            }
        }
    }

    public void openRandomCategory() throws InterruptedException {
        ItemDetailsPageObj = new ItemDetailsPage(driver);
        waitFor(ListCategories);

        List<WebElement> ListOfCategories = driver.findElements(ListCategories);
        int SizeOfList = ListOfCategories.size();
        String[] categories = new String[SizeOfList];
        for (int i = 0; i < SizeOfList; i++) {
            categories[i] = ListOfCategories.get(i).getText();
        }
        Random rand = new Random();
        int randomElement = rand.nextInt(SizeOfList);
        System.out.println("Random NO is : " + randomElement);
        System.out.println("The Selected Random Category is : \"" + categories[randomElement] + "\"");
        driver.findElement(By.xpath("//a[contains(text(),'" + categories[randomElement] + "')]")).click();
        Thread.sleep(2000);
        selectFirstItem();
        Assert.assertTrue(driver.findElement(ItemDetailsPageObj.AddToCartButton).isDisplayed(),"Item Details Page not Open");
    }

    public void selectFirstItem(){
        waitFor(firstItem);
        driver.findElement(firstItem).click();
    }

    private void waitFor(By element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }
}

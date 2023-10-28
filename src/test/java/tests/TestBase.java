package tests;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import pages.HomePage;

import java.util.concurrent.TimeUnit;

public class TestBase {
    public static WebDriver driver;
    Faker faker = new Faker();
    String Username = faker.name().username();
    String Password = faker.internet().password();
    public static String CreatedUsername = null;
    public static String CreatedPassword = null;

    @BeforeSuite
    public void startDriver(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.navigate().to("https://www.demoblaze.com");
    }

    @BeforeTest
    public void getUsernameAndPassword(){
        CreatedUsername = Username;
        CreatedPassword = Password;
    }

    @AfterTest
    public void logOut(){
        HomePage HomePageObj = new HomePage(driver);
        HomePageObj.signOut();
    }

    @AfterSuite
    public void stopDriver() {
        driver.quit();
    }
}

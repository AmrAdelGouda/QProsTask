package tests;

import org.testng.annotations.Test;
import pages.*;
public class RequiredTests extends TestBase{

    HomePage HomePageObject ;
    SignUpForm SignUpFormObj ;
    LoginForm LoginFormObj ;
    ItemDetailsPage ItemDetailsPageObj;
    CartPage CartPageObj;
    PlaceOrderForm PlaceOrderFormObj;

    public String AddedItem = "";
    public String Name= Username;
    public String Country= "Egypt";
    public String City= "Cairo";
    public String CardNo = "1111111111111111";
    public String CardMonth = "10";
    public String CardYear = "2023";



    @Test(priority = 1)
    public void verifySignUpProcess() {
        HomePageObject = new HomePage(driver);
        SignUpFormObj = new SignUpForm(driver);

        HomePageObject.openSignUpForm();
        SignUpFormObj.fillSignUpForm(CreatedUsername,CreatedPassword);
        SignUpFormObj.verifySignUp();
    }
    @Test(priority = 2)
    public void verifyLoginProcess() {
        HomePageObject = new HomePage(driver);
        LoginFormObj = new LoginForm(driver);

        HomePageObject.openLoginForm();
        LoginFormObj.fillLoginForm(CreatedUsername,CreatedPassword);
        LoginFormObj.verifyLogin(CreatedUsername,CreatedPassword);
    }
    @Test(priority = 3)
    public void verifyCategoriesHasItems() throws InterruptedException {
        HomePageObject = new HomePage(driver);

        HomePageObject.openHomePage();
        HomePageObject.openAllCategories();
    }
    @Test(priority = 4)
    public void addRandomItemToCart () throws InterruptedException {
        addingRandomItemToCart();
    }

    @Test(priority = 5 , dependsOnMethods = "addRandomItemToCart")
    public void verifyDeleteItemFromCart (){
        CartPageObj = new CartPage(driver);

        CartPageObj.deleteItemFromCartByName(AddedItem);
    }
    @Test(priority = 6)
    public void addItemToCartAndCheckout() throws InterruptedException {
        PlaceOrderFormObj = new PlaceOrderForm(driver);
        CartPageObj = new CartPage(driver);

        addingRandomItemToCart();
        CartPageObj.openPlaceOrderForm();
        PlaceOrderFormObj.FillPlaceOrderForm(Name, Country, City, CardNo, CardMonth, CardYear );
        PlaceOrderFormObj.verifyCompletingPurchasingOrder();
    }

    public void addingRandomItemToCart() throws InterruptedException {
        HomePageObject = new HomePage(driver);
        ItemDetailsPageObj = new ItemDetailsPage(driver);

        HomePageObject.openHomePage();
        HomePageObject.openRandomCategory();
        ItemDetailsPageObj.addItemToCart();
        AddedItem = ItemDetailsPageObj.getItemName();
        ItemDetailsPageObj.VerifyAddingItemToCart();
    }
}

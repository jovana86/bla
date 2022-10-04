package com.e2e;

import com.e2e.config.pages.shopping.JacketPage;
import com.e2e.config.pages.shopping.SearchPage;
import com.e2e.config.pages.shopping.ShoppingCartPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ShoppingTest extends TestBase {

    String baseUrl = "https://magento.softwaretestingboard.com/";
    SearchPage searchPage;
    JacketPage jacketPage;
    ShoppingCartPage shoppingCartPage;

    @Test(priority = 2, dataProvider = "shoppingSuccess", description = "Successful e2e shopping test with login")
    public void testShopping_Success(String quantity, String email, String password,
                                     String country, String city, String address, String phone, String zip,
                                     String title)throws InterruptedException {
        SearchPage searchPage = new SearchPage(TestBase.driver, waitTime);
        TestBase.driver.get(baseUrl);
        searchPage.enterSearch();


        JacketPage jacketPage = new JacketPage(TestBase.driver, waitTime);
        jacketPage.selectJacket();
        jacketPage.selectSizeOfJacket();

        jacketPage.selectColorOfJacket();

        jacketPage.inputQuantityOfJackets(quantity);
        jacketPage.clickAddToCartButton();

        Assert.assertTrue(jacketPage.isNumberInShoppingCartBadgeShown(quantity));

        jacketPage.clickShoppingCartIcon();
        jacketPage.clickProceedToCheckoutBtn();

        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(TestBase.driver, waitTime);
        shoppingCartPage.enterEmail(email);
        shoppingCartPage.enterPassword(password);
        shoppingCartPage.clickLogin();
        Thread.sleep(5000);
        shoppingCartPage.selectCountry(country);
        shoppingCartPage.enterStreetAddress(address);
        shoppingCartPage.enterZipCode(zip);
        shoppingCartPage.enterCity(city);
        shoppingCartPage.enterPhoneNumber(phone);
        Thread.sleep(5000);
        shoppingCartPage.clickNextButton();
        Thread.sleep(5000);
        shoppingCartPage.placeYourOrder();
        Assert.assertTrue(shoppingCartPage.isOrderPlaced(title));

        if (TestBase.DEMO) {
            try {
                Thread.sleep(TestBase.demoWait);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @DataProvider(name = "shoppingSuccess")
    public Object[][] shoppingSuccess() {
        Object[][] data = {
                {"1", "test001456@example.com", "Demo!1234", "Serbia", "Lazarevac", "Botanicka 37", "021345678", "11550", "Thank you for your purchase!"}
    };
        return data;
    }

    @Test(priority = 1, dataProvider = "shoppingFailed", description = "Successful e2e shopping test with login")
    public void testShopping_Failed(String description,String quantity, String email, String password,
                                     String country, String city, String address, String phone, String zip,
                                     String error)throws InterruptedException {
        SearchPage searchPage = new SearchPage(TestBase.driver, waitTime);
        TestBase.driver.get(baseUrl);
        searchPage.enterSearch();


        JacketPage jacketPage = new JacketPage(TestBase.driver, waitTime);
        jacketPage.selectJacket();
        jacketPage.selectSizeOfJacket();

        jacketPage.selectColorOfJacket();

        jacketPage.inputQuantityOfJackets(quantity);
        jacketPage.clickAddToCartButton();

        Assert.assertTrue(jacketPage.isNumberInShoppingCartBadgeShown(quantity));

        jacketPage.clickShoppingCartIcon();
        jacketPage.clickProceedToCheckoutBtn();

        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(TestBase.driver, waitTime);
        shoppingCartPage.enterEmail(email);
        shoppingCartPage.enterPassword(password);
        shoppingCartPage.clickLogin();
        Thread.sleep(5000);
        shoppingCartPage.selectCountry(country);
        shoppingCartPage.enterStreetAddress(address);
        shoppingCartPage.enterZipCode(zip);
        shoppingCartPage.enterCity(city);
        shoppingCartPage.enterPhoneNumber(phone);
        Thread.sleep(5000);
        shoppingCartPage.clickNextButton();
        Thread.sleep(5000);
        Assert.assertTrue(shoppingCartPage.isErrorMessageShown(error));
        driver.manage().deleteAllCookies();
        Thread.sleep(7000);



        if (TestBase.DEMO) {
            try {
                Thread.sleep(TestBase.demoWait);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @DataProvider(name = "shoppingFailed")
    public Object[][] shoppingFailed() {
        Object[][] data = {
                {"Missing password","1", "test011456@example.com", "", "Serbia", "Lazarevac", "Botanicka 37",
                        "021345678", "11550", "This is a required field."},
                {"Missing city","1", "test011456@example.com", "Demo!1234", "Serbia", "", "Botanicka 37",
                        "021345678", "11550", "This is a required field."},
                {"Missing street","1", "test011456@example.com", "Demo!1234", "Serbia", "Lazarevac", "",
                        "021345678", "11550", "This is a required field."},
                {"Missing phone number","1", "test011456@example.com", "Demo!1234", "Serbia", "Lazarevac", "Botanicka 37",
                        "", "11550", "This is a required field."},
                {"Missing zip code","1", "test011456@example.com", "Demo!1234", "Serbia", "Lazarevac", "Botanicka 37",
                        "021345678", "", "This is a required field."},

        };
        return data;
    }
}

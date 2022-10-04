package com.e2e;

import com.e2e.config.model.TestUser;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.e2e.config.pages.authentication.LoginPage;

public class LoginTest extends TestBase {
    LoginPage loginPage;
    String loginUrl = "https://magento.softwaretestingboard.com/customer/account/login/referer/aHR0cHM6Ly9tYWdlbnRvLnNvZnR3YXJldGVzdGluZ2JvYXJkLmNvbS8%2C/";

    @Test(priority = 2, enabled = true, description = "Successful basic login with valid credentials")
    public void testLogin_Success()throws InterruptedException {
        RegistrationTest registrationTest = new RegistrationTest();
        String username = registrationTest.username;
        String password = registrationTest.password;

        loginPage = new LoginPage(TestBase.driver, waitTime);
        TestBase.driver.get(loginUrl);

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);

        if (TestBase.DEMO) {
            try {
                Thread.sleep(TestBase.demoWait);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        loginPage.clickLoginBtn();
        if (TestBase.DEMO) {
            try {
                Thread.sleep(TestBase.demoWait);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Assert.assertTrue(loginPage.isUserLogged());
    }

    @Test(priority = 1,
            enabled = true,
            dataProvider = "invalidLoginData",
            description = "Unsuccessful login with invalid data provided")
    public void testLogin_Failed(String description, String username, String password, String errMessage)throws InterruptedException {
        loginPage = new LoginPage(TestBase.driver, waitTime);
        TestBase.driver.get(loginUrl);

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);

        if (TestBase.DEMO) {
            try {
                Thread.sleep(TestBase.demoWait);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        loginPage.clickLoginBtn();
        if (TestBase.DEMO) {
            try {
                Thread.sleep(TestBase.demoWait);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Assert.assertTrue(loginPage.isErrorMessageShown(errMessage));
    }

    @DataProvider(name = "invalidLoginData")
    public Object[][] invalidLoginData() {
        Object[][] data = {
                {"Missing username", "", "Demo!1234", "This is a required field."},
                {"Missing password", "trainer@example.com", "", "This is a required field."}
        };
        return data;
    }
}

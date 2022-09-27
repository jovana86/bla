package com.e2e;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.e2e.config.pages.authentication.LoginPage;

public class LoginTest extends TestBase {
    LoginPage loginPage;
    String loginUrl = "http://wagner.wang/Identity/Account/Login";

    @Test(priority = 2, enabled = true, description = "Successful basic login with valid credentials")
    public void testLogin_Success() {
        String username = RegistrationTest.testUser.getUsername();
        String password = RegistrationTest.testUser.getPassword();
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

    @Test(priority = 1, enabled = false, dataProvider = "invalidLoginData", description = "Unsuccessful login with invalid data provided")
    public void testLogin_Failed(String description, String username, String password, String errMessage) {
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

    @DataProvider(name = "invalidLoginData")
    public Object[][] invalidLoginData() {
        Object[][] data = {
                {"Missing username", "", "Demo!1234", "The Email field is required."},
                {"Missing password", "trainer@example.com", "", "The Password field is required."}
        };
        return data;
    }
}

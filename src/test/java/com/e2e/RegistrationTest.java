package com.e2e;

import com.e2e.config.model.TestUser;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.e2e.config.pages.authentication.RegistrationPage;

public class RegistrationTest extends TestBase {
    RegistrationPage registerPage;
    String registrationUrl = "https://magento.softwaretestingboard.com/customer/account/create/";
    public static TestUser testUser;
//    String username = "test00345@example.com";
//    String password = "Demo!1234";
//    String firstName = "Demo";
//    String lastName = "Cratic";
    String username = "test011456@example.com";
    String password = "Demo!1234";
    String firstName = "Demo";
    String lastName = "Cratic";


    @Test(priority = 2, description = "Successful user account registration")
    public void testRegister_Success() {
        testUser = new TestUser(username, password, firstName, lastName);
        registerPage = new RegistrationPage(TestBase.driver, waitTime);
        TestBase.driver.get(registrationUrl);

        registerPage.enterUsername(testUser.getUsername());
        registerPage.enterPassword(testUser.getPassword());
        registerPage.confirmPassword(testUser.getPassword());
        registerPage.enterFirstName(testUser.getFirstName());
        registerPage.enterLastName(testUser.getLastName());
        registerPage.clickRegisterBtn();

        Assert.assertTrue(registerPage.isUserRegistered());
    }

    @Test(priority = 1, enabled = true, dataProvider = "invalidRegistrationData", description = "Unsuccessful user account registration")
    public void testRegister_Failed(String description, String username, String password, String firstName, String lastName, String errMessage) {
        registerPage = new RegistrationPage(TestBase.driver, waitTime);
        TestBase.driver.get(registrationUrl);

        registerPage.enterUsername(username);
        registerPage.enterPassword(password);
        registerPage.confirmPassword(password);
        registerPage.enterFirstName(firstName);
        registerPage.enterLastName(lastName);

        if (TestBase.DEMO) {
            try {
                Thread.sleep(TestBase.demoWait);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        registerPage.clickRegisterBtn();
        boolean error = registerPage.isErrorMessageShown(errMessage);
        Assert.assertTrue(error);
    }

    @DataProvider(name = "invalidRegistrationData")
    public Object[][] invalidRegistrationData() {
        Object[][] data = {
                {"Missing email", "", "Demo!1234", "Demo", "Bradic", "This is a required field."},
                {"Missing password", "trainer@example.com", "", "Demo", "Gradic", "This is a required field."},
                {"Missing first name", "trainer@example.com", "Demo!1234", "", "Nadic", "This is a required field."},
                {"Missing last name", "trainer@example.com", "Demo!1234", "Demo", "", "This is a required field."}


        };
        return data;
    }
}

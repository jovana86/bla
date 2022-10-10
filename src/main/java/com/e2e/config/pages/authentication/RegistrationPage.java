package com.e2e.config.pages.authentication;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.e2e.config.utilities.SeleniumUtilities;

public class RegistrationPage {

    public static String ERR_MESSAGE_XPATH = "//div[@class='mage-error']";
    public static String USER_REGISTERED_XPATH = "//span[@class='logged-in']";

    WebDriver driver;
    int waitTime = 0;

    @FindBy(id = "email_address")
    private WebElement inputEmail;

    @FindBy(id = "password")
    private WebElement inputPassword;

    @FindBy(id = "password-confirmation")
    private WebElement inputConfirmPassword;

    @FindBy(id = "firstname")
    private WebElement inputName;

    @FindBy(id = "lastname")
    private WebElement inputLastName;

    @FindBy(xpath = "//button/span[text()='Create an Account']")
    private WebElement btnRegister;

    public RegistrationPage(WebDriver driver, int waitTime) {
        this.driver = driver;
        this.waitTime = waitTime;
        PageFactory.initElements(driver, this);
    }

    public void enterUsername(String email) {
        inputEmail.sendKeys(email);
    }

    public void enterPassword(String password) {
        inputPassword.sendKeys(password);
    }

    public void confirmPassword(String password) {
        inputConfirmPassword.sendKeys(password);
    }

    public void enterFirstName(String firstName) {
        inputName.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        inputLastName.sendKeys(lastName);
    }


    public void clickRegisterBtn() {
        btnRegister.click();
    }

    public boolean isUserRegistered() {
        WebElement logOut = driver.findElement(By.xpath(USER_REGISTERED_XPATH));
        return logOut.isDisplayed();
    }

    public boolean isErrorMessageShown(String message) {
        WebElement error = driver.findElement(By.xpath(String.format(ERR_MESSAGE_XPATH, message)));
        SeleniumUtilities.highlightControl(error, driver);
        return error.isDisplayed();
    }
}

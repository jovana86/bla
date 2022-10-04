package com.e2e.config.pages.authentication;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    public static String ERR_MESSAGE_CLASS = "mage-error";

    WebDriver driver;
    int waitTime = 0;

    @FindBy(id = "email")
    private WebElement inputEmail;

    @FindBy(id = "pass")
    private WebElement inputPassword;

    @FindBy(id = "send2")
    private WebElement loginButton;

    public LoginPage(WebDriver driver, int waitTime) {
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

    public void clickLoginBtn() {
        loginButton.click();
    }

    public boolean isUserLogged() {
        WebElement logOut = driver.findElement(By.className("logged-in"));
        return logOut.isDisplayed();
    }

    public boolean isErrorMessageShown(String message) {
        WebElement error = driver.findElement(By.className(ERR_MESSAGE_CLASS));
        return error.isDisplayed();
    }
}

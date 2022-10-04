package com.e2e.config.pages.shopping;

import com.e2e.config.utilities.SeleniumUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ShoppingCartPage {
    WebDriver driver;
    int waitTime= 3;

    public static String PAGE_TITLE_PURCHASE_XPATH = "//h1[@class='page-title']";
    public static String SELECT_COUNTRY_XPATH = "//select[@name='country_id']";
    public static String ERROR_MESSAGE_XPATH = "//div[@class='field-error']";


    @FindBy(id = "customer-email")
    private WebElement customerEmailField;
    @FindBy(id = "customer-password")
    private WebElement customerPasswordField;
    @FindBy(xpath = "//span[text()='Login']")
    private WebElement btnLogin;
    @FindBy(xpath = "//li//input[@name='street[0]']")
    private WebElement streetAddressField;
    @FindBy(xpath = "//input[@name='city']")
    private WebElement cityField;
    @FindBy(id = "W1I7KHH")
    private WebElement provinceSelectField;
    @FindBy(xpath = "//input[@name='postcode']")
    private WebElement zipCodeField;
    @FindBy(xpath = "//select[@name='country_id']")
    private WebElement countrySelectField;
    @FindBy(xpath = "//input[@name='telephone']")
    private WebElement phoneNumberField;
    @FindBy(xpath = "//div[@class='actions-toolbar']//span[text()='Next']")
    private WebElement btnNext;
    @FindBy(xpath = "//span[text()='Place Order']")
    private WebElement btnPlaceYourOrder;
    @FindBy(xpath = "//p[text()='Your order number is: ']")
    private WebElement orderNumber;

    public ShoppingCartPage(WebDriver driver, int waitTime) {
        this.driver = driver;
        this.waitTime = waitTime;
        PageFactory.initElements(driver,this);
    }

    public void enterEmail(String email){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(customerEmailField));
        customerEmailField.clear();
        customerEmailField.sendKeys(email);
        wait.until(ExpectedConditions.visibilityOf(customerPasswordField));
    }
    public void enterPassword(String password){
        customerPasswordField.clear();
        customerPasswordField.sendKeys(password);
    }
    public void clickLogin(){
        btnLogin.click();
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(8));
        wait.until(ExpectedConditions.visibilityOf(streetAddressField));
    }
    public void enterStreetAddress(String streetAddress){
        streetAddressField.clear();
        streetAddressField.sendKeys(streetAddress);
    }
    public void selectCountry(String country){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", countrySelectField);
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(8));
        wait.until(ExpectedConditions.elementToBeClickable(countrySelectField));
        Select select = new Select(driver.findElement(By.xpath(SELECT_COUNTRY_XPATH)));
        select.selectByVisibleText("Serbia");
    }
    public void enterCity(String city){
        cityField.clear();
        cityField.sendKeys(city);
    }
    public void enterZipCode(String zipCode){
        zipCodeField.clear();
        zipCodeField.sendKeys(zipCode);
    }
    public void enterPhoneNumber(String phoneNo){
        phoneNumberField.clear();
        phoneNumberField.sendKeys(phoneNo);
    }
    public void clickNextButton(){
        btnNext.click();
    }
    public void placeYourOrder(){
        btnPlaceYourOrder.click();
    }
    public boolean isOrderPlaced(String title){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(orderNumber));
        WebElement placedOrderTitle = driver.findElement(By.xpath(String.format(PAGE_TITLE_PURCHASE_XPATH,title)));
        SeleniumUtilities.highlightControl(placedOrderTitle,driver);
        return placedOrderTitle.isDisplayed();
    }
    public boolean isErrorMessageShown(String message) {
        WebElement error = driver.findElement(By.xpath(String.format(ERROR_MESSAGE_XPATH, message)));
        SeleniumUtilities.highlightControl(error, driver);
        return error.isDisplayed();

    }



}

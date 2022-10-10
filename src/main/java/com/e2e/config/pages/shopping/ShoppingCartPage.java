package com.e2e.config.pages.shopping;

import com.e2e.config.utilities.FrontActionsUtil;
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
import java.util.concurrent.TimeUnit;

public class ShoppingCartPage {
    WebDriver driver;
    int waitTime = 5;

    public static String PAGE_TITLE_PURCHASE_XPATH = "//h1[@class='page-title']";
    public static String SELECT_COUNTRY_XPATH = "//select[@name='country_id']";
    public static String ERROR_MESSAGE_XPATH = "//div[@class='field-error']";
    public static String PAYOUT_PAGE_URL = "https://magento.softwaretestingboard.com/checkout/#payment";
    public static String SUCCESS_PAGE_URL = "https://magento.softwaretestingboard.com/checkout/onepage/success/";


    @FindBy(id = "customer-email")
    private WebElement customerEmailField;
    @FindBy(id = "customer-password")
    private WebElement customerPasswordField;
    @FindBy(xpath = "//div[@class='actions-toolbar']//span[text()='Login']")
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
    @FindBy(xpath = "//td[text()='Best Way']")
    private WebElement bestWayOfPaying;
    @FindBy(xpath = "//div[@class='field-error']")
    private WebElement error;
    @FindBy(xpath = "//td[text()='Fixed']")
    private WebElement fixedRate;
    @FindBy(id = "shipping")
    private WebElement shippingAddressHeadline;

    public ShoppingCartPage(WebDriver driver, int waitTime) {
        this.driver = driver;
        this.waitTime = waitTime;
        PageFactory.initElements(driver, this);
    }

    public void enterEmail(String email) {
        FrontActionsUtil.waitForElementToBeVisible(driver, customerEmailField);
        customerEmailField.clear();
        customerEmailField.sendKeys(email);
        FrontActionsUtil.waitForElementToBeVisible(driver, customerPasswordField);
    }

    public void enterPassword(String password) {
        customerPasswordField.clear();
        customerPasswordField.sendKeys(password);
    }

    public void clickLogin() {
        btnLogin.click();
    }

    public void enterStreetAddress(String streetAddress) {
        FrontActionsUtil.actionScroll2ElementAndClick(driver, streetAddressField);
        streetAddressField.clear();
        streetAddressField.sendKeys(streetAddress);
    }

    public void selectCountry(String country) {
        FrontActionsUtil.waitForElementToBeClickable(driver, countrySelectField);

        Select select = new Select(driver.findElement(By.xpath(SELECT_COUNTRY_XPATH)));
        select.selectByVisibleText(country);

    }

    public void enterCity(String city) {
        cityField.clear();
        cityField.sendKeys(city);
    }

    public void enterZipCode(String zipCode) {
        zipCodeField.clear();
        zipCodeField.sendKeys(zipCode);
    }

    public void enterPhoneNumber(String phoneNo) {
        FrontActionsUtil.actionScroll2ElementAndClick(driver, phoneNumberField);
        phoneNumberField.clear();
        phoneNumberField.sendKeys(phoneNo);
    }

    public void clickNextButton() {
        btnNext.click();
    }

    public void waitForPageUrlLoads() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOf(bestWayOfPaying));
        wait.until(ExpectedConditions.urlMatches(PAYOUT_PAGE_URL));
        wait.until(ExpectedConditions.elementToBeClickable(btnPlaceYourOrder));
    }

    public void placeYourOrder() {
        btnPlaceYourOrder.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlMatches(SUCCESS_PAGE_URL));
    }

    public boolean isOrderPlaced(String title) {
        WebElement placedOrderTitle = driver.findElement(By.xpath(String.format(PAGE_TITLE_PURCHASE_XPATH, title)));
        SeleniumUtilities.highlightControl(placedOrderTitle, driver);
        return placedOrderTitle.isDisplayed();
    }

    public boolean isErrorMessageShown(String message) {
        WebElement error = driver.findElement(By.xpath(String.format(ERROR_MESSAGE_XPATH, message)));
        SeleniumUtilities.highlightControl(error, driver);
        return error.isDisplayed();

    }

    public void selectShippingMethod() {
        fixedRate.click();
    }


}

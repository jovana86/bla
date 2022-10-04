package com.e2e.config.pages.shopping;

import com.e2e.config.utilities.SeleniumUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class JacketPage {


    public static String NUMBER_IN_SHOPPING_CART_BADGE_XPATH = "//span[@class='counter-number']";
    WebDriver driver;
    int waitTime = 5;


    @FindBy(xpath = "//span[@class='product-image-container']")
    private WebElement jacketList;
    @FindBy(xpath = "//div[@class='product-add-form']//div[@class='swatch-option text']")
    //@FindBy(xpath = "//div[@id='option-label-size-143-item-166']")
    private WebElement sizeOfJacket;
    @FindBy(xpath = "//div[@class='swatch-option color']")
    //@FindBy(xpath = "//div[@id='option-label-color-93-item-52']")
    private WebElement colorOfJacket;
    @FindBy(id = "qty")
    private WebElement inputQuantityOfJackets;
    @FindBy(id = "product-addtocart-button")
    private WebElement btnAddToCart;
    @FindBy(xpath = "//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']")
    private WebElement confirmAddToCartMessage;
    @FindBy(xpath = "//span[@class='counter-number']")
    private WebElement numberInShoppingCart;
    @FindBy(xpath = "//a[@class='action showcart active']")
    private WebElement shoppingCartIcon;
    @FindBy(id = "top-cart-btn-checkout")
    private WebElement btnProceedToCheckout;

    public static String CONFIRM_MESSAGE_XPATH = "//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)'";



    public JacketPage(WebDriver driver, int waitTime){
        this.driver = driver;
        this.waitTime = waitTime;
        PageFactory.initElements(driver,this);
    }
    public void selectJacket(){
        jacketList.click();
    }
    public void selectSizeOfJacket(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(sizeOfJacket));

//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", sizeOfJacket);
        sizeOfJacket.click();
    }
    public void selectColorOfJacket(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(colorOfJacket));
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", colorOfJacket);
        colorOfJacket.click();
    }
    public void inputQuantityOfJackets(String quantity){
        inputQuantityOfJackets.clear();
        inputQuantityOfJackets.sendKeys(quantity);
    }
    public void clickAddToCartButton(){
        btnAddToCart.click();
    }
    public boolean isConfirmMsgShown(String message){
        WebElement confirmMsg = driver.findElement(By.xpath(String.format(CONFIRM_MESSAGE_XPATH, message)));
        SeleniumUtilities.highlightControl(confirmMsg, driver);
        return confirmMsg.isDisplayed();
    }
    public void clickShoppingCartIcon(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(numberInShoppingCart));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", numberInShoppingCart);
        numberInShoppingCart.click();
        wait.until(ExpectedConditions.elementToBeClickable(btnProceedToCheckout));
    }
    public void clickProceedToCheckoutBtn(){
        btnProceedToCheckout.click();
    }

    public boolean isNumberInShoppingCartBadgeShown(String quantity){
        WebElement numberOnBadge = driver.findElement(By.xpath(String.format
                (NUMBER_IN_SHOPPING_CART_BADGE_XPATH,quantity)));
        SeleniumUtilities.highlightControl(numberOnBadge,driver);
        return numberOnBadge.isDisplayed();

    }






}

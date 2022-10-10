package com.e2e.config.pages.shopping;

import com.e2e.config.utilities.FrontActionsUtil;
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
import java.util.ArrayList;

public class JacketPage {

    WebDriver driver;
    int waitTime = 5;
    public static String NUMBER_IN_SHOPPING_CART_BADGE_XPATH = "//span[@class='counter-number']";
    public static String CONFIRM_MESSAGE_XPATH = "//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)'";
    public static String CHECKOUT_PAGE_URL = "https://magento.softwaretestingboard.com/checkout/#shipping";


    @FindBy(xpath = "//span[@class='product-image-container']")
    private WebElement jacketList;
    @FindBy(xpath = "//div[@class='product-add-form']//div[@class='swatch-option text']")
    private WebElement sizeOfJacket;
    @FindBy(xpath = "//div[@class='swatch-option color']")
    private WebElement colorOfJacket;
    @FindBy(id = "qty")
    private WebElement inputQuantityOfJackets;
    @FindBy(id = "product-addtocart-button")
    private WebElement btnAddToCart;
    @FindBy(xpath = "//span[@class='counter-number']")
    private WebElement numberInShoppingCart;
    @FindBy(id = "top-cart-btn-checkout")
    private WebElement btnProceedToCheckout;

    public JacketPage(WebDriver driver, int waitTime) {
        this.driver = driver;
        this.waitTime = waitTime;
        PageFactory.initElements(driver, this);
    }

    public void selectJacket() {
        jacketList.click();
    }

    public void selectSizeOfJacket() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(sizeOfJacket));
        sizeOfJacket.click();
    }

    public void selectColorOfJacket() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(colorOfJacket));
        colorOfJacket.click();
    }

    public void inputQuantityOfJackets(String quantity) {
        inputQuantityOfJackets.clear();
        inputQuantityOfJackets.sendKeys(quantity);
    }

    public void clickAddToCartButton() {
        btnAddToCart.click();
    }

    public void clickShoppingCartIcon() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        FrontActionsUtil.actionScroll2ElementAndClick(driver, numberInShoppingCart);
        wait.until(ExpectedConditions.elementToBeClickable(btnProceedToCheckout));
    }

    public void clickProceedToCheckoutBtn() {
        btnProceedToCheckout.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlMatches(CHECKOUT_PAGE_URL));
    }

    public boolean isNumberInShoppingCartBadgeShown(String quantity) {
        WebElement numberOnBadge = driver.findElement(By.xpath(String.format
                (NUMBER_IN_SHOPPING_CART_BADGE_XPATH, quantity)));
        SeleniumUtilities.highlightControl(numberOnBadge, driver);
        return numberOnBadge.isDisplayed();
    }

    public WebElement getSize(int index) {
        ArrayList<WebElement> chooseSize = new ArrayList<WebElement>(driver.findElements(By.xpath("//div[@class='product-add-form']//div[@class='swatch-option text']")));
        return chooseSize.get(index);
    }

    public WebElement getColor(int index) {
        ArrayList<WebElement> chooseColor = new ArrayList<WebElement>(driver.findElements(By.xpath("//div[@class='swatch-option color']")));
        return chooseColor.get(index);
    }

    public void selectSize(int index) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(getSize(index)));
        getSize(index).click();
    }

    public void selectSize() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(sizeOfJacket));
        sizeOfJacket.click();
    }

    public void selectColor(int index) {
        getColor(index).click();
    }


}

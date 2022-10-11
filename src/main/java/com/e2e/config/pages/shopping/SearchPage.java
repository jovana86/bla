package com.e2e.config.pages.shopping;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchPage {
    WebDriver driver;
    int waitTime = 5;
    public static String JACKET_LIST_XPATH = "//span[@class='product-image-wrapper']";
    public static String SEARCH_RESULT_XPATH = "//dd/a[contains(text(),'jacket')]";


    public static String SEARCH_PRODUCT = "jacket";

    @FindBy(id = "search")
    private WebElement btnSearch;
    @FindBy(xpath = "//dd/a[contains(text(),'jacket')]")
    private WebElement searchResult;

    public SearchPage(WebDriver driver, int waitTime) {
        this.driver = driver;
        this.waitTime = waitTime;
        PageFactory.initElements(driver,this);
    }


    public void enterSearch(){
        btnSearch.sendKeys(SEARCH_PRODUCT);
        btnSearch.sendKeys(Keys.ENTER);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SEARCH_RESULT_XPATH)));
    }

}

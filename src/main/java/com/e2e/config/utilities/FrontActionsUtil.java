package com.e2e.config.utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class FrontActionsUtil {

    public static void actionClick(WebDriver driver, WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().build().perform();
    }

    public static void move2ElementAndClick(WebDriver driver, WebElement element) {
        Actions actions =  new Actions(driver);
        actions.moveToElement(element).build().perform();
        actions.click(element).build().perform();
    }

    public static void actionScroll2ElementAndClick(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        actionClick(driver, element);
    }

    public static void actionSendKeys(WebDriver driver, WebElement element, String keys, boolean clickEnter) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
        actions.click().sendKeys(keys).perform();
        if (clickEnter) {
            actions.sendKeys(Keys.ENTER).perform();
        }

    }

    public static void actionScroll2ElementSendKeys(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

    }

    public static void actionSwitchToElement(WebDriver driver, WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
    }

    public static void clickOnElementByJS(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click(true);", element);

    }
}

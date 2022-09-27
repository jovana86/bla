package com.e2e.config.utilities;

import com.e2e.config.config.ConfigurationManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SeleniumUtilities {
    public static String HIGHLIGHT_COLOR = "yellow";
    public static String HIGHLIGHT_BORDER = "border: 2px solid red";
    public static String HIGHLIGHT_CSS = "arguments[0].setAttribute('style', 'background: %s; %s;');";
    public static String REMOVE_HIGHLIGHT_CSS = "arguments[0].setAttribute('style', 'background: %s; border: 0;');";

    public static void highlightControl(WebElement selectedCtrl, WebDriver driver){
        String bkgctrlr = selectedCtrl.getCssValue("background");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(String.format(HIGHLIGHT_CSS, HIGHLIGHT_COLOR, HIGHLIGHT_BORDER), selectedCtrl);
        try {
            Thread.sleep(ConfigurationManager.configuration().ctrlHighLightTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        js.executeScript(String.format(REMOVE_HIGHLIGHT_CSS, bkgctrlr), selectedCtrl);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public static void findControlByXpathAndClick(WebDriver driver, WebElement ctrl, int waitTime, String xpathExp) {

        ctrl = (new WebDriverWait(driver, Duration.ofSeconds(waitTime))).
                until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathExp)));
        if (ctrl != null) {
            ctrl.click();
        }
    }
    public static boolean findControlByXpathAndHighLight(WebDriver driver, WebElement ctrl, int waitTime, String xpathExp) {
        boolean success = false;
        ctrl = (new WebDriverWait(driver, Duration.ofSeconds(waitTime))).
                until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathExp)));
        if (ctrl != null) {
            highlightControl(ctrl, driver);
            success = true;
        }
        return success;
    }

    public static WebElement findControlByXpathAndHighLightReturnCtrl(WebDriver driver, WebElement ctrl, int waitTime, String xpathExp) {

        ctrl = (new WebDriverWait(driver, Duration.ofSeconds(waitTime))).
                until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathExp)));
        if (ctrl != null) {
            FrontActionsUtil.actionSwitchToElement(driver, ctrl);
            highlightControl(ctrl, driver);
        }
        return ctrl;
    }

    /**
     * Try to locate desired control within specified period of time and, if located, click on it and return true as result
     *
     * @param driver   Driver instance
     * @param ctrl     Control to be founded
     * @param waitTime Time to wait until presence of control is detected
     * @param xpathExp Xpath expression used to locate element
     * @return true - if control founded and clicked on
     */
    public static boolean findControlByXpathAndClickInform(WebDriver driver, WebElement ctrl, int waitTime, String xpathExp) {
        boolean success = false;
        ctrl = (new WebDriverWait(driver, Duration.ofSeconds(waitTime))).
                until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathExp)));
        if (ctrl != null) {
            ctrl.click();
            success = true;
        }
        return success;
    }


    public static WebElement findControlByXpathReturnCtrl(WebDriver driver, WebElement ctrl, int waitTime, String xpathExp) {

        ctrl = (new WebDriverWait(driver, Duration.ofSeconds(waitTime))).
                until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathExp)));
        return ctrl;
    }

}

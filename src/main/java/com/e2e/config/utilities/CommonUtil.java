package com.e2e.config.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CommonUtil {

    public static void clkElementFromListByTxt(List<WebElement> list, String txt) {
        for (WebElement element : list) {
            if (element.getText().equals(txt)) {
                element.click();
                break;
            }
        }
    }

    public static String formatXpath(String xpath, Object... param) {
        return String.format(xpath, param);
    }

    public static WebElement buildWebElement(WebDriver driver, String xpath, Object... param) {
        return driver.findElement(By.xpath(formatXpath(xpath, param)));
    }
}

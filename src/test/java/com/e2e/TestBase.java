package com.e2e;

import com.e2e.config.config.ConfigurationManager;
import com.e2e.config.driver.DriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;

public abstract class TestBase {
    public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd.HHmmss");
    protected static final boolean DEMO = true;
    protected static int demoWait = 2000;
    private final String urlReport = System.getProperty("user.dir") + "\\test-output\\SmokeSuit" + "SmokeTest.html";
    boolean autoShowReport = ConfigurationManager.configuration().showReport();
    protected static WebDriver driver;
    protected static WebDriverWait wait;
    protected int waitTime = 5;
    public static boolean dockingAllowed = ConfigurationManager.configuration().dockingAllowed();
    public static String dockSide = ConfigurationManager.configuration().dockSide();

    @BeforeSuite(alwaysRun = true)
    public void suiteSetup() {
        String browserName = ConfigurationManager.configuration().browser();
        driver = DriverManager.createInstance(browserName);
        driver.manage().window().maximize();

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(waitTime));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(waitTime));

        wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigurationManager.configuration().waitTime()));

        if (dockingAllowed)
            DriverManager.dockBrowserWindow(dockSide);
    }


    @AfterSuite(alwaysRun = true)
    public void suiteTearDown() {
        if (driver != null) {
            driver.quit();
        }
        if (autoShowReport) {
            File htmlFile = new File(urlReport);
            try {
                Desktop.getDesktop().browse(htmlFile.toURI());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @BeforeMethod(alwaysRun = true)
    public void clearCacheAndCookies() {
        driver.manage().deleteAllCookies();
        driver.get("chrome://settings/clearBrowserData");
        driver.findElement(By.xpath("//settings-ui")).sendKeys(Keys.ENTER);
    }

    @AfterMethod(alwaysRun = true)
    public void methodPostConditions(ITestResult testResult)
            throws IOException {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            takeScreenshot(testResult.getName(), Arrays.toString(testResult.getParameters()));
        }
    }

    public static void takeScreenshot(String testMethod, String testParams) {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(srcFile, new File(ConfigurationManager.configuration().path25screenShots() + testMethod + "-" + testParams + sdf.format(new Timestamp(System.currentTimeMillis())) + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

package com.e2e;

import com.e2e.config.pages.footerLinks.FooterLinksPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FooterLinkTest extends TestBase{
    FooterLinksPage footerLinksPage;
    String baseUrl = "https://magento.softwaretestingboard.com/";

    @Test(priority = 5,enabled = true,description = "Connection to links in footer page - About Us")
    public void aboutUsTest(){
        footerLinksPage = new FooterLinksPage(TestBase.driver,waitTime);
        TestBase.driver.get(baseUrl);
        footerLinksPage.aboutUsClick();

        if (TestBase.DEMO) {
            try {
                Thread.sleep(TestBase.demoWait);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Assert.assertTrue(footerLinksPage.isAboutUsHeadlineShown());
    }
    @Test(priority = 5,enabled = true,description = "Connection to links in footer page - Customer Service")
    public void customerServiceTest(){
        footerLinksPage = new FooterLinksPage(TestBase.driver,waitTime);
        TestBase.driver.get(baseUrl);
        footerLinksPage.customerServiceClick();

        if (TestBase.DEMO) {
            try {
                Thread.sleep(TestBase.demoWait);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Assert.assertTrue(footerLinksPage.isCustomerServiceHeadlineShown());
    }
    @Test(priority = 5,enabled = true,description = "Connection to links in footer page - Contact Us")
    public void contactUsTest(){
        footerLinksPage = new FooterLinksPage(TestBase.driver,waitTime);
        TestBase.driver.get(baseUrl);
        footerLinksPage.contactUsClick();

        if (TestBase.DEMO) {
            try {
                Thread.sleep(TestBase.demoWait);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Assert.assertTrue(footerLinksPage.isContactUsPageShown());
    }
    @Test(priority = 5,enabled = true,description = "Connection to links in footer page - Write For Us")
    public void writeForUsTest(){
        footerLinksPage = new FooterLinksPage(TestBase.driver,waitTime);
        TestBase.driver.get(baseUrl);
        footerLinksPage.writeForUsClick();

        if (TestBase.DEMO) {
            try {
                Thread.sleep(TestBase.demoWait);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Assert.assertTrue(footerLinksPage.isWriteForUsHeadlineShown());
    }
    @Test(priority = 5,enabled = true,description = "Connection to links in footer page - Popular Search Terms")
    public void searchTermsTest(){
        footerLinksPage = new FooterLinksPage(TestBase.driver,waitTime);
        TestBase.driver.get(baseUrl);
        footerLinksPage.searchTermsClick();

        if (TestBase.DEMO) {
            try {
                Thread.sleep(TestBase.demoWait);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Assert.assertTrue(footerLinksPage.isSearchTermsHeadlineShown());
    }
    @Test(priority = 5,enabled = true,description = "Connection to links in footer page - Privacy and cookie policy")
    public void privacyPolicyTest(){
        footerLinksPage = new FooterLinksPage(TestBase.driver,waitTime);
        TestBase.driver.get(baseUrl);
        footerLinksPage.privacyPolicyClick();

        if (TestBase.DEMO) {
            try {
                Thread.sleep(TestBase.demoWait);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Assert.assertTrue(footerLinksPage.isPrivacyPolicyHeadlineShown());
    }
    @Test(priority = 5,enabled = true,description = "Connection to links in footer page - Advanced search")
    public void advancedSearchTest(){
        footerLinksPage = new FooterLinksPage(TestBase.driver,waitTime);
        TestBase.driver.get(baseUrl);
        footerLinksPage.advancedSearchClick();

        if (TestBase.DEMO) {
            try {
                Thread.sleep(TestBase.demoWait);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Assert.assertTrue(footerLinksPage.isAdvancedSearchHeadlineShown());
    }
    @Test(priority = 5,enabled = true,description = "Connection to links in footer page - Orders and Returns")
    public void ordersAndReturnsTest(){
        footerLinksPage = new FooterLinksPage(TestBase.driver,waitTime);
        TestBase.driver.get(baseUrl);
        footerLinksPage.ordersAndReturnsClick();

        if (TestBase.DEMO) {
            try {
                Thread.sleep(TestBase.demoWait);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Assert.assertTrue(footerLinksPage.isOrdersAndReturnsHeadlineShown());
    }
}

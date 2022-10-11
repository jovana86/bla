package com.e2e.config.pages.footerLinks;
import com.e2e.config.utilities.FrontActionsUtil;
import org.checkerframework.checker.index.qual.PolyUpperBound;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import javax.swing.plaf.PanelUI;

    public class FooterLinksPage {
        WebDriver driver;
        int waitTime = 3;
        String message;
        public static String ABOUT_US_URL ="https://magento.softwaretestingboard.com/about-us";
        public static String ABOUT_US_HEADLINE_XPATH = "//h1/span[text()='About us']";
        public static String CUSTOMER_SERVICE_HEADLINE_XPATH = "//h1/span[text()='Customer Service']";
        public static String CONTACT_US_XPATH = "//strong[text()='Contact Us']";
        public static String WRITE_FOR_US_XPATH = "//h1[text()='Write For Us']";
        public static String SEARCH_TERMS_HEADLINE_XPATH = "//span[text()='Popular Search Terms']";
        public static String PRIVACY_POLICY_HEADLINE_XPATH = "//span[text()='Privacy Policy']";
        public static String ADVANCED_SEARCH_HEADLINE_XPATH = "//span[text()='Advanced Search']";
        public static String ORDERS_AND_RETURNS_HEADLINE_XPATH = "//span[text()='Orders and Returns']";

        @FindBy(xpath = "//a[text()='About us']")
        private WebElement btnAboutUs;
        @FindBy(xpath = "//a[text()='Customer Service']")
        private WebElement btnCustomerService;
        @FindBy(xpath = "//a[text()='Contact Us']")
        private WebElement btnContactUs;
        @FindBy(xpath = "//a[text()='Write for Us']")
        private WebElement btnWriteForUs;
        @FindBy(xpath = "//a[text()='Search Terms']")
        private WebElement btnSearchTerms;
        @FindBy(xpath = "//a[text()='Privacy and Cookie Policy']")
        private WebElement btnPrivacyPolicy;
        @FindBy(xpath = "//a[text()='Advanced Search']")
        private WebElement btnAdvancedSearch;
        @FindBy(xpath = "//a[text()='Orders and Returns']")
        private WebElement btnOrdersAndReturns;
        @FindBy(xpath = "//h1/span[text()='About us']")
        private WebElement headlineAboutUs;

        public FooterLinksPage(WebDriver driver, int waitTime) {
            this.driver = driver;
            this.waitTime = waitTime;
            PageFactory.initElements(driver,this);
        }

        public void aboutUsClick(){
            FrontActionsUtil.actionScroll2ElementAndClick(driver,btnAboutUs);
        }
        public void customerServiceClick(){
            FrontActionsUtil.actionScroll2ElementAndClick(driver,btnCustomerService);
        }
        public void contactUsClick(){
            FrontActionsUtil.actionScroll2ElementAndClick(driver,btnContactUs);
        }
        public void writeForUsClick(){
            FrontActionsUtil.actionScroll2ElementAndClick(driver,btnWriteForUs);
        }
        public void searchTermsClick(){
            FrontActionsUtil.actionScroll2ElementAndClick(driver,btnSearchTerms);
        }
        public void privacyPolicyClick(){
            FrontActionsUtil.actionScroll2ElementAndClick(driver,btnPrivacyPolicy);
        }
        public void advancedSearchClick(){
            FrontActionsUtil.actionScroll2ElementAndClick(driver,btnAdvancedSearch);
        }
        public void ordersAndReturnsClick(){
            FrontActionsUtil.actionScroll2ElementAndClick(driver,btnOrdersAndReturns);
        }
        public boolean isAboutUsHeadlineShown(){
            FrontActionsUtil.isHeadlineShown(driver,message,ABOUT_US_HEADLINE_XPATH );
            return true;
        }
        public boolean isCustomerServiceHeadlineShown(){
            FrontActionsUtil.isHeadlineShown(driver,message,CUSTOMER_SERVICE_HEADLINE_XPATH);
            return true;
        }
        public boolean isContactUsPageShown(){
            FrontActionsUtil.isHeadlineShown(driver,message,CONTACT_US_XPATH);
            return true;
        }
        public boolean isWriteForUsHeadlineShown(){
            FrontActionsUtil.isHeadlineShown(driver,message,WRITE_FOR_US_XPATH);
            return true;
        }
        public boolean isSearchTermsHeadlineShown(){
            FrontActionsUtil.isHeadlineShown(driver,message,SEARCH_TERMS_HEADLINE_XPATH);
            return true;
        }
        public boolean isPrivacyPolicyHeadlineShown(){
            FrontActionsUtil.isHeadlineShown(driver,message,PRIVACY_POLICY_HEADLINE_XPATH);
            return true;
        }
        public boolean isAdvancedSearchHeadlineShown(){
            FrontActionsUtil.isHeadlineShown(driver,message,ADVANCED_SEARCH_HEADLINE_XPATH);
            return true;
        }
        public boolean isOrdersAndReturnsHeadlineShown(){
            FrontActionsUtil.isHeadlineShown(driver,message,ORDERS_AND_RETURNS_HEADLINE_XPATH);
            return true;

        }
    }


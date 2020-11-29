package com.mobile.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class WelcomePage extends BasePageObject {

    private By loginScreenLocator = MobileBy.AccessibilityId ("Login Screen");
    private By listDemoLocator = MobileBy.AccessibilityId ("List Demo");
    private By webViewLocator = MobileBy.AccessibilityId ("Webview Demo");
    private By verifyPhoneNumberLocator = MobileBy.AccessibilityId ("Verify Phone Number");

    public WelcomePage(AppiumDriver driver, Logger log) {
        super (driver, log);
    }


    public void goToLoginScreen() {
        waitForVisibilityOf (loginScreenLocator, 10);
        WebElement loginScreen = find (loginScreenLocator);
        loginScreen.click ();
    }

    public void goToListDemo() {
        waitForVisibilityOf (listDemoLocator, 10);
        WebElement listDemo = find (listDemoLocator);
        listDemo.click ();
    }

    public void goToWebView() {
        waitForVisibilityOf (webViewLocator, 10);
        WebElement webView = find (webViewLocator);
        webView.click ();
    }

    public void goToVerifyPhoneNumber() {
        waitForVisibilityOf (verifyPhoneNumberLocator, 10);
        find (verifyPhoneNumberLocator).click ();
    }
}

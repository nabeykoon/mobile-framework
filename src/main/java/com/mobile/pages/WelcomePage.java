package com.mobile.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class WelcomePage extends BasePageObject {
    public WelcomePage(AppiumDriver driver, Logger log) {
        super (driver, log);
    }

    private By loginScreenLocator = MobileBy.AccessibilityId ("Login Screen");
    private By listDemoLocator = MobileBy.AccessibilityId ("List Demo");

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
}

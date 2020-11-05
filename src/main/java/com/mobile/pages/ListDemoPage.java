package com.mobile.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ListDemoPage extends BasePageObject {

    public ListDemoPage(AppiumDriver driver, Logger log) {
        super (driver, log);
    }

    public void selectListOption(String optionName){
        By optionLocator = MobileBy.AccessibilityId (optionName);
        waitForVisibilityOf (optionLocator, 10);
        WebElement element = find (optionLocator);
        element.click ();
    }
}




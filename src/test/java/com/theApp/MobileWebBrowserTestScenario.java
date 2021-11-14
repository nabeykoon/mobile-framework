package com.theApp;

import com.mobile.core.base.TestUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MobileWebBrowserTestScenario extends TestUtilities {

    private static final String siteUrl = "https://appiumpro.com/";

    @Test
    public void testAppiumWebPage() {
        //simple test written to test web browser testing without page Objects
        WebDriverWait wait = new WebDriverWait (getRemoteDriver (), 30);
        getRemoteDriver ().get (siteUrl);
        wait.until (ExpectedConditions.visibilityOfElementLocated (By.id("toggleMenu"))).click();
        wait.until (ExpectedConditions.presenceOfElementLocated (By.linkText ("Contact"))).click ();
        wait.until (ExpectedConditions.presenceOfElementLocated (By.id ("contactEmail"))).sendKeys ("nadeera19848@yahoo.com");
        getRemoteDriver ().findElement (By.id ("contactText")).sendKeys ("test Message");
        getRemoteDriver ().findElement (By.xpath ("//input[@value='Send']")).click ();
        //String msg = wait.until (ExpectedConditions.presenceOfElementLocated (By.className ("response___1yZzw error___2pSWM"))).getText ();
        //assert (msg.contains ("You must fill out the Captcha box"));
    }
}

package com.mobile.pages;

import io.appium.java_client.AppiumDriver;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class BasePageObject {

    protected AppiumDriver driver;
    protected Logger log;

    public BasePageObject(AppiumDriver driver, Logger log) {
        this.driver = driver;
        this.log = log;
    }

    /**
     * Find element using given locator
     */
    protected WebElement find(By locator) {
        return driver.findElement (locator);
    }

    /**
     * Find all elements using given locator
     */
    protected List<WebElement> findAll(By locator) {
        return driver.findElements (locator);
    }

    /**
     * Get title of current page
     * @return
     */
    public String getCurrentPageTitle() {
        return driver.getTitle();
    }

    /**
     * Wait until page title contains given page title
     * @param pageTitle
     */
    protected void waitForPageTitleContains(String pageTitle){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until (ExpectedConditions.titleContains (pageTitle));
    }


    /**
     * Wait for given number of seconds for element with given locator to be visible
     * on the page
     */
    protected void waitForVisibilityOf(By locator, Integer... timeOutInSeconds) {
        int attempts = 0;
        while (attempts < 2) {
            try {
                waitFor(ExpectedConditions.visibilityOfElementLocated(locator),
                        (timeOutInSeconds.length > 0 ? timeOutInSeconds[0] : null));
                break;
            } catch (StaleElementReferenceException e) {
            }
            attempts++;
        }
    }

    /**
     * Wait for alert present and then switch to it
     * @return
     */
    protected Alert switchToAlert() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.alertIsPresent());
        return driver.switchTo().alert();
    }


    //===========================private methods========================================
    /**
     * Wait for specific ExpectedCondition for the given amount of time in seconds
     */
    private void waitFor(ExpectedCondition<WebElement> condition, Integer timeOutInSeconds) {
        timeOutInSeconds = timeOutInSeconds != null ? timeOutInSeconds : 30;
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.until(condition);
    }

}

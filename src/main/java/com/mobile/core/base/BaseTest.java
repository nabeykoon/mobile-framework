package com.mobile.core.base;

import io.appium.java_client.AppiumDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.lang.reflect.Method;
import java.net.MalformedURLException;

public class BaseTest {
    private static final ThreadLocal<AppiumDriver> appiumDrivers = new ThreadLocal<AppiumDriver> ();
    private static final ThreadLocal<RemoteWebDriver> remoteDrivers = new ThreadLocal<RemoteWebDriver> ();
    protected Logger log;

    protected String testSuiteName;
    protected String testName;
    protected String testMethodName;

    public AppiumDriver getAppiumDriver() {
        return appiumDrivers.get ();
    }

    public RemoteWebDriver getRemoteDriver() {
        return remoteDrivers.get ();
    }

    /**
     * @param method
     * @param context
     * @param executionPlatform - predefoned platform for execution. Should be provided from TestNG xml. Default = emulator-nexus5-android9
     * @param driverType        - Which driver to be used in execution - "browserStack" should be used for execute using cloud provider BrowserStack, "local" should be used for execute using local Appium server and "RemoteWebDriver" should be used for execute using remote web driver(mobile browser testing)
     * @throws MalformedURLException
     */
    @Parameters({"executionPlatform", "driverType"})
    @BeforeMethod(alwaysRun = true)
    public void setup(Method method, ITestContext context, @Optional("emulator-nexus5-android9") String executionPlatform, @Optional("local") String driverType) throws MalformedURLException {

        log = LogManager.getLogger (method.getName ());
        this.testSuiteName = context.getSuite ().getName ();
        this.testName = context.getCurrentXmlTest ().getName ();
        this.testMethodName = method.getName ();

        AppiumDriverFactory appiumDriverFactory = new AppiumDriverFactory (executionPlatform, log);
        if (driverType.equals ("browserStack")) {
            AppiumDriver driver = appiumDriverFactory.getBrowserStackDriver (testName);
            appiumDrivers.set (driver);
        } else if (driverType.equals ("local")) {
            AppiumDriver driver = appiumDriverFactory.getDriver ();
            appiumDrivers.set (driver);
        } else {
            RemoteWebDriver driver = appiumDriverFactory.getRemoteWebDriver ();
            remoteDrivers.set (driver);
        }

    }

    @AfterMethod
    public void tearDown() {
        log.info ("close driver");
        if (getAppiumDriver () != null){
            getAppiumDriver ().quit ();
        }
        if (getRemoteDriver () != null){
            getRemoteDriver ().quit ();
        }
        appiumDrivers.remove ();
        remoteDrivers.remove ();
    }

}

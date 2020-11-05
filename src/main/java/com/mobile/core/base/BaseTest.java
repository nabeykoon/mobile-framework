package com.mobile.core.base;

import io.appium.java_client.AppiumDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.lang.reflect.Method;
import java.net.MalformedURLException;

public class BaseTest {
    private static final ThreadLocal<AppiumDriver> drivers = new ThreadLocal<AppiumDriver> ();
    protected Logger log;

    protected String testSuiteName;
    protected String testName;
    protected String testMethodName;

    public AppiumDriver getDriver() {
        return drivers.get ();
    }

    @Parameters({"executionPlatform", "useBrowserStack"})
    @BeforeMethod(alwaysRun = true)
    public void setup(Method method, ITestContext context, @Optional("emulator-nexus5-android9") String executionPlatform, @Optional("false") String useBrowserStack) throws MalformedURLException {

        log = LogManager.getLogger (method.getName ());
        this.testSuiteName = context.getSuite ().getName ();
        this.testName = context.getCurrentXmlTest ().getName ();
        this.testMethodName = method.getName ();

        AppiumDriverFactory appiumDriverFactory = new AppiumDriverFactory (executionPlatform, log);
        if (useBrowserStack.equals ("true")){
            AppiumDriver driver = appiumDriverFactory.getBrowserStackDriver (testName);
            drivers.set (driver);
        }
        else{
            AppiumDriver driver = appiumDriverFactory.getDriver ();
            drivers.set (driver);
        }

    }

    @AfterMethod
    public void tearDown() {
        log.info ("close driver");
        getDriver ().quit ();
        drivers.remove ();
    }

}

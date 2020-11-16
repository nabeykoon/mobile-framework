package com.mobile.core.base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
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
     * @param platform         - android or ios
     * @param capabilitiesDesc - predefined platform for execution. Should be provided from TestNG xml. Default = emulator-nexus5-android9
     * @param driverType       - Which driver to be used in execution - "browserStack" should be used for execute using cloud provider BrowserStack, "appiumLocal" should be used for execute using appiumDriver in local Appium server and "webLocal" should be used for execute using remote webDriver in local Appium server (mobile web browser testing)
     * @throws MalformedURLException
     */
    @Parameters({"platform", "capabilitiesDesc", "driverType"})
    @BeforeMethod(alwaysRun = true)
    public void setup(Method method, ITestContext context, @Optional("android") String platform, @Optional("emulator-nexus5-android9-system-apps") String capabilitiesDesc, @Optional("appiumLocal") String driverType) throws MalformedURLException {

        log = LogManager.getLogger (method.getName ());
        this.testSuiteName = context.getSuite ().getName ();
        this.testName = context.getCurrentXmlTest ().getName ();
        this.testMethodName = method.getName ();

        AppiumDriverFactory appiumDriverFactory = new AppiumDriverFactory (capabilitiesDesc, log);
        if (platform.toLowerCase ().equals ("android")) {
            AndroidDriver androidDriver;
            RemoteWebDriver remoteWebDriver;
            switch (driverType.toLowerCase ()) {
                case "browserstack":
                    androidDriver = appiumDriverFactory.getAndroidBrowserStackDriver (testName);
                    appiumDrivers.set (androidDriver);
                    break;
                case "appiumlocal":
                    androidDriver = appiumDriverFactory.getAndroidDriver ();
                    appiumDrivers.set (androidDriver);
                    break;
                case "weblocal":
                    remoteWebDriver = appiumDriverFactory.getRemoteWebDriver ();
                    remoteDrivers.set (remoteWebDriver);
                    break;
                default:
                    log.info ("No match found for given " + driverType + " use 'appiumLocal' setup");
                    androidDriver = appiumDriverFactory.getAndroidDriver ();
                    appiumDrivers.set (androidDriver);
                    break;
            }
        } else{
            //Implement for ios
        }
    }

    @AfterMethod
    public void tearDown() {
        log.info ("close driver");
        if (getAppiumDriver () != null) {
            getAppiumDriver ().quit ();
        }
        if (getRemoteDriver () != null) {
            getRemoteDriver ().quit ();
        }
        appiumDrivers.remove ();
        remoteDrivers.remove ();
    }

}

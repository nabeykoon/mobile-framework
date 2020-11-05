package com.mobile.core.base;

import com.mobile.core.util.PropertyUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class AppiumDriverFactory {

    private String executionPlatform;
    private Logger log;

    public AppiumDriverFactory(String executionPlatform, Logger log) {
        this.executionPlatform = executionPlatform.toLowerCase ();
        this.log = log;
    }

    private static String getAbsolutePath(String appRelativePath) {
        File file = new File (appRelativePath);
        return file.getAbsolutePath ();
    }

    //===================Android capabilities==========================================
    private String platformName = PropertyUtils.getProperty ("android.platform");
    private String platformVersion = PropertyUtils.getProperty ("android.platform.version");
    private String deviceName = PropertyUtils.getProperty ("android.device.name");
    private String automationName = PropertyUtils.getProperty ("android.automationName");

    //===================Local App location and names========================================
    private String app = PropertyUtils.getProperty ("android.app.name");
    String appRelativePath = PropertyUtils.getProperty ("android.app.location") + app;
    String appAbsolutePath = getAbsolutePath (appRelativePath);

    //==========================BrowserStack access credentials========================
    private String browserStackUser = PropertyUtils.getProperty ("browserstack.user");
    private String browserStackKey = PropertyUtils.getProperty ("browserstack.key");

    //===================BrowserStack app url========================================
    private String browserStackUrlTheApp = PropertyUtils.getProperty ("browserstack.app.theApp");

    //===================BrowserStack capabilities========================================
    private String browserStackDeviceGooglePixel3 = PropertyUtils.getProperty ("browserstack.device.google.pixel3");
    private String browserStackOsVersion9 = PropertyUtils.getProperty ("browserstack.os.version.9");


    public AppiumDriver getDriver() throws MalformedURLException {
        log.info ("Create Appium driver for local execution");
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities ();

        switch (executionPlatform) {

            case "emulator-nexus5-android9":
                desiredCapabilities.setCapability (MobileCapabilityType.PLATFORM_NAME, platformName);
                desiredCapabilities.setCapability (MobileCapabilityType.PLATFORM_VERSION, platformVersion);
                desiredCapabilities.setCapability (MobileCapabilityType.DEVICE_NAME, deviceName);
                desiredCapabilities.setCapability (MobileCapabilityType.AUTOMATION_NAME, automationName);
                desiredCapabilities.setCapability (MobileCapabilityType.APP, appAbsolutePath);

            default:
                desiredCapabilities.setCapability (MobileCapabilityType.PLATFORM_NAME, platformName);
                desiredCapabilities.setCapability (MobileCapabilityType.PLATFORM_VERSION, platformVersion);
                desiredCapabilities.setCapability (MobileCapabilityType.DEVICE_NAME, deviceName);
                desiredCapabilities.setCapability (MobileCapabilityType.AUTOMATION_NAME, automationName);
                desiredCapabilities.setCapability (MobileCapabilityType.APP, appAbsolutePath);

        }
        return new AppiumDriver (new URL (PropertyUtils.getProperty ("appium.server.url")), desiredCapabilities);
    }

    public AppiumDriver getBrowserStackDriver(String testName) throws MalformedURLException {
        log.info ("Create Appium driver for BrowserStack execution" + executionPlatform);
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities ();
        //TODO: Check following variable usage with Jenkins integration
        String buildName = System.getenv("BROWSERSTACK_BUILD_NAME");
        switch (executionPlatform) {

            case "browserstack-pixel3-android9":
                desiredCapabilities.setCapability ("browserstack.user", browserStackUser);
                desiredCapabilities.setCapability ("browserstack.key", browserStackKey);
                desiredCapabilities.setCapability (MobileCapabilityType.APP, browserStackUrlTheApp);
                desiredCapabilities.setCapability ("device", browserStackDeviceGooglePixel3);
                desiredCapabilities.setCapability ("os_version", browserStackOsVersion9);
                //TODO: Check following variable usage with Jenkins integration
                desiredCapabilities.setCapability("project", "The App");
                desiredCapabilities.setCapability("build", buildName);
                desiredCapabilities.setCapability("name", testName);

            default:
                desiredCapabilities.setCapability ("browserstack.user", browserStackUser);
                desiredCapabilities.setCapability ("browserstack.key", browserStackKey);
                desiredCapabilities.setCapability (MobileCapabilityType.APP, browserStackUrlTheApp);
                desiredCapabilities.setCapability ("device", browserStackDeviceGooglePixel3);
                desiredCapabilities.setCapability ("os_version", browserStackOsVersion9);

        }
        return new AppiumDriver (new URL (PropertyUtils.getProperty ("browserstack.server.url")), desiredCapabilities);
    }
}

package com.mobile.core.base;

import com.mobile.core.util.PropertyUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class AppiumDriverFactory {

    private String capabilitiesDesc;
    private Logger log;

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

    //===============================Constructor==========================================================
    public AppiumDriverFactory(String capabilitiesDesc, Logger log) {
        this.capabilitiesDesc = capabilitiesDesc.toLowerCase ();
        this.log = log;
    }

    private static String getAbsolutePath(String appRelativePath) {
        File file = new File (appRelativePath);
        return file.getAbsolutePath ();
    }

    public AndroidDriver getAndroidDriver() throws MalformedURLException {

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities ();
        switch (capabilitiesDesc) {
            case "emulator-nexus5-android9":
                desiredCapabilities.setCapability (MobileCapabilityType.PLATFORM_NAME, platformName);
                desiredCapabilities.setCapability (MobileCapabilityType.PLATFORM_VERSION, platformVersion);
                desiredCapabilities.setCapability (MobileCapabilityType.DEVICE_NAME, deviceName);
                desiredCapabilities.setCapability (MobileCapabilityType.AUTOMATION_NAME, automationName);
                desiredCapabilities.setCapability (MobileCapabilityType.APP, appAbsolutePath);
                desiredCapabilities.setCapability ("autoGrantPermissions", true);
                break;

            //To run using real device
            case "realDevice-oneplus6":
                desiredCapabilities.setCapability (MobileCapabilityType.PLATFORM_NAME, platformName);
                desiredCapabilities.setCapability (MobileCapabilityType.PLATFORM_VERSION, platformVersion);
                desiredCapabilities.setCapability (MobileCapabilityType.DEVICE_NAME, deviceName);
                desiredCapabilities.setCapability (MobileCapabilityType.AUTOMATION_NAME, automationName);
                desiredCapabilities.setCapability (MobileCapabilityType.APP, appAbsolutePath);
                desiredCapabilities.setCapability (MobileCapabilityType.UDID, "74d36c16");
                break;

            //To automate system apps
            case "emulator-nexus5-android9-system-apps":
                desiredCapabilities.setCapability (MobileCapabilityType.PLATFORM_NAME, platformName);
                desiredCapabilities.setCapability (MobileCapabilityType.PLATFORM_VERSION, platformVersion);
                desiredCapabilities.setCapability (MobileCapabilityType.DEVICE_NAME, deviceName);
                desiredCapabilities.setCapability (MobileCapabilityType.AUTOMATION_NAME, automationName);
                desiredCapabilities.setCapability ("appPackage", "com.google.android.apps.photos");
                desiredCapabilities.setCapability ("appActivity", ".home.HomeActivity");
                //desiredCapabilities.setCapability("appPackage", "com.android.settings");
                //desiredCapabilities.setCapability("appActivity", ".Settings");
                break;

            default:
                System.out.println ("No matched found for given capabilitiesDesc. Using 'emulator-nexus5-android9'");
                desiredCapabilities.setCapability (MobileCapabilityType.PLATFORM_NAME, platformName);
                desiredCapabilities.setCapability (MobileCapabilityType.PLATFORM_VERSION, platformVersion);
                desiredCapabilities.setCapability (MobileCapabilityType.DEVICE_NAME, deviceName);
                desiredCapabilities.setCapability (MobileCapabilityType.AUTOMATION_NAME, automationName);
                desiredCapabilities.setCapability (MobileCapabilityType.APP, appAbsolutePath);
                break;
        }
        log.info ("Create Android driver for local appium server execution");
        return new AndroidDriver (new URL (PropertyUtils.getProperty ("appium.server.url")), desiredCapabilities);
    }

    public AndroidDriver getAndroidBrowserStackDriver(String testName) throws MalformedURLException {

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities ();
        //TODO: Check following variable usage with Jenkins integration
        String buildName = System.getenv ("BROWSERSTACK_BUILD_NAME");
        switch (capabilitiesDesc) {

            case "browserstack-pixel3-android9":
                desiredCapabilities.setCapability ("browserstack.user", browserStackUser);
                desiredCapabilities.setCapability ("browserstack.key", browserStackKey);
                desiredCapabilities.setCapability (MobileCapabilityType.APP, browserStackUrlTheApp);
                desiredCapabilities.setCapability ("device", browserStackDeviceGooglePixel3);
                desiredCapabilities.setCapability ("os_version", browserStackOsVersion9);
                //TODO: Check following variable usage with Jenkins integration
                desiredCapabilities.setCapability ("project", "The App");
                desiredCapabilities.setCapability ("build", buildName);
                desiredCapabilities.setCapability ("name", testName);
                break;

            default:
                System.out.println ("No matched found for given capabilitiesDesc. Using 'browserstack-pixel3-android9'");
                desiredCapabilities.setCapability ("browserstack.user", browserStackUser);
                desiredCapabilities.setCapability ("browserstack.key", browserStackKey);
                desiredCapabilities.setCapability (MobileCapabilityType.APP, browserStackUrlTheApp);
                desiredCapabilities.setCapability ("device", browserStackDeviceGooglePixel3);
                desiredCapabilities.setCapability ("os_version", browserStackOsVersion9);
                break;

        }
        log.info ("Create Android driver for BrowserStack cloud execution");
        return new AndroidDriver (new URL (PropertyUtils.getProperty ("browserstack.server.url")), desiredCapabilities);
    }

    public RemoteWebDriver getRemoteWebDriver() throws MalformedURLException {

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities ();
        switch (capabilitiesDesc) {
            case "emulator-nexus5-android9":
                desiredCapabilities.setCapability (MobileCapabilityType.PLATFORM_NAME, platformName);
                desiredCapabilities.setCapability (MobileCapabilityType.PLATFORM_VERSION, platformVersion);
                desiredCapabilities.setCapability (MobileCapabilityType.DEVICE_NAME, deviceName);
                desiredCapabilities.setCapability (MobileCapabilityType.AUTOMATION_NAME, automationName);
                desiredCapabilities.setCapability (MobileCapabilityType.BROWSER_NAME, "chrome");
                break;

            default:
                System.out.println ("No matched found for given capabilitiesDesc. Using 'emulator-nexus5-android9'");
                desiredCapabilities.setCapability (MobileCapabilityType.PLATFORM_NAME, platformName);
                desiredCapabilities.setCapability (MobileCapabilityType.PLATFORM_VERSION, platformVersion);
                desiredCapabilities.setCapability (MobileCapabilityType.DEVICE_NAME, deviceName);
                desiredCapabilities.setCapability (MobileCapabilityType.AUTOMATION_NAME, automationName);
                desiredCapabilities.setCapability (MobileCapabilityType.BROWSER_NAME, "chrome");
                break;
        }
        log.info ("Create remote web driver for browser based execution in local appium server");
        return new RemoteWebDriver (new URL (PropertyUtils.getProperty ("appium.server.url")), desiredCapabilities);
    }
}

package com.theApp;

import com.mobile.core.base.TestUtilities;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.Test;

import java.io.File;

public class SystemAppTestScenario extends TestUtilities {

    private static final String androidPhotoPath ="/mnt/sdcard/Pictures";

    @Test
    public void settingsAppTest1(){
        //log.info (getAppiumDriver ().getPageSource ());
        File image = new File ("src/test/resources/testFiles/nature.jpg").getAbsoluteFile ();
        //((AndroidDriver)getAppiumDriver ()).pushFile ();
    }
}

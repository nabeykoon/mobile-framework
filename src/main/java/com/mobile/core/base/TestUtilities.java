package com.mobile.core.base;

import io.appium.java_client.AppiumDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Interaction;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;

import static com.mobile.core.util.TimeUtils.getSystemTime;
import static com.mobile.core.util.TimeUtils.getTodaysDate;

public class TestUtilities extends BaseTest {

    //STATIC SLEEP IF REQUIRED FOR DEBUGGING. NOT RECOMMENDED TO USE IN TESTS
    public void sleep(long millis) {
        try {
            Thread.sleep (millis);
        } catch (InterruptedException e) {
            e.printStackTrace ();
        }
    }

    /**
     * Method to single finger swipe on the screen
     *
     * @param x1            - starting x coordinate relative to viewport
     * @param y1            - starting y coordinate relative to viewport
     * @param swipeDuration - swipe duration
     * @param x2            - Ending x coordinate relative to viewport
     * @param y2            - Ending x coordinate relative to viewport
     */


    public void singleFingerSwipe(int x1, int y1, long swipeDuration, int x2, int y2) {
        PointerInput finger = new PointerInput (PointerInput.Kind.TOUCH, "finger");
        Interaction moveToStart = finger.createPointerMove (Duration.ZERO, PointerInput.Origin.viewport (), x1, y1);
        Interaction pressDown = finger.createPointerDown (PointerInput.MouseButton.LEFT.asArg ());
        Interaction moveToEnd = finger.createPointerMove (Duration.ofMillis (swipeDuration), PointerInput.Origin.viewport (), x2, y2);
        Interaction pressUp = finger.createPointerUp (PointerInput.MouseButton.LEFT.asArg ());

        Sequence swipe = new Sequence (finger, 0);

        swipe.addAction (moveToStart);
        swipe.addAction (pressDown);
        swipe.addAction (moveToEnd);
        swipe.addAction (pressUp);

        getAppiumDriver ().perform (Arrays.asList (swipe));

    }

    /**
     * get the web context from App
     *
     * @return
     */
    public String getWebContext() {
        ArrayList<String> contexts = new ArrayList (getAppiumDriver ().getContextHandles ());
        for (String context : contexts) {
            if (!context.equals ("NATIVE_APP")) {
                return context;
            }
        }
        return null;
    }


    /**
     * change context to web context if available
     */
    public void changeToWebContext() throws NoContextFound {
        boolean foundContext = false;
        if (!getWebContext ().equals ("NATIVE_APP") && getWebContext () != null) {
            getAppiumDriver ().context (getWebContext ());
            log.info (getWebContext ());
            foundContext = true;
        }
        if (!foundContext) {
            throw new NoContextFound ("No context found to switch");
        }
    }

    /**
     * change to Native context
     */
    public void changeToNativeContext() {
        getAppiumDriver ().context ("NATIVE_APP");
    }


    public class NoContextFound extends Exception {
        public NoContextFound(String errorMessage) {
            super (errorMessage);
        }
    }

    /**
     * Take screenshot from any point of test
     *
     * @return
     */
    protected void takeScreenshotInTest(String fileName) {
        File scrFile = ((TakesScreenshot) getAppiumDriver ()).getScreenshotAs (OutputType.FILE);
        String path = System.getProperty ("user.dir")
                + File.separator + File.separator + "test-output"
                + File.separator + File.separator + "screenshots"
                + File.separator + File.separator + getTodaysDate ()
                + File.separator + File.separator + testSuiteName
                + File.separator + File.separator + testName
                + File.separator + File.separator + testMethodName
                + File.separator + File.separator +
                getSystemTime () + " " + fileName + ".png";
        try {
            FileUtils.copyFile (scrFile, new File (path));
        } catch (IOException e) {
            e.printStackTrace ();
        }
    }

    protected void changeOrientation(ScreenOrientation screenOrientation){
        ScreenOrientation curOrientation = getAppiumDriver ().getOrientation ();
        if(curOrientation != screenOrientation){
            getAppiumDriver ().rotate (screenOrientation);
            log.info ("changed orientation to: " + screenOrientation);
        }else{
            log.info ("Already device is in given: " + screenOrientation);
        }
    }
}

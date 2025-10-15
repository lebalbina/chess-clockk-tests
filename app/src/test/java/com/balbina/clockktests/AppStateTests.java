package com.balbina.clockktests;

import com.balbina.clockktests.pom.MainViewPOM;
import com.balbina.clockktests.pom.TimeSetBottomSheetPOM;
import io.appium.java_client.android.appmanagement.AndroidTerminateApplicationOptions;
import io.appium.java_client.appmanagement.ApplicationState;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class AppStateTests extends BaseTest {

    private final String packageName = "com.example.chessclockk";
    private MainViewPOM pom;

    //TODO time setting logic should be extracted
    @BeforeClass
    private void setUp() {
        pom = new MainViewPOM(driver);
        TimeSetBottomSheetPOM sheetPOM = new TimeSetBottomSheetPOM(driverWait);

        pom.clickClockBtn();

        //TODO refactor
        sheetPOM.getSeconds().clear();
        sheetPOM.getSeconds().sendKeys("5");
        sheetPOM.getDoneBtn().click();
    }

    @AfterMethod
    private void restart() {
        ApplicationState state = driver.queryAppState(packageName);
        if (state == ApplicationState.RUNNING_IN_FOREGROUND) {
            driver.terminateApp(packageName,
                                new AndroidTerminateApplicationOptions().withTimeout(Duration.ofSeconds(0))
            );
        }
        driver.activateApp(packageName);
    }

    @Test
    public void backPressed_exitsApp() {
        driver.navigate().back();
        Assert.assertNotEquals(driver.getCurrentPackage(), packageName);
    }

    @Test
    public void hideApp_return_gameRunning() {
        pom.clickTopClock();
        pom.processBottomClockWait("0:04");
        hideAndReturnApp(2);
        Assert.assertNotEquals(pom.getBottomClockTime(), "0:04");
    }

    @Test
    public void hideApp_gameFinishes() {
        pom.clickTopClock();
        hideAndReturnApp(5);
        Assert.assertTrue(pom.isFlagVisible());
        Assert.assertEquals(pom.getBottomClockTime(), "0:00");
    }

    @Test
    public void hideApp_return_gamePaused() {
        pom.clickTopClock();
        pom.clickPpBtn();
        hideAndReturnApp(3);
        Assert.assertFalse(pom.isTopClockEnabled());
        Assert.assertFalse(pom.isBottomClockEnabled());
        Assert.assertEquals(pom.getTopClockTime(), "0:05");
    }

    private void hideAndReturnApp(int duration) {
        driver.runAppInBackground(Duration.ofSeconds(duration));
        driver.activateApp(packageName);
    }
}

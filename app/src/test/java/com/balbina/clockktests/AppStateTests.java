package com.balbina.clockktests;

import com.balbina.clockktests.pom.MainViewPOM;
import com.balbina.clockktests.pom.TimeSetBottomSheetPOM;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class AppStateTests extends BaseTest {

    private MainViewPOM pom;

    @BeforeMethod
    private void setUp() {
        pom = new MainViewPOM(driver);
        TimeSetBottomSheetPOM sheetPOM = new TimeSetBottomSheetPOM(driver);
        pom.clickClockBtn();
        sheetPOM.typeSeconds("5");
        sheetPOM.clickDoneBtn();
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

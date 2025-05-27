package com.balbina.clockktests;

import com.balbina.clockktests.pom.MainViewPOM;
import com.balbina.clockktests.pom.TimeSetBottomSheetPOM;
import io.appium.java_client.android.appmanagement.AndroidTerminateApplicationOptions;
import io.appium.java_client.appmanagement.ApplicationState;
import org.openqa.selenium.support.ui.ExpectedConditions;
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

        pom.getClockBtn().click();
        sheetPOM.getSeconds().clear();
        sheetPOM.getSeconds().sendKeys("5");
        sheetPOM.getDoneBtn().click();
    }

    @AfterMethod
    private void restart() {
        ApplicationState state = driver.queryAppState(packageName);
        if (state == ApplicationState.RUNNING_IN_FOREGROUND) {
            driver.terminateApp("com.example.chessclockk",
                                new AndroidTerminateApplicationOptions().withTimeout(Duration.ofSeconds(0))
            );
        }
        driver.activateApp("com.example.chessclockk");
    }

    @Test
    public void backPressed_exitsApp() {
        driver.navigate().back();
        Assert.assertNotEquals(driver.getCurrentPackage(), packageName);
    }

    @Test
    public void hideApp_return_gameRunning() {
        driverWait.until(ExpectedConditions.visibilityOf(pom.getClockTop()));
        pom.getClockTop().click();
        driverWait.until(ExpectedConditions.textToBePresentInElement(pom.getBottomClockTimeElement(), "0:04"));
        hideAndReturnApp(2);
        Assert.assertNotEquals(pom.getBottomClockTime(), "0:04");
    }

    @Test
    public void hideApp_gameFinishes() {
        driverWait.until(ExpectedConditions.visibilityOf(pom.getClockTop()));
        pom.getClockTop().click();
        hideAndReturnApp(5);

        driverWait.until(ExpectedConditions.visibilityOf(pom.getFlag()));
        Assert.assertEquals(pom.getBottomClockTime(), "0:00");
    }

    @Test
    public void hideApp_return_gamePaused() {
        driverWait.until(ExpectedConditions.visibilityOf(pom.getClockTop()));
        pom.getClockTop().click();
        pom.getPpBtn().click();
        hideAndReturnApp(3);
        Assert.assertFalse(pom.getClockBottom().isEnabled());
        Assert.assertFalse(pom.getClockTop().isEnabled());
        Assert.assertEquals(pom.getTopClockTime(), "0:05");
    }

    private void hideAndReturnApp(int duration) {
        driver.runAppInBackground(Duration.ofSeconds(duration));
        driver.activateApp(packageName);
        driverWait.until(ExpectedConditions.visibilityOf(pom.getClockTop()));
    }
}

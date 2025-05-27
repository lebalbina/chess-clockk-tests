package com.balbina.clockktests;

import com.balbina.clockktests.pom.MainViewPOM;
import com.balbina.clockktests.pom.TimeSetBottomSheetPOM;
import io.appium.java_client.android.appmanagement.AndroidTerminateApplicationOptions;
import io.appium.java_client.appmanagement.BaseTerminateApplicationOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class InitialStateTests extends BaseTest {

    private MainViewPOM pom;

    @BeforeClass
    private void setUp() {
        pom = new MainViewPOM(driver);
    }

    @AfterMethod
    private void restart() {
        driver.terminateApp("com.example.chessclockk",
                            new AndroidTerminateApplicationOptions().withTimeout(Duration.ofSeconds(0))
        );
        driver.activateApp("com.example.chessclockk");
    }

    @BeforeMethod
    public void waitForMainScreen() {
        driverWait.until(ExpectedConditions.visibilityOf(pom.getClockTop()));
    }

    @Test
    public void timeCorrectlySet() {
        String topClockInitialValue = pom.getTopClockTime();
        String bottomClockInitialValue = pom.getBottomClockTime();

        Assert.assertEquals(topClockInitialValue, "3:00");
        Assert.assertEquals(bottomClockInitialValue, "3:00");
    }

    @Test
    public void buttonsStateCorrectlySet() {
        boolean isPpBtnEnabled = pom.getPpBtn().isEnabled();
        boolean isClockBtnEnabled = pom.getClockBtn().isEnabled();
        boolean isRestartBtnEnabled = pom.getRestartBtn().isEnabled();

        Assert.assertFalse(isPpBtnEnabled);
        Assert.assertTrue(isClockBtnEnabled);
        Assert.assertFalse(isRestartBtnEnabled);
    }

    @Test
    public void countersAreZero() {
        int topCounterValue = pom.getTopMovesCounterValue();
        int bottomCounterValue = pom.getBottomMovesCounterValue();

        Assert.assertEquals(topCounterValue, 0);
        Assert.assertEquals(bottomCounterValue, 0);
    }

    @Test
    public void topPlayerCanStartGame() {
        pom.tapClockTop();

        Boolean hasClockStarted = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(webDriver -> {
                    String time = pom.getBottomClockTime();
                    return !time.equals("3:00");
                });

        Assert.assertTrue(hasClockStarted, "Bottom clock did not update to 2:57 in time.");
    }

    @Test
    public void bottomPlayerCanStartGame() {
        pom.tapBottomClock();

        Boolean hasClockStarted = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(webDriver -> {
                    String time = pom.getTopClockTime();
                    return !time.equals("3:00");
                });

        Assert.assertTrue(hasClockStarted, "Bottom clock did not update to 2:57 in time.");
    }
}

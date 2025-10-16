package com.balbina.clockktests;

import com.balbina.clockktests.pom.MainViewPOM;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class InitialStateTests extends BaseTest {

    private MainViewPOM pom;

    @BeforeMethod
    private void setUp() {
        pom = new MainViewPOM(driver);
    }

    @Test
    public void timeCorrectlySet() {
        Assert.assertEquals(pom.getTopClockTime(), "3:00");
        Assert.assertEquals(pom.getBottomClockTime(), "3:00");
    }

    @Test
    public void buttonsStateCorrectlySet() {
        Assert.assertFalse(pom.isPpBtnEnabled());
        Assert.assertTrue(pom.isClockBtnEnabled());
        Assert.assertFalse(pom.isRestartBtnEnabled());
    }

    @Test
    public void countersAreZero() {
        Assert.assertEquals(pom.getTopMovesCounterValue(), 0);
        Assert.assertEquals(pom.getBottomMovesCounterValue(), 0);
    }

    @Test
    public void topPlayerCanStartGame() {
        pom.clickTopClock();
        pom.processBottomClockWait("2:57");
        Assert.assertEquals(pom.getBottomClockTime(), "2:57");
    }

    @Test
    public void bottomPlayerCanStartGame() {
        pom.clickBottomClock();
        pom.processTopClockWait("2:57");
        Assert.assertEquals(pom.getTopClockTime(), "2:57");
    }
}

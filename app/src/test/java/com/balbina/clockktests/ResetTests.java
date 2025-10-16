package com.balbina.clockktests;

import com.balbina.clockktests.pom.MainViewPOM;
import com.balbina.clockktests.pom.RestartDialogPOM;
import com.balbina.clockktests.pom.TimeSetBottomSheetPOM;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ResetTests extends BaseTest {

    private RestartDialogPOM dialogPom;
    private MainViewPOM pom;
    private TimeSetBottomSheetPOM timeSheetPom;

    @BeforeMethod
    public void setUp() {
        pom = new MainViewPOM(driver);
        dialogPom  = new RestartDialogPOM(driver);
        timeSheetPom = new TimeSetBottomSheetPOM(driver);
    }

    //region gameOn

    @Test
    public void gameOn_resetGame() {
        setGameTimeTo5Seconds();
        startAndRunGame();
        pom.clickRestartBtn();
        dialogPom.clickConfirmBtn();
        assertGameIsRestarted();
    }

    @Test
    public void gameOn_restartDialogDismiss_doesNotResetGame() {
        setGameTimeTo5Seconds();
        pom.clickTopClock();
        pom.processBottomClockWait("0:04");
        pom.clickRestartBtn();
        dialogPom.clickDismissBtn();

        SoftAssert soft = new SoftAssert();
        soft.assertFalse(pom.isTopClockEnabled());
        soft.assertFalse(pom.isBottomClockEnabled());
        soft.assertTrue(pom.isPpBtnEnabled());
        soft.assertTrue(pom.isClockBtnEnabled());
        soft.assertTrue(pom.isRestartBtnEnabled());
        soft.assertEquals(pom.getBottomClockTime(), "0:04");
        soft.assertEquals(pom.getTopClockTime(), "0:05");
        soft.assertAll();
    }
    @Test()
    public void gameOn_restartDialog_backButtonPressed_doesNotResetGame() {
        setGameTimeTo5Seconds();
        pom.clickTopClock();
        pom.processBottomClockWait("0:04");
        pom.clickRestartBtn();
        driver.navigate().back();

        SoftAssert soft = new SoftAssert();
        soft.assertFalse(pom.isTopClockEnabled());
        soft.assertFalse(pom.isBottomClockEnabled());
        soft.assertTrue(pom.isPpBtnEnabled());
        soft.assertTrue(pom.isClockBtnEnabled());
        soft.assertTrue(pom.isRestartBtnEnabled());
        soft.assertEquals(pom.getBottomClockTime(), "0:04");
        soft.assertEquals(pom.getTopClockTime(), "0:05");
        soft.assertAll();
    }

    //endregion
    //region gamePaused

    @Test
    public void gamePaused_resetGame() {
        setGameTimeTo5Seconds();
        startAndRunGame();

        pom.clickPpBtn();
        pom.clickRestartBtn();
        dialogPom.clickConfirmBtn();

        assertGameIsRestarted();
    }
    //endregion
    //region gameEnd

    @Test
    public void gameEnd_resetGame() {
        setGameTimeTo5Seconds();
        startAndRunGame();

        Assert.assertTrue(pom.isFlagVisible());
        pom.clickRestartBtn();
        dialogPom.clickConfirmBtn();

        assertGameIsRestarted();
    }
    //endregion
    //region --- helper methods ---

    private void setGameTimeTo5Seconds() {
        pom.clickClockBtn();
        timeSheetPom.typeSeconds("5");
        timeSheetPom.clickDoneBtn();
    }

    private void startAndRunGame() {
        int counter = 5;
        while (counter > 0) {
            counter--;
            pom.clickTopClock();
            pom.clickBottomClock();
        }
    }

    private void assertGameIsRestarted() {
        SoftAssert soft = new SoftAssert();
        soft.assertEquals(pom.getTopMovesCounterValue(), 0);
        soft.assertEquals(pom.getBottomMovesCounterValue(), 0);
        soft.assertEquals(pom.getTopClockTime(), "0:05");
        soft.assertEquals(pom.getBottomClockTime(), "0:05");
        soft.assertEquals(pom.getTopTimeSetting(), "0' 5\"");
        soft.assertEquals(pom.getBottomTimeSetting(), "0' 5\"");
        soft.assertTrue(pom.isBottomClockEnabled());
        soft.assertTrue(pom.isTopClockEnabled());
        soft.assertTrue(pom.isClockBtnEnabled());
        soft.assertFalse(pom.isPpBtnEnabled());
        soft.assertFalse(pom.isRestartBtnEnabled());
        soft.assertAll();
    }
    //endregion
}

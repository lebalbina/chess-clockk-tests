package com.balbina.clockktests;

import com.balbina.clockktests.pom.MainViewPOM;
import com.balbina.clockktests.pom.RestartDialogPOM;
import com.balbina.clockktests.pom.TimeSetBottomSheetPOM;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ResetTests extends BaseTest {

    private RestartDialogPOM dialogPom;
    private MainViewPOM pom;

    @BeforeClass
    public void setUpTime() {
        pom = new MainViewPOM(driver);
        dialogPom  = new RestartDialogPOM(driverWait);
        TimeSetBottomSheetPOM sheetPOM = new TimeSetBottomSheetPOM(driverWait);

        driverWait.until(ExpectedConditions.visibilityOf(pom.getClockBtn()));

        pom.getClockBtn().click();
        sheetPOM.getSeconds().clear();
        sheetPOM.getSeconds().sendKeys("5");
        sheetPOM.getDoneBtn().click();
    }

    //region gameOn
    @Test
    public void gameOn_resetGame() {
        startAndRunGame();

        pom.getRestartBtn().click();
        dialogPom.getDialogRestartConfirmBtn().click();

        assertGameIsRestarted();
    }


    //TODO tthis test require restarted game
    @Test
    public void gameOn_restartDialogDismiss_doesNotResetGame() {
        pom.getClockTop().click();
        driverWait.until(ExpectedConditions.textToBePresentInElement(pom.getBottomClockTimeElement(), "0:04"));
        pom.getRestartBtn().click();
        dialogPom.getDialogRestartDismissBtn().click();

        SoftAssert soft = new SoftAssert();
        soft.assertFalse(pom.isTopClockEnabled());
        soft.assertFalse(pom.isBottomClockEnabled());
        soft.assertTrue(pom.getPpBtn().isEnabled());
        soft.assertTrue(pom.getClockBtn().isEnabled());
        soft.assertTrue(pom.getRestartBtn().isEnabled());
        //TODO diff than 0:05 rather that precise time or driverwait perhaps
        soft.assertEquals(pom.getBottomClockTime(), "0:04");
        soft.assertEquals(pom.getTopClockTime(), "0:05");
        soft.assertAll();
    }

    @Test()
    public void gameOn_restartDialog_backButtonPressed_doesNotResetGame() {
        pom.tapClockTop();
        //TODO diff than 0:05 rather that precise time or driverwait perhaps
        driverWait.until(ExpectedConditions.textToBePresentInElement(pom.getBottomClockTimeElement(), "0:04"));
        pom.getRestartBtn().click();
        driver.navigate().back();

        SoftAssert soft = new SoftAssert();
        soft.assertFalse(pom.isTopClockEnabled());
        soft.assertFalse(pom.isBottomClockEnabled());
        soft.assertTrue(pom.getPpBtn().isEnabled());
        soft.assertTrue(pom.getClockBtn().isEnabled());
        soft.assertTrue(pom.getRestartBtn().isEnabled());
        soft.assertEquals(pom.getBottomClockTime(), "0:04");
        soft.assertEquals(pom.getTopClockTime(), "0:05");
        soft.assertAll();
    }
    //endregion

    //region gamePaused
    @Test
    public void gamePaused_resetGame() {
        startAndRunGame();

        pom.getPpBtn().click();
        pom.getRestartBtn().click();
        dialogPom.getDialogRestartConfirmBtn().click();

        assertGameIsRestarted();
    }
    //endregion

    //region gameEnd
    @Test
    public void gameEnd_resetGame() {
        startAndRunGame();

        driverWait.until(ExpectedConditions.visibilityOf(pom.getFlag()));

        pom.getRestartBtn().click();
        dialogPom.getDialogRestartConfirmBtn().click();

        assertGameIsRestarted();
    }
    //endregion

    //region --- helper methods ---
    private void startAndRunGame() {
        int counter = 5;
        while (counter > 0) {
            counter--;
            pom.tapClockTop();
            pom.tapBottomClock();
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

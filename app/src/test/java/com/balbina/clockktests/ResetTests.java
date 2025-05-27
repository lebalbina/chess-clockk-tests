package com.balbina.clockktests;

import com.balbina.clockktests.BaseTest;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ResetTests extends BaseTest {

    private final RestartDialogPOM dialogPom = new RestartDialogPOM(driverWait);
    private final TimeSetBottomSheetPOM sheetPOM = new TimeSetBottomSheetPOM(driverWait);

    @BeforeClass
    public void setUpTime() {
        pom.getClockBtn().click();
        sheetPOM.getSheetSeconds().clear();
        sheetPOM.getSheetSeconds().sendKeys("5");
        sheetPOM.getDoneBtnSeconds().click();
    }

    @Test
    public void gameOn_resetGame() {
        startAndRunGame(5);

        pom.getRestartBtn().click();
        dialogPom.getDialogRestartConfirmBtn().click();

        assertGameIsRestarted();
    }

    @Test
    public void gamePaused_resetGame() {
        startAndRunGame(5);

        pom.getPpBtn().click();
        pom.getRestartBtn().click();
        dialogPom.getDialogRestartConfirmBtn().click();

        assertGameIsRestarted();
    }

    @Test
    public void gameEnd_resetGame() {
        startAndRunGame(3);

        driverWait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.androidUIAutomator("new UiSelector().description(\"flag\")")));
        pom.getRestartBtn().click();
        dialogPom.getDialogRestartConfirmBtn().click();

        assertGameIsRestarted();
    }

    @Test
    public void restartDialogDismiss_doesNotResetGame() {
        pom.tapClockTop();
        pom.getRestartBtn().click();
        dialogPom.getDialogRestartDismissBtn().click();

        SoftAssert soft = new SoftAssert();
        soft.assertFalse(pom.isTopClockEnabled());
        soft.assertFalse(pom.isBottomClockEnabled());
        soft.assertTrue(pom.getPpBtn().isEnabled());
        soft.assertTrue(pom.getClockBtn().isEnabled());
        soft.assertTrue(pom.getRestartBtn().isEnabled());
        soft.assertAll();
    }

    //region --- helper methods ---
    private void startAndRunGame(int counter) {
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

        soft.assertEquals(pom.getTopTimeSetting().getText(), "0' 5\"");
        soft.assertEquals(pom.getBottomTimeSetting().getText(), "0' 5\"");

        soft.assertTrue(pom.isBottomClockEnabled());
        soft.assertTrue(pom.isTopClockEnabled());

        soft.assertTrue(pom.isClockBtnEnabled());
        soft.assertFalse(pom.isPpBtnEnabled());
        soft.assertFalse(pom.isRestartBtnEnabled());

        soft.assertAll();
    }

    //endregion
}



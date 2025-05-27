package com.balbina.clockktests;

import com.balbina.clockktests.pom.MainViewPOM;
import com.balbina.clockktests.pom.RestartDialogPOM;
import com.balbina.clockktests.pom.TimeSetBottomSheetPOM;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class InteractionTests extends BaseTest {

    private RestartDialogPOM dialogPom;
    private TimeSetBottomSheetPOM sheetPOM;
    private MainViewPOM pom;
    private TimeSetBottomSheetPOM timePom;

    @BeforeClass
    public void setUp() {
        dialogPom = new RestartDialogPOM(driverWait);
        sheetPOM = new TimeSetBottomSheetPOM(driverWait);
        pom = new MainViewPOM(driver);
        timePom = new TimeSetBottomSheetPOM(driverWait);
    }

    @BeforeMethod
    public void waitForMainScreen() {
        driverWait.until(ExpectedConditions.visibilityOf(pom.getClockTop()));
    }

    @AfterMethod
    public void restartApp() {
        if (driver.isKeyboardShown()) {
            driver.hideKeyboard();
        }

        if (pom.isRestartBtnEnabled()) {
            pom.getRestartBtn().click();
            dialogPom.getDialogRestartConfirmBtn().click();
            driverWait.until(ExpectedConditions.visibilityOf(pom.getClockTop()));
        }
    }

    //region --- play pause interactions ---
    @Test
    public void gameOn_ppBtnEnabled() {
        pom.tapBottomClock();
        Assert.assertTrue(pom.getPpBtn().isEnabled());
    }

    @Test
    public void gameOn_ppBtnClicked_gamePaused() {
        pom.tapBottomClock();
        pom.getPpBtn().click();

        Assert.assertFalse(pom.isTopClockEnabled());
        Assert.assertFalse(pom.isBottomClockEnabled());
    }

    @Test
    public void gamePaused_ppBtnClicked_gameResumed() {
        pom.tapClockTop();
        pom.getPpBtn().click();
        pom.getPpBtn().click();

        Assert.assertTrue(pom.isBottomClockEnabled());
        Assert.assertFalse(pom.isTopClockEnabled());
    }

    @Test
    public void gamePaused_ppBtnEnabled() {
        pom.tapClockTop();
        pom.getPpBtn().click();

        Assert.assertTrue(pom.getPpBtn().isEnabled());
    }

    @Test
    public void gameEnd_ppBtnDisabled() {
        reachGameEnd();
        Assert.assertFalse(pom.getPpBtn().isEnabled());
    }

    //endregion

    //region --- time set interaction - no input validation ---
    @Test
    public void gameEnd_timeSetBtnEnabled() {
        reachGameEnd();
        Assert.assertTrue(pom.getClockBtn().isEnabled());
    }

    @Test
    public void gamePaused_timeSetBtnEnabled() {
        pom.tapClockTop();
        pom.getPpBtn().click();
        Assert.assertTrue(pom.getClockBtn().isEnabled());
    }

    @Test
    public void gameOn_timeSetEnabled() {
        pom.tapClockTop();
        Assert.assertTrue(pom.getClockBtn().isEnabled());
    }

    @Test
    public void openTimeSetDialog_keyboardShowsUp() {
        pom.getClockBtn().click();
        timePom.getHours().click();
        boolean isKeyboardShown = driver.isKeyboardShown();
        Assert.assertTrue(isKeyboardShown);
    }

    @Test
    public void closeTimeSetDialog_keyboardHides() {
        pom.getClockBtn().click();
        timePom.getHours().click();
        timePom.getDoneBtn().click();
        boolean isKeyboardShown = driver.isKeyboardShown();
        Assert.assertFalse(isKeyboardShown);
    }

    @Test
    public void pressBackTimeSetDialog_keyboardHides() {
        pom.getClockBtn().click();
        timePom.getHours().click();
        driver.navigate().back();
        boolean isKeyboardShown = driver.isKeyboardShown();
        Assert.assertFalse(isKeyboardShown);
    }

    //endregion -- end

    //region --- restart interaction - no state validation ---
    @Test
    public void gameOn_restartBtnEnabled() {
        pom.tapClockTop();
        Assert.assertTrue(pom.getRestartBtn().isEnabled());
    }

    @Test
    public void gamePaused_restartBtnEnabled() {
        pom.tapClockTop();
        pom.getPpBtn().click();
        Assert.assertTrue(pom.getRestartBtn().isEnabled());
    }

    @Test
    public void gameOn_restartBtnClicked_dialogAppears() {
        pom.tapClockTop();
        pom.getRestartBtn().click();

        Assert.assertTrue(dialogPom.getDialogRestart().isDisplayed());

        dialogPom.getDialogRestartDismissBtn().click();
    }

    @Test
    public void gamePaused_restartBtnClicked_dialogAppears() {
        pom.tapClockTop();
        pom.getPpBtn().click();
        pom.getRestartBtn().click();

        Assert.assertTrue(dialogPom.getDialogRestart().isDisplayed());

        dialogPom.getDialogRestartDismissBtn().click();
    }

    @Test
    public void gameEnd_restartBtnEnabled() {
        reachGameEnd();
        Assert.assertTrue(pom.getRestartBtn().isEnabled());
    }

    //endregion

    //region --- helper methods ---

    private void reachGameEnd() {
        pom.getClockBtn().click();
        sheetPOM.getSeconds().clear();
        sheetPOM.getSeconds().sendKeys("3");
        sheetPOM.getDoneBtn().click();
        pom.tapClockTop();

        driverWait.until(ExpectedConditions.visibilityOf(pom.getFlag()));
    }

    //endregion
}




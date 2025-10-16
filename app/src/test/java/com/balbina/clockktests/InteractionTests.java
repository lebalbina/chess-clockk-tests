package com.balbina.clockktests;

import com.balbina.clockktests.pom.MainViewPOM;
import com.balbina.clockktests.pom.RestartDialogPOM;
import com.balbina.clockktests.pom.TimeSetBottomSheetPOM;
import org.testng.Assert;
import org.testng.annotations.*;

public class InteractionTests extends BaseTest {

    private RestartDialogPOM dialogPom;
    private TimeSetBottomSheetPOM sheetPOM;
    private MainViewPOM pom;
    private TimeSetBottomSheetPOM timePom;

    @BeforeMethod
    public void setUp() {
        dialogPom = new RestartDialogPOM(driver);
        sheetPOM = new TimeSetBottomSheetPOM(driver);
        pom = new MainViewPOM(driver);
        timePom = new TimeSetBottomSheetPOM(driver);
    }

    //region --- play pause interactions ---
    @Test
    public void gameOn_ppBtnEnabled() {
        pom.clickBottomClock();
        Assert.assertTrue(pom.isPpBtnEnabled());
    }

    @Test
    public void gameOn_ppBtnClicked_gamePaused() {
        pom.clickBottomClock();
        pom.clickPpBtn();

        Assert.assertFalse(pom.isTopClockEnabled());
        Assert.assertFalse(pom.isBottomClockEnabled());
    }

    @Test
    public void gamePaused_ppBtnClicked_gameResumed() {
        pom.clickTopClock();
        pom.clickPpBtn();
        pom.clickPpBtn();

        Assert.assertTrue(pom.isBottomClockEnabled());
        Assert.assertFalse(pom.isTopClockEnabled());
    }

    @Test
    public void gamePaused_ppBtnEnabled() {
        pom.clickTopClock();
        pom.clickPpBtn();

        Assert.assertTrue(pom.isPpBtnEnabled());
    }

    @Test
    public void gameEnd_ppBtnDisabled() {
        reachGameEnd();
        Assert.assertTrue(pom.isFlagVisible());
        Assert.assertFalse(pom.isPpBtnEnabled());
    }

    //endregion

    //region --- time set interaction - no input validation ---
    @Test
    public void gameEnd_timeSetBtnEnabled() {
        reachGameEnd();
        Assert.assertTrue(pom.isFlagVisible());
        Assert.assertTrue(pom.isClockBtnEnabled());
    }

    @Test
    public void gamePaused_timeSetBtnEnabled() {
        pom.clickTopClock();
        pom.clickPpBtn();
        Assert.assertTrue(pom.isClockBtnEnabled());
    }

    @Test
    public void gameOn_timeSetEnabled() {
        pom.clickTopClock();
        Assert.assertTrue(pom.isClockBtnEnabled());
    }

    @Test
    public void closeTimeSetDialog_keyboardHides() {
        pom.clickClockBtn();
        timePom.clickHours();
        timePom.clickDoneBtn();
        Assert.assertFalse(driver.isKeyboardShown());
    }

    @Test
    public void pressBackTimeSetDialog_keyboardHides() {
        pom.clickClockBtn();
        timePom.clickHours();
        driver.navigate().back();
        Assert.assertFalse(driver.isKeyboardShown());
    }

    //endregion -- end

    //region --- restart interaction - no state validation ---
    @Test
    public void gameOn_restartBtnEnabled() {
        pom.clickTopClock();
        Assert.assertTrue(pom.isRestartBtnEnabled());
    }

    @Test
    public void gamePaused_restartBtnEnabled() {
        pom.clickTopClock();
        pom.clickPpBtn();
        Assert.assertTrue(pom.isRestartBtnEnabled());
    }

    @Test
    public void gameOn_restartBtnClicked_dialogAppears() {
        pom.clickTopClock();
        pom.clickRestartBtn();

        Assert.assertTrue(dialogPom.isDialogDisplayed());

        dialogPom.clickDismissBtn();
    }

    @Test
    public void gamePaused_restartBtnClicked_dialogAppears() {
        pom.clickTopClock();
        pom.clickPpBtn();
        pom.clickRestartBtn();

        Assert.assertTrue(dialogPom.isDialogDisplayed());

        dialogPom.clickDismissBtn();
    }

    @Test
    public void gameEnd_restartBtnEnabled() {
        reachGameEnd();
        Assert.assertTrue(pom.isFlagVisible());
        Assert.assertTrue(pom.isRestartBtnEnabled());
    }

    //endregion

    //region --- helper methods ---

    private void reachGameEnd() {
        pom.clickClockBtn();
        sheetPOM.typeSeconds("3");
        sheetPOM.clickDoneBtn();
        pom.clickTopClock();
    }
    //endregion
}

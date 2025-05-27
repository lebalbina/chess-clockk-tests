import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class InteractionTests extends BaseTest {

    private final RestartDialogPOM dialogPom = new RestartDialogPOM(driverWait);
    private final TimeSetBottomSheetPOM sheetPOM = new TimeSetBottomSheetPOM(driverWait);

    @BeforeMethod
    public void restartApp() {
        if (pom.isRestartBtnEnabled()) {
            pom.getRestartBtn().click();
            dialogPom.getDialogRestartConfirmBtn().click();
        }
        driverWait.until(ExpectedConditions.visibilityOf(pom.getClockTop()));
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
        sheetPOM.getSheetSeconds().clear();
        sheetPOM.getSheetSeconds().sendKeys("3");
        sheetPOM.getDoneBtnSeconds().click();
        pom.tapClockTop();

        driverWait.until(ExpectedConditions.visibilityOf(pom.getFlag()));
    }

    //endregion
}




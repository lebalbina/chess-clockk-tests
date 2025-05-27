import org.testng.Assert;
import org.testng.annotations.Test;

public class InitialStateTests extends BaseTest {

    @Test
    public void initial_timeCorrectlySet() {
        System.out.println(InitialStateTests.class.getPackage().getName());

        String topClockInitialValue = pom.getTopClockTime();
        String bottomClockInitialValue = pom.getBottomClockTime();

        Assert.assertEquals(topClockInitialValue, "3:00");
        Assert.assertEquals(bottomClockInitialValue, "3:00");
    }

    @Test
    public void initial_buttonsStateCorrectlySet() {
        boolean isPpBtnEnabled = pom.getPpBtn().isEnabled();
        boolean isClockBtnEnabled = pom.getClockBtn().isEnabled();
        boolean isRestartBtnEnabled = pom.getRestartBtn().isEnabled();

        Assert.assertFalse(isPpBtnEnabled);
        Assert.assertTrue(isClockBtnEnabled);
        Assert.assertFalse(isRestartBtnEnabled);
    }

    @Test
    public void initial_countersAreZero() {
        int topCounterValue = pom.getTopMovesCounterValue();
        int bottomCounterValue = pom.getBottomMovesCounterValue();

        Assert.assertEquals(topCounterValue, 0);
        Assert.assertEquals(bottomCounterValue, 0);
    }

    @Test
    public void initial_topPlayerCanStartGame() {
        pom.tapClockTop();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String bottomClockValueAfter = pom.getBottomClockTime();
        Assert.assertEquals(bottomClockValueAfter, "2:57");
    }

    @Test
    public void initial_bottomPlayerCanStartGame() {
        pom.tapBottomClock();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String topClockValueAfter = pom.getBottomClockTime();
        Assert.assertEquals(topClockValueAfter, "2:57");
    }
}

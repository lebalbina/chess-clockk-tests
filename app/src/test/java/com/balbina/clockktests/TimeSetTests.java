package com.balbina.clockktests;

import com.balbina.clockktests.pom.MainViewPOM;
import com.balbina.clockktests.pom.SystemPOM;
import com.balbina.clockktests.pom.TimeSetBottomSheetPOM;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TimeSetTests extends BaseTest {

    private MainViewPOM pom;
    private TimeSetBottomSheetPOM timePom;
    private SystemPOM systemPOM;

    @BeforeMethod
    public void setUp() {
        pom = new MainViewPOM(driver);
        timePom = new TimeSetBottomSheetPOM(driver);
        systemPOM = new SystemPOM(driver);
    }

    @DataProvider(name = "validTimes")
    public Object[][] validTimes() {
        return new Object[][]{
                {"0", "1", "0", "0", "0", "1:00", "1'"},
                {"0", "2", "0", "0", "1", "2:00", "2' + 1\""},
                {"0", "3", "0", "0", "0", "3:00", "3'"},
                {"0", "3", "0", "0", "2", "3:00", "3' + 2\""},
                {"0", "5", "0", "0", "0", "5:00", "5'"},
                {"0", "5", "0", "0", "3", "5:00", "5' + 3\""},
                {"0", "10", "0", "0", "0", "10:00", "10'"},
                {"0", "10", "0", "0", "5", "10:00", "10' + 5\""},
                {"0", "15", "0", "0", "10", "15:00", "15' + 10\""},
                {"0", "30", "0", "0", "0", "30:00", "30'"},
                {"0", "30", "0", "0", "20", "30:00", "30' + 20\""}
        };
    }

    @DataProvider(name = "edgeCases")
    public Object[][] edgeCases() {
        return new Object[][]{
                {"99", "59", "59", "59", "59", "99:59:59", "5999\" 59\" + 59' 59\""},
                {"60", "60", "60", "60", "60", "59:59:59", "3599\" 59\""},
                {"1", "1", "1", "1", "1", "1:01:01", "61' 1\" + 1' 1\""},
        };
    }

    @Test(dataProvider = "validTimes")
    public void setTimeTestSuccess(String hours,
                                   String minutes,
                                   String seconds,
                                   String bonusMinutes,
                                   String bonusSeconds,
                                   String expectedClockTime,
                                   String expectedTimeFormat
    ) {
        runTimeSettingSuccessTest(hours, minutes, seconds, bonusMinutes, bonusSeconds, expectedClockTime,
                                  expectedTimeFormat
        );
    }

    @Test(dataProvider = "edgeCases")
    public void setTimeTestEdgeCases(String hours,
                                     String minutes,
                                     String seconds,
                                     String bonusMinutes,
                                     String bonusSeconds,
                                     String expectedClockTime,
                                     String expectedTimeFormat
    ) {
        runTimeSettingSuccessTest(hours, minutes, seconds, bonusMinutes, bonusSeconds, expectedClockTime,
                                  expectedTimeFormat
        );
    }

    @Test
    public void setTimeTo0h0min0sec0bm0bs() {
        pom.clickClockBtn();
        timePom.clickDoneBtn();
        Assert.assertEquals(systemPOM.getToastMessage(), "Please specify valid time");
    }

    @Test
    public void noTimeEntered() {
        pom.clickClockBtn();
        timePom.clearAll();
        timePom.clickDoneBtn();
        Assert.assertEquals(systemPOM.getToastMessage(), "Please specify valid time");
    }

    private void runTimeSettingSuccessTest(String hours,
                                           String minutes,
                                           String seconds,
                                           String bonusMinutes,
                                           String bonusSeconds,
                                           String expectedClockTime,
                                           String expectedTimeFormat) {
        enterTimeData(hours, minutes, seconds, bonusMinutes, bonusSeconds);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(pom.getBottomClockTime(), expectedClockTime);
        softAssert.assertEquals(pom.getTopClockTime(), expectedClockTime);
        softAssert.assertEquals(pom.getTopTimeSetting(), expectedTimeFormat);
        softAssert.assertEquals(pom.getBottomTimeSetting(), expectedTimeFormat);
        softAssert.assertAll();
    }

    private void enterTimeData(String hours, String minutes, String seconds, String bonusMinutes, String bonusSeconds) {
        pom.clickClockBtn();
        timePom.typeHours(hours);
        timePom.typeMinutes(minutes);
        timePom.typeSeconds(seconds);
        timePom.typeMinutesBonus(bonusMinutes);
        timePom.typeSecondsBonus(bonusSeconds);
        timePom.clickDoneBtn();
    }
}


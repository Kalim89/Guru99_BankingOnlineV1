package Guru99_BankingOnline_TestCases;

import Guru99_BankingOnline_PageObject.BaseTest;
import Guru99_BankingOnline_PageObject.LoginPage;
import Guru99_BankingOnline_Utilities.Log;
import Guru99_BankingOnline_Utilities.XLUtilityManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {
    private String baseurl = BASE_URL + "/V4";
    private LoginPage loginPageObj;

    private String actualTitle;
    private String actualalertMsg;


    @DataProvider(name = "TestData")
    public Object[][] getdata() throws IOException {
        return XLUtilityManager.getData();
    }

    @Test(dataProvider = "TestData")
    public void loginTest(String Username,String Pass) {
        driver.get(baseurl);
        loginPageObj = new LoginPage(driver);
        if (loginPageObj.LoginPage_logo.isDisplayed()) {
            loginPageObj.enterUseerName(Username);
            loginPageObj.enterPassword(Pass);
            loginPageObj.clickLoginBtn();
            try {
                Alert alt = driver.switchTo().alert();
                actualalertMsg = alt.getText(); // get content of the Alter Message
                alt.accept();
                // Compare Error Text with Expected Error Value
                assertEquals(actualalertMsg, BaseTest.EXPECTED_ALERT_TXT);
            } catch (NoAlertPresentException Ex) {
                actualTitle = driver.getTitle();
                // On Successful login compare Actual Page Title with Expected Title
                assertEquals(actualTitle, BaseTest.EXPECTED_HOMEPAGE_TITLE);
            }


        }

    }
}

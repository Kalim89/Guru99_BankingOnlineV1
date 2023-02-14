package Guru99_BankingOnline_PageObject;

import Guru99_BankingOnline_Utilities.Log;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class BaseTest
{
    public static WebDriver driver;
    public static final String BASE_URL = "https://www.demo.guru99.com";
    public static final int WAIT_TIME = 30;
    public static final String USERNAME = "mngr474579";
    public static final String PASSWORD = "papAdyj";
    public static final String EXPECTED_HOMEPAGE_TITLE = "Guru99 Bank Manager HomePage";
    public static final String EXPECTED_ALERT_TXT = "User or Password is not valid";

    @BeforeTest
    public void setUp()
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(WAIT_TIME, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        Log.info("Set up the Browser successfully");
    }

    @AfterTest
    public void tearDown()
    {
        driver.close();
        Log.info("Closing the browser");
    }

    public static String[][] getData() throws IOException {
        File excelFile = new File("C:\\Users\\user\\IdeaProjects\\Guru99_BankingOnline\\TestData\\LoginData.xlsx");
        FileInputStream fis = new FileInputStream(excelFile);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0);
        int noOfrows = sheet.getPhysicalNumberOfRows();    //Give no of physical no of row
        //System.out.println(sheet.getLastRowNum());  //Same as above but return index value
        int noOfColumns = sheet.getRow(0).getLastCellNum();
        String data[][] = new String[noOfrows - 1][noOfColumns];
        for (int i = 0; i < noOfrows - 1; i++) {
            for (int j = 0; j < noOfColumns; j++) {
                DataFormatter df = new DataFormatter();
                data[i][j] = df.formatCellValue(sheet.getRow(i + 1).getCell(j));
                //System.out.println(sheet.getRow(i).getCell(j).getStringCellValue());
            }
        }

        workbook.close();
        fis.close();
        return data;
    }

    public static String getScreenshot(WebDriver driver, String screenshotname) throws IOException {
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        String destination = System.getProperty("user.dir") + "/Screenshots/" + screenshotname + dateName + ".jpg";
        File finalDestination = new File(destination);
        FileUtils.copyFile(src, finalDestination);
        return destination;
    }


}

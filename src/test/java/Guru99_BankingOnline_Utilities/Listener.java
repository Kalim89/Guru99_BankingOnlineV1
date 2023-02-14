package Guru99_BankingOnline_Utilities;

import Guru99_BankingOnline_PageObject.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

import static Guru99_BankingOnline_PageObject.BaseTest.driver;

public class Listener implements ITestListener
{
    private static ExtentReports extent = ExtentReportManager.createInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private static String screenshotPath;

    @Override
    public synchronized void onStart(ITestContext context) {
        System.out.println("Extent Reports Version 3 Test Suite started!");
    }

    @Override
    public synchronized void onFinish(ITestContext context) {
        System.out.println(("Extent Reports Version 3  Test Suite is ending!"));
        extent.flush();
    }

    @Override
    public synchronized void onTestStart(ITestResult result) {
        System.out.println((result.getMethod().getMethodName() + " started!"));
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(),result.getMethod().getDescription());
        test.set(extentTest);
    }

    @Override
    public synchronized void onTestSuccess(ITestResult result) {
        System.out.println((result.getMethod().getMethodName() + " passed!"));
        test.get().pass("Test passed");
        try {
            screenshotPath = BaseTest.getScreenshot(driver,result.getName());
        } catch (IOException e) {
            throw new RuntimeException(e);

        }
        try {
            test.get().addScreenCaptureFromPath(screenshotPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public synchronized void onTestFailure(ITestResult result) {
        System.out.println((result.getMethod().getMethodName() + " failed!"));
        test.get().fail(result.getThrowable());
    }

    @Override
    public synchronized void onTestSkipped(ITestResult result) {
        System.out.println((result.getMethod().getMethodName() + " skipped!"));
        test.get().skip(result.getThrowable());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println(("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName()));
    }
}

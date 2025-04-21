package tests;

import utility.CommonStepsUtil.ScreenshotUtil;
import utility.DriverFactory;
import utility.ExtentManager;
import utility.ExtentTestManager;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;

public class BaseTest extends DriverFactory {

	protected ExtentReports extent;

	@BeforeSuite
	public void setUpReport() {
		extent = ExtentManager.getInstance();
	}

	@BeforeMethod
	public void setUp(Method method) throws Exception {
		initializeDriver();
		ExtentTestManager.startTest(method.getName());
	}

	@AfterMethod
	public void tearDownTest(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed");
			String screenshotPath = ScreenshotUtil.capture(driver, result.getName());
		    ExtentTestManager.getTest().addScreenCaptureFromPath(screenshotPath);
		    ExtentTestManager.getTest().fail(result.getThrowable());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			ExtentTestManager.getTest().log(Status.PASS, "Test Passed");
		} else {
			ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
		}
		quitDriver();
	}

	@AfterSuite
	public void tearDownReport() {
		extent.flush();
	}
}
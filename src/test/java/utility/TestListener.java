package utility;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class TestListener implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		ExtentTestManager.startTest(testName);
		ExtentTestManager.getTest().info("Test started: " + testName);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		ExtentTestManager.getTest().pass("Test passed");
		ExtentTestManager.endTest();
	}

	@Override
	public void onTestFailure(ITestResult result) {
		ExtentTestManager.getTest().fail("Test failed: " + result.getThrowable());
		ExtentTestManager.endTest();
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		ExtentTestManager.getTest().skip("Test skipped: " + result.getThrowable());
		ExtentTestManager.endTest();
	}

	// Optional: implement these if needed
	@Override
	public void onStart(ITestContext context) {
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("I am in on Finish method " + context.getName());
		ExtentTestManager.endTest();

	}
}

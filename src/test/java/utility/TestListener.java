package utility;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class TestListener implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		ExtentManager.startTest(testName);
		ExtentManager.getTest().info("Test started: " + testName);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		ExtentManager.getTest().pass("Test passed");
		ExtentManager.endTest();
	}

	@Override
	public void onTestFailure(ITestResult result) {
		ExtentManager.getTest().fail("Test failed: " + result.getThrowable());
		ExtentManager.endTest();
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		ExtentManager.getTest().skip("Test skipped: " + result.getThrowable());
		ExtentManager.endTest();
	}

	// Optional: implement these if needed
	@Override
	public void onStart(ITestContext context) {
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("I am in on Finish method " + context.getName());
		ExtentManager.endTest();

	}
}

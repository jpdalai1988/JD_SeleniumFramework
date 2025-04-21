package utility;

import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManager {

    public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    public static ExtentTest getTest() {
        return extentTest.get();
    }

    public static void startTest(String testName) {
        ExtentTest test = ExtentManager.getInstance().createTest(testName);
        extentTest.set(test);
    }
}

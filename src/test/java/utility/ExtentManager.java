package utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;
    public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();


    public static ExtentReports getInstance() {
        if (extent == null) {
            createInstance("test-output/ExtentReport.html");
        }
        return extent;
    }

    private static ExtentReports createInstance(String filePath) {
        ExtentSparkReporter reporter = new ExtentSparkReporter(filePath);
        reporter.config().setReportName("Automation Test Report");
        reporter.config().setDocumentTitle("Execution Report");

        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("QA Engineer", "Jitendra");
        extent.setSystemInfo("Browser", "Chrome");
        extent.setSystemInfo("Environment", "QA");
        return extent;
    }
    
    public static ExtentTest getTest() {
        return extentTest.get();
    }

    public static void startTest(String testName) {
        ExtentTest test = ExtentManager.getInstance().createTest(testName);
        extentTest.set(test);
    }
    
    public static void endTest() {
        extentTest.remove();
    }
}

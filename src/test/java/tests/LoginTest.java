package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import flows.LoginFlow;
import utility.ExcelUtil;
import utility.ExtentTestManager;

public class LoginTest extends BaseTest{

@Test(dataProvider = "loginData", dataProviderClass = ExcelUtil.class)
public void loginTest(String username, String password) throws Exception  {
    ExtentTestManager.getTest().info("Launching application...");

	LoginFlow lf=new LoginFlow(driver);
	lf.verifyLogin(username, password);
	ExtentTestManager.getTest().pass("Login successful!");
}
}
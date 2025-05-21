package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import flows.LoginFlow;
import utility.ExcelUtil;
import utility.ExtentManager;

public class LoginTest extends BaseTest{
  private static final Logger logger = LogManager.getLogger(LoginTest.class);

@Test(dataProvider = "loginData", dataProviderClass = ExcelUtil.class)
public void loginTest(String username, String password) throws Exception  {
    ExtentManager.getTest().info("Launching application...");
	LoginFlow lf=new LoginFlow(driver);
	lf.verifyLogin(username, password);
    logger.info("Test is passed");
    ExtentManager.getTest().pass("Login Test is passed");

}
}
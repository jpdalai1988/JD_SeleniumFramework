package flows;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import pages.LoginPage;
import utility.CommonStepsUtil;
import utility.ConfigReader;
import utility.DriverFactory;
import utility.JavaUtil;
import utility.SeleniumUtil;

public class LoginFlow extends SeleniumUtil {
    private static final Logger logger = LogManager.getLogger(LoginFlow.class);

    private WebDriver driver;
    private LoginPage lp;
    private JavaUtil ju;

    
    public LoginFlow(WebDriver driver) {
    	super(driver); 
        this.driver = driver;
        PageFactory.initElements(driver, this);
        lp = new LoginPage(driver); // Initialize AFTER driver is set
        ju=new JavaUtil();
    }

    /*
    
    public void setUserName(String username) {
        getWebDriverWait(driver).until(ExpectedConditions.visibilityOf(lp.userName));
        lp.userName.sendKeys(username);
        
        
    }

    public void setPassword(String password) {
        getWebDriverWait(driver).until(ExpectedConditions.visibilityOf(lp.pwd));
        lp.pwd.sendKeys(password);
        
    }

    public void clickLogin() {
        getWebDriverWait(driver).until(ExpectedConditions.visibilityOf(lp.loginbutton));
        lp.loginbutton.click();
        
    }

*/
    public void verifyLogin(String username, String password) throws InterruptedException {

    	String url = ConfigReader.get("url");
        logger.info("Navigating to URL: " + url);

        driver.get(url);
        ju.waitForSeconds(5);

        waitforVisibility(lp.userName);
        logger.info("Entering username: " + username);
        lp.userName.sendKeys(username);

        waitforVisibility(lp.pwd);
        logger.info("Entering password.");
        lp.pwd.sendKeys(password);
        logger.info("Clicking login button.");
        lp.loginbutton.click();
        System.out.println("Testing login with: " + username + " / " + password);
    }
}

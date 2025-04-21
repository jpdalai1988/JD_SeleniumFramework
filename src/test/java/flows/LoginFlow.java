package flows;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import pages.LoginPage;
import utility.CommonStepsUtil;

public class LoginFlow extends CommonStepsUtil {
    private WebDriver driver;
    private LoginPage lp;

    public LoginFlow(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        lp = new LoginPage(driver); // Initialize AFTER driver is set
    }

    
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

    public void verifyLogin(String username, String password) throws InterruptedException {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        Thread.sleep(4000); // Consider replacing with wait
        setUserName(username);
        setPassword(password);
        clickLogin();
        System.out.println("Testing login with: " + username + " / " + password);
    }
}

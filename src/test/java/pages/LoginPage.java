package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage  {
    WebDriver driver;
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); 
    }
	@FindBy(xpath = "//input[@placeholder='Username']")
	public WebElement userName;

	@FindBy(xpath = "//input[@placeholder='Password']")
	public WebElement pwd;

	@FindBy(xpath = "//button[normalize-space()='Login']")
	public WebElement loginbutton;

	}

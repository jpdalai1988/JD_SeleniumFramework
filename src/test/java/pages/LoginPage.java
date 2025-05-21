package pages;


import org.openqa.selenium.By;
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
    
    /*
	@FindBy(xpath = "//input[@placeholder='username']")
	public WebElement userName;

	@FindBy(xpath = "//input[@placeholder='password']")
	public WebElement pwd;

	@FindBy(xpath = "//button[normalize-space()='Login']")
	public WebElement loginbutton;
	
	*/
	
	public By userName = By.xpath("//input[@name='username']");
	public By pwd = By.xpath("//input[@name='password']");
	public By loginbutton = By.xpath("//button[normalize-space()='Login']");
	public By dashboardhome = By.xpath("//h6[normalize-space()='Dashboard']");
	public By userdropdown = By.xpath("//i[@class='oxd-icon bi-caret-down-fill oxd-userdropdown-icon']");
	public By logoutbutton = By.xpath("//a[normalize-space()='Logout']");


	
	}

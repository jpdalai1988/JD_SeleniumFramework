package utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	public static WebDriver driver;
	public static Properties prop;

	public static void loadConfig() throws IOException {
		prop = new Properties();
		FileInputStream fis = new FileInputStream("src/test/resources/environment.properties");
		prop.load(fis);
	}

	public static void initializeDriver() throws IOException {
		loadConfig();
		String browserName = prop.getProperty("browser").toLowerCase();

		switch (browserName) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;

		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;

		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;

		default:
			throw new IllegalArgumentException("Invalid browser specified in config: " + browserName);
		}

		driver.manage().window().maximize();
	//	driver.get(prop.getProperty("url")); // Optional: use URL from config
	}

	public static void quitDriver() {
		if (driver != null) {
			driver.quit();
		}
	}
}

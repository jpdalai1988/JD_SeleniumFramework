package utility;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;

public class SeleniumUtil {

	public static WebDriver driver;
	public static WebDriverWait wait;
	public static Actions actions;

	public SeleniumUtil(WebDriver driver) {
		SeleniumUtil.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		actions = new Actions(driver);
	}

//    // Constructor
//    public SeleniumUtil(WebDriver driver) {
//        SeleniumUtil.driver = driver;
//        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
//        actions = new Actions(driver);
//    }

	// Element Interaction (Click Method)
	public void waitforVisibility(WebElement locator) {
		wait.until(ExpectedConditions.visibilityOf(locator));
	}

	// Element Interaction (Click Method)
	public void click(By locator) {
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}

	// Element Interaction (SendKeys Method)
	public void type(By locator, String text) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(text);
	}

	// Element Interaction (GetText Method)
	public String getText(By locator) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
	}

	// Element Interaction (isDisplayed Method)
	public boolean isElementDisplayed(By locator) {
		try {
			return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
		} catch (NoSuchElementException | TimeoutException e) {
			return false;
		}
	}

	// Dropdowns by Text
	public void selectByVisibleText(By locator, String text) {
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		new Select(element).selectByVisibleText(text);
	}

	// Dropdowns by Index
	public void selectByIndex(By locator, int index) {
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		new Select(element).selectByIndex(index);
	}

	// Dropdowns by value
	public void selectByValue(By locator, String value) {
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		new Select(element).selectByValue(value);
	}

	// Alerts
	public void acceptAlert() {
		wait.until(ExpectedConditions.alertIsPresent()).accept();
	}

	public void dismissAlert() {
		wait.until(ExpectedConditions.alertIsPresent()).dismiss();
	}

	public String getAlertText() {
		return wait.until(ExpectedConditions.alertIsPresent()).getText();
	}

	// Frames
	public void switchToFrame(String newFrame) {
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(newFrame));
	}

	public void switchToDefaultContent() {
		driver.switchTo().defaultContent();
	}

	// Window Handling
	public void switchToWindow(String windowTitle) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String window : allWindows) {
			driver.switchTo().window(window);
			if (driver.getTitle().equals(windowTitle)) {
				break;
			}
		}
	}

	// Mouse Actions
	public void hoverOverElement(By locator) {
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		actions.moveToElement(element).perform();
	}

	// Screenshot
	public void takeScreenshot(String filePath) {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(src, new File(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Scroll using JavascriptExecutor
	public void scrollToElement(By locator) {
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	
}

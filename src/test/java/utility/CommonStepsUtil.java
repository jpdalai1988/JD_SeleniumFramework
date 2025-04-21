package utility;

import java.io.File;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonStepsUtil {
	/**
     * Generates WebDriverWait instance with the specified wait time duration
     *
     * @param driver  WebDriver Instance
     * @return WebDriverWait
     *
     */
    public static WebDriverWait getWebDriverWait(WebDriver driver){

        return new WebDriverWait(driver, Duration.ofMillis(90000L));
    }

    /**
     * Generates WebDriverWait instance with the specified wait time & polling interval
     *
     * @param driver  WebDriver Instance
     * @return WebDriverWait
     *
     */
    public static Wait<WebDriver> getFluentWait(WebDriver driver){

        return new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(90))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);
    }

 // Step: Verify page title
    public boolean isPageTitle(String expectedTitle, WebDriver driver) {
        String actualTitle = driver.getTitle();
        return actualTitle.equalsIgnoreCase(expectedTitle);
    }
    
    //Taking Screenshot
    public class ScreenshotUtil {
        public static String capture(WebDriver driver, String screenshotName) {
            String path = "screenshots/" + screenshotName + ".png";
            try {
                File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(src, new File(path));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return path;
        }
    }
}


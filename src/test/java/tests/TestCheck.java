package tests;

import org.testng.annotations.Test;

public class TestCheck extends BaseTest{

	  @Test(priority = 1)
	
	public void openGoogle() {
		driver.get("https://www.google.com");
        System.out.println("Title: " + driver.getTitle());
    }
	

}

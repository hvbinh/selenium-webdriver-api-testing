package webdriver;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Check_Environment_TestNG {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {

		driver = new FirefoxDriver();
		driver.get("http://facebook.com");
	}

	@Test
	public void TC_01() {
	}

	@Test
	public void TC_02() {
	}

	@Test
	public void TC_03() {
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}

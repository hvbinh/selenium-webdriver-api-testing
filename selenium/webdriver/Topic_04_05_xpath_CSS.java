package webdriver;

import org.testng.annotations.Test;

import com.sun.org.glassfish.gmbal.Description;

import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertTrue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_04_05_xpath_CSS {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
	}

	@Test(description = "TC_01 login with empty email, passworld")
	public void TC_01_Login_With_Empty_Email_Password() {
		driver.get("http://live.demoguru99.com");
		driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(),'My Account')]")).click();
		driver.findElement(By.id("send2")).click();

		String emailErrorMessage = driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText();
		String expectedTest = "This is a required field.";
		Assert.assertEquals(emailErrorMessage, expectedTest);
		String passwordErrorMessage = driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText();
		Assert.assertEquals(passwordErrorMessage, expectedTest);

	}

	@Test(description = "TC_02 login with invalid email")
	public void TC_02_Login_With_Invalid_Email() {
		driver.get("http://live.demoguru99.com");
		driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(),'My Account')]")).click();

		driver.findElement(By.id("email")).sendKeys("1234@123.12");
		driver.findElement(By.id("pass")).sendKeys("123456");

		driver.findElement(By.id("send2")).click();

		String emailErrorMessage = driver.findElement(By
				.xpath("//div[contains(text(),'Please enter a valid email address. For example johndoe@domain.com.')]"))
				.getText();
		String expectedTest = "Please enter a valid email address. For example johndoe@domain.com.";
		Assert.assertEquals(emailErrorMessage, expectedTest);

	}

	@Test(description = "TC_03 login with pass < 6 characters")
	public void TC_03_Login_With_Password_Less_Than_6_Characters() {
		driver.get("http://live.demoguru99.com");
		driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(),'My Account')]")).click();

		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123");

		driver.findElement(By.id("send2")).click();

		String passWordErrorMessage = driver.findElement(By.id("advice-validate-password-pass")).getText();
		String expectedTest = "Please enter 6 or more characters without leading or trailing spaces.";
		Assert.assertEquals(passWordErrorMessage, expectedTest);

	}

	@Test(description = "TC_04 login with incorrect password")
	public void TC_04_Login_With_Incorrect_Password() {
		driver.get("http://live.demoguru99.com");
		driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(),'My Account')]")).click();

		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123123123");

		driver.findElement(By.id("send2")).click();

		String errorMessage = driver.findElement(By.xpath("//span[contains(text(),'Invalid login or password.')]"))
				.getText();
		String expectedTest = "Invalid login or password.";
		Assert.assertEquals(errorMessage, expectedTest);

	}

	@Test(description = "TC_05 login with valid username and password")
	public void TC_05_Login_With_Valid_Email_Password() {
		driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(),'My Account')]")).click();

		driver.findElement(By.id("email")).sendKeys("automation_13@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123123");

		driver.findElement(By.id("send2")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//h1[contains(.,'My Dashboard')]")).getText(), "MY DASHBOARD");

		Assert.assertEquals(
				driver.findElement(By.xpath("//strong[contains(string(),'Hello, Automation Testing!')]")).getText(),
				"Hello, Automation Testing!");

		String contactInformationText = driver.findElement(By.xpath("//div[@class='col-1']//p")).getText();
		assertTrue(contactInformationText.contains("Automation Testing"));
		assertTrue(contactInformationText.contains("automation_13@gmail.com"));

		driver.findElement(By.xpath("//span[@class='label' and contains(text(),'Account')]")).click();
		driver.findElement(By.linkText("Log Out")).click();

	}

	@Test(description = "TC_06 create new account")
	public void TC_06() {
		driver.findElement(By.xpath("(//a[@title='My Account'])[last()]")).click();
		driver.findElement(By.xpath("//span[contains(text(),'Create an Account')]")).click();

		/*
		 * DateFormat dateFormat = new SimpleDateFormat("yyymmddhhssmm"); Calendar
		 * calendar = Calendar.getInstance(); String dateTime =
		 * dateFormat.format(calendar.getTime());
		 */
		Random random = new Random();
		Integer number = random.nextInt(9999);

		String firstName = "Tony";
		String lastName = "Ferguson";
		String email = "tonyferguson" + number.toString() + "@abc.com";

		driver.findElement(By.id("firstname")).sendKeys(firstName);
		driver.findElement(By.id("lastname")).sendKeys(lastName);
		driver.findElement(By.id("email_address")).sendKeys(email);
		driver.findElement(By.id("password")).sendKeys("123456");
		driver.findElement(By.id("confirmation")).sendKeys("123456");

		driver.findElement(By.xpath("//button[@type='submit' and @title='Register']")).click();

		String textDisplay = "Thank you for registering with Main Website Store.";
		String actualTextDisplay = driver
				.findElement(By.xpath("//span[.='Thank you for registering with Main Website Store.']")).getText();
		Assert.assertEquals(actualTextDisplay, textDisplay);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains(firstName));
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains(lastName));
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains(email));

		
		driver.findElement(By.xpath("//span[@class='label' and .='Account']")).click();
		driver.findElement(By.xpath("//a[.='Log Out']")).click();

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@class='page-title']//h2[contains(text(),'This is demo site for')]")));
		
		Assert.assertEquals(driver.getTitle(), "Home page");
	}

	@AfterClass
	public void afterClass() {
		// driver.close();
	}

}

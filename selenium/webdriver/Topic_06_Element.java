package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_06_Element {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.get("https://automationfc.github.io/basic-form/index.html");
	}

	@Test(description = "check element is displayed")
	public void TC_01() {
		Boolean email, ageUnder18, education;
		email = driver.findElement(By.xpath("//label[@for='email']")).isDisplayed();
		ageUnder18 = driver.findElement(By.xpath("//label[@for='under_18']")).isDisplayed();
		education = driver.findElement(By.xpath("//label[@for='edu']")).isDisplayed();

		Assert.assertTrue(email);
		Assert.assertTrue(ageUnder18);
		Assert.assertTrue(education);
	}

	@Test(description = "check element is enable/disable")
	public void TC_02() {
		Boolean email, ageUnder18, education, jobRole1, jobRole2, development, slider1;
		email = driver.findElement(By.xpath("//label[@for='email']")).isEnabled();
		ageUnder18 = driver.findElement(By.xpath("//label[@for='under_18']")).isEnabled();
		education = driver.findElement(By.xpath("//label[@for='edu']")).isEnabled();
		jobRole1 = driver.findElement(By.xpath("//label[@for='job1']")).isEnabled();
		jobRole2 = driver.findElement(By.xpath("//label[@for='job2']")).isEnabled();
		development = driver.findElement(By.xpath("//label[@for='development']")).isEnabled();
		slider1 = driver.findElement(By.xpath("//label[@for='slider-1']")).isEnabled();

		if (email == true) {
			System.out.println("email is enable");
		}
		if (ageUnder18 == true) {
			System.out.println("ageUnder18 is enable");
		}
		if (education == true) {
			System.out.println("education is enable");
		}
		if (jobRole1 == true) {
			System.out.println("jobRole1 is enable");
		}
		if (jobRole2 == true) {
			System.out.println("jobRole2 is enable");
		}
		if (development == true) {
			System.out.println("development is enable");
		}
		if (slider1 == true) {
			System.out.println("slider1 is enable");
		}

		Assert.assertTrue(email);
		Assert.assertTrue(ageUnder18);
		Assert.assertTrue(education);
		Assert.assertTrue(jobRole1);
		Assert.assertTrue(jobRole2);
		Assert.assertTrue(development);
		Assert.assertTrue(slider1);

		Boolean password, radio, biography, jobRole3, checkbox, slider2;
		password = driver.findElement(By.xpath("//input[@id='password' and @disabled='disabled']")).isEnabled();
		radio = driver.findElement(By.xpath("//input[@id='radio-disabled']")).isEnabled();
		biography = driver.findElement(By.xpath("//textarea[@id='bio']")).isEnabled();
		jobRole3 = driver.findElement(By.xpath("//select[@id='job3']")).isEnabled();
		checkbox = driver.findElement(By.xpath("//input[@id='check-disbaled']")).isEnabled();
		slider2 = driver.findElement(By.xpath("//input[@id='slider-2']")).isEnabled();

		if (password == false) {
			System.out.println("password is disable");
		}
		if (radio == false) {
			System.out.println("radio is disable");
		}
		if (biography == false) {
			System.out.println("biography is disable");
		}
		if (jobRole3 == false) {
			System.out.println("jobRole3 is disable");
		}
		if (checkbox == false) {
			System.out.println("checkbox is disable");
		}
		if (slider2 == false) {
			System.out.println("slider2 is disable");
		}

		Assert.assertFalse(password);
		Assert.assertFalse(radio);
		Assert.assertFalse(biography);
		Assert.assertFalse(jobRole3);
		Assert.assertFalse(checkbox);
		Assert.assertFalse(slider2);
	}

	@Test(description = "check element is selected")
	public void TC_03() {
		Boolean ageUnder18, development;
		driver.findElement(By.xpath("//input[@id='under_18']")).click();
		driver.findElement(By.xpath("//input[@id='development']")).click();
		ageUnder18 = driver.findElement(By.xpath("//input[@id='under_18']")).isSelected();
		development = driver.findElement(By.xpath("//input[@id='development']")).isSelected();
		Assert.assertTrue(ageUnder18);
		Assert.assertTrue(development);
		
		driver.findElement(By.xpath("//input[@id='development']")).click();
		development = driver.findElement(By.xpath("//input[@id='development']")).isSelected();
		Assert.assertFalse(development);

	}
	@Test(description = "register function with mailChimp")
	public void TC_04() {
		driver.get("https://login.mailchimp.com/signup/");
		driver.findElement(By.id("email")).sendKeys("tonytest1@test.com");
		driver.findElement(By.id("new_username")).sendKeys("tony1");
		
		driver.findElement(By.id("new_password")).sendKeys("a");
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isEnabled());
		
		driver.findElement(By.id("new_password")).clear();
		driver.findElement(By.id("new_password")).sendKeys("A");
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isEnabled());

		driver.findElement(By.id("new_password")).clear();
		driver.findElement(By.id("new_password")).sendKeys("1");
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed']")).isEnabled());

		driver.findElement(By.id("new_password")).clear();
		driver.findElement(By.id("new_password")).sendKeys("#");
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed']")).isEnabled());
		
		driver.findElement(By.id("new_password")).clear();
		driver.findElement(By.id("new_password")).sendKeys("12345678");
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed']")).isEnabled());
		
		Boolean signInButton = driver.findElement(By.xpath("//button[@id='create-account']")).isEnabled();
		Assert.assertFalse(signInButton);
		
		driver.findElement(By.id("marketing_newsletter")).click();
		Assert.assertTrue(driver.findElement(By.id("marketing_newsletter")).isSelected());

	}

	@AfterClass
	public void afterClass() {
	}

}

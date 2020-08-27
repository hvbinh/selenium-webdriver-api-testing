package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_06_Element {
	WebDriver driver;
	By emailTextBoxBy = By.xpath("//input[@id='mail']");
	By ageUnder18RadioBy = By.xpath("//input[@id='under_18']");
	By educationTextAreaBy = By.xpath("//textarea[@id='edu']");
	
	By jobRole1DropDownBy = By.xpath("//select[@id='job1']");
	By jobRole2DropDownBy = By.xpath("//select[@id='job2']");
	By developeCheckBoxBy = By.xpath("//input[@id='development']");
	By slider1By = By.xpath("//input[@id='slider-1']");
	
	By passwordTextBoxBy = By.xpath("//input[@id='password' and @disabled='disabled']");
	By disableRadioBy = By.xpath("//input[@id='radio-disabled']");
	By biographyTextArea = By.xpath("//textarea[@id='bio']");
	By jobRole3DropDownBy = By.xpath("//select[@id='job3']");
	By disableCheckboxBy = By.xpath("//input[@id='check-disbaled']");
	By slider2By = By.xpath("//input[@id='slider-2']");
	
	By javaCheckboxBy = By.xpath("//input[@id='java']");
	


	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.get("https://automationfc.github.io/basic-form/index.html");
	}

	@Test(description = "check element is displayed")
	public void TC_01_Check_Element_Is_Displayed() {
		if(isElementDisplayed(emailTextBoxBy))
		{
			sendKeyToElement(emailTextBoxBy, "Automation Testing");
		}
		if(isElementDisplayed(educationTextAreaBy))
		{
			sendKeyToElement(educationTextAreaBy, "Automation Testing");
		}
		if(isElementDisplayed(ageUnder18RadioBy))
		{
			clickToElement(ageUnder18RadioBy);
		}
		
	}

	@Test(description = "check element is enable/disable")
	public void TC_02_Check_Element_Enable_Disable() {
		
		driver.navigate().refresh();

		Assert.assertTrue(isElementEnable(emailTextBoxBy));
		Assert.assertTrue(isElementEnable(ageUnder18RadioBy));
		Assert.assertTrue(isElementEnable(educationTextAreaBy));
		Assert.assertTrue(isElementEnable(jobRole1DropDownBy));
		Assert.assertTrue(isElementEnable(jobRole2DropDownBy));
		Assert.assertTrue(isElementEnable(developeCheckBoxBy));
		Assert.assertTrue(isElementEnable(slider1By));

		Assert.assertFalse(isElementEnable(passwordTextBoxBy));
		Assert.assertFalse(isElementEnable(disableRadioBy));
		Assert.assertFalse(isElementEnable(biographyTextArea));
		Assert.assertFalse(isElementEnable(jobRole3DropDownBy));
		Assert.assertFalse(isElementEnable(disableCheckboxBy));
		Assert.assertFalse(isElementEnable(slider2By));
	}

	@Test(description = "check element is selected")
	public void TC_03() {
		
		driver.navigate().refresh();
		
		clickToElement(ageUnder18RadioBy);
		clickToElement(javaCheckboxBy);
		
		Assert.assertTrue(isElementSelected(ageUnder18RadioBy));
		Assert.assertTrue(isElementSelected(javaCheckboxBy));
		
		clickToElement(javaCheckboxBy);
		Assert.assertFalse(isElementSelected(javaCheckboxBy));
		
	}
	//@Test(description = "register function with mailChimp")
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
	public boolean isElementDisplayed(By by)
	{
		WebElement element = driver.findElement(by);
		if(element.isDisplayed())
		{
			System.out.println("element is displayed "+element);
			return true;
			
		}
		else
		{
			System.out.println("element is not displayed "+element);
			return false;
		}
		
	}
	public boolean isElementEnable(By by)
	{
		WebElement element = driver.findElement(by);
		if(element.isEnabled())
		{
			System.out.println("element is enabled "+element);
			return true;
			
		}
		else
		{
			System.out.println("element is disable "+element);
			return false;
		}
		
	}
	public boolean isElementSelected(By by)
	{
		WebElement element = driver.findElement(by);
		if(element.isSelected())
		{
			System.out.println("element is selected "+element);
			return true;
			
		}
		else
		{
			System.out.println("element is de-selected "+element);
			return false;
		}
		
	}
	public void sendKeyToElement(By by,String value)
	{
		WebElement element = driver.findElement(by);
		element.clear();
		element.sendKeys(value);
	}
	public void clickToElement(By by)
	{
		WebElement element = driver.findElement(by);
		element.click();
	}


	@AfterClass
	public void afterClass() {
	}

}

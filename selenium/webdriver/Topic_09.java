package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_09 {
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;

  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
	  driver.manage().window().maximize();
	  jsExecutor = (JavascriptExecutor) driver;
	  
  }
  //@Test
  public void TC_01_Verify_Enable_Disable() {
	  
	  driver.get("https://www.fahasa.com/customer/account/create?attempt=1");
	  
	  //first time go to page, login button will disable
	  driver.findElement(By.xpath("//a[text()='Đăng nhập']")).click();
	  Assert.assertFalse(driver.findElement(By.xpath("//button[@class='fhs-btn-login']")).isEnabled());
	  
	  //after input username, password then login button is enable
	  String phone = "0123456789";
	  String pass = "123456";
	  driver.findElement(By.id("login_username")).sendKeys(phone);
	  driver.findElement(By.id("login_password")).sendKeys(pass);
	  Assert.assertTrue(driver.findElement(By.xpath("//button[@class='fhs-btn-login']")).isEnabled());
	  
	  //go to Đăng ký tab
	  driver.findElement(By.xpath("//a[text()='Đăng ký']")).click();
	  Assert.assertFalse(driver.findElement(By.xpath("//button[@class='fhs-btn-register']")).isEnabled());
  }
  //@Test
  public void TC_02_Practice_With_Button()
  {
	  driver.get("http://live.demoguru99.com/");
	  //1. Click by function click()
	  //driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	  sleepInSeconds(3);
	  
	  //2. Click by javascript
	  driver.get("http://live.demoguru99.com/");
	  WebElement linkMyAccount = driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']"));
	  jsExecutor.executeScript("arguments[0].click();", linkMyAccount);
	  
  }
  @Test
  public void TC_03_Default_CheckBox()
  {
	  driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
	  
	  //default
	  WebElement duaZoneCheckbox = driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input"));
	  duaZoneCheckbox.click();
	  Assert.assertTrue(duaZoneCheckbox.isSelected());
	  sleepInSeconds(3);
	  
	  driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
	  WebElement petrolRadio = driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input"));
	  petrolRadio.click();
	  sleepInSeconds(3);
	  Assert.assertTrue(petrolRadio.isSelected());
	  //check if not select then select
	  if(!driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input")).isSelected())
	  {
		  driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input")).click();
	  }
  }
  @Test
  public void custom_Checkbox_RadioButton()
  {
	  driver.get("https://material.angular.io/components/radio/examples");
	  
	  WebElement winter = driver.findElement(By.xpath("//label[@class='mat-radio-label']//div[contains(.,'Winter')]/preceding-sibling::div/input"));
	  jsExecutor.executeScript("arguments[0].click();", winter);
	  Assert.assertTrue(winter.isSelected());

	  
  }
  public void sleepInSeconds(long second)
  {
	  try {
		Thread.sleep(second * 1000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	} 
  }

  @AfterClass
  public void afterClass() {
  }

}

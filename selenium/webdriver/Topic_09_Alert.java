package webdriver;

import org.testng.annotations.Test;


import org.testng.annotations.BeforeClass;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.security.UserAndPassword;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_09_Alert {
  WebDriver driver;
  WebDriverWait explicitWait;
  Alert alert;
  By resultText = By.xpath("//p[@id='result']");
  
  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
	  driver.manage().window().maximize();
	  explicitWait = new WebDriverWait(driver, 20);
	  
  }
  @Test
  public void TC_01_Accept_Alert()
  {
	  driver.get("http://demo.guru99.com/v4/index.php");
	  driver.findElement(By.name("btnLogin")).click();
	  sleepInSecond(2);
	  
	  explicitWait.until(ExpectedConditions.alertIsPresent());
	  //switch to alert
	  alert = driver.switchTo().alert();
	  Assert.assertEquals(alert.getText(), "User or Password is not valid");
	  alert.accept();
	  
	  driver.get("https://automationfc.github.io/basic-form/");
	  driver.findElement(By.xpath("//button[contains(.,'Click for JS Alert')]")).click();
	  sleepInSecond(2);
	  explicitWait.until(ExpectedConditions.alertIsPresent());
	  alert = driver.switchTo().alert();
	  Assert.assertEquals(alert.getText(), "I am a JS Alert");
	  alert.accept();
	  Assert.assertEquals(driver.findElement(resultText).getText(), "You clicked an alert successfully");
	  
	  
  }
  @Test
  public void TC_02_Confirm_Alert()
  {
	  driver.get("https://automationfc.github.io/basic-form/");
	  driver.findElement(By.xpath("//button[contains(.,'Click for JS Confirm')]")).click();
	  sleepInSecond(2);
	  explicitWait.until(ExpectedConditions.alertIsPresent());
	  alert = driver.switchTo().alert();
	  Assert.assertEquals(alert.getText(), "I am a JS Confirm");
	  alert.dismiss();
	  sleepInSecond(2);
	  Assert.assertEquals(driver.findElement(resultText).getText(), "You clicked: Cancel"); 
  }
  @Test
  public void TC_03_Prompt_Alert()
  {
	  String fullName = "Automation FC";
	  driver.get("https://automationfc.github.io/basic-form/");
	  driver.findElement(By.xpath("//button[contains(.,'Click for JS Prompt')]")).click();
	  sleepInSecond(2);
	  explicitWait.until(ExpectedConditions.alertIsPresent());
	  alert = driver.switchTo().alert();
	  Assert.assertEquals(alert.getText(), "I am a JS prompt");
	  alert.sendKeys(fullName);
	  sleepInSecond(2);
	  alert.accept();
	  sleepInSecond(2);
	  Assert.assertEquals(driver.findElement(resultText).getText(), "You entered: "+fullName);
	  
	  
  }
  @Test
  public void TC_05_Authentication_Alert_Selenium()
  {
	  driver.get("http://the-internet.herokuapp.com");
	  String basicAuthenLink = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
	  inputUserAndPasswordToHreft(basicAuthenLink,"admin","admin");
	  
	  sleepInSecond(2);
	  Assert.assertTrue(driver.findElement(By.xpath("//p[contains(string(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
	  
	 
  }
  @Test
  public void TC_05_Authentication_Alert()
  {
	  
  }
  public void inputUserAndPasswordToHreft(String href,String user, String pass)
  {
	  String []hrefSplit = href.split("//");
	  href = hrefSplit[0]+"//"+user+":"+pass+"@"+hrefSplit[1];
	  driver.get(href);
	  
	  
  }
  public void sleepInSecond(long time)
  {
	  try {
		Thread.sleep(time * 1000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
  }

  @AfterClass
  public void afterClass() {
  }

}

package webdriver;

import org.testng.annotations.Test;

import com.sun.org.glassfish.gmbal.Description;



import org.testng.annotations.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_04_05 {
	WebDriver driver;

  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
	  driver.get("http://live.demoguru99.com");  
  }
  @Test(description = "TC_01 login with empty username, passworld") 
  public void TC_01()
  {
	  driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(),'My Account')]")).click();
	  driver.findElement(By.id("send2")).click();
	  
	  String actualTest1 = driver.findElement(By.xpath("//div[@id='advice-required-entry-email' and text()='This is a required field.']")).getText();
	  String expectedTest = "This is a required field.";
	  Assert.assertEquals(actualTest1, expectedTest);
	  String actualTest2 = driver.findElement(By.xpath("//div[@id='advice-required-entry-pass' and text()='This is a required field.']")).getText();
	  Assert.assertEquals(actualTest2, expectedTest); 
	  
	  driver.navigate().back();
  }
  @Test(description = "TC_02 login with invalid email") 
  public void TC_02()
  {
	  driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(),'My Account')]")).click();
	  
	  driver.findElement(By.id("email")).sendKeys("1234@123.12");
	  driver.findElement(By.id("pass")).sendKeys("123456");
	  
	  driver.findElement(By.id("send2")).click();
	  
	  String actualTest = driver.findElement(By.xpath("//div[contains(text(),'Please enter a valid email address. For example johndoe@domain.com.')]")).getText();
	  String expectedTest = "Please enter a valid email address. For example johndoe@domain.com.";
	  Assert.assertEquals(actualTest, expectedTest);
	  
	  driver.navigate().back();
  }
  @Test(description = "TC_03 login with pass < 6 characters") 
  public void TC_03()
  {
	  driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(),'My Account')]")).click();
	  
	  driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
	  driver.findElement(By.id("pass")).sendKeys("123");
	  
	  driver.findElement(By.id("send2")).click();
	  
	  String actualTest = driver.findElement(By.xpath("//div[contains(text(),'Please enter 6 or more characters without leading or trailing spaces.')]")).getText();
	  String expectedTest = "Please enter 6 or more characters without leading or trailing spaces.";
	  Assert.assertEquals(actualTest, expectedTest);
	  
	  driver.navigate().back();
  }
  @Test(description = "TC_04 login with incorrect password") 
  public void TC_04()
  {
	  driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(),'My Account')]")).click();
	  
	  driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
	  driver.findElement(By.id("pass")).sendKeys("123123123");
	  
	  driver.findElement(By.id("send2")).click();
	  
	  String actualTest = driver.findElement(By.xpath("//span[contains(text(),'Invalid login or password.')]")).getText();
	  String expectedTest = "Invalid login or password.";
	  Assert.assertEquals(actualTest, expectedTest);
	  
	  driver.navigate().back();
  }
  @Test(description = "TC_05 login with valid username and password") 
  public void TC_05()
  {
	  driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(),'My Account')]")).click();
	  
	  driver.findElement(By.id("email")).sendKeys("automation_13@gmail.com");
	  driver.findElement(By.id("pass")).sendKeys("123123");
	  
	  driver.findElement(By.id("send2")).click();
	  
	  String actualTest1 = driver.findElement(By.xpath("//h1[contains(text(),'My Dashboard')]")).getText();
	  String expectedTest1 = "My Dashboard";
	  Assert.assertEquals(actualTest1.toLowerCase(), expectedTest1.toLowerCase());
	  
	  String actualTest2 = driver.findElement(By.xpath("//strong[contains(text(),'Hello, Automation Testing!')]")).getText();
	  String expectedTest2 = "Hello, Automation Testing!";
	  Assert.assertEquals(actualTest2, expectedTest2);
	  
	  String actualTest3 = driver.findElement(By.xpath("//div[@class='box-content']//p[contains(text(),'Automation Testing')]")).getText();
	  String expectedTest3 = "Automation Testing";
	  Assert.assertEquals(actualTest3, expectedTest3);
	  
	  String actualTest4 = driver.findElement(By.xpath("//div[@class='box-content']//p[contains(text(),'automation_13@gmail.com')]")).getText();
	  String expectedTest4 = "automation_13@gmail.com";
	  Assert.assertEquals(actualTest4, expectedTest4);
	  
	  driver.navigate().back();
  }

  @AfterClass
  public void afterClass() {
	 //driver.close();
  }

}

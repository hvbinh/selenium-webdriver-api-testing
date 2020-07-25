package webdriver;

import org.testng.annotations.Test;


import org.testng.annotations.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;



public class Topic_06_Web_Browser {
  WebDriver driver;

  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
	  driver.get("http://live.demoguru99.com");
  }
  @SuppressWarnings("deprecation")
@Test(description = "verify URL")
  public void TC_01()
  {
	  driver.findElement(By.xpath("(//a[@title='My Account'])[last()]")).click();
	  String loginURL = "http://live.demoguru99.com/index.php/customer/account/login/";
	  Assert.assertEquals(driver.getCurrentUrl(), loginURL);
	  
	  driver.findElement(By.xpath("//span[contains(text(),'Create an Account')]")).click();
	  String createURL = "http://live.demoguru99.com/index.php/customer/account/create/";
	  Assert.assertEquals(driver.getCurrentUrl(), createURL);
	  
  }
  @Test(description = "verify title")
  public void TC_02()
  {
	  driver.get("http://live.demoguru99.com");
	  driver.findElement(By.xpath("(//a[@title='My Account'])[last()]")).click();
	  String loginTitle = "Customer Login";
	  Assert.assertEquals(driver.getTitle(), loginTitle);
	  
	  driver.findElement(By.xpath("//span[contains(text(),'Create an Account')]")).click();
	  String createTitle = "Create New Customer Account";
	  Assert.assertEquals(driver.getTitle(), createTitle);
  }
  @Test(description = "navigate function")
  public void TC_03()
  {
	  driver.get("http://live.demoguru99.com");
	  driver.findElement(By.xpath("(//a[@title='My Account'])[last()]")).click(); 
	  driver.findElement(By.xpath("//span[contains(text(),'Create an Account')]")).click();
	  String createURL = "http://live.demoguru99.com/index.php/customer/account/create/";
	  Assert.assertEquals(driver.getCurrentUrl(), createURL);
	  
	  driver.navigate().back();
	  String loginURL = "http://live.demoguru99.com/index.php/customer/account/login/";
	  Assert.assertEquals(driver.getCurrentUrl(), loginURL);
	  
	  driver.navigate().forward();
	  String createTitle = "Create New Customer Account";
	  Assert.assertEquals(driver.getTitle(), createTitle);
  }
  @Test(description = "get page source")
  public void TC_04()
  {
	  driver.get("http://live.demoguru99.com");
	  driver.findElement(By.xpath("(//a[@title='My Account'])[last()]")).click(); 
	  Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));
	  
	  driver.findElement(By.xpath("//span[contains(text(),'Create an Account')]")).click();
	  Assert.assertTrue(driver.getPageSource().contains("Create an Account"));
  }
  @AfterClass
  public void afterClass() {
  }

}

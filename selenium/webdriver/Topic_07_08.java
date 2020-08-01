package webdriver;

import org.testng.annotations.Test;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import org.testng.annotations.BeforeClass;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.james.mime4j.field.datetime.DateTime;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_07_08 {
	WebDriver driver;

  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
	  
  }
  
  @Test(description = "process textbox/textarea",enabled = false)
  public void TC_01() {
	  driver.get("http://demo.guru99.com/v4/");
	  
	  driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("mngr274687");
	  driver.findElement(By.xpath("//input[@name='password']")).sendKeys("mynysUq");
	  driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
	  String homePageURL = "http://demo.guru99.com/v4/manager/Managerhomepage.php";
	  Assert.assertEquals(driver.getCurrentUrl(), homePageURL);
	  
	  /*
	  driver.findElement(By.linkText("New Customer")).click();
	  driver.findElement(By.xpath("//input[@name='name']")).sendKeys("tony fer");
	  driver.findElement(By.xpath("//input[@id='dob']")).sendKeys("04/04/1990");
	  driver.findElement(By.xpath("//textarea[@name='addr']")).sendKeys("123 address");
	  driver.findElement(By.xpath("//input[@name='city']")).sendKeys("Ho Chi Minh");
	  driver.findElement(By.xpath("//input[@name='state']")).sendKeys("Thu Duc");
	  driver.findElement(By.xpath("//input[@name='pinno']")).sendKeys("123456");
	  driver.findElement(By.xpath("//input[@name='telephoneno']")).sendKeys("0123456789");
	  driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys("tonyfer2@yopmail.com");
	  driver.findElement(By.xpath("//input[@name='password']")).sendKeys("123456");
	  driver.findElement(By.xpath("//input[@name='sub']")).click();
	  
	  System.out.println("Customer id: "+driver.findElement(By.xpath("//table[@id='customer']//td[contains(text(),'Customer ID')]/following-sibling::td")).getText());
	  System.out.println("Customer name: "+driver.findElement(By.xpath("//table[@id='customer']//td[contains(text(),'Customer Name')]/following-sibling::td")).getText());
	  System.out.println("Birthdate: "+driver.findElement(By.xpath("//table[@id='customer']//td[contains(text(),'Birthdate')]/following-sibling::td")).getText());
	  System.out.println("Address: "+driver.findElement(By.xpath("//table[@id='customer']//td[contains(text(),'Address')]/following-sibling::td")).getText());
	  System.out.println("City: "+driver.findElement(By.xpath("//table[@id='customer']//td[contains(text(),'City')]/following-sibling::td")).getText());
	  System.out.println("State: "+driver.findElement(By.xpath("//table[@id='customer']//td[contains(text(),'State')]/following-sibling::td")).getText());
	  System.out.println("Pin: "+driver.findElement(By.xpath("//table[@id='customer']//td[contains(text(),'Pin')]/following-sibling::td")).getText());
	  System.out.println("Mobile No.: "+driver.findElement(By.xpath("//table[@id='customer']//td[contains(text(),'Mobile No.')]/following-sibling::td")).getText());
	  System.out.println("Email: "+driver.findElement(By.xpath("//table[@id='customer']//td[contains(text(),'Email')]/following-sibling::td")).getText());
	  */
	  driver.findElement(By.linkText("Edit Customer")).click();
	  driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys("60065");
	  driver.findElement(By.xpath("//input[@name='AccSubmit']")).click();
	  
	  Assert.assertEquals(driver.findElement(By.xpath("//input[@name='name']")).getAttribute("value"), "tony fer");
	  Assert.assertEquals(driver.findElement(By.xpath("//textarea[@name='addr']")).getText(), "123 address");
	  
	  //edit enable field
	  driver.findElement(By.xpath("//textarea[@name='addr']")).clear();
	  driver.findElement(By.xpath("//textarea[@name='addr']")).sendKeys("123 Le Duan");
	  driver.findElement(By.xpath("//input[@name='city']")).clear();
	  driver.findElement(By.xpath("//input[@name='city']")).sendKeys("new Ho Chi Minh");
	  driver.findElement(By.xpath("//input[@name='state']")).clear();
	  driver.findElement(By.xpath("//input[@name='state']")).sendKeys("Binh Thanh");
	  driver.findElement(By.xpath("//input[@name='pinno']")).clear();
	  driver.findElement(By.xpath("//input[@name='pinno']")).sendKeys("700000");
	  driver.findElement(By.xpath("//input[@name='telephoneno']")).clear();
	  driver.findElement(By.xpath("//input[@name='telephoneno']")).sendKeys("0909877787");
	  driver.findElement(By.xpath("//input[@name='emailid']")).clear();
	  driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys("tonyfer2@yopmail.com");
	  driver.findElement(By.xpath("//input[@name='sub']")).click();
	  
	  //verify edit values
	  Assert.assertEquals(driver.findElement(By.xpath("//table[@id='customer']//td[contains(text(),'Customer ID')]/following-sibling::td")).getText(), "60065");
	  Assert.assertEquals(driver.findElement(By.xpath("//table[@id='customer']//td[contains(text(),'Customer Name')]/following-sibling::td")).getText(), "tony fer");
	  Assert.assertEquals(driver.findElement(By.xpath("//table[@id='customer']//td[contains(text(),'Gender')]/following-sibling::td")).getText(), "male");
	  Assert.assertEquals(driver.findElement(By.xpath("//table[@id='customer']//td[contains(text(),'Birthdate')]/following-sibling::td")).getText(), "1990-04-04");
	  Assert.assertEquals(driver.findElement(By.xpath("//table[@id='customer']//td[contains(text(),'Address')]/following-sibling::td")).getText(), "123 Le Duan");
	  Assert.assertEquals(driver.findElement(By.xpath("//table[@id='customer']//td[contains(text(),'City')]/following-sibling::td")).getText(), "new Ho Chi Minh");
	  Assert.assertEquals(driver.findElement(By.xpath("//table[@id='customer']//td[contains(text(),'State')]/following-sibling::td")).getText(), "Binh Thanh");
	  Assert.assertEquals(driver.findElement(By.xpath("//table[@id='customer']//td[contains(text(),'Pin')]/following-sibling::td")).getText(), "700000");
	  Assert.assertEquals(driver.findElement(By.xpath("//table[@id='customer']//td[contains(text(),'Mobile No.')]/following-sibling::td")).getText(), "0909877787");
	  Assert.assertEquals(driver.findElement(By.xpath("//table[@id='customer']//td[contains(text(),'Email')]/following-sibling::td")).getText(), "tonyfer2@yopmail.com");

	  
  } 
  @Test(description = "check process dropdownlist",enabled = false)
  public void TC_02()
  {
	  driver.get("https://automationfc.github.io/basic-form/index.html");
	  Select dropdown = new Select(driver.findElement(By.id("job1")));
	  Assert.assertFalse(dropdown.isMultiple());
	  
	  dropdown.selectByVisibleText("Mobile Testing");
	  String selectedOption = dropdown.getFirstSelectedOption().getText();
	  Assert.assertEquals(selectedOption, "Mobile Testing");
	  
	  dropdown.selectByValue("manual");
	  String selectValue = dropdown.getFirstSelectedOption().getText();
	  Assert.assertEquals(selectValue, "Manual Testing");
	  
	  dropdown.selectByIndex(9);
	  String selectIndex = dropdown.getFirstSelectedOption().getText();
	  Assert.assertEquals(selectIndex, "Functional UI Testing");
	  
	  //check how many options
	  int i = dropdown.getOptions().size();
	  System.out.println("number options are: "+i);
	  Assert.assertEquals(i, 10);
	  
	  //check multiselect for job role 2
	  Select dropdownMulti = new Select(driver.findElement(By.id("job2")));
	  Assert.assertEquals(dropdownMulti.isMultiple(), true, "job role 2 is mutil select");
	  
	  //select many option in a time
	  dropdownMulti.selectByVisibleText("Automation");
	  dropdownMulti.selectByValue("mobile");
	  dropdownMulti.selectByIndex(4);
	  
	  //check 3 selected options are selected successfully
	  Assert.assertEquals(dropdownMulti.getAllSelectedOptions().get(0).getText(),"Automation");
	  Assert.assertEquals(dropdownMulti.getAllSelectedOptions().get(1).getText(),"Mobile");
	  Assert.assertEquals(dropdownMulti.getAllSelectedOptions().get(2).getText(),"Desktop");
	  
	  //deselect 3 options
	  dropdownMulti.deselectAll();
	  
	  //check no option is selected
	  Assert.assertEquals(dropdownMulti.getAllSelectedOptions().size(),0);  
  }
  @Test(description = "process html dropdown list",enabled = true)
  public void TC03()
  {
	  driver.get("https://demo.nopcommerce.com/");
	  driver.findElement(By.linkText("Register")).click();
	  
	  //input valid information to register
	  driver.findElement(By.cssSelector("input#gender-male")).click();
	  driver.findElement(By.cssSelector("#FirstName")).sendKeys("Tony");
	  driver.findElement(By.cssSelector("#LastName")).sendKeys("Ferguson");
	  
	  Select dateOfBirth = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']")));
	  dateOfBirth.selectByVisibleText("1");
	  //check dateOfBirth has 32 items
	  int dateNumberOptions = dateOfBirth.getOptions().size();
	  Assert.assertEquals(dateNumberOptions, 32);
	  
	  Select monthOfBirth = new Select(driver.findElement(By.cssSelector("[name='DateOfBirthMonth']")));
	  monthOfBirth.selectByValue("5");
	  //check month has 13 item
	  int monthNumberOptions = monthOfBirth.getOptions().size();
	  Assert.assertEquals(monthNumberOptions, 13);
	  
	  Select yearOfBirth = new Select(driver.findElement(By.cssSelector("[name='DateOfBirthYear']")));
	  yearOfBirth.selectByVisibleText("1980");
	  //check month has 13 item
	  int yearNumberOptions = yearOfBirth.getOptions().size();
	  Assert.assertEquals(yearNumberOptions, 112);
	  
	  DateFormat datetime = new SimpleDateFormat("yyyymmddhhmmss");
	  Calendar calendar = Calendar.getInstance();
	  String date = datetime.format(calendar.getTime());
	  driver.findElement(By.cssSelector("#Email")).sendKeys("tonyfer"+date+"@abc.com");
	  
	  driver.findElement(By.cssSelector("#Password")).sendKeys("123456");
	  driver.findElement(By.cssSelector("#ConfirmPassword")).sendKeys("123456");
	  driver.findElement(By.cssSelector("#register-button")).click();
	  
	  //verify register successfully
	  Assert.assertEquals(driver.findElement(By.linkText("My account")).getText(), "My account");
	  Assert.assertEquals(driver.findElement(By.linkText("Log out")).getText(), "Log out");
	  Assert.assertEquals(driver.findElement(By.xpath("//div[contains(text(),'Your registration completed')]")).getText(), "Your registration completed");
	  
  }

  @AfterClass
  public void afterClass() {
  }

}

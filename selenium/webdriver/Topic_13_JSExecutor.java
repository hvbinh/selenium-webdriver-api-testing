package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_13_JSExecutor {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	
	By customerNameTextBox = By.name("name");
	By dobTextBox = By.name("dob");
	By addrTextArea = By.name("addr");
	By cityTextBox = By.name("city");
	By stateTextBox = By.name("state");
	By pinTextBox = By.name("pinno");
	By phoneTextBox = By.name("telephoneno");
	By emailTextBox = By.name("emailid");
	By passTestBox = By.name("password");
	
	String loginPageURL, userID, password, customerName, dateOfBirthInput, dateOfBirthOutput;
	String pin, phone, email, date, month, year, address, city, state, customerID;

	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.chrome.driver", ".\\browserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		customerName = "tony fer";
		date = "05";
		month = "05";
		year = "1990";
		dateOfBirthInput = month + "/" + date + "/" + year;
		dateOfBirthOutput = year + "-" + month + "-" + date;
		address = "123 Le Loi\nHai Chau\nDa Nang";
		city = "Da Nang";
		state = "Hai Chau";
		pin = "123456";
		phone = "0123456789";
		email = "tonyfer" + getRandomNumber() + "@yopmail.com";


	}

	//@Test
	public void TC_01_Javascript_Executor() {
		navigateToUrlByJS("http://live.demoguru99.com/");
		
		String guruDomain = (String) jsExecutor.executeScript("return document.domain");
		Assert.assertEquals(guruDomain, "live.demoguru99.com");
		
		//get URL
		String url = (String)jsExecutor.executeScript("return document.URL");
		Assert.assertEquals(url, "http://live.demoguru99.com/");
		sleepInSecond(3);
		
		//click on mobile link
		jsExecutor.executeScript("arguments[0].click();",driver.findElement(By.xpath("//a[text()='Mobile']")));
		
		//add to cart samsum
		WebElement samsung = driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following::div[@class='actions']//span[text()='Add to Cart']"));
		jsExecutor.executeScript("arguments[0].click();", samsung);
		
		String liveGureInnerValue = (String) jsExecutor.executeScript("return document.documentElement.innerText;");
		Assert.assertTrue(liveGureInnerValue.contains("Samsung Galaxy was added to your shopping cart."));

		//click on customer service page, verify title page
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[text()='Customer Service']")));
		String customerServiceTitle = (String) jsExecutor.executeScript("return document.title;");
		Assert.assertEquals(customerServiceTitle, "Customer Service");
		
		//scroll to NEW LETTER text box, verify co text hien thi
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.id("newsletter")));
		String customerServiceInnerValue = (String)jsExecutor.executeScript("return document.documentElement.innerText;");
		Assert.assertTrue(customerServiceInnerValue.contains(" Praesent ipsum libero, auctor ac, tempus nec, tempor nec, justo."));
		
		//navigate to http://demo.guru99.com/v4/, assert domain
		navigateToUrlByJS("http://demo.guru99.com/v4/");
		String demoGuruDomain = (String) jsExecutor.executeScript("return document.domain;");
		Assert.assertEquals(demoGuruDomain, "demo.guru99.com");
		
	}
	//@Test
	public void TC_02_Remove_Attribute()
	{
		navigateToUrlByJS("http://demo.guru99.com/v4/");
		
		//log in mngr289005/UjYraza
		driver.findElement(By.name("uid")).sendKeys("mngr289005");
		driver.findElement(By.name("password")).sendKeys("UjYraza");
		driver.findElement(By.name("btnLogin")).click();
		
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[text()='New Customer']")));
		
		removeAttributeByJS("//input[@id='dob']","type");
		sleepInSecond(3);
		
		
		driver.findElement(customerNameTextBox).sendKeys(customerName);
		driver.findElement(dobTextBox).sendKeys(dateOfBirthInput);
		driver.findElement(addrTextArea).sendKeys(address);
		driver.findElement(cityTextBox).sendKeys(city);
		driver.findElement(stateTextBox).sendKeys(state);
		driver.findElement(pinTextBox).sendKeys(pin);
		driver.findElement(phoneTextBox).sendKeys(phone);
		driver.findElement(emailTextBox).sendKeys(email);
		driver.findElement(passTestBox).sendKeys("123456");
		driver.findElement(By.name("sub")).click();
		
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//p[@class='heading3']")).getText(), "Customer Registered Successfully!!!");
	}
	@Test
	public void TC_03_Create_An_Account()
	{
		navigateToUrlByJS("http://live.demoguru99.com/");
		
		WebElement myAccountLink = driver.findElement(By.xpath("//div[@id='header-account']//a[text()='My Account']"));
		jsExecutor.executeScript("arguments[0].click();", myAccountLink);
		
		WebElement createAccountButton = driver.findElement(By.xpath("//a[@title='Create an Account']"));
		jsExecutor.executeScript("arguments[0].click();", createAccountButton);
	
		String firstname = "tony1";
		String middlename = "jr.";
		String lastname = "Fer";
		String emailAddress = "tonyfer" + getRandomNumber() + "@yopmail.com";
		String password = "123456";
		String confirmation = "123456";
		
		//input create account form
		jsExecutor.executeScript("document.getElementById('firstname').value='"+firstname+"'");
		jsExecutor.executeScript("document.getElementById('middlename').value='"+middlename+"'");
		jsExecutor.executeScript("document.getElementById('lastname').value='"+lastname+"'");
		jsExecutor.executeScript("document.getElementById('email_address').value='"+emailAddress+"'");
		jsExecutor.executeScript("document.getElementById('password').value='"+password+"'");
		jsExecutor.executeScript("document.getElementById('confirmation').value='"+confirmation+"'");
		
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[text()='Register']")));
		
		//verify message displays 
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "Thank you for registering with Main Website Store.");
		
		//click log out
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[@title='Log Out']")));
		sleepInSecond(6);
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/");
	}

	public void navigateToUrlByJS(String URL) {
		jsExecutor.executeScript("window.location='" + URL + "'");
	}
	public void removeAttributeByJS(String locator, String attributeRemove)
	{
		WebElement element = driver.findElement(By.xpath(locator));
		jsExecutor.executeScript("arguments[0].removeAttribute('"+attributeRemove+"')", element);
	}
	public void sleepInSecond(long time)
	{
		try {
			Thread.sleep(time *1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}

	@AfterClass
	public void afterClass() {
	}

}

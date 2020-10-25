package testNG;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class TC_03_Data_Provider {
	WebDriver driver;
	String sourceFolder = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", sourceFolder + "\\browserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test(dataProvider = "register_login")
	public void TC_01_Login(String email, String password) {
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");

		driver.findElement(By.id("email")).sendKeys(email);
		driver.findElement(By.id("pass")).sendKeys(password);
		driver.findElement(By.id("send2")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains(email));

		driver.findElement(By.xpath("//span[@class='label' and text()='Account']")).click();
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();

	}
	@Test(dataProvider = "register_login")
	public void TC_02_Register(String email, String password) {
		System.out.println(email + "   " + password);
	}

	@DataProvider(name = "email_pass")
	public Object[][] emailAndPasswordData() {
		return new Object[][] { { "selenium_11_01@gmail.com", "111111" }, { "selenium_11_02@gmail.com", "111111" },
				{ "selenium_11_03@gmail.com", "111111" } };
	}

	@DataProvider(name = "register_login")
	public Object[][] emailAndPasswordData(Method method) {
		Object[][] result = null;
		if (method.getName().contains("Login")) {
			result = new Object[][] { 
					{ "selenium_11_01@gmail.com", "111111" },
					{ "selenium_11_02@gmail.com", "111111" },
					{ "selenium_11_03@gmail.com", "111111" } };
		}
		else if(method.getName().contains("Register")) {
			result = new Object[][] { 
				{ "selenium_11_01@gmail.com", "222222" },
				{ "selenium_11_02@gmail.com", "222222" },
				{ "selenium_11_03@gmail.com", "222222" } };
			}
		
			return result;

	}

	@AfterClass
	public void afterClass() {
	}

}

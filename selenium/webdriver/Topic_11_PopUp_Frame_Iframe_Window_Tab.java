package webdriver;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_11_PopUp_Frame_Iframe_Window_Tab {
	WebDriver driver;
	WebDriverWait explicitWait;
	boolean status;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		explicitWait = new WebDriverWait(driver, 30);
	}

	@Test
	public void TC_01_Popup_Fix1() {
		driver.get("https://www.zingpoll.com");

		explicitWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("Loginform"))));
		// click vao Sign in
		driver.findElement(By.id("Loginform")).click();
		// kiem tra pop-up hien thi

		explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("Login"))));
		status = driver.findElement(By.id("Login")).isDisplayed();
		System.out.println("Login pop up: " + status);
		Assert.assertTrue(status);

		// close pop up
		driver.findElement(By.xpath("//div[@id='Login']//button[@class='close']")).click();
		sleepInSecond(2);

		status = driver.findElement(By.id("Login")).isDisplayed();
		System.out.println("Login pop up: " + status);
		Assert.assertFalse(status);

		driver.findElement(By.id("Loginform")).click();
		// dang nhap
		sleepInSecond(3);
		driver.findElement(By.id("loginEmail")).sendKeys("automationfc.vn@gmail.com");
		driver.findElement(By.id("loginPassword")).sendKeys("automationfc");
		sleepInSecond(2);
		driver.findElement(By.id("button-login")).click();

		sleepInSecond(3);
		driver.navigate().refresh();
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='username' and contains(string(),'Automation Testing')]")).isDisplayed());
		
		
		
	}
	@Test
	public void TC_01_Popup_Fix2() {
		driver.get("https://bni.vn/");
		
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='sgpb-popup-dialog-main-div']")).isDisplayed());
		sleepInSecond(3);
		driver.findElement(By.xpath("//img[@class='sgpb-popup-close-button-1']")).click();
		sleepInSecond(3);
		Assert.assertFalse(driver.findElement(By.xpath("//div[@id='sg-popup-content-wrapper-1236']")).isDisplayed());
		
		
	}

	public void sleepInSecond(long time) {
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

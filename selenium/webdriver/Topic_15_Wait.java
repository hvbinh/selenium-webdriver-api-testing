package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.expectThrows;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_15_Wait {
	WebDriver driver;
	String source_folder = System.getProperty("user.dir");
	WebElement dateSelected;
	String today = "Sunday, October 18, 2020";
	JavascriptExecutor jsExecutor;
	
	String gold = "gold.jpg";
	String silver = "silver.jpg";
	String bronce = "bronce.jpg";

	String gold_path = source_folder + "\\uploadFile\\" + gold;
	String silver_path = source_folder + "\\uploadFile\\" + silver;
	String bronce_path = source_folder + "\\uploadFile\\" + bronce;


	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", source_folder + "\\browserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();	
	}

	//@Test
	public void TC_01_Implicit_Wait() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//h4[text()='Hello World!']")).isDisplayed());
	}
	//@Test
	public void TC_02_Static_Wait()
	{
		driver.navigate().to("http://the-internet.herokuapp.com/dynamic_loading/2");
		
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		
		sleepInSecond(5);
		
		Assert.assertTrue(driver.findElement(By.xpath("//h4[text()='Hello World!']")).isDisplayed());
		
		
	}
	//@Test
	public void TC_03_Explicit_Wait()
	{
		WebDriverWait explicitWait = new WebDriverWait(driver, 10);
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		
		WebElement loadingElement = driver.findElement(By.xpath("//div[@id='loading']"));
		explicitWait.until(ExpectedConditions.invisibilityOf(loadingElement));
			
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(), "Hello World!");
			
	}
	//@Test
	public void TC_04_Explicit_Wait()
	{
		WebDriverWait explicitWait = new WebDriverWait(driver, 10);
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[text()='Hello World!']")));	
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(), "Hello World!");
			
	}
	//@Test
	public void TC_05_Explicit_Wait()
	{
		jsExecutor = (JavascriptExecutor) driver;
		WebDriverWait explicitWait = new WebDriverWait(driver, 10);
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='calendarContainer']")));
		
		dateSelected = driver.findElement(By.xpath("//td[@title='"+today+"']"));
		//before select date
		System.out.println(driver.findElement(By.id("ctl00_ContentPlaceholder1_Panel1")).getText());
		
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.id("ctl00_ContentPlaceholder1_Panel1")));
		//select date
		explicitWait.until(ExpectedConditions.elementToBeClickable(dateSelected)).click();
		
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[not(@style='display:none;')]/div[@class='raDiv']")));
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(@class,'rcSelected')]//a[text()='18']")));
		
		Assert.assertEquals(driver.findElement(By.xpath("//td[contains(@class,'rcSelected')]")).getAttribute("title"), "Sunday, October 18, 2020");
		
	}
	@Test
	public void TC_06_Explicit_Wait_Upload()
	{
		System.setProperty("webdriver.chrome.driver", source_folder + "\\browserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://gofile.io/uploadFiles");
		jsExecutor = (JavascriptExecutor) driver;
		WebDriverWait explicitWait = new WebDriverWait(driver, 60);

		explicitWait.until(ExpectedConditions.elementToBeClickable(By.id("dropZoneBtnSelect")));
		WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));

		// add pictures
		//uploadFile.sendKeys(gold_path + "\n" + silver_path + "\n" + bronce_path);
		uploadFile.sendKeys(gold_path + "\n" + silver_path);

		// Verify add file successfully
		Assert.assertTrue(driver.findElement(By.xpath("//tbody/tr/td[text()='" + gold + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//tbody/tr/td[text()='" + silver + "']")).isDisplayed());
		//Assert.assertTrue(driver.findElement(By.xpath("//tbody/tr/td[text()='" + bronce + "']")).isDisplayed());

		// click upload button
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.id("uploadFiles-btnUpload"))).click();

		// click on download link
		String idCurrentWindow = driver.getWindowHandle();
		explicitWait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//td[text()='Download link']/following-sibling::td/a")));
		driver.findElement(By.xpath("//td[text()='Download link']/following-sibling::td/a")).click();

		// switch window
		switchToWindowById(idCurrentWindow);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='I have a VPN already']")));

		// close pop-up
		driver.findElement(By.xpath("//button[text()='I have a VPN already']")).click();

		// check icon download displays
		Assert.assertTrue(driver
				.findElement(
						By.xpath("//td[text()='" + gold + "']/following-sibling::td//a[contains(@class,'download')]"))
				.isDisplayed());
		Assert.assertTrue(driver
				.findElement(
						By.xpath("//td[text()='" + silver + "']/following-sibling::td//a[contains(@class,'download')]"))
				.isDisplayed());
		

		// check icon play displays
		Assert.assertTrue(driver
				.findElement(
						By.xpath("//td[text()='" + gold + "']/following-sibling::td//a[contains(@class,'showInfo')]"))
				.isDisplayed());
		Assert.assertTrue(driver
				.findElement(
						By.xpath("//td[text()='" + silver + "']/following-sibling::td//a[contains(@class,'showInfo')]"))
				.isDisplayed());

	}
	public void switchToWindowById(String idCurrentWindow) {
		Set<String> windows = driver.getWindowHandles();

		for (String window : windows) {
			if (!idCurrentWindow.equals(window)) {
				driver.switchTo().window(window);
			}
		}

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
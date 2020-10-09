package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;

public class Topic_14_Upload_File {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	String source_folder = System.getProperty("user.dir");

	String gold = "gold.jpg";
	String silver = "silver.jpg";
	String bronce = "bronce.jpg";

	String gold_path = source_folder + "\\uploadFile\\" + gold;
	String silver_path = source_folder + "\\uploadFile\\" + silver;
	String bronce_path = source_folder + "\\uploadFile\\" + bronce;

	// @Test
	public void TC_01_Upload_By_Sendkey_Chrome() throws Exception {
		System.setProperty("webdriver.chrome.driver", source_folder + "\\browserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://blueimp.github.io/jQuery-File-Upload/");
		jsExecutor = (JavascriptExecutor) driver;

		WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
		uploadFile.sendKeys(gold_path + "\n" + silver_path + "\n" + bronce_path);

		// Verify hinh da duoc added
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + gold + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + silver + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + bronce + "']")).isDisplayed());

		// click on start in every row
		List<WebElement> startButtons = driver
				.findElements(By.xpath("//td//button[contains(@class,'btn-primary start')]"));
		for (WebElement start : startButtons) {
			start.click();
			sleepInSecond(1);
		}

		// Verify upload file successfully
		Assert.assertTrue(isImageDisplayed("//img[contains(@src,'" + gold + "')]"));
		Assert.assertTrue(isImageDisplayed("//img[contains(@src,'" + silver + "')]"));
		Assert.assertTrue(isImageDisplayed("//img[contains(@src,'" + bronce + "')]"));

		// Verify image name displays
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + gold + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + silver + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + bronce + "']")).isDisplayed());

	}

	// @Test
	public void TC_02_Upload_By_Sendkey_Firefox() throws Exception {
		System.setProperty("webdriver.firefox.driver", source_folder + "\\browserDriver\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("http://blueimp.github.io/jQuery-File-Upload/");
		jsExecutor = (JavascriptExecutor) driver;

		WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
		uploadFile.sendKeys(gold_path + "\n" + silver_path + "\n" + bronce_path);
		/*
		 * uploadFile.sendKeys(gold_path);
		 * 
		 * uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
		 * uploadFile.sendKeys(silver_path);
		 * 
		 * uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
		 * uploadFile.sendKeys(bronce_path);
		 */
		// Verify hinh da duoc added
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + gold + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + silver + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + bronce + "']")).isDisplayed());

		// click on start in every row
		List<WebElement> startButtons = driver
				.findElements(By.xpath("//td//button[contains(@class,'btn-primary start')]"));
		for (WebElement start : startButtons) {
			start.click();
			sleepInSecond(1);
		}

		// Verify upload file successfully
		Assert.assertTrue(isImageDisplayed("//img[contains(@src,'" + gold + "')]"));
		Assert.assertTrue(isImageDisplayed("//img[contains(@src,'" + silver + "')]"));
		Assert.assertTrue(isImageDisplayed("//img[contains(@src,'" + bronce + "')]"));

		// Verify image name displays
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + gold + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + silver + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + bronce + "']")).isDisplayed());
	}

	@Test
	public void TC_03_Upload_By_Sendkey_Chrome() {
		System.setProperty("webdriver.chrome.driver", source_folder + "\\browserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://gofile.io/uploadFiles");
		jsExecutor = (JavascriptExecutor) driver;
		WebDriverWait explicitWait = new WebDriverWait(driver, 20);

		sleepInSecond(3);
		WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));

		// add pictures
		uploadFile.sendKeys(gold_path + "\n" + silver_path + "\n" + bronce_path);
		sleepInSecond(3);
		
		//Verify add file successfully
		Assert.assertTrue(driver.findElement(By.xpath("//tbody/tr/td[text()='"+gold+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//tbody/tr/td[text()='"+silver+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//tbody/tr/td[text()='"+bronce+"']")).isDisplayed());
		
		// click upload button
		driver.findElement(By.id("uploadFiles-btnUpload")).click();
		sleepInSecond(6);

		// click on download link
		String idCurrentWindow = driver.getWindowHandle();
		explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//td[text()='Download link']/following-sibling::td/a"))));
		driver.findElement(By.xpath("//td[text()='Download link']/following-sibling::td/a")).click();

		//switch window
		switchToWindowById(idCurrentWindow);
		sleepInSecond(6);
		
		//close pop-up
		driver.findElement(By.xpath("//button[text()='I have a VPN already']")).click();
		
		//check icon download displays
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='"+gold+"']/following-sibling::td//a[contains(@class,'download')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='"+silver+"']/following-sibling::td//a[contains(@class,'download')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='"+bronce+"']/following-sibling::td//a[contains(@class,'download')]")).isDisplayed());
		
		//check icon play displays
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='"+gold+"']/following-sibling::td//a[contains(@class,'showInfo')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='"+silver+"']/following-sibling::td//a[contains(@class,'showInfo')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='"+bronce+"']/following-sibling::td//a[contains(@class,'showInfo')]")).isDisplayed());
	}

	public void switchToWindowById(String idCurrentWindow) {
		Set<String> windows = driver.getWindowHandles();
		
		for(String window : windows)
		{
			if(!idCurrentWindow.equals(window))
			{
				driver.switchTo().window(window);
			}
		}

	}

	public boolean isImageDisplayed(String xpathLocator) throws Exception {
		WebElement image = driver.findElement(By.xpath(xpathLocator));

		Boolean ImagePresent = (Boolean) ((JavascriptExecutor) driver).executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
				image);
		return ImagePresent;
	}

	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@AfterMethod
	public void afterMethod() {
		// driver.quit();
	}

}
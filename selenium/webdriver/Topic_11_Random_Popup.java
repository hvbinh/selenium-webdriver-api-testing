package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_11_Random_Popup {
	WebDriver driver;
	WebDriverWait explicitWait;

	@Test(priority = 1)
	public void TC_01_Popup_Random_Display() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://blog.testproject.io/");

		explicitWait = new WebDriverWait(driver, 30);

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		sleepInSecond(10);

		if (driver.findElement(By.cssSelector(".mailch-wrap.rocket-lazyload")).isDisplayed()) {
			// kiem tra SIGN UP NOW button hien thi
			Assert.assertTrue(driver.findElement(By.xpath("//a[contains(.,'Sign Up Now')]")).isDisplayed());

			System.out.println("in the if function");
			driver.findElement(By.xpath("//div[@id='close-mailch']")).click();
			sleepInSecond(2);

		}
		// search selenium
		driver.findElement(By.xpath("//section[@id='search-2']//input[@type='search']")).sendKeys("Selenium");
		driver.findElement(By.xpath("//section[@id='search-2']//span[@class='glass']")).click();
		sleepInSecond(2);
		List<WebElement> allArticleTitles = driver.findElements(By.xpath("//h3[@class='post-title']/a"));
		for (WebElement article : allArticleTitles) {
			String articleText = article.getText().trim();
			Assert.assertTrue(articleText.contains("Selenium"));
		}
		sleepInSecond(3);
		driver.quit();

	}

	@Test(priority = 2)
	public void TC_02_Popup_Random_Not_Display() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://blog.testproject.io/");

		explicitWait = new WebDriverWait(driver, 30);

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		sleepInSecond(10);

		// search selenium
		driver.findElement(By.xpath("//section[@id='search-2']//input[@type='search']")).sendKeys("Selenium");
		driver.findElement(By.xpath("//section[@id='search-2']//span[@class='glass']")).click();
		sleepInSecond(2);
		List<WebElement> allArticleTitles = driver.findElements(By.xpath("//h3[@class='post-title']/a"));
		for (WebElement article : allArticleTitles) {
			String articleText = article.getText().trim();
			Assert.assertTrue(articleText.contains("Selenium"));
		}
		sleepInSecond(3);
		driver.quit();

	}

	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

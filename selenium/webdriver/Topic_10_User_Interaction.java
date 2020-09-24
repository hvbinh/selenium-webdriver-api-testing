package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.expectThrows;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_10_User_Interaction {
	WebDriver driver;
	WebDriverWait explicitWait;
	Actions action;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 20);
		action = new Actions(driver);
	}

	// @Test
	public void TC_01_Hover_To_Element1() {
		driver.get("https://jqueryui.com/resources/demos/tooltip/default.html");

		explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("age"))));
		action.moveToElement(driver.findElement(By.id("age"))).perform();
		String hoverText = driver.findElement(By.xpath("//div[@class='ui-tooltip-content']")).getText();

		Assert.assertEquals(hoverText, "We ask for your age only for statistical purposes.");

	}

	// @Test
	public void TC_02_Hover_To_Element2() {
		driver.get("https://www.myntra.com");

		WebElement kid = driver.findElement(By.xpath("//div[@class='desktop-navLink']//a[text()='Kids']"));
		explicitWait.until(ExpectedConditions.visibilityOf(kid));
		action.moveToElement(kid).perform();

		explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[text()='Home & Bath']"))));
		driver.findElement(By.xpath("//a[text()='Home & Bath']")).click();
		WebElement kidHome = driver
				.findElement(By.xpath("//span[@class='breadcrumbs-crumb' and text()='Kids Home Bath']"));
		explicitWait.until(ExpectedConditions.visibilityOf(kidHome));
		Assert.assertTrue(kidHome.isDisplayed());

	}

	@Test
	public void TC_03_Click_And_Hold() {
		driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");

		List<WebElement> items = driver.findElements(By.xpath("//ol/li"));
		action.clickAndHold(items.get(0)).moveToElement(items.get(3)).perform();
		action.release().perform();

		items = driver.findElements(By.xpath("//li[contains(@class,'ui-selected')]"));
		Assert.assertEquals(items.size(), 4);
	}

	@Test
	public void TC_03_Click_Random() {
		driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");

		List<WebElement> items = driver.findElements(By.xpath("//ol/li"));
		action.keyDown(Keys.CONTROL).click(items.get(1)).click(items.get(4)).click(items.get(7)).click(items.get(10))
				.perform();

		items = driver.findElements(By.xpath("//li[contains(@class,'ui-selected')]"));
		Assert.assertEquals(items.size(), 4);

	}
	@Test
	public void TC_04_Double_Click()
	{
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
		
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='demo']")).getText(), "Hello Automation Guys!");
	}

	@AfterClass
	public void afterClass() {
	}

}

package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.expectThrows;

import org.openqa.selenium.By;
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

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 20);
	}

	//@Test
	public void TC_01_Hover_To_Element1() {
		driver.get("https://jqueryui.com/resources/demos/tooltip/default.html");
		Actions action = new Actions(driver);
		explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("age"))));
		action.moveToElement(driver.findElement(By.id("age"))).perform();
		String hoverText = driver.findElement(By.xpath("//div[@class='ui-tooltip-content']")).getText();

		Assert.assertEquals(hoverText, "We ask for your age only for statistical purposes.");

	}
	@Test
	public void TC_02_Hover_To_Element2()
	{
		driver.get("https://www.myntra.com");
		Actions action = new Actions(driver);
		WebElement kid = driver.findElement(By.xpath("//div[@class='desktop-navLink']//a[text()='Kids']"));
		explicitWait.until(ExpectedConditions.visibilityOf(kid));
		action.moveToElement(kid).perform();
		
		driver.findElement(By.xpath("//a[text()='Home & Bath']")).click();
		WebElement kidHome = driver.findElement(By.xpath("//span[@class='breadcrumbs-crumb' and text()='Kids Home Bath']"));
		explicitWait.until(ExpectedConditions.visibilityOf(kidHome));
		Assert.assertTrue(kidHome.isDisplayed());
		
	}

	@AfterClass
	public void afterClass() {
	}

}

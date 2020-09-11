package webdriver;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeClass;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_07_08_Custome_dropdownlist {
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		explicitWait = new WebDriverWait(driver, 30);
		jsExecutor = (JavascriptExecutor) driver;
	}

	// @Test
	public void TC_01_JQuery() {
		selectTheItemInCustomeDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "5");
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']//span[text()='5']")).isDisplayed());

		selectTheItemInCustomeDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "10");
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']//span[text()='10']")).isDisplayed());

		selectTheItemInCustomeDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "19");
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']//span[text()='19']")).isDisplayed());

		selectTheItemInCustomeDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "1");
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']//span[text()='1']")).isDisplayed());
	}

	// @Test
	public void TC_02_ReactJS() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

		selectTheItemInCustomeDropdown("//div[@class='ui fluid selection dropdown']",
				"//div[@class='ui active visible fluid selection dropdown']//span", "Matt");
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Matt']")).isDisplayed());

		selectTheItemInCustomeDropdown("//div[@class='ui fluid selection dropdown']",
				"//div[@class='ui active visible fluid selection dropdown']//span", "Justen Kitsune");
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Justen Kitsune']")).isDisplayed());
	}
	//@Test
	public void TC_03_VuJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");

		selectTheItemInCustomeDropdown("//li[@class='dropdown-toggle']",
				"//ul[@class='dropdown-menu']//a", "Second Option");
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//li[contains(.,'Second Option')]")).isDisplayed());
		
		selectTheItemInCustomeDropdown("//li[@class='dropdown-toggle']",
				"//ul[@class='dropdown-menu']//a", "First Option");
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//li[contains(.,'First Option')]")).isDisplayed());
		
		selectTheItemInCustomeDropdown("//li[@class='dropdown-toggle']",
				"//ul[@class='dropdown-menu']//a", "Third Option");
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//li[contains(.,'Third Option')]")).isDisplayed());
	}
	@Test
	public void TC_Example()
	{
		driver.get("https://demos.telerik.com/kendo-ui/dropdownlist/index");

		selectTheItemInCustomeDropdown1("//span[@class='k-dropdown-wrap k-state-default']",
				"//span[@class='k-state-default']/h3", "Produce");
		sleepInSecond(3);
	}

	public void selectTheItemInCustomeDropdown(String parentXpath, String childXpath, String expectedText) {

		driver.findElement(By.xpath(parentXpath)).click();
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));

		List<WebElement> childItems = driver.findElements(By.xpath(childXpath));

		for (WebElement actualItem : childItems) {
			System.out.println("actual: "+actualItem.getText());
			if (actualItem.getText().trim().equals(expectedText)) {
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", actualItem);
				sleepInSecond(1);
				actualItem.click();
				break;
			}
		}

	}
	public void selectTheItemInCustomeDropdown1(String parentXpath, String childXpath, String expectedText) {

		driver.findElement(By.xpath(parentXpath)).click();
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));

		List<WebElement> childItems = driver.findElements(By.xpath(childXpath));

		for (WebElement actualItem : childItems) {
			System.out.println("actual: "+actualItem.getText());
			if (actualItem.getText().trim().equals(expectedText)) {
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", actualItem);
				jsExecutor.executeScript("arguments[0].click();", actualItem);
				sleepInSecond(1);
				System.out.println("actual: "+actualItem.getText());
				actualItem.click();
				break;
			}
		}

	}

	public void sleepInSecond(long second) {

		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@AfterClass
	public void afterClass() {
	}

}

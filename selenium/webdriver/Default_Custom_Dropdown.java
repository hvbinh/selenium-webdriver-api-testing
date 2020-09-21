package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Default_Custom_Dropdown {
	WebDriver driver;
	WebDriverWait explicitWait;
	Select dropdown;
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 20);
		jsExecutor = (JavascriptExecutor) driver;
	}

	//@Test
	public void default_Dropdown() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		dropdown = new Select(driver.findElement(By.id("job1")));
		dropdown.selectByVisibleText("Unit Testing");
		sleepInSecond(3);

		Assert.assertEquals(dropdown.getFirstSelectedOption().getText(), "Unit Testing");

	}

	//@Test
	public void custom_Dropdown() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		String parentXpath, childXpath;
		parentXpath = "//span[@id='speed-button']//span[contains(@class,'ui-icon-triangle-1-s')]";
		childXpath = "//ul[@id='speed-menu']//li[@class='ui-menu-item']/div";
		selectItemInCustomDropdown(parentXpath, childXpath, "Fast");
		sleepInSecond(3);

		Assert.assertEquals(
				driver.findElement(By.xpath("//span[@id='speed-button']//span[@class='ui-selectmenu-text']")).getText(),
				"Fast");

	}
	@Test
	public void cutom_Dropdown_Angular()
	{
		driver.get("https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");
		
		selectItemInCustomDropdown("//ejs-dropdownlist[@id='games']//span[contains(@class,'e-search-icon')]","//ul[@id='games_options']/li","Cricket");
		Assert.assertEquals(getHiddenText("select[id='games_hidden']>option"), "Cricket");
		
	}

	public void selectItemInCustomDropdown(String parentXpath, String childXpath, String expectedItem) {
		driver.findElement(By.xpath(parentXpath)).click();
		List<WebElement> items = driver.findElements(By.xpath(childXpath));

		for (WebElement item : items) {
			if (item.getText().trim().equals(expectedItem)) {
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				item.click();
				sleepInSecond(2);
				break;
			}
		}
	}

	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public String getHiddenText(String cssLocator)
	{
		return (String) jsExecutor.executeScript("return document.querySelector(\""+cssLocator+"\").textContent");
	}


	@AfterClass
	public void afterClass() {
	}

}

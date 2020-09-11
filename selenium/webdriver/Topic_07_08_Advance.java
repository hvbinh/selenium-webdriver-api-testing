package webdriver;

import org.testng.annotations.Test;


import org.testng.annotations.BeforeClass;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_07_08_Advance {
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.get("http://multiple-select.wenzhixin.net.cn/examples#basic.html");
		explicitWait = new WebDriverWait(driver, 30);
		jsExecutor = (JavascriptExecutor) driver;
	}

	// @Test
	public void TC_01() {

		selectTheItemInCustomeDropdown("//div[@id='default-place']", "//div[@id='default-place']/ul/li", "Peugeot");
		sleepInSeconds(3);

	}

	// @Test
	public void TC_02_Input_To_Dropdow() {
		driver.findElement(By.xpath("//div[@id='default-place']")).click();
		sleepInSeconds(3);
		driver.findElement(By.xpath("//div[@id='default-place']/input")).sendKeys("Peugeot");
		driver.findElement(By.xpath("//div[@id='default-place']/input")).sendKeys(Keys.ENTER);
		sleepInSeconds(3);
	}

	@Test
	public void TC_03_Multi_Select() {
		String months[]= {"May","June","July"};
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		multipleSelect("(//button[@class='ms-choice'])[1]","//li/label/span",months);

		if(months.length<=3)
		{
			Assert.assertEquals(driver.findElement(By.xpath("(//button[@class='ms-choice'])[1]/span")).getText().trim(),"May, June, July");
		}
		else if(months.length>3)
		{
			Assert.assertEquals(driver.findElement(By.xpath("(//button[@class='ms-choice'])[1]/span")).getText().trim(),months.length+" of 12 selected");
		}
		
	}

	public void selectTheItemInCustomeDropdown(String parentXpath, String childXpath, String expectedText1) {
		driver.findElement(By.xpath(parentXpath)).click();
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));

		List<WebElement> childItems = driver.findElements(By.xpath(childXpath));
		for (WebElement actualItem : childItems) {
			if (actualItem.getText().trim().equals(expectedText1)) {
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", actualItem);
				actualItem.click();
				sleepInSeconds(1);
				break;
			}
			
		}

	}

	public void multipleSelect(String parentXpath, String childXpath, String expectedText[]) {
		
		driver.switchTo().frame(0);
		driver.findElement(By.xpath(parentXpath)).click();

		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));

		List<WebElement> childItems = driver.findElements(By.xpath(childXpath));
		for(int i = 0;i<expectedText.length;i++)
		{
			selectItem(childItems, expectedText[i]);
		
		}
	}
	public void selectItem(List<WebElement>list,String expectedText)
	{
		for(WebElement actualItem : list)
		{
			if(actualItem.getText().trim().equals(expectedText))
			{
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", actualItem);
				actualItem.click();
				sleepInSeconds(1);
			}
		}
	}

	public void sleepInSeconds(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public void afterClass() {
	}

}

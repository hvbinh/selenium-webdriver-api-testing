package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.expectThrows;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
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
	String jsPathFile;
	String jqueryPathFile;
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 20);
		action = new Actions(driver);
		jsExecutor = (JavascriptExecutor) driver;
		String projectFolder = System.getProperty("user.dir");
		String jsPathFile = projectFolder + "\\drapAndDrop\\drap_And_Drop_Helper.js";
		String jqueryPathFile = projectFolder + "\\drapAndDrop\\jquery_load_helper.js";
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

	// @Test
	public void TC_03_Click_And_Hold() {
		driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");

		List<WebElement> items = driver.findElements(By.xpath("//ol/li"));
		action.clickAndHold(items.get(0)).moveToElement(items.get(3)).perform();
		action.release().perform();

		items = driver.findElements(By.xpath("//li[contains(@class,'ui-selected')]"));
		Assert.assertEquals(items.size(), 4);
	}

	// @Test
	public void TC_03_Click_Random() {
		driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");

		List<WebElement> items = driver.findElements(By.xpath("//ol/li"));
		action.keyDown(Keys.CONTROL).click(items.get(1)).click(items.get(4)).click(items.get(7)).click(items.get(10))
				.perform();

		items = driver.findElements(By.xpath("//li[contains(@class,'ui-selected')]"));
		Assert.assertEquals(items.size(), 4);

	}

	// @Test
	public void TC_04_Double_Click() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();

		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='demo']")).getText(), "Hello Automation Guys!");
	}

	// @Test
	public void TC_05_Right_Click() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");

		action.contextClick(driver.findElement(By.cssSelector(".btn-neutral"))).perform();
		// kiem tra quit menu hien thi
		Assert.assertTrue(driver.findElement(By.cssSelector(".context-menu-icon-quit")).isDisplayed());
		// hover on quit option
		action.moveToElement(driver.findElement(By.cssSelector(".context-menu-icon-quit"))).perform();
		// kiem tra quit co su kien visible + hover
		Assert.assertTrue(
				driver.findElement(By.cssSelector(".context-menu-icon-quit.context-menu-visible.context-menu-visible"))
						.isDisplayed());
		// click vao quit
		driver.findElement(By.cssSelector(".context-menu-icon-quit")).click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
		// kiem tra quit menu khong hien thi
		Assert.assertFalse(driver.findElement(By.cssSelector(".context-menu-icon-quit")).isDisplayed());

	}

	// @Test
	public void TC_06_Drap_And_Drop() {
		driver.get("https://demos.telerik.com/kendo-ui/dragdrop/index");

		explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("div#draggable"))));

		WebElement sourceCircle = driver.findElement(By.cssSelector("div#draggable"));
		WebElement targetCircle = driver.findElement(By.cssSelector("div#droptarget"));

		// scroll to sourceCircle
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(By.cssSelector("#example")));
		sleepInSecond(2);

		action.dragAndDrop(sourceCircle, targetCircle).perform();
		// verify thay doi
		Assert.assertEquals(targetCircle.getText(), "You did great!");
		// verify backgourd color, convert hex to grba
		Assert.assertEquals(targetCircle.getCssValue("background-color"), "rgba(3, 169, 244, 1)");
	}

	@Test
	public void TC_07_Drap_And_Drop_HTML5_CSS() throws IOException {
		driver.get("https://automationfc.github.io/drag-drop-html5");

		//explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("#column-a"))));

		String sourceCss = "#column-a";
		String targetCss = "#column-b";

		//doc file dua vao bien
		System.out.println("path: "+jsPathFile);
		String jsContent = readFile("D:\\Automation\\Automation FC\\02. Selenium API\\drapAndDrop\\drap_And_Drop_Helper.js"); 
		//String jqueryContent = readFile(jqueryPathFile);
		
		//nhung jquery dua vao tring duyet
		//jsExecutor.executeScript(jqueryContent);

		// A to B
		jsContent = jsContent + "$(\"" + sourceCss + "\").simulateDragDrop({ dropTarget: \"" + targetCss + "\"});";
		
		jsExecutor.executeScript(jsContent);
		sleepInSecond(5);
		Assert.assertTrue(isElementDisplayed("//div[@id='column-a']/header[text()='B']"));
		
		// B to A
		jsExecutor.executeScript(jsContent);
		sleepInSecond(5);
		Assert.assertTrue(isElementDisplayed("//div[@id='column-a']/header[text()='A']"));

	}
	@Test
	public void TC_07_Drap_And_Drop_HTML5_Xpath() throws AWTException
	{
		driver.get("https://automationfc.github.io/drag-drop-html5");
		
		drag_the_and_drop_html5_by_xpath("//div[@id='column-a']","//div[@id='column-b']");
		sleepInSecond(2);
		Assert.assertTrue(isElementDisplayed("//div[@id='column-a']/header[text()='B']"));
		
		drag_the_and_drop_html5_by_xpath("//div[@id='column-a']","//div[@id='column-b']");
		sleepInSecond(2);
		Assert.assertTrue(isElementDisplayed("//div[@id='column-a']/header[text()='A']"));
		
		
	}
	public void drag_the_and_drop_html5_by_xpath(String sourceLocator, String targetLocator) throws AWTException {

		WebElement source = driver.findElement(By.xpath(sourceLocator));
		WebElement target = driver.findElement(By.xpath(targetLocator));

		// Setup robot
		Robot robot = new Robot();
		robot.setAutoDelay(500);

		// Get size of elements
		Dimension sourceSize = source.getSize();
		Dimension targetSize = target.getSize();

		// Get center distance
		int xCentreSource = sourceSize.width / 2;
		int yCentreSource = sourceSize.height / 2;
		int xCentreTarget = targetSize.width / 2;
		int yCentreTarget = targetSize.height / 2;

		Point sourceLocation = source.getLocation();
		Point targetLocation = target.getLocation();
		System.out.println(sourceLocation.toString());
		System.out.println(targetLocation.toString());

		// Make Mouse coordinate center of element
		sourceLocation.x += 20 + xCentreSource;
		sourceLocation.y += 110 + yCentreSource;
		targetLocation.x += 20 + xCentreTarget;
		targetLocation.y += 110 + yCentreTarget;

		System.out.println(sourceLocation.toString());
		System.out.println(targetLocation.toString());

		// Move mouse to drag from location
		robot.mouseMove(sourceLocation.x, sourceLocation.y);

		// Click and drag
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x, ((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

		// Move to final position
		robot.mouseMove(targetLocation.x, targetLocation.y);

		// Drop
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}

	private boolean isElementDisplayed(String locator) {
		return driver.findElement(By.xpath(locator)).isDisplayed();
		
	}

	public String readFile(String file) throws IOException {
		Charset cs = Charset.forName("UTF-8");
		FileInputStream stream = new FileInputStream(file);
		try {
			Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			return builder.toString();
		} finally {
			stream.close();
		}
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

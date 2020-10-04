package webdriver;

import org.testng.annotations.Test;

import com.sun.javafx.beans.IDProperty;


import org.testng.annotations.BeforeClass;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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
	String idParent;
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		explicitWait = new WebDriverWait(driver, 30);
		jsExecutor = (JavascriptExecutor) driver;

	}

	// @Test
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
		Assert.assertTrue(
				driver.findElement(By.xpath("//div[@class='username' and contains(string(),'Automation Testing')]"))
						.isDisplayed());

	}

	// @Test
	public void TC_02_Popup_Fix2() {
		driver.get("https://bni.vn/");

		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='sgpb-popup-dialog-main-div']")).isDisplayed());
		sleepInSecond(3);
		driver.findElement(By.xpath("//img[@class='sgpb-popup-close-button-1']")).click();
		sleepInSecond(3);
		Assert.assertFalse(driver.findElement(By.xpath("//div[@id='sg-popup-content-wrapper-1236']")).isDisplayed());

	}

	// @Test
	public void TC_03_Iframe() {
		driver.get("https://kyna.vn/");

		driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='face-content']/iframe")));
		String facebookLike = driver.findElement(By.xpath("//a[text()='Kyna.vn']/parent::div/following-sibling::div"))
				.getText();

		Assert.assertEquals(facebookLike, "169K likes");

		driver.switchTo().defaultContent();
		// check search text box displays
		Assert.assertTrue(driver.findElement(By.id("live-search-bar")).isDisplayed());

		// switch to webchat
		driver.switchTo().frame(driver.findElement(By.id("cs_chat_iframe")));
		sleepInSecond(5);

		// input selenium
		driver.findElement(By.cssSelector(".textarea")).sendKeys("selenium");
		driver.findElement(By.cssSelector(".textarea")).sendKeys(Keys.ENTER);

		// verify form hien thi
		Assert.assertTrue(driver.findElement(By.cssSelector(".meshim_widget_components_chatWindow_profileMenu_Edit"))
				.isDisplayed());

		// input Java
		driver.switchTo().defaultContent();
		driver.findElement(By.id("live-search-bar")).sendKeys("Java");
		driver.findElement(By.cssSelector(".search-button>img")).click();
		sleepInSecond(5);
		Assert.assertEquals(driver.findElement(By.xpath("//h1[contains(text(),'Java')]")).getText(), "'Java'");

	}

	// @Test
	public void TC_04_Window_Tab() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		idParent = driver.getWindowHandle();

		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();

		// switch to Google tab
		switchToWindowByTitle("Google");
		Assert.assertEquals(driver.getTitle(), "Google");

		// swith back to parent tab
		switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");

		// click facebook link and switch qua tab moi
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		switchToWindowByTitle("Facebook – log in or sign up");
		Assert.assertEquals(driver.getTitle(), "Facebook - Đăng nhập hoặc đăng ký");

		// swith back to parent tab
		switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");

		// click TIKI link
		driver.findElement(By.xpath("//a[text()='TIKI']")).click();
		switchToWindowByTitle("Mua Hàng Trực Tuyến Uy Tín với Giá Rẻ Hơn tại Tiki.vn");

		// close all tabs without parent tab
		boolean parentStatus = closeAllWindowsWithoutParent(idParent);
		System.out.println("parent status =" + parentStatus);
		Assert.assertEquals(driver.getTitle(), "SELENIUM WEBDRIVER FORM DEMO");
	}

	//@Test
	public void TC_05_Window_Tab() {
		driver.get("https://kyna.vn/");
		sleepInSecond(5);
		// close pop-up chat

		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='cs_chat_iframe']")));
		clickToElementByJS("//div[@class='icons']");

		// click on facebook link 
		driver.switchTo().parentFrame();
		clickToElementByJS("//img[@alt='facebook']");
		switchToWindowByTitle("Kyna.vn - Home | Facebook");
		
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/kyna.vn");

		// click on youtube 
		switchToWindowByTitle("Kyna.vn - Học online cùng chuyên gia");
		driver.switchTo().parentFrame();
		clickToElementByJS("//img[@alt='youtube']");
		switchToWindowByTitle("Kyna.vn - YouTube");
		
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.youtube.com/user/kynavn");
		
		switchToWindowByTitle("Kyna.vn - Học online cùng chuyên gia");
		driver.switchTo().parentFrame();
		clickToElementByJS("//img[@alt='zalo']");
		switchToWindowByTitle("Zalo Official Account");
		
		Assert.assertEquals(driver.getCurrentUrl(), "https://zalo.me/1985686830006307471");

		// click on kyna
		switchToWindowByTitle("Kyna.vn - Học online cùng chuyên gia");
		driver.switchTo().parentFrame();
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='face-content']/iframe")));
		clickToElementByJS("//a[text()='Kyna.vn']");
		switchToWindowByTitle("Kyna.vn - Home | Facebook");
		
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/kyna.vn/");
		
		//close all with parent window
		switchToWindowByTitle("Kyna.vn - Học online cùng chuyên gia");
		String parentWindow = driver.getWindowHandle();
		closeAllWindowsWithoutParent(parentWindow);
		
	}
	public void TC_06_Window_Tab() {
		driver.get("http://live.demoguru99.com/index.php/");
		
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		
		
		
		
	}
	public void clickToElementByJS(String locator)
	{
		
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath(locator)));
	}

	public boolean closeAllWindowsWithoutParent(String idParent) {
		Set<String> allWindows = driver.getWindowHandles();

		for (String runWindow : allWindows) {

			if (!runWindow.equals(idParent)) {
				driver.switchTo().window(runWindow);
				driver.close();
			}
		}
		sleepInSecond(3);
		driver.switchTo().window(idParent);
		if (driver.getWindowHandles().size() == 1)
			return true;
		else
			return false;
	}

	public void switchToWindowByTitle(String title) {
		Set<String> allWindows = driver.getWindowHandles();

		for (String runWindow : allWindows) {
			driver.switchTo().window(runWindow);
			String currentTitle = driver.getTitle();
			if (currentTitle.trim().equals(title)) {
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

	@AfterClass
	public void afterClass() {
	}

}

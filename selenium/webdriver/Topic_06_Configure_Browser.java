package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;


public class Topic_06_Configure_Browser {
	WebDriver driver;
  @Test
  public void TC_01_Run_On_Firefox() {
	  driver = new FirefoxDriver();
	  driver.get("http://demo.guru99.com/v4/");
	  driver.quit();
	  
  }
  @Test
  public void TC_02_Run_On_Chrome() {
	  System.setProperty("webdriver.chrome.driver", "D:\\Automation\\Automation FC\\02. Selenium API\\browserDriver\\chromedriver.exe");
	  driver = new ChromeDriver();
	  driver.get("http://demo.guru99.com/v4/");
	  driver.quit();
  }

  public void TC_03_Run_On_Edge() {
	  System.setProperty("webdriver.edge.driver", "D:\\Automation\\Automation FC\\02. Selenium API\\browserDriver\\msedgedriver.exe");
	  driver = new EdgeDriver();
	  driver.get("http://demo.guru99.com/v4/");
	  driver.quit();
  }
}

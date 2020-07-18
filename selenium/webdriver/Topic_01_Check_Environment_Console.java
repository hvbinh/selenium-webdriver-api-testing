package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Topic_01_Check_Environment_Console {

	public static void main(String[] args) {
		WebDriver driver;
		// TODO Auto-generated method stub
		System.out.println("hello world");
		driver = new FirefoxDriver();
		driver.get("http://facebook.com");

	}

}

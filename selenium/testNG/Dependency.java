package testNG;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Dependency {
	@Test
	public void TC_01_Register() {
		System.out.println("register user");
		
		Assert.assertTrue(false);
	}

	@Test(dependsOnMethods = "TC_01_Register")
	public void TC_02_Login() {
		System.out.println("login function");
	}
}

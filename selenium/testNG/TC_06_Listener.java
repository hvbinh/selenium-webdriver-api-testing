package testNG;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(testNG.testNGListener.class)
public class TC_06_Listener {

	@Test
	public void TC_01_Register() {
		System.out.println("register user");

		Assert.assertTrue(false);
	}

	@Test()
	public void TC_02_Login() {
		System.out.println("login function");
	}
}

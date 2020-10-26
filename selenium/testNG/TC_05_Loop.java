package testNG;

import java.util.Random;

import org.testng.annotations.Test;

public class TC_05_Loop {
  @Test(invocationCount = 10)
  public void TC_01_Register() {
	  System.out.println("TC_01_Register: "+randomNumber());
  }
  public int randomNumber()
  {
	  Random random = new Random();
	  return random.nextInt(9999);
  }
}

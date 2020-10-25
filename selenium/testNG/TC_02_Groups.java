package testNG;

import org.testng.annotations.Test;

public class TC_02_Groups {
  @Test(groups = "mobile")
  public void TC_01() {
	  System.out.println("TC 01");
  }
  @Test(groups = "mobile")
  public void TC_02() {
	  System.out.println("TC 02");
  }
  @Test(groups = "web")
  public void TC_03() {
	  System.out.println("TC 03");
  }
  @Test(groups = "web")
  public void TC_04() {
	  System.out.println("TC 04");
  }
}

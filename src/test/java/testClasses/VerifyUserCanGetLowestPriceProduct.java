package testClasses;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import baseClasses.Base1;
import pomClasses.HomePage;

public class VerifyUserCanGetLowestPriceProduct {
	
WebDriver driver;
	
	HomePage hp;
	
	@BeforeClass
	@Parameters("browser")
	public void beforeClass(String browser) {
		driver = Base1.getDriver(browser);
	}
	
	@BeforeMethod
	public void beforeMethod() {
		hp = new HomePage(driver);
	}
	
	@Test()
	public void VerifyUserGetLowestPriceProduct() {
		hp.searchProduct();	
		
		for(int i=1; i<=5; i++) {
			
			if(i != 1) {
				hp.switchPage(i);
			}
			
			Assert.assertNotEquals(hp.getLowsetPrice(), "");
		}
		
		
		
	}
	
	
	@AfterMethod
	public void afterMethod() {
	
	}
	
	@AfterClass
	public void afterClass() {
		Base1.unloadDriver();
	}

}

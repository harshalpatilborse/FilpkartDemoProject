package testClasses;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import UtilClasses.Util1;
import baseClasses.Base1;
import pomClasses.HomePage;
import pomClasses.ProfilePage;

public class VerifyUserCanAddAddress {
	WebDriver driver;

	ExtentHtmlReporter htmlReporter;
	ExtentReports reports;
	ExtentTest test;

	HomePage hp;
	ProfilePage pp;

	int oldAddressCount;
	int currentAddressCount;

	boolean isAddressUpdated = true;

	@BeforeClass
	@Parameters("browser")
	public void beforeClass(String browser) {
		htmlReporter = Base1.getHtmlReporter();
		reports = Base1.getReports();
		
		test = Base1.getExtentTest("VerifyUserCanLogin");
		driver = Base1.getDriver(browser);
	}

	@BeforeMethod
	public void beforeMethod() {
		hp = new HomePage(driver);
		pp = new ProfilePage(driver);
	}

	@Test(priority = 1)
	public void verifyUserCanGoToProfilePage() {
		if (hp.clickOnMyProfile()) {
			System.out.println("Test is passed");
		} else {
			System.out.println("Test is failed");
		}

		pp.clickOnManageAddress();

		oldAddressCount = pp.getAddressCount();

		pp.clickOnAddNewAddressText();
	}

	@DataProvider(name = "testData")
	public String[][] getData() {
		String[][] uData = { { "Anvi", "8956235623", "411033", "near dmart", "Shivaji chowk, near busstand, pune" },
				{ "Sai", "7845125623", "411017", "deccan chowk", "Gandhi chowk, near mall, pune" } };
		return uData;
	}

	@Test(priority = 2, dataProvider = "testData")
	public void verifyUserCanAddNewAddress(String name, String MobNumber, String pincode, String locality,
			String detailAdd) {

		List<String> addressDataList = new ArrayList<>(Arrays.asList(name, MobNumber, pincode, locality, detailAdd));

		pp.addAddress(addressDataList);

		currentAddressCount = pp.getAddressCount();

		if (oldAddressCount == currentAddressCount) {
			isAddressUpdated = false;
		}

		Assert.assertTrue(isAddressUpdated);

	}
	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException {
		Util1.getExtentReportStatus(result, driver, test);
	}
	@AfterClass
	public void afterClass() {
		
		reports.flush();
	
}
	}


package UtilClasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

public class Util1 {
	
	//Explicit wait
		//Screenshot
		//Excel sheet
		//javascript executor
		//mouse actions
		//scrolling
		
		public static void waitTillElementPresent(WebDriver driver, By element) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		}
		
		
		public static void waitTillElementPresent(WebDriver driver, WebElement element) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOf(element));
		}
		
		
		public void moveToElement(WebDriver driver, WebElement element) {
			Actions act = new Actions(driver);
			act.moveToElement(element).perform();
		}
		
		
		public static String getDataFromConfig(String key) throws IOException {
			FileInputStream file = new FileInputStream("configuration\\config.properties");
			
			Properties prop = new Properties();
			
			prop.load(file);
			
			System.out.println(prop.getProperty(key));
			
			return prop.getProperty(key);
		}
		
		
		public static String getScreenShotPath(WebDriver driver, String methodName) throws IOException {
			
			
			TakesScreenshot ts = (TakesScreenshot)driver;
			
			File source = ts.getScreenshotAs(OutputType.FILE);
			
			String path = methodName +".png";
			
			File dest = new File(path);
			
			FileHandler.copy(source, dest);
			
			return path;
			
		}
		
		public static void getExtentReportStatus(ITestResult result,WebDriver driver, ExtentTest test)throws IOException {
			if(result.getStatus() == ITestResult.SUCCESS) {
				test.log(Status.PASS, result.getName() +" is Passed");
			}
			
			
			
			else if(result.getStatus() == ITestResult.FAILURE)
			{
				String path = Util1.getScreenShotPath(driver, result.getName());
				
				test.log(Status.FAIL, result.getName() +" is Failed", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			}
			
			else if(result.getStatus() == ITestResult.SKIP){
				
				test.log(Status.FAIL, result.getName() +" is Skipped");
			}
		
		}
		public static void clickByJE(WebDriver driver, WebElement element) {
			JavascriptExecutor js =(JavascriptExecutor)driver;
			js.executeScript("arugument[0].click();", element);
		}

		
		
		
		public static void moveTOclick() {
			
			
		}
		
}

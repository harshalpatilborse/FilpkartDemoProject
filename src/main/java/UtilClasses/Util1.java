package UtilClasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

}

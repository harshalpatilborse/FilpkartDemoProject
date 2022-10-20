package pomClasses;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import UtilClasses.Util1;

public class ProfilePage extends Util1{
	
	
WebDriver driver;
	
	@FindBy(xpath="//div[text()='Manage Addresses']")
	private WebElement manageAddressText;
	
	@FindBy(xpath="//div[@class='_1QhEVk']")
	private WebElement addNewAddressText;
	
	@FindBy(xpath="//textarea[@tabindex='5']")
	private WebElement detailAddress;
	
	@FindBy(xpath="//button[text()='Save']")
	private WebElement saveButton;
	
	@FindBy(xpath="//div[@class='_1CeZIA']")
	private List<WebElement> addressCount;
	
	
	
	
	
	
	public ProfilePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}
	
	
	public void clickOnManageAddress() {
		manageAddressText.click();
	}
	
	public void clickOnAddNewAddressText() {
		addNewAddressText.click();
	}
	
	
	public void addAddress(List<String> addressData) {
		
		for(int i=1; i<=4; i++) {
			driver.findElement(By.xpath("//input[@tabindex='"+i+"']")).sendKeys(addressData.get(i-1));
		}
		
		detailAddress.sendKeys(addressData.get(4));
		
		saveButton.click();
		
	}
	
	
	
	public int getAddressCount() {
		waitTillElementPresent(driver, addNewAddressText);
		
		return addressCount.size();
		
	}

}

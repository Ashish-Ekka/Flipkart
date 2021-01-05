package resources;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Pincode {

	public WebDriver driver;
	
	public String str;
	
	public Pincode(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public void setPincode() throws InterruptedException
	{
		String s = driver.getWindowHandle().toString();
		//System.out.println("Pincode driver window handle "+s);
		MainPage mp = new MainPage(driver);
		mp.setPincode().clear();
		Thread.sleep(5000);
		mp.checkPincode().click();
		mp.setPincode().sendKeys("560077");
		
		mp.setPincode().sendKeys(Keys.ENTER);
	}
	
	public boolean checkAvailability()
	{
		MainPage mp = new MainPage(driver);
		str = mp.chechPinCodeStatus().getText().toString();
		if(str.contains("Currently out of stock in this area."))
			return false;
		else
			return true;
	}
	
	
	
}

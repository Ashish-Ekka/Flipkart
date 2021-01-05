package resources;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage {
	
	public WebDriver driver;
	public MainPage(WebDriver driver)
	{
		this.driver = driver;
	}
	private By searchProduct = By.cssSelector("input._3704LK");
	private By wholePage = By.cssSelector("div._4rR01T");
	private By pincode = By.cssSelector("input#pincodeInputId");
	private By buy = By.cssSelector("button._2KpZ6l._2U9uOA.ihZ75k._3AWRsL");
	private By address = By.cssSelector("span.rPNEXT.Br27Zz");
	private By deliverHere = By.cssSelector("button._2KpZ6l.RLM7ES._3AWRsL");
	private By finalProduct = By.cssSelector("div._2Kn22P");
	private By finalPrice = By.cssSelector("div._2Tpdn3");
	private By continueButton = By.cssSelector("button._2KpZ6l._1seccl._3AWRsL");
	private By checkPincodeStatus = By.cssSelector("div._3XINqE");
	private By ramOfMobile = By.xpath("//li[contains(text(),'6 GB RAM | 128 GB ROM | Expandable Upto 512 GB')]");
	private By checkPincode = By.cssSelector("span._2P_LDn");
	
	public WebElement search()
	{
		return driver.findElement(searchProduct);
	}
	public WebElement checkPincode()
	{
		return driver.findElement(checkPincode);
	}
	
	public WebElement chechPinCodeStatus()
	{
		return driver.findElement(checkPincodeStatus);
	}
	
	public List<WebElement> noOfProducts()
	{
		return driver.findElements(wholePage);
	}
	
	public WebElement setPincode()
	{
		return driver.findElement(pincode);
	}
	
	public WebElement clickOnBuy()
	{
		return driver.findElement(buy);
	}
	
	public List<WebElement> getAddress()
	{
		return driver.findElements(address);
	}
	
	public WebElement deliverButton()
	{
		return driver.findElement(deliverHere);
	}
	
	public WebElement finalProductNameCheck()
	{
		return driver.findElement(finalProduct);
	}
	
	public WebElement finalPriceCheck()
	{
		return driver.findElement(finalPrice);
	}
	
	public WebElement clickOnContinue()
	{
		return driver.findElement(continueButton);
	}
	
	public List<WebElement> selectRamofMobile()
	{
		return driver.findElements(ramOfMobile);
	}
}

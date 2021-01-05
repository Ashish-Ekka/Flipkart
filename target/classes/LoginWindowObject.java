package resources;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginWindowObject {
	
	public WebDriver driver;
	
	public LoginWindowObject(WebDriver driver)
	{
		this.driver = driver;
	}
	
	private By userName = By.cssSelector("input._2IX_2-.VJZDxU");
	private By password = By.cssSelector("input._2IX_2-._3mctLh.VJZDxU");
	private By login = By.cssSelector("button._2KpZ6l._2HKlqd._3AWRsL");
	
	public WebElement setUserName()
	{
		return driver.findElement(userName);
	}
	
	public WebElement setPassword()
	{
		return driver.findElement(password);
	}
	
	public WebElement clickLogin()
	{
		return driver.findElement(login);
	}
}

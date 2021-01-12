package Execution;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Automation.Flipkart.DriverSetup;
import resources.Address;
import resources.KeywordEngine;
import resources.LoginWindowObject;
import resources.MainPage;
import resources.NoOfProducts;
import resources.Pincode;

public class HomePage extends DriverSetup{

	public Logger log = LogManager.getLogger(DriverSetup.class.getClass());
	
	public WebDriver driver;
	@BeforeTest
	public void beforeMethod()
	{
		driver = driverInitializer(driver);
		driver.get("https://www.flipkart.com/");
	}
	
	@Test
	public void Login() throws InterruptedException
	{
		log.info("Module Name: Login");
		LoginWindowObject l = new LoginWindowObject(driver);
		log.info("enter username");
		l.setUserName().sendKeys("7206000870");
		log.info("enter password");
		l.setPassword().sendKeys("iamawesome");
		l.clickLogin().click();
		WebDriverWait expWait = new WebDriverWait(driver, 10);
		expWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div._3wFoIb.row")));
	}
	
	@Test(dependsOnMethods= {"Login"})
	public void searchProduct() throws EncryptedDocumentException, IOException
	{
		KeywordEngine ke = new KeywordEngine();
		log.info("Module Name: Product Search");
		MainPage mp = new MainPage(driver);
		log.info("entering product name to be search");
		ke.stratExecution("product");
		mp.search().sendKeys(ke.productName);
		log.info("product name entered");
		mp.search().sendKeys(Keys.ENTER);
		
	}
	
	@Test(dependsOnMethods= {"Login","searchProduct"})
	public void searchNoOfProducts() throws InterruptedException, IOException
	{
		log.info("Module Name: Search number of products");
		NoOfProducts np = new NoOfProducts(driver);
		log.info("getting no. of products");
		np.getNoOfProducts();
		
	}
	
	@Test(dependsOnMethods= {"Login","searchProduct","searchNoOfProducts"})
	public void setPincode() throws InterruptedException, IOException
	{
		log.info("Module Name: Set Pincode");
		final String path = System.getProperty("user.dir")+"\\src\\main\\java\\resources\\childwindowReference.properties";
		FileInputStream fip = new FileInputStream(path);
		Properties prop = new Properties();
		prop.load(fip);
		String childWindow = prop.getProperty("childWindow");
		System.out.println("Set Pincode Child Window: "+childWindow);
		driver.switchTo().window(childWindow);
		
		Pincode p = new Pincode(driver);
		p.setPincode();
		p.checkAvailability();
		Assert.assertTrue(p.checkAvailability());
		log.error("Product available for this area");
		
	}
	@Test(dependsOnMethods= {"Login","searchProduct","searchNoOfProducts","setPincode"})
	public void clickOnBuy()
	{
		MainPage mp = new MainPage(driver);
		log.info("clicking on buy button");
		mp.clickOnBuy().click();  
		
	}
	@Test(dependsOnMethods= {"Login","searchProduct","searchNoOfProducts","setPincode","clickOnBuy"})
	public void setAddress()
	{
		Address sa = new Address(driver);
		sa.getAddress();
	}
	
	@Test(dependsOnMethods={"Login","searchProduct","searchNoOfProducts","setPincode","clickOnBuy","setAddress"})
	public void checkingProductDetails()
	{
		NoOfProducts np = new NoOfProducts(driver);
		np.checkProductDetail();
	}
	@AfterTest
	
	public void closeBrowser()
	{
		driver.quit();
	}
}

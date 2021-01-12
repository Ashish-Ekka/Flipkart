package resources;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;


public class NoOfProducts extends KeywordEngine{
	
	public WebDriver driver;
	public Logger log = LogManager.getLogger(NoOfProducts.class.getClass());
	
	KeywordEngine ke = new KeywordEngine();
	public NoOfProducts(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public void getNoOfProducts() throws InterruptedException, IOException
	{
		MainPage mp = new MainPage(driver);
		
		List<WebElement> list = mp.noOfProducts();
		List<WebElement> mobileRam = mp.selectRamofMobile();
		ke.stratExecution("product");
		
		log.info("collecting list of products with the same name");
		//int count = list.size();
		//System.out.println(count);
		Iterator<WebElement> it =list.iterator();
		int len = list.size();
		
		Iterator<WebElement> it1 = mobileRam.iterator();
		System.out.println("Length of the list: "+len);
		int count = 0;
		while(it.hasNext())
		{
				WebElement productName = it.next();
				String pName="";
				try {
					pName = productName.getText().toString();
				}catch(Exception e)
				{
					//System.out.println("Exception: "+e);
				}
				//System.out.println(pName);
				log.info("checking the desired product");
				//Assert.assertTrue(pName.equalsIgnoreCase("Redmi K20 Pro (Glacier Blue, 128 GB)"));
				//pName.equals("Redmi K20 Pro (Glacier Blue, 110 GB)")//
				String product = ke.productName ;
				//System.out.println("pName "+pName);
				//System.out.println("product to be searced "+product);
					if(pName.equalsIgnoreCase(product))
					{
						
						System.out.println("inside Product if case");
				
						productName.click();
						log.info("Product Name: "+pName);
						//Thread.sleep(4000);
						final String path = System.getProperty("user.dir")+"\\src\\main\\java\\resources\\childwindowReference.properties";
						
						Properties prop = new Properties();
						FileOutputStream fos = new FileOutputStream(path);
						Set<String> handle = driver.getWindowHandles();
						Iterator<String> winHandle = handle.iterator();
						String parentWindow = winHandle.next();
						String childWindow = winHandle.next();
						prop.setProperty("childWindow",childWindow);
						
						prop.store(fos, null);
				//		driver.switchTo().window(childWindow);
					}
		}
		
	}
	
	public void getAddress()
	{
		MainPage mp = new MainPage(driver);
		KeywordEngine ke = new KeywordEngine();
		String checkAddress = ke.address;
		List<WebElement> addressList = mp.getAddress();
		log.info("getting all the address list which exist");
		Iterator<WebElement> it = addressList.iterator();
		while(it.hasNext())
		{
			WebElement add = it.next();
			String address="";
			try {
				 address = add.getText();
			}catch(Exception e) {}
			
			System.out.println(address);
			log.info("checking the desired address");
			if(address.contains(checkAddress))
			{
				//System.out.println("Inside address condition");
				log.info("got the desired addess, now selecting it");
				add.click();
				log.info("clicking on deliver here");
				mp.deliverButton().click();   //deliver here
			//	System.out.println("clicked deliver button");
			}
		}
	}
	
	//product for check-out
	public void checkProductDetail()
	{
		MainPage mp = new MainPage(driver);
		String finalProduct = mp.finalProductNameCheck().getText();
		if(finalProduct.equals("Redmi 9 (Sporty Orange, 128 GB)"))
		{
			//checking price
			String amount = mp.finalPriceCheck().getText();
			System.out.println("price of product: "+amount);
			if(amount.contains("10,740"))
			{
				System.out.println("Under amount if condition: "+amount);
				mp.clickOnContinue().click();
				System.out.println("continue button cliked");
			}
		}
	}
	
	

}

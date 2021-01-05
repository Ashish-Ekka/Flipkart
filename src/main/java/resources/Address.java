package resources;

import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Address {
	
	public WebDriver driver;
	public Logger log = LogManager.getLogger(Address.class.getClass());
	
	public Address(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public void getAddress()
	{
		MainPage mp = new MainPage(driver);
		List<WebElement> addressList = mp.getAddress();
		log.info("getting all address which exist ");
		Iterator<WebElement> it = addressList.iterator();
		while(it.hasNext())
		{
			WebElement add = it.next();
			String address="";
			try {
				 address = add.getText();
			}catch(Exception e) {}
			
			//System.out.println(address);
			log.info("inside getAddress Method");
			log.info("checking the desired address");
			if(address.contains("House No.22"))
			{
				//System.out.println("Inside address condition");
				add.click();
				log.info("clicking the desired address");
				mp.deliverButton().click();   //deliver here
				log.info("clicking on deliver button");
				//System.out.println("clicked deliver button");
				break;
			}
		}
	}

}

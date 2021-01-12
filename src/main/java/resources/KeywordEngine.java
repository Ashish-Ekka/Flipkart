package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class KeywordEngine {
	
	public WebDriver driver;
	public Workbook book;
	public Sheet sheet;
	public WebElement element;  
	private String path = System.getProperty("user.dir")+"\\src\\main\\java\\resources\\ProductDetails.xlsx";
	public FileInputStream fip;
	public String productName, pincode, address, testing;
	
	public void stratExecution(String sheetName) throws EncryptedDocumentException, IOException
	{
		fip = new FileInputStream(path);
		book = WorkbookFactory.create(fip);
		sheet = book.getSheet(sheetName);
		int k=0;
		for(int i=0;i<sheet.getLastRowNum();i++)
		{
			
			testing = sheet.getRow(i+1).getCell(k).toString().trim();
			if(testing.equalsIgnoreCase("Yes"))
			{
				productName = sheet.getRow(i+1).getCell(k+1).toString().trim();
				pincode = sheet.getRow(i+1).getCell(k+2).toString().trim();
				address = sheet.getRow(i+1).getCell(k+3).toString().trim();
			}
			
		}
	}
}

package Execution;

import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import Automation.Flipkart.DriverSetup;

public class Listeners extends DriverSetup implements ITestListener{
	

	public void onTestStart(ITestResult result) {
		
	}

	public void onTestSuccess(ITestResult result) {
	
		  try {

	            XWPFDocument docx = new XWPFDocument();
	            XWPFRun run = docx.createParagraph().createRun();
	            FileOutputStream out = new FileOutputStream("F:/Flipkart/flipkart_testing.docx");

	            for (int counter = 1; counter <= 5; counter++) {
	                captureScreenShot(docx, run, out);
	                TimeUnit.SECONDS.sleep(1);
	            }

	            docx.write(out);
	            out.flush();
	            out.close();
	            docx.close();

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}

	public void onTestFailure(ITestResult result){
		WebDriver driver = null;
		String methodname = result.getMethod().getMethodName();
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
			
		}catch(Exception e) {
			
		}
		try {
			screenshot(driver,methodname );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		
	}

	public void onStart(ITestContext context) {
		
	}

	public void onFinish(ITestContext context) {
		
	}

	
	   public static void captureScreenShot(XWPFDocument docx, XWPFRun run, FileOutputStream out) throws Exception {

	        String screenshot_name = System.currentTimeMillis() + ".png";
	        BufferedImage image = new Robot()
	                .createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
	        File file = new File("F:/Flipkart/" + screenshot_name);
	        ImageIO.write(image, "png", file);
	        InputStream pic = new FileInputStream("F:/Flipkart/" + screenshot_name);
	        run.addBreak();
	        run.addPicture(pic, XWPFDocument.PICTURE_TYPE_PNG, screenshot_name, Units.toEMU(350), Units.toEMU(350));
	        pic.close();
	        file.delete();
	    }
}

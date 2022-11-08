package com.BlackthronWeb.pages;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.Blackthron.web.sanityscripts.BaseTestWeb;
import com.Blackthron.web.utils.ConstantsWeb;
import com.Blackthron.web.utils.GlobalVariablesWeb;
import com.aventstack.extentreports.ExtentReporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class BasePageWeb {
	 
	
	public static  WebDriver driver;
	
	

	public BasePageWeb(WebDriver driver) {
		// TODO Auto-generated constructor stub
	}

	public static void reportPass(String passMsg){
		BaseTestWeb.test.log(BaseTestWeb.status.PASS, passMsg);
		 takeScreenshot();
	}
	
	public static void reportFail(String failureMsg) throws Exception{
		
		//test.log(LogStatus.FAIL, failureMsg);
		 takeScreenshot();
		Assert.fail(failureMsg);
		 		 
	}
	

		
	
	public static void takeScreenshot(){
		
		String CURRENTPATH="";
		switch(ConstantsWeb.OS) {
		case "Mac":
		{
			CURRENTPATH=ConstantsWeb.SCREENSHOT_PATH_MAC;
			
			break;
		}
		case"Windows":
		{
			CURRENTPATH=ConstantsWeb.SCREENSHOT_PATH;
			break;
		}
		}
		
		
		// decide the file name
		Date d = new Date();
		String screenshotFile=d.toString().replace(":", "_").replace(" ","_")+".png";
		System.out.println(screenshotFile);
		String path=CURRENTPATH+screenshotFile; 
		
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		// take screenshot
		try {
			//FileUtils.copyFile(scrFile, new File(path));
			File DestFile=new File(path);
			FileUtils.copyFile(scrFile, DestFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//add screenshot to report
   //test.log(LogStatus.INFO,"Snapshot below: "+test.addScreenCapture(addScreenshot()));
			
	}
	
	@SuppressWarnings("resource")
	 public static String addScreenshot() {
	     File scrFile = ((TakesScreenshot) BasePageWeb.driver).getScreenshotAs(OutputType.FILE);
	     String encodedBase64 = null;
	     FileInputStream fileInputStreamReader = null;
	     try {
	         fileInputStreamReader = new FileInputStream(scrFile);
	         byte[] bytes = new byte[(int)scrFile.length()];
	         fileInputStreamReader.read(bytes);
	         encodedBase64 = new String(Base64.encodeBase64(bytes));
	     } catch (FileNotFoundException e) {
	         e.printStackTrace();
	     } catch (IOException e) {
	         e.printStackTrace();
	     }
	     return "data:image/png;base64,"+encodedBase64;
	 }
	
	public static void fullScreenshot() throws InterruptedException, AWTException, IOException
	{
		String CURRENTPATH="";
		switch(ConstantsWeb.OS) {
		case "Mac":
		{
			CURRENTPATH=ConstantsWeb.SCREENSHOT_PATH_MAC;
			
			break;
		}
		case"Windows":
		{
			CURRENTPATH=ConstantsWeb.SCREENSHOT_PATH;
			break;
		}
		}
	
		Date d = new Date();
		String screenshotFile=d.toString().replace(":", "_").replace(" ","_")+".png";
		String path=CURRENTPATH+screenshotFile;
		Robot robot=new Robot();		
		Rectangle rect=new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
		BufferedImage src = robot.createScreenCapture(rect);
		File scrFile=new File(path);
		ImageIO.write(src, "png", scrFile);
		//add screenshot to report
		//test.log(LogStatus.INFO,"Snapshot below: "+test.addScreenCapture(addScreenshotFull(scrFile)));
			
}
	
	
	@SuppressWarnings("resource")
	 public static String addScreenshotFull(File file) throws AWTException, IOException {


	     String encodedBase64 = null;
	     FileInputStream fileInputStreamReader = null;
	     try {
	         fileInputStreamReader = new FileInputStream(file);
	         byte[] bytes = new byte[(int)file.length()];
	         fileInputStreamReader.read(bytes);
	         encodedBase64 = new String(Base64.encodeBase64(bytes));
	     } catch (FileNotFoundException e) {
	         e.printStackTrace();
	     } catch (IOException e) {
	         e.printStackTrace();
	     }
	     return "data:image/png;base64,"+encodedBase64;
	 }
}
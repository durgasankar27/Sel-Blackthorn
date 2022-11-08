package com.Blackthron.web.utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;



/*************************************************************************************
 *     Class : GlobalVariables           
 *     Purpose : This class is used for storing all the global constants
 *     
 **************************************************************************************/

public class ConstantsWeb {

	public static String OS = "Windows";
//	public static String OS = "Mac";
	
//	If Running a single Class
	public static String executionMode="class";
//	If Running in a Suite
//	public static String executionMode = Base_Test_Web_Utils.getTimeStamp();

	public static String browser = "Chrome";
	//public static String browser = "firefox";
   //public static String browser="Safari";
	
       public static String URL= "https://login.salesforce.com";
       
      
	
	public static String salesforceurl="";
     
    public boolean SCREENSHOT_TO_FOLDER = true;
    
	public static String Version;
	public static final String REPORT_PATH_MAC = "";
	public static final String SCREENSHOT_PATH_MAC = "";
	public static final String REPORT_PATH = "C:\\work\\Reports\\";
	public static final String SCREENSHOT_PATH = "C:\\work\\Screenshots\\";
	public static final String REPORT_EMAIL_SUBJECT = "Automation reports from Web";
	
	
	// ----------- Read The Data From Excel Sheet ----------//
	
	public static String EXCEL_PATH="";
	public static String SHEET_NAME="";

	public static String UserName="";
	public static String password="";
	
	public static WebDriver driver;
	
	public static boolean Result = false;
	
	public static String environment = "Test Environment";
	
	public static String Event_planner = "";
	public static String Event_admin = "";
	public static String ERS_Completed="Completed";
	
	public static String Eventtab="https://raagvitechcom4-dev-ed.lightning.force.com/lightning/o/conference360__Event__c/list?filterName=Recent";
	
	public static final String EMAIL_SUBJECT = "Blackthorn - Automation";
	public static final String sheet = "Blackthorn";

    //	---------- User/local/bin ----------//
	
	public static final String CHROME_DRIVER_EXE = ".//drivers/chromedriver.exe";
	public static final String MOZILLA_DRIVER_EXE = ".//drivers/geckodriver.exe";
	public static final String IE_DRIVER_EXE = ".//drivers/IEDriverServer.exe";

	public static final String CHROME_DRIVER_DMG = ".//drivers/chromedriver";
	public static final String MOZILLA_DRIVER_DMG = ".//drivers/geckodriver.exe";
	public static final String IE_DRIVER_DMG = ".//drivers/IEDriverServer.exe";
}
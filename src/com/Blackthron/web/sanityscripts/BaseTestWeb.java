package com.Blackthron.web.sanityscripts;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.swing.text.Utilities;

import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.util.SheetUtil;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariDriverService;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import com.Blackthron.web.utils.Base_Test_Web_Utils;
import com.Blackthron.web.utils.ConstantsWeb;
import com.Blackthron.web.utils.ExtentManager;
import com.Blackthron.web.utils.GlobalVariablesWeb;
import com.Blackthron.web.utils.ListenersWeb;
import com.Blackthron.web.utils.UtilitiesWeb;
import com.BlackthronWeb.pages.BasePageWeb;
import com.aventstack.extentreports.ExtentReporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ClearValuesRequest;
import com.google.api.services.sheets.v4.model.ClearValuesResponse;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTestWeb extends Base_Test_Web_Utils {
	// @Parameters({ "browserName" })
	// As of now we are not passing browser name from testNG , if required we will
	// uncomment the above line and also in testng.xml and pass "String browser" as
	// parameter to setup() below.

	public static String methodName;
	public static String screenShotFilePath="";
	public static String className="";
	public static String hour="";
	
	@BeforeSuite(alwaysRun = true)
	public void beforeSuite(ITestContext ctx) throws UnknownHostException {
		suiteName = ctx.getCurrentXmlTest().getSuite().getName() + " ";
		
		
			initializeReportClassLevel(suiteName);
		String IP_adress=systemAddress.getHostAddress().replace(".", "_");
		
		
		
		if (ConstantsWeb.OS.equalsIgnoreCase("Windows")) {
			screenShotFilePath = ConstantsWeb.SCREENSHOT_PATH +IP_adress+"\\"+ getDateStamp() + "\\";
			}

			else if (ConstantsWeb.OS.equalsIgnoreCase("Mac")) {
			screenShotFilePath = ConstantsWeb.SCREENSHOT_PATH_MAC +IP_adress+"/"+ getDateStamp() + "/";
			}
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String fileName = "ExecutionLog_" + timeStamp + ".log";
		String filePath = "";
		 
		if (ConstantsWeb.OS.equalsIgnoreCase("Mac")) {
			filePath = ConstantsWeb.SCREENSHOT_PATH_MAC +"/Logs/"+ fileName;
			}
		else
		{
			filePath="C:\\work\\Reports\\"+ fileName;
		}
		
		File f = new File(filePath);
		System.setProperty("log.file", filePath);
		System.setProperty("log.parent.path", f.getParent());
		System.setProperty("log.file.name", FilenameUtils.getBaseName(f.getName()));
		logger = LogManager.getRootLogger();
		logger.trace("Initializing the log file in before suit.");
		logger.trace("Executing the suite file : " + suiteName);
	}

	@BeforeTest(alwaysRun = true)
	public void beforeTest(ITestContext ctx) {

		logger.trace("Executing the test file : " + ctx.getCurrentXmlTest().getName());

	}

	@BeforeClass(alwaysRun = true)
	public void beforeClass(ITestContext ctx) {
		testName = ctx.getCurrentXmlTest().getSuite().getName() + " : " + ctx.getCurrentXmlTest().getName();
		logger.trace("Executing the class : " + this.getClass().getSimpleName());
		mainTest = report.createTest(testName + " : " + this.getClass().getSimpleName());
		mainTest.assignCategory(ctx.getCurrentXmlTest().getName());
		parent.set(mainTest);
	}

	@BeforeMethod(alwaysRun = true)
	public void setup(Method testName, ITestResult result) throws InterruptedException {
		// VootConstantsWeb.browser = browser;
		// rep = ExtentManager.getInstance();
		testCaseName = testName.getName();
		logger.trace("Executing the test case : " + testCaseName);
		test = mainTest.createNode(testCaseName);
		child.set(test);
//		test = rep.startTest(testCaseName);
		

	}

	@AfterMethod(alwaysRun = true)
	public void getResult(Method testName, ITestResult result) throws IOException {
		testCaseName = testName.getName();
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(status.FAIL, "Test Case Failed is " + testCaseName, MediaEntityBuilder
					.createScreenCaptureFromPath(UtilitiesWeb.capture_screenshot(driver, testCaseName)).build());
			test.log(status.FAIL, "Test Case Failed is " + result.getThrowable());
		} else if (driver == null) {
			test.log(status.FAIL, "Test Case Failed is " + result.getTestName());
		} else {
			logger.trace("Completed the test case : " + testCaseName);
		}
		report.flush();
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() throws IOException {
		if (report != null) {
			report.flush();
			logger.trace("Completed the class : " + testCaseName);
//			killProcess();
		}
	}

	@AfterTest(alwaysRun = true)
	public void quitbrowser(ITestContext ctx) throws Exception {
		if (report != null) {
			report.flush();
		}
//		if (driver != null) {
//			if (ConstantsWeb.OS.equals("Windows")) {
//				try {driver.quit();
//				Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
//				} catch (IOException e) {
//				} 
//			}
//			if(ConstantsWeb.OS.equals("Mac")){
//				try {driver.quit();
//				String cmds[] = {"killall","Google Chrome"};
//				Runtime.getRuntime().exec(cmds);
//				} catch (Exception e) {
//				}
//			}
//		}
		logger.trace("Completed the test  : " + ctx.getCurrentXmlTest().getName());
//		killProcess();
	}

	@AfterSuite(alwaysRun = true)
	public void teardown() throws Exception {
		Thread.sleep(1000);

		if (report != null) {
			report.flush();
//			killProcess();
		}
//		try {
//			if (driver != null) {
//				//driver.quit();
//			}
//		} finally {
//		}
		logger.trace("Execution has been completed for the : " + suiteName);
//		driver.quit();
	}
	
	
	public String getDateStamp() {
		DateFormat dfor = new SimpleDateFormat("ddMMyyyy");
		Date obj = new Date();
		String date = dfor.format(obj);
		return date;
		}
	
	
	public void logScreenShot(WebDriver driver, ExtentTest test, String details) {
		UtilitiesWeb utilities = new UtilitiesWeb();
		ConstantsWeb Constants = new ConstantsWeb();

		if (Constants.SCREENSHOT_TO_FOLDER) {
			try {
				test.log(Status.INFO, details,
						MediaEntityBuilder
								.createScreenCaptureFromPath(
										utilities.captureScreenshotWithName(driver, Constants.SCREENSHOT_TO_FOLDER))
								.build());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (!Constants.SCREENSHOT_TO_FOLDER) {
			try {
				test.log(Status.INFO, details,
						MediaEntityBuilder
								.createScreenCaptureFromBase64String(
										utilities.captureScreenshotWithName(driver, Constants.SCREENSHOT_TO_FOLDER))
								.build());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
package com.Blackthron.web.utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.BlackthronWeb.pages.BasePageWeb;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Protocol;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base_Test_Web_Utils {
	
	
	
	public static WebDriver driver;
	public static ExtentHtmlReporter reporter;
	public static ExtentReports report;
	public static ExtentTest mainTest;
	public static ExtentTest test;
	public static Status status;
	public static Properties prop;
	public static FileReader reader;
	public String testName;
	public String testCaseName;
	public String suiteName;
	public static Logger logger;
	public static InetAddress systemAddress;
	public static File file;
	public static String  report_filePath;
	public static DesiredCapabilities capabilities;
	public static ThreadLocal parent = new ThreadLocal();
	public static ThreadLocal child = new ThreadLocal();
	public static String mainWindow;
	
	
	public void launchWeb(String os_Type,String bType) throws Exception {
//		test.log(status.INFO, "Opening OS - " + os_Type+" in "+bType+" browser");		
		if (os_Type.equalsIgnoreCase("Mac")) {
			switch (bType.toLowerCase()) {
			case "Firefox":
				//	WebDriverManager.firefoxdriver().setup();
				//	FirefoxProfile ffprofile = new FirefoxProfile();
				//	ffprofile.setPreference("dom.webnotifications.enabled", false);
				driver= new FirefoxDriver();
				break;
				
			case "chrome":
				/*
				 * ChromeOptions options = new ChromeOptions();
				 * 
				 * options.addArguments("--disable-notifications"); driver= new
				 * ChromeDriver(options);
				 */
				WebDriverManager.chromedriver().setup();
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--disable-notifications");
				// DesiredCapabilities capabilities = DesiredCapabilities.chrome();
				// capabilities.setCapability(ChromeOptions.CAPABILITY, options);
				// capabilities.setCapability("pageLoadStrategy", "normal");
				driver = new ChromeDriver(options);
				
				driver.manage().window().maximize();
				break;
				
			case "ie":
				WebDriverManager.iedriver().setup();
				DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
				capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
						true);

				capabilities.setCapability("requireWindowFocus", true);
				driver= new InternetExplorerDriver();
				driver.manage().window().maximize();
				break;
			case "safari":
				// System.setProperty("webdriver.ie.driver", VootConstantsWeb.IE_DRIVER_EXE);
				// FirefoxProfile ffprofile = new FirefoxProfile();
				// ffprofile.setPreference("dom.webnotifications.enabled", false);
				capabilities = DesiredCapabilities.safari();
				//				capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
				//						true);
				capabilities.setCapability("requireWindowFocus", true);
				driver = new SafariDriver(capabilities);
				driver.manage().window().maximize();
				break;
			default:Assert.assertTrue(false, "Given OS : '"+ os_Type+"'or Given Browser :'"+bType+"' is invalid");
			break;
			}
			
		}else if(os_Type.equalsIgnoreCase("windows")) {
			switch (bType.toLowerCase()) {
			case "firefox":

				WebDriverManager.firefoxdriver().setup();
								FirefoxProfile ffprofile = new FirefoxProfile();
								ffprofile.setPreference("dom.webnotifications.enabled", false);
				driver = new FirefoxDriver();
				break;
			case "chrome":
				/*
				 * ChromeOptions options = new ChromeOptions();
				 * 
				 * options.addArguments("--disable-notifications"); driver= new
				 * ChromeDriver(options);
				 */

				//System.setProperty("webdriver.chrome.driver", VootConstantsWeb.CHROME_DRIVER_EXE);
				WebDriverManager.chromedriver().setup();
				ChromeOptions options = new ChromeOptions();
				// options.addArguments("--start-maximized");
				options.addArguments("--disable-notifications");
				// DesiredCapabilities capabilities = DesiredCapabilities.chrome();
				// capabilities.setCapability(ChromeOptions.CAPABILITY, options);
				// capabilities.setCapability("pageLoadStrategy", "normal");
				driver = new ChromeDriver(options);
				driver.manage().window().maximize();
				break;
			case "ie":
				WebDriverManager.iedriver().setup();
				// FirefoxProfile ffprofile = new FirefoxProfile();
				// ffprofile.setPreference("dom.webnotifications.enabled", false);
				capabilities = DesiredCapabilities.internetExplorer();
				capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
						true);

				capabilities.setCapability("requireWindowFocus", true);
				driver = new InternetExplorerDriver(capabilities);
				driver.manage().window().maximize();
				break;
			 case "safari":
				System.setProperty("webdriver.ie.driver", ConstantsWeb.IE_DRIVER_EXE);
				// FirefoxProfile ffprofile = new FirefoxProfile();
				// ffprofile.setPreference("dom.webnotifications.enabled", false);
				DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
				capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
						true);

				capabilities.setCapability("requireWindowFocus", true);
				driver = new InternetExplorerDriver(capabilities);

				driver.manage().window().maximize();
				break;
			default:Assert.assertTrue(false, "Given OS : '"+ os_Type+"'or Given Browser :'"+bType+"' is invalid");
			break;
			}
		}
		Thread.sleep(3000);
		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		Thread.sleep(2000);

		try {
			driver.get(ConstantsWeb.URL);
		} catch (TimeoutException e) {
			try {
				driver.navigate().to(ConstantsWeb.URL);
			} catch (TimeoutException ex) {

				try {
					driver.navigate().to(ConstantsWeb.URL);

				} catch (TimeoutException exe) {
					BasePageWeb.reportFail("Page is not loaded for long time ");
				}
			}
		}
		Thread.sleep(5000);
//		test.log(status.INFO, "Opened Voot successfully in -browser " + bType);
	}
	
	public static FileReader loadPropertyFile(String propfilename) throws Exception {
		prop = new Properties();
		String propUrl = System.getProperty("user.dir") +"\\src\\com\\Blackthron\\web\\properties\\" + propfilename;
		file = new File(propUrl);
		FileReader reader = new FileReader(file);
		return reader;
	}
	public void initializeReportClassLevel(String executionType) throws UnknownHostException {
		UtilitiesWeb.createFolder(ConstantsWeb.REPORT_PATH+"//Class//");
		report_filePath=ConstantsWeb.REPORT_PATH+"//Class//"+ConstantsWeb.browser+"_"+this.getClass().getSimpleName()+"_"+getTimeStamp()+".html";
		reporter = new ExtentHtmlReporter(report_filePath);
		reportConfigurations();
	}
	public void initializeReportSuiteLevel(String name) throws UnknownHostException {
		UtilitiesWeb.createFolder(ConstantsWeb.REPORT_PATH+"//Suite//");
		report_filePath=ConstantsWeb.REPORT_PATH+"//Suite//"+"Suite_"+ConstantsWeb.browser+"_"+name+"_"+".html";
		reporter = new ExtentHtmlReporter(report_filePath);
		reportConfigurations();
		
	
		
	}
	public void reportConfigurations() throws UnknownHostException {
		reporter.setAppendExisting(true);
	    reporter.config().setChartVisibilityOnOpen(true);
		reporter.config().setDocumentTitle("BrokerPortal_Web "+suiteName+ConstantsWeb.browser);
		reporter.config().setProtocol(Protocol.HTTPS);
		reporter.config().setEncoding("UTF-8");
		reporter.config().setTheme(Theme.DARK);
		reporter.config().setReportName("Suite_"+ConstantsWeb.browser+getTimeStamp());		
		report= new ExtentReports();
		report.attachReporter(reporter);
		report.setReportUsesManualConfiguration(true);
		
			systemAddress = Inet4Address.getLocalHost();
				
		report.setSystemInfo("IP Address",systemAddress.getHostAddress() );
		report.setSystemInfo("Host Name",systemAddress.getHostName() );
		report.setSystemInfo("UserName",System.getProperty("user.name") );
		report.setSystemInfo("Operating System", System.getProperty("os.name"));
	}
	
	public void killProcess() throws IOException {
		if (driver != null) {
			if (ConstantsWeb.OS.equalsIgnoreCase("windows")) {
				//driver.quit();
				try {driver.quit();
				String cmds[] = {"kill all","Google Chrome"};
				Runtime.getRuntime().exec(cmds);
				} catch (Exception e) {
				}
			}
			if(ConstantsWeb.OS.equals("Mac")){
				try {driver.quit();
				String cmds[] = {"kill all","Google Chrome"};
				Runtime.getRuntime().exec(cmds);
				} catch (Exception e) {
				}
			}
		}
	}
	public static String getTimeStamp() {
		Date d= new Date();
		return d.toString().replace(":", "_").replace(" ", "_");
	}
}

package com.BlackthronWeb.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Blackthron.web.utils.UtilitiesWeb;
import com.aventstack.extentreports.Status;


public class DashBoardPage extends BasePageWeb
{
	public DashBoardPage(WebDriver driver) 
	{
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	 @FindBy(xpath="//div[text()='Navigation']//..//a") //span [@title='Mortgage Rates']
	 public List<WebElement> AllTabs;
	 
	
	 @FindBy(xpath="//h1 [@class='paddingValue setHeader mediaHide']")
	 public WebElement welcomeMessage;
	 
	 @FindBy(xpath="//a [text()='View all deals']")
	 public WebElement viewAllDeals;
	 
	 @FindBy(xpath="(//div //a[@class='viewAllUpdates'])[2]")
	 public WebElement viewAllUpdates;
	 
	 @FindBy(xpath="//h1 [text()='Most Recent Deals']")
	 public WebElement mostRecentDealsHeader;
	
	 @FindBy(xpath="//div [contains(text(),'My Active Pipeline')]")
	 public WebElement myActivePipeline;
	 
	 @FindBy(xpath="//div [contains(text(),'My YTD Activity')]")
	 public WebElement myYTDActivity;
	 
	 @FindBy(xpath="//div [contains(text(),'My YTD Funding Report')]")
	 public WebElement myYTDFundingReport;
	 
	 @FindBy(xpath="//div [contains(text(),'My Submission Ratios')]")
	 public WebElement mySubmissionRatios;
	 
	 @FindBy(xpath="//div [@class='logo cBrokerDashboardLogo']//img")
	 public WebElement portalLogo;
	 
	 @FindBy(xpath="//div [@class='promoBanners displayForDesktop']//img")
	 public WebElement portalPromoBanner;
	 
	 @FindBy(xpath="//div [contains(text(),'Team Active Pipeline')]")
	 public WebElement teamActivePipeline;
	 
	 @FindBy(xpath="//div [contains(text(),'Team YTD Activity')]")
	 public WebElement teamYTDActivity;
	 
	 @FindBy(xpath="//div [contains(text(),'Team YTD Funding Report')]")
	 public WebElement teamYTDFundingReport;
	 
	 @FindBy(xpath="//div [contains(text(),'Team Submission Ratios')]")
	 public WebElement teamSubmissionRatios;
	 
	 @FindBy(xpath="//table//a [@class='styleDeal']")
	 public List<WebElement> mostRecentDeals;
	 
	 @FindBy(xpath="//h1 [@class='setFooterPad']")
	 public WebElement pageFooterMessage;
	 
	 @FindBy(xpath="//*[local-name()='svg' and @data-key='notification']")
	 public WebElement bellNotificationIcon;
	 
	 @FindBy(xpath="//div [@class='searchContainer']//div//input")
	 public WebElement globalSearch;
	 
	 @FindBy(xpath="//div [@data-select='single']//div//div//button")
	 public WebElement globalSearchButton;
	
	 @FindBy(xpath="//td//a")
	 public WebElement globalSearchMortgageResults;
	 
	 @FindBy(xpath="//div[@title=' Property Address' or  text()='Property Address']")
	 public WebElement propertyAddress;
	 
	 @FindBy(xpath="//div[text()='Broker Name']")
	 public WebElement brokerName;
	 
	 @FindBy(xpath = "//a[text()='My Profile']")
	 public WebElement My_profile;

	 @FindBy(xpath = "//article [@class='slds-card cardDetails cUpdateCardDetails']//b [@class='text']")
	 public List<WebElement> recentUpdates;
	 
	 @FindBy(xpath="//ul [@class='navContainer']//a")
	 public List<WebElement> Available_Tabs;
	 	 
	 @FindBy(xpath="//div [@title='Deal#']")
	 public WebElement dealNumber; 
	 
	 @FindBy(xpath="//div [@title='Status']")
	 public WebElement status; 
	 
	 @FindBy(xpath="//div [@title='Borrower name']")
	 public WebElement borrowerName; 
	 
	 @FindBy(xpath="//div [@title=' Property Address']")
	 public WebElement mostrecentdealpropertyAddress; 
	 
	 @FindBy(xpath="//div [@title='Anticip. Fund Date']")
	 public WebElement AnticipatedFundDate;
	 
	 @FindBy(xpath=" (//tr//th)[4]")
	 public WebElement mouseHoverPropertyAddress;
	
	 

	 /*
	   else
	   {
		   if(dashboard.brokerName.isDisplayed())
		   {
	   test.log(Status.INFO, " Logged in User is belongs to the Master Broker Profile");
			   UtilitiesWeb.scrollTop(driver);
			   Thread.sleep(5000);
		String attribute = dashboard.brokerName.getAttribute("title");
			   int x = dashboard.brokerName.getLocation().getX();
			   int y = dashboard.brokerName.getLocation().getY();
		  action.moveToElement(dashboard.brokerName, x, y).perform();
			           Thread.sleep(2000);
					   UtilitiesWeb.mouseHover(driver, dashboard.brokerName);   
					   Thread.sleep(1000);
  if( attribute.contentEquals("Property Address") || attribute.contains("Property Address"))
			   {
				   logScreenShot(driver, test, "");
			 test.log(Status.ERROR, " On Mouse Over Broker Name its Displaying "+ attribute);
			   }
		   }
		   else
		   {
			   logScreenShot(driver, test, "");
		 test.log(Status.FAIL, "Broker Name Column is not availabe for Master Broker user"); 	   
		   }
	   }
	 	 
	  */
	 
}

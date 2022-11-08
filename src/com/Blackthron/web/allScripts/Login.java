package com.Blackthron.web.allScripts;

import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.Blackthron.web.sanityscripts.BaseTestWeb;
import com.Blackthron.web.utils.Application_Utilities;
import com.Blackthron.web.utils.ConstantsWeb;
import com.Blackthron.web.utils.UtilitiesWeb;
import com.BlackthronWeb.pages.BasePageWeb;
import com.BlackthronWeb.pages.DashBoardPage;
import com.BlackthronWeb.pages.EventsPage;
import com.BlackthronWeb.pages.LoginPage;
import com.aventstack.extentreports.Status;
import com.BlackthronWeb.pages.LoginPage;


public class Login extends BaseTestWeb{
	


	/**
	 * @throws Exception
	 */
	@Test (priority=1)
	public void Lauchbrowser() throws Exception
	{
		try {
		launchWeb(ConstantsWeb.OS, ConstantsWeb.browser);
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		reader=loadPropertyFile("credentials.properties");
		prop.load(reader);
		LoginPage login= new LoginPage(driver);
		EventsPage eventpage= new EventsPage(driver);
		ConstantsWeb.UserName=prop.getProperty("username");
		ConstantsWeb.password=prop.getProperty("password");
		login.Login(ConstantsWeb.UserName, ConstantsWeb.password);
		//driver.get("https://raagvitechcom4-dev-ed.lightning.force.com/");
		Thread.sleep(3000);	
		Application_Utilities.TabbedUIevent();Q
		
 	 // Creating Event Item
 	 
 	eventpage.Event_Item.click();
 	eventpage.Eventitem_New.click();
 	eventpage.Eventitem_name.click();
 	String randomname2 = UtilitiesWeb.Randomname(5);
 	eventpage.Eventitem_name.sendKeys(randomname2);
 	eventpage.Eventitem_name.click();
 	eventpage.Save_eventitem.click();
 	Thread.sleep(3000);
 	 eventpage.eventName.click();
 	 String event_url= driver.getCurrentUrl();
 	eventpage.Publish.click();
 	Thread.sleep(3000);
 	UtilitiesWeb.scroll_to_particular_element(eventpage.Scroll_Eventlink);
 	UtilitiesWeb.scrollUp200(driver);
 	JavascriptExecutor executor = (JavascriptExecutor)driver;
 	executor.executeScript("arguments[0].click();", eventpage.EventReglink);
 	UtilitiesWeb.wait_until_the_page_is_loaded();
 	UtilitiesWeb.hardRefresh(driver);
 	//System.out.println("pointer is here 1");
 	UtilitiesWeb.wait_until_the_page_is_loaded();
 	
 	String mainWindow=driver.getWindowHandle();
 	Set<String> set =driver.getWindowHandles();
 	Iterator<String> itr= set.iterator();
 	while(itr.hasNext()){
 	String childWindow=itr.next();
 	if(!mainWindow.equals(childWindow)){
 		driver.switchTo().window(childWindow); //Switching to 2nd Tab
 	}
 	}

 	//driver.switchTo().window(mainWindow);
 	Thread.sleep(5000);
 	eventpage.RegisterButton.click();
 	
 	eventpage.nooftickets.click();
 	eventpage.selecttickets.click();
 	eventpage.checkout.click();
 	
 	eventpage.attendeefirstname.click();
 	String attendeename1 = UtilitiesWeb.Randomname(5);
 	eventpage.attendeefirstname.sendKeys(attendeename1);
 	
 	eventpage.attendeelaststname.click();
 	String attendeename2 = UtilitiesWeb.Randomname(5);
 	eventpage.attendeelaststname.sendKeys(attendeename2);
 	
 	eventpage.attendeeEmail.click();
 	String attendeeemail= UtilitiesWeb.generateEmailid();
 	eventpage.attendeeEmail.sendKeys(attendeeemail);
 	
 	UtilitiesWeb.wait_until_the_page_is_loaded();
 	eventpage.registerbtn.click();
 	
 	UtilitiesWeb.wait_until_the_page_is_loaded();
 	eventpage.registerbtn.click();
 	
 	UtilitiesWeb.wait_until_the_page_is_loaded();
 	Thread.sleep(5000);
 	//System.out.println(eventpage.success.getText());
 	if(eventpage.success.getText().equals("Your order is complete!")) {
 		System.out.println("Sucessfull");
 		eventpage.Done_button.click();
 	}
 	else {
 		System.out.println("Failed");
 	}
 	driver.switchTo().window(mainWindow);
 	//eventpage.ERS.click();
 	UtilitiesWeb.wait_until_the_page_is_loaded();
 	
 	String ERS_tab=str[0]+"/lightning/o/conference360__Event_Registration_Submission__c/list?filterName=Recent";
 	driver.get(ERS_tab);
 	UtilitiesWeb.wait_until_the_page_is_loaded();
 	eventpage.ers_record.click();
 	UtilitiesWeb.wait_until_the_page_is_loaded();
 	//Thread.sleep(3000);
 	//UtilitiesWeb.ScrollTillPageEnd(driver);
 	UtilitiesWeb.scroll_to_particular_element(eventpage.ers_status);
 	System.out.println("Pointer here");
 	
 
 	//System.out.println(eventpage.to_process_status.isEnabled());
// 	if(eventpage.to_process_status.isDisplayed())
// 	{
// 		System.out.println("In Process");
// 		for(int i=0;i<5;i++)
// 		{
// 			UtilitiesWeb.hardRefresh(driver);
// 		}
// 	}
// 	else if(eventpage.draft_status.isDisplayed())
// 	{
// 		System.out.println("Draft");
// 		for(int i=0;i<5;i++)
// 		{
// 			UtilitiesWeb.hardRefresh(driver);
// 		}
// 	}
// 	else if(eventpage.completed_status.isDisplayed())
// 	{
// 		System.out.println("Completed");
//		Assert.assertEquals(eventpage.completed_status.getText(),ConstantsWeb.ERS_Completed);
// 	}
// 	else
// 	{
// 		Assert.assertFalse(false);
// 	}
 
 	driver.navigate().to(event_url);
 	UtilitiesWeb.wait_until_the_page_is_loaded();
 	eventpage.event_Attendees.click();
 	UtilitiesWeb.wait_until_the_page_is_loaded();
 	eventpage.reg_attendee.click();
 	UtilitiesWeb.wait_until_the_page_is_loaded();
 	System.out.println(eventpage.attendee_status.isDisplayed());
 	
 	
 	
 	
 	
 	
 	
 	
 	
 		
 	
 	
 
 	
 
		
		}
		catch(Exception e)
		{
		test.log(Status.FAIL, e.getMessage()); 
		}		
	}
}


	
	
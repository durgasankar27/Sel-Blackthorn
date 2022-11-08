package com.Blackthron.web.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

import com.BlackthronWeb.pages.EventsPage;



public class Application_Utilities extends Base_Test_Web_Utils {
	
	
	//This method is for Creating Event with Simple UI
	public static void SimpleUIevent() {
	
		EventsPage eventpage= new EventsPage(driver);
		eventpage.applauncher.click();
		UtilitiesWeb.wait_until_the_page_is_loaded();
		eventpage.searchBar.sendKeys("Events (Admin)");
		eventpage.EventAdmin.click();
		UtilitiesWeb.wait_until_the_page_is_loaded();
		Actions action = new Actions(driver);
		action.moveToElement(eventpage.Eventstab).click().perform();
		UtilitiesWeb.wait_until_the_page_is_loaded();
		eventpage.eventspagenewbutton.click();
		UtilitiesWeb.wait_until_the_page_is_loaded();
		eventpage.Eventname.click();
		eventpage.Eventname.sendKeys(UtilitiesWeb.Randomname(5));
		eventpage.uiexperience.click();
		eventpage.simpleui.click();
		UtilitiesWeb.scroll_to_particular_element(eventpage.Dates);
		eventpage.startdate.click();
	 	eventpage.startdate.sendKeys("Dec 29, 2021");
	 	eventpage.enddate.click();
	 	eventpage.enddate.sendKeys("Dec 29, 2030");
	 	
	 	eventpage.start_time.click();
	 	eventpage.start_time.sendKeys("12:00 AM");
	 	
	 	eventpage.end_time.click();
	 	eventpage.end_time.sendKeys("12:00 AM");
	 	 eventpage.Save_button.click(); 
		
	}
	
	//This method is for Creating Event with tabbed UI
		public static void TabbedUIevent() {
		
			EventsPage eventpage= new EventsPage(driver);
			eventpage.applauncher.click();
			UtilitiesWeb.wait_until_the_page_is_loaded();
			eventpage.searchBar.sendKeys("Events (Admin)");
			System.out.println("Ismail Innocent");
			eventpage.EventAdmin.click();
			UtilitiesWeb.wait_until_the_page_is_loaded();
			Actions action = new Actions(driver);
			action.moveToElement(eventpage.Eventstab).click().perform();
			UtilitiesWeb.wait_until_the_page_is_loaded();
			eventpage.eventspagenewbutton.click();
			UtilitiesWeb.wait_until_the_page_is_loaded();
			eventpage.Eventname.click();
			eventpage.Eventname.sendKeys(UtilitiesWeb.Randomname(5));
			eventpage.uiexperience.click();
			eventpage.tabbedui.click();
			UtilitiesWeb.scroll_to_particular_element(eventpage.Dates);
			eventpage.startdate.click();
		 	eventpage.startdate.sendKeys("Dec 29, 2021");
		 	eventpage.enddate.click();
		 	eventpage.enddate.sendKeys("Dec 29, 2030");
		 	
		 	eventpage.start_time.click();
		 	eventpage.start_time.sendKeys("12:00 AM");
		 	
		 	eventpage.end_time.click();
		 	eventpage.end_time.sendKeys("12:00 AM");
		 	 eventpage.Save_button.click(); 
			
		}
		
		//This method is for Creating Event with Full Width UI
		public static void FullwidthUIevent() {
			
			EventsPage eventpage= new EventsPage(driver);
			eventpage.applauncher.click();
			UtilitiesWeb.wait_until_the_page_is_loaded();
			eventpage.searchBar.sendKeys("Events (Admin)");
			System.out.println("Ismail Innocent");
			eventpage.EventAdmin.click();
			UtilitiesWeb.wait_until_the_page_is_loaded();
			Actions action = new Actions(driver);
			action.moveToElement(eventpage.Eventstab).click().perform();
			UtilitiesWeb.wait_until_the_page_is_loaded();
			eventpage.eventspagenewbutton.click();
			UtilitiesWeb.wait_until_the_page_is_loaded();
			eventpage.Eventname.click();
			eventpage.Eventname.sendKeys(UtilitiesWeb.Randomname(5));
			eventpage.uiexperience.click();
			eventpage.fullwidthui.click();
			UtilitiesWeb.scroll_to_particular_element(eventpage.Dates);
			eventpage.startdate.click();
		 	eventpage.startdate.sendKeys("Dec 29, 2021");
		 	eventpage.enddate.click();
		 	eventpage.enddate.sendKeys("Dec 29, 2030");
		 	
		 	eventpage.start_time.click();
		 	eventpage.start_time.sendKeys("12:00 AM");
		 	
		 	eventpage.end_time.click();
		 	eventpage.end_time.sendKeys("12:00 AM");
		 	 eventpage.Save_button.click(); 
			
		}

}

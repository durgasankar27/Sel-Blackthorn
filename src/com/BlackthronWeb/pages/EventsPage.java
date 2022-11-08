package com.BlackthronWeb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EventsPage {
	
	
	
	public EventsPage(WebDriver driver)
	{
		
		PageFactory.initElements(driver, this);
	 }
	
	
	@FindBy(xpath="//span[contains(@title,'(Admin)')]")
	 public WebElement EventforAdmin;
	
	@FindBy(xpath="//div[@class= 'slds-icon-waffle']")
	 public WebElement applauncher;
	
	@FindBy(xpath="//b[contains(text(),'Events (Admin)')]")
	public WebElement EventAdmin;
	
	@FindBy(xpath="//a[@title='Events']/span[contains(text(),'Events')]")
	public WebElement Eventstab;

	@FindBy(xpath="//span [@title='Events (Planner)']")
	 public WebElement planner;
	
	@FindBy(xpath="//span[@title='Events (Admin)']")
	 public WebElement Admin;
	
	
	@FindBy(xpath="//div [@class='slds-form-element__control slds-grow slds-input-has-icon slds-input-has-icon_left-right']/child::input")
	 public WebElement searchBar;
	
	@FindBy(xpath="(//div [@class='slds-form-element__control slds-grow slds-input-has-icon slds-input-has-icon_left-right']/child::input)[2]")
	 public WebElement plannerSearchbar;
	
	@FindBy(xpath="//div [text()='New']")
	 public WebElement eventspagenewbutton;
	
	@FindBy(xpath="//input[@name='Name']")
	 public WebElement Eventname;
	
	@FindBy(xpath="//label[text()='UI Experience']")
	public WebElement uiexperience;
	
	@FindBy(xpath="//span[text()='Tabbed']")
	public WebElement tabbedui;
	
	@FindBy(xpath="//span[text()='Simple']")
	public WebElement simpleui;
	
	@FindBy(xpath="//span[text()='Full-Width']")
	public WebElement fullwidthui;
	
	@FindBy(xpath="//span[text()='Dates']")
	 public WebElement Dates;
	
	 @FindBy(xpath="//input[@name='conference360__Event_Start_Date__c']")
	 public WebElement startdate;
	
	  @FindBy(xpath="//input[@name='conference360__Event_End_Date__c']")
	 public WebElement enddate;
	
	 @FindBy(xpath="//input[@name='conference360__Event_Start_Time__c']")
	 public WebElement start_time;
	
	 @FindBy(xpath="//input[@name='conference360__Event_End_Time__c']")
	 public WebElement end_time;
	
	   @FindBy(xpath="//button[@name='SaveEdit']")
	 public WebElement Save_button;
	
	   @FindBy(xpath="//span[text()='Event Items']")
	 public WebElement Event_Item;
	
	   	@FindBy(xpath="(//div[@title='New'])[2]")
	 public WebElement Eventitem_New;
	
	
	   	@FindBy(xpath="//input[@name='conference360__Item_Name__c']")
	 public WebElement Eventitem_name;
	
	
	@FindBy(xpath="//button[@name='SaveEdit']")
	 public WebElement Save_eventitem;
	
	 @FindBy(xpath="(//a[@title='Events'])[3]")
	 public WebElement Event_itempage;
	
		@FindBy(xpath="(//a[@title='Events'])[2]")
	 public WebElement Event_EventItempage;
		
	 @FindBy(xpath="(//li[@class='slds-breadcrumb__item slds-line-height--reset'])[2]/a")
		public WebElement eventName;
	 
		
	 @FindBy(xpath="(//button[text()='Publish'])[2]")
	 public WebElement Publish;
	 
	  @FindBy(xpath="//span[text()='URLs & Info']")
	 public WebElement Scroll_Eventlink;
	  
	  @FindBy(xpath="//a [contains(text(),'https://events.blackthorn.io/')]")
		 public WebElement EventReglink;
	  
	  @FindBy(xpath="//span[text()='Register']")
		 public WebElement RegisterButton;
	  
	  @FindBy(xpath="//div[@class='mat-select-value ng-tns-c125-4']")
	  	public WebElement nooftickets;
	  
	  @FindBy(xpath="(//span[@class='mat-option-text'])[2]")
	  	public WebElement selecttickets;
	  
	  @FindBy(xpath="(//span[text()='checkout'])[1]")
	  	public WebElement checkout;
	  
	  @FindBy(xpath="//input[@name='firstName']")
		public WebElement attendeefirstname;
	  
	  @FindBy(xpath="//input[@name='lastName']")
		public WebElement attendeelaststname;
	  
	  @FindBy(xpath="//input[@name='email']")
		public WebElement attendeeEmail;
	  
	  @FindBy(xpath="//span[text()='register']")
	  	public WebElement registerbtn;
	  
	  @FindBy(xpath="//h1[contains(text(),'Your order is complete')]")
	  	public WebElement success;
	  @FindBy(xpath="//span[contains(text(),'DONE')]")
	  	public WebElement Done_button;
	  
	  @FindBy(xpath="//a[@title='Event Registration Submissions']")
	  	public WebElement ERS;
	  
	  @FindBy(xpath="//a[contains(text(),'ER-')][1]")
	  	public WebElement ers_record;
	  @FindBy(xpath="(//span[text()='Status'])[2]")
	  	public WebElement ers_status;
	  
	  @FindBy(xpath="//lightning-formatted-text[contains(text(),'Completed')]")
	  	public WebElement completed_status;
	  
	  @FindBy(xpath="//lightning-formatted-text[contains(text(),'Draft')]")
	  	public WebElement draft_status;
	  
	  @FindBy(xpath="//lightning-formatted-text[contains(text(),'To Process')]")
	  	public WebElement to_process_status;
	  @FindBy(xpath="//span[@title='Attendees']")
	  	public WebElement event_Attendees;
	  
	  @FindBy(xpath="(//a[contains(text(),'AT-')])[1]")
	  	public WebElement reg_attendee;
	  @FindBy(xpath="//lightning-formatted-text[contains(text(),'Registered')]")
	  	public WebElement attendee_status;
	  
	  
	
	
	  //span[text()='URLs & Info']     //span[text()='Event Page URL']
	 
//	   @FindBy(xpath="(//span[text()='Register'])[3]")
//	 public WebElement Register_Button;
	 
	   /* @FindBy(xpath="//div[@class='slds-icon-waffle']/parent::button")
	 public WebElement applauncher;
	 
	 @FindBy(xpath="//div[@class='slds-icon-waffle']/parent::button")
	 public WebElement applauncher;
	 
	 @FindBy(xpath="//div[@class='slds-icon-waffle']/parent::button")
	 public WebElement applauncher;
	 
	 @FindBy(xpath="//div[@class='slds-icon-waffle']/parent::button")
	 public WebElement applauncher;
	 
	 
	 @FindBy(xpath="//div[@class='slds-icon-waffle']/parent::button")
	 public WebElement applauncher;
	 
	 @FindBy(xpath="//div[@class='slds-icon-waffle']/parent::button")
	 public WebElement applauncher;
	 
	 @FindBy(xpath="//div[@class='slds-icon-waffle']/parent::button")
	 public WebElement applauncher;
	 
	 @FindBy(xpath="//div[@class='slds-icon-waffle']/parent::button")
	 public WebElement applauncher;
	 
	 @FindBy(xpath="//div[@class='slds-icon-waffle']/parent::button")
	 public WebElement applauncher;
	 
	 @FindBy(xpath="//div[@class='slds-icon-waffle']/parent::button")
	 public WebElement applauncher;
	
	*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

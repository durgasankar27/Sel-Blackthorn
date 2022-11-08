package com.BlackthronWeb.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Blackthron.web.sanityscripts.BaseTestWeb;

public class LoginPage extends BaseTestWeb
{
	public LoginPage(WebDriver driver)
	{
		
		PageFactory.initElements(driver, this);
	 } 
	
	@FindBy(xpath="//input [@id='username']")
	 public WebElement username;

	@FindBy(xpath="//input [@id='password']")
	 public WebElement Password;
	
	@FindBy(xpath="//input [@name='Login']")
	 public WebElement loginbutton;
	
	
	public Object Login(String Username, String password)
	{
		
		//if(UtilitiesWeb.explicitWaitClickable(driver, username, 20)) {
		
			username.clear();
			username.sendKeys(Username);
		//}
		//else {
		
		//	BaseTestWeb.mainTest.log(BaseTestWeb.status.FAIL, "Username field is not Found in Login Page");
	   //}
		
		
	   //if(UtilitiesWeb.explicitWaitClickable(driver, Password, 20)) {
			Password.clear();
            Password.sendKeys(password);
           	
            //	}
	//	else {
		
	//	BaseTestWeb.mainTest.log(BaseTestWeb.status.FAIL, "Password field is not Found in Login Page");
			//	}
			
            //	if(UtilitiesWeb.explicitWaitClickable(driver, loginbutton, 20)) {
			
			loginbutton.click();
			BaseTestWeb.mainTest.log(BaseTestWeb.status.INFO, "Clicking on Login Button");
			//}
			//else {
		
			// BaseTestWeb.mainTest.log(BaseTestWeb.status.FAIL, "LoginButton field is not Found in Login Page");
     //	}
		
		
		
		
		DashBoardPage DashboardPage= new DashBoardPage(driver);
		return DashboardPage;	
	}
	
	
	
}

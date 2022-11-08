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


public class class1 extends BaseTestWeb{
	


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
		Thread.sleep(3000);	
		Application_Utilities.TabbedUIevent();	
		}
		catch(Exception e)
		{
		test.log(Status.FAIL, e.getMessage()); 
		}		
	}
}


	
	
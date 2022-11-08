package com.Blackthron.web.utils;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Blackthron.web.sanityscripts.BaseTestWeb;




/*************************************************************************************
 * Class : Utilities Purpose : This class is used for reusable common functions across
 * the framework Remarks :  Author :Raagvitech
 * 
 **************************************************************************************/
public class UtilitiesWeb {

	//Wait for the page to Load
	public static void wait_until_the_page_is_loaded() {
		Object status = "";

		while (!status.toString().equalsIgnoreCase("complete")) {

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			JavascriptExecutor js = (JavascriptExecutor) Base_Test_Web_Utils.driver;
			status = js.executeScript("return document.readyState");
		}
	}
	
	//Creation of random name
	public static String Randomname(int len) 
	{
		String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijk"
          +"lmnopqrstuvwxyz!@#$%&";
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(chars.charAt(rnd.nextInt(chars.length())));
		return sb.toString();
	}
	
	//Mouse Hover
	public static void mouseHove(WebDriver driver, WebElement HoverElement) {
		Actions act =new Actions(driver);
		int x=HoverElement.getLocation().getX();
		int y=HoverElement.getLocation().getY();
		try {
//			act.moveToElement(HoverElement).build().perform();
			act.moveByOffset(x, y).build().perform();
		} catch (Exception e) {
			act.moveByOffset(x, y).build().perform();
//			act.moveToElement(HoverElement).build().perform();
		} 
	}
	
//	public static void OpenEventItem( WebDriver driver,WebElement element, String Name) 
//	{
//		try {
//			driver.findElement(By.xpath("(//a [text()='"+Name+"'])[2]")).click();
//	
//	//	Base_Test_Web_Utils.driver.findElement(By.xpath("(//nav[@role='navigation']//a [@title='"+Name+"'])[2]")).click();
//		
//		System.out.println("(//nav[@role='navigation']//a [@title='"+Name+"'])[2]");
//		} catch (Exception e) {
//			e.getMessage();
//		}
//	}
	
	//Mouse Hover Using java script
	public static void mouseHoverJScript(WebDriver driver, WebElement HoverElement) {
		try {
			if (isElementPresent(HoverElement)) {
				
				String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
				((JavascriptExecutor) driver).executeScript(mouseOverScript, HoverElement);

			} else {
				System.out.println("Element was not visible to hover " + "\n");

			}
		} catch (StaleElementReferenceException e) {
			System.out.println("Element with " + HoverElement
					+ "is not attached to the page document"
					+ e.getStackTrace());
		} catch (NoSuchElementException e) {
			System.out.println("Element " + HoverElement + " was not found in DOM"
					+ e.getStackTrace());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error occurred while hovering"
					+ e.getStackTrace());
		}
	}
	
	//
	public static boolean isElementPresent(WebElement element) {
		boolean flag = false;
		try {
			if (element.isDisplayed()
					|| element.isEnabled())
				flag = true;
		} catch (NoSuchElementException e) {
			flag = false;
		} catch (StaleElementReferenceException e) {
			flag = false;
		}
		return flag;
	}
	
	
	//Wait for 5 seconds
	public static void waitForAwhile() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//Scrolling to particular Element
	public static void scroll_to_particular_element(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) Base_Test_Web_Utils.driver;
		js.executeScript("arguments[0].scrollIntoView();", element);
	}
	
	//Select value from List
	public static void selectValue_from_listBox(List<WebElement> elements, String input, int timeout) {
		BaseTestWeb.logger.info("Clicking on the element " + elements.toString());
		if (elements == null) {
			
			System.out.println("No element is found with locator:" + elements.toString());
		} else {
			for (WebElement option : elements) {
				if (option.getText().equalsIgnoreCase(input)) {
					option.click();
				} else {
					System.out.println();
				}
			}
		}
	}

	//Time Stamp
	public static String getTimeStamp_sec() {
		String timeStamp = "";
		Date d = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		timeStamp = timeStamp + c.get(Calendar.MONTH) + c.get(Calendar.DAY_OF_MONTH) + c.get(Calendar.HOUR)
				+ c.get(Calendar.MINUTE) + c.get(Calendar.SECOND);
		return timeStamp;
	}
	
     // this method is to  Move to Element and perform Click action 
	public static void move_to_element_and_click(WebElement element, int timeout) {

		BaseTestWeb.logger.info("Clicking on the element "+ element.toString());
		if (element == null) {
			//			Assert.assertFalse(true, "No element is found with locator:" + by.toString());
			System.out.println( "No element is found with locator:" + element.toString());
		} else {
			if (wait_until_element_is_visible(element, timeout)) {
				if (wait_until_elememt_is_enable(element, timeout)) {
					Actions act= new Actions(Base_Test_Web_Utils.driver);
					act.moveToElement(element).click().build().perform();
					BaseTestWeb.logger.info("Clicked on the element " + element.toString());
				} else {
					System.out.println("Element : " + element.toString() + " is not enabled even after waiting for "
							+ timeout + " secconds.");
				}
			} else {
				System.out.println("Element : " + element.toString() + " is not visible even after waiting for "
						+ timeout + " secconds.");
			}
		}
	}
	
	
	
	//Move to Element
	public static void move_to_element(WebElement element, int timeout) {

		BaseTestWeb.logger.info("Clicking on the element "+ element.toString());
		if (element == null) {
			//			Assert.assertFalse(true, "No element is found with locator:" + by.toString());
			System.out.println( "No element is found with locator:" + element.toString());
		} else {
			if (wait_until_element_is_visible(element, timeout)) {
				if (wait_until_elememt_is_enable(element, timeout)) {
					Actions act= new Actions(Base_Test_Web_Utils.driver);
					act.moveToElement(element);
					BaseTestWeb.logger.info("Moved to the element " + element.toString());
				} else {
					System.out.println("Element : " + element.toString() + " is not enabled even after waiting for "
							+ timeout + " secconds.");
				}
			} else {
				System.out.println("Element : " + element.toString() + " is not visible even after waiting for "
						+ timeout + " secconds.");
			}
		}
	}
	
	//Switch to Window
	public static void switch_to_latestWindow() {
		BaseTestWeb.logger.info("Switching to latest window");
		Set<String> windows = Base_Test_Web_Utils.driver.getWindowHandles();
		for (String winHandle : windows) {
			Base_Test_Web_Utils.driver.switchTo().window(winHandle);
			BaseTestWeb.logger.info("Switched to latest window");
		}
	}
	
	//Switch to window by Name
	public static boolean switchToWindowByName(String windowName) {
		BaseTestWeb.logger.info("Switching to the window :" + windowName);
		Set<String> allWindowHandles = Base_Test_Web_Utils.driver.getWindowHandles();
		boolean isWindowFound = false;
		for (String handle : allWindowHandles) {
			Base_Test_Web_Utils.driver.switchTo().window(handle);
			String windowTitle = Base_Test_Web_Utils.driver.getTitle();
			if (windowTitle.contains(windowName)) {
				isWindowFound = true;
				BaseTestWeb.logger.info("Switched to window :" + windowName);
				break;
			}
		}
		
		if (!isWindowFound) {

			System.out.println("Window : " + windowName + " does not exist.");
		}
		return isWindowFound;
	}

	//Alert Dismiss
	public static void to_dismiss_alert(int timeout) {
		WebDriverWait wait = new WebDriverWait(Base_Test_Web_Utils.driver, timeout);
		wait.until(ExpectedConditions.alertIsPresent());
		wait.pollingEvery(Duration.ofMillis(200));
		Base_Test_Web_Utils.driver.switchTo().alert().getText();
		Base_Test_Web_Utils.driver.switchTo().alert().dismiss();

	}
	
	//Click on Button
	public static void clickButton(WebElement element,int timeout) throws Exception {
		BaseTestWeb.logger.info("Clicking on the element "+ element.toString());
		try {
			if (element == null) {
				System.out.println( "No element is found with locator:" + element.toString());
			} else {
				if (wait_until_element_is_visible(element, timeout)) {
					if (wait_until_elememt_is_enable(element, timeout)) {

						element.click();
						BaseTestWeb.logger.info("Clicked on the element "+ element.toString());


					System.out.println("Element : " + element.toString() + " is not enabled even after waiting for"
							+ timeout + "secconds.");
				}
			} else {
				System.out.println("Element : " + element.toString() + " is not visible even after waiting for "
						+ timeout + "secconds.");
					System.out.println("Element : " + element.toString() + " is not visible even after waiting for "+timeout+ "secconds.");
				}
			}
		}catch (ElementClickInterceptedException ecie) {
			((JavascriptExecutor) Base_Test_Web_Utils.driver).executeScript("arguments[0].click();", element);
		}
	}
	
	//Enter value in field
	public static void enterValue(WebElement element, String input, int timeout) {
		BaseTestWeb.logger.info("Entering the value : " + input + " into the object : " + element.toString());
		if (element == null) {
			System.out.println("No element is found with locator:" + element.toString());
		} else {
			if (wait_until_element_is_visible(element, timeout)) {
				if (wait_until_elememt_is_enable(element, timeout)) {

					element.clear();

					BaseTestWeb.logger.info("Value  : " + input + "entered into the object : " + element.toString());

					element.sendKeys(input);

				} else {
					System.out.println(
							"Element : " + element.toString() + " is not enabled even after waiting for 20 secconds.");
				}
			} else {
				System.out.println(
						"Element : " + element.toString() + " is not visible even after waiting for 20 secconds.");
			}
		}
	}
	
	//Convert to minutes
	public static int convertToMinutes(String duration) {
		int timeAfterSplit = 0;
		String[] time = duration.split(":");
		String[] timeAtZero = time[0].trim().split("");
		int size = time.length;
		// System.out.println("size:"+size);
		if (size == 2) {

			timeAfterSplit = (Integer.parseInt(time[0].trim())) * 60 + Integer.parseInt(time[1].trim());
		} else if (size == 3) {
			int secondToMinute = (Integer.parseInt(time[2].trim())) / 60;
			if (timeAtZero[0].contains("0")) {
				timeAfterSplit = (Integer.parseInt(timeAtZero[1])) * 60 + Integer.parseInt(time[1].trim())
						+ secondToMinute;
			} else {
				timeAfterSplit = (Integer.parseInt(time[0])) * 60 + Integer.parseInt(time[1].trim()) + secondToMinute;
			}
		}
		return timeAfterSplit;
	}

	
	//Explicit wait until the Element is visible
	public static boolean explicitWaitVisible(WebDriver driver, WebElement element, int time) {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		try {
			WebDriverWait wait = new WebDriverWait(driver, time);
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
		return true;
	}

	//Scroll to End
	public static void scrollTillEnd(WebDriver driver) throws Exception {
		for (int i = 0; i < 30; i++) {
			try {
				((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
				Thread.sleep(500);
			} catch (Exception e) {
				break;
			}
		}

	}

	/* 
	 public static void timeconverstionmovie(String playerTime, int durinpercentage, String percentage) {
		String[] durs = playerTime.split("\\ \\/\\ ");
		String[] mins = durs[1].split("\\:");
		String hr = mins[0].toString();
		int shrtoint = Integer.parseInt(hr);
		String min = mins[1].toString();
		int smintoint = Integer.parseInt(min);
		int smintosec = shrtoint * 60 * 60 + smintoint * 60 + (Integer.parseInt(mins[1].toString()) + 30);
		System.out.println("Total Duration of the cotent in secs: " + smintosec);
		int Percent = (smintosec * durinpercentage) / 100;
		System.out.println(percentage + " Duration of the cotent in secs: " + Percent);

		GlobalVariablesWeb.totalwaitingTime = (Percent * 1000);
		System.out.println("Total waiting time for " + percentage + " of the content in millisecond is "
				+ GlobalVariablesWeb.totalwaitingTime);
	}  
	*/

	//Capture Screenshot with Name
	public String captureScreenshotWithName(WebDriver driver, boolean screenshotToFile) throws IOException {

		String screenShotFilePath = "";
		String screenShotName = "";
		String returnString = "";
		BaseTestWeb baseTest = new BaseTestWeb();
		try {
			if (!screenshotToFile) {
				TakesScreenshot ts = (TakesScreenshot) driver;
				String image = ts.getScreenshotAs(OutputType.BASE64);
				// return image;
			} else if (screenshotToFile) {
				if (ConstantsWeb.OS.equalsIgnoreCase("Windows")) {
					screenShotFilePath = baseTest.screenShotFilePath + baseTest.className + "_" + baseTest.hour + "\\";
				}

				else if (ConstantsWeb.OS.equalsIgnoreCase("Mac")) {
					screenShotFilePath = baseTest.screenShotFilePath + baseTest.className + "_" + baseTest.hour + "\\";
				}

				createFolder(screenShotFilePath);
				screenShotName = baseTest.methodName + "_" + getTimeStamp_sec() + ".png";
				TakesScreenshot ts = (TakesScreenshot) driver;
				File ScreenShot = ts.getScreenshotAs(OutputType.FILE);
				String destpath = "";

				destpath = screenShotFilePath + screenShotName;

				File destPath = new File(destpath);

				FileUtils.moveFile(ScreenShot, destPath);

				returnString = destpath;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnString;
	}
	
	/* 
	  public String createResizedCopy(String base64String, int scaledWidth, int scaledHeight, boolean preserveAlpha) {
	 
		try {
			@SuppressWarnings("restriction")
			BASE64Decoder decoder = new BASE64Decoder();
			byte[] decodedBytes = decoder.decodeBuffer(base64String);
			BufferedImage originalImage = ImageIO.read(new ByteArrayInputStream(decodedBytes));
			// System.out.println("resizing...");
			int imageType = preserveAlpha ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
			BufferedImage scaledBI = new BufferedImage(scaledWidth, scaledHeight, imageType);
			Graphics2D g = scaledBI.createGraphics();
			if (preserveAlpha) {
				g.setComposite(AlphaComposite.Src);
			}
			g.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);

			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ImageIO.write(scaledBI, "gif", bos);
			String imageString = Base64.getEncoder().encodeToString(bos.toByteArray());
			/*
			 * byte[] imageBytes = bos.toByteArray(); BASE64Encoder encoder = new
			 * BASE64Encoder(); String imageString = encoder.encode(imageBytes);
			 */
			// System.out.println(imageString);
		/*	g.dispose();
			return imageString;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
*/
	//Scroll to Bottom
	public static void scrollBottom(WebDriver driver) throws InterruptedException {

		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(2000);
	}
	
	//Explicit wait until the Element is Clickable
	public static boolean explicitWaitClickable(WebDriver driver, WebElement element, int time) {
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		try {
			WebDriverWait wait = new WebDriverWait(driver, time);

			wait.until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception e) {
			return false;
		} finally {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
		return true;
	}

	//Display of element
	public static boolean isDisplayed(WebDriver driver, WebElement element) {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		try {
			for (int i = 0; i < 2; i++) {
				if (element.isDisplayed())
					break;
			}
		}
		catch (Exception e) {
			return false;
		} finally {
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		}
		return true;
	}

	//Generate random Email
	public static String generateEmailid() {
		String strRandom = "";
		String strNumbers = "abcdefghijklmnopqrstuvwxyzacvbeewfde";
		Random rnd = new Random();
		StringBuilder strRandomNumber = new StringBuilder(9);
		for (int i = 0; i < 4; i++) {
			strRandomNumber.append(strNumbers.charAt(rnd.nextInt(strNumbers.length())));
		}
		strRandom = strRandomNumber.toString();
		String email = strRandom + "ba" + "@" + "gmail" + ".com";
		return email;
	}
	
	//Generate Random Password
	public static String generatePwd() {
		String strRandom = "";
		String strAlpha = "abcdefghijklmnopqrstuvwxyzacvbe";
		String strNumerics = "0123456789";
		Random rnd = new Random();
		StringBuilder strRandomNumber = new StringBuilder(9);
		StringBuilder strRandomAlpha = new StringBuilder(9);
		for (int i = 0; i < 4; i++) {
			strRandomAlpha.append(strAlpha.charAt(rnd.nextInt(strAlpha.length())));
			strRandomNumber.append(strNumerics.charAt(rnd.nextInt(strNumerics.length())));

		}
		strRandom = strRandomAlpha.toString() + strRandomNumber.toString();
		String pwd = strRandom;
		return pwd;
	}
	
	//Generate Random Number
	public static WebElement generateANumber(List<WebElement> Size) {
		int   int_rnd = 0;
		Random rnd = new Random();
		for(int i=0; i<5; i++) {
			int_rnd= rnd.nextInt(Size.size());
		 if(int_rnd>=3 && int_rnd<=Size.size())
		 {
			 break;
		 }
		 else
		 {
			 int_rnd++;
			 i++;
		  }
		 }
		return Size.get(int_rnd);
	}

	//Capture Screenshot
	public static void captureScreenshot(WebDriver driver) throws IOException {
		EventFiringWebDriver e = new EventFiringWebDriver(driver);
		File srcFile = e.getScreenshotAs(OutputType.FILE);

		SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMMyyyy");
		Calendar cal = Calendar.getInstance();
		String sysdate1 = dateFormat.format(cal.getTime());

		SimpleDateFormat dateFormat1 = new SimpleDateFormat("HHmmss");
		String sysdate2 = dateFormat1.format(cal.getTime());

		File destFile = new File("Screenshots/" + sysdate1 + "/" + "/" + sysdate2 + ".png");
		FileUtils.copyFile(srcFile, destFile);
	}

	public static String capture_screenshot(WebDriver driver, String screenshotname) throws IOException {
		// Createfolder(System.getProperty("user.dir")+"\\screenshots\\");
		TakesScreenshot ts = (TakesScreenshot) driver;
		SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMMyyyy");
		Calendar cal = Calendar.getInstance();
		String sysdate1 = dateFormat.format(cal.getTime());
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("HHmmss");
		String sysdate2 = dateFormat1.format(cal.getTime());
		screenshotname = screenshotname + sysdate1 + "/" + "/" + sysdate2 + UtilitiesWeb.getTimeStamp_sec() + ".png";
		File image = ts.getScreenshotAs(OutputType.FILE);
		String destpath = "C:\\Voot-V3-Web-Automation-Screeenshots\\" + screenshotname;
		File destPath = new File(destpath);
		FileUtils.moveFile(image, destPath);
		return destpath;
	}

	/*---------------------------------------------------------------------------------------------------------------------*/

	// vertical swipe
	public static void verticalSwipe(WebDriver driver, String end) throws InterruptedException {

		for (int i = 0; i < 10; i++) {
			try {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("window.scrollBy(0,500)");
			} catch (Exception e) {
			}
			if (driver.findElements(By.xpath(end)).size() > 0)
				break;
		}
	}

	
	// vertical swipe
	public static void verticalSwipe(WebDriver driver, WebElement end) throws InterruptedException {

		for (int i = 0; i < 10; i++) {
			try {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("window.scrollBy(0,250)");
			} catch (Exception e) {
			}
			if (UtilitiesWeb.explicitWaitVisible(driver, end, 2))
				break;
		}
	}

	public static void verticalSwipe(WebDriver driver) throws InterruptedException {
		// scrolling starts

		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,350)");
		} catch (Exception e) {
		}
	}

	

	
	

	public static boolean isElementPresent(WebDriver driver, String locator) {
		int s = driver.findElements(By.xpath(locator)).size();
		if (s == 0)
			return false;
		else
			return true;
	}

	public static void runBat(String batfileName) {
		String filePath = System.getProperty("user.dir") + "/" + batfileName;
		String pathToBatchFile = "cmd /c start" + " " + filePath;
		System.out.println(pathToBatchFile);
		final Runtime rt = Runtime.getRuntime();
		try {
			Process p = rt.exec(pathToBatchFile);
			System.out.println("inside try block");
		} catch (final IOException e) {
			throw new RuntimeException("Failed to run bat file.");
		}

	}

	

	public static void mouseHover(WebDriver driver, WebElement el) {
		Actions action = new Actions(driver);
		action.moveToElement(el).build().perform();
	}

	public static void DoubletimeClick(WebDriver driver, WebElement el) {
		el.click();
		if (el.isDisplayed()) {
			el.click();
		}
	}


	public static void scrollDown(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,250)");
	}

	public static void scrollDown100(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,100)");
	}
	
	public static void scrollUp100(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(200,0)");
	}

	public static void scrollTop(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0,0)", "");
	}

	public static void scrollToElement(WebDriver driver, WebElement element) {
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

		for (int i = 0; i <= 70; i++) {
			try {
				int loc = element.getLocation().getY();
				System.out.println(loc);
				((JavascriptExecutor) driver).executeScript("window.scrollTo(0," + loc + ")", "");
				break;
			} catch (Exception e) {
				((JavascriptExecutor) driver).executeScript("window.scrollBy(0,250)", "");
			}
		}
	}
	
	public static void hardRefresh(WebDriver driver) {
		try {
		Robot robot= new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_SHIFT);
		robot.keyPress(KeyEvent.VK_R);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_SHIFT);
		robot.keyRelease(KeyEvent.VK_R);
		}
		catch(Exception e) {
			e.getMessage();
		}
		
	}

	public static void scrollToElementb(WebDriver driver, WebElement element) {
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		for (int i = 0; i <= 10; i++) {
			try {
				int loc = element.getLocation().getY();
				System.out.println(loc);
				((JavascriptExecutor) driver).executeScript("window.scrollTo(0," + loc + ")", "");
				break;
			} catch (Exception e) {
				((JavascriptExecutor) driver).executeScript("window.scrollBy(0,200)", "");
			}
		}
	}

	public static WebElement scrollToElement(WebDriver driver, String xpath) {
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		WebElement element = null;
		for (int i = 0; i <= 50; i++) {
			try {
				element = driver.findElement(By.xpath(xpath));
				int loc = element.getLocation().getY();
				System.out.println(loc);
				((JavascriptExecutor) driver).executeScript("window.scrollTo(0," + loc + ")", "");
				break;
			} catch (Exception e) {
				((JavascriptExecutor) driver).executeScript("window.scrollBy(0,200)", "");
			}

		}
		return element;

	}
	public static void ScrollTillPageEnd(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public static void fourTimesScrollAndFindElement(WebDriver driver, WebElement element) {
		for (int i = 0; i < 4; i++) {
			try {
				int loc = element.getLocation().getY();
				System.out.println(loc);
				((JavascriptExecutor) driver).executeScript("window.scrollTo(0," + loc + ")", "");
				break;
			} catch (Exception e) {
				((JavascriptExecutor) driver).executeScript("window.scrollBy(0,200)", "");
			}

		}

	}

	public static boolean compareSorting(ArrayList<String> sorted) {

		String previous = ""; // empty string: guaranteed to be less than or
		// equal to any other

		for (final String current : sorted) {
			if (current.compareTo(previous) < 0)
				return false;
			previous = current;
		}

		return true;
	}

	// method to get Status code of URL
	public static int StatusResponseCode(String fileURL) throws Exception {
		HttpURLConnection connection = null;
		int code;
		try {
			URL url1 = new URL(fileURL);
			connection = (HttpURLConnection) url1.openConnection();
			connection.setDefaultUseCaches(false);
			connection.setInstanceFollowRedirects(false);

			connection.setRequestMethod("GET");
			connection.connect();

		} catch (Exception e) {

		}
		code = connection.getResponseCode();
		System.out.println("Response Code of Kaltura URL is: " + code);

		return code;

	}

	
	public static String convertMinutesToHours(String duration_number) {

		String newsec = "";
		String newformat = "";
		String newMinformat = "";
		float duration = Integer.parseInt(duration_number);
		if (duration < 60) {
			int Duration = Integer.parseInt(duration_number);
			newsec = Duration + "s";
			return newsec;
		}
		float hours = (duration / 60) / 60; // since both are ints, you get an int
		int newHour = (int) hours;
		float minutes = Math.round((duration / 60) % 60);
		int newMinutes = (int) minutes;
		if (newHour != 0) {
			newformat = newHour + "h" + " " + newMinutes + "m";
			return newformat;
		} else {
			newMinformat = newMinutes + "m";
			return newMinformat;
		}
	}

	
	public static String convertCamelCase(String toConvert) throws Exception {
		if (toConvert == "") {
			return "";
		} else {
			String finalString = "";
			String[] array = toConvert.toLowerCase().split(" ");
			for (int i = 0; i < array.length; i++) {
				char firstCharBefore = array[i].charAt(0);
				char firstCharAfter = Character.toUpperCase(firstCharBefore);
				String newWord = array[i].substring(1);
				newWord = firstCharAfter + newWord;
				System.out.println("////////////////////////" + newWord);
				array[i] = newWord;
				if (i == array.length - 1)
					finalString = finalString.concat(array[i]);
				else
					finalString = finalString.concat(array[i] + " ");
			}
			return finalString;
		}
	}

	public static void createFolder(String foldPath) {
		File f = new File(foldPath);
		if (!f.exists()) {
			f.mkdirs();
		}
	}

	public static boolean wait_until_element_is_visible(WebElement element, int timeOut) {
		boolean isElementEnabled;
		try {
			WebDriverWait wait = new WebDriverWait(BaseTestWeb.driver, timeOut);
			wait.until(ExpectedConditions.visibilityOf(element));
			wait.pollingEvery(Duration.ofMillis(200));
			isElementEnabled = true;
		} catch (Exception e) {
			isElementEnabled = false;
			System.out.println("element " + element.toString() + "' is not enabled even after waiting for :" + timeOut
					+ " seconds.");
		}
		return isElementEnabled;
	}

	public static boolean wait_until_elememt_is_enable(WebElement element, int timeOut) {
		boolean isElementEnabled;
		try {
			WebDriverWait wait = new WebDriverWait(BaseTestWeb.driver, timeOut);
			wait.until(ExpectedConditions.elementToBeClickable(element));
			wait.pollingEvery(Duration.ofMillis(200));
			isElementEnabled = true;
		} catch (Exception e) {
			isElementEnabled = false;
			System.out.println("element " + element.toString() + "' is not enabled even after waiting for :" + timeOut
					+ " seconds.");
		}
		return isElementEnabled;
	}

	public static void switch_to_parent_window() {
		ArrayList<String> windows = new ArrayList<String>(Base_Test_Web_Utils.driver.getWindowHandles());

		Base_Test_Web_Utils.driver.switchTo().window(windows.get(0));
	}

	public static void scrollDownBy2000(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,2000)");

	}
	public static void scrollUp200(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,-300)");
	}
	
	
	
	public static String random_Number()
{
		Random random=new Random();
		
		String	Random_Number = null;
		for(int i=0; i<=5; i++) {
			int nextInt = random.nextInt(1000);
			Random_Number=Integer.toString(nextInt);
			Random_Number=Random_Number+nextInt;
		}
	
		return Random_Number;
		}
	
	  // generate a random AlphaNumeric String 
	public static String gererate_alphanumeric() {
	
		// chose a Character random from this String 
	String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
								+ "0123456789"
			                    + "#?!@$%^*-"
								+ "abcdefghijklmnopqrstuvxyz"; 
	
	// create StringBuffer size of AlphaNumericString 
	StringBuilder sb = new StringBuilder(10); 
	StringBuilder append=null;
	for (int i = 0; i <= 9; i++) { 

	
		int index = (int)(AlphaNumericString.length()* Math.random()); 

		// add Character one by one in end of sb 
		 append = sb.append(AlphaNumericString.charAt(index)); 
	
	 } 
	
	 String alphanumericString = append.toString();
	 return alphanumericString;
  }
	
	public static String getCellValue(Workbook wb, String sheetName, int rowNum, int colNum){
		
		String cellValue = "";
		try {
			wb = WorkbookFactory.create(new FileInputStream(ConstantsWeb.EXCEL_PATH));
			cellValue = wb.getSheet(sheetName).getRow(rowNum).getCell(colNum).toString();
		} catch (Exception e) {
			e.getMessage();
		}
		return cellValue;
	}
	public static int getTotalRowCount(Workbook wb,String excelFileName, String sheetName)
	{
		int rowCount = 0;
		try 
		{
			wb = WorkbookFactory.create(new FileInputStream(ConstantsWeb.EXCEL_PATH));
		    rowCount = wb.getSheet(sheetName).getLastRowNum();
		} 
		catch (Exception e) 
		{
			e.getMessage();
		} 
		return rowCount;
	}
	
	public static String getBusinessDate(LocalDate date, int days) {
	    LocalDate result = date;
	    int addedDays = 0;
	    while (addedDays < days) 
	    {
	        result = result.plusDays(1);
	        if (!(result.getDayOfWeek() == DayOfWeek.SATURDAY || result.getDayOfWeek() == DayOfWeek.SUNDAY)) {
	            ++addedDays;
	        }
	    }
	    String Businessdate="";
	    LocalDate businessDate=result;
	    String string = businessDate.toString();
	    String[] split = string.split("-");
	    for(int i=split.length-1;i>0;i--)
	    {
	    	Businessdate=Businessdate+split[i]+"/";
	    }
	    Businessdate=Businessdate+split[0];
	    return Businessdate;
	}
	 public static String getpreviousBusinessDate(LocalDate date, int days)
	 {
		    LocalDate result = date;
		    int addedDays = 0;
		    while (addedDays < days) 
		    {
		        result = result.minusDays(1);
		        if (!(result.getDayOfWeek() == DayOfWeek.SATURDAY || result.getDayOfWeek() == DayOfWeek.SUNDAY)) 
		        {
		            ++addedDays;
		        }
		        
		    }
		    String Businessdate="";
		    LocalDate businessDate=result;
		    String string = businessDate.toString();
		    String[] split = string.split("-");
		    for(int i=split.length-1;i>0;i--)
		    {
		    	Businessdate=Businessdate+split[i]+"/";
		    }
		    Businessdate=Businessdate+split[0];
		    return Businessdate;
	 }
	 public static String getrandomNumber()
	 {
			int randomPIN = (int)(Math.random()*9000)+1000;
			String val = ""+randomPIN;
			return val;
	 }
	public static String getdate()
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		String sysdate1 = dateFormat.format(cal.getTime());
		return sysdate1;
	}	
}
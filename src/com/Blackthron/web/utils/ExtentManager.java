//http://relevantcodes.com/Tools/ExtentReports2/javadoc/index.html?com/relevantcodes/extentreports/ExtentReports.html

package com.Blackthron.web.utils;

import java.io.File;
import java.util.Date;

/*************************************************************************************
 * Class        : ExtentManager
 * Purpose      : This class is used for configuring Extent Reports for the framework
 * Remarks      : none
 *                
 *                
 *
 **************************************************************************************/
public class ExtentManager {
//	private static ExtentReports extent;
//	
//	private ExtentManager(){}
//
//	public static ExtentReports getInstance() {
//		if (extent == null) {
//			
//			String CURRENTPATH="";
//			switch(ConstantsWeb.OS) {
//			case "Mac":
//			{
//				CURRENTPATH=ConstantsWeb.REPORT_PATH_MAC;
//				
//				break;
//			}
//			case"Windows":
//			{
//				CURRENTPATH=ConstantsWeb.REPORT_PATH;
//				break;
//			}
//			}
//			
//			
//			
//			Date d= new Date();
//			String fileName=ConstantsWeb.browser+"_"+d.toString().replace(":", "_").replace(" ", "_")+".html";
//			
//			extent = new ExtentReports(CURRENTPATH+fileName, true, DisplayOrder.OLDEST_FIRST);
//
//			// optional
////			extent.config().documentTitle("Automation Report")
////					.reportName("Regression").reportHeadline("");
//			extent.loadConfig(new File(System.getProperty("user.dir")+"//ReportsConfig.xml"));
//			// optional
//			extent.addSystemInfo("Selenium Version", "2.53").addSystemInfo(
//					"Environment", "PROD");
//		}
//		return extent;
//	}
}

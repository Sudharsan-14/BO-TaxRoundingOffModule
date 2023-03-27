package epicList_Chrome_Account_User;

import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import epicList_Chrome_Account_User.Utility;
import newReadExcelFile_New.ExcelDataConfig;

public class Verify_Revenue_Center_Report {

	public WebDriver driver;

		ExtentReports rep = ExtentManager.getInstance();
		ExtentTest test = rep.startTest("Verify_Revenue_Center_Report");
		
		float unknownValue = 00;
		
		@AfterMethod
		public void tearDown(ITestResult result)
		{
			if(ITestResult.FAILURE == result.getStatus())
			{
				Utility.captureScreenshot(driver, result.getName());
			}
		}
			
		@AfterClass
		public void flushTest() throws Exception
		{
			Thread.sleep(2000);
			rep.endTest(test);
			rep.flush();
			SendMail.snedMailWithAttachment();
		}

		@Test(priority=1)
		public void login() throws Exception
		{
			Thread.sleep(2000);
			//Call the chrome driver
			System.setProperty("webdriver.chrome.driver",Utility.getProperty("Chrome_Driver_Path"));
			//Open the Chrome window
			driver = new ChromeDriver();
			//Wait for 30 seconds
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			//Maximize the Chrome window
			driver.manage().window().maximize();
			Thread.sleep(1000);
			//Launch the URL
			driver.get(Utility.getProperty("appURL"));
			if(Utility.getProperty("Product").equalsIgnoreCase("UPOS"))
			{
				Browser_Account_Level_User a = new Browser_Account_Level_User();
				a.UPOS_login(driver, test);
			}
			else 			
			{
				Browser_Account_Level_User a = new Browser_Account_Level_User();
				a.Linga_login(driver, test);
			}	
		}
			
		@Test(priority=500)
		public void logout() throws Exception
		{		Browser_Account_Level_User a = new Browser_Account_Level_User();
		a.Logout(driver, test);}
	
		@Test(priority = 100)
		public void calling() throws Exception
		{
			Thread.sleep(10000);
			try
			{
					Thread.sleep(1000);System.out.println("Minimize Chat Icon");
					driver.findElement(By.id("zsiq_minimize")).click();			
			}
			catch(Exception e)
			{
				System.out.println("Minimize Chat Icon Missing");
			}
			//ViewDashBoardReports a = new ViewDashBoardReports();
			Thread.sleep(1000);
			Revenue_Center_Report_Open_Page_Report(driver);
			Revenue_Center_Report_By_All_Options_Selected(driver);
			Revenue_Center_Report_By_Required_Options_Selected(driver);
			Thread.sleep(1500);
		}

		@Test(enabled=false,priority=24)
		public void Revenue_Center_Report_Open_Page_Report(WebDriver driver) throws Exception
		{
            File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
/*				//Click the Reports option
				driver.findElement(By.xpath("//span[.='Reports']")).click();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				// Create instance of Java script executor
				JavascriptExecutor je = (JavascriptExecutor) driver;
				//Identify the WebElement which will appear after scrolling down
				WebElement element = driver.findElement(By.xpath("//span[.='Revenue Center']"));
				//Scroll the page till the Revenue Center Report option present
				je.executeScript("arguments[0].scrollIntoView(true);",element); 
		        //Click the Tax Report Option		
				driver.findElement(By.xpath("//span[.='Revenue Center']")).click();*/
				driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id1")+"revenueCenterReport");
				Thread.sleep(5000);
				try
				{
				//Check Customer Preference Report page opened or not
				if(driver.findElement(By.xpath(excel.getData(1, 1103, 1))).getText().equalsIgnoreCase("Revenue Center"))
				{
					test.log(LogStatus.PASS,"Revenue Center Report page loaded Successfully");
				
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));
				}
				else
				{
					test.log(LogStatus.FAIL,"Revenue Center Report page loaded Failed");
				
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));
				}
				}
				catch(Exception e)
				{
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));
				}
				Thread.sleep(3000);wb.close();
				
			}
	
		@Test(enabled=false,priority=25)
		public void Revenue_Center_Report_By_All_Options_Selected(WebDriver driver) throws Exception
		{
			    File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			    Thread.sleep(2000);
				//Select the Time Period option
			    driver.findElement(By.xpath(excel.getData(1, 1104, 1))).click();
			    //Enter the required Period of time for Specific date
			    driver.findElement(By.xpath(excel.getData(1, 1105, 1))).sendKeys("Date Range");
			    //Press the Enter key
			    driver.findElement(By.xpath(excel.getData(1, 1105, 1))).sendKeys(Keys.ENTER);

			    //Clear the Date field
			    driver.findElement(By.xpath(excel.getData(1, 1106, 1))).clear();
			    //Enter the required date
			    driver.findElement(By.xpath(excel.getData(1, 1106, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));

			    //Clear the Date field
			    driver.findElement(By.xpath(excel.getData(1, 1107, 1))).clear();
			    //Enter the required date
			    driver.findElement(By.xpath(excel.getData(1, 1107, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
			    
			    //Click the Run
			    driver.findElement(By.cssSelector(excel.getData(1, 974, 4))).click();
			    Thread.sleep(8000);
		
			    //Check whether the all options are displayed or not
			    if(driver.findElement(By.xpath(excel.getData(1, 1108, 1))).isDisplayed()
			    		&&driver.findElement(By.xpath(excel.getData(1, 1109, 1))).isDisplayed()
			    		&&driver.findElement(By.xpath(excel.getData(1, 1110, 1))).isDisplayed()
			    		&&driver.findElement(By.xpath(excel.getData(1, 1111, 1))).isDisplayed()
			    		&&driver.findElement(By.xpath(excel.getData(1, 1112, 1))).isDisplayed())
			    {
			    	test.log(LogStatus.PASS, "All the Options are displayed for All filters");
			    	
			    	//Others Total of - No Of Guests
			    	String NOG_Others_Total = driver.findElement(By.xpath(excel.getData(1, 1113, 1))).getText();
			    	
			    	//Grand Total of - No Of Guests
			    	String NOG_Grand_Total = driver.findElement(By.xpath(excel.getData(1, 1114, 1))).getText();
			    	
			    	//Others Total of - No Of Checks
			    	String NOC_Others_Total = driver.findElement(By.xpath(excel.getData(1, 1115, 1))).getText();
			    	
			    	//Grand Total of - No Of Checks
			    	String NOC_Grand_Total = driver.findElement(By.xpath(excel.getData(1, 1116, 1))).getText();

			    	//Others Total of - Net Sales by Category
			    	String NSBC_Others_Total = driver.findElement(By.xpath(excel.getData(1, 1117, 1))).getText();
			    	
			    	//Grand Total of - Net Sales by Category
			    	String NSBC_Grand_Total = driver.findElement(By.xpath(excel.getData(1, 1118, 1))).getText();

			    	//Others Total of - Net Sales by Shift
			    	String NSBS_Others_Total = driver.findElement(By.xpath(excel.getData(1, 1119, 1))).getText();
			    	
			    	//Grand Total of - Net Sales by Shift
			    	String NSBS_Grand_Total = driver.findElement(By.xpath(excel.getData(1, 1120, 1))).getText();
			    	
			    	//Others Total of - Net Sales by Revenue Center
			    	String NSBRC_Others_Total = driver.findElement(By.xpath(excel.getData(1, 1121, 1))).getText();
			    	
			    	//Grand Total of - Net Sales by Revenue Center
			    	String NSBRC_Grand_Total = driver.findElement(By.xpath(excel.getData(1, 1122, 1))).getText();

			    	test.log(LogStatus.INFO, "No Of Guests - Others Total Is : "+NOG_Others_Total);
			    	
			    	test.log(LogStatus.INFO, "No Of Guests - Grand Total Is : "+NOG_Grand_Total);
			    	
			    	test.log(LogStatus.INFO, "No Of Checks - Others Total Is : "+NOC_Others_Total);
			    	
			    	test.log(LogStatus.INFO, "No Of Checks - Grand Total Is : "+NOC_Grand_Total);
			    	
			    	test.log(LogStatus.INFO, "Net Sales by Category - Others Total Is : "+NSBC_Others_Total);
			    	
			    	test.log(LogStatus.INFO, "Net Sales by Category - Grand Total Is : "+NSBC_Grand_Total);
			    	
			    	test.log(LogStatus.INFO, "Net Sales by Shift - Others Total Is : "+NSBS_Others_Total);
			    	
			    	test.log(LogStatus.INFO, "Net Sales by Shift - Grand Total Is : "+NSBS_Grand_Total);
			    	
			    	test.log(LogStatus.INFO, "Net Sales by Revenue Center - Others Total Is : "+NSBRC_Others_Total);
			    	
			    	test.log(LogStatus.INFO, "Net Sales by Revenue Center - Grand Total Is : "+NSBRC_Grand_Total);
			 
			    
			    	Thread.sleep(3000);
			    	driver.findElement(By.tagName("html")).sendKeys(Keys.END);
			    	
			    	String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));

					Thread.sleep(3000);
					driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			    
			    
			    }
			    else
			    {
			    	test.log(LogStatus.FAIL, "Required options are not displayed");wb.close();
			  
			    	String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));

			    
			    }
			    
			}

		@Test(enabled=false,priority=26)
		public void Revenue_Center_Report_By_Required_Options_Selected(WebDriver driver) throws Exception
		{

		    File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				for(int i = 1; i<= 5; i++)
				{
					//Click the Close button of Sales By Revenue Center Option
					driver.findElement(By.xpath(excel.getData(1, 1123, 1))).click();
				}
				
				Thread.sleep(2000);
				//Click the Sales By Revenue Center option
				driver.findElement(By.xpath(excel.getData(1, 1124, 1))).click();
				//Enter the Required Option
				driver.findElement(By.xpath(excel.getData(1, 1125, 1))).sendKeys(Utility.getReportPropertyUser("Sales_By_Revenue_Center"));
				//Press the Enter Button
				driver.findElement(By.xpath(excel.getData(1, 1125, 1))).sendKeys(Keys.ENTER);
				
				//Select the Time Period option
			    driver.findElement(By.xpath(excel.getData(1, 1104, 1))).click();
			    //Enter the required Period of time for Specific date
			    driver.findElement(By.xpath(excel.getData(1, 1105, 1))).sendKeys("Date Range");
			    //Press the Enter key
			    driver.findElement(By.xpath(excel.getData(1, 1105, 1))).sendKeys(Keys.ENTER);

			    //Clear the Date field
			    driver.findElement(By.xpath(excel.getData(1, 1106, 1))).clear();
			    //Enter the required date
			    driver.findElement(By.xpath(excel.getData(1, 1106, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));

			    //Clear the Date field
			    driver.findElement(By.xpath(excel.getData(1, 1107, 1))).clear();
			    //Enter the required date
			    driver.findElement(By.xpath(excel.getData(1, 1107, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
			    
			    //Click the Run
			    driver.findElement(By.cssSelector(excel.getData(1, 974, 4))).click();
			    Thread.sleep(8000);
			    
/*			    Select Option = new Select(driver.findElement(By.xpath("//label[.='Sales By Revenue Center']/../select")));
			    Option.selectByValue(Utility.getReportPropertyUser("Sales_By_Revenue_Center"));*/
			    
			    //Check Whether the Required option is displayed or not
			    if(Utility.getReportPropertyUser("Sales_By_Revenue_Center").equalsIgnoreCase("Net sales for Revenue Center"))
			    {
				    //Check whether the all options are displayed or not
				    if(driver.findElement(By.xpath(excel.getData(1, 1112, 1))).isDisplayed())
				    {
				    	test.log(LogStatus.PASS, "Net sales for Revenue Center Option is displayed for Net sales for Revenue Center filter");
				    	
				    	//Others Total of - Net Sales by Revenue Center
				    	String NSBRC_Others_Total = driver.findElement(By.xpath(excel.getData(1, 1121, 1))).getText();
				    	
				    	//Grand Total of - Net Sales by Revenue Center
				    	String NSBRC_Grand_Total = driver.findElement(By.xpath(excel.getData(1, 1122, 1))).getText();

				    	test.log(LogStatus.INFO, "Net Sales by Revenue Center - Others Total Is : "+NSBRC_Others_Total);
				    	
				    	test.log(LogStatus.INFO, "Net Sales by Revenue Center - Grand Total Is : "+NSBRC_Grand_Total);
				  
				    	Thread.sleep(3000);
				    	driver.findElement(By.tagName("html")).sendKeys(Keys.END);
				    	
				    	String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));

						Thread.sleep(3000);
						driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
				    
				    	
				    }
				    else
				    {
				    	test.log(LogStatus.FAIL, "Net sales for Revenue Center Option is Not displayed for Net sales for Revenue Center filter");
				   
				    
				    	//Thread.sleep(3000);
				    	//driver.findElement(By.tagName("html")).sendKeys(Keys.END);
				    	
				    	String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));

						//Thread.sleep(3000);
						//driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
				    
				    
				    }
			    }
			    else if(Utility.getReportPropertyUser("Sales_By_Revenue_Center").equalsIgnoreCase("Net Sales by Shift"))
			    {
				    //Check whether the all options are displayed or not
				    if(driver.findElement(By.xpath(excel.getData(1, 1111, 1))).isDisplayed())
				    {
				    	test.log(LogStatus.PASS, "Net Sales by Shift Option is displayed for Net Sales by Shift filter");
				    	
				    	//Others Total of - Net Sales by Shift
				    	String NSBS_Others_Total = driver.findElement(By.xpath(excel.getData(1, 1119, 1))).getText();
				    	
				    	//Grand Total of - Net Sales by Shift
				    	String NSBS_Grand_Total = driver.findElement(By.xpath(excel.getData(1, 1120, 1))).getText();
				    	
				    	test.log(LogStatus.INFO, "Net Sales by Shift - Others Total Is : "+NSBS_Others_Total);
				    	
				    	test.log(LogStatus.INFO, "Net Sales by Shift - Grand Total Is : "+NSBS_Grand_Total);

				    	
				    	Thread.sleep(3000);
				    	driver.findElement(By.tagName("html")).sendKeys(Keys.END);
				    	
				    	String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));

						Thread.sleep(3000);
						driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
				    
				    }
				    else
				    {
				    	test.log(LogStatus.FAIL, "Net Sales by Shift Option is not displayed for Net Sales by Shift filter");
				 
				    	//Thread.sleep(3000);
				    	//driver.findElement(By.tagName("html")).sendKeys(Keys.END);
				    	
				    	String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));

						//Thread.sleep(3000);
					//	driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
				    
				    }
			    }
			    else if(Utility.getReportPropertyUser("Sales_By_Revenue_Center").equalsIgnoreCase("Net sales by Category"))
			    {
				    //Check whether the all options are displayed or not
				    if(driver.findElement(By.xpath(excel.getData(1, 1110, 1))).isDisplayed())
				    {
				    	test.log(LogStatus.PASS, "Net Sales by Category Option is displayed for Net Sales by Category filter");
				    	
				    	//Others Total of - Net Sales by Category
				    	String NSBC_Others_Total = driver.findElement(By.xpath(excel.getData(1, 1117, 1))).getText();
				    	
				    	//Grand Total of - Net Sales by Category
				    	String NSBC_Grand_Total = driver.findElement(By.xpath(excel.getData(1, 1118, 1))).getText();

				    	test.log(LogStatus.INFO, "Net Sales by Category - Others Total Is : "+NSBC_Others_Total);
				    	
				    	test.log(LogStatus.INFO, "Net Sales by Category - Grand Total Is : "+NSBC_Grand_Total);
				   
				    	
				    	Thread.sleep(3000);
				    	driver.findElement(By.tagName("html")).sendKeys(Keys.END);
				    	
				    	String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));

						Thread.sleep(3000);
						driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
				    
				    }
				    else
				    {
				    	test.log(LogStatus.FAIL, "Net Sales by Category Option is not displayed for Net Sales by Category filter");
				    
				    //	Thread.sleep(3000);
				    //	driver.findElement(By.tagName("html")).sendKeys(Keys.END);
				    	
				    	String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));

					//	Thread.sleep(3000);
					//	driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
				    
				    
				    }
			    }
			    
			    else if(Utility.getReportPropertyUser("Sales_By_Revenue_Center").equalsIgnoreCase("Number of checks"))
			    {
				    //Check whether the all options are displayed or not
				    if(driver.findElement(By.xpath(excel.getData(1, 1109, 1))).isDisplayed())
				    {
				    	test.log(LogStatus.PASS, "No Of Checks Option is displayed for No Of Checks filter");
				    	
				    	//Others Total of - No Of Checks
				    	String NOC_Others_Total = driver.findElement(By.xpath(excel.getData(1, 1115, 1))).getText();
				    	
				    	//Grand Total of - No Of Checks
				    	String NOC_Grand_Total = driver.findElement(By.xpath(excel.getData(1, 1116, 1))).getText();
				    	
				    	test.log(LogStatus.INFO, "No Of Checks - Others Total Is : "+NOC_Others_Total);
				    	
				    	test.log(LogStatus.INFO, "No Of Checks - Grand Total Is : "+NOC_Grand_Total);
				   
				    	
				    	Thread.sleep(3000);
				    	driver.findElement(By.tagName("html")).sendKeys(Keys.END);
				    	
				    	String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));

						Thread.sleep(3000);
						driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
				    
				    	
				    }
				    else
				    {
				    	test.log(LogStatus.FAIL, "No Of Checks Option is not displayed for No Of Checks filter");
				    
				    //	Thread.sleep(3000);
				    //	driver.findElement(By.tagName("html")).sendKeys(Keys.END);
				    	
				    	String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));

					//	Thread.sleep(3000);
					//	driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
				    
				    }
			    }
			    
			    else if(Utility.getReportPropertyUser("Sales_By_Revenue_Center").equalsIgnoreCase("Number of guests"))
			    {
				    //Check whether the all options are displayed or not
				    if(driver.findElement(By.xpath(excel.getData(1, 1108, 1))).isDisplayed())
				    {
				    	test.log(LogStatus.PASS, "No Of Guests Option is displayed for No Of Guests filter");
				    	
				    	//Others Total of - No Of Guests
				    	String NOG_Others_Total = driver.findElement(By.xpath(excel.getData(1, 1113, 1))).getText();
				    	
				    	//Grand Total of - No Of Guests
				    	String NOG_Grand_Total = driver.findElement(By.xpath(excel.getData(1, 1114, 1))).getText();

				    	test.log(LogStatus.INFO, "No Of Guests - Others Total Is : "+NOG_Others_Total);
				    	
				    	test.log(LogStatus.INFO, "No Of Guests - Grand Total Is : "+NOG_Grand_Total);
				    
				    	Thread.sleep(3000);
				    	driver.findElement(By.tagName("html")).sendKeys(Keys.END);
				    	
				    	String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));

						Thread.sleep(3000);
						driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
				    
				    }
				    else
				    {
				    	test.log(LogStatus.FAIL, "No Of Guests Option is not displayed for No Of Guests filter");wb.close();
				    
				    //	Thread.sleep(3000);
				   // 	driver.findElement(By.tagName("html")).sendKeys(Keys.END);
				    	
				    	String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));

					//	Thread.sleep(3000);
					//	driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
				    
				    }
			    }
			    			    
			}

}

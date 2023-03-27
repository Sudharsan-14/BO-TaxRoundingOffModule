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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import epicList_Chrome_Account_User.Utility;
import newReadExcelFile_New.ExcelDataConfig;

public class Verify_SaleRecap_Report {

	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Verify_SaleRecap_Report");
	
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
			Sale_Recap_Sale_method_openpage_Report(driver);
			Sale_Recap_Sale_method_Verify_Report(driver);
			Thread.sleep(1500);
		}
	
			@Test(priority=140,enabled = false)
			public void Sale_Recap_Sale_method_openpage_Report(WebDriver driver) throws Exception
			{
				File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				/*//Click the Reports option
				driver.findElement(By.xpath("//span[.='Reports']")).click();
				// Create instance of Java script executor
				JavascriptExecutor je = (JavascriptExecutor) driver;
				//Identify the WebElement which will appear after scrolling down
				WebElement element = driver.findElement(By.xpath("//span[.='Sale']"));
				//Scroll the page till the Sale option present
				je.executeScript("arguments[0].scrollIntoView(true);",element); 
		        //Click the Sale Option		
				driver.findElement(By.xpath("//span[.='Sale']")).click(); */
				

				Thread.sleep(3000);
				//Enter the SaleRecap Url
				driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id1")+"report/saleRecap");
				
				Thread.sleep(3000);
				try
				{
				//Check weather the Sale Recap Report page is loaded or not
				if(driver.findElement(By.xpath(excel.getData(1, 1091, 1))).getText().equalsIgnoreCase("Sale recap Type"))
				{
					test.log(LogStatus.PASS, "Sale Recap Report page loaded successfully");
				
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));
				}
				else
				{
					test.log(LogStatus.FAIL, "Sale Recap Report page loaded fail");
				
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));
				}
				}
				catch(Exception e)
				{
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.FAIL,test.addScreenCapture(s));
				}

				Thread.sleep(3000);wb.close();

			}	
			
			@Test(priority=141,enabled = false)
			public void Sale_Recap_Sale_method_Verify_Report(WebDriver driver) throws Exception
			{

				File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				Thread.sleep(3000);
				//Click the Select Recap Type option
				driver.findElement(By.xpath("//select[@ng-model='query.recapFilter']/../div/a")).click();
				//Enter the required department
				driver.findElement(By.xpath("//select[@ng-model='query.recapFilter']/../div/div/div/input")).sendKeys("Time period");
				//Press the Enter Key
				driver.findElement(By.xpath("//select[@ng-model='query.recapFilter']/../div/div/div/input")).sendKeys(Keys.ENTER);
			
				Thread.sleep(3000);
				//Click the Employee option
				driver.findElement(By.xpath("//select[@ng-model='query.selectedUserID']/../div/a")).click();
				//Enter the required employee
				driver.findElement(By.xpath("//select[@ng-model='query.selectedUserID']/../div/div/div/input")).sendKeys("All");
				//Press the Enter Key
				driver.findElement(By.xpath("//select[@ng-model='query.selectedUserID']/../div/div/div/input")).sendKeys(Keys.ENTER);
				
				Thread.sleep(3000);
				//Click the Time Period option
				driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/a")).click();
				//Enter the required Time Period
				driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/div/div/input")).sendKeys("Date Range");
				//Press the Enter Key
				driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/div/div/input")).sendKeys(Keys.ENTER);
				
				Thread.sleep(2000);
				//Clear the date field
				driver.findElement(By.xpath("//input[@ng-model='query.fromDate']/.")).clear();
				//Enter the date
				driver.findElement(By.xpath("//input[@ng-model='query.fromDate']/.")).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));

				Thread.sleep(2000);
				//Clear the date field
				driver.findElement(By.xpath("//input[@ng-model='query.toDate']/.")).clear();
				//Enter the date
				driver.findElement(By.xpath("//input[@ng-model='query.toDate']/.")).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
				
				Thread.sleep(2000);
				//Click the Run Button
				driver.findElement(By.xpath(excel.getData(1, 572, 1))).click();
				long start = System.currentTimeMillis();
		    	
//				WebDriverWait wait1=new WebDriverWait(driver, 30);
//				if(driver.getPageSource().contains("CHECK STATS"))
//				WebElement ele11=driver.findElement(By.xpath("//b[.='CHECK STATS']/."));
				
				Thread.sleep(8000);
				//Check weather the Check Status 
				if(driver.getPageSource().contains("CHECK STATS"))
				{
					test.log(LogStatus.PASS, "Check Stats field is available");
					
					long finish = System.currentTimeMillis();
					long totalTime = finish- start; 
					System.out.println("Time in Millisecomds : "+totalTime);
					double ActualfinishTime = TimeUnit.MILLISECONDS.toMinutes(totalTime);
//					long ActualfinishTimeDouble = ((totalTime%86400)%3600)/60;
					long ActualfinishTimeDouble1 = (totalTime/1000)/6;
					System.out.println("MilSEc to Mins basic conversion : "+ActualfinishTimeDouble1);
//					test.log(LogStatus.INFO, "Total Time : "+ActualfinishTimeDouble1);
					test.log(LogStatus.INFO, "Time in Millisecomds : "+totalTime);
					
		        	//Replace all commo's with empty space
		        	String expected1 = Utility.getReportPropertyUser("Check_Count").replace(",", "");
		        	
		        	//Convert the String value of the Check_Count element into float value
		        	float expect1 = Float.parseFloat(expected1);  

					
					Thread.sleep(2000);
					//Check the Check Count
					if(driver.findElement(By.xpath(excel.getData(3, 2229, 1))).getText().equals(Utility.getReportPropertyUser("Check_Count")))
					{
						test.log(LogStatus.PASS, "Actual and Expected check counts are same");
						
			        	//Get the Total value of Check Count
			        	String actualText1 = driver.findElement(By.xpath(excel.getData(3, 2229, 1))).getText();
			        	
			        	//Replace all commo's with empty space
			        	String actualText= actualText1.replace(",", "");
			        	
			        	//Convert the String value of the Check Count Total element into float value
			        	float actual = Float.parseFloat(actualText);
			        	
			        	//Print the actual value
			        	System.out.println("The Actual Check Count Value is : "+actual);
			        	
			        	test.log(LogStatus.PASS, "The Actual Check Count Value is : "+ actual);

					}
					
					else if(expect1 == unknownValue)
					{
						test.log(LogStatus.PASS, "Here we don't have the exact expected value");
						
			        	//Get the Total value of Check Count
			        	String actualText = driver.findElement(By.xpath(excel.getData(3, 2229, 1))).getText();

			        	System.out.println("The Actual Check Count value is : "+actualText);
			        	
			        	test.log(LogStatus.INFO, "The Actual Check Count value is : "+actualText);
					}
					
					else
					{
						test.log(LogStatus.FAIL, "Actual and Expected Check counts are different");
						
			        	//Get the Total value of Check Count
			        	String actualText1 = driver.findElement(By.xpath(excel.getData(3, 2229, 1))).getText();
			        	
			        	//Replace all commo's with empty space
			        	String actualText= actualText1.replace(",", "");
			        	
			        	//Convert the String value of the Check Count Total element into float value
			        	float actual = Float.parseFloat(actualText);
			        			        	
			        	//Get the different
			        	float different = actual - expect1;
			        	
			        	//Print the different value
			        	System.out.println("Check Count Value different is : "+different);
			        	
			        	test.log(LogStatus.FAIL, "Check Count Value different is : "+different);	

					}
					
					Thread.sleep(3000);
					//Check the SALES
					if(driver.getPageSource().contains("SALES"))
					{
						test.log(LogStatus.INFO, "SALES is available");
						
						System.out.println("SALES is available");
						
			        	//Replace all commo's with empty space
			        	String expecte1 = Utility.getReportPropertyUser("Gross_Sales").replace(",", "");
			        	
			        	//Convert the String value of the Gross_Sales element into float value
			        	float expects1 = Float.parseFloat(expecte1);  
			        	
			        	//Replace all commo's with empty space
			        	String expected2 = Utility.getReportPropertyUser("Sale_Report_Net_Sale").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Net_Sale element into float value
			        	float expect2 = Float.parseFloat(expected2);
			        	
			        	//Replace all commo's with empty space
			        	String expected3 = Utility.getReportPropertyUser("Sale_Report_Grand_Sale").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Grand_Sale element into float value
			        	float expect3 = Float.parseFloat(expected3);
			        	
			        	//Replace all commo's with empty space
			        	String expected4 = Utility.getReportPropertyUser("Sale_Report_Rounding_Off").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Grand_Sale element into float value
			        	float expect4 = Float.parseFloat(expected3);
			        	
			        	
			        	//Check weather the Gross Sales total is same or not
			        	if(driver.findElement(By.xpath(excel.getData(1, 764, 1))).getText().equals(Utility.getReportPropertyUser("Gross_Sales")))
			        	{
				        	test.log(LogStatus.PASS, "Actual and Expected Gross Sales Values are same");
				        	
				        	//Get the Total value of Gross Sales
				        	String actualText1 = driver.findElement(By.xpath(excel.getData(1, 764, 1))).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Gross Sales element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Gross Sales Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Gross Sales Value is : "+ actual);
				        }
				        
						else if(expects1 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Gross Sales
				        	String actualText = driver.findElement(By.xpath(excel.getData(1, 764, 1))).getText();

				        	System.out.println("The Actual Gross Sales value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Gross Sales value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Gross Sales values are different");
				        	
				        	//Get the Total value of Gross Sales
				        	String actualText1 = driver.findElement(By.xpath(excel.getData(1, 764, 1))).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Gross Sales Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expects1;
				        	
				        	//Print the different value
				        	System.out.println("Gross Sales Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Gross Sales Value different is : "+different);	
				        }
			        	
			        	//Check weather the Net Sales total is same or not
			        	if(driver.findElement(By.xpath(excel.getData(1, 765, 1))).getText().equals(Utility.getReportPropertyUser("Sale_Report_Net_Sale")))
			        	{
				        	test.log(LogStatus.PASS, "Actual and Expected Net Sales Values are same");
				        	
				        	//Get the Total value of Net Sales
				        	String actualText1 = driver.findElement(By.xpath(excel.getData(1, 765, 1))).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Net Sales element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Net Sales Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Net Sales Value is : "+ actual);
				        }
				        
						else if(expect2 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Net Sales
				        	String actualText = driver.findElement(By.xpath(excel.getData(1, 765, 1))).getText();

				        	System.out.println("The Actual Net Sales value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Net Sales value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Net Sales values are different");
				        	
				        	//Get the Total value of Gross Sales
				        	String actualText1 = driver.findElement(By.xpath(excel.getData(1, 765, 1))).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Net Sales Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect2;
				        	
				        	//Print the different value
				        	System.out.println("Net Sales Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Net Sales Value different is : "+different);	
				        }
			        	
			        	//Check weather the Grand Sales total is same or not
			        	if(driver.findElement(By.xpath(excel.getData(1, 766, 1))).getText().equals(Utility.getReportPropertyUser("Sale_Report_Grand_Sale")))
			        	{
				        	test.log(LogStatus.PASS, "Actual and Expected Grand Sales Values are same");
				        	
				        	//Get the Total value of Grand Sales
				        	String actualText1 = driver.findElement(By.xpath(excel.getData(1, 766, 1))).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Grand Sales element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Grand Sales Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Grand Sales Value is : "+ actual);
				        }
				        
						else if(expect3 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Grand Sales
				        	String actualText = driver.findElement(By.xpath(excel.getData(1, 766, 1))).getText();

				        	System.out.println("The Actual Grand Sales value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Grand Sales value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Grand Sales values are different");
				        	
				        	//Get the Total value of Grand Sales
				        	String actualText1 = driver.findElement(By.xpath(excel.getData(1, 766, 1))).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Net Sales Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect3;
				        	
				        	//Print the different value
				        	System.out.println("Grand Sales Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Grand Sales Value different is : "+different);	
				        }
			        	
			        	driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
			        	driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
					
			        	String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));

					}
					else
					{
						test.log(LogStatus.INFO, "Sales is not available");
						
						driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
						driver.findElement(By.tagName("html")).sendKeys(Keys.DOWN);
						
						String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));

						
						System.out.println("Sales is not available");
					}
					
					
					Thread.sleep(15000);
					//Check weather the Taxes Status 
					if(driver.getPageSource().contains("TAXES"))
					{
						test.log(LogStatus.PASS, "Tax Stats field is available");
						
			        	//Replace all commo's with empty space
			        	String expected11 = Utility.getReportPropertyUser("Sale_Report_Tax").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Tax element into float value
			        	float expect11 = Float.parseFloat(expected11);  

						
						Thread.sleep(2000);
						//Check the Sale Tax
						if(driver.findElement(By.xpath("//label[contains(.,'TAXES')]/../../div[contains(.,'Total Tax Collected')]/div[4]")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Rounding_Off")))
						{
							test.log(LogStatus.PASS, "Actual and Expected sale Tax amount are same");
							
				        	//Get the Total value of Sale Tax
				        	String actualText1 = driver.findElement(By.xpath("//label[contains(.,'TAXES')]/../../div[contains(.,'Total Tax Collected')]/div[4]")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Sale Tax Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Rounding Off Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Sale Tax Amount Value is : "+ actual);

						}
						
						else if(expect11 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Check Count
				        	String actualText = driver.findElement(By.xpath("//label[contains(.,'TAXES')]/../../div[contains(.,'Total Tax Collected')]/div[4]")).getText();

				        	System.out.println("The Actual Sale tax amount value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Sale Tax Amount value is : "+actualText);
						}
						
						else
						{
							test.log(LogStatus.FAIL, "Actual and Expected Rounding Off are different");
							
				        	//Get the Total value of Sale Tax
				        	String actualText1 = driver.findElement(By.xpath("//label[contains(.,'TAXES')]/../../div[contains(.,'Total Tax Collected')]/div[4]")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Total Sale Tax element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect11;
				        	
				        	//Print the different value
				        	System.out.println("Sale Tax Amount Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Sale Tax Amount Value different is : "+different);	

						}
				}
				}
				else
				{
					test.log(LogStatus.FAIL, "Check Stats field is not available");
					
					
					
					Thread.sleep(3000);
					//Check the SALES
					if(driver.getPageSource().contains("SALES"))
					{
						test.log(LogStatus.INFO, "SALES is available");
						
						System.out.println("SALES is available");
						
			        	//Replace all commo's with empty space
			        	String expected1 = Utility.getReportPropertyUser("Gross_Sales").replace(",", "");
			        	
			        	//Convert the String value of the Gross_Sales element into float value
			        	float expect1 = Float.parseFloat(expected1);  
			        	
			        	//Replace all commo's with empty space
			        	String expected2 = Utility.getReportPropertyUser("Sale_Report_Net_Sale").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Net_Sale element into float value
			        	float expect2 = Float.parseFloat(expected2);
			        	
			        	//Replace all commo's with empty space
			        	String expected3 = Utility.getReportPropertyUser("Sale_Report_Grand_Sale").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Grand_Sale element into float value
			        	float expect3 = Float.parseFloat(expected3);
			        	
			        	//Replace all commo's with empty space
			        	String expected4 = Utility.getReportPropertyUser("Sale_Report_Rounding_Off").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Grand_Sale element into float value
			        	float expect4 = Float.parseFloat(expected3);
			        	
			        	
			        	//Check weather the Gross Sales total is same or not
			        	if(driver.findElement(By.xpath("//b[.='SALES']/../../../div[contains(.,'Gross Sales')]/div[2]")).getText().equals(Utility.getReportPropertyUser("Gross_Sales")))
			        	{
				        	test.log(LogStatus.PASS, "Actual and Expected Gross Sales Values are same");
				        	
				        	//Get the Total value of Gross Sales
				        	String actualText1 = driver.findElement(By.xpath("//b[.='SALES']/../../../div[contains(.,'Gross Sales')]/div[2]")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Gross Sales element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Gross Sales Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Gross Sales Value is : "+ actual);
				        }
				        
						else if(expect1 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Gross Sales
				        	String actualText = driver.findElement(By.xpath(excel.getData(1, 764, 1))).getText();

				        	System.out.println("The Actual Gross Sales value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Gross Sales value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Gross Sales values are different");
				        	
				        	//Get the Total value of Gross Sales
				        	String actualText1 = driver.findElement(By.xpath(excel.getData(1, 764, 1))).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Gross Sales Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect1;
				        	
				        	//Print the different value
				        	System.out.println("Gross Sales Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Gross Sales Value different is : "+different);	
				        }
			        	
			        	//Check weather the Net Sales total is same or not
			        	if(driver.findElement(By.xpath(excel.getData(1, 765, 1))).getText().equals(Utility.getReportPropertyUser("Sale_Report_Net_Sale")))
			        	{
				        	test.log(LogStatus.PASS, "Actual and Expected Net Sales Values are same");
				        	
				        	//Get the Total value of Net Sales
				        	String actualText1 = driver.findElement(By.xpath(excel.getData(1, 765, 1))).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Net Sales element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Net Sales Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Net Sales Value is : "+ actual);
				        }
				        
						else if(expect2 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Net Sales
				        	String actualText = driver.findElement(By.xpath(excel.getData(1, 765, 1))).getText();

				        	System.out.println("The Actual Net Sales value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Net Sales value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Net Sales values are different");
				        	
				        	//Get the Total value of Gross Sales
				        	String actualText1 = driver.findElement(By.xpath(excel.getData(1, 765, 1))).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Net Sales Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect2;
				        	
				        	//Print the different value
				        	System.out.println("Net Sales Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Net Sales Value different is : "+different);	
				        }
			        	
			        	//Check weather the Grand Sales total is same or not
			        	if(driver.findElement(By.xpath(excel.getData(1, 766, 1))).getText().equals(Utility.getReportPropertyUser("Sale_Report_Grand_Sale")))
			        	{
				        	test.log(LogStatus.PASS, "Actual and Expected Grand Sales Values are same");
				        	
				        	//Get the Total value of Grand Sales
				        	String actualText1 = driver.findElement(By.xpath(excel.getData(1, 766, 1))).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Grand Sales element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Grand Sales Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Grand Sales Value is : "+ actual);
				        }
				        
						else if(expect3 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Grand Sales
				        	String actualText = driver.findElement(By.xpath(excel.getData(1, 766, 1))).getText();

				        	System.out.println("The Actual Grand Sales value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Grand Sales value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Grand Sales values are different");
				        	
				        	//Get the Total value of Grand Sales
				        	String actualText1 = driver.findElement(By.xpath(excel.getData(1, 766, 1))).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Net Sales Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect3;
				        	
				        	//Print the different value
				        	System.out.println("Grand Sales Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Grand Sales Value different is : "+different);	
				        }
			        	
			        	driver.findElement(By.tagName("html")).sendKeys(Keys.END);
			        	driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
			        	
			        	String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));

					}
					else
					{
						test.log(LogStatus.INFO, "Sales is not available");
						
						System.out.println("Sales is not available");wb.close();
						
						String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));

					}
					
					
					Thread.sleep(15000);
					//Check weather the Taxes Status 
					if(driver.getPageSource().contains("TAXES"))
					{
						test.log(LogStatus.PASS, "Tax Stats field is available");
						
			        	//Replace all commo's with empty space
			        	String expected1 = Utility.getReportPropertyUser("Sale_Report_Rounding_Off").replace(",", "");
			        	
			        	//Convert the String value of the Rounding Off element into float value
			        	float expect1 = Float.parseFloat(expected1);  

						
						Thread.sleep(2000);
						//Check the Rounding Off
						if(driver.findElement(By.xpath("//label[contains(.,'TAXES')]/../../div[contains(.,'Rounding Off')]/div[4]")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Rounding_Off")))
						{
							test.log(LogStatus.PASS, "Actual and Expected Rounding Off are same");
							
				        	//Get the Total value of Rounding Off
				        	String actualText1 = driver.findElement(By.xpath("//label[contains(.,'TAXES')]/../../div[contains(.,'Rounding Off')]/div[4]")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Rounding Off Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Rounding Off Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Rounding Off Value is : "+ actual);

						}
						
						else if(expect1 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Check Count
				        	String actualText = driver.findElement(By.xpath("//label[contains(.,'TAXES')]/../../div[contains(.,'Rounding Off')]/div[4]")).getText();

				        	System.out.println("The Actual Rounding Off value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Rounding Off value is : "+actualText);
						}
						
						else
						{
							test.log(LogStatus.FAIL, "Actual and Expected Rounding Off are different");
							
				        	//Get the Total value of Check Count
				        	String actualText1 = driver.findElement(By.xpath("//label[contains(.,'TAXES')]/../../div[contains(.,'Rounding Off')]/div[4]")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Check Count Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect1;
				        	
				        	//Print the different value
				        	System.out.println("Rounding Off Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Rounding Off Value different is : "+different);	

						}
				}
				}
				driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			}
	
	
}

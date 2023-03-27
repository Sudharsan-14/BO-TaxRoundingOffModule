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

public class Verify_Enterprise_SaleRecap_Report {



	public WebDriver driver;
	
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Verify_Enterprise_SaleRecap_Report");

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
			//SendMail.snedMailWithAttachment();
		}

		@Test(priority=1)
		public void login() throws Exception
		{
			Thread.sleep(2000);
			// Call the chrome driver by using local path
			System.setProperty("webdriver.chrome.driver", Utility.getProperty("Chrome_Driver_Path"));
			// Open the Chrome window
			driver = new ChromeDriver();
			// Wait for 30 seconds
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			// Maximize the Chrome window
			driver.manage().window().maximize();
			Thread.sleep(1000);
			// Launch the URL
			driver.get(Utility.getProperty("appURL"));
			if (Utility.getProperty("Product").equalsIgnoreCase("UPOS")) {
				Browser_Account_Level_User a = new Browser_Account_Level_User();
				a.UPOS_login(driver, test);
			} else {
				Browser_Account_Level_User a = new Browser_Account_Level_User();
				a.Linga_login(driver, test);
			}
		}

		@Test(priority = 500)
		public void logout() throws Exception {
			Browser_Account_Level_User a = new Browser_Account_Level_User();
			a.Logout(driver, test);
		}	
	
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
			SaleRecap_Enterprise_Report_Method_For_openpage(driver);
			//SaleRecap_Enterprise_Report_Method_For_Non_Close_Sales(driver);
			SaleRecap_Enterprise_Report_Method_For_Timeperiod(driver);
			Thread.sleep(1500);
		}
	
		@Test(enabled=false,priority=14)
		public void SaleRecap_Enterprise_Report_Method_For_openpage(WebDriver driver) throws Exception
		{

			 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		/*	//Click the My Stores option
			driver.findElement(By.xpath("//span[.='My Stores']")).click();

			Thread.sleep(3000);
	        //Click the EnterPrise Reports Option		
			driver.findElement(By.xpath("//span[.='EnterPrise Reports']")).click();
			
			Thread.sleep(2000);
			//Click the Sale option
			driver.findElement(By.xpath("//span[.='Sale']")).click();		*/
			
			Thread.sleep(2000);
			//Enter the URl
			driver.get(Utility.getProperty("Enterprise_Base_URL")+"enterPriseReport/saleRecapSummary");

			Thread.sleep(8000);
			try
			{
			//Check SaleRecap Report page opened or not
			if(driver.findElement(By.xpath(excel.getData(3, 798, 1))).getText().equalsIgnoreCase("Sale recap report"))
			{
				test.log(LogStatus.PASS, "SaleRecap Sale Report page loaded Successfully");
			
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));
			}
			else
			{
				test.log(LogStatus.FAIL, "SaleRecap Sale Report page loaded Failed");
			
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
			Thread.sleep(5000);wb.close();
			
		}
		
		@Test(enabled=false,priority=15)
		public void SaleRecap_Enterprise_Report_Method_For_Non_Close_Sales(WebDriver driver) throws Exception
		{
			 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

			Thread.sleep(8000);
			//Click the Select option
			driver.findElement(By.xpath(excel.getData(3, 755, 1))).click();
			//Enter the required filter
			driver.findElement(By.xpath(excel.getData(3, 756, 1))).sendKeys(Utility.getReportPropertyUser("Store"));
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 756, 1))).sendKeys(Keys.ENTER);
			
			
			Thread.sleep(1500);
			//Click the Select reacp Options
			driver.findElement(By.xpath(excel.getData(3, 799, 1))).click();
			//Enter the required option
			driver.findElement(By.xpath(excel.getData(3, 800, 1))).sendKeys("Non close sales");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 800, 1))).sendKeys(Keys.ENTER);
		
			Thread.sleep(1500);
			//Click the Employee Options
			driver.findElement(By.xpath(excel.getData(3, 801, 1))).click();
			//Enter the required option
			driver.findElement(By.xpath(excel.getData(3, 802, 1))).sendKeys("All");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 802, 1))).sendKeys(Keys.ENTER);
		
			Thread.sleep(1000);
			//Click the Run button
			driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
			
			long start = System.currentTimeMillis();
			
			WebDriverWait wait1=new WebDriverWait(driver, 30);
			WebElement ele11=driver.findElement(By.xpath("//b[.='CHECK STATS']/."));
		
			
			System.out.println("******************** Sale Recap Type as Non Close Sales ********************************");
			test.log(LogStatus.INFO, "******************** Sale Recap Type as Non Close Sales ********************************");
			
		
			//Check weather the Check Status 
			if(wait1.until(ExpectedConditions.visibilityOf(ele11)).isDisplayed())
			{
				test.log(LogStatus.PASS, "Check Stats field is available");
				long finish = System.currentTimeMillis();
				long totalTime = finish- start; 
				System.out.println("Time in Millisecomds : "+totalTime);
				double ActualfinishTime = TimeUnit.MILLISECONDS.toMinutes(totalTime);
//				long ActualfinishTimeDouble = ((totalTime%86400)%3600)/60;
				long ActualfinishTimeDouble1 = (totalTime/1000)/6;
				System.out.println("MilSEc to Mins basic conversion : "+ActualfinishTimeDouble1);
//				test.log(LogStatus.INFO, "Total Time : "+ActualfinishTimeDouble1);
				test.log(LogStatus.INFO, "Time in Millisecomds : "+totalTime);
	        	//Replace all commo's with empty space
	        	String expected1 = Utility.getReportPropertyUser("Check_Count").replace(",", "");
	        	System.out.println("CHCK : "+expected1);
	        	//Convert the String value of the Check_Count element into float value
	        	float expect1 = Float.parseFloat(expected1);  

				System.out.println("CHCKS : "+driver.findElement(By.xpath(excel.getData(3, 2229, 1))).getText());
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
					test.log(LogStatus.INFO, "Actual and Expected Check counts are different");
					
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
		        	
		        	test.log(LogStatus.INFO, "Check Count Value different is : "+different);	

				}
				
				Thread.sleep(5000);
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
		        	
		        	//Check weather the Gross Sales total is same or not
		        	if(driver.findElement(By.xpath(excel.getData(3, 764, 1))).getText().equals(Utility.getReportPropertyUser("Gross_Sales")))
		        	{
			        	test.log(LogStatus.PASS, "Actual and Expected Gross Sales Values are same");
			        	
			        	//Get the Total value of Gross Sales
			        	String actualText1 = driver.findElement(By.xpath(excel.getData(3, 764, 1))).getText();
			        	
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
			        	String actualText = driver.findElement(By.xpath(excel.getData(3, 764, 1))).getText();

			        	System.out.println("The Actual Gross Sales value is : "+actualText);
			        	
			        	test.log(LogStatus.INFO, "The Actual Gross Sales value is : "+actualText);
					}
			        
			        else
			        {
			        	test.log(LogStatus.INFO, "Actual and Expected Gross Sales values are different");
			        	
			        	//Get the Total value of Gross Sales
			        	String actualText1 = driver.findElement(By.xpath(excel.getData(3, 764, 1))).getText();
			        	
			        	//Replace all commo's with empty space
			        	String actualText= actualText1.replace(",", "");
			        	
			        	//Convert the String value of the Gross Sales Total element into float value
			        	float actual = Float.parseFloat(actualText);
			        			        	
			        	//Get the different
			        	float different = actual - expects1;
			        	
			        	//Print the different value
			        	System.out.println("Gross Sales Value different is : "+different);
			        	
			        	test.log(LogStatus.INFO, "Gross Sales Value different is : "+different);	
			        }
		        	
		        	//Check weather the Net Sales total is same or not
		        	if(driver.findElement(By.xpath(excel.getData(3, 765, 1))).getText().equals(Utility.getReportPropertyUser("Sale_Report_Net_Sale")))
		        	{
			        	test.log(LogStatus.PASS, "Actual and Expected Net Sales Values are same");
			        	
			        	//Get the Total value of Net Sales
			        	String actualText1 = driver.findElement(By.xpath(excel.getData(3, 765, 1))).getText();
			        	
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
			        	String actualText = driver.findElement(By.xpath(excel.getData(3, 765, 1))).getText();

			        	System.out.println("The Actual Net Sales value is : "+actualText);
			        	
			        	test.log(LogStatus.INFO, "The Actual Net Sales value is : "+actualText);
					}
			        
			        else
			        {
			        	test.log(LogStatus.INFO, "Actual and Expected Net Sales values are different");
			        	
			        	//Get the Total value of Gross Sales
			        	String actualText1 = driver.findElement(By.xpath(excel.getData(3, 765, 1))).getText();
			        	
			        	//Replace all commo's with empty space
			        	String actualText= actualText1.replace(",", "");
			        	
			        	//Convert the String value of the Net Sales Total element into float value
			        	float actual = Float.parseFloat(actualText);
			        			        	
			        	//Get the different
			        	float different = actual - expect2;
			        	
			        	//Print the different value
			        	System.out.println("Net Sales Value different is : "+different);
			        	
			        	test.log(LogStatus.INFO, "Net Sales Value different is : "+different);	
			        }
		        	
		        	//Check weather the Grand Sales total is same or not
		        	if(driver.findElement(By.xpath(excel.getData(3, 766, 1))).getText().equals(Utility.getReportPropertyUser("Sale_Report_Grand_Sale")))
		        	{
			        	test.log(LogStatus.PASS, "Actual and Expected Grand Sales Values are same");
			        	
			        	//Get the Total value of Grand Sales
			        	String actualText1 = driver.findElement(By.xpath(excel.getData(3, 766, 1))).getText();
			        	
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
			        	String actualText = driver.findElement(By.xpath(excel.getData(3, 766, 1))).getText();

			        	System.out.println("The Actual Grand Sales value is : "+actualText);
			        	
			        	test.log(LogStatus.INFO, "The Actual Grand Sales value is : "+actualText);
					}
			        
			        else
			        {
			        	test.log(LogStatus.INFO, "Actual and Expected Grand Sales values are different");
			        	
			        	//Get the Total value of Grand Sales
			        	String actualText1 = driver.findElement(By.xpath(excel.getData(3, 766, 1))).getText();
			        	
			        	//Replace all commo's with empty space
			        	String actualText= actualText1.replace(",", "");
			        	
			        	//Convert the String value of the Net Sales Total element into float value
			        	float actual = Float.parseFloat(actualText);
			        			        	
			        	//Get the different
			        	float different = actual - expect3;
			        	
			        	//Print the different value
			        	System.out.println("Grand Sales Value different is : "+different);
			        	
			        	test.log(LogStatus.INFO, "Grand Sales Value different is : "+different);	
			        }
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
					test.log(LogStatus.INFO, "Sales is not available");
					
					System.out.println("Sales is not available");
					
					
			    	
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
		        	String expected11 = Utility.getReportPropertyUser("Sale_Report_Rounding_Off").replace(",", "");
		        	
		        	//Convert the String value of the Rounding Off element into float value
		        	float expect11 = Float.parseFloat(expected11);  

					
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
					
					else if(expect11 == unknownValue)
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
			        	float different = actual - expect11;
			        	
			        	//Print the different value
			        	System.out.println("Rounding Off Value different is : "+different);
			        	
			        	test.log(LogStatus.FAIL, "Rounding Off Value different is : "+different);	

					}
			}
			}
			else
			{
				test.log(LogStatus.INFO, "Check Stats field is not available");
				
				
				
				Thread.sleep(5000);
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
		        	
		        	//Check weather the Gross Sales total is same or not
		        	if(driver.findElement(By.xpath(excel.getData(3, 764, 1))).getText().equals(Utility.getReportPropertyUser("Gross_Sales")))
		        	{
			        	test.log(LogStatus.PASS, "Actual and Expected Gross Sales Values are same");
			        	
			        	//Get the Total value of Gross Sales
			        	String actualText1 = driver.findElement(By.xpath(excel.getData(3, 764, 1))).getText();
			        	
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
			        	String actualText = driver.findElement(By.xpath(excel.getData(3, 764, 1))).getText();

			        	System.out.println("The Actual Gross Sales value is : "+actualText);
			        	
			        	test.log(LogStatus.INFO, "The Actual Gross Sales value is : "+actualText);
					}
			        
			        else
			        {
			        	test.log(LogStatus.INFO, "Actual and Expected Gross Sales values are different");
			        	
			        	//Get the Total value of Gross Sales
			        	String actualText1 = driver.findElement(By.xpath(excel.getData(3, 764, 1))).getText();
			        	
			        	//Replace all commo's with empty space
			        	String actualText= actualText1.replace(",", "");
			        	
			        	//Convert the String value of the Gross Sales Total element into float value
			        	float actual = Float.parseFloat(actualText);
			        			        	
			        	//Get the different
			        	float different = actual - expect1;
			        	
			        	//Print the different value
			        	System.out.println("Gross Sales Value different is : "+different);
			        	
			        	test.log(LogStatus.INFO, "Gross Sales Value different is : "+different);	
			        }
		        	
		        	//Check weather the Net Sales total is same or not
		        	if(driver.findElement(By.xpath(excel.getData(3, 765, 1))).getText().equals(Utility.getReportPropertyUser("Sale_Report_Net_Sale")))
		        	{
			        	test.log(LogStatus.PASS, "Actual and Expected Net Sales Values are same");
			        	
			        	//Get the Total value of Net Sales
			        	String actualText1 = driver.findElement(By.xpath(excel.getData(3, 765, 1))).getText();
			        	
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
			        	String actualText = driver.findElement(By.xpath(excel.getData(3, 765, 1))).getText();

			        	System.out.println("The Actual Net Sales value is : "+actualText);
			        	
			        	test.log(LogStatus.INFO, "The Actual Net Sales value is : "+actualText);
					}
			        
			        else
			        {
			        	test.log(LogStatus.INFO, "Actual and Expected Net Sales values are different");
			        	
			        	//Get the Total value of Gross Sales
			        	String actualText1 = driver.findElement(By.xpath(excel.getData(3, 765, 1))).getText();
			        	
			        	//Replace all commo's with empty space
			        	String actualText= actualText1.replace(",", "");
			        	
			        	//Convert the String value of the Net Sales Total element into float value
			        	float actual = Float.parseFloat(actualText);
			        			        	
			        	//Get the different
			        	float different = actual - expect2;
			        	
			        	//Print the different value
			        	System.out.println("Net Sales Value different is : "+different);
			        	
			        	test.log(LogStatus.INFO, "Net Sales Value different is : "+different);	
			        }
		        	
		        	//Check weather the Grand Sales total is same or not
		        	if(driver.findElement(By.xpath(excel.getData(3, 766, 1))).getText().equals(Utility.getReportPropertyUser("Sale_Report_Grand_Sale")))
		        	{
			        	test.log(LogStatus.PASS, "Actual and Expected Grand Sales Values are same");
			        	
			        	//Get the Total value of Grand Sales
			        	String actualText1 = driver.findElement(By.xpath(excel.getData(3, 766, 1))).getText();
			        	
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
			        	String actualText = driver.findElement(By.xpath(excel.getData(3, 766, 1))).getText();

			        	System.out.println("The Actual Grand Sales value is : "+actualText);
			        	
			        	test.log(LogStatus.INFO, "The Actual Grand Sales value is : "+actualText);
					}
			        
			        else
			        {
			        	test.log(LogStatus.INFO, "Actual and Expected Grand Sales values are different");
			        	
			        	//Get the Total value of Grand Sales
			        	String actualText1 = driver.findElement(By.xpath(excel.getData(3, 766, 1))).getText();
			        	
			        	//Replace all commo's with empty space
			        	String actualText= actualText1.replace(",", "");
			        	
			        	//Convert the String value of the Net Sales Total element into float value
			        	float actual = Float.parseFloat(actualText);
			        			        	
			        	//Get the different
			        	float different = actual - expect3;
			        	
			        	//Print the different value
			        	System.out.println("Grand Sales Value different is : "+different);
			        	
			        	test.log(LogStatus.INFO, "Grand Sales Value different is : "+different);	
			        }
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
					test.log(LogStatus.INFO, "Sales is not available");
					
					System.out.println("Sales is not available");
					
			    	
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
			System.out.println("******************** End of Sale Recap Type as Non Close Sales ********************************");
			test.log(LogStatus.INFO, "******************** End of Sale Recap Type as Non Close Sales ********************************");wb.close();
		}
				
		@Test(enabled=false,priority=16)
		public void SaleRecap_Enterprise_Report_Method_For_Timeperiod(WebDriver driver) throws Exception
		{

			File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			Thread.sleep(3000);
			//Click the Select option
			driver.findElement(By.xpath(excel.getData(3, 755, 1))).click();
			Thread.sleep(1000);
			//Enter the required filter
			driver.findElement(By.xpath(excel.getData(3, 756, 1))).sendKeys(Utility.getReportPropertyUser("Store"));
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 756, 1))).sendKeys(Keys.ENTER);
			
			
			Thread.sleep(1500);
			//Click the Select reacp Options
			driver.findElement(By.xpath(excel.getData(3, 799, 1))).click();
			//Enter the required option
			driver.findElement(By.xpath(excel.getData(3, 800, 1))).sendKeys("Time period");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 800, 1))).sendKeys(Keys.ENTER);
		
			Thread.sleep(1500);
			//Click the Employee Options
			driver.findElement(By.xpath(excel.getData(3, 801, 1))).click();
			//Enter the required option
			driver.findElement(By.xpath(excel.getData(3, 802, 1))).sendKeys("All");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 802, 1))).sendKeys(Keys.ENTER);
		
			Thread.sleep(3000);
			//Click the Time Period option
			driver.findElement(By.xpath(excel.getData(3, 803, 1))).click();
			//Enter the required Time Period
			driver.findElement(By.xpath(excel.getData(3, 804, 1))).sendKeys("Date Range");
			//Press the Enter Key
			driver.findElement(By.xpath(excel.getData(3, 804, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(2000);
			//Clear the date field
			driver.findElement(By.xpath(excel.getData(3, 805, 1))).clear();
			//Enter the date
			driver.findElement(By.xpath(excel.getData(3, 805, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));

			Thread.sleep(2000);
			//Clear the date field
			driver.findElement(By.xpath(excel.getData(3, 806, 1))).clear();
			//Enter the date
			driver.findElement(By.xpath(excel.getData(3, 806, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));

			
			Thread.sleep(1000);
			//Click the Run button
			driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
			
			long start = System.currentTimeMillis();
	    	
			WebDriverWait wait1=new WebDriverWait(driver, 30);
			WebElement ele11=driver.findElement(By.xpath("//b[.='CHECK STATS']/."));
			
			System.out.println("******************** Sale Recap Type as Time Period ********************************");
			test.log(LogStatus.INFO, "******************** Sale Recap Type as Time Period ********************************");
			
			
			//Check weather the Check Status 
			if(wait1.until(ExpectedConditions.visibilityOf(ele11)).isDisplayed())
			{
				test.log(LogStatus.PASS, "Check Stats field is available");
				long finish = System.currentTimeMillis();
				long totalTime = finish- start; 
				System.out.println("Time in Millisecomds : "+totalTime);
				double ActualfinishTime = TimeUnit.MILLISECONDS.toMinutes(totalTime);
//				long ActualfinishTimeDouble = ((totalTime%86400)%3600)/60;
				long ActualfinishTimeDouble1 = (totalTime/1000)/6;
				System.out.println("MilSEc to Mins basic conversion : "+ActualfinishTimeDouble1);
//				test.log(LogStatus.INFO, "Total Time : "+ActualfinishTimeDouble1);
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
				
				Thread.sleep(5000);
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
		        	
		        	//Check weather the Gross Sales total is same or not
		        	if(driver.findElement(By.xpath(excel.getData(3, 764, 1))).getText().equals(Utility.getReportPropertyUser("Gross_Sales")))
		        	{
			        	test.log(LogStatus.PASS, "Actual and Expected Gross Sales Values are same");
			        	
			        	//Get the Total value of Gross Sales
			        	String actualText1 = driver.findElement(By.xpath(excel.getData(3, 764, 1))).getText();
			        	
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
			        	String actualText = driver.findElement(By.xpath(excel.getData(3, 764, 1))).getText();

			        	System.out.println("The Actual Gross Sales value is : "+actualText);
			        	
			        	test.log(LogStatus.INFO, "The Actual Gross Sales value is : "+actualText);
					}
			        
			        else
			        {
			        	test.log(LogStatus.FAIL, "Actual and Expected Gross Sales values are different");
			        	
			        	//Get the Total value of Gross Sales
			        	String actualText1 = driver.findElement(By.xpath(excel.getData(3, 764, 1))).getText();
			        	
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
		        	if(driver.findElement(By.xpath(excel.getData(3, 765, 1))).getText().equals(Utility.getReportPropertyUser("Sale_Report_Net_Sale")))
		        	{
			        	test.log(LogStatus.PASS, "Actual and Expected Net Sales Values are same");
			        	
			        	//Get the Total value of Net Sales
			        	String actualText1 = driver.findElement(By.xpath(excel.getData(3, 765, 1))).getText();
			        	
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
			        	String actualText = driver.findElement(By.xpath(excel.getData(3, 765, 1))).getText();

			        	System.out.println("The Actual Net Sales value is : "+actualText);
			        	
			        	test.log(LogStatus.INFO, "The Actual Net Sales value is : "+actualText);
					}
			        
			        else
			        {
			        	test.log(LogStatus.FAIL, "Actual and Expected Net Sales values are different");
			        	
			        	//Get the Total value of Gross Sales
			        	String actualText1 = driver.findElement(By.xpath(excel.getData(3, 765, 1))).getText();
			        	
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
		        	if(driver.findElement(By.xpath(excel.getData(3, 766, 1))).getText().equals(Utility.getReportPropertyUser("Sale_Report_Grand_Sale")))
		        	{
			        	test.log(LogStatus.PASS, "Actual and Expected Grand Sales Values are same");
			        	
			        	//Get the Total value of Grand Sales
			        	String actualText1 = driver.findElement(By.xpath(excel.getData(3, 766, 1))).getText();
			        	
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
			        	String actualText = driver.findElement(By.xpath(excel.getData(3, 766, 1))).getText();

			        	System.out.println("The Actual Grand Sales value is : "+actualText);
			        	
			        	test.log(LogStatus.INFO, "The Actual Grand Sales value is : "+actualText);
					}
			        
			        else
			        {
			        	test.log(LogStatus.FAIL, "Actual and Expected Grand Sales values are different");
			        	
			        	//Get the Total value of Grand Sales
			        	String actualText1 = driver.findElement(By.xpath(excel.getData(3, 766, 1))).getText();
			        	
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
					test.log(LogStatus.INFO, "Sales is not available");
					
					System.out.println("Sales is not available");
					
			    	
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
		        	String expected11 = Utility.getReportPropertyUser("Sale_Report_Rounding_Off").replace(",", "");
		        	
		        	//Convert the String value of the Rounding Off element into float value
		        	float expect11 = Float.parseFloat(expected11);  

					
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
					
					else if(expect11 == unknownValue)
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
			        	float different = actual - expect11;
			        	
			        	//Print the different value
			        	System.out.println("Rounding Off Value different is : "+different);
			        	
			        	test.log(LogStatus.FAIL, "Rounding Off Value different is : "+different);	

					}
			}
			}
			else
			{
				test.log(LogStatus.FAIL, "Check Stats field is not available");
				
				
				
				Thread.sleep(5000);
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
		        	
		        	//Check weather the Gross Sales total is same or not
		        	if(driver.findElement(By.xpath(excel.getData(3, 764, 1))).getText().equals(Utility.getReportPropertyUser("Gross_Sales")))
		        	{
			        	test.log(LogStatus.PASS, "Actual and Expected Gross Sales Values are same");
			        	
			        	//Get the Total value of Gross Sales
			        	String actualText1 = driver.findElement(By.xpath(excel.getData(3, 764, 1))).getText();
			        	
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
			        	String actualText = driver.findElement(By.xpath(excel.getData(3, 764, 1))).getText();

			        	System.out.println("The Actual Gross Sales value is : "+actualText);
			        	
			        	test.log(LogStatus.INFO, "The Actual Gross Sales value is : "+actualText);
					}
			        
			        else
			        {
			        	test.log(LogStatus.FAIL, "Actual and Expected Gross Sales values are different");
			        	
			        	//Get the Total value of Gross Sales
			        	String actualText1 = driver.findElement(By.xpath(excel.getData(3, 764, 1))).getText();
			        	
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
		        	if(driver.findElement(By.xpath(excel.getData(3, 765, 1))).getText().equals(Utility.getReportPropertyUser("Sale_Report_Net_Sale")))
		        	{
			        	test.log(LogStatus.PASS, "Actual and Expected Net Sales Values are same");
			        	
			        	//Get the Total value of Net Sales
			        	String actualText1 = driver.findElement(By.xpath(excel.getData(3, 765, 1))).getText();
			        	
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
			        	String actualText = driver.findElement(By.xpath(excel.getData(3, 765, 1))).getText();

			        	System.out.println("The Actual Net Sales value is : "+actualText);
			        	
			        	test.log(LogStatus.INFO, "The Actual Net Sales value is : "+actualText);
					}
			        
			        else
			        {
			        	test.log(LogStatus.FAIL, "Actual and Expected Net Sales values are different");
			        	
			        	//Get the Total value of Gross Sales
			        	String actualText1 = driver.findElement(By.xpath(excel.getData(3, 765, 1))).getText();
			        	
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
		        	if(driver.findElement(By.xpath(excel.getData(3, 766, 1))).getText().equals(Utility.getReportPropertyUser("Sale_Report_Grand_Sale")))
		        	{
			        	test.log(LogStatus.PASS, "Actual and Expected Grand Sales Values are same");
			        	
			        	//Get the Total value of Grand Sales
			        	String actualText1 = driver.findElement(By.xpath(excel.getData(3, 766, 1))).getText();
			        	
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
			        	String actualText = driver.findElement(By.xpath(excel.getData(3, 766, 1))).getText();

			        	System.out.println("The Actual Grand Sales value is : "+actualText);
			        	
			        	test.log(LogStatus.INFO, "The Actual Grand Sales value is : "+actualText);
					}
			        
			        else
			        {
			        	test.log(LogStatus.FAIL, "Actual and Expected Grand Sales values are different");
			        	
			        	//Get the Total value of Grand Sales
			        	String actualText1 = driver.findElement(By.xpath(excel.getData(3, 766, 1))).getText();
			        	
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
					test.log(LogStatus.INFO, "Sales is not available");
					
					System.out.println("Sales is not available");
					
			    	
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
			
			System.out.println("******************** End of Sale Recap Type as Time Period ********************************");
			test.log(LogStatus.INFO, "******************** End of Sale Recap Type as Time Period ********************************");wb.close();
			
		}

}

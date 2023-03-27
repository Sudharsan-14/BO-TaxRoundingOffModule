package epicList_Chrome;

import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import epicList_Chrome.Utility;
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
				Browser a = new Browser();
				a.UPOS_login(driver, test);
			} else {
				Browser a = new Browser();
				a.Linga_login(driver, test);
			}
		}

		@Test(priority = 500)
		public void logout() throws Exception {
			Browser a = new Browser();
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
			//Check SaleRecap Report page opened or not
			if(driver.findElement(By.xpath(excel.getData(3, 798, 1))).getText().equalsIgnoreCase("Sale recap report"))
			{
				test.log(LogStatus.PASS, "SaleRecap Sale Report page loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "SaleRecap Sale Report page loaded Failed");
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

			Thread.sleep(3000);
			//Click the Select option
			driver.findElement(By.xpath(excel.getData(3, 755, 1))).click();
			//Enter the required filter
			driver.findElement(By.xpath(excel.getData(3, 756, 1))).sendKeys(Utility.getReportProperty("Store"));
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
			
			System.out.println("******************** Sale Recap Type as Non Close Sales ********************************");
			test.log(LogStatus.INFO, "******************** Sale Recap Type as Non Close Sales ********************************");
			
			Thread.sleep(15000);
			//Check weather the Check Status 
			if(driver.getPageSource().contains("CHECK STATS"))
			{
				test.log(LogStatus.PASS, "Check Stats field is available");
				
	        	//Replace all commo's with empty space
	        	String expected1 = Utility.getReportProperty("Check_Count").replace(",", "");
	        	System.out.println("CHCK : "+expected1);
	        	//Convert the String value of the Check_Count element into float value
	        	float expect1 = Float.parseFloat(expected1);  

				System.out.println("CHCKS : "+driver.findElement(By.xpath(excel.getData(3, 763, 1))).getText());
				Thread.sleep(2000);
				//Check the Check Count
				if(driver.findElement(By.xpath(excel.getData(3, 763, 1))).getText().equals(Utility.getReportProperty("Check_Count")))
				{
					test.log(LogStatus.PASS, "Actual and Expected check counts are same");
					
		        	//Get the Total value of Check Count
		        	String actualText1 = driver.findElement(By.xpath(excel.getData(3, 763, 1))).getText();
		        	
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
		        	String actualText = driver.findElement(By.xpath(excel.getData(3, 763, 1))).getText();

		        	System.out.println("The Actual Check Count value is : "+actualText);
		        	
		        	test.log(LogStatus.INFO, "The Actual Check Count value is : "+actualText);
				}
				
				else
				{
					test.log(LogStatus.FAIL, "Actual and Expected Check counts are different");
					
		        	//Get the Total value of Check Count
		        	String actualText1 = driver.findElement(By.xpath(excel.getData(3, 763, 1))).getText();
		        	
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
		        	String expecte1 = Utility.getReportProperty("Gross_Sales").replace(",", "");
		        	
		        	//Convert the String value of the Gross_Sales element into float value
		        	float expects1 = Float.parseFloat(expecte1);  
		        	
		        	//Replace all commo's with empty space
		        	String expected2 = Utility.getReportProperty("Sale_Report_Net_Sale").replace(",", "");
		        	
		        	//Convert the String value of the Sale_Report_Net_Sale element into float value
		        	float expect2 = Float.parseFloat(expected2);
		        	
		        	//Replace all commo's with empty space
		        	String expected3 = Utility.getReportProperty("Sale_Report_Grand_Sale").replace(",", "");
		        	
		        	//Convert the String value of the Sale_Report_Grand_Sale element into float value
		        	float expect3 = Float.parseFloat(expected3);
		        	
		        	//Check weather the Gross Sales total is same or not
		        	if(driver.findElement(By.xpath(excel.getData(3, 764, 1))).getText().equals(Utility.getReportProperty("Gross_Sales")))
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
		        	if(driver.findElement(By.xpath(excel.getData(3, 765, 1))).getText().equals(Utility.getReportProperty("Sale_Report_Net_Sale")))
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
		        	if(driver.findElement(By.xpath(excel.getData(3, 766, 1))).getText().equals(Utility.getReportProperty("Sale_Report_Grand_Sale")))
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
		        	
				}
				else
				{
					test.log(LogStatus.INFO, "Sales is not available");
					
					System.out.println("Sales is not available");
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
		        	String expected1 = Utility.getReportProperty("Gross_Sales").replace(",", "");
		        	
		        	//Convert the String value of the Gross_Sales element into float value
		        	float expect1 = Float.parseFloat(expected1);  
		        	
		        	//Replace all commo's with empty space
		        	String expected2 = Utility.getReportProperty("Sale_Report_Net_Sale").replace(",", "");
		        	
		        	//Convert the String value of the Sale_Report_Net_Sale element into float value
		        	float expect2 = Float.parseFloat(expected2);
		        	
		        	//Replace all commo's with empty space
		        	String expected3 = Utility.getReportProperty("Sale_Report_Grand_Sale").replace(",", "");
		        	
		        	//Convert the String value of the Sale_Report_Grand_Sale element into float value
		        	float expect3 = Float.parseFloat(expected3);
		        	
		        	//Check weather the Gross Sales total is same or not
		        	if(driver.findElement(By.xpath(excel.getData(3, 764, 1))).getText().equals(Utility.getReportProperty("Gross_Sales")))
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
		        	if(driver.findElement(By.xpath(excel.getData(3, 765, 1))).getText().equals(Utility.getReportProperty("Sale_Report_Net_Sale")))
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
		        	if(driver.findElement(By.xpath(excel.getData(3, 766, 1))).getText().equals(Utility.getReportProperty("Sale_Report_Grand_Sale")))
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
		        	
				}
				else
				{
					test.log(LogStatus.INFO, "Sales is not available");
					
					System.out.println("Sales is not available");
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
			//Enter the required filter
			driver.findElement(By.xpath(excel.getData(3, 756, 1))).sendKeys(Utility.getReportProperty("Store"));
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
			driver.findElement(By.xpath(excel.getData(3, 805, 1))).sendKeys(Utility.getReportProperty("Date_Range_From"));

			Thread.sleep(2000);
			//Clear the date field
			driver.findElement(By.xpath(excel.getData(3, 806, 1))).clear();
			//Enter the date
			driver.findElement(By.xpath(excel.getData(3, 806, 1))).sendKeys(Utility.getReportProperty("Date_Range_To"));

			
			Thread.sleep(1000);
			//Click the Run button
			driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
			
			System.out.println("******************** Sale Recap Type as Time Period ********************************");
			test.log(LogStatus.INFO, "******************** Sale Recap Type as Time Period ********************************");
			
			Thread.sleep(25000);
			//Check weather the Check Status 
			if(driver.getPageSource().contains("CHECK STATS"))
			{
				test.log(LogStatus.PASS, "Check Stats field is available");
				
	        	//Replace all commo's with empty space
	        	String expected1 = Utility.getReportProperty("Check_Count").replace(",", "");
	        	
	        	//Convert the String value of the Check_Count element into float value
	        	float expect1 = Float.parseFloat(expected1);  

				
				Thread.sleep(2000);
				//Check the Check Count
				if(driver.findElement(By.xpath(excel.getData(3, 763, 1))).getText().equals(Utility.getReportProperty("Check_Count")))
				{
					test.log(LogStatus.PASS, "Actual and Expected check counts are same");
					
		        	//Get the Total value of Check Count
		        	String actualText1 = driver.findElement(By.xpath(excel.getData(3, 763, 1))).getText();
		        	
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
		        	String actualText = driver.findElement(By.xpath(excel.getData(3, 763, 1))).getText();

		        	System.out.println("The Actual Check Count value is : "+actualText);
		        	
		        	test.log(LogStatus.INFO, "The Actual Check Count value is : "+actualText);
				}
				
				else
				{
					test.log(LogStatus.FAIL, "Actual and Expected Check counts are different");
					
		        	//Get the Total value of Check Count
		        	String actualText1 = driver.findElement(By.xpath(excel.getData(3, 763, 1))).getText();
		        	
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
		        	String expecte1 = Utility.getReportProperty("Gross_Sales").replace(",", "");
		        	
		        	//Convert the String value of the Gross_Sales element into float value
		        	float expects1 = Float.parseFloat(expecte1);  
		        	
		        	//Replace all commo's with empty space
		        	String expected2 = Utility.getReportProperty("Sale_Report_Net_Sale").replace(",", "");
		        	
		        	//Convert the String value of the Sale_Report_Net_Sale element into float value
		        	float expect2 = Float.parseFloat(expected2);
		        	
		        	//Replace all commo's with empty space
		        	String expected3 = Utility.getReportProperty("Sale_Report_Grand_Sale").replace(",", "");
		        	
		        	//Convert the String value of the Sale_Report_Grand_Sale element into float value
		        	float expect3 = Float.parseFloat(expected3);
		        	
		        	//Check weather the Gross Sales total is same or not
		        	if(driver.findElement(By.xpath(excel.getData(3, 764, 1))).getText().equals(Utility.getReportProperty("Gross_Sales")))
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
		        	if(driver.findElement(By.xpath(excel.getData(3, 765, 1))).getText().equals(Utility.getReportProperty("Sale_Report_Net_Sale")))
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
		        	if(driver.findElement(By.xpath(excel.getData(3, 766, 1))).getText().equals(Utility.getReportProperty("Sale_Report_Grand_Sale")))
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
		        	
				}
				else
				{
					test.log(LogStatus.INFO, "Sales is not available");
					
					System.out.println("Sales is not available");
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
		        	String expected1 = Utility.getReportProperty("Gross_Sales").replace(",", "");
		        	
		        	//Convert the String value of the Gross_Sales element into float value
		        	float expect1 = Float.parseFloat(expected1);  
		        	
		        	//Replace all commo's with empty space
		        	String expected2 = Utility.getReportProperty("Sale_Report_Net_Sale").replace(",", "");
		        	
		        	//Convert the String value of the Sale_Report_Net_Sale element into float value
		        	float expect2 = Float.parseFloat(expected2);
		        	
		        	//Replace all commo's with empty space
		        	String expected3 = Utility.getReportProperty("Sale_Report_Grand_Sale").replace(",", "");
		        	
		        	//Convert the String value of the Sale_Report_Grand_Sale element into float value
		        	float expect3 = Float.parseFloat(expected3);
		        	
		        	//Check weather the Gross Sales total is same or not
		        	if(driver.findElement(By.xpath(excel.getData(3, 764, 1))).getText().equals(Utility.getReportProperty("Gross_Sales")))
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
		        	if(driver.findElement(By.xpath(excel.getData(3, 765, 1))).getText().equals(Utility.getReportProperty("Sale_Report_Net_Sale")))
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
		        	if(driver.findElement(By.xpath(excel.getData(3, 766, 1))).getText().equals(Utility.getReportProperty("Sale_Report_Grand_Sale")))
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
		        	
				}
				else
				{
					test.log(LogStatus.INFO, "Sales is not available");
					
					System.out.println("Sales is not available");
				}
				

			}
			
			System.out.println("******************** End of Sale Recap Type as Time Period ********************************");
			test.log(LogStatus.INFO, "******************** End of Sale Recap Type as Time Period ********************************");wb.close();
			
		}

}

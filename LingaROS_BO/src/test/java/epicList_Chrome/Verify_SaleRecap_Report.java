package epicList_Chrome;

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

import epicList_Chrome.Utility;
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
				Browser a = new Browser();
				a.UPOS_login(driver, test);
			}
			else 			
			{
				Browser a = new Browser();
				a.Linga_login(driver, test);
			}	
		}
			
		@Test(priority=500)
		public void logout() throws Exception
		{		Browser a = new Browser();
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
				driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"report/saleRecap");
				
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
				driver.findElement(By.xpath(excel.getData(1, 755, 1))).click();
				//Enter the required department
				driver.findElement(By.xpath(excel.getData(1, 756, 1))).sendKeys("Time period");
				//Press the Enter Key
				driver.findElement(By.xpath(excel.getData(1, 756, 1))).sendKeys(Keys.ENTER);
			
				Thread.sleep(3000);
				//Click the Employee option
				driver.findElement(By.xpath(excel.getData(1, 757, 1))).click();
				//Enter the required employee
				driver.findElement(By.xpath(excel.getData(1, 758, 1))).sendKeys("All");
				//Press the Enter Key
				driver.findElement(By.xpath(excel.getData(1, 758, 1))).sendKeys(Keys.ENTER);
				
				Thread.sleep(3000);
				//Click the Time Period option
				driver.findElement(By.xpath(excel.getData(1, 759, 1))).click();
				//Enter the required Time Period
				driver.findElement(By.xpath(excel.getData(1, 760, 1))).sendKeys("Date Range");
				//Press the Enter Key
				driver.findElement(By.xpath(excel.getData(1, 760, 1))).sendKeys(Keys.ENTER);
				
				Thread.sleep(2000);
				//Clear the date field
				driver.findElement(By.xpath(excel.getData(1, 761, 1))).clear();
				//Enter the date
				driver.findElement(By.xpath(excel.getData(1, 761, 1))).sendKeys(Utility.getReportProperty("Date_Range_From"));

				Thread.sleep(2000);
				//Clear the date field
				driver.findElement(By.xpath(excel.getData(1, 762, 1))).clear();
				//Enter the date
				driver.findElement(By.xpath(excel.getData(1, 762, 1))).sendKeys(Utility.getReportProperty("Date_Range_To"));
				
				Thread.sleep(2000);
				//Click the Run Button
				driver.findElement(By.xpath(excel.getData(1, 572, 1))).click();
				
				Thread.sleep(3000);
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
					if(driver.findElement(By.xpath(excel.getData(3, 2229, 1))).getText().equals(Utility.getReportProperty("Check_Count")))
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
			        	
			        	test.log(LogStatus.FAIL, "Check Count Value different is : "+different);	

					}
					
					Thread.sleep(3000);
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
			        	if(driver.findElement(By.xpath(excel.getData(1, 764, 1))).getText().equals(Utility.getReportProperty("Gross_Sales")))
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
				        	test.log(LogStatus.INFO, "Actual and Expected Gross Sales values are different");
				        	
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
				        	
				        	test.log(LogStatus.INFO, "Gross Sales Value different is : "+different);	
				        }
			        	
			        	//Check weather the Net Sales total is same or not
			        	if(driver.findElement(By.xpath(excel.getData(1, 765, 1))).getText().equals(Utility.getReportProperty("Sale_Report_Net_Sale")))
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
				        	test.log(LogStatus.INFO, "Actual and Expected Net Sales values are different");
				        	
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
				        	
				        	test.log(LogStatus.INFO, "Net Sales Value different is : "+different);	
				        }
			        	
			        	//Check weather the Grand Sales total is same or not
			        	if(driver.findElement(By.xpath(excel.getData(1, 766, 1))).getText().equals(Utility.getReportProperty("Sale_Report_Grand_Sale")))
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
				        	test.log(LogStatus.INFO, "Actual and Expected Grand Sales values are different");
				        	
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
				        	
				        	test.log(LogStatus.INFO, "Grand Sales Value different is : "+different);	
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
			        	if(driver.findElement(By.xpath("//b[.='SALES']/../../../div[contains(.,'Gross Sales')]/span[2]")).getText().equals(Utility.getReportProperty("Gross_Sales")))
			        	{
				        	test.log(LogStatus.PASS, "Actual and Expected Gross Sales Values are same");
				        	
				        	//Get the Total value of Gross Sales
				        	String actualText1 = driver.findElement(By.xpath("//b[.='SALES']/../../../div[contains(.,'Gross Sales')]/span[2]")).getText();
				        	
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
				        	test.log(LogStatus.INFO, "Actual and Expected Gross Sales values are different");
				        	
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
				        	
				        	test.log(LogStatus.INFO, "Gross Sales Value different is : "+different);	
				        }
			        	
			        	//Check weather the Net Sales total is same or not
			        	if(driver.findElement(By.xpath(excel.getData(1, 765, 1))).getText().equals(Utility.getReportProperty("Sale_Report_Net_Sale")))
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
				        	test.log(LogStatus.INFO, "Actual and Expected Net Sales values are different");
				        	
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
				        	
				        	test.log(LogStatus.INFO, "Net Sales Value different is : "+different);	
				        }
			        	
			        	//Check weather the Grand Sales total is same or not
			        	if(driver.findElement(By.xpath(excel.getData(1, 766, 1))).getText().equals(Utility.getReportProperty("Sale_Report_Grand_Sale")))
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
				        	test.log(LogStatus.INFO, "Actual and Expected Grand Sales values are different");
				        	
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
				        	
				        	test.log(LogStatus.INFO, "Grand Sales Value different is : "+different);	
				        }
			        	
			        	driver.findElement(By.tagName("html")).sendKeys(Keys.END);
			        	
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
				}
				driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			}
	
	
}

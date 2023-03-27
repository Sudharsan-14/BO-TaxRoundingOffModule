package epicList_Chrome_Account_User;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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

import epicList_Chrome_Account_User.Utility;
import newReadExcelFile_New.ExcelDataConfig;

public class Verify_Discount_Report {

	public WebDriver driver;
	
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Verify_Discount_Report");

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
			Discount_Report_Method_OpenPage(driver);
			Discount_Report_Method_For_Summary(driver);
			Discount_Report_Method_For_ByEmployee(driver);
			Discount_Report_Method_For_ByDiscount(driver);
			Discount_Report_Method_For_ByDiscountType(driver);
			Thread.sleep(1500);
		}
		
			@Test(priority=12,enabled = false)
			public void Discount_Report_Method_OpenPage(WebDriver driver) throws Exception
			{
                File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				/*Thread.sleep(2000);
				WebElement html = driver.findElement(By.tagName("html"));
				html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
				html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
				html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
				html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
				
				//Click the Reports option
				driver.findElement(By.xpath("//span[.='Reports']")).click();
				// Create instance of Java script executor
				JavascriptExecutor je = (JavascriptExecutor) driver;
				//Identify the WebElement which will appear after scrolling down
				WebElement element = driver.findElement(By.xpath("//span[.='Discount']"));
				//Scroll the page till the Discount option present
				je.executeScript("arguments[0].scrollIntoView(true);",element); 
		        //Click the Discount Option		
				driver.findElement(By.xpath("//span[.='Discount']")).click(); */
				
				Thread.sleep(3000);
				//Enter the Users URL
				driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id1")+"report/discountReport");
				
				Thread.sleep(5000);
				try
				{
				//Check Discount Report page opened or not
				if(driver.findElement(By.xpath(excel.getData(3, 870, 1))).getText().equalsIgnoreCase("Discount Report"))
				{
					test.log(LogStatus.PASS, "Discount report page loaded Successfully");
				
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));
				}
				else
				{
					test.log(LogStatus.FAIL, "Discount report page loaded Failed");
				
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
				Thread.sleep(2000);wb.close();
		
		
			}
			
			@Test(priority=13,enabled = false)
			public void Discount_Report_Method_For_Summary(WebDriver driver) throws Exception
			{ 

                File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				Thread.sleep(5000);
				//Click the Report Type option
				driver.findElement(By.xpath(excel.getData(3, 871, 1))).click();
				Thread.sleep(1000);
				//Enter the required Report Type
				driver.findElement(By.xpath(excel.getData(3, 872, 1))).sendKeys("Summary");
				//Press the Enter Key
				driver.findElement(By.xpath(excel.getData(3, 872, 1))).sendKeys(Keys.ENTER);
		
				//Click the Discount option
				driver.findElement(By.xpath(excel.getData(3, 873, 1))).click();
				Thread.sleep(1000);
				//Enter the required Discount
				driver.findElement(By.xpath(excel.getData(3, 874, 1))).sendKeys("All");
				//Press the Enter Key
				driver.findElement(By.xpath(excel.getData(3, 874, 1))).sendKeys(Keys.ENTER);
		
				//Click the Sale created by option
				driver.findElement(By.xpath(excel.getData(2, 35, 1))).click();
				//Enter the required Employee
				driver.findElement(By.xpath(excel.getData(2, 36, 1))).sendKeys("All");
				//Press the Enter Key
				driver.findElement(By.xpath(excel.getData(2, 36, 1))).sendKeys(Keys.ENTER);
		
				//Click the Discount Coupon Type option
				driver.findElement(By.xpath(excel.getData(3, 875, 1))).click();
				//Enter the Required Coupon Type
				driver.findElement(By.xpath(excel.getData(3, 876, 1))).sendKeys("All");
				//Press the Enter Key
				driver.findElement(By.xpath(excel.getData(3, 876, 1))).sendKeys(Keys.ENTER);
		
				//Click the Time Period option
				driver.findElement(By.xpath(excel.getData(3, 877, 1))).click();
				//Enter the required Time Period
				driver.findElement(By.xpath(excel.getData(3, 878, 1))).sendKeys("Date Range");
				//Press the Enter Key
				driver.findElement(By.xpath(excel.getData(3, 878, 1))).sendKeys(Keys.ENTER);
				
				//Clear the date field
				driver.findElement(By.xpath(excel.getData(3, 879, 1))).clear();
				//Enter the date
				driver.findElement(By.xpath(excel.getData(3, 879, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
			
				//Clear the date field
				driver.findElement(By.xpath(excel.getData(3, 880, 1))).clear();
				//Enter the date
				driver.findElement(By.xpath(excel.getData(3, 880, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
				
				Thread.sleep(2000);
				//Click the Run Button
				driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
				
				Thread.sleep(3000);
				try
				{
					//Check weather the report is available for the selected time period
					if(driver.findElement(By.xpath(excel.getData(3, 704, 1))).isDisplayed())
					{
						test.log(LogStatus.FAIL, "Discount Report is not available for Specific Date");
					
						driver.findElement(By.tagName("html")).sendKeys(Keys.END);
						
						String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));

					}
				}
				catch(Exception fd)				
				{
					test.log(LogStatus.INFO, "******* The Below is Summary Report of Discount *******");
					
					System.out.println("******* The Below is Summary Report of Discount *******");
					
					test.log(LogStatus.PASS, "Discount Report available for Specific Date");
					
					//Get the Total discount value
					String total_Discount = driver.findElement(By.xpath(excel.getData(3, 881, 1))).getText();
					
					//Print the Total
					System.out.println("Total Discount is : "+total_Discount);
					
					test.log(LogStatus.INFO, "Total Discount is : "+total_Discount);
					
					//Get the Percentage of Total discount
					String percentage_Of_Total = driver.findElement(By.xpath(excel.getData(3, 882, 1))).getText();
					
					//Print the percentage_Of_Total
					System.out.println("Percentage Of Total Discount is : "+percentage_Of_Total);
					
					test.log(LogStatus.INFO, "Percentage Of Total Discount is : "+percentage_Of_Total);
					
					//Get the Count value
					String count = driver.findElement(By.xpath(excel.getData(3, 883, 1))).getText();
					
					//Print the count
					System.out.println("Total number of count is : "+count);
					
					test.log(LogStatus.INFO, "Total number of count is : "+count);
					
					//Get the Average discount value
					String average = driver.findElement(By.xpath(excel.getData(3, 884, 1))).getText();
					
					//Print the Average
					System.out.println("Average Discount is : "+average);
					
					test.log(LogStatus.INFO, "Average Discount is : "+average);
					
					//Get the number of rows
					List<WebElement> rows = driver.findElements(By.xpath(excel.getData(3, 885, 1)));
					
					//Get the Total Checks
					String total_Checks = driver.findElement(By.xpath(excel.getData(3, 886, 1))).getText();
					
					//Print the Total Checks
					System.out.println("Total Checks count is : "+total_Checks);
					
					test.log(LogStatus.INFO, "Total Checks count is : "+total_Checks);
					
					//Get the Percentage of Checks discounted
					String percentage_Of_Checks_discounted = driver.findElement(By.xpath(excel.getData(3, 887, 1))).getText();
					
					//Print the percentage_Of_Checks_discounted
					System.out.println("Percentage of Checks discounted is : "+percentage_Of_Checks_discounted);
					
					test.log(LogStatus.INFO, "Percentage of Checks discounted is : "+percentage_Of_Checks_discounted);
					
					//Get the Gross Sales
					String gross_Sales = driver.findElement(By.xpath(excel.getData(3, 888, 1))).getText();
					
					//Print the Gross Sales
					System.out.println("Total Gross Sales is : "+gross_Sales);
					
					test.log(LogStatus.INFO, "Total Gross Sales is : "+gross_Sales);
					
					//Get the percentage discount of gross sales
					String percentage_Discount_Of_Gross_Sales = driver.findElement(By.xpath(excel.getData(3, 889, 1))).getText();
					
					//Print the percentage discount of gross sales
					System.out.println("Percentage of Gross Sales is : "+percentage_Discount_Of_Gross_Sales);
					
					test.log(LogStatus.INFO, "Percentage of Gross Sales is : "+percentage_Discount_Of_Gross_Sales);
					
					//Get the Checks Discounted
					String checks_Discounted = driver.findElement(By.xpath(excel.getData(3, 890, 1))).getText();
					
					//Print the Checks Discounted
					System.out.println("Total Checks discounted is : "+checks_Discounted);
					
					test.log(LogStatus.INFO, "Total Checks discounted is : "+checks_Discounted);wb.close();
					wb.close();
				
				}
				driver.findElement(By.tagName("html")).sendKeys(Keys.END);
				
				//driver.findElement(By.tagName("html")).sendKeys(Keys.END);
				
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));

				Thread.sleep(2000);
				driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			}
			
			@Test(priority=14,enabled = false)
			public void Discount_Report_Method_For_ByEmployee(WebDriver driver) throws Exception
			{
                File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				Thread.sleep(5000);
				//Click the Report Type option
				driver.findElement(By.xpath(excel.getData(3, 871, 1))).click();
				//Enter the required Report Type
				driver.findElement(By.xpath(excel.getData(3, 872, 1))).sendKeys("By Employee");
				//Press the Enter Key
				driver.findElement(By.xpath(excel.getData(3, 872, 1))).sendKeys(Keys.ENTER);
		
				//Click the Discount option
				driver.findElement(By.xpath(excel.getData(3, 873, 1))).click();
				//Enter the required Discount
				driver.findElement(By.xpath(excel.getData(3, 874, 1))).sendKeys("All");
				//Press the Enter Key
				driver.findElement(By.xpath(excel.getData(3, 874, 1))).sendKeys(Keys.ENTER);
		
				//Click the Employee option
				driver.findElement(By.xpath(excel.getData(2, 35, 1))).click();
				//Enter the required Employee
				driver.findElement(By.xpath(excel.getData(2, 36, 1))).sendKeys("All");
				//Press the Enter Key
				driver.findElement(By.xpath(excel.getData(2, 36, 1))).sendKeys(Keys.ENTER);
			
				Thread.sleep(2000);
				//Click the Discount Coupon Type option
				driver.findElement(By.xpath(excel.getData(3, 875, 1))).click();
				//Enter the Required Coupon Type
				driver.findElement(By.xpath(excel.getData(3, 876, 1))).sendKeys("All");
				//Press the Enter Key
				driver.findElement(By.xpath(excel.getData(3, 876, 1))).sendKeys(Keys.ENTER);
		
				Thread.sleep(3000);
				//Click the Time Period option
				driver.findElement(By.xpath(excel.getData(3, 2157, 1))).click();
				//Enter the required Time Period
				driver.findElement(By.xpath(excel.getData(3, 2158, 1))).sendKeys("Date Range");
				//Press the Enter Key
				driver.findElement(By.xpath(excel.getData(3, 2158, 1))).sendKeys(Keys.ENTER);
				
				//Clear the date field
				driver.findElement(By.xpath(excel.getData(3, 1982, 1))).clear();
				//Enter the date
				driver.findElement(By.xpath(excel.getData(3, 1982, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
			
				//Clear the date field
				driver.findElement(By.xpath(excel.getData(3, 1983, 1))).clear();
				//Enter the date
				driver.findElement(By.xpath(excel.getData(3, 1983, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
				
				Thread.sleep(2000);
				//Click the Run Button
				driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
				
				Thread.sleep(3000);
				try
				{
					//Check weather the report is available for the selected time period
					if(driver.findElement(By.xpath(excel.getData(3, 704, 1))).isDisplayed())
					{
						test.log(LogStatus.FAIL, "Discount Report is not available for Specific Date");
					
						driver.findElement(By.tagName("html")).sendKeys(Keys.END);
						
						String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));

					}
				}
				catch(Exception fd)
				{
					test.log(LogStatus.INFO, "******* The Below is By Employee Report of Discount *******");
					
					System.out.println("******* The Below is By Employee Report of Discount *******");
					
					test.log(LogStatus.PASS, "Discount Report available for Specific Date");
					
					driver.findElement(By.tagName("html")).sendKeys(Keys.END);
					
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));

						//Get the number of rows
				/*	List<WebElement> rows = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[5]/table/tbody/tr"));
					
					//Get the Total discount value
					String total_Discount_amount = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[5]/table/tbody/tr["+rows.size()+"]/td[7]")).getText();
					
					//Print the Total
					System.out.println("Total Discount Amount is : "+total_Discount_amount);
					
					test.log(LogStatus.INFO, "Total Discount Amount is : "+total_Discount_amount);
					
					//Get the Quantity
					String quantity = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[5]/table/tbody/tr["+rows.size()+"]/td[8]")).getText();
					
					//Print the percentage_Of_Total
					System.out.println("Quantity is : "+quantity);
					
					test.log(LogStatus.INFO, "Quantity is : "+quantity);
	*/								
				//	Discount_Report_Search_Field_Discount_Report(driver);
					driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
					wb.close();
				}
			}
			
			@Test(priority=15,enabled = false)
			public void Discount_Report_Method_For_ByDiscount(WebDriver driver) throws Exception
			{
                File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				Thread.sleep(2000);
				//Click the Report Type option
				driver.findElement(By.xpath(excel.getData(3, 871, 1))).click();
				//Enter the required Report Type
				driver.findElement(By.xpath(excel.getData(3, 872, 1))).sendKeys("By Discount");
				//Press the Enter Key
				driver.findElement(By.xpath(excel.getData(3, 872, 1))).sendKeys(Keys.ENTER);
		
				//Click the Discount option
				driver.findElement(By.xpath(excel.getData(3, 873, 1))).click();
				//Enter the required Discount
				driver.findElement(By.xpath(excel.getData(3, 874, 1))).sendKeys("All");
				//Press the Enter Key
				driver.findElement(By.xpath(excel.getData(3, 874, 1))).sendKeys(Keys.ENTER);
		
				//Click the Employee option
				driver.findElement(By.xpath(excel.getData(2, 35, 1))).click();
				//Enter the required Employee
				driver.findElement(By.xpath(excel.getData(2, 36, 1))).sendKeys("All");
				//Press the Enter Key
				driver.findElement(By.xpath(excel.getData(2, 36, 1))).sendKeys(Keys.ENTER);
			
				//Click the Discount Coupon Type option
				driver.findElement(By.xpath(excel.getData(3, 875, 1))).click();
				//Enter the Required Coupon Type
				driver.findElement(By.xpath(excel.getData(3, 876, 1))).sendKeys("All");
				//Press the Enter Key
				driver.findElement(By.xpath(excel.getData(3, 876, 1))).sendKeys(Keys.ENTER);
		
				//Click the Time Period option
				driver.findElement(By.xpath(excel.getData(3, 2157, 1))).click();
				//Enter the required Time Period
				driver.findElement(By.xpath(excel.getData(3, 2158, 1))).sendKeys("Date Range");
				//Press the Enter Key
				driver.findElement(By.xpath(excel.getData(3, 2158, 1))).sendKeys(Keys.ENTER);
		
				//Clear the date field
				driver.findElement(By.xpath(excel.getData(3, 1982, 1))).clear();
				//Enter the date
				driver.findElement(By.xpath(excel.getData(3, 1982, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
		
				//Clear the date field
				driver.findElement(By.xpath(excel.getData(3, 1983, 1))).clear();
				//Enter the date
				driver.findElement(By.xpath(excel.getData(3, 1983, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
				
				Thread.sleep(2000);
				//Click the Run Button
				driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
				
				Thread.sleep(3000);
				try
				{
					//Check weather the report is available for the selected time period
					if(driver.findElement(By.xpath(excel.getData(3, 704, 1))).isDisplayed())
					{
						test.log(LogStatus.FAIL, "Discount Report is not available for Specific Date");
					//	driver.findElement(By.tagName("html")).sendKeys(Keys.END);
						
						String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));

					}
				}
				catch(Exception ret)
				{
					test.log(LogStatus.INFO, "******* The Below is By Discount Report of Discount *******");
					
					test.log(LogStatus.INFO, "By Discount Report is available");
						
					test.log(LogStatus.PASS, "Discount Report available for Specific Date");
					
				//	Discount_Report_Search_Field_Discount_Report(driver);wb.close();
				}
				wb.close();
			}
			
			@Test(priority=16,enabled = false)
			public void Discount_Report_Method_For_ByDiscountType(WebDriver driver) throws Exception
			{
                File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				Thread.sleep(2000);
				//Click the Report Type option
				driver.findElement(By.xpath(excel.getData(3, 871, 1))).click();
				//Enter the required Report Type
				driver.findElement(By.xpath(excel.getData(3, 872, 1))).sendKeys("By Discount Type");
				//Press the Enter Key
				driver.findElement(By.xpath(excel.getData(3, 872, 1))).sendKeys(Keys.ENTER);
		
				//Click the Discount option
				driver.findElement(By.xpath(excel.getData(3, 873, 1))).click();
				//Enter the required Discount
				driver.findElement(By.xpath(excel.getData(3, 874, 1))).sendKeys("All");
				//Press the Enter Key
				driver.findElement(By.xpath(excel.getData(3, 874, 1))).sendKeys(Keys.ENTER);
		
				//Click the Employee option
				driver.findElement(By.xpath(excel.getData(2, 35, 1))).click();
				//Enter the required Employee
				driver.findElement(By.xpath(excel.getData(2, 36, 1))).sendKeys("All");
				//Press the Enter Key
				driver.findElement(By.xpath(excel.getData(2, 36, 1))).sendKeys(Keys.ENTER);
			
				//Click the Discount Coupon Type option
				driver.findElement(By.xpath(excel.getData(3, 875, 1))).click();
				//Enter the Required Coupon Type
				driver.findElement(By.xpath(excel.getData(3, 876, 1))).sendKeys("All");
				//Press the Enter Key
				driver.findElement(By.xpath(excel.getData(3, 876, 1))).sendKeys(Keys.ENTER);
		
				//Click the Time Period option
				driver.findElement(By.xpath(excel.getData(3, 2157, 1))).click();
				//Enter the required Time Period
				driver.findElement(By.xpath(excel.getData(3, 2158, 1))).sendKeys("Date Range");
				//Press the Enter Key
				driver.findElement(By.xpath(excel.getData(3, 2158, 1))).sendKeys(Keys.ENTER);
				
				//Clear the date field
				driver.findElement(By.xpath(excel.getData(3, 1982, 1))).clear();
				//Enter the date
				driver.findElement(By.xpath(excel.getData(3, 1982, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
			
				//Clear the date field
				driver.findElement(By.xpath(excel.getData(3, 1983, 1))).clear();
				//Enter the date
				driver.findElement(By.xpath(excel.getData(3, 1983, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
				
				Thread.sleep(1000);
				//Click the Run Button
				driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
				
				Thread.sleep(3000);
				try
				{
					//Check weather the report is available for the selected time period
					if(driver.findElement(By.xpath(excel.getData(3, 704, 1))).isDisplayed())
					{
						test.log(LogStatus.FAIL, "Discount Report is not available for Specific Date");
					
						String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));
					}
				}
				catch(Exception vf)
				{
					test.log(LogStatus.INFO, "******* The Below is By Discount Type Report of Discount *******");
					
					test.log(LogStatus.INFO, "By Discount Type Report is available");
						
					test.log(LogStatus.PASS, "Discount Report available for Specific Date");
										
					//Discount_Report_Search_Field_Discount_Report(driver);wb.close();
//driver.findElement(By.tagName("html")).sendKeys(Keys.END);
					
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));

				}
				wb.close();
			}
			
			@Test(priority = 16,enabled=false)
			public void Discount_Report_Search_Field_Discount_Report(WebDriver driver) throws Exception
			{
				    File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
					
					FileInputStream fis = new FileInputStream(src);
					
					XSSFWorkbook wb = new XSSFWorkbook(fis);
					
					XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
					
					ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
					
					Thread.sleep(5000);
					//Get the number of rows
				List<WebElement> rows = driver.findElements(By.xpath(excel.getData(3, 885, 1)));
		
				int row = rows.size()-1;
				
				Thread.sleep(2000);
				//Get the Date
				String date = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[5]/table/tbody/tr["+row+"]/td[1]/span")).getText();
		
				Thread.sleep(2000);
				//Clear the Date field
				driver.findElement(By.xpath(excel.getData(3, 892, 1))).clear();
				//Enter the Required Date
				driver.findElement(By.xpath(excel.getData(3, 892, 1))).sendKeys(date);
				Thread.sleep(1000);
				
				//Check whether the required filter is working or not
				try
				{
					if(driver.findElement(By.xpath(excel.getData(3, 893, 1))).isDisplayed())
					{
						test.log(LogStatus.PASS, "Filter show the required data for date filter");
						
						//Get the number of rows
						List<WebElement> filter_Rows = driver.findElements(By.xpath(excel.getData(3, 885, 1)));
						
						if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[5]/table/tbody/tr["+filter_Rows.size()+"]/td[1]/span")).getText().equals(date))
						{}
						else
						{
							test.log(LogStatus.FAIL, "Unwanted data found for date filter");
						}
						
						Thread.sleep(2000);
						//Clear the date from the field
						driver.findElement(By.xpath(excel.getData(3, 892, 1))).clear();wb.close();
					}
				}
				catch(Exception e)
				{
					test.log(LogStatus.FAIL, "Filter not show the required data for date filter");
				}
				
				//Get the Check
				String check = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[5]/table/tbody/tr["+row+"]/td[2]/span")).getText();
		
				Thread.sleep(2000);
				//Clear the Check field
				driver.findElement(By.xpath(excel.getData(3, 896, 1))).clear();
				//Enter the Required Check
				driver.findElement(By.xpath(excel.getData(3, 896, 1))).sendKeys(check);
				Thread.sleep(1000);
				
				//Check whether the required filter is working or not
				try
				{
					if(driver.findElement(By.xpath(excel.getData(3, 893, 1))).isDisplayed())
					{
						test.log(LogStatus.PASS, "Filter show the required data for Check filter");
						
						//Get the number of rows
						List<WebElement> filter_Rows = driver.findElements(By.xpath(excel.getData(3, 885, 1)));
						
						if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[5]/table/tbody/tr["+filter_Rows.size()+"]/td[2]/span")).getText().equals(check))
						{}
						else
						{
							test.log(LogStatus.FAIL, "Unwanted data found for Check filter");
						}
						
						Thread.sleep(2000);
						//Clear the date from the field
						driver.findElement(By.xpath(excel.getData(3, 896, 1))).clear();
					}
				}
				catch(Exception e)
				{
					test.log(LogStatus.FAIL, "Filter not show the required data for check filter");
				}
				
				//Get the Employee
				String emp = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[5]/table/tbody/tr["+row+"]/td[3]/a")).getText();
		
				Thread.sleep(2000);
				//Clear the Employee field
				driver.findElement(By.xpath(excel.getData(3, 898, 1))).clear();
				//Enter the Required Employee
				driver.findElement(By.xpath(excel.getData(3, 898, 1))).sendKeys(emp);
				Thread.sleep(1000);
				
				//Check whether the required filter is working or not
				try
				{				
					if(driver.findElement(By.xpath(excel.getData(3, 893, 1))).isDisplayed())
					{
						test.log(LogStatus.PASS, "Filter show the required data for employee filter");
						
						//Get the number of rows
						List<WebElement> filter_Rows = driver.findElements(By.xpath(excel.getData(3, 885, 1)));
						
						if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[5]/table/tbody/tr["+filter_Rows.size()+"]/td[3]/a")).getText().equals(emp))
						{
							Thread.sleep(1000);
							//Click the First Employee from the user
							driver.findElement(By.xpath(excel.getData(3, 900, 1))).click();
							
							Thread.sleep(20000);
							
							//Get the number of check presents below the Employee
							List<WebElement> empCheck = driver.findElements(By.xpath(excel.getData(3, 901, 1)));
							int rowCnt = empCheck.size()-1;
							//Get the Check Number 
							String checkNum = driver.findElement(By.xpath("//div[@class='row']/table/tbody/tr["+rowCnt+"]/td[1]/a")).getText();
							
							//Get the Transaction Date
							String transactionDate = driver.findElement(By.xpath("//div[@class='row']/table/tbody/tr["+rowCnt+"]/td[3]/span")).getText();
		
							//Get the Net sales
							String netSales = driver.findElement(By.xpath("//div[@class='row']/table/tbody/tr["+rowCnt+"]/td[6]/span")).getText();
		
							//Get the Total Tax amount
							String totalTaxAmount = driver.findElement(By.xpath("//div[@class='row']/table/tbody/tr["+rowCnt+"]/td[7]/span")).getText();
		
							//Get the Inclusive Tax Amount
							String incTaxAmount = driver.findElement(By.xpath("//div[@class='row']/table/tbody/tr["+rowCnt+"]/td[8]/span")).getText();
		
							//Get the CC Service Charge
							String ccServiceCharge = driver.findElement(By.xpath("//div[@class='row']/table/tbody/tr["+rowCnt+"]/td[9]/span")).getText();
		
							//Get the Discount
							String disc = driver.findElement(By.xpath("//div[@class='row']/table/tbody/tr["+rowCnt+"]/td[10]/span")).getText();
		
							//Get the Gross Receipt
							String grossRecipt = driver.findElement(By.xpath("//div[@class='row']/table/tbody/tr["+rowCnt+"]/td[11]/span")).getText();
		
							Thread.sleep(1000);
							//Clear the Check Number
							driver.findElement(By.xpath(excel.getData(3, 910, 1))).clear();
							//Enter the Check Number
							driver.findElement(By.xpath(excel.getData(3, 910, 1))).sendKeys(checkNum);
							
							//Check whether the required filter is working or not
							try
							{
								if(driver.findElement(By.xpath(excel.getData(3, 901, 1))).isDisplayed())
								{
									test.log(LogStatus.PASS, "Filter show the required data for Check Number filter");
									
									//Get the number of rows
									List<WebElement> filter_Row = driver.findElements(By.xpath(excel.getData(3, 901, 1)));
									
									if(driver.findElement(By.xpath("//div[@class='row']/table/tbody/tr["+rowCnt+"]/td[1]/a")).getText().equals(checkNum))
									{
										//Click the First Check
										//driver.findElement(By.xpath("//div[@class='row']/table/tbody/tr[1]/td[1]/a")).click();
										
										Discount_Report_Method_Check_View(driver);
										
									}
									else
									{
										test.log(LogStatus.FAIL, "Unwanted data found for Check Number filter");
									}
									
									Thread.sleep(2000);
									//Clear the Check Number from the field
									driver.findElement(By.xpath(excel.getData(3, 910, 1))).clear();
								}
							}
							catch(Exception e)
							{
								test.log(LogStatus.FAIL, "Filter not show the required data for Check Number filter");
		
							}
								
							Thread.sleep(1000);
							//Clear the Transaction Date
							driver.findElement(By.xpath(excel.getData(3, 911, 1))).clear();
							//Enter the Check Number
							driver.findElement(By.xpath(excel.getData(3, 911, 1))).sendKeys(transactionDate);
							
							//Check whether the required filter is working or not
							try
							{
								if(driver.findElement(By.xpath(excel.getData(3, 901, 1))).isDisplayed())
								{
									test.log(LogStatus.PASS, "Filter show the required data for Transaction Date filter");
									
									//Get the number of rows
									List<WebElement> filter_Row = driver.findElements(By.xpath(excel.getData(3, 901, 1)));
									
									if(driver.findElement(By.xpath("//div[@class='row']/table/tbody/tr["+filter_Row.size()+"]/td[3]/span")).getText().equals(transactionDate))
									{}
									else
									{
										test.log(LogStatus.FAIL, "Unwanted data found for Transaction Date filter");
									}
									
									Thread.sleep(2000);
									//Clear the Transaction Date from the field
									driver.findElement(By.xpath(excel.getData(3, 911, 1))).clear();
								}
							}
							catch(Exception e)
							{
								test.log(LogStatus.FAIL, "Filter not show the required data for Transaction Date filter");
							}
							
							Thread.sleep(1000);
							//Clear the NetSales
							driver.findElement(By.xpath(excel.getData(3, 913, 1))).clear();
							//Enter the netSales
							driver.findElement(By.xpath(excel.getData(3, 913, 1))).sendKeys(netSales);
							
							//Check whether the required filter is working or not
							try
							{
								if(driver.findElement(By.xpath(excel.getData(3, 901, 1))).isDisplayed())
								{
									test.log(LogStatus.PASS, "Filter show the required data for NetSales filter");
									
									//Get the number of rows
									List<WebElement> filter_Row = driver.findElements(By.xpath(excel.getData(3, 901, 1)));
									
									if(driver.findElement(By.xpath("//div[@class='row']/table/tbody/tr["+filter_Row.size()+"]/td[6]/span")).getText().equals(netSales))
									{}
									else
									{
										test.log(LogStatus.FAIL, "Unwanted data found for NetSales filter");
									}
									
									Thread.sleep(2000);
									//Clear the NetSales from the field
									driver.findElement(By.xpath(excel.getData(3, 913, 1))).clear();
								}
							}
							catch(Exception e)
							{
								test.log(LogStatus.FAIL, "Filter not show the required data for NetSales filter");
							}
									
							Thread.sleep(1000);
							//Clear the Total Tax Amount
							driver.findElement(By.xpath(excel.getData(3, 915, 1))).clear();
							//Enter the Total Tax Amount
							driver.findElement(By.xpath(excel.getData(3, 915, 1))).sendKeys(totalTaxAmount);
							
							//Check whether the required filter is working or not
							try
							{
								if(driver.findElement(By.xpath(excel.getData(3, 901, 1))).isDisplayed())
								{
									test.log(LogStatus.PASS, "Filter show the required data for Total Tax Amount filter");
									
									//Get the number of rows
									List<WebElement> filter_Row = driver.findElements(By.xpath(excel.getData(3, 901, 1)));
									
									if(driver.findElement(By.xpath("//div[@class='row']/table/tbody/tr["+filter_Row.size()+"]/td[7]/span")).getText().equals(totalTaxAmount))
									{}
									else
									{
										test.log(LogStatus.FAIL, "Unwanted data found for Total Tax Amount filter");
									}
									
									Thread.sleep(2000);
									//Clear the Total Tax Amount from the field
									driver.findElement(By.xpath(excel.getData(3, 915, 1))).clear();
								}
							}
							catch(Exception e)
							{
								test.log(LogStatus.FAIL, "Filter not show the required data for Total Tax Amount filter");
							}
							
							Thread.sleep(1000);
							//Clear the Inclusive Tax Amount
							driver.findElement(By.xpath(excel.getData(3, 917, 1))).clear();
							//Enter the Inclusive Tax Amount
							driver.findElement(By.xpath(excel.getData(3, 917, 1))).sendKeys(incTaxAmount);
							
							//Check whether the required filter is working or not
							try
							{
								if(driver.findElement(By.xpath(excel.getData(3, 901, 1))).isDisplayed())
								{
									test.log(LogStatus.PASS, "Filter show the required data for Inclusive Tax Amount filter");
									
									//Get the number of rows
									List<WebElement> filter_Row = driver.findElements(By.xpath(excel.getData(3, 901, 1)));
									
									if(driver.findElement(By.xpath("//div[@class='row']/table/tbody/tr["+filter_Row.size()+"]/td[8]/span")).getText().equals(incTaxAmount))
									{}
									else
									{
										test.log(LogStatus.FAIL, "Unwanted data found for Inclusive Tax Amount filter");
									}
									
									Thread.sleep(2000);
									//Clear the Inclusive Tax Amount from the field
									driver.findElement(By.xpath(excel.getData(3, 917, 1))).clear();
								}
							}
							catch(Exception e)
							{
								test.log(LogStatus.FAIL, "Filter not show the required data for Inclusive Tax Amount filter");
							}
							
							Thread.sleep(1000);
							//Clear the CC Service Charge
							driver.findElement(By.xpath(excel.getData(3, 919, 1))).clear();
							//Enter the CC Service Charge
							driver.findElement(By.xpath(excel.getData(3, 919, 1))).sendKeys(ccServiceCharge);
							
							//Check whether the required filter is working or not
							try
							{
								if(driver.findElement(By.xpath(excel.getData(3, 901, 1))).isDisplayed())
								{
									test.log(LogStatus.PASS, "Filter show the required data for CC Service Charge filter");
									
									//Get the number of rows
									List<WebElement> filter_Row = driver.findElements(By.xpath(excel.getData(3, 901, 1)));
									
									if(driver.findElement(By.xpath("//div[@class='row']/table/tbody/tr["+filter_Row.size()+"]/td[9]/span")).getText().equals(ccServiceCharge))
									{}
									else
									{
										test.log(LogStatus.FAIL, "Unwanted data found for CC Service Charge filter");
									}
									
									Thread.sleep(2000);
									//Clear the CC Service Charge from the field
									driver.findElement(By.xpath(excel.getData(3, 919, 1))).clear();
								}
							}
							catch(Exception e)
							{
								test.log(LogStatus.FAIL, "Filter not show the required data for CC Service Charge filter");
							}
							
							Thread.sleep(1000);
							//Clear the Discount
							driver.findElement(By.xpath(excel.getData(3, 921, 1))).clear();
							//Enter the Discount
							driver.findElement(By.xpath(excel.getData(3, 921, 1))).sendKeys(disc);
							
							//Check whether the required filter is working or not
							try
							{
								if(driver.findElement(By.xpath(excel.getData(3, 901, 1))).isDisplayed())
								{
									test.log(LogStatus.PASS, "Filter show the required data for Discount filter");
									
									//Get the number of rows
									List<WebElement> filter_Row = driver.findElements(By.xpath(excel.getData(3, 901, 1)));
									
									if(driver.findElement(By.xpath("//div[@class='row']/table/tbody/tr["+filter_Row.size()+"]/td[10]/span")).getText().equals(disc))
									{}
									else
									{
										test.log(LogStatus.FAIL, "Unwanted data found for Discount filter");
									}
									
									Thread.sleep(2000);
									//Clear the Discount from the field
									driver.findElement(By.xpath(excel.getData(3, 921, 1))).clear();
								}
								
							}
							catch(Exception e)
							{
								test.log(LogStatus.FAIL, "Filter not show the required data for Discount filter");
							}
							
							Thread.sleep(1000);
							//Clear the Gross Receipt
							driver.findElement(By.xpath(excel.getData(3, 923, 1))).clear();
							//Enter the Gross Receipt
							driver.findElement(By.xpath(excel.getData(3, 923, 1))).sendKeys(grossRecipt);
							
							//Check whether the required filter is working or not
							try
							{
								if(driver.findElement(By.xpath(excel.getData(3, 901, 1))).isDisplayed())
								{
									test.log(LogStatus.PASS, "Filter show the required data for Gross Receipt filter");
									
									//Get the number of rows
									List<WebElement> filter_Row = driver.findElements(By.xpath(excel.getData(3, 901, 1)));
									
									if(driver.findElement(By.xpath("//div[@class='row']/table/tbody/tr["+filter_Row.size()+"]/td[11]/span")).getText().equals(grossRecipt))
									{}
									else
									{
										test.log(LogStatus.FAIL, "Unwanted data found for Gross Receipt filter");
									}
									
									Thread.sleep(2000);
									//Clear the Gross Receipt from the field
									driver.findElement(By.xpath(excel.getData(3, 923, 1))).clear();
								}
								
							}
							catch(Exception e)
							{
								test.log(LogStatus.FAIL, "Filter not show the required data for Gross Receipt filter");
							}
							
							Thread.sleep(5000);
							//Click the Back button
							driver.findElement(By.xpath(excel.getData(2, 44, 1))).click();
							Thread.sleep(3000);
													
						}
						else
						{
							test.log(LogStatus.FAIL, "Unwanted data found for Employee filter");
						}
											
						Thread.sleep(2000);
						//Clear the date from the field
						driver.findElement(By.xpath(excel.getData(3, 926, 1))).clear();
					}
				
				}
				catch(Exception e)
				{
					test.log(LogStatus.INFO, "Filter not show the required data for employee filter");
					
					Thread.sleep(5000);
					//Click the Back button
					driver.findElement(By.xpath(excel.getData(2, 44, 1))).click();
					Thread.sleep(3000);
					
					Thread.sleep(2000);
					//Clear the date from the field
					driver.findElement(By.xpath(excel.getData(3, 926, 1))).clear();

				}
				
				//Get the Reason
				String reason = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[5]/table/tbody/tr["+row+"]/td[5]/span")).getText();
				//System.out.println(reason);
				Thread.sleep(2000);
				//Clear the reason field
				driver.findElement(By.xpath(excel.getData(3, 928, 1))).clear();
				//Enter the Required reason
				driver.findElement(By.xpath(excel.getData(3, 928, 1))).sendKeys(reason);
				Thread.sleep(1000);
				
				//Check whether the required filter is working or not
				try
				{
					if(driver.findElement(By.xpath(excel.getData(3, 893, 1))).isDisplayed())
					{
						test.log(LogStatus.PASS, "Filter show the required data for reason filter");
						
						//Get the number of rows
						List<WebElement> filter_Rows = driver.findElements(By.xpath(excel.getData(3, 901, 1)));
						
						if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[5]/table/tbody/tr["+filter_Rows.size()+"]/td[5]/span")).getText().equals(reason))
						{}
						else
						{
							test.log(LogStatus.FAIL, "Unwanted data found for reason filter");
						}
						
						Thread.sleep(2000);
						//Clear the reason from the field
						driver.findElement(By.xpath(excel.getData(3, 928, 1))).clear();
					}
				}
				catch(Exception e)
				{
					test.log(LogStatus.FAIL, "Filter not show the required data for reason filter");
				}
				
				//Get the Discount Name
				String discName = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[5]/table/tbody/tr["+row+"]/td[6]/span")).getText();
		
				Thread.sleep(2000);
				//Clear the Discount Name field
				driver.findElement(By.xpath(excel.getData(3, 930, 1))).clear();
				//Enter the Required Discount Name
				driver.findElement(By.xpath(excel.getData(3, 930, 1))).sendKeys(discName);
				Thread.sleep(1000);
				
				//Check whether the required filter is working or not
				try
				{
					if(driver.findElement(By.xpath(excel.getData(3, 893, 1))).isDisplayed())
					{
						test.log(LogStatus.PASS, "Filter show the required data for Discount Name filter");
						
						//Get the number of rows
						List<WebElement> filter_Rows = driver.findElements(By.xpath(excel.getData(3, 901, 1)));
						
						if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[5]/table/tbody/tr["+row+"]/td[6]/span")).getText().equals(discName))
						{}
						else
						{
							test.log(LogStatus.FAIL, "Unwanted data found for Discount Name filter");
						
							//driver.findElement(By.tagName("html")).sendKeys(Keys.END);
							
							String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
							String s="data:image/png;base64,"+scnShot;
							test.log(LogStatus.INFO,test.addScreenCapture(s));

						}
						
						Thread.sleep(2000);
						//Clear the Discount name from the field
						driver.findElement(By.xpath(excel.getData(3,930, 1))).clear();
					}
//driver.findElement(By.tagName("html")).sendKeys(Keys.END);
					
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));

				}
				catch(Exception e)
				{
					test.log(LogStatus.FAIL, "Filter not show the required data for Discount Name filter");
				
//driver.findElement(By.tagName("html")).sendKeys(Keys.END);
					
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));

				
				}
				
				Thread.sleep(3000);
				//Get the Discount Type
				String discType = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[5]/table/tbody/tr["+row+"]/td[7]/span")).getText();
		
				Thread.sleep(2000);
				//Clear the Discount Type field
				driver.findElement(By.xpath(excel.getData(3, 934, 1))).clear();
				//Enter the Required Discount Type
				driver.findElement(By.xpath(excel.getData(3, 934, 1))).sendKeys(discType);
				Thread.sleep(1000);
				
				//Check whether the required filter is working or not
				try
				{
					if(driver.findElement(By.xpath(excel.getData(3, 893, 1))).isDisplayed())
					{
						test.log(LogStatus.PASS, "Filter show the required data for Discount Type filter");
						
						//Get the number of rows
						List<WebElement> filter_Rows = driver.findElements(By.xpath(excel.getData(3, 901, 1)));
						
						if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[5]/table/tbody/tr["+filter_Rows.size()+"]/td[7]/span")).getText().equals(discType))
						{}
						else
						{
							test.log(LogStatus.FAIL, "Unwanted data found for Discount Type filter");
						}
						
						Thread.sleep(2000);
						//Clear the Discount Type from the field
						driver.findElement(By.xpath(excel.getData(3, 934, 1))).clear();
					}
					//driver.findElement(By.tagName("html")).sendKeys(Keys.END);
					
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));

				}
				catch(Exception e)
				{
					test.log(LogStatus.FAIL, "Filter not show the required data for Discount Type filter");
				
					//driver.findElement(By.tagName("html")).sendKeys(Keys.END);
					
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));

				}
				
				//Get the Amount
				String amt = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[5]/table/tbody/tr["+row+"]/td[8]/span")).getText();
		
				Thread.sleep(2000);
				//Clear the Amount field
				driver.findElement(By.xpath(excel.getData(3, 937, 1))).clear();
				//Enter the Required Amount
				driver.findElement(By.xpath(excel.getData(3, 937, 1))).sendKeys(amt);
				Thread.sleep(1000);
				
				//Check whether the required filter is working or not
				try
				{
					if(driver.findElement(By.xpath(excel.getData(3, 893, 1))).isDisplayed())
					{
						test.log(LogStatus.PASS, "Filter show the required data for Amount filter");
						
						//Get the number of rows
						List<WebElement> filter_Rows = driver.findElements(By.xpath(excel.getData(3, 901, 1)));
						
						if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[5]/table/tbody/tr["+filter_Rows.size()+"]/td[8]/span")).getText().equals(amt))
						{}
						else
						{
							test.log(LogStatus.FAIL, "Unwanted data found for Amount filter");
						}
						
						Thread.sleep(2000);
						//Clear the Amount from the field
						driver.findElement(By.xpath(excel.getData(3, 937, 1))).clear();
					}
					//driver.findElement(By.tagName("html")).sendKeys(Keys.END);
					
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));

				}
				catch(Exception e)
				{
					test.log(LogStatus.FAIL, "Filter not show the required data for Amount filter");
				
					//driver.findElement(By.tagName("html")).sendKeys(Keys.END);
					
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));

				}
				
				//Get the Quantity
				String quan = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[5]/table/tbody/tr["+row+"]/td[9]/span")).getText();
		
				Thread.sleep(2000);
				//Clear the Quantity field
				driver.findElement(By.xpath(excel.getData(3, 940, 1))).clear();
				//Enter the Required Quantity
				driver.findElement(By.xpath(excel.getData(3, 940, 1))).sendKeys(quan);
				Thread.sleep(1000);
				
				//Check whether the required filter is working or not
				try
				{
					if(driver.findElement(By.xpath(excel.getData(3, 893, 1))).isDisplayed())
					{
						test.log(LogStatus.PASS, "Filter show the required data for Quantity filter");
						
						//Get the number of rows
						List<WebElement> filter_Rows = driver.findElements(By.xpath(excel.getData(3, 901, 1)));
						
						if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[5]/table/tbody/tr["+filter_Rows.size()+"]/td[9]/span")).getText().contains(quan))
						{}
						else
						{
							test.log(LogStatus.FAIL, "Unwanted data found for Quantity filter");
						}
						
						Thread.sleep(2000);
						//Clear the Quantity from the field
						driver.findElement(By.xpath(excel.getData(3, 940, 1))).clear();
					}
					//driver.findElement(By.tagName("html")).sendKeys(Keys.END);
					
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));

				}
				catch(Exception e)
				{
					test.log(LogStatus.FAIL, "Filter not show the required data for Quantity filter");wb.close();
//driver.findElement(By.tagName("html")).sendKeys(Keys.END);
					
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));

				}
				wb.close();
			}
		
			@Test(priority = 17,enabled=false)
			public void Discount_Report_Method_Check_View(WebDriver driver) throws Exception
		{
				 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
					
					FileInputStream fis = new FileInputStream(src);
					
					XSSFWorkbook wb = new XSSFWorkbook(fis);
					
					XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
					
					ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
					Thread.sleep(5000);
					List <WebElement> rowCnt=driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[5]/div[3]/table/tbody/tr"));
					
					Thread.sleep(3000);
			//Get the Check Number for particular User
			String ticketNum = driver.findElement(By.xpath("//div[@class='row']/table/tbody/tr["+rowCnt+"]/td[1]/a")).getText();
			//System.out.println("ticketNum"+ticketNum);
			
			//Get the Transaction date for particular User
			String trandate = driver.findElement(By.xpath("//div[@class='row']/table/tbody/tr["+rowCnt+"]/td[3]/span")).getText();
			//System.out.println("trandate"+trandate);
			
			//Get the Total Tax Amount for particular User
			String tTaxAmt = driver.findElement(By.xpath("//div[@class='row']/table/tbody/tr["+rowCnt+"]/td[7]/span")).getText().replaceAll("[a-zA-Z $ ₹ , :]", "");
			double atTaxAmt = Double.parseDouble(tTaxAmt);
			//System.out.println("atTaxAmt"+atTaxAmt);
			
			//Get the CC Service Charge for particular User
			String ccSCharge = driver.findElement(By.xpath("//div[@class='row']/table/tbody/tr["+rowCnt+"]/td[9]/span")).getText().replaceAll("[a-zA-Z $ ₹ , :]", "");
			double accSCharge = Double.parseDouble(ccSCharge);
			//System.out.println("accSCharge"+accSCharge);
	
			//Get the Discount for particular User
			String disco = driver.findElement(By.xpath("//div[@class='row']/table/tbody/tr["+rowCnt+"]/td[10]/span")).getText().replaceAll("[a-zA-Z $ ₹ , :]", "");
			double adisco = Double.parseDouble(disco);
			//System.out.println("adisco"+adisco);
			
			//Get the Gross Receipt for particular User
			String gRecipt = driver.findElement(By.xpath("//div[@class='row']/table/tbody/tr["+rowCnt+"]/td[11]/span")).getText().replaceAll("[a-zA-Z $ ₹ , :]", "");
			double agRecipt = Double.parseDouble(gRecipt);
			//System.out.println("agRecipt"+agRecipt);
			Thread.sleep(2000);
			
			
			//Click the Check
			driver.findElement(By.xpath(excel.getData(3, 943, 1))).click();
			
			Thread.sleep(2000);
	
			//Get the Check Number for particular User
			String eticketNum = driver.findElement(By.xpath(excel.getData(3, 944, 1))).getText();
			
			//Get the Transaction date for particular User
			String etrandate = driver.findElement(By.xpath(excel.getData(3, 945, 1))).getText();
			
			//Get the Total Tax Amount for particular User
			String etTaxAmt = driver.findElement(By.xpath(excel.getData(3, 946, 1))).getText().replaceAll("[a-zA-Z $ ₹ , :]", "");
			double aetTaxAmt = Double.parseDouble(etTaxAmt);
			
			//Get the CC Service Charge for particular User
			String eccSCharge = driver.findElement(By.xpath(excel.getData(3, 947, 1))).getText().replaceAll("[a-zA-Z $ ₹ , :]", "");
			double aeccSCharge = Double.parseDouble(eccSCharge);
			//System.out.println("TESTTSET : "+aeccSCharge);
			
			//Get the Discount for particular User
			String edisco = driver.findElement(By.xpath(excel.getData(3, 948, 1))).getText().replaceAll("[a-zA-Z $ ₹ , :]", "");
			double aedisco = Double.parseDouble(edisco);
			
			//Get the Gross Receipt for particular User
			String egRecipt = driver.findElement(By.xpath(excel.getData(3, 949, 1))).getText().replaceAll("[a-zA-Z $ ₹ , :]", "");
			double aegRecipt = Double.parseDouble(egRecipt);
			aegRecipt = aegRecipt+aeccSCharge;
			//System.out.println("TESTETSTESTT : "+aegRecipt);
			
			//Check Whether the Check Number is correct or not
			if(ticketNum.equals(eticketNum))
			{
				
			}
			else
			{
				test.log(LogStatus.FAIL, "Check Number is different between the user view screen and customer view screen");
			}
			
			//Check Whether the Transaction Date is correct or not
			if(trandate.equals(etrandate))
			{
				
			}
			else
			{
				test.log(LogStatus.FAIL, "Transaction is different between the user view screen and customer view screen");
			}
			
			//Check Whether the Total Tax Amount is correct or not
			if(atTaxAmt == aetTaxAmt)
			{
				
			}
			else
			{
				test.log(LogStatus.FAIL, "Total Tax Amount is different between the user view screen and customer view screen");
			}
			
			//Check Whether the CC Service Charge is correct or not
			if(accSCharge == aeccSCharge)
			{
				
			}
			else
			{
				test.log(LogStatus.FAIL, "CC Service Charge is different between the user view screen and customer view screen");
			}
			
			//Check Whether the Discount is correct or not
			if(adisco == aedisco)
			{
				
			}
			else
			{
				test.log(LogStatus.FAIL, "Discount is different between the user view screen and customer view screen");
			}
			
			//Check Whether the Gross Receipt is correct or not
			if(agRecipt == aegRecipt)
			{}
			else
			{
				test.log(LogStatus.FAIL, "Gross Receipt is different between the user view screen and customer view screen");
			}
		
			//Clear the mail Field
			driver.findElement(By.xpath(excel.getData(3, 778, 1))).clear();
			//Enter the email
			driver.findElement(By.xpath(excel.getData(3, 778, 1))).sendKeys("sappanimuthub@benseron.com");
			
			Thread.sleep(2000);
			//Click the Send Receipt button
			driver.findElement(By.xpath(excel.getData(3, 833, 1))).click();
			
			Thread.sleep(1500);
			if(driver.findElement(By.xpath(excel.getData(3, 835, 1))).getText().equalsIgnoreCase("Email receipt sent successfully"))
			{
				test.log(LogStatus.PASS, "Email receipt sent successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Email receipt sent fail");
			}
			//driver.findElement(By.tagName("html")).sendKeys(Keys.END);
			
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s="data:image/png;base64,"+scnShot;
			test.log(LogStatus.INFO,test.addScreenCapture(s));

			Thread.sleep(5000);
			//Click the Back button
			driver.findElement(By.xpath(excel.getData(3, 942, 1))).click();wb.close();
			Thread.sleep(3000);wb.close();
		}
}

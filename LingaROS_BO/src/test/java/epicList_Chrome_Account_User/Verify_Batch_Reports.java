package epicList_Chrome_Account_User;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
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
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import newReadExcelFile_New.ExcelDataConfig;

public class Verify_Batch_Reports {
	public WebDriver driver;

	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Verify_Batch_Reports");

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
			Thread.sleep(3000);
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
			Batch_Report_Method_OpenPage(driver);
			Batch_Report_Method_All_For_Date_Range(driver);
			Batch_Report_Method_Except_Previous_Node_For_Date_Range(driver);
			Batch_Report_Method_All_For_This_Week(driver);
			Batch_Report_Method_All_For_Last_Week(driver);
			Batch_Report_Method_All_For_Specific_Date(driver);
			Batch_Report_Method_Manual_For_Date_Range(driver);
			Batch_Report_Method_Auto_Batch_For_Date_Range(driver); 
			
			Thread.sleep(1500);
		}

		@Test(enabled=false,priority=4)
		public void Batch_Report_Method_OpenPage(WebDriver driver) throws Exception
		{
			File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		/*	//Click the Reports option
			driver.findElement(By.xpath("//span[.='Reports']")).click();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			// Create instance of Java script executor
			JavascriptExecutor je = (JavascriptExecutor) driver;
			//Identify the WebElement which will appear after scrolling down
			WebElement element = driver.findElement(By.xpath("//span[.='Batch']"));
			//Scroll the page till the Batch option present
			je.executeScript("arguments[0].scrollIntoView(true);",element); 
	        //Click the Batch Option		
			driver.findElement(By.xpath("//span[.='Batch']")).click(); */
			
			Thread.sleep(1500);
			//Enter the Users URL
			driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id1")+"batchReport");
			
			Thread.sleep(5000);
			try
			{
			//Check Batch page opened or not
			if(driver.findElement(By.xpath(excel.getData(3, 1039, 1))).getText().equalsIgnoreCase("Batch"))
			{
				test.log(LogStatus.PASS,"Batch Report page loaded Successfully");
				
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));
			}
			else
			{
				test.log(LogStatus.FAIL,"Batch Report page loaded Failed");
				
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
		
		@Test(enabled=false,priority=5)
		public void Batch_Report_Method_All_For_Date_Range(WebDriver driver) throws Exception
		{
            File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			Thread.sleep(2000);
			//Click the Node Option
			driver.findElement(By.xpath(excel.getData(3, 1040, 1))).click();
			//Clear the node option
			//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[1]/div/form/div[1]/div/ul/li/input")).clear();
			//Enter the node
			driver.findElement(By.xpath(excel.getData(3, 1041, 1))).sendKeys(Utility.getReportPropertyUser("Batch_Report_Node"));
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 1041, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(2000); 
			//Click the Employee Option
			driver.findElement(By.xpath(excel.getData(3, 1042, 1))).click();
			//Clear the Employee Option
			//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[1]/div/form/div[2]/div/ul/li/input")).clear();
			//Enter the Employee
			driver.findElement(By.xpath(excel.getData(3, 1043, 1))).sendKeys(Utility.getReportPropertyUser("Batch_Report_Employee"));
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 1043, 1))).sendKeys(Keys.ENTER);

			Thread.sleep(2000);
			//Click the Batch Type
			driver.findElement(By.xpath(excel.getData(3, 2324, 1))).click();
			//Select the Batch Type
			driver.findElement(By.xpath(excel.getData(3, 2325, 1))).sendKeys("All");
			//Enter the Batch Type
			driver.findElement(By.xpath(excel.getData(3, 2325, 1))).sendKeys(Keys.ENTER);
			
			
			Thread.sleep(2000);
			//Select the Time Period option
		    driver.findElement(By.xpath(excel.getData(3, 2157, 1))).click();
		    //Enter the required Period of time for Specific date
		    driver.findElement(By.xpath(excel.getData(3, 2158, 1))).sendKeys("Date Range");
		    //Press the Enter key
		    driver.findElement(By.xpath(excel.getData(3, 2158, 1))).sendKeys(Keys.ENTER);
		    Thread.sleep(2000);
		    
		   //Clear the Date Field
		    driver.findElement(By.xpath(excel.getData(3, 1982, 1))).clear();
		   //Enter the specific date
		    driver.findElement(By.xpath(excel.getData(3, 1982, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));

			   //Clear the Date Field
		    driver.findElement(By.xpath(excel.getData(3, 1983, 1))).clear();
		   //Enter the specific date
		    driver.findElement(By.xpath(excel.getData(3, 1983, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
		    
		    Thread.sleep(3000);
		    //Click the Run
		    driver.findElement(By.cssSelector(excel.getData(3, 974, 4))).click();
		    
		    
		    Thread.sleep(5000);
			try
			{
				//Check weather the report is available for the selected time period
				if(driver.findElement(By.xpath(excel.getData(3, 2355, 1))).getText().equalsIgnoreCase("No transaction for selected time period"))
				{
					test.log(LogStatus.FAIL, "Batch Report is not available for Specific Date");
				
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));

				
				}
				else
				{
					test.log(LogStatus.PASS, "Batch Report is available for Specific Date");
					
					List<WebElement> rows = driver.findElements(By.xpath(excel.getData(3, 856, 1)));
					
					int rowSize=rows.size();
					for(int i=2;i<=rowSize-2;i++)
					{
					List<WebElement> nodeList=driver.findElements(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr["+i+"]/td[9]"));
					
					for(WebElement node:nodeList)
					{
						if(node.getText().equalsIgnoreCase(Utility.getReportPropertyUser("Batch_Report_Node")))
						{
							test.log(LogStatus.PASS, "Node Number validated successfully");
						}
						else
						{
							test.log(LogStatus.FAIL, "Incorrect Node Found "+node.getText());
						}
					}
					
					}
					//Get the total records Value
					String total_Records = driver.findElement(By.xpath("//td[contains(.,'Total Records')]/../../tr["+rowSize+"]/td[1]")).getText();
					
					System.out.println("Total Records is : "+total_Records);
					
					test.log(LogStatus.INFO, "Total Records is : "+total_Records);
					
					//Get the payments Value
					String payment = driver.findElement(By.xpath("//td[contains(.,'Total Payment')]/../../tr["+rowSize+"]/td[3]")).getText();
					
					System.out.println("Total Payment is : "+payment);
					
					test.log(LogStatus.INFO, "Total Payment is : "+payment);
					
					//Get the Refund Value
					String refund = driver.findElement(By.xpath("//td[contains(.,'Total Refund')]/../../tr["+rowSize+"]/td[4]")).getText();
					
					System.out.println("Total Refund is : "+refund);
					
					test.log(LogStatus.INFO, "Total Refund is : "+refund);
					
					//Get the Tips Value
					String tip = driver.findElement(By.xpath("//td[contains(.,'Total Tips')]/../../tr["+rowSize+"]/td[5]")).getText();
					
					System.out.println("Total Tip is : "+tip);
					
					test.log(LogStatus.INFO, "Total Tip is : "+tip);
					
					//Get the grand total value
					String grand_total = driver.findElement(By.xpath("//td[contains(.,'Grand Total')]/../../tr["+rowSize+"]/td[6]")).getText();
					
					System.out.println("Grand Total is : "+grand_total);
					
					test.log(LogStatus.INFO, "Grand Total is : "+grand_total);wb.close();
					
					Thread.sleep(3000);
					
					driver.findElement(By.tagName("html")).sendKeys(Keys.END);
					
					

					Thread.sleep(3000);
					driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);

				}
			}
			catch(Exception fd)
			{
				
							
			}
		}
		
		
		
		@Test(enabled=false,priority=5)
		public void Batch_Report_Method_Except_Previous_Node_For_Date_Range(WebDriver driver) throws Exception
		{
            File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			Thread.sleep(2000);
			//Click the Node Option
			driver.findElement(By.xpath(excel.getData(3, 1040, 1))).click();
			
			List<WebElement> list=driver.findElements(By.xpath("//ul[@class='chosen-results']/li"));
			
			int liSize=list.size();
			
			for(int i=1;i<=liSize;i++)
			{
				driver.findElement(By.xpath(excel.getData(3, 1041, 1))).click();
				Thread.sleep(500);
				driver.findElement(By.xpath(excel.getData(3, 1041, 1))).sendKeys(Keys.ENTER);

			}
			//Clear the node option
			//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[1]/div/form/div[1]/div/ul/li/input")).clear();
		
			Thread.sleep(2000);
			//Enter the node
			driver.findElement(By.xpath("//span[.='"+Utility.getReportPropertyUser("Batch_Report_Node")+"']/../a")).click();
		//	.sendKeys();
			//Press the Enter button
			
			Thread.sleep(2000); 
			//Click the Employee Option
			driver.findElement(By.xpath(excel.getData(3, 1042, 1))).click();
			//Clear the Employee Option
			//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[1]/div/form/div[2]/div/ul/li/input")).clear();
			//Enter the Employee
			driver.findElement(By.xpath(excel.getData(3, 1043, 1))).sendKeys(Utility.getReportPropertyUser("Batch_Report_Employee"));
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 1043, 1))).sendKeys(Keys.ENTER);

			Thread.sleep(2000);
			//Click the Batch Type
			driver.findElement(By.xpath(excel.getData(3, 2324, 1))).click();
			//Select the Batch Type
			driver.findElement(By.xpath(excel.getData(3, 2325, 1))).sendKeys("All");
			//Enter the Batch Type
			driver.findElement(By.xpath(excel.getData(3, 2325, 1))).sendKeys(Keys.ENTER);
			
			
			Thread.sleep(2000);
			//Select the Time Period option
		    driver.findElement(By.xpath(excel.getData(3, 2157, 1))).click();
		    //Enter the required Period of time for Specific date
		    driver.findElement(By.xpath(excel.getData(3, 2158, 1))).sendKeys("Date Range");
		    //Press the Enter key
		    driver.findElement(By.xpath(excel.getData(3, 2158, 1))).sendKeys(Keys.ENTER);
		    Thread.sleep(2000);
		    
		   //Clear the Date Field
		    driver.findElement(By.xpath(excel.getData(3, 1982, 1))).clear();
		   //Enter the specific date
		    driver.findElement(By.xpath(excel.getData(3, 1982, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));

			   //Clear the Date Field
		    driver.findElement(By.xpath(excel.getData(3, 1983, 1))).clear();
		   //Enter the specific date
		    driver.findElement(By.xpath(excel.getData(3, 1983, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
		    
		    Thread.sleep(3000);
		    //Click the Run
		    driver.findElement(By.cssSelector(excel.getData(3, 974, 4))).click();
		    
		    
		    Thread.sleep(5000);
			try
			{
				//Check weather the report is available for the selected time period
				if(driver.findElement(By.xpath(excel.getData(3, 2355, 1))).getText().equalsIgnoreCase("No transaction for selected time period"))
				{
					test.log(LogStatus.FAIL, "Batch Report is not available for Specific Date");
				
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));

				
				}
				else
				{
					test.log(LogStatus.PASS, "Batch Report is available for Specific Date");
					
					List<WebElement> rows = driver.findElements(By.xpath(excel.getData(3, 856, 1)));
					
					int rowSize=rows.size();
					
					//Get the total records Value
					String total_Records = driver.findElement(By.xpath("//td[contains(.,'Total Records')]/../../tr["+rowSize+"]/td[1]")).getText();
					
					System.out.println("Total Records is : "+total_Records);
					
					test.log(LogStatus.INFO, "Total Records is : "+total_Records);
					
					//Get the payments Value
					String payment = driver.findElement(By.xpath("//td[contains(.,'Total Payment')]/../../tr["+rowSize+"]/td[3]")).getText();
					
					System.out.println("Total Payment is : "+payment);
					
					test.log(LogStatus.INFO, "Total Payment is : "+payment);
					
					//Get the Refund Value
					String refund = driver.findElement(By.xpath("//td[contains(.,'Total Refund')]/../../tr["+rowSize+"]/td[4]")).getText();
					
					System.out.println("Total Refund is : "+refund);
					
					test.log(LogStatus.INFO, "Total Refund is : "+refund);
					
					//Get the Tips Value
					String tip = driver.findElement(By.xpath("//td[contains(.,'Total Tips')]/../../tr["+rowSize+"]/td[5]")).getText();
					
					System.out.println("Total Tip is : "+tip);
					
					test.log(LogStatus.INFO, "Total Tip is : "+tip);
					
					//Get the grand total value
					String grand_total = driver.findElement(By.xpath("//td[contains(.,'Grand Total')]/../../tr["+rowSize+"]/td[6]")).getText();
					
					System.out.println("Grand Total is : "+grand_total);
					
					test.log(LogStatus.INFO, "Grand Total is : "+grand_total);wb.close();
					
					Thread.sleep(3000);
					
					driver.findElement(By.tagName("html")).sendKeys(Keys.END);
					
					

					Thread.sleep(3000);
					driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);

				}
			}
			catch(Exception fd)
			{
				
							
			}
		}
		
		
		@Test(enabled=false,priority=6)
		public void Batch_Report_Method_All_For_This_Week(WebDriver driver) throws Exception
		{
            File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			Thread.sleep(2000);
			//Click the Node Option
			driver.findElement(By.xpath(excel.getData(3, 1040, 1))).click();
			//Clear the node option
			//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[1]/div/form/div[1]/div/ul/li/input")).clear();
			//Enter the node
			driver.findElement(By.xpath(excel.getData(3, 1041, 1))).sendKeys(Utility.getReportPropertyUser("Batch_Report_Node"));
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 1041, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(3000);
			//Click the Employee Option
			driver.findElement(By.xpath(excel.getData(3, 1042, 1))).click();
			//Clear the Employee Option
			//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[1]/div/form/div[2]/div/ul/li/input")).clear();
			//Enter the Employee
			driver.findElement(By.xpath(excel.getData(3, 1043, 1))).sendKeys(Utility.getReportPropertyUser("Batch_Report_Employee"));
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 1043, 1))).sendKeys(Keys.ENTER);

			Thread.sleep(2000);
			//Click the Batch Type
			driver.findElement(By.xpath(excel.getData(3, 2324, 1))).click();
			//Select the Batch Type
			driver.findElement(By.xpath(excel.getData(3, 2325, 1))).sendKeys("All");
			//Enter the Batch Type
			driver.findElement(By.xpath(excel.getData(3, 2325, 1))).sendKeys(Keys.ENTER);
			
			
			Thread.sleep(2000);
			//Select the Time Period option
		    driver.findElement(By.xpath(excel.getData(3, 2157, 1))).click();
		    //Enter the required Period of time for Specific date
		    driver.findElement(By.xpath(excel.getData(3, 2158, 1))).sendKeys("This Week");
		    //Press the Enter key
		    driver.findElement(By.xpath(excel.getData(3, 2158, 1))).sendKeys(Keys.ENTER);
		    Thread.sleep(2000);
		    

		    Thread.sleep(3000);
		    //Click the Run
		    driver.findElement(By.cssSelector(excel.getData(3, 974, 4))).click();
		    
		    
		    Thread.sleep(3000);
			try
			{
				//Check weather the report is available for the selected time period
				if(driver.findElement(By.xpath(excel.getData(3, 2355, 1))).isDisplayed())
				{
					test.log(LogStatus.FAIL, "Batch Report is not available for Specific Date");
				
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));

				
				}
				else
				{
					test.log(LogStatus.PASS, "Batch Report is available for Specific Date");
					
					List<WebElement> rows = driver.findElements(By.xpath(excel.getData(3, 856, 1)));
					
					int rowSize=rows.size();
					
					//Get the total records Value
					String total_Records = driver.findElement(By.xpath("//td[contains(.,'Total Records')]/../../tr["+rowSize+"]/td[1]")).getText();
					
					System.out.println("Total Records is : "+total_Records);
					
					test.log(LogStatus.INFO, "Total Records is : "+total_Records);
					
					//Get the payments Value
					String payment = driver.findElement(By.xpath("//td[contains(.,'Total Payment')]/../../tr["+rowSize+"]/td[3]")).getText();
					
					System.out.println("Total Payment is : "+payment);
					
					test.log(LogStatus.INFO, "Total Payment is : "+payment);
					
					//Get the Refund Value
					String refund = driver.findElement(By.xpath("//td[contains(.,'Total Refund')]/../../tr["+rowSize+"]/td[4]")).getText();
					
					System.out.println("Total Refund is : "+refund);
					
					test.log(LogStatus.INFO, "Total Refund is : "+refund);
					
					//Get the Tips Value
					String tip = driver.findElement(By.xpath("//td[contains(.,'Total Tips')]/../../tr["+rowSize+"]/td[5]")).getText();
					
					System.out.println("Total Tip is : "+tip);
					
					test.log(LogStatus.INFO, "Total Tip is : "+tip);
					
					//Get the grand total value
					String grand_total = driver.findElement(By.xpath("//td[contains(.,'Grand Total')]/../../tr["+rowSize+"]/td[6]")).getText();
					
					System.out.println("Grand Total is : "+grand_total);
					
					test.log(LogStatus.INFO, "Grand Total is : "+grand_total);wb.close();
					
					Thread.sleep(3000);
					
					driver.findElement(By.tagName("html")).sendKeys(Keys.END);
					
					

					Thread.sleep(3000);
					driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
					
				

				}
			}
			catch(Exception fd)
			{
				
			}
		}
		
		@Test(enabled=false,priority=7)
		public void Batch_Report_Method_All_For_Last_Week(WebDriver driver) throws Exception
		{
            File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			Thread.sleep(2000);
			//Click the Node Option
			driver.findElement(By.xpath(excel.getData(3, 1040, 1))).click();
			//Clear the node option
			//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[1]/div/form/div[1]/div/ul/li/input")).clear();
			//Enter the node
			driver.findElement(By.xpath(excel.getData(3, 1041, 1))).sendKeys(Utility.getReportPropertyUser("Batch_Report_Node"));
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 1041, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(3000);
			//Click the Employee Option
			driver.findElement(By.xpath(excel.getData(3, 1042, 1))).click();
			//Clear the Employee Option
			//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[1]/div/form/div[2]/div/ul/li/input")).clear();
			//Enter the Employee
			driver.findElement(By.xpath(excel.getData(3, 1043, 1))).sendKeys(Utility.getReportPropertyUser("Batch_Report_Employee"));
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 1043, 1))).sendKeys(Keys.ENTER);

			Thread.sleep(2000);
			//Click the Batch Type
			driver.findElement(By.xpath(excel.getData(3, 2324, 1))).click();
			//Select the Batch Type
			driver.findElement(By.xpath(excel.getData(3, 2325, 1))).sendKeys("All");
			//Enter the Batch Type
			driver.findElement(By.xpath(excel.getData(3, 2325, 1))).sendKeys(Keys.ENTER);
			
			
			Thread.sleep(2000);
			//Select the Time Period option
		    driver.findElement(By.xpath(excel.getData(3, 2157, 1))).click();
		    //Enter the required Period of time for Specific date
		    driver.findElement(By.xpath(excel.getData(3, 2158, 1))).sendKeys("Last Week");
		    //Press the Enter key
		    driver.findElement(By.xpath(excel.getData(3, 2158, 1))).sendKeys(Keys.ENTER);
		    Thread.sleep(2000);
		    

		    Thread.sleep(3000);
		    //Click the Run
		    driver.findElement(By.cssSelector(excel.getData(3, 974, 4))).click();
		    
		    
		    Thread.sleep(3000);
			try
			{
				//Check weather the report is available for the selected time period
				if(driver.findElement(By.xpath(excel.getData(3, 2355, 1))).isDisplayed())
				{
					test.log(LogStatus.FAIL, "Batch Report is not available for Specific Date");
				
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));

				
				}
				else
				{
					test.log(LogStatus.PASS, "Batch Report is available for Specific Date");
					
					List<WebElement> rows = driver.findElements(By.xpath(excel.getData(3, 856, 1)));
					
					int rowSize=rows.size();
					
					//Get the total records Value
					String total_Records = driver.findElement(By.xpath("//td[contains(.,'Total Records')]/../../tr["+rowSize+"]/td[1]")).getText();
					
					System.out.println("Total Records is : "+total_Records);
					
					test.log(LogStatus.INFO, "Total Records is : "+total_Records);
					
					//Get the payments Value
					String payment = driver.findElement(By.xpath("//td[contains(.,'Total Payment')]/../../tr["+rowSize+"]/td[3]")).getText();
					
					System.out.println("Total Payment is : "+payment);
					
					test.log(LogStatus.INFO, "Total Payment is : "+payment);
					
					//Get the Refund Value
					String refund = driver.findElement(By.xpath("//td[contains(.,'Total Refund')]/../../tr["+rowSize+"]/td[4]")).getText();
					
					System.out.println("Total Refund is : "+refund);
					
					test.log(LogStatus.INFO, "Total Refund is : "+refund);
					
					//Get the Tips Value
					String tip = driver.findElement(By.xpath("//td[contains(.,'Total Tips')]/../../tr["+rowSize+"]/td[5]")).getText();
					
					System.out.println("Total Tip is : "+tip);
					
					test.log(LogStatus.INFO, "Total Tip is : "+tip);
					
					//Get the grand total value
					String grand_total = driver.findElement(By.xpath("//td[contains(.,'Grand Total')]/../../tr["+rowSize+"]/td[6]")).getText();
					
					System.out.println("Grand Total is : "+grand_total);
					
					test.log(LogStatus.INFO, "Grand Total is : "+grand_total);wb.close();
					
					Thread.sleep(3000);
					
					driver.findElement(By.tagName("html")).sendKeys(Keys.END);
					
					

					Thread.sleep(3000);
					driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
					

				}
			}
			catch(Exception fd)
			{
				
			}
		}
		

		@Test(enabled=false,priority=8)
		public void Batch_Report_Method_All_For_Specific_Date(WebDriver driver) throws Exception
		{
            File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			Thread.sleep(2000);
			//Click the Node Option
			driver.findElement(By.xpath(excel.getData(3, 1040, 1))).click();
			//Clear the node option
			//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[1]/div/form/div[1]/div/ul/li/input")).clear();
			//Enter the node
			driver.findElement(By.xpath(excel.getData(3, 1041, 1))).sendKeys(Utility.getReportPropertyUser("Batch_Report_Node"));
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 1041, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(3000);
			//Click the Employee Option
			driver.findElement(By.xpath(excel.getData(3, 1042, 1))).click();
			//Clear the Employee Option
			//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[1]/div/form/div[2]/div/ul/li/input")).clear();
			//Enter the Employee
			driver.findElement(By.xpath(excel.getData(3, 1043, 1))).sendKeys(Utility.getReportPropertyUser("Batch_Report_Employee"));
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 1043, 1))).sendKeys(Keys.ENTER);

			Thread.sleep(2000);
			//Click the Batch Type
			driver.findElement(By.xpath(excel.getData(3, 2324, 1))).click();
			//Select the Batch Type
			driver.findElement(By.xpath(excel.getData(3, 2325, 1))).sendKeys("All");
			//Enter the Batch Type
			driver.findElement(By.xpath(excel.getData(3, 2325, 1))).sendKeys(Keys.ENTER);
			
			
			Thread.sleep(2000);
			//Select the Time Period option
		    driver.findElement(By.xpath(excel.getData(3, 2157, 1))).click();
		    //Enter the required Period of time for Specific date
		    driver.findElement(By.xpath(excel.getData(3, 2158, 1))).sendKeys("Specific Date");
		    //Press the Enter key
		    driver.findElement(By.xpath(excel.getData(3, 2158, 1))).sendKeys(Keys.ENTER);
		    Thread.sleep(2000);
		    
		    Thread.sleep(1000);
		    //Clear the Date
		    driver.findElement(By.xpath(excel.getData(3, 2326, 1))).clear();
		    //Enter the Specific Date
		    driver.findElement(By.xpath(excel.getData(3, 2326, 1))).sendKeys(Utility.getReportPropertyUser("Specific_Date"));

		    Thread.sleep(3000);
		    //Click the Run
		    driver.findElement(By.cssSelector(excel.getData(3, 974, 4))).click();
		    
		    
		    Thread.sleep(3000);
			try
			{
				//Check weather the report is available for the selected time period
				if(driver.findElement(By.xpath(excel.getData(3, 2355, 1))).isDisplayed())
				{
					test.log(LogStatus.FAIL, "Batch Report is not available for Specific Date");
				
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));

				}
				else
				{
					test.log(LogStatus.PASS, "Batch Report is available for Specific Date");
					
					List<WebElement> rows = driver.findElements(By.xpath(excel.getData(3, 856, 1)));
					
					int rowSize=rows.size();
					
					//Get the total records Value
					String total_Records = driver.findElement(By.xpath("//td[contains(.,'Total Records')]/../../tr["+rowSize+"]/td[1]")).getText();
					
					System.out.println("Total Records is : "+total_Records);
					
					test.log(LogStatus.INFO, "Total Records is : "+total_Records);
					
					//Get the payments Value
					String payment = driver.findElement(By.xpath("//td[contains(.,'Total Payment')]/../../tr["+rowSize+"]/td[3]")).getText();
					
					System.out.println("Total Payment is : "+payment);
					
					test.log(LogStatus.INFO, "Total Payment is : "+payment);
					
					//Get the Refund Value
					String refund = driver.findElement(By.xpath("//td[contains(.,'Total Refund')]/../../tr["+rowSize+"]/td[4]")).getText();
					
					System.out.println("Total Refund is : "+refund);
					
					test.log(LogStatus.INFO, "Total Refund is : "+refund);
					
					//Get the Tips Value
					String tip = driver.findElement(By.xpath("//td[contains(.,'Total Tips')]/../../tr["+rowSize+"]/td[5]")).getText();
					
					System.out.println("Total Tip is : "+tip);
					
					test.log(LogStatus.INFO, "Total Tip is : "+tip);
					
					//Get the grand total value
					String grand_total = driver.findElement(By.xpath("//td[contains(.,'Grand Total')]/../../tr["+rowSize+"]/td[6]")).getText();
					
					System.out.println("Grand Total is : "+grand_total);
					
					test.log(LogStatus.INFO, "Grand Total is : "+grand_total);wb.close();
					
					Thread.sleep(3000);
					
					driver.findElement(By.tagName("html")).sendKeys(Keys.END);
					
					

					Thread.sleep(3000);
					driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);

				}
			}
			catch(Exception fd)
			{
				
							
			}
		}
		

		
		@Test(enabled=false,priority=9)
		public void Batch_Report_Method_Manual_For_Date_Range(WebDriver driver) throws Exception
		{
            File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			Thread.sleep(2000);
			//Click the Node Option
			driver.findElement(By.xpath(excel.getData(3, 1040, 1))).click();
			//Clear the node option
			//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[1]/div/form/div[1]/div/ul/li/input")).clear();
			//Enter the node
			driver.findElement(By.xpath(excel.getData(3, 1041, 1))).sendKeys(Utility.getReportPropertyUser("Batch_Report_Node"));
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 1041, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(3000);
			//Click the Employee Option
			driver.findElement(By.xpath(excel.getData(3, 1042, 1))).click();
			//Clear the Employee Option
			//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[1]/div/form/div[2]/div/ul/li/input")).clear();
			//Enter the Employee
			driver.findElement(By.xpath(excel.getData(3, 1043, 1))).sendKeys(Utility.getReportPropertyUser("Batch_Report_Employee"));
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 1043, 1))).sendKeys(Keys.ENTER);

			
			Thread.sleep(2000);
			//Click the Batch Type
			driver.findElement(By.xpath(excel.getData(3, 2324, 1))).click();
			//Select the Batch Type
			driver.findElement(By.xpath(excel.getData(3, 2325, 1))).sendKeys("Manual");
			//Enter the Batch Type
			driver.findElement(By.xpath(excel.getData(3, 2325, 1))).sendKeys(Keys.ENTER);
			
			
			Thread.sleep(2000);
			//Select the Time Period option
		    driver.findElement(By.xpath(excel.getData(3, 2157, 1))).click();
		    //Enter the required Period of time for Specific date
		    driver.findElement(By.xpath(excel.getData(3, 2158, 1))).sendKeys("Date Range");
		    //Press the Enter key
		    driver.findElement(By.xpath(excel.getData(3, 2158, 1))).sendKeys(Keys.ENTER);
		    Thread.sleep(2000);
		    
		   //Clear the Date Field
		    driver.findElement(By.xpath(excel.getData(3, 1982, 1))).clear();
		   //Enter the specific date
		    driver.findElement(By.xpath(excel.getData(3, 1982, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));

			   //Clear the Date Field
		    driver.findElement(By.xpath(excel.getData(3, 1983, 1))).clear();
		   //Enter the specific date
		    driver.findElement(By.xpath(excel.getData(3, 1983, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
		    
		    Thread.sleep(3000);
		    //Click the Run
		    driver.findElement(By.cssSelector(excel.getData(3, 974, 4))).click();
		    
		    
		    Thread.sleep(3000);
			try
			{
				//Check weather the report is available for the selected time period
				if(driver.findElement(By.xpath(excel.getData(3, 2355, 1))).isDisplayed())
				{
					test.log(LogStatus.FAIL, "Batch Report is not available for Specific Date");
				
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));

				
				}
				else
				{
					test.log(LogStatus.PASS, "Batch Report is available for Specific Date");
					
					List<WebElement> rows = driver.findElements(By.xpath(excel.getData(3, 856, 1)));
					
					int rowSize=rows.size();
					
					//Get the total records Value
					String total_Records = driver.findElement(By.xpath("//td[contains(.,'Total Records')]/../../tr["+rowSize+"]/td[1]")).getText();
					
					System.out.println("Total Records is : "+total_Records);
					
					test.log(LogStatus.INFO, "Total Records is : "+total_Records);
					
					//Get the payments Value
					String payment = driver.findElement(By.xpath("//td[contains(.,'Total Payment')]/../../tr["+rowSize+"]/td[3]")).getText();
					
					System.out.println("Total Payment is : "+payment);
					
					test.log(LogStatus.INFO, "Total Payment is : "+payment);
					
					//Get the Refund Value
					String refund = driver.findElement(By.xpath("//td[contains(.,'Total Refund')]/../../tr["+rowSize+"]/td[4]")).getText();
					
					System.out.println("Total Refund is : "+refund);
					
					test.log(LogStatus.INFO, "Total Refund is : "+refund);
					
					//Get the Tips Value
					String tip = driver.findElement(By.xpath("//td[contains(.,'Total Tips')]/../../tr["+rowSize+"]/td[5]")).getText();
					
					System.out.println("Total Tip is : "+tip);
					
					test.log(LogStatus.INFO, "Total Tip is : "+tip);
					
					//Get the grand total value
					String grand_total = driver.findElement(By.xpath("//td[contains(.,'Grand Total')]/../../tr["+rowSize+"]/td[6]")).getText();
					
					System.out.println("Grand Total is : "+grand_total);
					
					test.log(LogStatus.INFO, "Grand Total is : "+grand_total);wb.close();
					
					Thread.sleep(3000);
					
					driver.findElement(By.tagName("html")).sendKeys(Keys.END);
					
					

					Thread.sleep(3000);
					driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);

				}
			}
			catch(Exception fd)
			{
				
						
			}
		}
		
		@Test(enabled=false,priority=10)
		public void Batch_Report_Method_Auto_Batch_For_Date_Range(WebDriver driver) throws Exception
		{
            File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			Thread.sleep(25000);
			//Click the Node Option
			driver.findElement(By.xpath(excel.getData(3, 1040, 1))).click();
			//Clear the node option
			//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[1]/div/form/div[1]/div/ul/li/input")).clear();
			//Enter the node
			driver.findElement(By.xpath(excel.getData(3, 1041, 1))).sendKeys(Utility.getReportPropertyUser("Batch_Report_Node"));
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 1041, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(3000);
			//Click the Employee Option
			driver.findElement(By.xpath(excel.getData(3, 1042, 1))).click();
			//Clear the Employee Option
			//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[1]/div/form/div[2]/div/ul/li/input")).clear();
			//Enter the Employee
			driver.findElement(By.xpath(excel.getData(3, 1043, 1))).sendKeys(Utility.getReportPropertyUser("Batch_Report_Employee"));
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 1043, 1))).sendKeys(Keys.ENTER);

			
			Thread.sleep(2000);
			//Click the Batch Type
			driver.findElement(By.xpath(excel.getData(3, 2324, 1))).click();
			//Select the Batch Type
			driver.findElement(By.xpath(excel.getData(3, 2325, 1))).sendKeys("Auto Batch");
			//Enter the Batch Type
			driver.findElement(By.xpath(excel.getData(3, 2325, 1))).sendKeys(Keys.ENTER);
			
			
			Thread.sleep(2000);
			//Select the Time Period option
		    driver.findElement(By.xpath(excel.getData(3, 2157, 1))).click();
		    //Enter the required Period of time for Specific date
		    driver.findElement(By.xpath(excel.getData(3, 2158, 1))).sendKeys("Date Range");
		    //Press the Enter key
		    driver.findElement(By.xpath(excel.getData(3, 2158, 1))).sendKeys(Keys.ENTER);
		    Thread.sleep(2000);
		    
		   //Clear the Date Field
		    driver.findElement(By.xpath(excel.getData(3, 1982, 1))).clear();
		   //Enter the specific date
		    driver.findElement(By.xpath(excel.getData(3, 1982, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));

			//Clear the Date Field
		    driver.findElement(By.xpath(excel.getData(3, 1983, 1))).clear();
		    //Enter the specific date
		    driver.findElement(By.xpath(excel.getData(3, 1983, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
		    
		    Thread.sleep(3000);
		    //Click the Run
		    driver.findElement(By.cssSelector(excel.getData(3, 974, 4))).click();
		    
		    
		    Thread.sleep(5000);
			try
			{
				//Check weather the report is available for the selected time period
				if(driver.findElement(By.xpath(excel.getData(3, 2355, 1))).isDisplayed())
				{
					test.log(LogStatus.FAIL, "Batch Report is not available for Specific Date");
				
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));

				}
				else
				{
					test.log(LogStatus.PASS, "Batch Report is available for Specific Date");
					
					List<WebElement> rows = driver.findElements(By.xpath(excel.getData(3, 856, 1)));
					
					int rowSize=rows.size();
					
					//Get the total records Value
					String total_Records = driver.findElement(By.xpath("//td[contains(.,'Total Records')]/../../tr["+rowSize+"]/td[1]")).getText();
					
					System.out.println("Total Records is : "+total_Records);
					
					test.log(LogStatus.INFO, "Total Records is : "+total_Records);
					
					//Get the payments Value
					String payment = driver.findElement(By.xpath("//td[contains(.,'Total Payment')]/../../tr["+rowSize+"]/td[3]")).getText();
					
					System.out.println("Total Payment is : "+payment);
					
					test.log(LogStatus.INFO, "Total Payment is : "+payment);
					
					//Get the Refund Value
					String refund = driver.findElement(By.xpath("//td[contains(.,'Total Refund')]/../../tr["+rowSize+"]/td[4]")).getText();
					
					System.out.println("Total Refund is : "+refund);
					
					test.log(LogStatus.INFO, "Total Refund is : "+refund);
					
					//Get the Tips Value
					String tip = driver.findElement(By.xpath("//td[contains(.,'Total Tips')]/../../tr["+rowSize+"]/td[5]")).getText();
					
					System.out.println("Total Tip is : "+tip);
					
					test.log(LogStatus.INFO, "Total Tip is : "+tip);
					
					//Get the grand total value
					String grand_total = driver.findElement(By.xpath("//td[contains(.,'Grand Total')]/../../tr["+rowSize+"]/td[6]")).getText();
					
					System.out.println("Grand Total is : "+grand_total);
					
					test.log(LogStatus.INFO, "Grand Total is : "+grand_total);wb.close();
					
					Thread.sleep(3000);
					
					driver.findElement(By.tagName("html")).sendKeys(Keys.END);
					
					

					Thread.sleep(3000);
					driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);

				}
			}
			catch(Exception fd)
			{
				
						
			}
		}
}

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

import epicList_Chrome_Account_User.Utility;
import newReadExcelFile_New.ExcelDataConfig;

public class Verify_PaidIn_Or_PaidOut_Report {

	public WebDriver driver;
	
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Verify_PaidIn_Or_PaidOut_Report");

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
			PaidIn_Or_PaidOut_Report_Method_openpage(driver);
			PaidIn_Or_PaidOut_Report_Method_paidIn_VerifyALL(driver);
			PaidIn_Report_Method(driver);
			PaidOut_Report_Method(driver);
			Thread.sleep(1500);
		}
		
			@Test(priority=21,enabled=false)
			public void PaidIn_Or_PaidOut_Report_Method_openpage(WebDriver driver) throws Exception
			{
				 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
					
					FileInputStream fis = new FileInputStream(src);
					
					XSSFWorkbook wb = new XSSFWorkbook(fis);
					
					XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
					
					ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			/*
				Thread.sleep(2000);
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
				WebElement element = driver.findElement(By.xpath("//span[.='Paid In/Paid Out']"));
				//Scroll the page till the Discount option present
				je.executeScript("arguments[0].scrollIntoView(true);",element); 
		        //Click the Paid In/Paid Out Option		
				driver.findElement(By.xpath("//span[.='Paid In/Paid Out']")).click(); */
				
				Thread.sleep(3000);
				//Enter the Users URL
				driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id1")+"report/paid");
				
				Thread.sleep(5000);
				try
				{
				//Check Paid In/Paid Out Report page opened or not
				if(driver.findElement(By.xpath(excel.getData(1, 807, 1))).getText().equalsIgnoreCase("Paid In/Paid Out"))
				{
					test.log(LogStatus.PASS, "Paid In/Paid Out report page loaded Successfully");
				
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));
				}
				else
				{
					test.log(LogStatus.FAIL, "Paid In/Paid Out report page loaded Failed");
				
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
			
			@Test(priority=22,enabled=false)
			public void PaidIn_Or_PaidOut_Report_Method_paidIn_VerifyALL(WebDriver driver) throws Exception
			{

				 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
					
					FileInputStream fis = new FileInputStream(src);
					
					XSSFWorkbook wb = new XSSFWorkbook(fis);
					
					XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
					
					ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				Thread.sleep(5000);
				//Click the Paid Type option
				driver.findElement(By.xpath(excel.getData(1, 808, 1))).click();
				//Enter the required Paid Type
				driver.findElement(By.xpath(excel.getData(1, 809, 1))).sendKeys("All");
				//Press the Enter Key
				driver.findElement(By.xpath(excel.getData(1, 809, 1))).sendKeys(Keys.ENTER);
	
				Thread.sleep(3000);
				//Click the Time Period option
				driver.findElement(By.xpath(excel.getData(1, 810, 1))).click();
				//Enter the required Time Period
				driver.findElement(By.xpath(excel.getData(1, 811, 1))).sendKeys("Date Range");
				//Press the Enter Key
				driver.findElement(By.xpath(excel.getData(1, 811, 1))).sendKeys(Keys.ENTER);
				
				Thread.sleep(2000);
				//Clear the date field
				driver.findElement(By.xpath(excel.getData(1, 812, 1))).clear();
				//Enter the date
				driver.findElement(By.xpath(excel.getData(1, 812, 1))).sendKeys(Utility.getReportProperty("Date_Range_From"));
	
				Thread.sleep(2000);
				//Clear the date field
				driver.findElement(By.xpath(excel.getData(1, 813, 1))).clear();
				//Enter the date
				driver.findElement(By.xpath(excel.getData(1, 813, 1))).sendKeys(Utility.getReportProperty("Date_Range_To"));
				
				Thread.sleep(2000);
				//Click the Run Button
				driver.findElement(By.xpath(excel.getData(1, 572, 1))).click();
				
				Thread.sleep(3000);
				try
				{
					//Check weather the report is available for the selected time period
					if(driver.findElement(By.xpath(excel.getData(3, 2230, 1))).isDisplayed())
					{
						test.log(LogStatus.INFO, "Paid In/Paid Out Report is not available for Specific Date");
					
						String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));
					}
				}
				catch(Exception sd)
				{
					
					test.log(LogStatus.PASS, "Paid In/Paid Out Report available for Specific Date");
					
					//Get the number of rows
					List<WebElement> rows = driver.findElements(By.xpath(excel.getData(1, 815, 1)));
					
					//Get the Total amount value
					String total_Amount = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[5]/table/tbody/tr["+rows.size()+"]/td[5]")).getText();
					
					//Print the Total amount
					System.out.println("Total Paid In/Paid Out Amount is : "+total_Amount);
					
					test.log(LogStatus.INFO, "Total Paid In/Paid Out Amount is : "+total_Amount);wb.close();
				
					
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));
				}
			}
			
			@Test(priority=23,enabled=false)
			public void PaidIn_Report_Method(WebDriver driver) throws Exception
			{   
				File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				Thread.sleep(5000);
				//Click the Paid Type option
				driver.findElement(By.xpath(excel.getData(1, 808, 1))).click();
				//Enter the required Paid Type
				driver.findElement(By.xpath(excel.getData(1, 809, 1))).sendKeys("Paid In");
				//Press the Enter Key
				driver.findElement(By.xpath(excel.getData(1, 809, 1))).sendKeys(Keys.ENTER);
	
				Thread.sleep(3000);
				//Click the Time Period option
				driver.findElement(By.xpath(excel.getData(1, 810, 1))).click();
				//Enter the required Time Period
				driver.findElement(By.xpath(excel.getData(1, 811, 1))).sendKeys("Date Range");
				//Press the Enter Key
				driver.findElement(By.xpath(excel.getData(1, 811, 1))).sendKeys(Keys.ENTER);
				
				Thread.sleep(2000);
				//Clear the date field
				driver.findElement(By.xpath(excel.getData(1, 812, 1))).clear();
				//Enter the date
				driver.findElement(By.xpath(excel.getData(1, 812, 1))).sendKeys(Utility.getReportProperty("Date_Range_From"));
	
				Thread.sleep(2000);
				//Clear the date field
				driver.findElement(By.xpath(excel.getData(1, 813, 1))).clear();
				//Enter the date
				driver.findElement(By.xpath(excel.getData(1, 813, 1))).sendKeys(Utility.getReportProperty("Date_Range_To"));
				
				Thread.sleep(2000);
				//Click the Run Button
				driver.findElement(By.xpath(excel.getData(1, 572, 1))).click();
				
				Thread.sleep(3000);
				try
				{
					//Check weather the report is available for the selected time period
					if(driver.findElement(By.xpath(excel.getData(3, 2230, 1))).isDisplayed())
					{
						test.log(LogStatus.INFO, "Paid In Report is not available for Specific Date");
					
						String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));
					}
				}
				catch(Exception fds)
				{	
					test.log(LogStatus.PASS, "Paid In Report available for Specific Date");
					
					//Get the number of rows
					List<WebElement> rows = driver.findElements(By.xpath(excel.getData(1, 815, 1)));
					
					//Get the Total amount value
					String total_Amount = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[5]/table/tbody/tr["+rows.size()+"]/td[5]")).getText();
					
					//Print the Total amount
					System.out.println("Total Paid In Amount is : "+total_Amount);
					
					test.log(LogStatus.INFO, "Total Paid In Amount is : "+total_Amount);wb.close();
				
					
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));
				}
			}
			
			@Test(priority=24,enabled=false)
			public void PaidOut_Report_Method(WebDriver driver) throws Exception
			{
                File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				Thread.sleep(5000);
				//Click the Paid Type option
				driver.findElement(By.xpath(excel.getData(1, 808, 1))).click();
				//Enter the required Paid Type
				driver.findElement(By.xpath(excel.getData(1, 809, 1))).sendKeys("Paid Out");
				//Press the Enter Key
				driver.findElement(By.xpath(excel.getData(1, 809,1))).sendKeys(Keys.ENTER);
	
				Thread.sleep(3000);
				//Click the Time Period option
				driver.findElement(By.xpath(excel.getData(1, 810, 1))).click();
				//Enter the required Time Period
				driver.findElement(By.xpath(excel.getData(1, 811, 1))).sendKeys("Date Range");
				//Press the Enter Key
				driver.findElement(By.xpath(excel.getData(1, 811, 1))).sendKeys(Keys.ENTER);
				
				Thread.sleep(2000);
				//Clear the date field
				driver.findElement(By.xpath(excel.getData(1, 812, 1))).clear();
				//Enter the date
				driver.findElement(By.xpath(excel.getData(1, 812, 1))).sendKeys(Utility.getReportProperty("Date_Range_From"));
	
				Thread.sleep(2000);
				//Clear the date field
				driver.findElement(By.xpath(excel.getData(1, 813, 1))).clear();
				//Enter the date
				driver.findElement(By.xpath(excel.getData(1, 813, 1))).sendKeys(Utility.getReportProperty("Date_Range_To"));
				
				Thread.sleep(2000);
				//Click the Run Button
				driver.findElement(By.xpath(excel.getData(1, 572, 1))).click();
				
				Thread.sleep(3000);
				try
				{
					//Check weather the report is available for the selected time period
					if(driver.findElement(By.xpath(excel.getData(3, 2230, 1))).isDisplayed())
					{
						test.log(LogStatus.INFO, "Paid Out Report is not available for Specific Date");
					
						String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));
					}
				}
				catch(Exception ds)
				{
					
					test.log(LogStatus.PASS, "Paid Out Report available for Specific Date");
					
					//Get the number of rows
					List<WebElement> rows = driver.findElements(By.xpath(excel.getData(1, 815, 1)));
					
					//Get the Total amount value
					String total_Amount = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[5]/table/tbody/tr["+rows.size()+"]/td[5]")).getText();
					
					//Print the Total amount
					System.out.println("Total Paid Out Amount is : "+total_Amount);
					
					test.log(LogStatus.INFO, "Total Paid Out Amount is : "+total_Amount);wb.close();
				
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));
				}
			}	
}

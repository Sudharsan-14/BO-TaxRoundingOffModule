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


public class Verify_Employee_Cash_Tip_Reports {
	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Verify_Employee_Cash_Tip_Reports");

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
			Employee_CashTip_Report_Method_OpenPage(driver);
			Employee_CashTip_Report_Method_verify(driver);
			Thread.sleep(1500);
		}

			@Test(enabled=false,priority=36)
			public void Employee_CashTip_Report_Method_OpenPage(WebDriver driver) throws Exception
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
				WebElement element = driver.findElement(By.xpath("//span[.='Employee']"));
				//Scroll the page till the Transaction option present
				je.executeScript("arguments[0].scrollIntoView(true);",element); 
			    //Click the void Option		
				driver.findElement(By.xpath("//span[.='Employee']")).click(); */
				
				Thread.sleep(3000);
				//Enter the Users URL
				driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id1")+"cashTip");
				
				Thread.sleep(5000);
				try
				{
				//Check Cash Tip Report page opened or not
				if(driver.findElement(By.xpath(excel.getData(1, 1547, 1))).getText().equalsIgnoreCase("Cash Tip"))
				{
					test.log(LogStatus.PASS,"Cash Tip Report page loaded Successfully");
				
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));
				}
				else
				{
					test.log(LogStatus.FAIL,"Cash Tip Report page loaded Failed");wb.close();
				
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
			}
		
			@Test(enabled=false,priority=37)
			public void Employee_CashTip_Report_Method_verify(WebDriver driver) throws Exception
				{

                File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			    //Select the Employee option
			    driver.findElement(By.xpath(excel.getData(1, 1460, 1))).click();
			    //Enter the required Employee
			    driver.findElement(By.xpath(excel.getData(1, 1461, 1))).sendKeys("All");
			    //Press the Enter key
			    driver.findElement(By.xpath(excel.getData(1, 1461, 1))).sendKeys(Keys.ENTER);
			    
			    //Select the Time Period option
			    driver.findElement(By.xpath(excel.getData(1, 1539, 1))).click();
			    //Enter the required Period of time "Specific date"
			    driver.findElement(By.xpath(excel.getData(1, 1540, 1))).sendKeys("Date Range");
			    //Press the Enter key
			    driver.findElement(By.xpath(excel.getData(1, 1540, 1))).sendKeys(Keys.ENTER);
			    
			    
			    //Clear the date field
			    driver.findElement(By.xpath(excel.getData(1, 1541, 1))).clear();
			    //Select the required Specific date
			    driver.findElement(By.xpath(excel.getData(1, 1541, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
		
			    //Clear the date field
			    driver.findElement(By.xpath(excel.getData(1, 1542, 1))).clear();
			    //Select the required Specific date
			    driver.findElement(By.xpath(excel.getData(1, 1542, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
			      
			    //Select the Active/Inactive option
			    driver.findElement(By.xpath(excel.getData(1, 1543, 1))).click();
			    //Enter the required Active/Inactive
			    driver.findElement(By.xpath(excel.getData(1, 1544, 1))).sendKeys("All");
			    //Press the Enter key
			    driver.findElement(By.xpath(excel.getData(1, 1544, 1))).sendKeys(Keys.ENTER);
		
			    //Click the Run
			    driver.findElement(By.cssSelector(excel.getData(1, 974, 4))).click();
			    Thread.sleep(10000);
		
				try
				{
					//Check weather the report is available for the selected time period
					if(driver.findElement(By.xpath(excel.getData(3, 2238, 1))).isDisplayed())
					{
						test.log(LogStatus.FAIL, "Employee Cash Tip Report is not available for Specific Date");
					
						String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));

					
					}
				}
				catch(Exception sd)
				{
					
					test.log(LogStatus.PASS, "Employee Cash Tip Report(In Time and Daily) is available for Specific Date");
					
					System.out.println("******* The Below is Employee Cash Tip Report for In Time(Daily) *******");
					
					test.log(LogStatus.INFO, "******* The Below is Employee Cash Tip Report for In Time(Daily) *******");
					
					List<WebElement> rows = driver.findElements(By.xpath("//table[@class]/tbody/tr"));
					
					//Get the net sales Value
					String net_Sales = driver.findElement(By.xpath("//table[@class]/tbody/tr["+rows.size()+"]/td[4]")).getText();
					
					System.out.println("Total Net Sales is : "+net_Sales);
					
					test.log(LogStatus.INFO, "Total Net Sales is : "+net_Sales);
					
					//Get the non cash Value
					String non_Cash = driver.findElement(By.xpath("//table[@class]/tbody/tr["+rows.size()+"]/td[6]")).getText();
					
					System.out.println("Total Non Cash is : "+non_Cash);
					
					test.log(LogStatus.INFO, "Total Non Cash is : "+non_Cash);
					
					//Get the expected cash Value
					String expected_Cash = driver.findElement(By.xpath("//table[@class]/tbody/tr["+rows.size()+"]/td[7]")).getText();
					
					System.out.println("Total Expected Cash is : "+expected_Cash);
					
					test.log(LogStatus.INFO, "Total Expected Cash is : "+expected_Cash);
					
					//Get the declared cash value
					String declared_Cash = driver.findElement(By.xpath("//table[@class]/tbody/tr["+rows.size()+"]/td[8]")).getText();
					
					System.out.println("Total Declared Cash is : "+declared_Cash);
					
					test.log(LogStatus.INFO, "Total Declared Cash is : "+declared_Cash);wb.close();
					
					
					Thread.sleep(3000);
					driver.findElement(By.tagName("html")).sendKeys(Keys.END);
					
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));

					
					Thread.sleep(3000);
					
					driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
				}			     
			}
	
}

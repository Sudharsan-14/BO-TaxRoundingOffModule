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

public class Verify_DailyTender_Report {

	public WebDriver driver;

		ExtentReports rep = ExtentManager.getInstance();
		ExtentTest test = rep.startTest("Verify_DailyTender_Report");
		
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
			DailyTender_Report_Method_viewPage(driver);
			DailyTender_Report_Method_verify(driver);
			Thread.sleep(1500);
		}

		@Test(enabled=false,priority=20)
		public void DailyTender_Report_Method_viewPage(WebDriver driver) throws Exception
		{
			File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		/*//Click the Reports option
		/*	//Click the Reports option
			driver.findElement(By.xpath("//span[.='Reports']")).click();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			// Create instance of Java script executor
			JavascriptExecutor je = (JavascriptExecutor) driver;
			//Identify the WebElement which will appear after scrolling down
			WebElement element = driver.findElement(By.xpath("//span[.='Daily Tender']"));
			//Scroll the page till the Daily Tender option present
			je.executeScript("arguments[0].scrollIntoView(true);",element); 
		    //Click the Daily Tender Option		
			driver.findElement(By.xpath("//span[.='Daily Tender']")).click();   */
			
			Thread.sleep(3000);
			//Enter the Users URL
			driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id1")+"dailyTenderReport");
			
			Thread.sleep(5000);
			try
			{
			//Check Daily Tender page opened or not
			if(driver.findElement(By.xpath(excel.getData(3, 1069, 1))).getText().equalsIgnoreCase("Daily Tender"))
			{
				test.log(LogStatus.PASS,"Daily Tender Report page loaded Successfully");
			
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));
			}
			else
			{
				test.log(LogStatus.FAIL,"Daily Tender Report page loaded Failed");
			
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
		
		@Test(enabled=false,priority=21)
		public void DailyTender_Report_Method_verify(WebDriver driver) throws Exception
		{
            File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			Thread.sleep(5000);
			//Select the Time Period option
		    driver.findElement(By.xpath(excel.getData(3, 1060, 1))).click();
		    //Enter the required Period of time for Specific date
		    driver.findElement(By.xpath(excel.getData(3, 1061, 1))).sendKeys("Date Range");
		    //Press the Enter key
		    driver.findElement(By.xpath(excel.getData(3, 1061, 1))).sendKeys(Keys.ENTER);
		    Thread.sleep(2000);
		    
		   //Clear the Date Field
		    driver.findElement(By.xpath(excel.getData(3, 1062, 1))).clear();
		   //Enter the specific date
		    driver.findElement(By.xpath(excel.getData(3, 1062, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
		
		    Thread.sleep(2000);
		    
		   //Clear the Date Field
		    driver.findElement(By.xpath(excel.getData(3, 1063, 1))).clear();
		   //Enter the specific date
		    driver.findElement(By.xpath(excel.getData(3, 1063, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
		    
		    //Click the Run
		    driver.findElement(By.cssSelector(excel.getData(3, 974, 4))).click();
		    Thread.sleep(2000);
		
		    driver.findElement(By.tagName("html")).sendKeys(Keys.END);
		    
			try
			{
				//Check weather the report is available for the selected time period
				if(driver.findElement(By.xpath(excel.getData(3, 704, 1))).isDisplayed())
				{
					test.log(LogStatus.FAIL, "Daily Tender Report is not available for Specific Date");
				
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));

					
				
				}
			}
			catch(Exception fd)
			{
				
				test.log(LogStatus.PASS, "Daily Tender Report is available for Specific Date");
				
				List<WebElement> rows = driver.findElements(By.xpath(excel.getData(3, 1070, 1)));
				
				List<WebElement> columns = driver.findElements(By.xpath(excel.getData(3, 1071, 1)));
			
				for(int i = 2; i <= columns.size();i++ )
				{
					String head = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[5]/table/tbody/tr[1]/th["+i+"]")).getText();
				
					String total = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[5]/table/tbody/tr["+rows.size()+"]/td["+i+"]")).getText();
				
					System.out.println(head+" have the Total is : "+total);
			
					test.log(LogStatus.INFO, head+" have the Total is : "+total);
				
					Thread.sleep(3000);
					driver.findElement(By.tagName("html")).sendKeys(Keys.END);
					
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));

					Thread.sleep(3000);
					driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
					
				}
				
			}  
		    Thread.sleep(2000);wb.close();
		    
		}

	}
  
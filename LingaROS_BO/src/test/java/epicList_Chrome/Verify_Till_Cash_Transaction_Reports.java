package epicList_Chrome;

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

import epicList_Chrome.Utility;
import newReadExcelFile_New.ExcelDataConfig;


public class Verify_Till_Cash_Transaction_Reports {
	public WebDriver driver;

		ExtentReports rep = ExtentManager.getInstance();
		ExtentTest test = rep.startTest("Verify_Till_Cash_Transaction_Reports");
		
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
			CashTransaction_Report_Method_OpenPage(driver);
			CashTransaction_Report_Method_verifyAll(driver);
			Thread.sleep(1500);
		}

		@Test(enabled=false,priority=13)
		public void CashTransaction_Report_Method_OpenPage(WebDriver driver) throws Exception
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
			WebElement element = driver.findElement(By.xpath("//span[.='Till']"));
			//Scroll the page till the Till option present
			je.executeScript("arguments[0].scrollIntoView(true);",element); 
		    //Click the Till Option		
			driver.findElement(By.xpath("//span[.='Till']")).click(); */
			
			Thread.sleep(3000);
			//Enter the Users URL
			driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"cashTransactionReport");
			
			Thread.sleep(5000);
			try
			{
			//Check Cash Transaction page opened or not
			if(driver.findElement(By.xpath(excel.getData(1, 991, 1))).getText().equalsIgnoreCase("Cash Transaction"))
			{
				test.log(LogStatus.PASS,"Cash Transaction Report page loaded Successfully");
				
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));
			}
			else
			{
				test.log(LogStatus.FAIL,"Cash Transaction Report page loaded Failed");
				
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
		
		@Test(enabled=false,priority=14)
		public void CashTransaction_Report_Method_verifyAll(WebDriver driver) throws Exception
		{

			   File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			//Create the web element
		    WebElement html = driver.findElement(By.tagName("html"));
		  	//Zoom out the window
		  	html.sendKeys(Keys.HOME);
		  	html.sendKeys(Keys.HOME);
		/*	  	html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));-
		  	html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		  	html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		  	html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		  	html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));*/
		  	
			//Select the Employee option
		    driver.findElement(By.xpath(excel.getData(1, 992, 1))).click();
		    //Enter the required Employee
		    driver.findElement(By.xpath(excel.getData(1, 993, 1))).sendKeys("All");
		    //Press the Enter key
		    driver.findElement(By.xpath(excel.getData(1, 993, 1))).sendKeys(Keys.ENTER);
		    
		    //Select the Transaction type option (All)
		    driver.findElement(By.xpath(excel.getData(1, 994, 1))).click();
		    //Enter the required Transaction type (All)
		    driver.findElement(By.xpath(excel.getData(1, 995, 1))).sendKeys("All");
		    //Press the Enter key
		    driver.findElement(By.xpath(excel.getData(1, 995, 1))).sendKeys(Keys.ENTER);
		
		    //Select the Time Period option
		    driver.findElement(By.xpath(excel.getData(1, 996, 1))).click();
		    //Enter the required Period of time (Specific date)
		    driver.findElement(By.xpath(excel.getData(1, 997, 1))).sendKeys("Date Range");
		    //Press the Enter key
		    driver.findElement(By.xpath(excel.getData(1, 997, 1))).sendKeys(Keys.ENTER);
		    Thread.sleep(2000);
		    
		    //Clear the Date field
		    driver.findElement(By.xpath(excel.getData(1, 998, 1))).clear();
		    //Enter the number of days
		    driver.findElement(By.xpath(excel.getData(1, 998, 1))).sendKeys(Utility.getReportProperty("Date_Range_From"));
		
		    //Clear the Date field
		    driver.findElement(By.xpath(excel.getData(1, 999, 1))).clear();
		    //Enter the number of days
		    driver.findElement(By.xpath(excel.getData(1, 999, 1))).sendKeys(Utility.getReportProperty("Date_Range_To"));
		    
		    //Click the Run
		    driver.findElement(By.cssSelector(excel.getData(1, 974, 4))).click();
		    Thread.sleep(5000);
		
			try
			{
				//Check weather the report is available for the selected time period
				if(driver.findElement(By.xpath(excel.getData(1, 704, 1))).isDisplayed())
				{
					test.log(LogStatus.FAIL, "Cash Transaction Report is not available for Specific Date");
				
				
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));

					
				}
			}
			catch(Exception sd)
			{
				
				test.log(LogStatus.PASS, "Cash Transaction Report is available for Specific Date");
			
				
				List<WebElement> rows = driver.findElements(By.xpath(excel.getData(1,1000, 1)));
				
				//Get the amount Value
				String amount = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[6]")).getText();
				
				System.out.println("Total Amount is : "+amount);
				
				test.log(LogStatus.INFO, "Total Amount is : "+amount);wb.close();
			
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

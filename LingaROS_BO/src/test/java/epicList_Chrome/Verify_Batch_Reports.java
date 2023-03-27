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
			Batch_Report_Method_OpenPage(driver);
			Batch_Report_Method_verify(driver);
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
			
			Thread.sleep(3000);
			//Enter the Users URL
			driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"batchReport");
			
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
		public void Batch_Report_Method_verify(WebDriver driver) throws Exception
		{
            File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			Thread.sleep(3000);
			//Click the Node Option
			driver.findElement(By.xpath(excel.getData(3, 1040, 1))).click();
			//Clear the node option
			//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[1]/div/form/div[1]/div/ul/li/input")).clear();
			//Enter the node
			driver.findElement(By.xpath(excel.getData(3, 1041, 1))).sendKeys(Utility.getReportProperty("Batch_Report_Node"));
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 1041, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(3000);
			//Click the Employee Option
			driver.findElement(By.xpath(excel.getData(3, 1042, 1))).click();
			//Clear the Employee Option
			//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[1]/div/form/div[2]/div/ul/li/input")).clear();
			//Enter the Employee
			driver.findElement(By.xpath(excel.getData(3, 1043, 1))).sendKeys(Utility.getReportProperty("Batch_Report_Employee"));
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 1043, 1))).sendKeys(Keys.ENTER);

			//Select the Time Period option
		    driver.findElement(By.xpath(excel.getData(3, 1044, 1))).click();
		    //Enter the required Period of time for Specific date
		    driver.findElement(By.xpath(excel.getData(3, 1045, 1))).sendKeys("Date Range");
		    //Press the Enter key
		    driver.findElement(By.xpath(excel.getData(3, 1045, 1))).sendKeys(Keys.ENTER);
		    Thread.sleep(2000);
		    
		   //Clear the Date Field
		    driver.findElement(By.xpath(excel.getData(3, 1046, 1))).clear();
		   //Enter the specific date
		    driver.findElement(By.xpath(excel.getData(3, 1046, 1))).sendKeys(Utility.getReportProperty("Date_Range_From"));

			   //Clear the Date Field
		    driver.findElement(By.xpath(excel.getData(3, 1047, 1))).clear();
		   //Enter the specific date
		    driver.findElement(By.xpath(excel.getData(3, 1047, 1))).sendKeys(Utility.getReportProperty("Date_Range_To"));
		    
		    Thread.sleep(3000);
		    //Click the Run
		    driver.findElement(By.cssSelector(excel.getData(3, 974, 4))).click();
		    
		    
		    Thread.sleep(3000);
			try
			{
				//Check weather the report is available for the selected time period
				if(driver.findElement(By.xpath(excel.getData(3, 2238, 1))).isDisplayed())
				{
					test.log(LogStatus.FAIL, "Batch Report is not available for Specific Date");
				
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));

				
				}
			}
			catch(Exception fd)
			{
				
				test.log(LogStatus.PASS, "Batch Report is available for Specific Date");
				
				List<WebElement> rows = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[5]/table/tbody/tr"));
				
				//Get the total records Value
				String total_Records = driver.findElement(By.xpath(excel.getData(3, 886, 2))).getText();
				
				System.out.println("Total Records is : "+total_Records);
				
				test.log(LogStatus.INFO, "Total Records is : "+total_Records);
				
				//Get the payments Value
				String payment = driver.findElement(By.xpath(excel.getData(3, 888, 2))).getText();
				
				System.out.println("Total Payment is : "+payment);
				
				test.log(LogStatus.INFO, "Total Payment is : "+payment);
				
				//Get the Refund Value
				String refund = driver.findElement(By.xpath(excel.getData(3, 889, 2))).getText();
				
				System.out.println("Total Refund is : "+refund);
				
				test.log(LogStatus.INFO, "Total Refund is : "+refund);
				
				//Get the Tips Value
				String tip = driver.findElement(By.xpath(excel.getData(3, 890, 2))).getText();
				
				System.out.println("Total Tip is : "+tip);
				
				test.log(LogStatus.INFO, "Total Tip is : "+tip);
				
				//Get the grand total value
				String grand_total = driver.findElement(By.xpath(excel.getData(3, 1048, 2))).getText();
				
				System.out.println("Grand Total is : "+grand_total);
				
				test.log(LogStatus.INFO, "Grand Total is : "+grand_total);wb.close();
				
				Thread.sleep(3000);
				
				driver.findElement(By.tagName("html")).sendKeys(Keys.END);
				
				

				Thread.sleep(3000);
				driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
				
			}
		}
}

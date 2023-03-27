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

public class Verify_Tax_Report {

	public WebDriver driver;

		ExtentReports rep = ExtentManager.getInstance();
		ExtentTest test = rep.startTest("Verify_Tax_Report");
		
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
			Tax_Report_Method_viewPage(driver);
			Tax_Report_Method_verify(driver);
			Thread.sleep(1500);
		}

		@Test(enabled=false,priority=22)
		public void Tax_Report_Method_viewPage(WebDriver driver) throws Exception
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
			WebElement element = driver.findElement(By.xpath("//span[.='Tax Report']"));
			//Scroll the page till the Tax Report Report option present
			je.executeScript("arguments[0].scrollIntoView(true);",element); 
		    //Click the Tax Report Option		
			driver.findElement(By.xpath("//span[.='Tax Report']")).click();*/
			
			Thread.sleep(3000);
			//Enter the Users URL
			driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id1")+"taxReport");
			
			try
			{
			Thread.sleep(5000);
			//Check Customer Preference Report page opened or not
			if(driver.findElement(By.xpath(excel.getData(1, 1059, 1))).getText().equalsIgnoreCase("Tax Report"))
			{
				test.log(LogStatus.PASS,"Tax Report page loaded Successfully");
				
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));
			}
			else
			{
				test.log(LogStatus.FAIL,"Tax Report page loaded Failed");
				
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
		
		@Test(enabled=false,priority=23)
		public void Tax_Report_Method_verify(WebDriver driver) throws Exception
		{
			
			    File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		    Thread.sleep(5000);
			//Select the Time Period option
		    driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/a")).click();
		    //Enter the required Period of time for Specific date
		    driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/div/div/input")).sendKeys("Date Range");
		    //Press the Enter key
		    driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/div/div/input")).sendKeys(Keys.ENTER);
		    Thread.sleep(2000);
		    
		    Thread.sleep(3000);
		    //Clear the Date field
		    driver.findElement(By.xpath("//input[@ng-model='query.fromDate']/.")).clear();
		    //Enter the required date
		    driver.findElement(By.xpath("//input[@ng-model='query.fromDate']/.")).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
		    
		    Thread.sleep(3000);
		    //Clear the Date field
		    driver.findElement(By.xpath("//input[@ng-model='query.toDate']/.")).clear();
		    //Enter the required date
		    driver.findElement(By.xpath("//input[@ng-model='query.toDate']/.")).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
		    
		    //Click the Run
		    driver.findElement(By.cssSelector(excel.getData(1, 974, 4))).click();
		    Thread.sleep(8000);
		
		    driver.findElement(By.tagName("html")).sendKeys(Keys.END);
		    
			List<WebElement> rows = driver.findElements(By.xpath(excel.getData(1, 825, 1)));
		
			//Check weather the report is available for the selected time period
			if(rows.size() == 2)
				
			{
				test.log(LogStatus.FAIL, "Tax is not available for Specific Date");
			
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));

			
			}
			
			else if(driver.findElement(By.xpath(excel.getData(1, 1064, 1))).getText() != null)
			{
				
				test.log(LogStatus.PASS, "Tax is available for Specific Date");
		
				System.out.println("Total Tax Amount is : "+driver.findElement(By.xpath("//tr[contains(.,'Total')]/th[4]")).getText());
				
				test.log(LogStatus.INFO, "Total Tax Amount is : "+ driver.findElement(By.xpath("//tr[contains(.,'Total')]/th[4]")).getText());
				
				System.out.println("Total Tax Exempt is : "+driver.findElement(By.xpath("//tr[contains(.,'Total')]/th[5]")).getText());			
				
				test.log(LogStatus.INFO, "Total Tax Exempt is : "+driver.findElement(By.xpath("//tr[contains(.,'Total')]/th[5]")).getText());
			
				
				Thread.sleep(3000);
				driver.findElement(By.tagName("html")).sendKeys(Keys.END);
				
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));

				Thread.sleep(3000);
				driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			}
			
			Thread.sleep(15000);
			//Check weather the Taxes Status 
			if(driver.getPageSource().contains("Tax"))
			{
				test.log(LogStatus.PASS, "Tax Stats field is available");
				
	        	//Replace all commo's with empty space
	        	String expected1 = Utility.getReportPropertyUser("Sale_Report_Rounding_Off").replace(",", "");
	        	
	        	//Convert the String value of the Rounding Off element into float value
	        	float expect1 = Float.parseFloat(expected1);  

				
				Thread.sleep(2000);
				//Check the Rounding Off
				if(driver.findElement(By.xpath("//tr/td[contains(.,'Rounding Off')]/../td[4]")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Rounding_Off")))
				{
					test.log(LogStatus.PASS, "Actual and Expected Rounding Off are same");
					
		        	//Get the Total value of Rounding Off
		        	String actualText1 = driver.findElement(By.xpath("//tr/td[contains(.,'Rounding Off')]/../td[4]")).getText();
		        	
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
		        	String actualText = driver.findElement(By.xpath("//tr/td[contains(.,'Rounding Off')]/../td[4]")).getText();

		        	System.out.println("The Actual Rounding Off value is : "+actualText);
		        	
		        	test.log(LogStatus.INFO, "The Actual Rounding Off value is : "+actualText);
				}
				
				else
				{
					test.log(LogStatus.FAIL, "Actual and Expected Rounding Off are different");
					
		        	//Get the Total value of Check Count
		        	String actualText1 = driver.findElement(By.xpath("//tr/td[contains(.,'Rounding Off')]/../td[4]")).getText();
		        	
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
			
			
			
			else
			{
				test.log(LogStatus.FAIL, "Tax is not available for Specific Date");wb.close();
		
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));

			
			}  
		    Thread.sleep(2000);wb.close();
		    
		}
		
}

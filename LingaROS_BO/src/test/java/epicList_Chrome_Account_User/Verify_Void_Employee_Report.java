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

public class Verify_Void_Employee_Report {

	public WebDriver driver;
	
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Verify_Void_Employee_Report");

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
		{
			if(Utility.getProperty("Product").equalsIgnoreCase("UPOS"))
			{
			JavascriptExecutor je = (JavascriptExecutor) driver;
			//Identify the WebElement which will appear after scrolling down
			WebElement element = driver.findElement(By.xpath("//div[@id='side-bar']/div/div[1]/div[1]/div[2]/div/div/div/div[4]/a/i"));
			//Scroll the page till the Reason option present
			je.executeScript("arguments[0].scrollIntoView(true);",element); 
			//Wait for 30 seconds
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			// Click on Logout button
			driver.findElement(By.xpath("//div[@id='side-bar']/div/div[1]/div[1]/div[2]/div/div/div/div[4]/a/i")).click();
		    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(3000);
			
			//Check whether user get logged out or not
			if(driver.findElement(By.xpath("//b[.='User Login']")).getText().equalsIgnoreCase("User Login"))
			{
		    	test.log(LogStatus.PASS, "User Logged out Successfully for Dealer App");
			}
			else
			{
				test.log(LogStatus.FAIL, "User Logged out Failed for Dealer App");
			}
			Thread.sleep(3000);
			//Close the Browser_Account_Level_User
			driver.close();
		}
		else
		{
			JavascriptExecutor je = (JavascriptExecutor) driver;
			//Identify the WebElement which will appear after scrolling down
			WebElement element = driver.findElement(By.xpath("//div[@id='side-bar']/div/div[1]/div[1]/div[2]/div/div/div/div[4]/a/i"));
			//Scroll the page till the Reason option present
			je.executeScript("arguments[0].scrollIntoView(true);",element); 
			//Wait for 30 seconds
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			// Click on Logout button
			driver.findElement(By.xpath("//div[@id='side-bar']/div/div[1]/div[1]/div[2]/div/div/div/div[4]/a/i")).click();
		    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(3000);
			
			//Check whether user get logged out or not
	/*		if(driver.findElement(By.xpath("//div[@id='x-content-band-1']/div/div[2]/h2")).getText().equalsIgnoreCase("Account Login"))
			{
		    	test.log(LogStatus.PASS, "User Logged out Successfully LingaPOs");
			}
			else
			{
				test.log(LogStatus.FAIL, "User Logged out Failed LingaPos");
			} */
			Thread.sleep(3000);
			//Close the Browser_Account_Level_User
			driver.close();
		}
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
			Void_Node_Report_Method_Openpage(driver);
			Void_Node_Report_Method_Employees(driver);
			Thread.sleep(1500);
		}
	
			@Test(priority=19,enabled=false)
			public void Void_Node_Report_Method_Openpage (WebDriver driver) throws Exception
			{
				 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
					
					FileInputStream fis = new FileInputStream(src);
					
					XSSFWorkbook wb = new XSSFWorkbook(fis);
					
					XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
					
					ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				Thread.sleep(2000);

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
				WebElement element = driver.findElement(By.xpath("//span[.='Void']"));
				//Scroll the page till the Discount option present
				je.executeScript("arguments[0].scrollIntoView(true);",element); 
		       
				//Click the Void Option		
				driver.findElement(By.xpath("//span[.='Void']")).click(); */
				
				Thread.sleep(3000);
				//Enter the Users URL
				driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id1")+"report/empSale");
			
				Thread.sleep(5000);
				try
				{
				//Check Employee Report page opened or not
				if(driver.findElement(By.xpath(excel.getData(3, 849, 1))).getText().equalsIgnoreCase("Employee"))
				{
					test.log(LogStatus.PASS, "Employee report page loaded Successfully");
				
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));
				}
				else
				{
					test.log(LogStatus.FAIL, "Employee report page loaded Failed");
				
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
				wb.close();
				Thread.sleep(2000);
	
			}
		
			@Test(priority=20,enabled=false)
			public void Void_Node_Report_Method_Employees(WebDriver driver) throws Exception
			{
				 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
					
					FileInputStream fis = new FileInputStream(src);
					
					XSSFWorkbook wb = new XSSFWorkbook(fis);
					
					XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
					
					ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				Thread.sleep(2000);

				Thread.sleep(5000);
				//Click the Employee option
				driver.findElement(By.xpath(excel.getData(3, 850, 1))).click();
				//Enter the required Employee
				driver.findElement(By.xpath(excel.getData(3, 851, 1))).sendKeys("All");
				//Press the Enter Key
				driver.findElement(By.xpath(excel.getData(3, 851, 1))).sendKeys(Keys.ENTER);
			
				Thread.sleep(3000);
				//Click the Time Period option
				driver.findElement(By.xpath(excel.getData(3, 852, 1))).click();
				//Enter the required Time Period
				driver.findElement(By.xpath(excel.getData(3, 853, 1))).sendKeys("Date Range");
				//Press the Enter Key
				driver.findElement(By.xpath(excel.getData(3, 853, 1))).sendKeys(Keys.ENTER);
				
				Thread.sleep(2000);
				//Clear the date field
				driver.findElement(By.xpath(excel.getData(3, 854, 1))).clear();
				//Enter the date
				driver.findElement(By.xpath(excel.getData(3, 854, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
	
				Thread.sleep(2000);
				//Clear the date field
				driver.findElement(By.xpath(excel.getData(3, 855, 1))).clear();
				//Enter the date
				driver.findElement(By.xpath(excel.getData(3, 855, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
				
				Thread.sleep(5000);
				//Click the Run Button
				driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
				
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				Thread.sleep(10000);
				try
				{
					//Check weather the report is available for the selected time period
					if(driver.findElement(By.xpath(excel.getData(3, 1604, 1))).isDisplayed())
					{  
						test.log(LogStatus.FAIL, "Void Employee Report is not available for Specific Date");
						
						String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));
					
					}
					else
					{
						test.log(LogStatus.PASS, "Void Employee Report is available for Specific Date");
						
						//Get the number of rows
						List<WebElement> rows = driver.findElements(By.xpath(excel.getData(3, 856, 1)));
						
						//Get the Quantity value
						String quantity = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr["+rows.size()+"]/td[4]")).getText();
						
						//Print the Total
						System.out.println("Total Quantity is : "+quantity);
						
						test.log(LogStatus.INFO, "Total Quantity is : "+quantity);
						
						//Get the amount value
						String amount = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr["+rows.size()+"]/td[5]")).getText();
						
						//Print the amount
						System.out.println("Total amount is : "+amount);
						
						test.log(LogStatus.INFO, "Total amount is : "+amount);
						
						//Get the Tax value
						String tax = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr["+rows.size()+"]/td[6]")).getText();
						
						//Print the Tax
						System.out.println("Total Tax is : "+tax);
						
						test.log(LogStatus.INFO, "Total Tax is : "+tax);
						
						//Get the discount value
						String discount = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr["+rows.size()+"]/td[7]")).getText();
						
						//Print the discount
						System.out.println("Total Discount is : "+discount);
						
						test.log(LogStatus.INFO, "Total Discount is : "+discount);
						
						Thread.sleep(1000);
						int row = rows.size() - 1;
						
						//Click the Last Check Number
						driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr["+row+"]/td[1]/a")).click();
						
						//Clear the email field
						driver.findElement(By.xpath(excel.getData(3, 778, 1))).clear();
						//Enter the Required mail id
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
						
						String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));
						
						Thread.sleep(2000);
						//Click the Send Receipt button
						driver.findElement(By.xpath(excel.getData(3, 862, 1))).click();
						Thread.sleep(3000);					
						wb.close();

					}
				}
				catch(Exception fdg)
				{
				System.out.println(fdg.getMessage());	
				}
			}
}

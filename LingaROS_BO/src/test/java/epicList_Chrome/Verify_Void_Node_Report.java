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

public class Verify_Void_Node_Report {

	public WebDriver driver;
	
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Verify_Void_Node_Report");

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
			Node_Report_Method_Openpage(driver);
			Node_Report_Method_Verify(driver);
			Thread.sleep(1500);
		}
	
			@Test(priority=17,enabled=false)
			public void Node_Report_Method_Openpage(WebDriver driver) throws Exception
			{
				File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			/*	Thread.sleep(2000);
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
				driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"report/void");
				
				Thread.sleep(5000);
				try
				{
				//Check Node Report page opened or not
				if(driver.findElement(By.xpath(excel.getData(1, 863, 1))).getText().equalsIgnoreCase("Node"))
				{
					test.log(LogStatus.PASS, "Node report page loaded Successfully");
				
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));
				}
				else
				{
					test.log(LogStatus.FAIL, "Node report page loaded Failed");
				
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
			
			@Test(priority=18,enabled=false)
			public void Node_Report_Method_Verify(WebDriver driver) throws Exception
			{
                File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				Thread.sleep(5000);
				//Click the Node option
				driver.findElement(By.xpath(excel.getData(1, 864, 1))).click();
				//Enter the required node
				driver.findElement(By.xpath(excel.getData(1, 865, 1))).sendKeys("All");
				//Press the Enter Key
				driver.findElement(By.xpath(excel.getData(1, 865, 1))).sendKeys(Keys.ENTER);
			
				Thread.sleep(3000);
				//Click the Time Period option
				driver.findElement(By.xpath(excel.getData(1, 866, 1))).click();
				//Enter the required Time Period
				driver.findElement(By.xpath(excel.getData(1, 867, 1))).sendKeys("Date Range");
				//Press the Enter Key
				driver.findElement(By.xpath(excel.getData(1, 867, 1))).sendKeys(Keys.ENTER);
				
				Thread.sleep(2000);
				//Clear the date field
				driver.findElement(By.xpath(excel.getData(3, 1602, 1))).clear();
				//Enter the date
				driver.findElement(By.xpath(excel.getData(3, 1602, 1))).sendKeys(Utility.getReportProperty("Date_Range_From"));
	
				Thread.sleep(2000);
				//Clear the date field
				driver.findElement(By.xpath(excel.getData(3, 1603, 1))).clear();
				//Enter the date
				driver.findElement(By.xpath(excel.getData(3, 1603, 1))).sendKeys(Utility.getReportProperty("Date_Range_To"));
				
				Thread.sleep(5000);
				//Click the Run Button
				driver.findElement(By.xpath(excel.getData(1, 572, 1))).click();
				
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				Thread.sleep(5000);
				try
				{
					//Check weather the report is available for the selected time period
					if(driver.findElement(By.xpath(excel.getData(3, 1604, 1))).isDisplayed())
					{
						test.log(LogStatus.FAIL, "Void Node Report is not available for Specific Date");
					
						String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));
					}
				}
				catch(Exception fd)				
				{
					
					test.log(LogStatus.PASS, "Void Node Report is available for Specific Date");
					
					//Get the number of rows
					List<WebElement> rows = driver.findElements(By.xpath(excel.getData(1, 856, 1)));
					
					//Get the Quantity value
					String quantity = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr["+rows.size()+"]/td[5]")).getText();
					
					//Print the Total
					System.out.println("Total Quantity is : "+quantity);
					
					test.log(LogStatus.INFO, "Total Quantity is : "+quantity);
					
					//Get the amount value
					String amount = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr["+rows.size()+"]/td[6]")).getText();
					
					//Print the amount
					System.out.println("Total amount is : "+amount);
					
					test.log(LogStatus.INFO, "Total amount is : "+amount);
					
					//Get the Tax value
					String tax = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr["+rows.size()+"]/td[7]")).getText();
					
					//Print the Tax
					System.out.println("Total Tax is : "+tax);
					
					
					test.log(LogStatus.INFO, "Total Tax is : "+tax);
					
					//Get the discount value
					String discount = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr["+rows.size()+"]/td[8]")).getText();
					
					//Print the discount
					System.out.println("Total Discount is : "+discount);
					
					test.log(LogStatus.INFO, "Total Discount is : "+discount);
					
					int row = rows.size() - 1;
					
					//Click the Last Check Number
					driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr["+row+"]/td[2]/a")).click();
					
					//Clear the email field
					driver.findElement(By.xpath(excel.getData(3, 778, 1))).clear();
					//Enter the Required mail id
					driver.findElement(By.xpath(excel.getData(3, 778, 1))).sendKeys("sappanimuthub@benseron.com");
					
					Thread.sleep(2000);
					//Click the Send Receipt button
					driver.findElement(By.xpath(excel.getData(1, 833, 1))).click();
					
					Thread.sleep(1500);
					if(driver.findElement(By.xpath(excel.getData(1, 835, 1))).getText().equalsIgnoreCase("Email receipt sent successfully"))
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
					driver.findElement(By.xpath(excel.getData(1, 862, 1))).click();
					Thread.sleep(3000);wb.close();
				}
			}
	
}

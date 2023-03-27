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

public class Verify_Gift_Card_Report {

	public WebDriver driver;
	
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Verify_Gift_Card_Report");

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
			Gift_Card_Report_openpage(driver);
			Gift_Card_Report_Verify(driver);
			Gift_Card_Report_Verify_Card_Search(driver);
			Thread.sleep(1500);
		}
	
			@Test(enabled=false,priority=8)
			public void Gift_Card_Report_openpage(WebDriver driver) throws Exception
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
				WebElement element = driver.findElement(By.xpath("//span[.='Gift Card']"));
				//Scroll the page till the Gift Card option present
				je.executeScript("arguments[0].scrollIntoView(true);",element); 
		        //Click the Gift Card Option		
				driver.findElement(By.xpath("//span[.='Gift Card']")).click();*/
				
				Thread.sleep(3000);
				//Enter the Users URL
				driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id1")+"report/giftcardSale");
				
				Thread.sleep(5000);
				try
				{
				//Check Gift Card Report page opened or not
				if(driver.findElement(By.xpath(excel.getData(1, 950, 1))).getText().equalsIgnoreCase("Gift Card"))
				{
					test.log(LogStatus.PASS, "Gift Card report page loaded Successfully");
				
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));
				}
				else
				{
					test.log(LogStatus.FAIL, "Gift Card report page loaded Failed");
				
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
	
				wb.close();
			}
			
			@Test(enabled=false,priority=9)
			public void Gift_Card_Report_Verify(WebDriver driver) throws Exception
			{
				    File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
					
					FileInputStream fis = new FileInputStream(src);
					
					XSSFWorkbook wb = new XSSFWorkbook(fis);
					
					XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
					
					ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				Thread.sleep(5000);
				//Click the Activity Type option
				driver.findElement(By.xpath(excel.getData(1, 951, 1))).click();
				//Enter the required Activity Type
				driver.findElement(By.xpath(excel.getData(1, 952, 1))).sendKeys("All");
				//Press the Enter Key
				driver.findElement(By.xpath(excel.getData(1, 952, 1))).sendKeys(Keys.ENTER);
	
				Thread.sleep(5000);
				//Click the Employee option
				driver.findElement(By.xpath(excel.getData(1, 953, 1))).click();
				//Enter the required Employee
				driver.findElement(By.xpath(excel.getData(1, 954, 1))).sendKeys("All");
				//Press the Enter Key
				driver.findElement(By.xpath(excel.getData(1, 954, 1))).sendKeys(Keys.ENTER);
			
				Thread.sleep(3000);
				//Click the Time Period option
				driver.findElement(By.xpath(excel.getData(1, 955, 1))).click();
				//Enter the required Time Period
				driver.findElement(By.xpath(excel.getData(1, 956, 1))).sendKeys("Date Range");
				//Press the Enter Key
				driver.findElement(By.xpath(excel.getData(1, 956, 1))).sendKeys(Keys.ENTER);
				
				Thread.sleep(2000);
				//Clear the date field
				driver.findElement(By.xpath(excel.getData(1, 957, 1))).clear();
				//Enter the date
				driver.findElement(By.xpath(excel.getData(1, 957, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
	
				Thread.sleep(2000);
				//Clear the date field
				driver.findElement(By.xpath(excel.getData(1, 958, 1))).clear();
				//Enter the date
				driver.findElement(By.xpath(excel.getData(1, 958, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
				
				Thread.sleep(2000);
				//Click the Run Button
				driver.findElement(By.xpath(excel.getData(1, 572, 1))).click();
				
				Thread.sleep(3000);
				try
				{
					//Check weather the report is available for the selected time period
					if(driver.findElement(By.xpath(excel.getData(3, 1604, 1))).isDisplayed())
					{
						test.log(LogStatus.FAIL, "Gift Card is not available for Specific Date");
					
						String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));
					}
				}
				catch(Exception fd)
				{
					test.log(LogStatus.PASS, "Gift Card Report available for Specific Date");
					
					//Get the number of rows
					List<WebElement> rows = driver.findElements(By.xpath(excel.getData(1, 825, 1)));
					
					Thread.sleep(2000);
					//Print the Gift Card, Amount and Balance
					for(int i = 2; i <= rows.size(); i++)
					{
						//Get the Card_Number value
						String Card_Number = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/table/tbody/tr["+i+"]/td[1]")).getText();
						
						//Print the Card_Number value
						System.out.println("Gift Card number is : "+Card_Number);
						
						test.log(LogStatus.INFO, "Gift Card number is : "+Card_Number);
						
						//Get the Amount value
						String Amount = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/table/tbody/tr["+i+"]/td[7]")).getText();
						
						//Print the Amount value
						System.out.println("Amount of "+Card_Number+" is : "+Amount);
						
						test.log(LogStatus.INFO, "Amount of "+Card_Number+" is : "+Amount);
						
						//Get the Balance value
						String Balance = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/table/tbody/tr["+i+"]/td[8]")).getText();
						
						//Print the Balance value
						System.out.println("Balance of "+Card_Number+" is : "+Balance);
						
						test.log(LogStatus.INFO, "Balance of "+Card_Number+" is : "+Balance);
						wb.close();
	
					}
					driver.findElement(By.tagName("html")).sendKeys(Keys.END);
					
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));
					wb.close();
				}
				driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			}
	
			@Test(enabled=false,priority=9)
			public void Gift_Card_Report_Verify_Card_Search(WebDriver driver) throws Exception
			{
				 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
					
					FileInputStream fis = new FileInputStream(src);
					
					XSSFWorkbook wb = new XSSFWorkbook(fis);
					
					XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
					
					ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				Thread.sleep(2000);
				//Click the Activity Type option
				driver.findElement(By.xpath(excel.getData(1, 951, 1))).click();
				//Enter the required Activity Type
				driver.findElement(By.xpath(excel.getData(1, 952, 1))).sendKeys("All");
				//Press the Enter Key
				driver.findElement(By.xpath(excel.getData(1, 952, 1))).sendKeys(Keys.ENTER);
	
				
				//Click the Employee option
				driver.findElement(By.xpath(excel.getData(1, 953, 1))).click();
				//Enter the required Employee
				driver.findElement(By.xpath(excel.getData(1, 954, 1))).sendKeys("All");
				//Press the Enter Key
				driver.findElement(By.xpath(excel.getData(1, 954, 1))).sendKeys(Keys.ENTER);
			
				Thread.sleep(2000);
				//Click the Time Period option
				driver.findElement(By.xpath(excel.getData(1, 955, 1))).click();
				//Enter the required Time Period
				driver.findElement(By.xpath(excel.getData(1, 956, 1))).sendKeys("Date Range");
				//Press the Enter Key
				driver.findElement(By.xpath(excel.getData(1, 956, 1))).sendKeys(Keys.ENTER);
				
				
				//Clear the date field
				driver.findElement(By.xpath(excel.getData(1, 957, 1))).clear();
				//Enter the date
				driver.findElement(By.xpath(excel.getData(1, 957, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
	
				
				//Clear the date field
				driver.findElement(By.xpath(excel.getData(1, 958, 1))).clear();
				//Enter the date
				driver.findElement(By.xpath(excel.getData(1, 958, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
				
				Thread.sleep(2000);
				//Click the Run Button
				driver.findElement(By.xpath(excel.getData(1, 572, 1))).click();
				try
				{
					if(driver.findElement(By.xpath(excel.getData(3, 1604, 1))).isDisplayed())
					{
						test.log(LogStatus.FAIL, "There is no Gift card details available for his specific date");
					}
				}
				catch(Exception g) {
				//Get the number of rows
				List<WebElement> rows = driver.findElements(By.xpath(excel.getData(1, 825, 1)));
	
				//Get the Card_Number value
				String Card = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/table/tbody/tr["+rows.size()+"]/td[1]")).getText();
				
				//Clear the Card Input field
				driver.findElement(By.xpath(excel.getData(1, 963, 1))).clear();
				//Enter the required card number
				driver.findElement(By.xpath(excel.getData(1, 963, 1))).sendKeys(Card);
				
				Thread.sleep(2000);
				//Click the Activity Type option
				driver.findElement(By.xpath(excel.getData(1, 951, 1))).click();
				//Enter the required Activity Type
				driver.findElement(By.xpath(excel.getData(1, 952, 1))).sendKeys("All");
				//Press the Enter Key
				driver.findElement(By.xpath(excel.getData(1, 952, 1))).sendKeys(Keys.ENTER);
	
				Thread.sleep(2000);
				//Click the Employee option
				driver.findElement(By.xpath(excel.getData(1, 953, 1))).click();
				//Enter the required Employee
				driver.findElement(By.xpath(excel.getData(1, 954, 1))).sendKeys("All");
				//Press the Enter Key
				driver.findElement(By.xpath(excel.getData(1, 954, 1))).sendKeys(Keys.ENTER);
			
				
				//Click the Time Period option
				driver.findElement(By.xpath(excel.getData(1, 955, 1))).click();
				//Enter the required Time Period
				driver.findElement(By.xpath(excel.getData(1, 956, 1))).sendKeys("Date Range");
				//Press the Enter Key
				driver.findElement(By.xpath(excel.getData(1, 956, 1))).sendKeys(Keys.ENTER);
				
				
				//Clear the date field
				driver.findElement(By.xpath(excel.getData(1, 957, 1))).clear();
				//Enter the date
				driver.findElement(By.xpath(excel.getData(1, 957, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
	
				
				//Clear the date field
				driver.findElement(By.xpath(excel.getData(1, 958, 1))).clear();
				//Enter the date
				driver.findElement(By.xpath(excel.getData(1, 958, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
				
				Thread.sleep(2000);
				//Click the Run Button
				driver.findElement(By.xpath(excel.getData(1, 572, 1))).click();
				
				
				Thread.sleep(3000);
				try
				{
					//Check weather the report is available for the selected time period
					if(driver.findElement(By.xpath(excel.getData(3, 1604, 1))).isDisplayed())
					{
						test.log(LogStatus.FAIL, "Gift Card is not available for Specific Date");
						String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));
					}
				}
				catch(Exception fd)
				{
					test.log(LogStatus.PASS, "Gift Card Report available for Specific Date");
					
					Thread.sleep(2000);

						if(driver.findElement(By.xpath(excel.getData(1, 964, 1))).getText().equals(Card))
						{}
						
						else
						{
							test.log(LogStatus.FAIL, "Unwanted Card is available for the required search");wb.close();
						}
						
						driver.findElement(By.tagName("html")).sendKeys(Keys.END);
						
						String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));
				} 
				}
				wb.close();
			}
}

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


public class Verify_HouseAccount_Activity_Reports {
	
	
	public WebDriver driver;
	

	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Verify_HouseAccount_Activity_Reports");
	
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
			Employee_HAActivity_Repor_Method_Openpage(driver);
			Employee_HAActivity_Repor_Method_verify(driver);
			Employee_HAActivity_Repor_Method_verify_Card_Number(driver);
			Thread.sleep(1500);
		}

			@Test(priority=38,enabled=false)
			public void Employee_HAActivity_Repor_Method_Openpage(WebDriver driver) throws Exception
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
				WebElement element = driver.findElement(By.xpath("//div[@id='side-bar']/div/div[1]/div[2]/div/ul/li[9]/ul/li[8]/a/span"));
				//Scroll the page till the House Account option present
				je.executeScript("arguments[0].scrollIntoView(true);",element); 
			
				WebElement html = driver.findElement(By.tagName("html"));
				html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
				html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
				html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
				html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
				html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
				
				Thread.sleep(5000);
				//Click the House Account Option		
				driver.findElement(By.xpath("//div[@id='side-bar']/div/div[1]/div[2]/div/ul/li[9]/ul/li[8]/a")).click();   */
				
			    Thread.sleep(3000);
				//Enter the Users URL
				driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id1")+"houseAccountReport");
			 
			 	Thread.sleep(5000);
			 	try
			 	{
				//Check House Account page opened or not
				if(driver.findElement(By.xpath(excel.getData(1, 975, 1))).getText().equalsIgnoreCase("House Account"))
				{
					test.log(LogStatus.PASS,"House Account Report page loaded Successfully");
				
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));
				}
				else
				{
					test.log(LogStatus.FAIL,"House Account Report page loaded Failed");wb.close();
				
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
		 
		   @Test(priority=39,enabled=false)
			public void Employee_HAActivity_Repor_Method_verify(WebDriver driver) throws Exception
			{
			   File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				Thread.sleep(3000);
				//Select the Activity type option
			    driver.findElement(By.xpath(excel.getData(1, 976, 1))).click();
			    //Enter the required Activity type
			    driver.findElement(By.xpath(excel.getData(1, 977, 1))).sendKeys("All");
			    //Press the Enter key
			    driver.findElement(By.xpath(excel.getData(1, 977, 1))).sendKeys(Keys.ENTER);
		
			    //Select the Employee option
			    driver.findElement(By.xpath(excel.getData(1, 978, 1))).click();
			    //Enter the required Employee
			    driver.findElement(By.xpath(excel.getData(1, 979, 1))).sendKeys("All");
			    //Press the Enter key
			    driver.findElement(By.xpath(excel.getData(1, 979, 1))).sendKeys(Keys.ENTER);
			    
			    //Select the Time Period option(Specific date)
			    driver.findElement(By.xpath(excel.getData(1, 980, 1))).click();
			    //Enter the required Period of time
			    driver.findElement(By.xpath(excel.getData(1, 981, 1))).sendKeys("Date Range");
			    //Press the Enter key
			    driver.findElement(By.xpath(excel.getData(1, 981, 1))).sendKeys(Keys.ENTER);
			   
			    Thread.sleep(1000);
			    //Clear the Date
			    driver.findElement(By.xpath(excel.getData(3, 724, 1))).clear();
			    //Enter the Date
			    driver.findElement(By.xpath(excel.getData(3, 724, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
		
		
			    Thread.sleep(1000);
			    //Clear the Date
			    driver.findElement(By.xpath(excel.getData(3, 725, 1))).clear();
			    //Enter the Date
			    driver.findElement(By.xpath(excel.getData(3, 725, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
			    
			    Thread.sleep(1000);
			    //Click the Run
			    driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
			    Thread.sleep(10000);
		
				try
				{
					//Check weather the report is available for the selected time period
					if(driver.findElement(By.xpath(excel.getData(1, 704, 1))).isDisplayed())
					{
						test.log(LogStatus.FAIL, "House Account Activity Report is not available for Specific Date");
					
						String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));

						
					}
				}
				catch(Exception fd)
				{
					
					test.log(LogStatus.PASS, "House Account Activity Report is available for Specific Date");
					
					List<WebElement> rows = driver.findElements(By.xpath(excel.getData(1, 984, 1)));
					
					for(int i = 2; i <= rows.size(); i++)
					{
						String customer = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr["+i+"]/td[3] ")).getText();
						
						String amount = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr["+i+"]/td[7]")).getText();
						
						String balance = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr["+i+"]/td[8]")).getText();
					
						String check_number = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr["+i+"]/td[4]")).getText();
		
						System.out.println("The Card number is : "+customer+"(Check number is : "+check_number+")");
						
						test.log(LogStatus.INFO, "The Card number is : "+customer+"(Check number is : "+check_number+")");
						
						System.out.println(customer+"("+check_number+") have the Amount is : "+amount);
						
						test.log(LogStatus.INFO, customer+"("+check_number+") have the Amount is : "+amount);
						
						System.out.println(customer+"("+check_number+") have the Balance is : "+balance);
						
						test.log(LogStatus.INFO, customer+"("+check_number+") have the Balance is : "+balance);wb.close();
					
					
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
		
		    @Test(priority=39,enabled=false)
			public void Employee_HAActivity_Repor_Method_verify_Card_Number(WebDriver driver) throws Exception
			{

				   File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
					
					FileInputStream fis = new FileInputStream(src);
					
					XSSFWorkbook wb = new XSSFWorkbook(fis);
					
					XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
					
					ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				Thread.sleep(3000);
				//Select the Activity type option
			    driver.findElement(By.xpath(excel.getData(1, 976, 1))).click();
			    //Enter the required Activity type
			    driver.findElement(By.xpath(excel.getData(1, 977, 1))).sendKeys("All");
			    //Press the Enter key
			    driver.findElement(By.xpath(excel.getData(1, 977, 1))).sendKeys(Keys.ENTER);
		
			    //Select the Employee option
			    driver.findElement(By.xpath(excel.getData(1, 978, 1))).click();
			    //Enter the required Employee
			    driver.findElement(By.xpath(excel.getData(1, 979, 1))).sendKeys("All");
			    //Press the Enter key
			    driver.findElement(By.xpath(excel.getData(1, 979, 1))).sendKeys(Keys.ENTER);
			    
			    //Select the Time Period option(Specific date)
			    driver.findElement(By.xpath(excel.getData(1, 980, 1))).click();
			    //Enter the required Period of time
			    driver.findElement(By.xpath(excel.getData(1, 981, 1))).sendKeys("Date Range");
			    //Press the Enter key
			    driver.findElement(By.xpath(excel.getData(1, 981, 1))).sendKeys(Keys.ENTER);
		
			    //Clear the Date
			    driver.findElement(By.xpath(excel.getData(3, 724, 1))).clear();
			    //Enter the Date
			    driver.findElement(By.xpath(excel.getData(3, 724, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
		
			    //Clear the Date
			    driver.findElement(By.xpath(excel.getData(3, 725, 1))).clear();
			    //Enter the Date
			    driver.findElement(By.xpath(excel.getData(3, 725, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
		
			    
			    //Click the Run
			    driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
			    Thread.sleep(10000);
			    
			    
			    try
			    {
			    	if(driver.findElement(By.xpath(excel.getData(3, 1604, 1))).isDisplayed())
			    	{
			    		test.log(LogStatus.FAIL, "House Account Activty not available for this specific date");
			    	}
			    }
			    catch(Exception g)
			    {
				List<WebElement> rows = driver.findElements(By.xpath(excel.getData(1, 984, 1)));
		
				String cardNum = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr["+rows.size()+"]/td[3]")).getText();
				//System.out.println("CARD NUMBER : "+cardNum);
				
				Thread.sleep(1000);
				//Clear the Card Number Field
				driver.findElement(By.name(excel.getData(1, 990, 3))).clear();
				//Enter the Required Card Number
				driver.findElement(By.name(excel.getData(1, 990, 3))).sendKeys(cardNum);
				
				Thread.sleep(1000);
				//Select the Activity type option
			    driver.findElement(By.xpath(excel.getData(1, 976, 1))).click();
			    //Enter the required Activity type
			    driver.findElement(By.xpath(excel.getData(1, 977, 1))).sendKeys("All");
			    //Press the Enter key
			    driver.findElement(By.xpath(excel.getData(1, 977, 1))).sendKeys(Keys.ENTER);
		
			    //Select the Employee option
			    driver.findElement(By.xpath(excel.getData(1, 978, 1))).click();
			    //Enter the required Employee
			    driver.findElement(By.xpath(excel.getData(1, 979, 1))).sendKeys("All");
			    //Press the Enter key
			    driver.findElement(By.xpath(excel.getData(1, 979, 1))).sendKeys(Keys.ENTER);
			    
			    //Select the Time Period option(Specific date)
			    driver.findElement(By.xpath(excel.getData(1, 980, 1))).click();
			    //Enter the required Period of time
			    driver.findElement(By.xpath(excel.getData(1, 981, 1))).sendKeys("Date Range");
			    //Press the Enter key
			    driver.findElement(By.xpath(excel.getData(3, 724, 1))).sendKeys(Keys.ENTER);
			   
			    
			    //Clear the Date
			    driver.findElement(By.xpath(excel.getData(3, 724, 1))).clear();
			    //Enter the Date
			    driver.findElement(By.xpath(excel.getData(3, 724, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
		
			    
			    //Clear the Date
			    driver.findElement(By.xpath(excel.getData(3, 725, 1))).clear();
			    //Enter the Date
			    driver.findElement(By.xpath(excel.getData(3, 725, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
		
			    Thread.sleep(2000);
			    
			    //Click the Run
			    driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
			    Thread.sleep(10000);
		
				
				try
				{
					//Check weather the report is available for the selected time period
					if(driver.findElement(By.xpath(excel.getData(1, 704, 1))).isDisplayed())
					{
						test.log(LogStatus.FAIL, "House Account Activity Report is not available for Specific Date");
					
						String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));

					
					}
				}
				catch(Exception fd)
				{
					
					test.log(LogStatus.PASS, "House Account Activity Report is available for Specific Date");
					
					List<WebElement> filtered_rows = driver.findElements(By.xpath(excel.getData(1, 984, 1)));
					
					for(int i = 2; i <= filtered_rows.size(); i++)
					{
						String cNum = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr["+i+"]/td[3]")).getText();
						
						if(cardNum.equals(cNum))
						{
						}
						else
						{
							test.log(LogStatus.FAIL, "Card Filter details displayed fail");wb.close();
						}
						
						String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));

					}
					
				}
			}
			}
}

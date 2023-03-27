package epicList_Chrome_Account_User;

import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
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

public class Verify_Account_Balance_Report {

	public WebDriver driver;

		ExtentReports rep = ExtentManager.getInstance();
		ExtentTest test = rep.startTest("Verify_Account_Balance_Report");
		
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
//			SendMail.snedMailWithAttachment();
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
			Account_Balance_Open_Account_Balance_Report(driver);
			Account_Balance_Report_By_All_Options_Selected(driver);
			Thread.sleep(1500);
		}

		@Test(enabled=false,priority=27)
		public void Account_Balance_Open_Account_Balance_Report(WebDriver driver) throws Exception
		{     
            File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
/*				//Click the Reports option
				driver.findElement(By.xpath("//span[.='Reports']")).click();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				// Create instance of Java script executor
				JavascriptExecutor je = (JavascriptExecutor) driver;
				//Identify the WebElement which will appear after scrolling down
				WebElement element = driver.findElement(By.xpath("//span[.='Account Balance']"));
				//Scroll the page till the Revenue Center Report option present
				je.executeScript("arguments[0].scrollIntoView(true);",element); 
		        //Click the Account Balance Option		
				driver.findElement(By.xpath("//span[.='Account Balance']")).click();*/
				driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id1")+"accountBalance");
				Thread.sleep(10000);
				try
				{
				//Check Customer Preference Report page opened or not
				if(driver.findElement(By.xpath(excel.getData(3, 1049, 1))).getText().equalsIgnoreCase("Account Balance"))
				{
					test.log(LogStatus.PASS,"Account Balance Report page loaded Successfully");
				
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));
				}
				else
				{
					test.log(LogStatus.FAIL,"Account Balance Report page loaded Failed");
				
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
	
		@Test(enabled=false,priority=28)
		public void Account_Balance_Report_By_All_Options_Selected(WebDriver driver) throws Exception
		{    
			 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		    Thread.sleep(2000);
		    //Clear the Date field
		    //driver.findElement(By.xpath("//input[@name='date']")).clear();
		    //Enter the Required Date
		    //driver.findElement(By.xpath("//input[@name='date']")).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
		    	
		    //Click the Calendar option
		    driver.findElement(By.xpath(excel.getData(3, 1050, 1))).click();
		    
		    String date = Utility.getReportPropertyUser("Date_Range_From");
		    String day = date.substring(0, 2);
		    String month = date.substring(3, 6);
		    String year = date.substring(7, 11);
		    
		    Thread.sleep(1000);
		    //Click the Month heading
		    driver.findElement(By.xpath(excel.getData(3, 1051, 1))).click();
		    
		/*    Thread.sleep(1000);
		    //Click the Month heading
		    driver.findElement(By.xpath(excel.getData(3, 1051, 1))).click();
		    
		    Thread.sleep(1000);
		    //click the required year
		    driver.findElement(By.xpath("//span[.="+year+"]")).click();
		    
		    Thread.sleep(1000);
		    //Click the required month
		    driver.findElement(By.xpath("//span[contains(.,"+month+")]")).click();
		    
		    Thread.sleep(1000);
		    //Click the required day
		    driver.findElement(By.xpath("//span[contains(.,"+day+")]")).click();
		    
		    Thread.sleep(3000);
		    //Click the Run
		    driver.findElement(By.xpath(excel.getData(3, 1055, 1))).click();
		    Thread.sleep(8000);
		    
		    try
		    {
		    	//Check whether the all options are displayed or not
			    if(!driver.findElement(By.xpath(excel.getData(3, 704, 1))).isDisplayed())
			    {
			    	test.log(LogStatus.PASS, "Required Account Balance Report is Available");
			    	
			    	//List<WebElement> rows = driver.findElements(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr"));
			    	
			    	test.log(LogStatus.INFO, "Debit Balance is : "+driver.findElement(By.xpath(excel.getData(3, 1056, 1))).getText());
			    	
			    	test.log(LogStatus.INFO, "Credit Balance is : "+driver.findElement(By.xpath(excel.getData(3, 1057, 1))).getText());
			    	
			    }
		    }
		    catch(Exception df)
		    {
		    	test.log(LogStatus.FAIL, "Required Account Balance Report is missing");wb.close();
		    }
*/
			Thread.sleep(1000);
    		//Click the Previous Year
    		driver.findElement(By.xpath("//i[@class='glyphicon glyphicon-chevron-left']")).click();
	
		    
		 	try
	    	{
			    	if(year.equalsIgnoreCase(driver.findElement(By.xpath("//strong[.='year']")).getText()))
			    	{				    	
				    	if(driver.findElement(By.xpath("//strong[.="+year+"]")).isDisplayed())
				    	{
				    		//Click the March month
				    		driver.findElement(By.xpath("//span[contains(.,'"+month+"')]")).click();
				    		
				    		Thread.sleep(1000);
				    		//Click the date
				    		driver.findElement(By.xpath("//span[.='"+day+"']")).click();
				    		
						    Thread.sleep(5000);
						    //Click the Run
						    driver.findElement(By.xpath("//span[.='Run']")).click();
						    Thread.sleep(8000);
						    
						    //Check whether the all options are displayed or not
						    if(driver.findElement(By.xpath("//h3[.='No transaction for selected time period']")).isDisplayed())
						    {
						    	test.log(LogStatus.FAIL, "Required Account Balance Report is missing");
						   
						    	//Thread.sleep(3000);
						    	//driver.findElement(By.tagName("html")).sendKeys(Keys.END);
						    	
						    	String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
								String s="data:image/png;base64,"+scnShot;
								test.log(LogStatus.INFO,test.addScreenCapture(s));

								//Thread.sleep(3000);
								//driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
						    
						    }
						    else
						    {
						    	test.log(LogStatus.PASS, "Required Account Balance Report is Available");
						    	
						    	//List<WebElement> rows = driver.findElements(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr"));
						    	
						    	test.log(LogStatus.INFO, "Debit Balance is : "+driver.findElement(By.xpath("//th[.='Total']/../th[5]")).getText());
						    	
						    	test.log(LogStatus.INFO, "Credit Balance is : "+driver.findElement(By.xpath("//th[.='Total']/../th[6]")).getText());
						   
						    
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

			    
	    	}
	    	catch(Exception e)
	    	{
	    		
	    		Thread.sleep(2000);
	    		//Click the Previous Year
	    		driver.findElement(By.xpath("//i[@class='glyphicon glyphicon-chevron-left']")).click();
    	
	    		for(int i = 1; i <= 100; i++)
			    {
	    	
		    		Thread.sleep(3000);
			    	if(driver.findElement(By.xpath("//strong[.='2020']")).isDisplayed())
			    	{
			    		//Click the March month
			    		driver.findElement(By.xpath("//span[contains(.,'"+month+"')]")).click();
			    		
			    		Thread.sleep(1000);
			    		//Click the date
			    		driver.findElement(By.xpath("//span[.='"+day+"']")).click();
			    		
					    Thread.sleep(5000);
					    //Click the Run
					    driver.findElement(By.xpath("//span[.='Run']")).click();
					    Thread.sleep(8000);
					    
					    //Check whether the all options are displayed or not
					    if(driver.findElement(By.xpath("//h3[.='No transaction for selected time period']")).isDisplayed())
					    {
					    	test.log(LogStatus.FAIL, "Required Account Balance Report is missing");
					   
					    
					    	//Thread.sleep(3000);
					    	//driver.findElement(By.tagName("html")).sendKeys(Keys.END);
					    	
					    	String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
							String s="data:image/png;base64,"+scnShot;
							test.log(LogStatus.INFO,test.addScreenCapture(s));

							//Thread.sleep(3000);
							//driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
					    
					    }
					    else
					    {
					    	test.log(LogStatus.PASS, "Required Account Balance Report is Available");
					    	
					    	//List<WebElement> rows = driver.findElements(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr"));
					    	
					    	test.log(LogStatus.INFO, "Debit Balance is : "+driver.findElement(By.xpath("//th[.='Total']/../th[5]")).getText());
					    	
					    	test.log(LogStatus.INFO, "Credit Balance is : "+driver.findElement(By.xpath("//th[.='Total']/../th[6]")).getText());
					   
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
	    	}  wb.close(); 
	}


		    
		

}

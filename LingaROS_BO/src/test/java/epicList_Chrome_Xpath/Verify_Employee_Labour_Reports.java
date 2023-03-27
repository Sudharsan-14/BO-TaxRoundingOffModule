package epicList_Chrome_Xpath;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import epicList_Chrome.Utility;

public class Verify_Employee_Labour_Reports{
	
	public WebDriver driver;

	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Verify_Employee_Labour_Reports");
	
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
			Employee_Labour_Report_Method_DailyReport_Openpage(driver);
			Employee_Labour_Report_Method_verifyDailyReport(driver);
			Employee_Labour_Report_Method_verifyWeeklyReport(driver);
			Thread.sleep(1500);
		}

			@Test(enabled=false,priority=31)
			public void Employee_Labour_Report_Method_DailyReport_Openpage(WebDriver driver) throws Exception
			{
			/*	//Click the Reports option
				driver.findElement(By.xpath("//span[.='Reports']")).click();
				// Create instance of Java script executor
				JavascriptExecutor je = (JavascriptExecutor) driver;
				//Identify the WebElement which will appear after scrolling down
				WebElement element = driver.findElement(By.xpath("//span[.='Employee']"));
				//Scroll the page till the Transaction option present
				je.executeScript("arguments[0].scrollIntoView(true);",element); 
		        //Click the Employee Option		
				driver.findElement(By.xpath("//span[.='Employee']")).click(); */
				
				Thread.sleep(3000);
				//Enter the Users URL
				driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"labour");
				
				Thread.sleep(5000);
				
				//Check Labour Report page opened or not
				if(driver.findElement(By.xpath("//a[.='Labour']")).getText().equalsIgnoreCase("Labour"))
				{
					test.log(LogStatus.PASS,"Labour Report page loaded Successfully");
				}
				else
				{
					test.log(LogStatus.FAIL,"Labour Report page loaded Failed");
				}
				
			}
			
			@Test(enabled=false,priority=32)
			public void Employee_Labour_Report_Method_verifyDailyReport(WebDriver driver) throws Exception
			{
				
				Thread.sleep(1000);
				//Select the Employee option
			    driver.findElement(By.xpath("//form[@name='labour']/div[1]/div/div/a")).click();
			    //Enter the required Employee
			    driver.findElement(By.xpath("//form[@name='labour']/div[1]/div/div/div/div/input")).sendKeys("All");
			    //Press the Enter key
			    driver.findElement(By.xpath("//form[@name='labour']/div[1]/div/div/div/div/input")).sendKeys(Keys.ENTER);
			    
			    Thread.sleep(1000);
			    //Select the Process as Daily
				Select process = new Select(driver.findElement(By.xpath("//form[@name='labour']/div[2]/div/select")));
				process.selectByVisibleText("Daily");                               
			    
		  	    //Thread.sleep(1000);
			    //Select the Specific date Time Period option
			    driver.findElement(By.xpath("//form[@name='labour']/div[3]/div/div/a")).click();
			    //Enter the required Period of time
			    driver.findElement(By.xpath("//form[@name='labour']/div[3]/div/div/div/div/input")).sendKeys("Date Range");
			    //Press the Enter key
			    driver.findElement(By.xpath("//form[@name='labour']/div[3]/div/div/div/div/input")).sendKeys(Keys.ENTER);
			    
			    Thread.sleep(1000);
			    //Clear the Date field
			    driver.findElement(By.xpath("//form[@name='labour']/div[4]/div/div/input[1]")).clear();
			    //Enter the Date
			    driver.findElement(By.xpath("//form[@name='labour']/div[4]/div/div/input[1]")).sendKeys(Utility.getReportProperty("Date_Range_From"));

			    Thread.sleep(1000);
			    //Clear the Date field
			    driver.findElement(By.xpath("//form[@name='labour']/div[4]/div/div/input[2]")).clear();
			    //Enter the Date
			    driver.findElement(By.xpath("//form[@name='labour']/div[4]/div/div/input[2]")).sendKeys(Utility.getReportProperty("Date_Range_To"));
				  
				    //Select the Format option
				    driver.findElement(By.xpath("//form[@name='labour']/div[5]/div/div/a")).click();
				    //Enter the required Format
				    driver.findElement(By.xpath("//form[@name='labour']/div[5]/div/div/div/div/input")).sendKeys("In Time");
				    //Press the Enter key
				    driver.findElement(By.xpath("//form[@name='labour']/div[5]/div/div/div/div/input")).sendKeys(Keys.ENTER);
				  
				    //Select the Active/Inactive option
				    driver.findElement(By.xpath("//form[@name='labour']/div[6]/div/div/a")).click();
				    //Enter the required Active/Inactive
				    driver.findElement(By.xpath("//form[@name='labour']/div[6]/div/div/div/div/input")).sendKeys("All");
				    //Press the Enter key
				    driver.findElement(By.xpath("//form[@name='labour']/div[6]/div/div/div/div/input")).sendKeys(Keys.ENTER);
				    
				    //Click the Run
				    driver.findElement(By.xpath("//button[@type='submit']")).click();
				    Thread.sleep(1000);
				   
					try
					{
						//Check weather the report is available for the selected time period
						if(driver.findElement(By.xpath("//h3[.='No transaction for selected time period']")).isDisplayed())
						{
							test.log(LogStatus.FAIL, "Employee Labour Report is not available for Specific Date");
						}
					}
					catch(Exception dsf)
					{
						
						test.log(LogStatus.PASS, "Employee Labour Report(In Time and Daily) is available for Specific Date");
						
						System.out.println("******* The Below is Employee Labour Report for In Time(Daily) *******");
						
						test.log(LogStatus.INFO, "******* The Below is Employee Labour Report for In Time(Daily) *******");
						
						List<WebElement> rows = driver.findElements(By.xpath("//table[@class]/tbody/tr"));
						
						//Get the Net Sales Value
						String net_Sales = driver.findElement(By.xpath("//table[@class]/tbody/tr["+rows.size()+"]/td[2]")).getText();
						
						System.out.println("Total Net Sales is : "+net_Sales);
						
						test.log(LogStatus.INFO, "Total Net Sales is : "+net_Sales);
						
						//Get the Hours Value
						String hours = driver.findElement(By.xpath("//table[@class]/tbody/tr["+rows.size()+"]/td[3]")).getText();
						
						System.out.println("Total Hours is : "+hours);
						
						test.log(LogStatus.INFO, "Total Hours is : "+hours);
						
						//Get the Labour Value
						String labour = driver.findElement(By.xpath("//table[@class]/tbody/tr["+rows.size()+"]/td[4]")).getText();
						
						System.out.println("Total Labour is : "+labour);
						
						test.log(LogStatus.INFO, "Total Labour is : "+labour);
						
						//Get the Percentage of Net Sales Value
						String percentage_Of_Net_Sales = driver.findElement(By.xpath("//table[@class]/tbody/tr["+rows.size()+"]/td[5]")).getText();
						
						System.out.println("Total Percentage of Net Sales is : "+percentage_Of_Net_Sales);
						
						test.log(LogStatus.INFO, "Total Percentage of Net Sales is : "+percentage_Of_Net_Sales);
						
						//Get the Labour Percentage
						String labour_Percentage = driver.findElement(By.xpath("//table[@class]/tbody/tr["+rows.size()+"]/td[6]")).getText();
						
						System.out.println("Total Labour Percentage is : "+labour_Percentage);
						
						test.log(LogStatus.INFO, "Total Labour Percentage is : "+labour_Percentage);
					}
								  	    	
					
					Thread.sleep(1000);
					//Select the Employee option
				    driver.findElement(By.xpath("//form[@name='labour']/div[1]/div/div/a")).click();
				    //Enter the required Employee
				    driver.findElement(By.xpath("//form[@name='labour']/div[1]/div/div/div/div/input")).sendKeys("All");
				    //Press the Enter key
				    driver.findElement(By.xpath("//form[@name='labour']/div[1]/div/div/div/div/input")).sendKeys(Keys.ENTER);
				    
				    Thread.sleep(3000);
				    //Select the Process as Daily
					Select process1 = new Select(driver.findElement(By.xpath("//form[@name='labour']/div[2]/div/select")));
					process1.selectByVisibleText("Daily");                               
				    
			  	    //Thread.sleep(1000);
			    //Select the Specific date Time Period option
			    driver.findElement(By.xpath("//form[@name='labour']/div[3]/div/div/a")).click();
			    //Enter the required Period of time
			    driver.findElement(By.xpath("//form[@name='labour']/div[3]/div/div/div/div/input")).sendKeys("Date Range");
			    //Press the Enter key
			    driver.findElement(By.xpath("//form[@name='labour']/div[3]/div/div/div/div/input")).sendKeys(Keys.ENTER);
			    
			    Thread.sleep(1000);
			    //Clear the Date field
			    driver.findElement(By.xpath("//form[@name='labour']/div[4]/div/div/input[1]")).clear();
			    //Enter the Date
			    driver.findElement(By.xpath("//form[@name='labour']/div[4]/div/div/input[1]")).sendKeys(Utility.getReportProperty("Date_Range_From"));

			    Thread.sleep(1000);
			    //Clear the Date field
			    driver.findElement(By.xpath("//form[@name='labour']/div[4]/div/div/input[2]")).clear();
			    //Enter the Date
			    driver.findElement(By.xpath("//form[@name='labour']/div[4]/div/div/input[2]")).sendKeys(Utility.getReportProperty("Date_Range_To"));
					  
					    //Select the Format option
					    driver.findElement(By.xpath("//form[@name='labour']/div[5]/div/div/a")).click();
					    //Enter the required Format
					    driver.findElement(By.xpath("//form[@name='labour']/div[5]/div/div/div/div/input")).sendKeys("In Hours");
					    //Press the Enter key
					    driver.findElement(By.xpath("//form[@name='labour']/div[5]/div/div/div/div/input")).sendKeys(Keys.ENTER);
					  
					    //Select the Active/Inactive option
					    driver.findElement(By.xpath("//form[@name='labour']/div[6]/div/div/a")).click();
					    //Enter the required Active/Inactive
					    driver.findElement(By.xpath("//form[@name='labour']/div[6]/div/div/div/div/input")).sendKeys("All");
					    //Press the Enter key
					    driver.findElement(By.xpath("//form[@name='labour']/div[6]/div/div/div/div/input")).sendKeys(Keys.ENTER);
					    
					    //Click the Run
					    driver.findElement(By.xpath("//button[@type='submit']")).click();
					    Thread.sleep(1000);
					   
						try
						{
							//Check weather the report is available for the selected time period
							if(driver.findElement(By.xpath("//h3[.='No transaction for selected time period']")).isDisplayed())
							{
								test.log(LogStatus.FAIL, "Employee Labour Report is not available for Specific Date");
							}
						}
						catch(Exception fd)
						{
							
							test.log(LogStatus.PASS, "Employee Labour Report(In Hours and Daily) is available for Specific Date");
							
							System.out.println("******* The Below is Employee Labour Report for In Hours(Daily) *******");
							
							test.log(LogStatus.INFO, "******* The Below is Employee Labour Report for In Hours(Daily) *******");
							
							List<WebElement> rows = driver.findElements(By.xpath("//table[@class]/tbody/tr"));
							
							//Get the Net Sales Value
							String net_Sales = driver.findElement(By.xpath("//table[@class]/tbody/tr["+rows.size()+"]/td[2]")).getText();
							
							System.out.println("Total Net Sales is : "+net_Sales);
							
							test.log(LogStatus.INFO, "Total Net Sales is : "+net_Sales);
							
							//Get the Hours Value
							String hours = driver.findElement(By.xpath("//table[@class]/tbody/tr["+rows.size()+"]/td[3]")).getText();
							
							System.out.println("Total Hours is : "+hours);
							
							test.log(LogStatus.INFO, "Total Hours is : "+hours);
							
							//Get the Labour Value
							String labour = driver.findElement(By.xpath("//table[@class]/tbody/tr["+rows.size()+"]/td[4]")).getText();
							
							System.out.println("Total Labour is : "+labour);
							
							test.log(LogStatus.INFO, "Total Labour is : "+labour);
							
							//Get the Percentage of Net Sales Value
							String percentage_Of_Net_Sales = driver.findElement(By.xpath("//table[@class]/tbody/tr["+rows.size()+"]/td[5]")).getText();
							
							System.out.println("Total Percentage of Net Sales is : "+percentage_Of_Net_Sales);
							
							test.log(LogStatus.INFO, "Total Percentage of Net Sales is : "+percentage_Of_Net_Sales);
							
							//Get the Labour Percentage
							String labour_Percentage = driver.findElement(By.xpath("//table[@class]/tbody/tr["+rows.size()+"]/td[6]")).getText();
							
							System.out.println("Total Labour Percentage is : "+labour_Percentage);
							
							test.log(LogStatus.INFO, "Total Labour Percentage is : "+labour_Percentage);
						} 
			}

			@Test(enabled=false,priority=33)
			public void Employee_Labour_Report_Method_verifyWeeklyReport(WebDriver driver) throws Exception
			{

				
				Thread.sleep(1000);
				//Select the Employee option
			    driver.findElement(By.xpath("//form[@name='labour']/div[1]/div/div/a")).click();
			    //Enter the required Employee
			    driver.findElement(By.xpath("//form[@name='labour']/div[1]/div/div/div/div/input")).sendKeys("All");
			    //Press the Enter key
			    driver.findElement(By.xpath("//form[@name='labour']/div[1]/div/div/div/div/input")).sendKeys(Keys.ENTER);
			  
			    //Select the Process as Daily
				Select process = new Select(driver.findElement(By.xpath("//form[@name='labour']/div[2]/div/select")));
				process.selectByVisibleText("Weekly");
					
		  	    //Thread.sleep(1000);
			    //Select the Specific date Time Period option
			    driver.findElement(By.xpath("//form[@name='labour']/div[3]/div/div/a")).click();
			    //Enter the required Period of time
			    driver.findElement(By.xpath("//form[@name='labour']/div[3]/div/div/div/div/input")).sendKeys("Date Range");
			    //Press the Enter key
			    driver.findElement(By.xpath("//form[@name='labour']/div[3]/div/div/div/div/input")).sendKeys(Keys.ENTER);
			    
			    Thread.sleep(1000);
			    //Clear the Date field
			    driver.findElement(By.xpath("//form[@name='labour']/div[4]/div/div/input[1]")).clear();
			    //Enter the Date
			    driver.findElement(By.xpath("//form[@name='labour']/div[4]/div/div/input[1]")).sendKeys(Utility.getReportProperty("Date_Range_From"));

			    Thread.sleep(1000);
			    //Clear the Date field
			    driver.findElement(By.xpath("//form[@name='labour']/div[4]/div/div/input[2]")).clear();
			    //Enter the Date
			    driver.findElement(By.xpath("//form[@name='labour']/div[4]/div/div/input[2]")).sendKeys(Utility.getReportProperty("Date_Range_To"));
			    
			    //Select the Format option
			    driver.findElement(By.xpath("//form[@name='labour']/div[5]/div/div/a")).click();
			    //Enter the required Format
			    driver.findElement(By.xpath("//form[@name='labour']/div[5]/div/div/div/div/input")).sendKeys("In Hours");
			    //Press the Enter key
			    driver.findElement(By.xpath("//form[@name='labour']/div[5]/div/div/div/div/input")).sendKeys(Keys.ENTER);
			    
			    //Select the Active/Inactive option
			    driver.findElement(By.xpath("//form[@name='labour']/div[6]/div/div/a")).click();
			    //Enter the required Active/Inactive
			    driver.findElement(By.xpath("//form[@name='labour']/div[6]/div/div/div/div/input")).sendKeys("All");
			    //Press the Enter key
			    driver.findElement(By.xpath("//form[@name='labour']/div[6]/div/div/div/div/input")).sendKeys(Keys.ENTER);

			    Thread.sleep(1000);
			     //Click the Run
			    driver.findElement(By.xpath("//button[@type='submit']")).click();
			    Thread.sleep(1000);

				try
				{
					//Check weather the report is available for the selected time period
					if(driver.findElement(By.xpath("//h3[.='No transaction for selected time period']")).isDisplayed())
					{
						test.log(LogStatus.FAIL, "Employee Labour Report is not available for Specific Date");
					}
				}
				catch(Exception fd)
				{
					
					test.log(LogStatus.PASS, "Employee Labour Report(In Hours and Weekly) is available for Specific Date");
					
					System.out.println("******* The Below is Employee Labour Report for In Hours(Weekly) *******");
					
					test.log(LogStatus.INFO, "******* The Below is Employee Labour Report for In Hours(Weekly) *******");
					
					List<WebElement> rows = driver.findElements(By.xpath("//table[@class]/tbody/tr"));
					
					//Get the Net Sales Value
					String net_Sales = driver.findElement(By.xpath("//table[@class]/tbody/tr["+rows.size()+"]/td[2]")).getText();
					
					System.out.println("Total Net Sales is : "+net_Sales);
					
					test.log(LogStatus.INFO, "Total Net Sales is : "+net_Sales);
					
					//Get the Hours Value
					String hours = driver.findElement(By.xpath("//table[@class]/tbody/tr["+rows.size()+"]/td[3]")).getText();
					
					System.out.println("Total Hours is : "+hours);
					
					test.log(LogStatus.INFO, "Total Hours is : "+hours);
					
					//Get the Labour Value
					String labour = driver.findElement(By.xpath("//table[@class]/tbody/tr["+rows.size()+"]/td[4]")).getText();
					
					System.out.println("Total Labour is : "+labour);
					
					test.log(LogStatus.INFO, "Total Labour is : "+labour);
					
					//Get the Percentage of Net Sales Value
					String percentage_Of_Net_Sales = driver.findElement(By.xpath("//table[@class]/tbody/tr["+rows.size()+"]/td[5]")).getText();
					
					System.out.println("Total Percentage of Net Sales is : "+percentage_Of_Net_Sales);
					
					test.log(LogStatus.INFO, "Total Percentage of Net Sales is : "+percentage_Of_Net_Sales);
					
					//Get the Labour Percentage
					String labour_Percentage = driver.findElement(By.xpath("//table[@class]/tbody/tr["+rows.size()+"]/td[6]")).getText();
					
					System.out.println("Total Labour Percentage is : "+labour_Percentage);
					
					test.log(LogStatus.INFO, "Total Labour Percentage is : "+labour_Percentage);
				}  
				
				Thread.sleep(1000);
				//Select the Employee option
			    driver.findElement(By.xpath("//form[@name='labour']/div[1]/div/div/a")).click();
			    //Enter the required Employee
			    driver.findElement(By.xpath("//form[@name='labour']/div[1]/div/div/div/div/input")).sendKeys("All");
			    //Press the Enter key
			    driver.findElement(By.xpath("//form[@name='labour']/div[1]/div/div/div/div/input")).sendKeys(Keys.ENTER);
			  
			    //Select the Process as Daily
				Select process1 = new Select(driver.findElement(By.xpath("//form[@name='labour']/div[2]/div/select")));
				process1.selectByVisibleText("Weekly");
					
			    Thread.sleep(1000);
			    //Select the Specific date Time Period option
			    driver.findElement(By.xpath("//form[@name='labour']/div[3]/div/div/a")).click();
			    //Enter the required Period of time
			    driver.findElement(By.xpath("//form[@name='labour']/div[3]/div/div/div/div/input")).sendKeys("Date Range");
			    //Press the Enter key
			    driver.findElement(By.xpath("//form[@name='labour']/div[3]/div/div/div/div/input")).sendKeys(Keys.ENTER);
			    
			    Thread.sleep(1000);
			    //Clear the Date field
			    driver.findElement(By.xpath("//form[@name='labour']/div[4]/div/div/input[1]")).clear();
			    //Enter the Date
			    driver.findElement(By.xpath("//form[@name='labour']/div[4]/div/div/input[1]")).sendKeys(Utility.getReportProperty("Date_Range_From"));

			    Thread.sleep(1000);
			    //Clear the Date field
			    driver.findElement(By.xpath("//form[@name='labour']/div[4]/div/div/input[2]")).clear();
			    //Enter the Date
			    driver.findElement(By.xpath("//form[@name='labour']/div[4]/div/div/input[2]")).sendKeys(Utility.getReportProperty("Date_Range_To"));
			    
			    //Select the Format option
			    driver.findElement(By.xpath("//form[@name='labour']/div[5]/div/div/a")).click();
			    //Enter the required Format
			    driver.findElement(By.xpath("//form[@name='labour']/div[5]/div/div/div/div/input")).sendKeys("In Time");
			    //Press the Enter key
			    driver.findElement(By.xpath("//form[@name='labour']/div[5]/div/div/div/div/input")).sendKeys(Keys.ENTER);
			    
			    //Select the Active/Inactive option
			    driver.findElement(By.xpath("//form[@name='labour']/div[6]/div/div/a")).click();
			    //Enter the required Active/Inactive
			    driver.findElement(By.xpath("//form[@name='labour']/div[6]/div/div/div/div/input")).sendKeys("All");
			    //Press the Enter key
			    driver.findElement(By.xpath("//form[@name='labour']/div[6]/div/div/div/div/input")).sendKeys(Keys.ENTER);

			    Thread.sleep(1000);
			     //Click the Run
			    driver.findElement(By.xpath("//button[@type='submit']")).click();
			    Thread.sleep(1000);
			    
			    
				try
				{
					//Check weather the report is available for the selected time period
					if(driver.findElement(By.xpath("//h3[.='No transaction for selected time period']")).isDisplayed())
					{
						test.log(LogStatus.FAIL, "Employee Labour Report is not available for Specific Date");
					}
				}
				catch(Exception fds)
				{
					
					test.log(LogStatus.PASS, "Employee Labour Report(In Time and Weekly) is available for Specific Date");
					
					System.out.println("******* The Below is Employee Labour Report for In Time(Weekly) *******");
					
					test.log(LogStatus.INFO, "******* The Below is Employee Labour Report for In Time(Weekly) *******");
					
					List<WebElement> rows = driver.findElements(By.xpath("//table[@class]/tbody/tr"));
					
					//Get the Net Sales Value
					String net_Sales = driver.findElement(By.xpath("//table[@class]/tbody/tr["+rows.size()+"]/td[2]")).getText();
					
					System.out.println("Total Net Sales is : "+net_Sales);
					
					test.log(LogStatus.INFO, "Total Net Sales is : "+net_Sales);
					
					//Get the Hours Value
					String hours = driver.findElement(By.xpath("//table[@class]/tbody/tr["+rows.size()+"]/td[3]")).getText();
					
					System.out.println("Total Hours is : "+hours);
					
					test.log(LogStatus.INFO, "Total Hours is : "+hours);
					
					//Get the Labour Value
					String labour = driver.findElement(By.xpath("//table[@class]/tbody/tr["+rows.size()+"]/td[4]")).getText();
					
					System.out.println("Total Labour is : "+labour);
					
					test.log(LogStatus.INFO, "Total Labour is : "+labour);
					
					//Get the Percentage of Net Sales Value
					String percentage_Of_Net_Sales = driver.findElement(By.xpath("//table[@class]/tbody/tr["+rows.size()+"]/td[5]")).getText();
					
					System.out.println("Total Percentage of Net Sales is : "+percentage_Of_Net_Sales);
					
					test.log(LogStatus.INFO, "Total Percentage of Net Sales is : "+percentage_Of_Net_Sales);
					
					//Get the Labour Percentage
					String labour_Percentage = driver.findElement(By.xpath("//table[@class]/tbody/tr["+rows.size()+"]/td[6]")).getText();
					
					System.out.println("Total Labour Percentage is : "+labour_Percentage);
					
					test.log(LogStatus.INFO, "Total Labour Percentage is : "+labour_Percentage);
				}

			}

}

package epicList_Chrome_Xpath;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class Verify_Employee_Labour_By_Job_Code_Reports{
	
	public WebDriver driver;

	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Verify_Employee_Labour_By_Job_Code_Reports");
	
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
			//Close the Browser
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
			if(driver.findElement(By.xpath("//div[@id='x-content-band-1']/div/div[2]/h2")).getText().equalsIgnoreCase("Account Login"))
			{
		    	test.log(LogStatus.PASS, "User Logged out Successfully LingaPOs");
			}
			else
			{
				test.log(LogStatus.FAIL, "User Logged out Failed LingaPos");
			}
			Thread.sleep(3000);
			//Close the Browser
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
			Employee_Labour_By_Job_Code_Report_Method_PageOpen(driver);
			Employee_Labour_By_Job_Code_Report_Method_verifyDailyReport(driver);
			Employee_Labour_By_Job_Code_Report_Method_verifyWeeklyReport(driver);
			Thread.sleep(1500);
		}

			@Test(priority=60,enabled=false)
			public void Employee_Labour_By_Job_Code_Report_Method_PageOpen(WebDriver driver) throws Exception
			{
	/*			//Click the Reports option
				driver.findElement(By.xpath("//span[.='Reports']")).click();
				// Create instance of Java script executor
				JavascriptExecutor je = (JavascriptExecutor) driver;
				//Identify the WebElement which will appear after scrolling down
				WebElement element = driver.findElement(By.xpath("//span[.='Employee']"));
				//Scroll the page till the Transaction option present
				je.executeScript("arguments[0].scrollIntoView(true);",element); 
		        //Click the Employee Option		
				driver.findElement(By.xpath("//span[.='Employee']")).click();
				Thread.sleep(1000);
				//Check Labour By Job Code Report page opened or not
				if(driver.findElement(By.xpath("//a[.='Attendance']")).getText().equalsIgnoreCase("Attendance"))
				{
					test.log(LogStatus.PASS,"Attendance Report page loaded Successfully");
				}
				else
				{
					test.log(LogStatus.FAIL,"Attendance Report page loaded Failed");
				}*/
				Thread.sleep(3000);
				//Click the Labour By Job Code option
				//driver.findElement(By.xpath("//span[contains(.,'Labor By Job Code')]")).click();
				//Enter the Users URL
				driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"laborReport");
	
				Thread.sleep(1500);
				//Check Labour By Job Code Report page opened or not
				if(driver.findElement(By.xpath("//a[.='Labor Report']")).getText().equalsIgnoreCase("Labor Report"))
				{
					test.log(LogStatus.PASS,"Labour By Job Code Report page loaded Successfully");
				}
				else
				{
					test.log(LogStatus.FAIL,"Labour By Job Code Report page loaded Failed");
				}
				
			}
	
			@Test(priority=61,enabled=false)
			public void Employee_Labour_By_Job_Code_Report_Method_verifyDailyReport(WebDriver driver) throws Exception
			{
				
				Thread.sleep(1000);
				//Select the Employee option
			    driver.findElement(By.xpath("//form[@name='payRoll']/div[1]/div/div/a")).click();
			    //Enter the required Employee
			    driver.findElement(By.xpath("//form[@name='payRoll']/div[1]/div/div/div/div/input")).sendKeys("All");
			    //Press the Enter key
			    driver.findElement(By.xpath("//form[@name='payRoll']/div[1]/div/div/div/div/input")).sendKeys(Keys.ENTER);
			    
			    Thread.sleep(1000);
			    //Select the Process as Daily
				Select process = new Select(driver.findElement(By.xpath("//form[@name='payRoll']/div[2]/div/select")));
				process.selectByVisibleText("Daily");                               
			    
		  	    //Thread.sleep(1000);
			    //Select the Specific date Time Period option
			    driver.findElement(By.xpath("//form[@name='payRoll']/div[3]/div/div/a")).click();
			    //Enter the required Period of time
			    driver.findElement(By.xpath("//form[@name='payRoll']/div[3]/div/div/div/div/input")).sendKeys("Date Range");
			    //Press the Enter key
			    driver.findElement(By.xpath("//form[@name='payRoll']/div[3]/div/div/div/div/input")).sendKeys(Keys.ENTER);
			    
			    Thread.sleep(1000);
			    //Clear the Date field
			    driver.findElement(By.xpath("//form[@name='payRoll']/div[4]/div/div/input[1]")).clear();
			    //Enter the Date
			    driver.findElement(By.xpath("//form[@name='payRoll']/div[4]/div/div/input[1]")).sendKeys(Utility.getReportProperty("Date_Range_From"));
	
			    Thread.sleep(1000);
			    //Clear the Date field
			    driver.findElement(By.xpath("//form[@name='payRoll']/div[4]/div/div/input[2]")).clear();
			    //Enter the Date
			    driver.findElement(By.xpath("//form[@name='payRoll']/div[4]/div/div/input[2]")).sendKeys(Utility.getReportProperty("Date_Range_To"));
				  
				    //Select the Format option
				    driver.findElement(By.xpath("//form[@name='payRoll']/div[5]/div/div/a")).click();
				    //Enter the required Format
				    driver.findElement(By.xpath("//form[@name='payRoll']/div[5]/div/div/div/div/input")).sendKeys("In Time");
				    //Press the Enter key
				    driver.findElement(By.xpath("//form[@name='payRoll']/div[5]/div/div/div/div/input")).sendKeys(Keys.ENTER);
				  
				    //Select the Active/Inactive option
				    driver.findElement(By.xpath("//form[@name='payRoll']/div[6]/div/div/a")).click();
				    //Enter the required Active/Inactive
				    driver.findElement(By.xpath("//form[@name='payRoll']/div[6]/div/div/div/div/input")).sendKeys("All");
				    //Press the Enter key
				    driver.findElement(By.xpath("//form[@name='payRoll']/div[6]/div/div/div/div/input")).sendKeys(Keys.ENTER);
				    
				    //Select the Role option
				    driver.findElement(By.xpath("//form[@name='payRoll']/div[7]/div/div/a")).click();
				    //Enter the required Role
				    driver.findElement(By.xpath("//form[@name='payRoll']/div[7]/div/div/div/div/input")).sendKeys("All");
				    //Press the Enter key
				    driver.findElement(By.xpath("//form[@name='payRoll']/div[7]/div/div/div/div/input")).sendKeys(Keys.ENTER);
	
				    //Click the Run
				    driver.findElement(By.xpath("//button[@type='submit']")).click();
				    Thread.sleep(1000);
				   
					try
					{
						//Check weather the report is available for the selected time period
						if(driver.findElement(By.xpath("//h3[.='No transaction for selected time period']")).isDisplayed())
						{
							test.log(LogStatus.FAIL, "Employee Labour By Job Code Report is not available for Specific Date");
						}
					}
					catch(Exception fd)
					{
						
						test.log(LogStatus.PASS, "Employee Labour By Job Code Report(In Time and Daily) is available for Specific Date");
						
						System.out.println("******* The Below is Employee Labour By Job Code Report for In Time(Daily) *******");
						
						test.log(LogStatus.INFO, "******* The Below is Employee Labour By Job Code Report for In Time(Daily) *******");
						
						List<WebElement> rows = driver.findElements(By.xpath("//div[@class='col-sm-12 content-block']/div[5]/table/tbody/tr"));
						
						//Get the Hours
						String hours = driver.findElement(By.xpath("//div[@class='col-sm-12 content-block']/div[5]/table/tbody/tr["+rows.size()+"]/td[4]")).getText();
						
						System.out.println("Total Hours is : "+hours);
						
						test.log(LogStatus.INFO, "Total Hours is : "+hours);
						
						//Get the Reg Pay
						String regPay = driver.findElement(By.xpath("//div[@class='col-sm-12 content-block']/div[5]/table/tbody/tr["+rows.size()+"]/td[5]")).getText();
						
						System.out.println("Total Reg Pay is : "+regPay);
						
						test.log(LogStatus.INFO, "Total Reg Pay is : "+regPay);
						
						//Get the OT Hours
						String otHours = driver.findElement(By.xpath("//div[@class='col-sm-12 content-block']/div[5]/table/tbody/tr["+rows.size()+"]/td[6]")).getText();
						
						System.out.println("Total OT Hours is : "+otHours);
						
						test.log(LogStatus.INFO, "Total OT Hours is : "+otHours);
						
						//Get the OT Pay
						String otPay = driver.findElement(By.xpath("//div[@class='col-sm-12 content-block']/div[5]/table/tbody/tr["+rows.size()+"]/td[7]")).getText();
						
						System.out.println("Total OT Pay is : "+otPay);
						
						test.log(LogStatus.INFO, "Total OT Pay is : "+otPay);
						
						//Get the Total Hours
						String totalHours = driver.findElement(By.xpath("//div[@class='col-sm-12 content-block']/div[5]/table/tbody/tr["+rows.size()+"]/td[8]")).getText();
						
						System.out.println("Total Total Hours is : "+totalHours);
						
						test.log(LogStatus.INFO, "Total Total Hours is : "+totalHours);
						
						//Get the Total Pay
						String totalPay = driver.findElement(By.xpath("//div[@class='col-sm-12 content-block']/div[5]/table/tbody/tr["+rows.size()+"]/td[9]")).getText();
						
						System.out.println("Total Pay is : "+totalPay);
						
						test.log(LogStatus.INFO, "Total Pay is : "+totalPay);
						
						//Get the % Payroll
						String percentagePayroll = driver.findElement(By.xpath("//div[@class='col-sm-12 content-block']/div[5]/table/tbody/tr["+rows.size()+"]/td[10]")).getText();
						
						System.out.println("Total % Payroll is : "+percentagePayroll);
						
						test.log(LogStatus.INFO, "Total % Payroll is : "+percentagePayroll);
						
						List<WebElement> numRows = driver.findElements(By.xpath("//div[@class='col-sm-12 content-block']/div[7]/table/tbody/tr"));
					
						//Get the Total Paid
						String totalPaid = driver.findElement(By.xpath("//div[@class='col-sm-12 content-block']/div[7]/table/tbody/tr["+numRows.size()+"]/td[2]")).getText();
						
						System.out.println("Total Paid is : "+totalPaid);
						
						test.log(LogStatus.INFO, "Total Paid is : "+totalPaid);
						
						//Get the % Of Total Payroll
						String percentageOfTotalPayroll = driver.findElement(By.xpath("//div[@class='col-sm-12 content-block']/div[7]/table/tbody/tr["+numRows.size()+"]/td[3]")).getText();
						
						System.out.println("Total % Of Total Payroll is : "+percentageOfTotalPayroll);
						
						test.log(LogStatus.INFO, "Total % Of Total Payroll is : "+percentageOfTotalPayroll);
					
					}			  	    	
					
					Thread.sleep(1000);
					//Select the Employee option
				    driver.findElement(By.xpath("//form[@name='payRoll']/div[1]/div/div/a")).click();
				    //Enter the required Employee
				    driver.findElement(By.xpath("//form[@name='payRoll']/div[1]/div/div/div/div/input")).sendKeys("All");
				    //Press the Enter key
				    driver.findElement(By.xpath("//form[@name='payRoll']/div[1]/div/div/div/div/input")).sendKeys(Keys.ENTER);
				    
				    Thread.sleep(3000);
				    //Select the Process as Daily
					Select process1 = new Select(driver.findElement(By.xpath("//form[@name='payRoll']/div[2]/div/select")));
					process1.selectByVisibleText("Daily");                               
				    
			  	    //Thread.sleep(1000);
			    //Select the Specific date Time Period option
			    driver.findElement(By.xpath("//form[@name='payRoll']/div[3]/div/div/a")).click();
			    //Enter the required Period of time
			    driver.findElement(By.xpath("//form[@name='payRoll']/div[3]/div/div/div/div/input")).sendKeys("Date Range");
			    //Press the Enter key
			    driver.findElement(By.xpath("//form[@name='payRoll']/div[3]/div/div/div/div/input")).sendKeys(Keys.ENTER);
			    
			    Thread.sleep(1000);
			    //Clear the Date field
			    driver.findElement(By.xpath("//form[@name='payRoll']/div[4]/div/div/input[1]")).clear();
			    //Enter the Date
			    driver.findElement(By.xpath("//form[@name='payRoll']/div[4]/div/div/input[1]")).sendKeys(Utility.getReportProperty("Date_Range_From"));
	
			    Thread.sleep(1000);
			    //Clear the Date field
			    driver.findElement(By.xpath("//form[@name='payRoll']/div[4]/div/div/input[2]")).clear();
			    //Enter the Date
			    driver.findElement(By.xpath("//form[@name='payRoll']/div[4]/div/div/input[2]")).sendKeys(Utility.getReportProperty("Date_Range_To"));
					  
					    //Select the Format option
					    driver.findElement(By.xpath("//form[@name='payRoll']/div[5]/div/div/a")).click();
					    //Enter the required Format
					    driver.findElement(By.xpath("//form[@name='payRoll']/div[5]/div/div/div/div/input")).sendKeys("In Hours");
					    //Press the Enter key
					    driver.findElement(By.xpath("//form[@name='payRoll']/div[5]/div/div/div/div/input")).sendKeys(Keys.ENTER);
					  
					    //Select the Active/Inactive option
					    driver.findElement(By.xpath("//form[@name='payRoll']/div[6]/div/div/a")).click();
					    //Enter the required Active/Inactive
					    driver.findElement(By.xpath("//form[@name='payRoll']/div[6]/div/div/div/div/input")).sendKeys("All");
					    //Press the Enter key
					    driver.findElement(By.xpath("//form[@name='payRoll']/div[6]/div/div/div/div/input")).sendKeys(Keys.ENTER);
					    
					    //Select the Role option
					    driver.findElement(By.xpath("//form[@name='payRoll']/div[7]/div/div/a")).click();
					    //Enter the required Role
					    driver.findElement(By.xpath("//form[@name='payRoll']/div[7]/div/div/div/div/input")).sendKeys("All");
					    //Press the Enter key
					    driver.findElement(By.xpath("//form[@name='payRoll']/div[7]/div/div/div/div/input")).sendKeys(Keys.ENTER);
	
					    //Click the Run
					    driver.findElement(By.xpath("//button[@type='submit']")).click();
					    Thread.sleep(1000);
					   
						try
						{
							//Check weather the report is available for the selected time period
							if(driver.findElement(By.xpath("//h3[.='No transaction for selected time period']")).isDisplayed())
							{
								test.log(LogStatus.FAIL, "Employee Labour By Job Code Report is not available for Specific Date");
							}
						}
						catch(Exception fd)
						{
							
							test.log(LogStatus.PASS, "Employee Labour By Job Code Report(In Hours and Daily) is available for Specific Date");
							
							System.out.println("******* The Below is Employee Labour By Job Code Report for In Hours(Daily) *******");
							
							test.log(LogStatus.INFO, "******* The Below is Employee Labour By Job Code Report for In Hours(Daily) *******");
							
							List<WebElement> rows = driver.findElements(By.xpath("//div[@class='col-sm-12 content-block']/div[5]/table/tbody/tr"));
							
							//Get the Hours
							String hours = driver.findElement(By.xpath("//div[@class='col-sm-12 content-block']/div[5]/table/tbody/tr["+rows.size()+"]/td[4]")).getText();
							
							System.out.println("Total Hours is : "+hours);
							
							test.log(LogStatus.INFO, "Total Hours is : "+hours);
							
							//Get the Reg Pay
							String regPay = driver.findElement(By.xpath("//div[@class='col-sm-12 content-block']/div[5]/table/tbody/tr["+rows.size()+"]/td[5]")).getText();
							
							System.out.println("Total Reg Pay is : "+regPay);
							
							test.log(LogStatus.INFO, "Total Reg Pay is : "+regPay);
							
							//Get the OT Hours
							String otHours = driver.findElement(By.xpath("//div[@class='col-sm-12 content-block']/div[5]/table/tbody/tr["+rows.size()+"]/td[6]")).getText();
							
							System.out.println("Total OT Hours is : "+otHours);
							
							test.log(LogStatus.INFO, "Total OT Hours is : "+otHours);
							
							//Get the OT Pay
							String otPay = driver.findElement(By.xpath("//div[@class='col-sm-12 content-block']/div[5]/table/tbody/tr["+rows.size()+"]/td[7]")).getText();
							
							System.out.println("Total OT Pay is : "+otPay);
							
							test.log(LogStatus.INFO, "Total OT Pay is : "+otPay);
							
							//Get the Total Hours
							String totalHours = driver.findElement(By.xpath("//div[@class='col-sm-12 content-block']/div[5]/table/tbody/tr["+rows.size()+"]/td[8]")).getText();
							
							System.out.println("Total Total Hours is : "+totalHours);
							
							test.log(LogStatus.INFO, "Total Total Hours is : "+totalHours);
							
							//Get the Total Pay
							String totalPay = driver.findElement(By.xpath("//div[@class='col-sm-12 content-block']/div[5]/table/tbody/tr["+rows.size()+"]/td[9]")).getText();
							
							System.out.println("Total Pay is : "+totalPay);
							
							test.log(LogStatus.INFO, "Total Pay is : "+totalPay);
							
							//Get the % Payroll
							String percentagePayroll = driver.findElement(By.xpath("//div[@class='col-sm-12 content-block']/div[5]/table/tbody/tr["+rows.size()+"]/td[10]")).getText();
							
							System.out.println("Total % Payroll is : "+percentagePayroll);
							
							test.log(LogStatus.INFO, "Total % Payroll is : "+percentagePayroll);
							
							List<WebElement> numRows = driver.findElements(By.xpath("//div[@class='col-sm-12 content-block']/div[7]/table/tbody/tr"));
						
							//Get the Total Paid
							String totalPaid = driver.findElement(By.xpath("//div[@class='col-sm-12 content-block']/div[7]/table/tbody/tr["+numRows.size()+"]/td[2]")).getText();
							
							System.out.println("Total Paid is : "+totalPaid);
							
							test.log(LogStatus.INFO, "Total Paid is : "+totalPaid);
							
							//Get the % Of Total Payroll
							String percentageOfTotalPayroll = driver.findElement(By.xpath("//div[@class='col-sm-12 content-block']/div[7]/table/tbody/tr["+numRows.size()+"]/td[3]")).getText();
							
							System.out.println("Total % Of Total Payroll is : "+percentageOfTotalPayroll);
							
							test.log(LogStatus.INFO, "Total % Of Total Payroll is : "+percentageOfTotalPayroll);
	
						} 
			}
	
			@Test(priority=62,enabled=false)
			public void Employee_Labour_By_Job_Code_Report_Method_verifyWeeklyReport(WebDriver driver) throws Exception
			{
				Thread.sleep(1000);
				//Select the Employee option
			    driver.findElement(By.xpath("//form[@name='payRoll']/div[1]/div/div/a")).click();
			    //Enter the required Employee
			    driver.findElement(By.xpath("//form[@name='payRoll']/div[1]/div/div/div/div/input")).sendKeys("All");
			    //Press the Enter key
			    driver.findElement(By.xpath("//form[@name='payRoll']/div[1]/div/div/div/div/input")).sendKeys(Keys.ENTER);
			  
			    //Select the Process as Daily
				Select process = new Select(driver.findElement(By.xpath("//form[@name='payRoll']/div[2]/div/select")));
				process.selectByVisibleText("Weekly");
					
		  	    //Thread.sleep(1000);
			    //Select the Specific date Time Period option
			    driver.findElement(By.xpath("//form[@name='payRoll']/div[3]/div/div/a")).click();
			    //Enter the required Period of time
			    driver.findElement(By.xpath("//form[@name='payRoll']/div[3]/div/div/div/div/input")).sendKeys("Date Range");
			    //Press the Enter key
			    driver.findElement(By.xpath("//form[@name='payRoll']/div[3]/div/div/div/div/input")).sendKeys(Keys.ENTER);
			    
			    Thread.sleep(1000);
			    //Clear the Date field
			    driver.findElement(By.xpath("//form[@name='payRoll']/div[4]/div/div/input[1]")).clear();
			    //Enter the Date
			    driver.findElement(By.xpath("//form[@name='payRoll']/div[4]/div/div/input[1]")).sendKeys(Utility.getReportProperty("Date_Range_From"));
	
			    Thread.sleep(1000);
			    //Clear the Date field
			    driver.findElement(By.xpath("//form[@name='payRoll']/div[4]/div/div/input[2]")).clear();
			    //Enter the Date
			    driver.findElement(By.xpath("//form[@name='payRoll']/div[4]/div/div/input[2]")).sendKeys(Utility.getReportProperty("Date_Range_To"));
			    
			    //Select the Format option
			    driver.findElement(By.xpath("//form[@name='payRoll']/div[5]/div/div/a")).click();
			    //Enter the required Format
			    driver.findElement(By.xpath("//form[@name='payRoll']/div[5]/div/div/div/div/input")).sendKeys("In Hours");
			    //Press the Enter key
			    driver.findElement(By.xpath("//form[@name='payRoll']/div[5]/div/div/div/div/input")).sendKeys(Keys.ENTER);
			    
			    //Select the Active/Inactive option
			    driver.findElement(By.xpath("//form[@name='payRoll']/div[6]/div/div/a")).click();
			    //Enter the required Active/Inactive
			    driver.findElement(By.xpath("//form[@name='payRoll']/div[6]/div/div/div/div/input")).sendKeys("All");
			    //Press the Enter key
			    driver.findElement(By.xpath("//form[@name='payRoll']/div[6]/div/div/div/div/input")).sendKeys(Keys.ENTER);
	
			    //Select the Role option
			    driver.findElement(By.xpath("//form[@name='payRoll']/div[7]/div/div/a")).click();
			    //Enter the required Role
			    driver.findElement(By.xpath("//form[@name='payRoll']/div[7]/div/div/div/div/input")).sendKeys("All");
			    //Press the Enter key
			    driver.findElement(By.xpath("//form[@name='payRoll']/div[7]/div/div/div/div/input")).sendKeys(Keys.ENTER);
	
			    Thread.sleep(1000);
			     //Click the Run
			    driver.findElement(By.xpath("//button[@type='submit']")).click();
			    Thread.sleep(5000);
	
				try
				{
					//Check weather the report is available for the selected time period
					if(driver.findElement(By.xpath("//h3[.='No transaction for selected time period']")).isDisplayed())
					{
						test.log(LogStatus.FAIL, "Employee Labour By Job Code Report is not available for Specific Date");
					}
				}
				catch(Exception df)
				{
					
					test.log(LogStatus.PASS, "Employee Labour By Job Code Report(In Hours and Weekly) is available for Specific Date");
					
					System.out.println("******* The Below is Employee Labour By Job Code Report for In Hours(Weekly) *******");
					
					test.log(LogStatus.INFO, "******* The Below is Employee Labour By Job Code Report for In Hours(Weekly) *******");
					
					List<WebElement> rows = driver.findElements(By.xpath("//div[@class='col-sm-12 content-block']/div[5]/table/tbody/tr"));
					
					//Get the Hours
					String hours = driver.findElement(By.xpath("//div[@class='col-sm-12 content-block']/div[5]/table/tbody/tr["+rows.size()+"]/td[4]")).getText();
					
					System.out.println("Total Hours is : "+hours);
					
					test.log(LogStatus.INFO, "Total Hours is : "+hours);
					
					//Get the Reg Pay
					String regPay = driver.findElement(By.xpath("//div[@class='col-sm-12 content-block']/div[5]/table/tbody/tr["+rows.size()+"]/td[5]")).getText();
					
					System.out.println("Total Reg Pay is : "+regPay);
					
					test.log(LogStatus.INFO, "Total Reg Pay is : "+regPay);
					
					//Get the OT Hours
					String otHours = driver.findElement(By.xpath("//div[@class='col-sm-12 content-block']/div[5]/table/tbody/tr["+rows.size()+"]/td[6]")).getText();
					
					System.out.println("Total OT Hours is : "+otHours);
					
					test.log(LogStatus.INFO, "Total OT Hours is : "+otHours);
					
					//Get the OT Pay
					String otPay = driver.findElement(By.xpath("//div[@class='col-sm-12 content-block']/div[5]/table/tbody/tr["+rows.size()+"]/td[7]")).getText();
					
					System.out.println("Total OT Pay is : "+otPay);
					
					test.log(LogStatus.INFO, "Total OT Pay is : "+otPay);
					
					//Get the Total Hours
					String totalHours = driver.findElement(By.xpath("//div[@class='col-sm-12 content-block']/div[5]/table/tbody/tr["+rows.size()+"]/td[8]")).getText();
					
					System.out.println("Total Total Hours is : "+totalHours);
					
					test.log(LogStatus.INFO, "Total Total Hours is : "+totalHours);
					
					//Get the Total Pay
					String totalPay = driver.findElement(By.xpath("//div[@class='col-sm-12 content-block']/div[5]/table/tbody/tr["+rows.size()+"]/td[9]")).getText();
					
					System.out.println("Total Pay is : "+totalPay);
					
					test.log(LogStatus.INFO, "Total Pay is : "+totalPay);
					
					//Get the % Payroll
					String percentagePayroll = driver.findElement(By.xpath("//div[@class='col-sm-12 content-block']/div[5]/table/tbody/tr["+rows.size()+"]/td[10]")).getText();
					
					System.out.println("Total % Payroll is : "+percentagePayroll);
					
					test.log(LogStatus.INFO, "Total % Payroll is : "+percentagePayroll);
					
					List<WebElement> numRows = driver.findElements(By.xpath("//div[@class='col-sm-12 content-block']/div[7]/table/tbody/tr"));
				
					//Get the Total Paid
					String totalPaid = driver.findElement(By.xpath("//div[@class='col-sm-12 content-block']/div[7]/table/tbody/tr["+numRows.size()+"]/td[3]")).getText();
					
					System.out.println("Total Paid is : "+totalPaid);
					
					test.log(LogStatus.INFO, "Total Paid is : "+totalPaid);
					
					//Get the % Of Total Payroll
					String percentageOfTotalPayroll = driver.findElement(By.xpath("//div[@class='col-sm-12 content-block']/div[7]/table/tbody/tr["+numRows.size()+"]/td[4]")).getText();
					
					System.out.println("Total % Of Total Payroll is : "+percentageOfTotalPayroll);
					
					test.log(LogStatus.INFO, "Total % Of Total Payroll is : "+percentageOfTotalPayroll);
	
				} 
				
				Thread.sleep(1000);
				//Select the Employee option
			    driver.findElement(By.xpath("//form[@name='payRoll']/div[1]/div/div/a")).click();
			    //Enter the required Employee
			    driver.findElement(By.xpath("//form[@name='payRoll']/div[1]/div/div/div/div/input")).sendKeys("All");
			    //Press the Enter key
			    driver.findElement(By.xpath("//form[@name='payRoll']/div[1]/div/div/div/div/input")).sendKeys(Keys.ENTER);
			  
			    //Select the Process as Daily
				Select process1 = new Select(driver.findElement(By.xpath("//form[@name='payRoll']/div[2]/div/select")));
				process1.selectByVisibleText("Weekly");
					
			    Thread.sleep(1000);
			    //Select the Specific date Time Period option
			    driver.findElement(By.xpath("//form[@name='payRoll']/div[3]/div/div/a")).click();
			    //Enter the required Period of time
			    driver.findElement(By.xpath("//form[@name='payRoll']/div[3]/div/div/div/div/input")).sendKeys("Date Range");
			    //Press the Enter key
			    driver.findElement(By.xpath("//form[@name='payRoll']/div[3]/div/div/div/div/input")).sendKeys(Keys.ENTER);
			    
			    Thread.sleep(1000);
			    //Clear the Date field
			    driver.findElement(By.xpath("//form[@name='payRoll']/div[4]/div/div/input[1]")).clear();
			    //Enter the Date
			    driver.findElement(By.xpath("//form[@name='payRoll']/div[4]/div/div/input[1]")).sendKeys(Utility.getReportProperty("Date_Range_From"));
	
			    Thread.sleep(1000);
			    //Clear the Date field
			    driver.findElement(By.xpath("//form[@name='payRoll']/div[4]/div/div/input[2]")).clear();
			    //Enter the Date
			    driver.findElement(By.xpath("//form[@name='payRoll']/div[4]/div/div/input[2]")).sendKeys(Utility.getReportProperty("Date_Range_To"));
			    
			    //Select the Format option
			    driver.findElement(By.xpath("//form[@name='payRoll']/div[5]/div/div/a")).click();
			    //Enter the required Format
			    driver.findElement(By.xpath("//form[@name='payRoll']/div[5]/div/div/div/div/input")).sendKeys("In Time");
			    //Press the Enter key
			    driver.findElement(By.xpath("//form[@name='payRoll']/div[5]/div/div/div/div/input")).sendKeys(Keys.ENTER);
			    
			    //Select the Active/Inactive option
			    driver.findElement(By.xpath("//form[@name='payRoll']/div[6]/div/div/a")).click();
			    //Enter the required Active/Inactive
			    driver.findElement(By.xpath("//form[@name='payRoll']/div[6]/div/div/div/div/input")).sendKeys("All");
			    //Press the Enter key
			    driver.findElement(By.xpath("//form[@name='payRoll']/div[6]/div/div/div/div/input")).sendKeys(Keys.ENTER);
	
			    //Select the Role option
			    driver.findElement(By.xpath("//form[@name='payRoll']/div[7]/div/div/a")).click();
			    //Enter the required Role
			    driver.findElement(By.xpath("//form[@name='payRoll']/div[7]/div/div/div/div/input")).sendKeys("All");
			    //Press the Enter key
			    driver.findElement(By.xpath("//form[@name='payRoll']/div[7]/div/div/div/div/input")).sendKeys(Keys.ENTER);
	
			    Thread.sleep(1000);
			     //Click the Run
			    driver.findElement(By.xpath("//button[@type='submit']")).click();
			    Thread.sleep(5000);
			    
			    
				try
				{
					//Check weather the report is available for the selected time period
					if(driver.findElement(By.xpath("//h3[.='No transaction for selected time period']")).isDisplayed())
					{
						test.log(LogStatus.FAIL, "Employee Labour By Job Code Report is not available for Specific Date");
					}
				}
				catch(Exception fg)
				{
					
					test.log(LogStatus.PASS, "Employee Labour By Job Code Report(In Time and Weekly) is available for Specific Date");
					
					System.out.println("******* The Below is Employee Labour By Job Code Report for In Time(Weekly) *******");
					
					test.log(LogStatus.INFO, "******* The Below is Employee Labour By Job Code Report for In Time(Weekly) *******");
					
					List<WebElement> rows = driver.findElements(By.xpath("//div[@class='col-sm-12 content-block']/div[5]/table/tbody/tr"));
					
					//Get the Hours
					String hours = driver.findElement(By.xpath("//div[@class='col-sm-12 content-block']/div[5]/table/tbody/tr["+rows.size()+"]/td[4]")).getText();
					
					System.out.println("Total Hours is : "+hours);
					
					test.log(LogStatus.INFO, "Total Hours is : "+hours);
					
					//Get the Reg Pay
					String regPay = driver.findElement(By.xpath("//div[@class='col-sm-12 content-block']/div[5]/table/tbody/tr["+rows.size()+"]/td[5]")).getText();
					
					System.out.println("Total Reg Pay is : "+regPay);
					
					test.log(LogStatus.INFO, "Total Reg Pay is : "+regPay);
					
					//Get the OT Hours
					String otHours = driver.findElement(By.xpath("//div[@class='col-sm-12 content-block']/div[5]/table/tbody/tr["+rows.size()+"]/td[6]")).getText();
					
					System.out.println("Total OT Hours is : "+otHours);
					
					test.log(LogStatus.INFO, "Total OT Hours is : "+otHours);
					
					//Get the OT Pay
					String otPay = driver.findElement(By.xpath("//div[@class='col-sm-12 content-block']/div[5]/table/tbody/tr["+rows.size()+"]/td[7]")).getText();
					
					System.out.println("Total OT Pay is : "+otPay);
					
					test.log(LogStatus.INFO, "Total OT Pay is : "+otPay);
					
					//Get the Total Hours
					String totalHours = driver.findElement(By.xpath("//div[@class='col-sm-12 content-block']/div[5]/table/tbody/tr["+rows.size()+"]/td[8]")).getText();
					
					System.out.println("Total Total Hours is : "+totalHours);
					
					test.log(LogStatus.INFO, "Total Total Hours is : "+totalHours);
					
					//Get the Total Pay
					String totalPay = driver.findElement(By.xpath("//div[@class='col-sm-12 content-block']/div[5]/table/tbody/tr["+rows.size()+"]/td[9]")).getText();
					
					System.out.println("Total Pay is : "+totalPay);
					
					test.log(LogStatus.INFO, "Total Pay is : "+totalPay);
					
					//Get the % Payroll
					String percentagePayroll = driver.findElement(By.xpath("//div[@class='col-sm-12 content-block']/div[5]/table/tbody/tr["+rows.size()+"]/td[10]")).getText();
					
					System.out.println("Total % Payroll is : "+percentagePayroll);
					
					test.log(LogStatus.INFO, "Total % Payroll is : "+percentagePayroll);
					
					List<WebElement> numRows = driver.findElements(By.xpath("//div[@class='col-sm-12 content-block']/div[7]/table/tbody/tr"));
				
					//Get the Total Paid
					String totalPaid = driver.findElement(By.xpath("//div[@class='col-sm-12 content-block']/div[7]/table/tbody/tr["+numRows.size()+"]/td[3]")).getText();
					
					System.out.println("Total Paid is : "+totalPaid);
					
					test.log(LogStatus.INFO, "Total Paid is : "+totalPaid);
					
					//Get the % Of Total Payroll
					String percentageOfTotalPayroll = driver.findElement(By.xpath("//div[@class='col-sm-12 content-block']/div[7]/table/tbody/tr["+numRows.size()+"]/td[4]")).getText();
					
					System.out.println("Total % Of Total Payroll is : "+percentageOfTotalPayroll);
					
					test.log(LogStatus.INFO, "Total % Of Total Payroll is : "+percentageOfTotalPayroll);
				}
			}

}

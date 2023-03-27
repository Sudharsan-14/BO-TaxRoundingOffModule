package epicList_Chrome_Xpath;


import java.util.ArrayList;
import java.util.Collections;
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


public class Verify_Employee_Role_Based_Payroll_Reports {
	public WebDriver driver;

	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Verify_Employee_Role_Based_Payroll_Reports");

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
				Employee_Role_Based_Payroll_Report_Method_Page_Open(driver);
				Employee_Role_Based_Payroll_Report_Method_verifyDailyReport(driver);
				Employee_Role_Based_Payroll_Report_Method_verifyWeeklyReport(driver);
				Thread.sleep(1500);
			}
			
			    @Test(priority=50,enabled=false)
				public void Employee_Role_Based_Payroll_Report_Method_Page_Open(WebDriver driver) throws Exception
			{
		/*		//Click the Reports option
				driver.findElement(By.xpath("//span[.='Reports']")).click();
				// Create instance of Java script executor
				JavascriptExecutor je = (JavascriptExecutor) driver;
				//Identify the WebElement which will appear after scrolling down
				WebElement element = driver.findElement(By.xpath("//span[.='Employee']"));
				//Scroll the page till the Transaction option present
				je.executeScript("arguments[0].scrollIntoView(true);",element); 
			    //Click the Employee Option		
				driver.findElement(By.xpath("//span[.='Employee']")).click();
				Thread.sleep(5000);
				//Check Attendance Report page opened or not
				if(driver.findElement(By.xpath("//a[.='Attendance']")).getText().equalsIgnoreCase("Attendance"))
				{
					test.log(LogStatus.PASS,"Attendance Report page loaded Successfully");
				}
				else
				{
					test.log(LogStatus.FAIL,"Attendance Report page loaded Failed");
				}*/
				Thread.sleep(3000);
				//Click the Payroll option
				//driver.findElement(By.xpath("//span[text()[normalize-space()='Role Based Payroll']]")).click();
				//Enter the Users URL
				driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"roleBasedPayroll");
		
				
				//Check Payroll Report page opened or not
				if(driver.findElement(By.xpath("//a[.='Role Based Payroll']")).getText().equalsIgnoreCase("Role Based Payroll"))
				{
					test.log(LogStatus.PASS,"Role Based Payroll Report page loaded Successfully");
				}
				else
				{
					test.log(LogStatus.FAIL,"Role Based Payroll Report page loaded Failed");
				}
				
			}
		
				@Test(priority=51,enabled=false)
				public void Employee_Role_Based_Payroll_Report_Method_verifyDailyReport(WebDriver driver) throws Exception
				{
					//Create the web element
				    WebElement html = driver.findElement(By.tagName("html"));
				  	//Zoom out the window
				  	html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
				  	html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
				  	html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
				  	html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
				  	html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
				  	html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
				  	html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
					
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
				    driver.findElement(By.xpath("//form[@name='payRoll']/div[4]/div/div/input[1]")).sendKeys(Utility.getReportProperty("Payroll_Date_Range_From"));
		
				    Thread.sleep(1000);
				    //Clear the Date field
				    driver.findElement(By.xpath("//form[@name='payRoll']/div[4]/div/div/input[2]")).clear();
				    //Enter the Date
				    driver.findElement(By.xpath("//form[@name='payRoll']/div[4]/div/div/input[2]")).sendKeys(Utility.getReportProperty("Payroll_Date_Range_To"));
					  
					    //Select the Format option
					    driver.findElement(By.xpath("//form[@name='payRoll']/div[5]/div/div/a")).click();
					    //Enter the required Format
					    driver.findElement(By.xpath("//form[@name='payRoll']/div[5]/div/div/div/div/input")).sendKeys("In Time");
					    //Press the Enter key
					    driver.findElement(By.xpath("//form[@name='payRoll']/div[5]/div/div/div/div/input")).sendKeys(Keys.ENTER);
					  
					    //Select the Active/Inactive option
					    driver.findElement(By.xpath("//form[@name='payRoll']/div[6]/div/div/a")).click();
					    //Enter the required Format
					    driver.findElement(By.xpath("//form[@name='payRoll']/div[6]/div/div/div/div/input")).sendKeys("All");
					    //Press the Enter key
					    driver.findElement(By.xpath("//form[@name='payRoll']/div[6]/div/div/div/div/input")).sendKeys(Keys.ENTER);
		
					    Thread.sleep(1000);
					    //Select the Process as Daily
						Select sort1 = new Select(driver.findElement(By.xpath("//form[@name='payRoll']/div[7]/div/select")));
						sort1.selectByVisibleText("A-Z FirstName");
						
					    //Select the Role option
					    driver.findElement(By.xpath("//form[@name='payRoll']/div[8]/div/div/a")).click();
					    //Enter the required Role
					    driver.findElement(By.xpath("//form[@name='payRoll']/div[8]/div/div/div/div/input")).sendKeys("All");
					    //Press the Enter key
					    driver.findElement(By.xpath("//form[@name='payRoll']/div[8]/div/div/div/div/input")).sendKeys(Keys.ENTER);
		
					    //Click the Run
					    driver.findElement(By.xpath("//button[@type='submit']")).click();
					    Thread.sleep(1000);
					   
						try
						{
							//Check weather the report is available for the selected time period
							if(driver.findElement(By.xpath("//h3[.='No transaction for selected time period']")).isDisplayed())
							{
								test.log(LogStatus.FAIL, "Employee Payroll Report is not available for Specific Date");
							}
						}
						catch(Exception fd)
						{
							
							test.log(LogStatus.PASS, "Employee Payroll Report(In Time and Daily) is available for Specific Date");
							
							System.out.println("******* The Below is Employee Payroll Report for In Time(Daily) *******");
							
							test.log(LogStatus.INFO, "******* The Below is Employee Payroll Report for In Time(Daily) *******");
							
							List<WebElement> rows = driver.findElements(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr"));
							
							//Get Per Rate value
							String perRate = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[4]")).getText();
							
							System.out.println("Total Per Rate is : "+perRate);
							
							test.log(LogStatus.INFO, "Total Per Rate is : "+perRate);
							
							//Get Reg Hours
							String regHours = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[5]")).getText();
							
							System.out.println("Total Reg Hours is : "+regHours);
							
							test.log(LogStatus.INFO, "Total Reg Hours is : "+regHours);
							
							//Get Reg Pay
							String regPay = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[6]")).getText();
							
							System.out.println("Total Reg Pay is : "+regPay);
							
							test.log(LogStatus.INFO, "Total Reg Pay is : "+regPay);
							
							//Get OT Hours
							String otHours = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[7]")).getText();
							
							System.out.println("Total OT Hours is : "+otHours);
							
							test.log(LogStatus.INFO, "Total OT Hours is : "+otHours);
							
							//Get OT Pay
							String otPay = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[8]")).getText();
							
							System.out.println("Total OT Pay is : "+otPay);
							
							test.log(LogStatus.INFO, "Total OT Pay is : "+otPay);
							
							//Get Total Hours
							String hours = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[9]")).getText();
							
							System.out.println("Total Hours is : "+hours);
							
							test.log(LogStatus.INFO, "Total Hours is : "+hours);
							
							//Get TTL Pay
							String ttlPay = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[10]")).getText();
							
							System.out.println("Total TTL Pay is : "+ttlPay);
							
							test.log(LogStatus.INFO, "Total TTL Pay is : "+ttlPay);
							
							//Get CC Tip
							String ccTip = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[11]")).getText();
							
							System.out.println("Total CC Tip is : "+ccTip);
							
							test.log(LogStatus.INFO, "Total CC Tip is : "+ccTip);
							
							//Get Gratuity
							String gratuity = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[12]")).getText();
							
							System.out.println("Total Gratuity is : "+gratuity);
							
							test.log(LogStatus.INFO, "Total Gratuity is : "+gratuity);
							
							//Get Other Tips
							String otherTips = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[13]")).getText();
							
							System.out.println("Total Other Tips is : "+otherTips);
							
							test.log(LogStatus.INFO, "Total Other Tips is : "+otherTips);
							
							//Get Declared Cash Tip
							String declaredCashTip = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[14]")).getText();
							
							System.out.println("Total Declared Cash Tip is : "+declaredCashTip);
							
							test.log(LogStatus.INFO, "Total Declared Cash Tip is : "+declaredCashTip);
							
							//Get Total Tips
							String tip = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[15]")).getText();
							
							System.out.println("Total Tips is : "+tip);
							
							test.log(LogStatus.INFO, "Total Tips is : "+tip);
							
							//Get Net Sales
							String netSales = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[16]")).getText();
							
							System.out.println("Total Net Sales is : "+netSales);
							
							test.log(LogStatus.INFO, "Total Net Sales is : "+netSales);
							
							//Get Expected Tip
							String expectedTip = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[17]")).getText();
							
							System.out.println("Total Expected Tip is : "+expectedTip);
							
							test.log(LogStatus.INFO, "Total Expected Tip is : "+expectedTip);
							
							
							ArrayList<String> obtainedList = new ArrayList<>(); 
							List<WebElement> elementList= driver.findElements(By.xpath("//td[contains(.,'*')]"));
							for(int i = 1; i <= elementList.size(); i++)
							{
								String namess = driver.findElement(By.xpath("//td[contains(.,'*')]")).getText();
								
								String[] name = namess.split(" ");
								//System.out.println("TEST A-Z First : "+name[0]);
								obtainedList.add(name[0]);
							}
							ArrayList<String> sortedList = new ArrayList<>();
							for(String s:obtainedList)
							{
								sortedList.add(s);
							}
							Collections.sort(sortedList);
							
							for(int i = 0; i < elementList.size(); i++)
							{								
								if(sortedList.equals(obtainedList))
								{
									
								}else
								{
									test.log(LogStatus.FAIL, "Name sort is not working  for A-Z First Name");
								}
							}
						}
				  	    	
						
						Thread.sleep(1000);
						//Select the Employee option
					    driver.findElement(By.xpath("//form[@name='payRoll']/div[1]/div/div/a")).click();
					    //Enter the required Employee
					    driver.findElement(By.xpath("//form[@name='payRoll']/div[1]/div/div/div/div/input")).sendKeys("All");
					    //Press the Enter key
					    driver.findElement(By.xpath("//form[@name='payRoll']/div[1]/div/div/div/div/input")).sendKeys(Keys.ENTER);
					    
					    Thread.sleep(1000);
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
					    driver.findElement(By.xpath("//form[@name='payRoll']/div[4]/div/div/input[1]")).sendKeys(Utility.getReportProperty("Payroll_Date_Range_From"));
		
					    Thread.sleep(1000);
					    //Clear the Date field
					    driver.findElement(By.xpath("//form[@name='payRoll']/div[4]/div/div/input[2]")).clear();
					    //Enter the Date
					    driver.findElement(By.xpath("//form[@name='payRoll']/div[4]/div/div/input[2]")).sendKeys(Utility.getReportProperty("Payroll_Date_Range_To"));
						  
						    //Select the Format option
						    driver.findElement(By.xpath("//form[@name='payRoll']/div[5]/div/div/a")).click();
						    //Enter the required Format
						    driver.findElement(By.xpath("//form[@name='payRoll']/div[5]/div/div/div/div/input")).sendKeys("In Hours");
						    //Press the Enter key
						    driver.findElement(By.xpath("//form[@name='payRoll']/div[5]/div/div/div/div/input")).sendKeys(Keys.ENTER);
						  
						    //Select the Active/Inactive option
						    driver.findElement(By.xpath("//form[@name='payRoll']/div[6]/div/div/a")).click();
						    //Enter the required Format
						    driver.findElement(By.xpath("//form[@name='payRoll']/div[6]/div/div/div/div/input")).sendKeys("All");
						    //Press the Enter key
						    driver.findElement(By.xpath("//form[@name='payRoll']/div[6]/div/div/div/div/input")).sendKeys(Keys.ENTER);
		
						    Thread.sleep(1000);
						    //Select the Process as Daily
							Select sort = new Select(driver.findElement(By.xpath("//form[@name='payRoll']/div[7]/div/select")));
							sort.selectByVisibleText("Z-A FirstName");   
						    
						    //Select the Role option
						    driver.findElement(By.xpath("//form[@name='payRoll']/div[8]/div/div/a")).click();
						    //Enter the required Role
						    driver.findElement(By.xpath("//form[@name='payRoll']/div[8]/div/div/div/div/input")).sendKeys("All");
						    //Press the Enter key
						    driver.findElement(By.xpath("//form[@name='payRoll']/div[8]/div/div/div/div/input")).sendKeys(Keys.ENTER);
						    
						    //Click the Run
						    driver.findElement(By.xpath("//button[@type='submit']")).click();
						    Thread.sleep(1000);
						   
							try
							{
								//Check weather the report is available for the selected time period
								if(driver.findElement(By.xpath("//h3[.='No transaction for selected time period']")).isDisplayed())
								{
									test.log(LogStatus.FAIL, "Employee Payroll Report is not available for Specific Date");
								}
							}
							catch(Exception fd)
							{
								
								test.log(LogStatus.PASS, "Employee Payroll Report(In Hours and Daily) is available for Specific Date");
								
								System.out.println("******* The Below is Employee Payroll Report for In Hours(Daily) *******");
								
								test.log(LogStatus.INFO, "******* The Below is Employee Payroll Report for In Hours(Daily) *******");
								
								List<WebElement> rows = driver.findElements(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr"));
								
								//Get Per Rate value
								String perRate = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[4]")).getText();
								
								System.out.println("Total Per Rate is : "+perRate);
								
								test.log(LogStatus.INFO, "Total Per Rate is : "+perRate);
								
								//Get Reg Hours
								String regHours = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[5]")).getText();
								
								System.out.println("Total Reg Hours is : "+regHours);
								
								test.log(LogStatus.INFO, "Total Reg Hours is : "+regHours);
								
								//Get Reg Pay
								String regPay = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[6]")).getText();
								
								System.out.println("Total Reg Pay is : "+regPay);
								
								test.log(LogStatus.INFO, "Total Reg Pay is : "+regPay);
								
								//Get OT Hours
								String otHours = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[7]")).getText();
								
								System.out.println("Total OT Hours is : "+otHours);
								
								test.log(LogStatus.INFO, "Total OT Hours is : "+otHours);
								
								//Get OT Pay
								String otPay = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[8]")).getText();
								
								System.out.println("Total OT Pay is : "+otPay);
								
								test.log(LogStatus.INFO, "Total OT Pay is : "+otPay);
								
								//Get Total Hours
								String hours = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[9]")).getText();
								
								System.out.println("Total Hours is : "+hours);
								
								test.log(LogStatus.INFO, "Total Hours is : "+hours);
								
								//Get TTL Pay
								String ttlPay = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[10]")).getText();
								
								System.out.println("Total TTL Pay is : "+ttlPay);
								
								test.log(LogStatus.INFO, "Total TTL Pay is : "+ttlPay);
								
								//Get CC Tip
								String ccTip = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[11]")).getText();
								
								System.out.println("Total CC Tip is : "+ccTip);
								
								test.log(LogStatus.INFO, "Total CC Tip is : "+ccTip);
								
								//Get Gratuity
								String gratuity = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[12]")).getText();
								
								System.out.println("Total Gratuity is : "+gratuity);
								
								test.log(LogStatus.INFO, "Total Gratuity is : "+gratuity);
								
								//Get Other Tips
								String otherTips = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[13]")).getText();
								
								System.out.println("Total Other Tips is : "+otherTips);
								
								test.log(LogStatus.INFO, "Total Other Tips is : "+otherTips);
								
								//Get Declared Cash Tip
								String declaredCashTip = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[14]")).getText();
								
								System.out.println("Total Declared Cash Tip is : "+declaredCashTip);
								
								test.log(LogStatus.INFO, "Total Declared Cash Tip is : "+declaredCashTip);
								
								//Get Total Tips
								String tip = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[15]")).getText();
								
								System.out.println("Total Tips is : "+tip);
								
								test.log(LogStatus.INFO, "Total Tips is : "+tip);
								
								//Get Net Sales
								String netSales = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[16]")).getText();
								
								System.out.println("Total Net Sales is : "+netSales);
								
								test.log(LogStatus.INFO, "Total Net Sales is : "+netSales);
								
								JavascriptExecutor je = (JavascriptExecutor) driver;
								//Identify the WebElement which will appear after scrolling down
								WebElement element = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[17]"));
								//Scroll the page till the Reason option present
								je.executeScript("arguments[0].scrollIntoView(true);",element); 
								
								//Get Expected Tip
								String expectedTip = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[17]")).getText();
								
								System.out.println("Total Expected Tip is : "+expectedTip);
								
								test.log(LogStatus.INFO, "Total Expected Tip is : "+expectedTip);
								
								ArrayList<String> obtainedList = new ArrayList<>(); 
								List<WebElement> elementList= driver.findElements(By.xpath("//td[contains(.,'*')]"));
								for(int i = 1; i <= elementList.size(); i++)
								{
									String namess = driver.findElement(By.xpath("//td[contains(.,'*')]")).getText();
									
									String[] name = namess.split(" ");
									//System.out.println("TEST Z-A FIRST : "+name[0]);
									obtainedList.add(name[0]);
								}
								ArrayList<String> sortedList = new ArrayList<>();
								for(String s:obtainedList)
								{
									sortedList.add(s);
								}
								Collections.reverse(sortedList);
								
								for(int i = 0; i < elementList.size(); i++)
								{								
									if(sortedList.equals(obtainedList))
									{
										
									}else
									{
										test.log(LogStatus.FAIL, "Name sort is not working  for Z-A First Name");
									}
								}
								
		/*							ArrayList<String> obtainedList = new ArrayList<>(); 
								List<WebElement> elementList= driver.findElements(By.xpath("//td[contains(.,'*')]"));
								for(WebElement we:elementList){
								   obtainedList.add(we.getText());
								}
								ArrayList<String> sortedList = new ArrayList<>();   
								for(String s:obtainedList){
								sortedList.add(s);
								}
								Collections.sort(sortedList);
								Assert.assertTrue(sortedList.equals(obtainedList));*/
							}	
			
				}
				
				@Test(priority=52,enabled=false)
				public void Employee_Role_Based_Payroll_Report_Method_verifyWeeklyReport(WebDriver driver) throws Exception
				{
				
					Thread.sleep(5000);
					//Top of the page
					driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
					
					Thread.sleep(5000);
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
					
					
				    Thread.sleep(3000);
				    //Clear the Date field
				    driver.findElement(By.xpath("//form[@name='payRoll']/div[4]/div/div/input[1]")).clear();
				    //Enter the Date
				    driver.findElement(By.xpath("//form[@name='payRoll']/div[4]/div/div/input[1]")).sendKeys(Utility.getReportProperty("Payroll_Date_Range_From"));
		
				    Thread.sleep(1000);
				    //Clear the Date field
				    driver.findElement(By.xpath("//form[@name='payRoll']/div[4]/div/div/input[2]")).clear();
				    //Enter the Date
				    driver.findElement(By.xpath("//form[@name='payRoll']/div[4]/div/div/input[2]")).sendKeys(Utility.getReportProperty("Payroll_Date_Range_To"));
				    Thread.sleep(1000);
				    
				    //Select the Format option
				    driver.findElement(By.xpath("//form[@name='payRoll']/div[5]/div/div/a")).click();
				    //Enter the required Format
				    driver.findElement(By.xpath("//form[@name='payRoll']/div[5]/div/div/div/div/input")).sendKeys("In Hours");
				    //Press the Enter key
				    driver.findElement(By.xpath("//form[@name='payRoll']/div[5]/div/div/div/div/input")).sendKeys(Keys.ENTER);
				    
				    //Select the Active/Inactive option
				    driver.findElement(By.xpath("//form[@name='payRoll']/div[6]/div/div/a")).click();
				    //Enter the required Format
				    driver.findElement(By.xpath("//form[@name='payRoll']/div[6]/div/div/div/div/input")).sendKeys("All");
				    //Press the Enter key
				    driver.findElement(By.xpath("//form[@name='payRoll']/div[6]/div/div/div/div/input")).sendKeys(Keys.ENTER);
		
				    Thread.sleep(1000);
				    //Select the Process as Daily
					Select sort1 = new Select(driver.findElement(By.xpath("//form[@name='payRoll']/div[7]/div/select")));
					sort1.selectByVisibleText("A-Z LastName");
					
				    //Select the Role option
				    driver.findElement(By.xpath("//form[@name='payRoll']/div[8]/div/div/a")).click();
				    //Enter the required Role
				    driver.findElement(By.xpath("//form[@name='payRoll']/div[8]/div/div/div/div/input")).sendKeys("All");
				    //Press the Enter key
				    driver.findElement(By.xpath("//form[@name='payRoll']/div[8]/div/div/div/div/input")).sendKeys(Keys.ENTER);
				    
				    Thread.sleep(2000);
				    //Enable or Disable the show summary only options
				   driver.findElement(By.xpath("//form[@name='payRoll']/div[9]/div/div/input")).click();
				    
		
				    Thread.sleep(1000);
				     //Click the Run
				    driver.findElement(By.xpath("//button[@type='submit']")).click();
				    Thread.sleep(1000);
				
					try
					{
						//Check weather the report is available for the selected time period
						if(driver.findElement(By.xpath("//h3[.='No transaction for selected time period']")).isDisplayed())
						{
							test.log(LogStatus.FAIL, "Employee Payroll Report is not available for Specific Date");
						}
					}
					catch(Exception gf)
					{
						
						test.log(LogStatus.PASS, "Employee Payroll Report(In Hours and Weekly) is available for Specific Date");
						
						System.out.println("******* The Below is Employee Payroll Report for In Hours(Weekly) *******");
						
						test.log(LogStatus.INFO, "******* The Below is Employee Payroll Report for In Hours(Weekly) *******");
						
						List<WebElement> rows = driver.findElements(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr"));
						//Get Summary
						
						String Summary = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[3]")).getText();
						System.out.println("Total Summary is : "+Summary);
											
						test.log(LogStatus.INFO, "Total Summary is : "+Summary);
						//Get Per Rate value
						String perRate = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[4]")).getText();
						
						System.out.println("Total Per Rate is : "+perRate);
						
						test.log(LogStatus.INFO, "Total Per Rate is : "+perRate);
						
						//Get Reg Hours
						String regHours = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[5]")).getText();
						
						System.out.println("Total Reg Hours is : "+regHours);
						
						test.log(LogStatus.INFO, "Total Reg Hours is : "+regHours);
						
						//Get Reg Pay
						String regPay = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[6]")).getText();
						
						System.out.println("Total Reg Pay is : "+regPay);
						
						test.log(LogStatus.INFO, "Total Reg Pay is : "+regPay);
						
						//Get OT Hours
						String otHours = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[7]")).getText();
						
						System.out.println("Total OT Hours is : "+otHours);
						
						test.log(LogStatus.INFO, "Total OT Hours is : "+otHours);
						
						//Get OT Pay
						String otPay = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[8]")).getText();
						
						System.out.println("Total OT Pay is : "+otPay);
						
						test.log(LogStatus.INFO, "Total OT Pay is : "+otPay);
						
						//Get Total Hours
						String hours = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[9]")).getText();
						
						System.out.println("Total Hours is : "+hours);
						
						test.log(LogStatus.INFO, "Total Hours is : "+hours);
						
						//Get TTL Pay
						String ttlPay = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[10]")).getText();
						
						System.out.println("Total TTL Pay is : "+ttlPay);
						
						test.log(LogStatus.INFO, "Total TTL Pay is : "+ttlPay);
						
						//Get CC Tip
						String ccTip = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[11]")).getText();
						
						System.out.println("Total CC Tip is : "+ccTip);
						
						test.log(LogStatus.INFO, "Total CC Tip is : "+ccTip);
						
						//Get Gratuity
						String gratuity = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[12]")).getText();
						
						System.out.println("Total Gratuity is : "+gratuity);
						
						test.log(LogStatus.INFO, "Total Gratuity is : "+gratuity);
						
						//Get Other Tips
						String otherTips = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[13]")).getText();
						
						System.out.println("Total Other Tips is : "+otherTips);
						
						test.log(LogStatus.INFO, "Total Other Tips is : "+otherTips);
						
						//Get Declared Cash Tip
						String declaredCashTip = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[14]")).getText();
						
						System.out.println("Total Declared Cash Tip is : "+declaredCashTip);
						
						test.log(LogStatus.INFO, "Total Declared Cash Tip is : "+declaredCashTip);
						
						//Get Total Tips
						String tip = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[15]")).getText();
						
						System.out.println("Total Tips is : "+tip);
						
						test.log(LogStatus.INFO, "Total Tips is : "+tip);
						
						//Get Net Sales
						String netSales = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[16]")).getText();
						
						System.out.println("Total Net Sales is : "+netSales);
						
						test.log(LogStatus.INFO, "Total Net Sales is : "+netSales);
						
						JavascriptExecutor je = (JavascriptExecutor) driver;
						//Identify the WebElement which will appear after scrolling down
						WebElement element = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[17]"));
						//Scroll the page till the Reason option present
						je.executeScript("arguments[0].scrollIntoView(true);",element); 
						
						//Get Expected Tip
						String expectedTip = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[17]")).getText();
						
						System.out.println("Total Expected Tip is : "+expectedTip);
						
						test.log(LogStatus.INFO, "Total Expected Tip is : "+expectedTip);
						
						
						
						ArrayList<String> obtainedList = new ArrayList<>(); 
						List<WebElement> elementList= driver.findElements(By.xpath("//td[contains(.,'*')]"));
						for(int i = 1; i <= elementList.size(); i++)
						{
							String namess = driver.findElement(By.xpath("//td[contains(.,'*')]")).getText();
							
							String[] name = namess.split(" ");
							//System.out.println("TEST A-Z LAST : "+name[1]);
							obtainedList.add(name[1]);
						}
						ArrayList<String> sortedList = new ArrayList<>();
						for(String s:obtainedList)
						{
							sortedList.add(s);
						}
						Collections.sort(sortedList);
						
						for(int i = 0; i < elementList.size(); i++)
						{								
							if(sortedList.equals(obtainedList))
							{
								
							}else
							{
								test.log(LogStatus.FAIL, "Name sort is not working for A-Z Last Name");
							}
						}
					}
									
					Thread.sleep(5000);
					//Top of the page
					driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
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
						
				    for(int i = 1; i <= 4; i++)
				    {
						Thread.sleep(2000);
						driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
						Thread.sleep(3000);
				    }
				    
				    Thread.sleep(1000);
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
				    driver.findElement(By.xpath("//form[@name='payRoll']/div[4]/div/div/input[1]")).sendKeys(Utility.getReportProperty("Payroll_Date_Range_From"));
		
				    Thread.sleep(1000);
				    //Clear the Date field
				    driver.findElement(By.xpath("//form[@name='payRoll']/div[4]/div/div/input[2]")).clear();
				    //Enter the Date
				    driver.findElement(By.xpath("//form[@name='payRoll']/div[4]/div/div/input[2]")).sendKeys(Utility.getReportProperty("Payroll_Date_Range_To"));
				    Thread.sleep(1000);
				    
				    //Select the Format option
				    driver.findElement(By.xpath("//form[@name='payRoll']/div[5]/div/div/a")).click();
				    //Enter the required Format
				    driver.findElement(By.xpath("//form[@name='payRoll']/div[5]/div/div/div/div/input")).sendKeys("In Time");
				    //Press the Enter key
				    driver.findElement(By.xpath("//form[@name='payRoll']/div[5]/div/div/div/div/input")).sendKeys(Keys.ENTER);
				    
				    //Select the Active/Inactive option
				    driver.findElement(By.xpath("//form[@name='payRoll']/div[6]/div/div/a")).click();
				    //Enter the required Format
				    driver.findElement(By.xpath("//form[@name='payRoll']/div[6]/div/div/div/div/input")).sendKeys("All");
				    //Press the Enter key
				    driver.findElement(By.xpath("//form[@name='payRoll']/div[6]/div/div/div/div/input")).sendKeys(Keys.ENTER);
		
				    Thread.sleep(1000);
				    //Select the Process as Daily
					Select sort = new Select(driver.findElement(By.xpath("//form[@name='payRoll']/div[7]/div/select")));
					sort.selectByVisibleText("Z-A LastName");
					
				    //Select the Role option
				    driver.findElement(By.xpath("//form[@name='payRoll']/div[8]/div/div/a")).click();
				    //Enter the required Role
				    driver.findElement(By.xpath("//form[@name='payRoll']/div[8]/div/div/div/div/input")).sendKeys("All");
				    //Press the Enter key
				    driver.findElement(By.xpath("//form[@name='payRoll']/div[8]/div/div/div/div/input")).sendKeys(Keys.ENTER);
				    
				    Thread.sleep(1000);
				     //Click the Run
				    driver.findElement(By.xpath("//button[@type='submit']")).click();
				    Thread.sleep(1000);
				    
				    
					try
					{
						//Check weather the report is available for the selected time period
						if(driver.findElement(By.xpath("//h3[.='No transaction for selected time period']")).isDisplayed())
						{
							test.log(LogStatus.FAIL, "Employee Payroll Report is not available for Specific Date");
						}
					}
					catch(Exception fd)
					{
						
						test.log(LogStatus.PASS, "Employee Payroll Report(In Time and Weekly) is available for Specific Date");
						
						System.out.println("******* The Below is Employee Payroll Report for In Time(Weekly) *******");
						
						test.log(LogStatus.INFO, "******* The Below is Employee Payroll Report for In Time(Weekly) *******");
						
						List<WebElement> rows = driver.findElements(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr"));
						//Get Summary
						
						String Summary = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[3]")).getText();
						System.out.println("Total Summary is : "+Summary);
											
						test.log(LogStatus.INFO, "Total Summary is : "+Summary);
						
						//Get Per Rate value
						String perRate = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[4]")).getText();
						
						System.out.println("Total Per Rate is : "+perRate);
						
						test.log(LogStatus.INFO, "Total Per Rate is : "+perRate);
						
						//Get Reg Hours
						String regHours = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[5]")).getText();
						
						System.out.println("Total Reg Hours is : "+regHours);
						
						test.log(LogStatus.INFO, "Total Reg Hours is : "+regHours);
						
						//Get Reg Pay
						String regPay = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[6]")).getText();
						
						System.out.println("Total Reg Pay is : "+regPay);
						
						test.log(LogStatus.INFO, "Total Reg Pay is : "+regPay);
						
						//Get OT Hours
						String otHours = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[7]")).getText();
						
						System.out.println("Total OT Hours is : "+otHours);
						
						test.log(LogStatus.INFO, "Total OT Hours is : "+otHours);
						
						//Get OT Pay
						String otPay = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[8]")).getText();
						
						System.out.println("Total OT Pay is : "+otPay);
						
						test.log(LogStatus.INFO, "Total OT Pay is : "+otPay);
						
						//Get Total Hours
						String hours = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[9]")).getText();
						
						System.out.println("Total Hours is : "+hours);
						
						test.log(LogStatus.INFO, "Total Hours is : "+hours);
						
						//Get TTL Pay
						String ttlPay = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[10]")).getText();
						
						System.out.println("Total TTL Pay is : "+ttlPay);
						
						test.log(LogStatus.INFO, "Total TTL Pay is : "+ttlPay);
						
						//Get CC Tip
						String ccTip = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[11]")).getText();
						
						System.out.println("Total CC Tip is : "+ccTip);
						
						test.log(LogStatus.INFO, "Total CC Tip is : "+ccTip);
						
						//Get Gratuity
						String gratuity = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[12]")).getText();
						
						System.out.println("Total Gratuity is : "+gratuity);
						
						test.log(LogStatus.INFO, "Total Gratuity is : "+gratuity);
						
						//Get Other Tips
						String otherTips = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[13]")).getText();
						
						System.out.println("Total Other Tips is : "+otherTips);
						
						test.log(LogStatus.INFO, "Total Other Tips is : "+otherTips);
						
						//Get Declared Cash Tip
						String declaredCashTip = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[14]")).getText();
						
						System.out.println("Total Declared Cash Tip is : "+declaredCashTip);
						
						test.log(LogStatus.INFO, "Total Declared Cash Tip is : "+declaredCashTip);
						
						//Get Total Tips
						String tip = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[15]")).getText();
						
						System.out.println("Total Tips is : "+tip);
						
						test.log(LogStatus.INFO, "Total Tips is : "+tip);
						
						//Get Net Sales
						String netSales = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[16]")).getText();
						
						System.out.println("Total Net Sales is : "+netSales);
						
						test.log(LogStatus.INFO, "Total Net Sales is : "+netSales);
						
						//Get Expected Tip
						String expectedTip = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[17]")).getText();
						
						System.out.println("Total Expected Tip is : "+expectedTip);
						
						test.log(LogStatus.INFO, "Total Expected Tip is : "+expectedTip);
						
						ArrayList<String> obtainedList = new ArrayList<>(); 
						List<WebElement> elementList= driver.findElements(By.xpath("//td[contains(.,'*')]"));
						for(int i = 1; i <= elementList.size(); i++)
						{
							String namess = driver.findElement(By.xpath("//td[contains(.,'*')]")).getText();
							
							String[] name = namess.split(" ");
							//System.out.println("TEST Z-A LAST : "+name[1]);
							obtainedList.add(name[1]);
						}
						ArrayList<String> sortedList = new ArrayList<>();
						for(String s:obtainedList)
						{
							sortedList.add(s);
						}
						Collections.reverse(sortedList);
						
						for(int i = 0; i < elementList.size(); i++)
						{								
							if(sortedList.equals(obtainedList))
							{
								
							}else
							{
								test.log(LogStatus.FAIL, "Name sort is not working for Z-A Last Name");
							}
						}
					}
				}

		}

package epicList_Chrome_Account_User;


import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import epicList_Chrome_Account_User.Utility;
import newReadExcelFile_New.ExcelDataConfig;


public class Verify_Employee_Payroll_Reports {
	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Verify_Employee_Payroll_Reports");
	
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
				Employee_Payroll_Report_Method_DailyReport_Openpage(driver);
				Employee_Payroll_Report_Method_verifyDailyReport(driver);
				Employee_Payroll_Report_Method_verifyWeeklyReport(driver);
				Thread.sleep(1500);
			}
		
			@Test(priority=28,enabled=false)
			public void Employee_Payroll_Report_Method_DailyReport_Openpage(WebDriver driver) throws Exception
			{
				    File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				 	
					FileInputStream fis = new FileInputStream(src);
					
					XSSFWorkbook wb = new XSSFWorkbook(fis);
					
					XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
					
					ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
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
				driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id1")+"payroll");
				
				Thread.sleep(5000);
				try
				{
				//Check Payroll Report page opened or not
				if(driver.findElement(By.xpath(excel.getData(1, 1575, 1))).getText().equalsIgnoreCase("Payroll"))
				{
					test.log(LogStatus.PASS,"Payroll Report page loaded Successfully");
				
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));
				}
				else
				{
					test.log(LogStatus.FAIL,"Payroll Report page loaded Failed");wb.close();
				
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

			@Test(priority=29,enabled=false)
			public void Employee_Payroll_Report_Method_verifyDailyReport(WebDriver driver) throws Exception
			{
				    File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				 	
					FileInputStream fis = new FileInputStream(src);
					
					XSSFWorkbook wb = new XSSFWorkbook(fis);
					
					XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
					
					ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
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
			    driver.findElement(By.xpath(excel.getData(1, 1550, 1))).click();
			    //Enter the required Employee
			    driver.findElement(By.xpath(excel.getData(1, 1551, 1))).sendKeys("All");
			    //Press the Enter key
			    driver.findElement(By.xpath(excel.getData(1, 1551, 1))).sendKeys(Keys.ENTER);
			    
			    Thread.sleep(1000);
			    //Select the Process as Daily
				Select process = new Select(driver.findElement(By.xpath(excel.getData(1, 1552, 1))));
				process.selectByVisibleText("Daily");                               
			    
		  	    //Thread.sleep(1000);
			    //Select the Specific date Time Period option
			    driver.findElement(By.xpath(excel.getData(1, 1553, 1))).click();
			    //Enter the required Period of time
			    driver.findElement(By.xpath(excel.getData(1, 1554, 1))).sendKeys("Date Range");
			    //Press the Enter key
			    driver.findElement(By.xpath(excel.getData(1, 1554, 1))).sendKeys(Keys.ENTER);
			    
			    Thread.sleep(1000);
			    //Clear the Date field
			    driver.findElement(By.xpath(excel.getData(1, 1555, 1))).clear();
			    //Enter the Date
			    driver.findElement(By.xpath(excel.getData(1, 1555, 1))).sendKeys(Utility.getReportProperty("Payroll_Date_Range_From"));

			    Thread.sleep(1000);
			    //Clear the Date field
			    driver.findElement(By.xpath(excel.getData(1, 1556, 1))).clear();
			    //Enter the Date
			    driver.findElement(By.xpath(excel.getData(1, 1556, 1))).sendKeys(Utility.getReportProperty("Payroll_Date_Range_To"));
				  
				    //Select the Format option
				    driver.findElement(By.xpath(excel.getData(1, 1557, 1))).click();
				    //Enter the required Format
				    driver.findElement(By.xpath(excel.getData(1, 1558, 1))).sendKeys("In Time");
				    //Press the Enter key
				    driver.findElement(By.xpath(excel.getData(1, 1558, 1))).sendKeys(Keys.ENTER);
				  
				    //Select the Active/Inactive option
				    driver.findElement(By.xpath(excel.getData(1, 1559, 1))).click();
				    //Enter the required Format
				    driver.findElement(By.xpath(excel.getData(1, 1560, 1))).sendKeys("All");
				    //Press the Enter key
				    driver.findElement(By.xpath(excel.getData(1, 1560, 1))).sendKeys(Keys.ENTER);

				    Thread.sleep(1000);
				    //Select the Process as Daily
					Select sort1 = new Select(driver.findElement(By.xpath(excel.getData(1, 1576, 1))));
					sort1.selectByVisibleText("A-Z FirstName");
					
				    //Click the Run
				    driver.findElement(By.xpath(excel.getData(1, 572, 1))).click();
				    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				   
					try
					{
						//Check weather the report is available for the selected time period
						if(driver.findElement(By.xpath(excel.getData(3, 2231, 1))).isDisplayed())
						{
							test.log(LogStatus.INFO, "Employee Payroll Report is not available for Specific Date");
						}
					}
					catch(Exception df)
					{
						
						test.log(LogStatus.PASS, "Employee Payroll Report(In Time and Daily) is available for Specific Date");
						
						System.out.println("******* The Below is Employee Payroll Report for In Time(Daily) *******");
						
						test.log(LogStatus.INFO, "******* The Below is Employee Payroll Report for In Time(Daily) *******");
						
						List<WebElement> rows = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/table/tbody/tr"));
						
						//Get the hours Value
						String hours = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/table/tbody/tr["+rows.size()+"]/td[3]")).getText();
						
						System.out.println("Total Hours is : "+hours);
						
						test.log(LogStatus.INFO, "Total Hours is : "+hours);
						
						//Get the OT Hours Value
						String ot_hours = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/table/tbody/tr["+rows.size()+"]/td[4]")).getText();
						
						System.out.println("Total OT Hours is : "+ot_hours);
						
						test.log(LogStatus.INFO, "Total OT Hours is : "+ot_hours);
						
						//Get the Reg Pay Value
						String reg_Pay = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/table/tbody/tr["+rows.size()+"]/td[5]")).getText();
						
						System.out.println("Total Reg Pay is : "+reg_Pay);
						
						test.log(LogStatus.INFO, "Total Reg Pay is : "+reg_Pay);
						
						//Get the TTL Pay Value
						String ttl_Pay = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/table/tbody/tr["+rows.size()+"]/td[6]")).getText();
						
						System.out.println("Total TTL PAY is : "+ttl_Pay);
						
						test.log(LogStatus.INFO, "Total TTL PAY is : "+ttl_Pay);
						
						//Get the expected tip value
						String expected_Tip = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/table/tbody/tr["+rows.size()+"]/td[7]")).getText();
						
						System.out.println("Total Expected is : "+expected_Tip);
						
						test.log(LogStatus.INFO, "Total Expected is : "+expected_Tip);
						
						//Get the non_Cash Tip
						String non_Cash_Tip = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/table/tbody/tr["+rows.size()+"]/td[8]")).getText();
						
						System.out.println("Total Non Cash Tip is : "+non_Cash_Tip);
						
						test.log(LogStatus.INFO, "Total Non Cash Tip is : "+non_Cash_Tip);
						
						//Get the Declared Cash Tip
						String declared_Cash_Tip = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/table/tbody/tr["+rows.size()+"]/td[9]")).getText();
						
						System.out.println("Total Declared Cash Tip is : "+declared_Cash_Tip);
						
						test.log(LogStatus.INFO, "Total Declared Cash Tip is : "+declared_Cash_Tip);
						
						ArrayList<String> obtainedList = new ArrayList<String>(); 
						List<WebElement> elementList= driver.findElements(By.xpath("//td[contains(.,'*')]"));
						for(int i = 1; i <= elementList.size(); i++)
						{
							String namess = driver.findElement(By.xpath("//td[contains(.,'*')]")).getText();
							
							String[] name = namess.split(" ");
							System.out.println("TEST A-Z First : "+name[0]);
							obtainedList.add(name[0]);
						}
						ArrayList<String> sortedList = new ArrayList<String>();
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
						
						Employee_Payroll_Report_Method_verifyDailyReport_With_Dynamic_Report(driver);
					}			  	    	
					
					Thread.sleep(1000);
					//Select the Employee option
				    driver.findElement(By.xpath(excel.getData(1, 1550, 1))).click();
				    //Enter the required Employee
				    driver.findElement(By.xpath(excel.getData(1, 1551, 1))).sendKeys("All");
				    //Press the Enter key
				    driver.findElement(By.xpath(excel.getData(1, 1551, 1))).sendKeys(Keys.ENTER);
				    
				    Thread.sleep(1000);
				    //Select the Process as Daily
					Select process1 = new Select(driver.findElement(By.xpath(excel.getData(1, 1552, 1))));
					process1.selectByVisibleText("Daily");                               
				    
			  	    //Thread.sleep(1000);
				    //Select the Specific date Time Period option
				    driver.findElement(By.xpath(excel.getData(1, 1553, 1))).click();
				    //Enter the required Period of time
				    driver.findElement(By.xpath(excel.getData(1, 1554, 1))).sendKeys("Date Range");
				    //Press the Enter key
				    driver.findElement(By.xpath(excel.getData(1, 1554, 1))).sendKeys(Keys.ENTER);
				    
				    Thread.sleep(1000);
				    //Clear the Date field
				    driver.findElement(By.xpath(excel.getData(1, 1555, 1))).clear();
				    //Enter the Date
				    driver.findElement(By.xpath(excel.getData(1, 1555, 1))).sendKeys(Utility.getReportProperty("Payroll_Date_Range_From"));

				    Thread.sleep(1000);
				    //Clear the Date field
				    driver.findElement(By.xpath(excel.getData(1, 1556, 1))).clear();
				    //Enter the Date
				    driver.findElement(By.xpath(excel.getData(1, 1556, 1))).sendKeys(Utility.getReportProperty("Payroll_Date_Range_To"));
					  
					    //Select the Format option
					    driver.findElement(By.xpath(excel.getData(1, 1557, 1))).click();
					    //Enter the required Format
					    driver.findElement(By.xpath(excel.getData(1, 1558, 1))).sendKeys("In Hours");
					    //Press the Enter key
					    driver.findElement(By.xpath(excel.getData(1, 1558, 1))).sendKeys(Keys.ENTER);
					  
					    //Select the Active/Inactive option
					    driver.findElement(By.xpath(excel.getData(1, 1559, 1))).click();
					    //Enter the required Format
					    driver.findElement(By.xpath(excel.getData(1, 1560, 1))).sendKeys("All");
					    //Press the Enter key
					    driver.findElement(By.xpath(excel.getData(1, 1560, 1))).sendKeys(Keys.ENTER);

					    Thread.sleep(1000);
					    //Select the Process as Daily
						Select sort = new Select(driver.findElement(By.xpath(excel.getData(1, 1576, 1))));
						sort.selectByVisibleText("Z-A FirstName");   
					    
					    //Click the Run
					    driver.findElement(By.xpath(excel.getData(1, 572, 1))).click();
					    Thread.sleep(1000);
					   
					    try
					    {
					    	//Check weather the report is available for the selected time period
							if(driver.findElement(By.xpath(excel.getData(3, 2231, 1))).isDisplayed())
							{
								test.log(LogStatus.INFO, "Employee Payroll Report is not available for Specific Date");
							
								String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
								String s="data:image/png;base64,"+scnShot;
								test.log(LogStatus.INFO,test.addScreenCapture(s));

							}
					    }
						catch(Exception fd)
						{
							
							test.log(LogStatus.PASS, "Employee Payroll Report(In Hours and Daily) is available for Specific Date");
							
							System.out.println("******* The Below is Employee Payroll Report for In Hours(Daily) *******");
							
							test.log(LogStatus.INFO, "******* The Below is Employee Payroll Report for In Hours(Daily) *******");
							
							List<WebElement> rows = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/table/tbody/tr"));
							
							//Get the hours Value
							String hours = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/table/tbody/tr["+rows.size()+"]/td[3]")).getText();
							
							System.out.println("Total Hours is : "+hours);
							
							test.log(LogStatus.INFO, "Total Hours is : "+hours);
							
							//Get the OT Hours Value
							String ot_hours = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/table/tbody/tr["+rows.size()+"]/td[4]")).getText();
							
							System.out.println("Total OT Hours is : "+ot_hours);
							
							test.log(LogStatus.INFO, "Total OT Hours is : "+ot_hours);
							
							//Get the Reg Pay Value
							String reg_Pay = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/table/tbody/tr["+rows.size()+"]/td[5]")).getText();
							
							System.out.println("Total Reg Pay is : "+reg_Pay);
							
							test.log(LogStatus.INFO, "Total Reg Pay is : "+reg_Pay);
							
							//Get the TTL Pay Value
							String ttl_Pay = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/table/tbody/tr["+rows.size()+"]/td[6]")).getText();
							
							System.out.println("Total TTL PAY is : "+ttl_Pay);
							
							test.log(LogStatus.INFO, "Total TTL PAY is : "+ttl_Pay);
							
							//Get the expected tip value
							String expected_Tip = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/table/tbody/tr["+rows.size()+"]/td[7]")).getText();
							
							System.out.println("Total Expected is : "+expected_Tip);
							
							test.log(LogStatus.INFO, "Total Expected is : "+expected_Tip);
							
							//Get the non_Cash Tip
							String non_Cash_Tip = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/table/tbody/tr["+rows.size()+"]/td[8]")).getText();
							
							System.out.println("Total Non Cash Tip is : "+non_Cash_Tip);
							
							test.log(LogStatus.INFO, "Total Non Cash Tip is : "+non_Cash_Tip);
							
							//Get the Declared Cash Tip
							String declared_Cash_Tip = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/table/tbody/tr["+rows.size()+"]/td[9]")).getText();
							
							System.out.println("Total Declared Cash Tip is : "+declared_Cash_Tip);
							
							test.log(LogStatus.INFO, "Total Declared Cash Tip is : "+declared_Cash_Tip);
						
							driver.findElement(By.tagName("html")).sendKeys(Keys.END);
							
							Thread.sleep(3000);
							
							String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
							String s1="data:image/png;base64,"+scnShot;
							test.log(LogStatus.INFO,test.addScreenCapture(s1));

							Thread.sleep(3000);
							
							driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
							
							ArrayList<String> obtainedList = new ArrayList<String>(); 
							List<WebElement> elementList= driver.findElements(By.xpath("//td[contains(.,'*')]"));
							for(int i = 1; i <= elementList.size(); i++)
							{
								String namess = driver.findElement(By.xpath("//td[contains(.,'*')]")).getText();
								
								String[] name = namess.split(" ");
								System.out.println("TEST Z-A FIRST : "+name[0]);
								obtainedList.add(name[0]);
							}
							ArrayList<String> sortedList = new ArrayList<String>();
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
									test.log(LogStatus.FAIL, "Name sort is not working  for Z-A First Name");wb.close();
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
			
			@Test(priority=30,enabled=false)
			public void Employee_Payroll_Report_Method_verifyWeeklyReport(WebDriver driver) throws Exception
			{
				File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			 	
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				Thread.sleep(1000);
				//Select the Employee option
			    driver.findElement(By.xpath(excel.getData(1, 1550, 1))).click();
			    //Enter the required Employee
			    driver.findElement(By.xpath(excel.getData(1, 1551, 1))).sendKeys("All");
			    //Press the Enter key
			    driver.findElement(By.xpath(excel.getData(1, 1551, 1))).sendKeys(Keys.ENTER);
			  
			    //Select the Process as Daily
				Select process = new Select(driver.findElement(By.xpath(excel.getData(1, 1552, 1))));
				process.selectByVisibleText("Weekly");
					
		  	    //Thread.sleep(1000);
			    //Select the Specific date Time Period option
			    driver.findElement(By.xpath(excel.getData(1, 1553, 1))).click();
			    //Enter the required Period of time
			    driver.findElement(By.xpath(excel.getData(1, 1554, 1))).sendKeys("Date Range");
			    //Press the Enter key
			    driver.findElement(By.xpath(excel.getData(1, 1554, 1))).sendKeys(Keys.ENTER);
			    
			    Thread.sleep(3000);
			    //Clear the Date field
			    driver.findElement(By.xpath(excel.getData(1, 1555, 1))).clear();
			    //Enter the Date
			    driver.findElement(By.xpath(excel.getData(1, 1555, 1))).sendKeys(Utility.getReportProperty("Payroll_Date_Range_From"));

			    Thread.sleep(1000);
			    //Clear the Date field
			    driver.findElement(By.xpath(excel.getData(1, 1556, 1))).clear();
			    //Enter the Date
			    driver.findElement(By.xpath(excel.getData(1, 1556, 1))).sendKeys(Utility.getReportProperty("Payroll_Date_Range_To"));
			    Thread.sleep(1000);
			    
			    //Select the Format option
			    driver.findElement(By.xpath(excel.getData(1, 1557, 1))).click();
			    //Enter the required Format
			    driver.findElement(By.xpath(excel.getData(1, 1558, 1))).sendKeys("In Hours");
			    //Press the Enter key
			    driver.findElement(By.xpath(excel.getData(1, 1558, 1))).sendKeys(Keys.ENTER);
			    
			    //Select the Active/Inactive option
			    driver.findElement(By.xpath(excel.getData(1, 1559, 1))).click();
			    //Enter the required Format
			    driver.findElement(By.xpath(excel.getData(1, 1560, 1))).sendKeys("All");
			    //Press the Enter key
			    driver.findElement(By.xpath(excel.getData(1, 1560, 1))).sendKeys(Keys.ENTER);

			    Thread.sleep(1000);
			    //Select the Process as Daily
				Select sort1 = new Select(driver.findElement(By.xpath(excel.getData(1, 1576, 1))));
				sort1.selectByVisibleText("A-Z LastName");
				
			    Thread.sleep(1000);
			     //Click the Run
			    driver.findElement(By.xpath(excel.getData(1, 572, 1))).click();
			    Thread.sleep(1000);
			
				try
				{
					//Check weather the report is available for the selected time period
					if(driver.findElement(By.xpath(excel.getData(3, 2231, 1))).isDisplayed())
					{
						test.log(LogStatus.INFO, "Employee Payroll Report is not available for Specific Date");
					
						String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));

					}
				}
				catch(Exception df)
				{
					
					test.log(LogStatus.PASS, "Employee Payroll Report(In Hours and Weekly) is available for Specific Date");
					
					System.out.println("******* The Below is Employee Payroll Report for In Hours(Weekly) *******");
					
					test.log(LogStatus.INFO, "******* The Below is Employee Payroll Report for In Hours(Weekly) *******");
					
					List<WebElement> rows = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/table/tbody/tr"));
					
					//Get the Reg Pay Value
					String reg_Pay = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/table/tbody/tr["+rows.size()+"]/td[2]")).getText();
					
					System.out.println("Total Reg Pay is : "+reg_Pay);
					
					test.log(LogStatus.INFO, "Total Reg Pay is : "+reg_Pay);
					
					//Get the TTL Pay Value
					String ttl_Pay = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/table/tbody/tr["+rows.size()+"]/td[3]")).getText();
					
					System.out.println("Total TTL PAY is : "+ttl_Pay);
					
					test.log(LogStatus.INFO, "Total TTL PAY is : "+ttl_Pay);
					
					//Get the Working Hours Value
					String working_Hours = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/table/tbody/tr["+rows.size()+"]/td[4]")).getText();
					
					System.out.println("Total Working Hours is : "+working_Hours);
					
					test.log(LogStatus.INFO, "Total Working Hours is : "+working_Hours);
					
					//Get the OT Hours Value
					String ot_Hours = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/table/tbody/tr["+rows.size()+"]/td[5]")).getText();
					
					System.out.println("Total OT Hours is : "+ot_Hours);
					
					test.log(LogStatus.INFO, "Total OT Hours is : "+ot_Hours);
					
					//Get the expected tip value
					String expected_Tip = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/table/tbody/tr["+rows.size()+"]/td[6]")).getText();
					
					System.out.println("Total Expected Cash Tip is : "+expected_Tip);
					
					test.log(LogStatus.INFO, "Total Expected Cash Tip is : "+expected_Tip);
					
					//Get the credit card cash Tip
					String credit_Card_Cash_Tip = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/table/tbody/tr["+rows.size()+"]/td[7]")).getText();
					
					System.out.println("Total Credit Card Cash Tip is : "+credit_Card_Cash_Tip);
					
					test.log(LogStatus.INFO, "Total Credit Card Cash Tip is : "+credit_Card_Cash_Tip);
					
					//Get the Declared Cash Tip
					String declared_Cash_Tip = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/table/tbody/tr["+rows.size()+"]/td[8]")).getText();
					
					System.out.println("Total Declared Cash Tip is : "+declared_Cash_Tip);
					
					test.log(LogStatus.INFO, "Total Declared Cash Tip is : "+declared_Cash_Tip);
					
					driver.findElement(By.tagName("html")).sendKeys(Keys.END);
					
					Thread.sleep(3000);
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s2="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s2));

					Thread.sleep(3000);
					driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
					
					ArrayList<String> obtainedList = new ArrayList<String>(); 
					List<WebElement> elementList= driver.findElements(By.xpath("//td[contains(.,'*')]"));
					for(int i = 1; i <= elementList.size(); i++)
					{
						String namess = driver.findElement(By.xpath("//td[contains(.,'*')]")).getText();
						
						String[] name = namess.split(" ");
						System.out.println("TEST A-Z LAST : "+name[1]);
						obtainedList.add(name[1]);
					}
					ArrayList<String> sortedList = new ArrayList<String>();
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
					
					Employee_Payroll_Report_Method_verifyWeeklyReport_With_Dynamic_Report(driver);
				} 
				
				Thread.sleep(1000);
				//Select the Employee option
			    driver.findElement(By.xpath(excel.getData(1, 1550, 1))).click();
			    //Enter the required Employee
			    driver.findElement(By.xpath(excel.getData(1, 1551, 1))).sendKeys("All");
			    //Press the Enter key
			    driver.findElement(By.xpath(excel.getData(1, 1551, 1))).sendKeys(Keys.ENTER);
			  
			    //Select the Process as Daily
				Select process1 = new Select(driver.findElement(By.xpath(excel.getData(1, 1552, 1))));
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
			    driver.findElement(By.xpath(excel.getData(1, 1553, 1))).click();
			    //Enter the required Period of time
			    driver.findElement(By.xpath(excel.getData(1, 1554, 1))).sendKeys("Date Range");
			    //Press the Enter key
			    driver.findElement(By.xpath(excel.getData(1, 1554, 1))).sendKeys(Keys.ENTER);
			    
			    Thread.sleep(1000);
			    //Clear the Date field
			    driver.findElement(By.xpath(excel.getData(1, 1555, 1))).clear();
			    //Enter the Date
			    driver.findElement(By.xpath(excel.getData(1, 1555, 1))).sendKeys(Utility.getReportProperty("Payroll_Date_Range_From"));

			    Thread.sleep(1000);
			    //Clear the Date field
			    driver.findElement(By.xpath(excel.getData(1, 1556, 1))).clear();
			    //Enter the Date
			    driver.findElement(By.xpath(excel.getData(1, 1556, 1))).sendKeys(Utility.getReportProperty("Payroll_Date_Range_To_Weekly"));
			    Thread.sleep(1000);
			    
			    //Select the Format option
			    driver.findElement(By.xpath(excel.getData(1, 1557, 1))).click();
			    //Enter the required Format
			    driver.findElement(By.xpath(excel.getData(1, 1558, 1))).sendKeys("In Time");
			    //Press the Enter key
			    driver.findElement(By.xpath(excel.getData(1, 1558, 1))).sendKeys(Keys.ENTER);
			    
			    //Select the Active/Inactive option
			    driver.findElement(By.xpath(excel.getData(1, 1559, 1))).click();
			    //Enter the required Format
			    driver.findElement(By.xpath(excel.getData(1, 1560, 1))).sendKeys("All");
			    //Press the Enter key
			    driver.findElement(By.xpath(excel.getData(1, 1560, 1))).sendKeys(Keys.ENTER);

			    Thread.sleep(1000);
			    //Select the Process as Daily
				Select sort = new Select(driver.findElement(By.xpath(excel.getData(1, 1576, 1))));
				sort.selectByVisibleText("Z-A LastName");
				
			    Thread.sleep(1000);
			     //Click the Run
			    driver.findElement(By.xpath(excel.getData(1, 572, 1))).click();
			    Thread.sleep(1000);
			    
			    
				try
				
				{
					//Check weather the report is available for the selected time period
					if(driver.findElement(By.xpath(excel.getData(3, 2231, 1))).isDisplayed())
					{
						test.log(LogStatus.INFO, "Employee Payroll Report is not available for Specific Date");
					
						String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));

					
					}
				}
				catch(Exception gf)
				{
					
					test.log(LogStatus.PASS, "Employee Payroll Report(In Time and Weekly) is available for Specific Date");
					
					System.out.println("******* The Below is Employee Payroll Report for In Time(Weekly) *******");
					
					test.log(LogStatus.INFO, "******* The Below is Employee Payroll Report for In Time(Weekly) *******");
					
					List<WebElement> rows = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/table/tbody/tr"));
					
					//Get the Reg Pay Value
					String reg_Pay = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/table/tbody/tr["+rows.size()+"]/td[2]")).getText();
					
					System.out.println("Total Reg Pay is : "+reg_Pay);
					
					test.log(LogStatus.INFO, "Total Reg Pay is : "+reg_Pay);
					
					//Get the TTL Pay Value
					String ttl_Pay = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/table/tbody/tr["+rows.size()+"]/td[3]")).getText();
					
					System.out.println("Total TTL PAY is : "+ttl_Pay);
					
					test.log(LogStatus.INFO, "Total TTL PAY is : "+ttl_Pay);
					
					//Get the Working Hours Value
					String working_Hours = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/table/tbody/tr["+rows.size()+"]/td[4]")).getText();
					
					System.out.println("Total Working Hours is : "+working_Hours);
					
					test.log(LogStatus.INFO, "Total Working Hours is : "+working_Hours);
					
					//Get the OT Hours Value
					String ot_Hours = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/table/tbody/tr["+rows.size()+"]/td[5]")).getText();
					
					System.out.println("Total OT Hours is : "+ot_Hours);
					
					test.log(LogStatus.INFO, "Total OT Hours is : "+ot_Hours);
					
					//Get the expected tip value
					String expected_Tip = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/table/tbody/tr["+rows.size()+"]/td[6]")).getText();
					
					System.out.println("Total Expected Cash Tip is : "+expected_Tip);
					
					test.log(LogStatus.INFO, "Total Expected Cash Tip is : "+expected_Tip);
					
					//Get the credit card cash Tip
					String credit_Card_Cash_Tip = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/table/tbody/tr["+rows.size()+"]/td[7]")).getText();
					
					System.out.println("Total Credit Card Cash Tip is : "+credit_Card_Cash_Tip);
					
					test.log(LogStatus.INFO, "Total Credit Card Cash Tip is : "+credit_Card_Cash_Tip);
					
					//Get the Declared Cash Tip
					String declared_Cash_Tip = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/table/tbody/tr["+rows.size()+"]/td[8]")).getText();
					
					System.out.println("Total Declared Cash Tip is : "+declared_Cash_Tip);
					
					test.log(LogStatus.INFO, "Total Declared Cash Tip is : "+declared_Cash_Tip);
					
					driver.findElement(By.tagName("html")).sendKeys(Keys.END);
					
					Thread.sleep(2000);
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s3="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s3));

					Thread.sleep(2000);
					driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
					
					
					ArrayList<String> obtainedList = new ArrayList<String>(); 
					List<WebElement> elementList= driver.findElements(By.xpath("//td[contains(.,'*')]"));
					for(int i = 1; i <= elementList.size(); i++)
					{
						String namess = driver.findElement(By.xpath("//td[contains(.,'*')]")).getText();
						
						String[] name = namess.split(" ");
						System.out.println("TEST Z-A LAST : "+name[1]);
						obtainedList.add(name[1]);
					}
					ArrayList<String> sortedList = new ArrayList<String>();
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
							test.log(LogStatus.FAIL, "Name sort is not working for Z-A Last Name");wb.close();
						}
					}
				}
			}

			@Test(enabled=false,priority=29)
			public void Employee_Payroll_Report_Method_verifyDailyReport_With_Dynamic_Report(WebDriver driver) throws Exception
			{
				    File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				 	
					FileInputStream fis = new FileInputStream(src);
					
					XSSFWorkbook wb = new XSSFWorkbook(fis);
					
					XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
					
					ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
					//Check Whether all the field is selected or not
					if(driver.findElement(By.xpath(excel.getData(3, 1607, 1))).isSelected()
							&&driver.findElement(By.xpath("//span[contains(.,'OT Hours')]/../input")).isSelected()
							&&driver.findElement(By.xpath("//span[contains(.,'Reg Pay')]/../input")).isSelected()
							&&driver.findElement(By.xpath("//span[contains(.,'TTL Pay')]/../input")).isSelected()
							&&driver.findElement(By.xpath(excel.getData(3, 1609, 1))).isSelected()
							&&driver.findElement(By.xpath(excel.getData(3, 1610, 1))).isSelected()
							&&driver.findElement(By.xpath(excel.getData(3, 1611, 1))).isSelected())
					{
						//Check weather the table format report is available or not
						if(driver.findElement(By.xpath(excel.getData(1, 1577, 1))).isDisplayed())
						{
							test.log(LogStatus.PASS, "Table Format Report is available for the Required Date Range");
							
							//Check whether all the filed is available or not
							if(driver.findElement(By.xpath(excel.getData(3, 1614, 1))).isDisplayed()
									&&driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'OT Hours')]")).isDisplayed()
									&&driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'Reg Pay')]")).isDisplayed()
									&&driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'TTL Pay')]")).isDisplayed()
									&&driver.findElement(By.xpath(excel.getData(3, 1616, 1))).isDisplayed()
									&&driver.findElement(By.xpath(excel.getData(3, 1617, 1))).isDisplayed()
									&&driver.findElement(By.xpath(excel.getData(3, 1618, 1))).isDisplayed())
							{
								test.log(LogStatus.PASS, "All the Neccessary fields are displayed");
							}
							else
							{
								test.log(LogStatus.FAIL, "Neccessary fields are not displayed");
							}
							
							//Disable the required option from enable to disable Hours Option
							if(driver.findElement(By.xpath(excel.getData(3, 1607, 1))).isSelected())
							{
								//Click the Enabled Hours Option
								Actions act = new Actions(driver);
								act.moveToElement(driver.findElement(By.xpath(excel.getData(3, 1621, 1)))).click().build().perform();
								try
								{
									//Check whether the Hours Option is displayed or not
									if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th[3]/div/span[.='Hours']")).isDisplayed())
									{
										test.log(LogStatus.FAIL, "Hours is displayed");
									}
								}
								catch(Exception e)
								{
									test.log(LogStatus.PASS, "Hours is not displayed");
								}
							}
							else
							{
								test.log(LogStatus.FAIL, "Hours Option is Disabled When user enter the required date for date range");
							}
							
							//Disable the required option from enable to disable OT Hours Option
							if(driver.findElement(By.xpath("//span[contains(.,'OT Hours')]/../input")).isSelected())
							{
								//Click the Enabled OT Hours Option
								Actions act = new Actions(driver);
								act.moveToElement(driver.findElement(By.xpath("//input/../span[contains(.,'OT Hours')]"))).click().build().perform();
								try
								{
									//Check whether the OT Hours Option is displayed or not
									if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'OT Hours')]")).isDisplayed())
									{
										test.log(LogStatus.FAIL, "OT Hours is displayed");
									}
								}
								catch(Exception e)
								{
									test.log(LogStatus.PASS, "OT Hours is not displayed");
								}
							}
							else
							{
								test.log(LogStatus.FAIL, "OT Hours Option is Disabled When user enter the required date for date range");
							}
							
							//Disable the required option from enable to disable Reg Pay Option
							if(driver.findElement(By.xpath("//span[contains(.,'Reg Pay')]/../input")).isSelected())
							{
								//Click the Enabled Reg Pay Option
								Actions act = new Actions(driver);
								act.moveToElement(driver.findElement(By.xpath("//input/../span[contains(.,'Reg Pay')]"))).click().build().perform();
								try
								{
									//Check whether the Reg Pay Option is displayed or not
									if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'Reg Pay')]")).isDisplayed())
									{
										test.log(LogStatus.FAIL, "Reg Pay is displayed");
									}
								}
								catch(Exception e)
								{
									test.log(LogStatus.PASS, "Reg Pay is not displayed");
								}
							}
							else
							{
								test.log(LogStatus.FAIL, "Reg Pay Option is Disabled When user enter the required date for date range");
							}
							
							//Disable the required option from enable to disable TTL Pay Option
							if(driver.findElement(By.xpath("//span[contains(.,'TTL Pay')]/../input")).isSelected())
							{
								//Click the Enabled TTL Pay Option
								Actions act = new Actions(driver);
								act.moveToElement(driver.findElement(By.xpath("//input/../span[contains(.,'TTL Pay')]"))).click().build().perform();
								try
								{
									//Check whether the TTL Pay Option is displayed or not
									if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'TTL Pay')]")).isDisplayed())
									{
										test.log(LogStatus.FAIL, "TTL Pay is displayed");
									}
								}
								catch(Exception e)
								{
									test.log(LogStatus.PASS, "TTL Pay is not displayed");
								}
							}
							else
							{
								test.log(LogStatus.FAIL, "TTL Pay Option is Disabled When user enter the required date for date range");
							}
							
							//Disable the required option from enable to disable Expected Tip Option
							if(driver.findElement(By.xpath(excel.getData(3, 1609, 1))).isSelected())
							{
								//Click the Enabled Expected Tip Option
								Actions act = new Actions(driver);
								act.moveToElement(driver.findElement(By.xpath(excel.getData(3, 1623, 1)))).click().build().perform();
								try
								{
									//Check whether the Expected Tip Option is displayed or not
									if(driver.findElement(By.xpath(excel.getData(3, 1616, 1))).isDisplayed())
									{
										test.log(LogStatus.FAIL, "Expected Tip is displayed");
									}
								}
								catch(Exception e)
								{
									test.log(LogStatus.PASS, "Expected Tip is not displayed");
								}
							}
							else
							{
								test.log(LogStatus.FAIL, "Expected Tip Option is Disabled When user enter the required date for date range");
							}
							
							//Disable the required option from enable to disable Non Cash Tip Option
							if(driver.findElement(By.xpath(excel.getData(3, 1610, 1))).isSelected())
							{
								//Click the Enabled Non Cash Tip Option
								Actions act = new Actions(driver);
								act.moveToElement(driver.findElement(By.xpath(excel.getData(3, 1624, 1)))).click().build().perform();
								try
								{
									//Check whether the Non Cash Tip Option is displayed or not
									if(driver.findElement(By.xpath(excel.getData(3, 1617, 1))).isDisplayed())
									{
										test.log(LogStatus.FAIL, "Non Cash Tip is displayed");
									}
								}
								catch(Exception e)
								{
									test.log(LogStatus.PASS, "Non Cash Tip is not displayed");
								}
							}
							else
							{
								test.log(LogStatus.FAIL, "Non Cash Tip Option is Disabled When user enter the required date for date range");
							}
							
							//Disable the required option from enable to disable Declared Cash Tip Option
							if(driver.findElement(By.xpath(excel.getData(3, 1611, 1))).isSelected())
							{
								//Click the Enabled Declared Cash Tip Option
								Actions act = new Actions(driver);
								act.moveToElement(driver.findElement(By.xpath(excel.getData(3, 1625, 1)))).click().build().perform();
								try
								{
									//Check whether the Declared Cash Tip Option is displayed or not
									if(driver.findElement(By.xpath(excel.getData(3, 1618, 1))).isDisplayed())
									{
										test.log(LogStatus.FAIL, "Declared Cash Tip is displayed");
									}
								}
								catch(Exception e)
								{
									test.log(LogStatus.PASS, "Declared Cash Tip is not displayed");
								}
							}
							else
							{
								test.log(LogStatus.FAIL, "Declared Cash Tip Option is Disabled When user enter the required date for date range");
							}
												
							//Enable the Hours Option if it is in disabled status
							if(driver.findElement(By.xpath(excel.getData(3, 1607, 1))).isSelected())
							{
								test.log(LogStatus.FAIL, "Hours Option is Enabled after user click(Disable) the Hours option");
							}
							else
							{
								//Click the Enabled Hours Option
								Actions act = new Actions(driver);
								act.moveToElement(driver.findElement(By.xpath(excel.getData(3, 1621, 1)))).click().build().perform();
								try
								{
									//Check whether the Hours Option is displayed or not
									if(driver.findElement(By.xpath(excel.getData(3, 1614, 1))).isDisplayed())
									{
										test.log(LogStatus.PASS, "Hours is displayed");
									}
								}
								catch(Exception e)
								{
									test.log(LogStatus.FAIL, "Hours is not displayed");
								}				
							}
							
							//Disable the required option from enable to disable OT Hours Option
							if(driver.findElement(By.xpath("//span[contains(.,'OT Hours')]/../input")).isSelected())
							{
								test.log(LogStatus.FAIL, "OT Hours Option is Enabled after user click(Disable) the OT Hours option");
							}
							else
							{
								//Click the Enabled OT Hours Option
								Actions act = new Actions(driver);
								act.moveToElement(driver.findElement(By.xpath("//input/../span[contains(.,'OT Hours')]"))).click().build().perform();
								try
								{
									//Check whether the OT Hours Option is displayed or not
									if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'OT Hours')]")).isDisplayed())
									{
										test.log(LogStatus.PASS, "OT Hours is displayed");
									}
								}
								catch(Exception e)
								{
									test.log(LogStatus.FAIL, "OT Hours is not displayed");
								}

							}
							
							//Disable the required option from enable to disable Reg Pay Option
							if(driver.findElement(By.xpath("//span[contains(.,'Reg Pay')]/../input")).isSelected())
							{
								test.log(LogStatus.FAIL, "Reg Pay Option is Enabled after user click(Disable) the Reg Pay option");
							}
							else
							{

								//Click the Enabled Reg Pay Option
								Actions act = new Actions(driver);
								act.moveToElement(driver.findElement(By.xpath("//input/../span[contains(.,'Reg Pay')]"))).click().build().perform();
								try
								{
									//Check whether the Reg Pay Option is displayed or not
									if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'Reg Pay')]")).isDisplayed())
									{
										test.log(LogStatus.PASS, "Reg Pay is displayed");
									}
								}
								catch(Exception e)
								{
									test.log(LogStatus.FAIL, "Reg Pay is not displayed");
								}

							}
							
							//Disable the required option from enable to disable TTL Pay Option
							if(driver.findElement(By.xpath("//span[contains(.,'TTL Pay')]/../input")).isSelected())
							{
								test.log(LogStatus.FAIL, "TTL Pay Option is Enabled after user click(Disable) the TTL Pay option");

							}
							else
							{

								//Click the Enabled TTL Pay Option
								Actions act = new Actions(driver);
								act.moveToElement(driver.findElement(By.xpath("//input/../span[contains(.,'TTL Pay')]"))).click().build().perform();
								try
								{
									//Check whether the TTL Pay Option is displayed or not
									if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'TTL Pay')]")).isDisplayed())
									{
										test.log(LogStatus.PASS, "TTL Pay is displayed");
									}
								}
								catch(Exception e)
								{
									test.log(LogStatus.FAIL, "TTL Pay is not displayed");
								}
							}
							
							//Disable the required option from enable to disable Expected Tip Option
							if(driver.findElement(By.xpath(excel.getData(3, 1609, 1))).isSelected())
							{
								test.log(LogStatus.FAIL, "Expected Tip Option is Enabled after user click(Disable) the Expected Tip option");

							}
							else
							{

								//Click the Enabled Expected Tip Option
								Actions act = new Actions(driver);
								act.moveToElement(driver.findElement(By.xpath(excel.getData(3, 1623, 1)))).click().build().perform();
								try
								{
									//Check whether the Expected Tip Option is displayed or not
									if(driver.findElement(By.xpath(excel.getData(3, 1616, 1))).isDisplayed())
									{
										test.log(LogStatus.PASS, "Expected Tip is displayed");
									}
								}
								catch(Exception e)
								{
									test.log(LogStatus.FAIL, "Expected Tip is not displayed");
								}
							}
							
							//Disable the required option from enable to disable Non Cash Tip Option
							if(driver.findElement(By.xpath(excel.getData(3, 1610, 1))).isSelected())
							{
								test.log(LogStatus.FAIL, "Non Cash Tip Option is Enabled after user click(Disable) the Non Cash Tip option");

							}
							else
							{

								//Click the Enabled Non Cash Tip Option
								Actions act = new Actions(driver);
								act.moveToElement(driver.findElement(By.xpath(excel.getData(3, 1624, 1)))).click().build().perform();
								try
								{
									//Check whether the Non Cash Tip Option is displayed or not
									if(driver.findElement(By.xpath(excel.getData(3, 1617, 1))).isDisplayed())
									{
										test.log(LogStatus.PASS, "Non Cash Tip is displayed");
									}
								}
								catch(Exception e)
								{
									test.log(LogStatus.FAIL, "Non Cash Tip is not displayed");
								}
							}
							
							//Disable the required option from enable to disable Declared Cash Tip Option
							if(driver.findElement(By.xpath(excel.getData(3, 1611, 1))).isSelected())
							{
								test.log(LogStatus.FAIL, "Declared Cash Tip Option is Enabled after user click(Disable) the Declared Cash Tip option");

							}
							else
							{

								//Click the Enabled Declared Cash Tip Option
								Actions act = new Actions(driver);
								act.moveToElement(driver.findElement(By.xpath(excel.getData(3, 1625, 1)))).click().build().perform();
								try
								{
									//Check whether the Declared Cash Tip Option is displayed or not
									if(driver.findElement(By.xpath(excel.getData(3, 1618, 1))).isDisplayed())
									{
										test.log(LogStatus.PASS, "Declared Cash Tip is displayed");
									}
								}
								catch(Exception e)
								{
									test.log(LogStatus.FAIL, "Declared Cash Tip is not displayed");
								}
							}
							
						}
						else
						{
							test.log(LogStatus.INFO, "Table Format Report is not available for the Required Date Range");
						}
						
					}
					else
					{
						test.log(LogStatus.FAIL, "All dynamic option is not selected");wb.close();
					}  
			  	    	
			}
			
			@Test(enabled=false,priority=30)
			public void Employee_Payroll_Report_Method_verifyWeeklyReport_With_Dynamic_Report(WebDriver driver) throws Exception
			{		
				File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			 	
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				//Check Whether all the field is selected or not
				if(driver.findElement(By.xpath(excel.getData(3, 1605, 1))).isSelected()
						&&driver.findElement(By.xpath(excel.getData(3, 1606, 1))).isSelected()
						&&driver.findElement(By.xpath(excel.getData(3, 1607, 1))).isSelected()
						&&driver.findElement(By.xpath(excel.getData(3, 1608, 1))).isSelected()
						&&driver.findElement(By.xpath(excel.getData(3, 1609, 1))).isSelected()
						&&driver.findElement(By.xpath(excel.getData(3, 1610, 1))).isSelected()
						&&driver.findElement(By.xpath(excel.getData(3, 1611, 1))).isSelected())
				{
					//Check weather the table format report is available or not
					if(driver.findElement(By.xpath(excel.getData(1, 1577, 1))).isDisplayed())
					{
						test.log(LogStatus.PASS, "Table Format Report is available for the Required Date Range");
						
						//Check whether all the filed is available or not
						if(driver.findElement(By.xpath(excel.getData(3, 1612, 1))).isDisplayed()
								&&driver.findElement(By.xpath(excel.getData(3, 1613, 1))).isDisplayed()
								&&driver.findElement(By.xpath(excel.getData(3, 1614, 1))).isDisplayed()
								&&driver.findElement(By.xpath(excel.getData(3, 1615, 1))).isDisplayed()
								&&driver.findElement(By.xpath(excel.getData(3, 1616, 1))).isDisplayed()
								&&driver.findElement(By.xpath(excel.getData(3, 1617, 1))).isDisplayed()
								&&driver.findElement(By.xpath(excel.getData(3, 1618, 1))).isDisplayed())
						{
							test.log(LogStatus.PASS, "All the Neccessary fields are displayed");
						}
						else
						{
							test.log(LogStatus.FAIL, "Neccessary fields are not displayed");
						}
						
						//Disable the required option from enable to disable CheckIn Option
						if(driver.findElement(By.xpath(excel.getData(3, 1605, 1))).isSelected())
						{
							//Click the Enabled OT Hours Option
							Actions act = new Actions(driver);
							act.moveToElement(driver.findElement(By.xpath(excel.getData(3, 1619, 1)))).click().build().perform();
							try
							{
								//Check whether the CheckIn Option is displayed or not
								if(driver.findElement(By.xpath(excel.getData(3, 1612, 1))).isDisplayed())
								{
									test.log(LogStatus.FAIL, "CheckIn is displayed");
								}
							}
							catch(Exception e)
							{
								test.log(LogStatus.PASS, "CheckIn is not displayed");
							}
						}
						else
						{
							test.log(LogStatus.FAIL, "CheckIn Option is Disabled When user enter the required date for date range");
						}
						
						//Disable the required option from enable to disable CheckOut Option
						if(driver.findElement(By.xpath(excel.getData(3, 1606, 1))).isSelected())
						{
							//Click the Enabled CheckOut Option
							Actions act = new Actions(driver);
							act.moveToElement(driver.findElement(By.xpath(excel.getData(3, 1620, 1)))).click().build().perform();
							try
							{
								//Check whether the CheckOut Option is displayed or not
								if(driver.findElement(By.xpath(excel.getData(3, 1613, 1))).isDisplayed())
								{
									test.log(LogStatus.FAIL, "CheckOut is displayed");
								}
							}
							catch(Exception e)
							{
								test.log(LogStatus.PASS, "CheckOut is not displayed");
							}
						}
						else
						{
							test.log(LogStatus.FAIL, "CheckOut Option is Disabled When user enter the required date for date range");
						}

						//Disable the required option from enable to disable Hours Option
						if(driver.findElement(By.xpath(excel.getData(3, 1607, 1))).isSelected())
						{
							//Click the Enabled Hours Option
							Actions act = new Actions(driver);
							act.moveToElement(driver.findElement(By.xpath(excel.getData(3, 1621, 1)))).click().build().perform();
							try
							{
								//Check whether the Hours Option is displayed or not
								if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th[3]/div/span[.='Hours']")).isDisplayed())
								{
									test.log(LogStatus.FAIL, "Hours is displayed");
								}
							}
							catch(Exception e)
							{
								test.log(LogStatus.PASS, "Hours is not displayed");
							}
						}
						else
						{
							test.log(LogStatus.FAIL, "Hours Option is Disabled When user enter the required date for date range");
						}
												
						//Disable the required option from enable to disable Job Code Option
						if(driver.findElement(By.xpath(excel.getData(3, 1608, 1))).isSelected())
						{
							//Click the Enabled Job Code Option
							Actions act = new Actions(driver);
							act.moveToElement(driver.findElement(By.xpath(excel.getData(3, 1622, 1)))).click().build().perform();
							try
							{
								//Check whether the Job Code Option is displayed or not
								if(driver.findElement(By.xpath(excel.getData(3, 1615, 1))).isDisplayed())
								{
									test.log(LogStatus.FAIL, "Job Code is displayed");
								}
							}
							catch(Exception e)
							{
								test.log(LogStatus.PASS, "Job Code is not displayed");
							}
						}
						else
						{
							test.log(LogStatus.FAIL, "Job Code Option is Disabled When user enter the required date for date range");
						}
						
						//Disable the required option from enable to disable Expected Tip Option
						if(driver.findElement(By.xpath(excel.getData(3, 1609, 1))).isSelected())
						{
							//Click the Enabled Expected Tip Option
							Actions act = new Actions(driver);
							act.moveToElement(driver.findElement(By.xpath(excel.getData(3, 1623, 1)))).click().build().perform();
							try
							{
								//Check whether the Expected Tip Option is displayed or not
								if(driver.findElement(By.xpath(excel.getData(3, 1616, 1))).isDisplayed())
								{
									test.log(LogStatus.FAIL, "Expected Tip is displayed");
								}
							}
							catch(Exception e)
							{
								test.log(LogStatus.PASS, "Expected Tip is not displayed");
							}
						}
						else
						{
							test.log(LogStatus.FAIL, "Expected Tip Option is Disabled When user enter the required date for date range");
						}
						
						//Disable the required option from enable to disable Non Cash Tip Option
						if(driver.findElement(By.xpath(excel.getData(3, 1610, 1))).isSelected())
						{
							//Click the Enabled Non Cash Tip Option
							Actions act = new Actions(driver);
							act.moveToElement(driver.findElement(By.xpath(excel.getData(3, 1624, 1)))).click().build().perform();
							try
							{
								//Check whether the Non Cash Tip Option is displayed or not
								if(driver.findElement(By.xpath(excel.getData(3, 1617, 1))).isDisplayed())
								{
									test.log(LogStatus.FAIL, "Non Cash Tip is displayed");
								}
							}
							catch(Exception e)
							{
								test.log(LogStatus.PASS, "Non Cash Tip is not displayed");
							}
						}
						else
						{
							test.log(LogStatus.FAIL, "Non Cash Tip Option is Disabled When user enter the required date for date range");
						}
						
						//Disable the required option from enable to disable Declared Cash Tip Option
						if(driver.findElement(By.xpath(excel.getData(3, 1611, 1))).isSelected())
						{
							//Click the Enabled Declared Cash Tip Option
							Actions act = new Actions(driver);
							act.moveToElement(driver.findElement(By.xpath(excel.getData(3, 1625, 1)))).click().build().perform();
							try
							{
								//Check whether the Declared Cash Tip Option is displayed or not
								if(driver.findElement(By.xpath(excel.getData(3, 1618, 1))).isDisplayed())
								{
									test.log(LogStatus.FAIL, "Declared Cash Tip is displayed");
								}
							}
							catch(Exception e)
							{
								test.log(LogStatus.PASS, "Declared Cash Tip is not displayed");
							}
						}
						else
						{
							test.log(LogStatus.FAIL, "Declared Cash Tip Option is Disabled When user enter the required date for date range");
						}
						
						//Disable the required option from enable to disable CheckIn Option
						if(driver.findElement(By.xpath(excel.getData(3, 1605, 1))).isSelected())
						{
							test.log(LogStatus.FAIL, "CheckIn Option is Enabled after user click(Disable) the CheckIn option");
						}
						else
						{
							//Click the Enabled CheckIn Option
							Actions act = new Actions(driver);
							act.moveToElement(driver.findElement(By.xpath(excel.getData(3, 1619, 1)))).click().build().perform();
							try
							{
								//Check whether the CheckIn Option is displayed or not
								if(driver.findElement(By.xpath(excel.getData(3, 1612, 1))).isDisplayed())
								{
									test.log(LogStatus.PASS, "CheckIn is displayed");
								}
							}
							catch(Exception e)
							{
								test.log(LogStatus.FAIL, "CheckIn is not displayed");
							}

						}
						
						//Disable the required option from enable to disable CheckOut Option
						if(driver.findElement(By.xpath(excel.getData(3, 1606, 1))).isSelected())
						{
							test.log(LogStatus.FAIL, "CheckOut Option is Enabled after user click(Disable) the CheckOut option");
						}
						else
						{

							//Click the Enabled CheckOut Option
							Actions act = new Actions(driver);
							act.moveToElement(driver.findElement(By.xpath(excel.getData(3, 1620, 1)))).click().build().perform();
							try
							{
								//Check whether the CheckOut Option is displayed or not
								if(driver.findElement(By.xpath(excel.getData(3, 1613, 1))).isDisplayed())
								{
									test.log(LogStatus.PASS, "CheckOut is displayed");
								}
							}
							catch(Exception e)
							{
								test.log(LogStatus.FAIL, "CheckOut is not displayed");
							}

						}
						
						//Enable the Hours Option if it is in disabled status
						if(driver.findElement(By.xpath(excel.getData(3, 1607, 1))).isSelected())
						{
							test.log(LogStatus.FAIL, "Hours Option is Enabled after user click(Disable) the Hours option");
						}
						else
						{
							//Click the Enabled Hours Option
							Actions act = new Actions(driver);
							act.moveToElement(driver.findElement(By.xpath(excel.getData(3, 1621, 1)))).click().build().perform();
							try
							{
								//Check whether the Hours Option is displayed or not
								if(driver.findElement(By.xpath(excel.getData(3, 1614, 1))).isDisplayed())
								{
									test.log(LogStatus.PASS, "Hours is displayed");
								}
							}
							catch(Exception e)
							{
								test.log(LogStatus.FAIL, "Hours is not displayed");
							}				
						}
												
						//Disable the required option from enable to disable Job Code Option
						if(driver.findElement(By.xpath(excel.getData(3, 1608, 1))).isSelected())
						{
							test.log(LogStatus.FAIL, "Job Code Option is Enabled after user click(Disable) the Job Code option");

						}
						else
						{

							//Click the Enabled Job Code Option
							Actions act = new Actions(driver);
							act.moveToElement(driver.findElement(By.xpath(excel.getData(3, 1622, 1)))).click().build().perform();
							try
							{
								//Check whether the Job Code Option is displayed or not
								if(driver.findElement(By.xpath(excel.getData(3, 1615, 1))).isDisplayed())
								{
									test.log(LogStatus.PASS, "Job Code is displayed");
								}
							}
							catch(Exception e)
							{
								test.log(LogStatus.FAIL, "Job Code is not displayed");
							}
						}
						
						//Disable the required option from enable to disable Expected Tip Option
						if(driver.findElement(By.xpath(excel.getData(3, 1609, 1))).isSelected())
						{
							test.log(LogStatus.FAIL, "Expected Tip Option is Enabled after user click(Disable) the Expected Tip option");

						}
						else
						{

							//Click the Enabled Expected Tip Option
							Actions act = new Actions(driver);
							act.moveToElement(driver.findElement(By.xpath(excel.getData(3, 1623, 1)))).click().build().perform();
							try
							{
								//Check whether the Expected Tip Option is displayed or not
								if(driver.findElement(By.xpath(excel.getData(3, 1616, 1))).isDisplayed())
								{
									test.log(LogStatus.PASS, "Expected Tip is displayed");
								}
							}
							catch(Exception e)
							{
								test.log(LogStatus.FAIL, "Expected Tip is not displayed");
							}
						}
						
						//Disable the required option from enable to disable Non Cash Tip Option
						if(driver.findElement(By.xpath(excel.getData(3, 1610, 1))).isSelected())
						{
							test.log(LogStatus.FAIL, "Non Cash Tip Option is Enabled after user click(Disable) the Non Cash Tip option");

						}
						else
						{

							//Click the Enabled Non Cash Tip Option
							Actions act = new Actions(driver);
							act.moveToElement(driver.findElement(By.xpath(excel.getData(3, 1624, 1)))).click().build().perform();
							try
							{
								//Check whether the Non Cash Tip Option is displayed or not
								if(driver.findElement(By.xpath(excel.getData(3, 1617, 1))).isDisplayed())
								{
									test.log(LogStatus.PASS, "Non Cash Tip is displayed");
								}
							}
							catch(Exception e)
							{
								test.log(LogStatus.FAIL, "Non Cash Tip is not displayed");
							}
						}
						
						//Disable the required option from enable to disable Declared Cash Tip Option
						if(driver.findElement(By.xpath(excel.getData(3, 1611, 1))).isSelected())
						{
							test.log(LogStatus.FAIL, "Declared Cash Tip Option is Enabled after user click(Disable) the Declared Cash Tip option");

						}
						else
						{

							//Click the Enabled Declared Cash Tip Option
							Actions act = new Actions(driver);
							act.moveToElement(driver.findElement(By.xpath(excel.getData(3, 1625, 1)))).click().build().perform();
							try
							{
								//Check whether the Declared Cash Tip Option is displayed or not
								if(driver.findElement(By.xpath(excel.getData(3, 1618, 1))).isDisplayed())
								{
									test.log(LogStatus.PASS, "Declared Cash Tip is displayed");
								}
							}
							catch(Exception e)
							{
								test.log(LogStatus.FAIL, "Declared Cash Tip is not displayed");
							}
						}
						
					}
					else
					{
						test.log(LogStatus.INFO, "Table Format Report is not available for the Required Date Range");
					}
					
				}
				else
				{
					test.log(LogStatus.FAIL, "All dynamic option is not selected");wb.close();
				}
			
			}

	}

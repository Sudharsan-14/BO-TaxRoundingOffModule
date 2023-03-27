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
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import epicList_Chrome.Utility;
import newReadExcelFile_New.ExcelDataConfig;

public class Verify_Employee_Attendance_Report {
	
	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Verify_Employee_Attendance_Report");
	
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
			Employee_Attendance_Report_Method_OpenPage(driver);
			Employee_Attendance_Report_Method_For_InTime(driver);
			Employee_Attendance_Report_Method_For_InHours(driver);
			Employee_Attendance_Report_Method_For_InTime_With_Dynamic_Report(driver);
			Employee_Attendance_Report_Method_For_InHours_With_Dynamic_Report(driver);
			Thread.sleep(1500);
		}

			@Test(priority=25,enabled=false)
			public void Employee_Attendance_Report_Method_OpenPage(WebDriver driver) throws Exception
			{
				File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			  
				/*//Click the Reports option
				driver.findElement(By.xpath("//span[.='Reports']")).click();
				// Create instance of Java script executor
				JavascriptExecutor je = (JavascriptExecutor) driver;
				//Identify the WebElement which will appear after scrolling down
				WebElement element = driver.findElement(By.xpath("//span[.='Employee']"));
				//Scroll the page till the Transaction option present
				je.executeScript("arguments[0].scrollIntoView(true);",element); 
		        //Click the Employee Option		
				driver.findElement(By.xpath("//span[.='Employee']")).click();*/
				
				Thread.sleep(3000);
				//Enter the Users URL
				driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"report/attendance");
				
				Thread.sleep(5000);
				try
				{
				//Check Attendance Report page opened or not
				if(driver.findElement(By.xpath(excel.getData(1, 1538, 1))).getText().equalsIgnoreCase("Attendance"))
				{
					test.log(LogStatus.PASS,"Attendance Report page loaded Successfully");
				
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));
				}
				else
				{
					test.log(LogStatus.FAIL,"Attendance Report page loaded Failed");
				
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
			
			@Test(priority=26,enabled=false)
			public void Employee_Attendance_Report_Method_For_InTime(WebDriver driver) throws Exception
			{

				File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				//Select the Employee option
			    driver.findElement(By.xpath(excel.getData(1, 1460, 1))).click();
			    //Enter the required Employee
			    driver.findElement(By.xpath(excel.getData(1, 1461, 1))).sendKeys("All");
			    //Press the Enter key
			    driver.findElement(By.xpath(excel.getData(1, 1461, 1))).sendKeys(Keys.ENTER);
	
			    
				  //Select the Specific date Time Period option
			    driver.findElement(By.xpath(excel.getData(1, 1539, 1))).click();
			    //Enter the required Period of time
			    driver.findElement(By.xpath(excel.getData(1, 1540, 1))).sendKeys("Date Range");
			    //Press the Enter key
			    driver.findElement(By.xpath(excel.getData(1, 1540, 1))).sendKeys(Keys.ENTER);
			   
			    
			    //Clear the Date field
			    driver.findElement(By.xpath(excel.getData(1, 1541, 1))).clear();
			    //Enter the Date
			    driver.findElement(By.xpath(excel.getData(1, 1541, 1))).sendKeys(Utility.getReportProperty("Date_Range_From"));
	
			   
			    //Clear the Date field
			    driver.findElement(By.xpath(excel.getData(1, 1542, 1))).clear();
			    //Enter the Date
			    driver.findElement(By.xpath(excel.getData(1, 1542, 1))).sendKeys(Utility.getReportProperty("Date_Range_To"));
	
			    //Select the Format option
			    driver.findElement(By.xpath(excel.getData(1, 1543, 1))).click();
			    //Enter the required Format
			    driver.findElement(By.xpath(excel.getData(1, 1544, 1))).sendKeys("In Time");
			    //Press the Enter key
			    driver.findElement(By.xpath(excel.getData(1, 1544, 1))).sendKeys(Keys.ENTER);
	
			    //Select the Format option
			    driver.findElement(By.xpath(excel.getData(1, 1545, 1))).click();
			    //Enter the required Format
			    driver.findElement(By.xpath(excel.getData(1, 1546, 1))).sendKeys("All");
			    //Press the Enter key
			    driver.findElement(By.xpath(excel.getData(1, 1546, 1))).sendKeys(Keys.ENTER);
			    
			    Thread.sleep(2000);
			  //Click the Run
			    driver.findElement(By.xpath(excel.getData(1, 572, 1))).click();
			    Thread.sleep(3000);
			     
	
				try
				{
					//Check weather the report is available for the selected time period
					if(driver.findElement(By.xpath(excel.getData(1, 704, 1))).isDisplayed())
					{
						test.log(LogStatus.FAIL, "Employee Attendance Report is not available for Specific Date");
					
						String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));

					
					}
				}
				catch(Exception ds)
				{
					
					test.log(LogStatus.PASS, "Employee Attendance Report is available for Specific Date");
					
					//Get the number of rows
					List<WebElement> rows = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/table/tbody/tr"));
					
					//Get the total hours value
					String hours = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/table/tbody/tr["+rows.size()+"]/td[5]")).getText();
					
					//Print the Total
					System.out.println("Total Hours for In Time is : "+hours);
					
					test.log(LogStatus.INFO, "Total Hours for In Time is : "+hours);wb.close();
				
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));

				}
			}
	
			@Test(priority=27,enabled=false)
			public void Employee_Attendance_Report_Method_For_InHours(WebDriver driver) throws Exception
			{
                File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				Thread.sleep(2000);
				//Select the Employee option
			    driver.findElement(By.xpath(excel.getData(1, 1460, 1))).click();
			    //Enter the required Employee
			    driver.findElement(By.xpath(excel.getData(1, 1461, 1))).sendKeys("All");
			    //Press the Enter key
			    driver.findElement(By.xpath(excel.getData(1, 1461, 1))).sendKeys(Keys.ENTER);
	
			    
				  //Select the Specific date Time Period option
			    driver.findElement(By.xpath(excel.getData(1, 1539, 1))).click();
			    //Enter the required Period of time
			    driver.findElement(By.xpath(excel.getData(1, 1540, 1))).sendKeys("Date Range");
			    //Press the Enter key
			    driver.findElement(By.xpath(excel.getData(1, 1540, 1))).sendKeys(Keys.ENTER);
			   
			    
			    //Clear the Date field
			    driver.findElement(By.xpath(excel.getData(1, 1541, 1))).clear();
			    //Enter the Date
			    driver.findElement(By.xpath(excel.getData(1, 1541, 1))).sendKeys(Utility.getReportProperty("Date_Range_From"));
	
			    
			    //Clear the Date field
			    driver.findElement(By.xpath(excel.getData(1, 1542, 1))).clear();
			    //Enter the Date
			    driver.findElement(By.xpath(excel.getData(1, 1542, 1))).sendKeys(Utility.getReportProperty("Date_Range_To"));
	
			    //Select the Format option
			    driver.findElement(By.xpath(excel.getData(1, 1543, 1))).click();
			    //Enter the required Format
			    driver.findElement(By.xpath(excel.getData(1, 1544, 1))).sendKeys("In Hours");
			    //Press the Enter key
			    driver.findElement(By.xpath(excel.getData(1, 1544, 1))).sendKeys(Keys.ENTER);
	
			    //Select the Format option
			    driver.findElement(By.xpath(excel.getData(1, 1545, 1))).click();
			    //Enter the required Format
			    driver.findElement(By.xpath(excel.getData(1, 1546, 1))).sendKeys("All");
			    //Press the Enter key
			    driver.findElement(By.xpath(excel.getData(1, 1546, 1))).sendKeys(Keys.ENTER);
	
			    Thread.sleep(3000);
			    //Click the Run
			    driver.findElement(By.xpath(excel.getData(1, 572, 1))).click();
			    
			    Thread.sleep(3000);
				try
				{
					//Check weather the report is available for the selected time period
					if(driver.findElement(By.xpath(excel.getData(1, 704, 1))).isDisplayed())
					{
						test.log(LogStatus.FAIL, "Employee Attendance Report is not available for Specific Date");
					
						String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));

					}
				}
				catch(Exception sd)
				{
					
					test.log(LogStatus.PASS, "Employee Attendance Report is available for Specific Date");
					
					//Get the number of rows
					List<WebElement> rows = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/table/tbody/tr"));
					
					//Get the total hours value
					String hours = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/table/tbody/tr["+rows.size()+"]/td[5]")).getText();
					
					//Print the Total
					System.out.println("Total Hours for In Hours is : "+hours);
					Thread.sleep(3000);
				     test.log(LogStatus.INFO, "Total Hours for In Hours is : "+hours);wb.close();

				 	String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));

				}
			}

			@Test(enabled=false,priority=26)
			public void Employee_Attendance_Report_Method_For_InTime_With_Dynamic_Report(WebDriver driver) throws Exception
			{
                File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				Thread.sleep(2000);
				//Select the Employee option
			    driver.findElement(By.xpath(excel.getData(1, 1460, 1))).click();
			    //Enter the required Employee
			    driver.findElement(By.xpath(excel.getData(1, 1461, 1))).sendKeys("All");
			    //Press the Enter key
			    driver.findElement(By.xpath(excel.getData(1, 1461, 1))).sendKeys(Keys.ENTER);
	
			    
				  //Select the Specific date Time Period option
			    driver.findElement(By.xpath(excel.getData(1, 1539, 1))).click();
			    //Enter the required Period of time
			    driver.findElement(By.xpath(excel.getData(1, 1540, 1))).sendKeys("Date Range");
			    //Press the Enter key
			    driver.findElement(By.xpath(excel.getData(1, 1540, 1))).sendKeys(Keys.ENTER);
			   
			    
			    //Clear the Date field
			    driver.findElement(By.xpath(excel.getData(1, 1541, 1))).clear();
			    //Enter the Date
			    driver.findElement(By.xpath(excel.getData(1, 1541, 1))).sendKeys(Utility.getReportProperty("Date_Range_From"));
	
			   
			    //Clear the Date field
			    driver.findElement(By.xpath(excel.getData(1, 1542, 1))).clear();
			    //Enter the Date
			    driver.findElement(By.xpath(excel.getData(1, 1542, 1))).sendKeys(Utility.getReportProperty("Date_Range_To"));
	
			    //Select the Format option
			    driver.findElement(By.xpath(excel.getData(1, 1543, 1))).click();
			    //Enter the required Format
			    driver.findElement(By.xpath(excel.getData(1, 1544, 1))).sendKeys("In Time");
			    //Press the Enter key
			    driver.findElement(By.xpath(excel.getData(1, 1544, 1))).sendKeys(Keys.ENTER);
	
			    //Select the Format option
			    driver.findElement(By.xpath(excel.getData(1, 1545, 1))).click();
			    //Enter the required Format
			    driver.findElement(By.xpath(excel.getData(1, 1546, 1))).sendKeys("All");
			    //Press the Enter key
			    driver.findElement(By.xpath(excel.getData(1, 1546, 1))).sendKeys(Keys.ENTER);
			    
			    Thread.sleep(2000);
			  //Click the Run
			    driver.findElement(By.xpath(excel.getData(1, 572, 1))).click();
			    Thread.sleep(5000);
			     
	
				//Check Whether all the field is selected or not
				if(driver.findElement(By.xpath("//span[contains(.,'Check In')]/../input")).isSelected()
						&&driver.findElement(By.xpath("//span[contains(.,'Check Out')]/../input")).isSelected()
						&&driver.findElement(By.xpath("//span[contains(.,'Hours')]/../input")).isSelected()
						&&driver.findElement(By.xpath("//span[contains(.,'Job Code')]/../input")).isSelected())
				{
					//Check weather the table format report is available or not
					if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']")).isDisplayed())
					{
						test.log(LogStatus.PASS, "Table Format Report is available for the Required Date Range");
						
						//Check whether all the filed is available or not
						if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'Check In')]")).isDisplayed()
								&&driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'Check Out')]")).isDisplayed()
								&&driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'Hours')]")).isDisplayed()
								&&driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'Job Code')]")).isDisplayed())
						{
							test.log(LogStatus.PASS, "All the Neccessary fields are displayed");
						
							String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
							String s="data:image/png;base64,"+scnShot;
							test.log(LogStatus.INFO,test.addScreenCapture(s));

						}
						else
						{
							test.log(LogStatus.FAIL, "Neccessary fields are not displayed");
						
							String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
							String s="data:image/png;base64,"+scnShot;
							test.log(LogStatus.INFO,test.addScreenCapture(s));

						}
						
						//Disable the required option from enable to disable Check In Option
						if(driver.findElement(By.xpath("//span[contains(.,'Check In')]/../input")).isSelected())
						{
							//Click the Enabled Check In Option
							Actions act = new Actions(driver);
							act.moveToElement(driver.findElement(By.xpath("//input/../span[contains(.,'Check In')]"))).click().build().perform();
							try
							{
								//Check whether the Check In Option is displayed or not
								if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'Check In')]")).isDisplayed())
								{
									test.log(LogStatus.FAIL, "Check In is displayed");
								
									String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
									String s="data:image/png;base64,"+scnShot;
									test.log(LogStatus.INFO,test.addScreenCapture(s));

								}
							}
							catch(Exception e)
							{
								test.log(LogStatus.PASS, "Check In is not displayed");
							
								String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
								String s="data:image/png;base64,"+scnShot;
								test.log(LogStatus.INFO,test.addScreenCapture(s));

							}
						}
						else
						{
							test.log(LogStatus.FAIL, "Check In Option is Disabled When user enter the required date for date range");
						}
						
						//Disable the required option from enable to disable Check Out Option
						if(driver.findElement(By.xpath("//span[contains(.,'Check Out')]/../input")).isSelected())
						{
							//Click the Enabled Check Out Option
							Actions act = new Actions(driver);
							act.moveToElement(driver.findElement(By.xpath("//input/../span[contains(.,'Check Out')]"))).click().build().perform();
							try
							{
								//Check whether the Check Out Option is displayed or not
								if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'Check Out')]")).isDisplayed())
								{
									test.log(LogStatus.FAIL, "Check Out is displayed");
								}
							}
							catch(Exception e)
							{
								test.log(LogStatus.PASS, "Check Out is not displayed");
								String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
								String s="data:image/png;base64,"+scnShot;
								test.log(LogStatus.INFO,test.addScreenCapture(s));

							}
						}
						else
						{
							test.log(LogStatus.FAIL, "Check Out Option is Disabled When user enter the required date for date range");
						}
						
						//Disable the required option from enable to disable Hours Option
						if(driver.findElement(By.xpath("//span[contains(.,'Hours')]/../input")).isSelected())
						{
							//Click the Enabled Hours Option
							Actions act = new Actions(driver);
							act.moveToElement(driver.findElement(By.xpath("//input/../span[contains(.,'Hours')]"))).click().build().perform();
							try
							{
								//Check whether the Hours Option is displayed or not
								if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'Hours')]")).isDisplayed())
								{
									test.log(LogStatus.FAIL, "Hours is displayed");
								}
							}
							catch(Exception e)
							{
								test.log(LogStatus.PASS, "Hours is not displayed");
								String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
								String s="data:image/png;base64,"+scnShot;
								test.log(LogStatus.INFO,test.addScreenCapture(s));

							}
						}
						else
						{
							test.log(LogStatus.FAIL, "Hours Option is Disabled When user enter the required date for date range");
						}
						
						//Disable the required option from enable to disable Job Code Option
						if(driver.findElement(By.xpath("//span[contains(.,'Job Code')]/../input")).isSelected())
						{
							//Click the Enabled Job Code Option
							Actions act = new Actions(driver);
							act.moveToElement(driver.findElement(By.xpath("//input/../span[contains(.,'Job Code')]"))).click().build().perform();
							try
							{
								//Check whether the Job Code Option is displayed or not
								if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'Job Code')]")).isDisplayed())
								{
									test.log(LogStatus.FAIL, "Job Code is displayed");
								}
							}
							catch(Exception e)
							{
								test.log(LogStatus.PASS, "Job Code is not displayed");
								String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
								String s="data:image/png;base64,"+scnShot;
								test.log(LogStatus.INFO,test.addScreenCapture(s));

							}
						}
						else
						{
							test.log(LogStatus.FAIL, "Job Code Option is Disabled When user enter the required date for date range");
						}
											
						//Enable the Check In Option if it is in disabled status
						if(driver.findElement(By.xpath("//span[contains(.,'Check In')]/../input")).isSelected())
						{
							test.log(LogStatus.FAIL, "Check In Option is Enabled after user click(Disable) the Check In option");
	
						}
						else
						{
							//Click the Enabled Check In Option
							Actions act = new Actions(driver);
							act.moveToElement(driver.findElement(By.xpath("//input/../span[contains(.,'Check In')]"))).click().build().perform();
							try
							{
								//Check whether the Check In Option is displayed or not
								if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'Check In')]")).isDisplayed())
								{
									test.log(LogStatus.PASS, "Check In is displayed");
								
									String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
									String s="data:image/png;base64,"+scnShot;
									test.log(LogStatus.INFO,test.addScreenCapture(s));

								}
							}
							catch(Exception e)
							{
								test.log(LogStatus.FAIL, "Check In is not displayed");
							}				
						}
						
						//Disable the required option from enable to disable Check Out Option
						if(driver.findElement(By.xpath("//span[contains(.,'Check Out')]/../input")).isSelected())
						{
							test.log(LogStatus.FAIL, "Check Out Option is Enabled after user click(Disable) the Check Out option");
						}
						else
						{
							//Click the Enabled Check Out Option
							Actions act = new Actions(driver);
							act.moveToElement(driver.findElement(By.xpath("//input/../span[contains(.,'Check Out')]"))).click().build().perform();
							try
							{
								//Check whether the Check Out Option is displayed or not
								if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'Check Out')]")).isDisplayed())
								{
									test.log(LogStatus.PASS, "Check Out is displayed");
								
									String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
									String s="data:image/png;base64,"+scnShot;
									test.log(LogStatus.INFO,test.addScreenCapture(s));

								}
							}
							catch(Exception e)
							{
								test.log(LogStatus.FAIL, "Check Out is not displayed");
							}
	
						}
						
						//Disable the required option from enable to disable Hours Option
						if(driver.findElement(By.xpath("//span[contains(.,'Hours')]/../input")).isSelected())
						{
							test.log(LogStatus.FAIL, "Hours Option is Enabled after user click(Disable) the Hours option");
						}
						else
						{
	
							//Click the Enabled Hours Option
							Actions act = new Actions(driver);
							act.moveToElement(driver.findElement(By.xpath("//input/../span[contains(.,'Hours')]"))).click().build().perform();
							try
							{
								//Check whether the Hours Option is displayed or not
								if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'Hours')]")).isDisplayed())
								{
									test.log(LogStatus.PASS, "Hours is displayed");
								
									String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
									String s="data:image/png;base64,"+scnShot;
									test.log(LogStatus.INFO,test.addScreenCapture(s));

								}
							}
							catch(Exception e)
							{
								test.log(LogStatus.FAIL, "Hours is not displayed");
							}
	
						}
						
						//Disable the required option from enable to disable Job Code Option
						if(driver.findElement(By.xpath("//span[contains(.,'Job Code')]/../input")).isSelected())
						{
							test.log(LogStatus.FAIL, "Job Code Option is Enabled after user click(Disable) the Job Code option");
	
						}
						else
						{
	
							//Click the Enabled Job Code Option
							Actions act = new Actions(driver);
							act.moveToElement(driver.findElement(By.xpath("//input/../span[contains(.,'Job Code')]"))).click().build().perform();
							try
							{
								//Check whether the Job Code Option is displayed or not
								if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'Job Code')]")).isDisplayed())
								{
									test.log(LogStatus.PASS, "Job Code is displayed");
								
									String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
									String s="data:image/png;base64,"+scnShot;
									test.log(LogStatus.INFO,test.addScreenCapture(s));

								}
							}
							catch(Exception e)
							{
								test.log(LogStatus.FAIL, "Job Code is not displayed");
							}
						}
					}
					else
					{
						test.log(LogStatus.FAIL, "Table Format Report is not available for the Required Date Range");
					
						String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));
					}
					
				}
				else
				{
					test.log(LogStatus.FAIL, "All dynamic option is not selected");wb.close();
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));
				}
			}
	
			@Test(enabled=false,priority=27)
			public void Employee_Attendance_Report_Method_For_InHours_With_Dynamic_Report(WebDriver driver) throws Exception
			{
                File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				Thread.sleep(2000);
				//Select the Employee option
			    driver.findElement(By.xpath(excel.getData(1, 1460, 1))).click();
			    //Enter the required Employee
			    driver.findElement(By.xpath(excel.getData(1, 1461, 1))).sendKeys("All");
			    //Press the Enter key
			    driver.findElement(By.xpath(excel.getData(1, 1461, 1))).sendKeys(Keys.ENTER);
	
			    
				  //Select the Specific date Time Period option
			    driver.findElement(By.xpath(excel.getData(1, 1539, 1))).click();
			    //Enter the required Period of time
			    driver.findElement(By.xpath(excel.getData(1, 1540, 1))).sendKeys("Date Range");
			    //Press the Enter key
			    driver.findElement(By.xpath(excel.getData(1, 1540, 1))).sendKeys(Keys.ENTER);
			   
			    
			    //Clear the Date field
			    driver.findElement(By.xpath(excel.getData(1, 1541, 1))).clear();
			    //Enter the Date
			    driver.findElement(By.xpath(excel.getData(1, 1541, 1))).sendKeys(Utility.getReportProperty("Date_Range_From"));
	
			    
			    //Clear the Date field
			    driver.findElement(By.xpath(excel.getData(1, 1542, 1))).clear();
			    //Enter the Date
			    driver.findElement(By.xpath(excel.getData(1, 1542, 1))).sendKeys(Utility.getReportProperty("Date_Range_To"));
	
			    //Select the Format option
			    driver.findElement(By.xpath(excel.getData(1, 1543, 1))).click();
			    //Enter the required Format
			    driver.findElement(By.xpath(excel.getData(1, 1544, 1))).sendKeys("In Hours");
			    //Press the Enter key
			    driver.findElement(By.xpath(excel.getData(1, 1544, 1))).sendKeys(Keys.ENTER);
	
			    //Select the Format option
			    driver.findElement(By.xpath(excel.getData(1, 1545, 1))).click();
			    //Enter the required Format
			    driver.findElement(By.xpath(excel.getData(1, 1546, 1))).sendKeys("All");
			    //Press the Enter key
			    driver.findElement(By.xpath(excel.getData(1, 1546, 1))).sendKeys(Keys.ENTER);
	
			    Thread.sleep(3000);
			    //Click the Run
			    driver.findElement(By.xpath(excel.getData(1, 572, 1))).click();
			    Thread.sleep(5000);
			     
	
				//Check Whether all the field is selected or not
				if(driver.findElement(By.xpath("//span[contains(.,'Check In')]/../input")).isSelected()
						&&driver.findElement(By.xpath("//span[contains(.,'Check Out')]/../input")).isSelected()
						&&driver.findElement(By.xpath("//span[contains(.,'Hours')]/../input")).isSelected()
						&&driver.findElement(By.xpath("//span[contains(.,'Job Code')]/../input")).isSelected())
				{
					//Check weather the table format report is available or not
					if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']")).isDisplayed())
					{
						test.log(LogStatus.PASS, "Table Format Report is available for the Required Date Range");
						
						//Check whether all the filed is available or not
						if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'Check In')]")).isDisplayed()
								&&driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'Check Out')]")).isDisplayed()
								&&driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'Hours')]")).isDisplayed()
								&&driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'Job Code')]")).isDisplayed())
						{
							test.log(LogStatus.PASS, "All the Neccessary fields are displayed");
							
							String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
							String s="data:image/png;base64,"+scnShot;
							test.log(LogStatus.INFO,test.addScreenCapture(s));
						}
						else
						{
							test.log(LogStatus.FAIL, "Neccessary fields are not displayed");
							
							String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
							String s="data:image/png;base64,"+scnShot;
							test.log(LogStatus.INFO,test.addScreenCapture(s));
						}
						
						//Disable the required option from enable to disable Check In Option
						if(driver.findElement(By.xpath("//span[contains(.,'Check In')]/../input")).isSelected())
						{
							//Click the Enabled Check In Option
							Actions act = new Actions(driver);
							act.moveToElement(driver.findElement(By.xpath("//input/../span[contains(.,'Check In')]"))).click().build().perform();
							try
							{
								//Check whether the Check In Option is displayed or not
								if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'Check In')]")).isDisplayed())
								{
									test.log(LogStatus.FAIL, "Check In is displayed");
								}
							}
							catch(Exception e)
							{
								test.log(LogStatus.PASS, "Check In is not displayed");
							}
						}
						else
						{
							test.log(LogStatus.FAIL, "Check In Option is Disabled When user enter the required date for date range");
						}
						
						//Disable the required option from enable to disable Check Out Option
						if(driver.findElement(By.xpath("//span[contains(.,'Check Out')]/../input")).isSelected())
						{
							//Click the Enabled Check Out Option
							Actions act = new Actions(driver);
							act.moveToElement(driver.findElement(By.xpath("//input/../span[contains(.,'Check Out')]"))).click().build().perform();
							try
							{
								//Check whether the Check Out Option is displayed or not
								if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'Check Out')]")).isDisplayed())
								{
									test.log(LogStatus.FAIL, "Check Out is displayed");
								}
							}
							catch(Exception e)
							{
								test.log(LogStatus.PASS, "Check Out is not displayed");
							}
						}
						else
						{
							test.log(LogStatus.FAIL, "Check Out Option is Disabled When user enter the required date for date range");
						}
						
						//Disable the required option from enable to disable Hours Option
						if(driver.findElement(By.xpath("//span[contains(.,'Hours')]/../input")).isSelected())
						{
							//Click the Enabled Hours Option
							Actions act = new Actions(driver);
							act.moveToElement(driver.findElement(By.xpath("//input/../span[contains(.,'Hours')]"))).click().build().perform();
							try
							{
								//Check whether the Hours Option is displayed or not
								if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'Hours')]")).isDisplayed())
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
						if(driver.findElement(By.xpath("//span[contains(.,'Job Code')]/../input")).isSelected())
						{
							//Click the Enabled Job Code Option
							Actions act = new Actions(driver);
							act.moveToElement(driver.findElement(By.xpath("//input/../span[contains(.,'Job Code')]"))).click().build().perform();
							try
							{
								//Check whether the Job Code Option is displayed or not
								if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'Job Code')]")).isDisplayed())
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
											
						//Enable the Check In Option if it is in disabled status
						if(driver.findElement(By.xpath("//span[contains(.,'Check In')]/../input")).isSelected())
						{
							test.log(LogStatus.FAIL, "Check In Option is Enabled after user click(Disable) the Check In option");
	
						}
						else
						{
							//Click the Enabled Check In Option
							Actions act = new Actions(driver);
							act.moveToElement(driver.findElement(By.xpath("//input/../span[contains(.,'Check In')]"))).click().build().perform();
							try
							{
								//Check whether the Check In Option is displayed or not
								if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'Check In')]")).isDisplayed())
								{
									test.log(LogStatus.PASS, "Check In is displayed");
								}
							}
							catch(Exception e)
							{
								test.log(LogStatus.FAIL, "Check In is not displayed");
							}				
						}
						
						//Disable the required option from enable to disable Check Out Option
						if(driver.findElement(By.xpath("//span[contains(.,'Check Out')]/../input")).isSelected())
						{
							test.log(LogStatus.FAIL, "Check Out Option is Enabled after user click(Disable) the Check Out option");
						}
						else
						{
							//Click the Enabled Check Out Option
							Actions act = new Actions(driver);
							act.moveToElement(driver.findElement(By.xpath("//input/../span[contains(.,'Check Out')]"))).click().build().perform();
							try
							{
								//Check whether the Check Out Option is displayed or not
								if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'Check Out')]")).isDisplayed())
								{
									test.log(LogStatus.PASS, "Check Out is displayed");
								}
							}
							catch(Exception e)
							{
								test.log(LogStatus.FAIL, "Check Out is not displayed");
							}
	
						}
						
						//Disable the required option from enable to disable Hours Option
						if(driver.findElement(By.xpath("//span[contains(.,'Hours')]/../input")).isSelected())
						{
							test.log(LogStatus.FAIL, "Hours Option is Enabled after user click(Disable) the Hours option");
						}
						else
						{
	
							//Click the Enabled Hours Option
							Actions act = new Actions(driver);
							act.moveToElement(driver.findElement(By.xpath("//input/../span[contains(.,'Hours')]"))).click().build().perform();
							try
							{
								//Check whether the Hours Option is displayed or not
								if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'Hours')]")).isDisplayed())
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
						if(driver.findElement(By.xpath("//span[contains(.,'Job Code')]/../input")).isSelected())
						{
							test.log(LogStatus.FAIL, "Job Code Option is Enabled after user click(Disable) the Job Code option");
	
						}
						else
						{
	
							//Click the Enabled Job Code Option
							Actions act = new Actions(driver);
							act.moveToElement(driver.findElement(By.xpath("//input/../span[contains(.,'Job Code')]"))).click().build().perform();
							try
							{
								//Check whether the Job Code Option is displayed or not
								if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'Job Code')]")).isDisplayed())
								{
									test.log(LogStatus.PASS, "Job Code is displayed");
							
								}
							}
							catch(Exception e)
							{
								test.log(LogStatus.FAIL, "Job Code is not displayed");
							}
						}
					}
					else
					{
						test.log(LogStatus.FAIL, "Table Format Report is not available for the Required Date Range");
					}
					
				}
				else
				{
					test.log(LogStatus.FAIL, "All dynamic option is not selected");wb.close();
				}
			}

}
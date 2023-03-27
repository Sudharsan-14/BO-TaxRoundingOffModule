package epicList_Chrome_Xpath;

import java.io.File;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import newReadExcelFile_New.ExcelDataConfig;



public class Punched_In_Out {
	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Punched_In_Out");

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
				Usermanagement_ForcedPunchInOut_OpenPage(driver);
				Usermanagement_ForcedPunchInOut_addTimeEvent(driver);
				Usermanagement_ForcedPunchInOut_cancelButton_punchin_out(driver);
				Usermanagement_ForcedPunchInOut_edit_Employee_clock_in(driver);
				Usermanagement_ForcedPunchInOut_DeleteEmplyoee_Clock_In(driver);
				Thread.sleep(1500);
			}

		@Test(enabled=false,priority=24)
		public void Usermanagement_ForcedPunchInOut_OpenPage(WebDriver driver) throws Exception
					{
			File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		  
						
						//Click the User Management option
						//driver.findElement(By.xpath("//span[.='User Management']")).click();
						
						Thread.sleep(4000);
						//Enter the Punchin-out URL
						driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"employeeList");
						
						Thread.sleep(3000);
						//Check  Forced Punch-In/Out page opened or not
						if(driver.findElement(By.xpath(excel.getData(3, 621, 1))).getText().equalsIgnoreCase("Forced Punch-In/Out(s)"))
						{
							test.log(LogStatus.PASS,"Forced Punch-In/Out page loaded Successfully");
						}
						else
						{
							test.log(LogStatus.FAIL,"Forced Punch-In/Out page loaded Failed");
						}
						
					
						
						Thread.sleep(3000);
						//Click the Employee option
						driver.findElement(By.xpath(excel.getData(3, 622, 1))).click();
						//Enter the required Employee     
						driver.findElement(By.xpath(excel.getData(3, 623, 1))).sendKeys(Utility.getReportProperty("Punchin/out_name"));
						//Press the Enter Key              
						driver.findElement(By.xpath(excel.getData(3, 623, 1))).sendKeys(Keys.ENTER);
						//Clear the Date field
						driver.findElement(By.xpath(excel.getData(3, 624, 1))).clear();
						Thread.sleep(3000);
						//Enter the required date to view the clock In/Out
						driver.findElement(By.xpath(excel.getData(3, 625, 1))).sendKeys(Utility.getReportProperty("Punchin/out_Date"));
					
					    //Click the Run
					    driver.findElement(By.xpath(excel.getData(2, 42, 1))).click();
					    Thread.sleep(3000);

					    //Check whether the Forced Punch-In report is available or not		    
					    if(driver.findElement(By.xpath(excel.getData(3, 626, 1))).getText().equalsIgnoreCase("IN"))
					    {
					    	test.log(LogStatus.PASS,"Forced Punch-In report is available for the selected date");
					    	
					    }
					    else
					    {
					    	test.log(LogStatus.FAIL,"Forced Punch-In report is not available for the selected date");wb.close();
					    }
			
					}
					
		@Test(enabled=false,priority=25)
		public void Usermanagement_ForcedPunchInOut_addTimeEvent(WebDriver driver) throws Exception
					{      
            File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
						Thread.sleep(3000);
						//click the time event or create the time event
						driver.findElement(By.xpath(excel.getData(3, 627, 1))).click();
						Thread.sleep(3000); 	
						//check new time event can able to create or not
						if (driver.findElement(By.xpath(excel.getData(3, 628, 1))).getText().equalsIgnoreCase("New Clock-In Event"))
						
							{
								test.log(LogStatus.PASS,"Time event loaded Successfully");
							}
							else
							{
								test.log(LogStatus.FAIL,"Time event loading Failed");
							}
						Thread.sleep(3000);
						driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
						//click on roles option  	 
						driver.findElement(By.xpath(excel.getData(3, 629, 1))).click();
						
						Thread.sleep(3000);
						//Clear the Search field		
						driver.findElement(By.xpath(excel.getData(3, 630, 1))).clear();
						
						
						//Search the required roles from the below list
						//driver.findElement(By.xpath("//form[@name='employeeClockInOutForm']/div[1]/div[1]/div/div/div/div/input")).sendKeys();
									
						//PRESS THE ENTER KEY
						driver.findElement(By.xpath(excel.getData(3, 631, 1))).sendKeys(Keys.ENTER);
					
						//Clear the check in date field 
						driver.findElement(By.name(excel.getData(3, 632, 3))).clear();		
						//Enter the check in date field
						driver.findElement(By.name(excel.getData(3, 632, 3))).sendKeys(Utility.getReportProperty("Punchin/out_Date"));

						Thread.sleep(3000);
						//Click the up arrow(minute) of Check in Time
						Thread.sleep(3000);
							driver.findElement(By.xpath(excel.getData(3, 633, 1))).click();

						
						//Clear the check out date field 
						driver.findElement(By.name(excel.getData(3, 634, 3))).clear();		
						//Enter the check out date field
						driver.findElement(By.name(excel.getData(3, 634, 3))).sendKeys(Utility.getReportProperty("Punchin/out_Date"));
										
						Thread.sleep(3000);
						//Click the up arrow(minute) of Check Out Time
						Thread.sleep(3000);
							driver.findElement(By.xpath(excel.getData(3, 635, 1))).click();
							Thread.sleep(3000);

							//Check it is AM or PM in the Start Time Option
				          	if(driver.findElement(By.xpath(excel.getData(3, 636, 1))).getText().equalsIgnoreCase("AM"))
				          {
				          Thread.sleep(3000);  
				          //Click the AM or PM option in the End Time
				          	driver.findElement(By.xpath(excel.getData(3, 637, 1))).click();
				          }
				          else
				          {
				          Thread.sleep(3000);
				          //Click the AM or PM option in the Start Time
				          	driver.findElement(By.xpath(excel.getData(3, 636, 1))).click();
				         }
				          
				         Thread.sleep(2000);
				         
						//Click the save button
							driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
						
							Thread.sleep(2000);
						
						//Check whether time event saved successfully or not
						if(driver.findElement(By.xpath(excel.getData(3, 638, 1))).getText().equalsIgnoreCase("Forced Punch-In/Out(s)"))
						{
							test.log(LogStatus.PASS,"Time Event Saved Successfully");
						}
						else
						{
							test.log(LogStatus.FAIL,"Time Event failed to save");
						}
						Thread.sleep(5000);wb.close();
					}
					
		@Test(enabled=false,priority=26)
		public void Usermanagement_ForcedPunchInOut_cancelButton_punchin_out(WebDriver driver) throws Exception
					{
File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
						Thread.sleep(5000);
						//click the time event or create the time event
						driver.findElement(By.xpath(excel.getData(3, 639, 1))).click();
						Thread.sleep(3000);
						//check new time event page is loaded or not
						if (driver.findElement(By.xpath(excel.getData(3, 640, 1))).getText().equalsIgnoreCase("New Clock-In Event"))
						
						{
					    	test.log(LogStatus.PASS,"New Clock-In Event page loaded succesfully");
					    	
					    }
					    else
					    {
					    	test.log(LogStatus.FAIL,"New Clock-In Event page loaded failed");
					    }
						Thread.sleep(3000);
							//Click the cancel button
							driver.findElement(By.xpath(excel.getData(2, 43, 1))).click();
							Thread.sleep(3000);
							//Check Whether time event table cancelled successfully or not
							if(driver.findElement(By.xpath(excel.getData(3, 638, 1))).getText().equalsIgnoreCase("Forced Punch-In/Out(s)"))
							{
								test.log(LogStatus.PASS,"Time Event cancelled successfully");
						    	
						    }
						    else
						    {
						    	test.log(LogStatus.FAIL,"Time Event cancellation failed");
							}
							Thread.sleep(3000);wb.close();
						}
					
		@Test(enabled=false,priority=27)
		public void Usermanagement_ForcedPunchInOut_edit_Employee_clock_in (WebDriver driver) throws Exception
					{
File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
							Thread.sleep(3000);
							//Click the edit icon
							Thread.sleep(2000);driver.findElement(By.cssSelector(excel.getData(3, 41, 4))).click();Thread.sleep(2000);
							Thread.sleep(3000);
							
							DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyy");
							Date date = new Date();
							
							//Clear the check in date field 
							driver.findElement(By.name(excel.getData(3, 632, 3))).clear();		
							//Enter the check in date field
							driver.findElement(By.name(excel.getData(3, 632, 3))).sendKeys(dateFormat.format(date));
							
							Thread.sleep(3000);
							//Click the up arrow(minute) of Check in Time
							Thread.sleep(3000);
								driver.findElement(By.xpath(excel.getData(3, 633, 1))).click();
							
							//Clear the check out date field 
							driver.findElement(By.name(excel.getData(3, 634, 3))).clear();		
							//Enter the check out date field
							driver.findElement(By.name(excel.getData(3, 634, 3))).sendKeys(dateFormat.format(date));
							
							Thread.sleep(3000);
							//Click the up arrow(minute) of Check Out Time
							Thread.sleep(3000);
								driver.findElement(By.xpath(excel.getData(3, 635, 1))).click();

					    		//Check it is AM or PM in the Start Time Option
					          	if(driver.findElement(By.xpath(excel.getData(3, 636, 1))).getText().equalsIgnoreCase("PM") && driver.findElement(By.xpath("//form[@name='employeeClockInOutForm']/div[2]/div[2]/div/div/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
					          {
					          Thread.sleep(3000);
					          //Click the AM or PM option in the End Time
					        	driver.findElement(By.xpath(excel.getData(3, 637, 1))).click();

					          
					          Thread.sleep(3000);
					          //Click the AM or PM option in the Start Time
					          	driver.findElement(By.xpath(excel.getData(3, 636, 1))).click();

					          }
							
							//Click the update button
							driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
							Thread.sleep(3000);

							//Check whether the time-event was updated successfully or not
							if(driver.findElement(By.xpath(excel.getData(3, 638, 1))).getText().equalsIgnoreCase("Forced Punch-In/Out(s)"))
							{
								test.log(LogStatus.PASS,"details updated successfully");
							}
							else
							{
								test.log(LogStatus.FAIL,"details updated successfully is failed");wb.close();
							}
					}
							
		@Test(enabled=false,priority=28)
		public void Usermanagement_ForcedPunchInOut_DeleteEmplyoee_Clock_In(WebDriver driver) throws Exception

							{
File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
								Thread.sleep(3000);
								//Click the Delete button
								driver.findElement(By.cssSelector(excel.getData(3, 42, 4))).click();
								Thread.sleep(3000);
			
								//Check the ServingSizeLevel deleted or not
								if(driver.findElement(By.xpath(excel.getData(3, 638, 1))).getText().equalsIgnoreCase("Forced Punch-In/Out(s)"))
								{
									test.log(LogStatus.PASS,"ClockIn-Out record removed sucessfully");
								}
								else
								{
									test.log(LogStatus.FAIL,"ClockIn-Out record removed sucessfully is Failed");
								}
								Thread.sleep(3000);wb.close();
					}


}

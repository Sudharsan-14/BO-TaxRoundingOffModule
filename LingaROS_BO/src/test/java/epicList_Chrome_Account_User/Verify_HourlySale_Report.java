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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import epicList_Chrome_Account_User.Utility;
import newReadExcelFile_New.ExcelDataConfig;

public class Verify_HourlySale_Report {

	public WebDriver driver;
	
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Verify_HourlySale_Report");

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
			Hourly_Sale_method_openpage_Sale_Report(driver);
			Hourly_Sale_method_Report_For_Specific_Date(driver);
			Hourly_Sale_method_Report_For_Specific_Date_With_Dynamic_Report(driver);
			Thread.sleep(1500);
		}
	
			@Test(priority=100,enabled = false)
			public void Hourly_Sale_method_openpage_Sale_Report(WebDriver driver) throws Exception
			{
				/*//Click the Reports option
				driver.findElement(By.xpath("//span[.='Reports']")).click();
				// Create instance of Java script executor
				JavascriptExecutor je = (JavascriptExecutor) driver;
				//Identify the WebElement which will appear after scrolling down
				WebElement element = driver.findElement(By.xpath("//span[.='Sale']"));
				//Scroll the page till the Sale option present
				je.executeScript("arguments[0].scrollIntoView(true);",element); 
		        //Click the Sale Option		
				driver.findElement(By.xpath("//span[.='Sale']")).click(); */
			
				 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
					
					FileInputStream fis = new FileInputStream(src);
					
					XSSFWorkbook wb = new XSSFWorkbook(fis);
					
					XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
					
					ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

				Thread.sleep(3000);
				//Enter the Categories Url
				driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id1")+"report/hourlySale");
				
				Thread.sleep(3000);
				try
				{
				//Check weather the Modifier Sale Report page is loaded or not
				if(driver.findElement(By.xpath(excel.getData(3, 731, 1))).getText().equalsIgnoreCase("Hourly Sale"))
				{
					test.log(LogStatus.PASS, "Hourly Sale Report page loaded successfully");
				
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));
				}
				else
				{
					test.log(LogStatus.FAIL, "Hourly Sale Report page loaded fail");
				
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));
				}
				}
				catch(Exception e)
				{
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));
				}
				wb.close();
				Thread.sleep(3000);

			}
			
			@Test(priority=101,enabled = false)
			public void Hourly_Sale_method_Report_For_Specific_Date(WebDriver driver) throws Exception
			{
				 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
					
					FileInputStream fis = new FileInputStream(src);
					
					XSSFWorkbook wb = new XSSFWorkbook(fis);
					
					XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
					
					ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

				Thread.sleep(2000);
				WebElement html = driver.findElement(By.tagName("html"));
				html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
				html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
				html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
				html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		
				Thread.sleep(5000);
				//Click the Time Period option
				driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/a")).click();
				//Enter the required Time Period
				driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/div/div/input")).sendKeys("Date Range");
				//Press the Enter Key
				driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/div/div/input")).sendKeys(Keys.ENTER);
				
				Thread.sleep(2000);
				//Clear the date field
				driver.findElement(By.xpath(excel.getData(3, 1075, 1))).clear();
				Thread.sleep(2000);
				//Enter the date
				driver.findElement(By.xpath(excel.getData(3, 1075, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
		
				Thread.sleep(2000);
				//Clear the date field
				driver.findElement(By.xpath(excel.getData(3, 1076, 1))).clear();
				Thread.sleep(2000);
				//Enter the date
				driver.findElement(By.xpath(excel.getData(3, 1076, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
				
				Thread.sleep(2000);
				//Click the Run Button
				driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
				
				long start = System.currentTimeMillis();
				
				Thread.sleep(2000);
					//Check weather the report is available for the selected time period
				if(driver.findElement(By.xpath(excel.getData(3, 704, 1))).isDisplayed())
					{
						test.log(LogStatus.FAIL, "Hourly Sale Report is not available for Specific Date");
					}
					else
					{
					test.log(LogStatus.PASS, "Hourly Sale Report available for Specific Date");
					
					
					WebDriverWait wait1=new WebDriverWait(driver, 30);
					WebElement ele11=driver.findElement(By.xpath(excel.getData(3, 705, 1)));
					
					//Check Weather the Top 5 hourly sale available or not
					if(wait1.until(ExpectedConditions.visibilityOf(ele11)).isDisplayed())
					{
						test.log(LogStatus.PASS, "Here Top 5 Hourly Sale Report available for Specific Date");
						
						long finish = System.currentTimeMillis();
						long totalTime = finish- start; 
						System.out.println("Time in Millisecomds : "+totalTime);
						double ActualfinishTime = TimeUnit.MILLISECONDS.toMinutes(totalTime);
//						long ActualfinishTimeDouble = ((totalTime%86400)%3600)/60;
						long ActualfinishTimeDouble1 = (totalTime/1000)/6;
						System.out.println("MilSEc to Mins basic conversion : "+ActualfinishTimeDouble1);
						test.log(LogStatus.INFO, "Time in Millisecomds : "+totalTime);
						
						Thread.sleep(2000);
						//Click the down arrow button of chart type
						driver.findElement(By.xpath(excel.getData(3, 706, 1))).click();
						Thread.sleep(1000);
						
						//Click the Pie chart option
						driver.findElement(By.xpath(excel.getData(3, 707, 1))).click();
						Thread.sleep(2000);
						test.log(LogStatus.INFO, "There is Pie Chart available");
						Thread.sleep(3000);
						
						Thread.sleep(2000);
						//Click the down arrow button of chart type
						driver.findElement(By.xpath(excel.getData(3, 706, 1))).click();
						Thread.sleep(1000);
		
						//Click the Polar Area chart option
						driver.findElement(By.xpath(excel.getData(3, 708, 1))).click();
						Thread.sleep(2000);
						test.log(LogStatus.INFO, "There is Polar Area Chart available");
						Thread.sleep(3000);
						
						Thread.sleep(2000);
						//Click the down arrow button of chart type
						driver.findElement(By.xpath(excel.getData(3, 706, 1))).click();
						Thread.sleep(1000);
		
						//Click the Bar chart option
						driver.findElement(By.xpath(excel.getData(3, 709, 1))).click();
						Thread.sleep(2000);
						test.log(LogStatus.INFO, "There is Bar Chart available");
						Thread.sleep(3000);
					}
					else
					{
						test.log(LogStatus.FAIL, "Here Top 5 Hourly Sale Report not available for Specific Date");
					}
					
					Thread.sleep(5000);
					//Check weather the report graph is available or not
					if(driver.findElement(By.xpath(excel.getData(3, 710, 1))).isDisplayed())
					{
						test.log(LogStatus.PASS, "There is Graphical type of Hourly sale report is available for Specific Date");
					}
					else
					{
						test.log(LogStatus.FAIL, "Graphical report is not available for Specific Date");
					}
					
					Thread.sleep(5000);
					//Check weather the table format report is available or not
					if(driver.findElement(By.xpath(excel.getData(3, 711, 2))).isDisplayed())
					{
						test.log(LogStatus.PASS, "Table Format Report is available for Specific Date");
						
					//	List<WebElement> lk=driver.findElements(By.xpath("//ul[@class='pagination pull-right ng-scope']/li/a[1][@ng-switch-when='last']/span[1]"));
						try {
							driver.findElement(By.xpath("//li/a[@ng-switch-when='last']/span")).click();
						} catch (Exception e) {
							// Auto-generated catch block
							
						}
						
						Thread.sleep(3000);
				        //No.of rows
				        List<WebElement> rows = driver.findElements(By.xpath(excel.getData(3, 1565, 1)));
				        
		/*		        //Print number of Rows
				        System.out.println("Number of Rows are : "+rows);*/
				        
						//Replace all commo's with empty space
						String expected1 = Utility.getReportPropertyUser("Sale_Report_Net_Sale").replace(",", "");
						
						//Convert the String value of the Sale_Report_Net_Sale element into float value
						float expect1 = Float.parseFloat(expected1); 
		
						//Replace all commo's with empty space
						String expected2 = Utility.getReportPropertyUser("Sale_Report_Tax").replace(",", "");
						
						//Convert the String value of the Sale_Report_Tax element into float value
						float expect2 = Float.parseFloat(expected2);  
		
						//Replace all commo's with empty space
						String expected3 = Utility.getReportPropertyUser("Sale_Report_Discount").replace(",", "");
						
						//Convert the String value of the Sale_Report_Discount element into float value
						float expect3 = Float.parseFloat(expected3);     	
		
						//Replace all commo's with empty space
						String expected4 = Utility.getReportPropertyUser("Sale_Report_Grand_Sale").replace(",", "");
								
						//Convert the String value of the Sale_Report_Grand_Sale element into float value
						float expect4= Float.parseFloat(expected4);  
				        
			        	//Replace all commo's with empty space
			        	String expected5 = Utility.getReportPropertyUser("Hourly_Sale_Report_Number_Of_Customer").replace(",", "");
			        	
			        	//Convert the String value of the Hourly_Sale_Report_Number_Of_Customer element into float value
			        	float expect5 = Float.parseFloat(expected5);  
				        
				        //Check weather the Net Sale is correct or not
				        if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[3]")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Net_Sale")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Hourly Sale reports are same for Sale Amount");
				        	
				        	//Get the Total value of Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[3]")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Net Sale Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Net Sale Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Net Sale Value is : "+ actual);
				        }
				        
						else if(expect1 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Net Sale
				        	String actualText = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[3]")).getText();
		
				        	System.out.println("The Actual Net Sale value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Net Sale value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Hourly Sale reports are different for Sale Amount");
				        	
				        	//Get the Total value of Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[3]")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the net sale amount Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        			        	
				        	//Get the different
				        	float different = actual - expect1;
				        	
				        	//Print the different value
				        	System.out.println("Net Sale Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Net Sale Value different is : "+different);	
				        }
				        
				        //Check weather the Tax Report is correct or not
				        if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[4]")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Tax")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Hourly Sale reports are same for Tax");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[4]")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Tax Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Tax Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Tax Value is : "+ actual);
				        }
				        
						else if(expect2 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Tax
				        	String actualText = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[4]")).getText();
		
				        	System.out.println("The Actual Tax value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Tax value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Hourly Sale reports are different for Tax");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[4]")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Tax Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect2;
				        	
				        	//Print the different value
				        	System.out.println("Tax Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Tax Value different is : "+different);	
				        }
				        
				        
				        //Check weather the Discount Report is correct or not
				        if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[6]")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Discount")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Hourly Sale reports are same for Discount");
				        	
				        	//Get the Total value of Discount
				        	String actualText1 = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[6]")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Discount Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Discount Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Discount Value is : "+ actual);
				        }
				        
						else if(expect3 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Discount
				        	String actualText = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[6]")).getText();
		
				        	System.out.println("The Actual Discount value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Discount value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Hourly Sale reports are different for Discount");
				        	
				        	//Get the Total value of Discount
				        	String actualText1 = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[6]")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Discount Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect3;
				        	
				        	//Print the different value
				        	System.out.println("Discount Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Discount Value different is : "+different);	
				        }
				        
				        
				        //Check weather the Grand Sale Report is correct or not
				        if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[7]")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Grand_Sale")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Hourly Sale reports are same for Grand Sale");
				        	
				        	//Get the Total value of Grand Sale
				        	String actualText1 = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[7]")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");   	
		
				        	//Convert the String value of the Grand Sale Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Grand Sale Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Grand Sale Value is : "+ actual);
				        }
				        
						else if(expect4 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Sale Report
				        	String actualText = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[7]")).getText();
		
				        	System.out.println("The Actual Grand Sale value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Grand Sale value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Hourly Sale reports are different for Grand Sale");
				        	
				        	//Get the Total value of Grand Sale
				        	String actualText1 = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[7]")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Grand Sale Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect4;
				        	
				        	//Print the different value
				        	System.out.println("Grand Sale Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Grand Sale Value different is : "+different);	
				        }
				        
				        
				        //Check weather the Number of Customer Report is correct or not
				        if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[8]")).getText().equals(Utility.getReportPropertyUser("Hourly_Sale_Report_Number_Of_Customer")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Hourly Sale reports are same for Number of Customer");
				        	
				        	//Get the Total value of Number of Customer
				        	String actualText1 = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[8]")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Number of Customer Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Number of Customer Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Number of Customer Value is : "+ actual);
				        }
				        
						else if(expect5 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Number of Customer
				        	String actualText = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[8]")).getText();
		
				        	System.out.println("The Actual Number of Customer value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Number of Customer value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Hourly Sale reports are different for Number of Customer");
				        	
				        	//Get the Total value of Number of Customer
				        	String actualText1 = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[8]")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Number of Customer Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect5;
				        	
				        	//Print the different value
				        	System.out.println("Number of Customer Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Number of Customer Value different is : "+different);	
				        }
				        
				        driver.findElement(By.tagName("html")).sendKeys(Keys.END);
				        
				        String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));

				}
					else
					{
						test.log(LogStatus.FAIL, "Table Format Report is not available for Specific Date");
					
						String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));

					}
				}wb.close();
				driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			}
		
			@Test(priority=102,enabled = false)
			public void Hourly_Sale_method_Report_For_Specific_Date_With_Dynamic_Report(WebDriver driver) throws Exception
		{
				 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
					
					FileInputStream fis = new FileInputStream(src);
					
					XSSFWorkbook wb = new XSSFWorkbook(fis);
					
					XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
					
					ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

				Thread.sleep(2000);
			WebElement html = driver.findElement(By.tagName("html"));
			html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
			html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
			html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
			html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
	
			Thread.sleep(3000);
			//Click the Time Period option
			driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/a")).click();
			//Enter the required Time Period
			driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/div/div/input")).sendKeys("Date Range");
			//Press the Enter Key
			driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/div/div/input")).sendKeys(Keys.ENTER);
			
			Thread.sleep(2000);
			//Clear the date field
			driver.findElement(By.xpath(excel.getData(3, 1075, 1))).clear();
			//Enter the date
			driver.findElement(By.xpath(excel.getData(3, 1075, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
	
			Thread.sleep(2000);
			//Clear the date field
			driver.findElement(By.xpath(excel.getData(3, 1076, 1))).clear();
			//Enter the date
			driver.findElement(By.xpath(excel.getData(3, 1076, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
			
			Thread.sleep(2000);
			//Click the Run Button
			driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
			
			long start = System.currentTimeMillis();
			
			Thread.sleep(3000);
			for(int i = 1; i <= 8; i++)
			{
				driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
			}
			
			try
			{
				
				if(driver.findElement(By.xpath(excel.getData(3, 704, 1))).isDisplayed())
				{
					test.log(LogStatus.FAIL, "Hourly Sale Report is not available for the Required Date Range");
				
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));
				}
			}
			catch(Exception gf)
			{
				//Check Whether all the field is selected or not
				if(driver.findElement(By.xpath(excel.getData(3, 1083, 1))).isSelected()
						&&driver.findElement(By.xpath(excel.getData(3, 1084, 1))).isSelected()
						&&driver.findElement(By.xpath(excel.getData(3, 1085, 1))).isSelected()
						&&driver.findElement(By.xpath(excel.getData(3, 1086, 1))).isSelected()
						&&driver.findElement(By.xpath(excel.getData(3, 1549, 1))).isSelected())
				{
					WebDriverWait wait1=new WebDriverWait(driver, 30);
					WebElement ele11=driver.findElement(By.xpath(excel.getData(3, 711, 2)));
					
					//Check weather the table format report is available or not
					if(wait1.until(ExpectedConditions.visibilityOf(ele11)).isDisplayed())
					{
						test.log(LogStatus.PASS, "Table Format Report is available for the Required Date Range");
						
						long finish = System.currentTimeMillis();
						long totalTime = finish- start; 
						System.out.println("Time in Millisecomds : "+totalTime);
						double ActualfinishTime = TimeUnit.MILLISECONDS.toMinutes(totalTime);
//						long ActualfinishTimeDouble = ((totalTime%86400)%3600)/60;
						long ActualfinishTimeDouble1 = (totalTime/1000)/6;
						System.out.println("MilSEc to Mins basic conversion : "+ActualfinishTimeDouble1);
						test.log(LogStatus.INFO, "Time in Millisecomds : "+totalTime);
						
						String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));
						
						//Check whether all the filed is available or not
						if(driver.findElement(By.xpath(excel.getData(3, 1550, 1))).isDisplayed()
								&&driver.findElement(By.xpath(excel.getData(3, 1551, 1))).isDisplayed()
								&&driver.findElement(By.xpath(excel.getData(3, 1552, 1))).isDisplayed()
								&&driver.findElement(By.xpath(excel.getData(3, 1553, 1))).isDisplayed()
								&&driver.findElement(By.xpath(excel.getData(3, 1554, 1))).isDisplayed())
						{
							test.log(LogStatus.PASS, "All the Neccessary fields are displayed");
						}
						else
						{
							test.log(LogStatus.FAIL, "Neccessary fields are not displayed");
						}
						
						//Change the option from enable to Disable the Net Sale Option
						if(driver.findElement(By.xpath(excel.getData(3, 1083, 1))).isSelected())
						{
							//Click the Enabled Net Sale Option
							Actions act = new Actions(driver);
							act.moveToElement(driver.findElement(By.xpath(excel.getData(3, 1555, 1)))).click().build().perform();
							try
							{
								//Check whether the Net Sale Option is displayed or not
								if(driver.findElement(By.xpath(excel.getData(3, 1560, 1))).isDisplayed())
								{
									test.log(LogStatus.FAIL, "Net Sale is displayed");
								}
							}
							catch(Exception e)
							{
								test.log(LogStatus.PASS, "Net Sale is not displayed");
							}
						}
						else
						{
							test.log(LogStatus.FAIL, "Net Sale Option is Disabled When user enter the required date for date range");
						}
						
						//Change the option from enable to Disable the Tax Option
						if(driver.findElement(By.xpath(excel.getData(3, 1084, 1))).isSelected())
						{
							//Click the Enabled Tax Option
							Actions act = new Actions(driver);
							act.moveToElement(driver.findElement(By.xpath(excel.getData(3, 1556, 1)))).click().build().perform();
							try
							{
								//Check whether the Tax Option is displayed or not
								if(driver.findElement(By.xpath(excel.getData(3, 1561, 1))).isDisplayed())
								{
									test.log(LogStatus.FAIL, "Tax is displayed");
								}
							}
							catch(Exception e)
							{
								test.log(LogStatus.PASS, "Tax is not displayed");
							}
						}
						else
						{
							test.log(LogStatus.FAIL, "Tax Option is Disabled When user enter the required date for date range");
						}
										
						//Change the option from enable to Disable the Discount Option
						if(driver.findElement(By.xpath(excel.getData(3, 1085, 1))).isSelected())
						{
							//Click the Enabled Discount Option
							Actions act = new Actions(driver);
							act.moveToElement(driver.findElement(By.xpath(excel.getData(3, 1557, 1)))).click().build().perform();
							try
							{
								//Check whether the Discount Option is displayed or not
								if(driver.findElement(By.xpath(excel.getData(3, 1562, 1))).isDisplayed())
								{
									test.log(LogStatus.FAIL, "Discount is displayed");
								}
							}
							catch(Exception e)
							{
								test.log(LogStatus.PASS, "Discount is not displayed");
							}
						}
						else
						{
							test.log(LogStatus.FAIL, "Discount Option is Disabled When user enter the required date for date range");
						}
						
						//Change the option from enable to Disable the Grand Sale Option
						if(driver.findElement(By.xpath(excel.getData(3, 1086, 1))).isSelected())
						{
							//Click the Enabled Grand Sale Option
							Actions act = new Actions(driver);
							act.moveToElement(driver.findElement(By.xpath(excel.getData(3, 1558, 1)))).click().build().perform();
							try
							{
								//Check whether the Grand Sale Option is displayed or not
								if(driver.findElement(By.xpath(excel.getData(3, 1563, 1))).isDisplayed())
								{
									test.log(LogStatus.FAIL, "Grand Sale is displayed");
								}
							}
							catch(Exception e)
							{
								test.log(LogStatus.PASS, "Grand Sale is not displayed");
							}
						}
						else
						{
							test.log(LogStatus.FAIL, "Grand Sale Option is Disabled When user enter the required date for date range");
						}
						
						//Change the option from enable to Disable the Number Of Customer Option
						if(driver.findElement(By.xpath(excel.getData(3, 1549, 1))).isSelected())
						{
							//Click the Enabled Number Of Customer Option
							Actions act = new Actions(driver);
							act.moveToElement(driver.findElement(By.xpath(excel.getData(3, 1559, 1)))).click().build().perform();
							try
							{
								//Check whether the Number Of Customer Option is displayed or not
								if(driver.findElement(By.xpath(excel.getData(3, 1564, 1))).isDisplayed())
								{
									test.log(LogStatus.FAIL, "Number Of Customer is displayed");
								}
							}
							catch(Exception e)
							{
								test.log(LogStatus.PASS, "Number Of Customer is not displayed");
							}
						}
						else
						{
							test.log(LogStatus.FAIL, "Number Of Customer Option is Disabled When user enter the required date for date range");
						}
						
						//Enable the Net Sale Option if it is in disabled status
						if(driver.findElement(By.xpath(excel.getData(3, 1083, 1))).isSelected())
						{
							test.log(LogStatus.FAIL, "Net Sale Option is Enabled after user click(Disable) the Net Sale option");
		
						}
						else
						{
							//Click the Enabled Net Sale Option
							Actions act = new Actions(driver);
							act.moveToElement(driver.findElement(By.xpath(excel.getData(3, 1555, 1)))).click().build().perform();
							try
							{
								//Check whether the Net Sale Option is displayed or not
								if(driver.findElement(By.xpath(excel.getData(3, 1550, 1))).isDisplayed())
								{
									test.log(LogStatus.PASS, "Net Sale is displayed");
								}
							}
							catch(Exception e)
							{
								test.log(LogStatus.FAIL, "Net Sale is not displayed");
							}				
						}
						
						//Enable the Tax Option if it is in disabled status
						if(driver.findElement(By.xpath(excel.getData(3, 1084, 1))).isSelected())
						{
							test.log(LogStatus.FAIL, "Tax Option is Enabled after user click(Disable) the Tax option");
		
						}
						else
						{
							//Click the Enabled Tax Option
							Actions act = new Actions(driver);
							act.moveToElement(driver.findElement(By.xpath(excel.getData(3, 1556, 1)))).click().build().perform();
							try
							{
								//Check whether the Tax Option is displayed or not
								if(driver.findElement(By.xpath(excel.getData(3, 1551, 1))).isDisplayed())
								{
									test.log(LogStatus.PASS, "Tax is displayed");
								}
							}
							catch(Exception e)
							{
								test.log(LogStatus.FAIL, "Tax is not displayed");
							}				
						}
						
												
						//Disable the required option from enable to disable Discount Option
						if(driver.findElement(By.xpath(excel.getData(3, 1085, 1))).isSelected())
						{
							test.log(LogStatus.FAIL, "Discount Option is Enabled after user click(Disable) the Discount option");
		
						}
						else
						{
		
							//Click the Enabled Discount Option
							Actions act = new Actions(driver);
							act.moveToElement(driver.findElement(By.xpath(excel.getData(3, 1557, 1)))).click().build().perform();
							try
							{
								//Check whether the Discount Option is displayed or not
								if(driver.findElement(By.xpath(excel.getData(3, 1552, 1))).isDisplayed())
								{
									test.log(LogStatus.PASS, "Discount is displayed");
								}
							}
							catch(Exception e)
							{
								test.log(LogStatus.FAIL, "Discount is not displayed");
							}
						}
						
						//Disable the required option from enable to disable Grand Sale Option
						if(driver.findElement(By.xpath(excel.getData(3, 1086, 1))).isSelected())
						{
							test.log(LogStatus.FAIL, "Grand Sale Option is Enabled after user click(Disable) the Grand Sale option");
		
						}
						else
						{
		
							//Click the Enabled Grand Sale Option
							Actions act = new Actions(driver);
							act.moveToElement(driver.findElement(By.xpath(excel.getData(3, 1558, 1)))).click().build().perform();
							try
							{
								//Check whether the Grand Sale Option is displayed or not
								if(driver.findElement(By.xpath(excel.getData(3, 1553, 1))).isDisplayed())
								{
									test.log(LogStatus.PASS, "Grand Sale is displayed");
								}
							}
							catch(Exception e)
							{
								test.log(LogStatus.FAIL, "Grand Sale is not displayed");
							}
						}
		
						//Disable the required option from enable to disable Number Of Customer Option
						if(driver.findElement(By.xpath(excel.getData(3, 1549, 1))).isSelected())
						{
							test.log(LogStatus.FAIL, "Number Of Customer Option is Enabled after user click(Disable) the Number Of Customer option");
		
						}
						else
						{
		
							//Click the Enabled Number Of Customer Option
							Actions act = new Actions(driver);
							act.moveToElement(driver.findElement(By.xpath(excel.getData(3, 1559, 1)))).click().build().perform();
							try
							{
								//Check whether the Number Of Customer Option is displayed or not
								if(driver.findElement(By.xpath(excel.getData(3, 1554, 1))).isDisplayed())
								{
									test.log(LogStatus.PASS, "Number Of Customer is displayed");
								}
							}
							catch(Exception e)
							{
								test.log(LogStatus.FAIL, "Number Of Customer is not displayed");
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
					test.log(LogStatus.FAIL, "All dynamic option is not selected");
				}
			} wb.close();
		}

}

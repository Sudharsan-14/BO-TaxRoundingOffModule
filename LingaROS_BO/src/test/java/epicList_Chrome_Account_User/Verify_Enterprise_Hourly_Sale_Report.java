package epicList_Chrome_Account_User;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
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

public class Verify_Enterprise_Hourly_Sale_Report {
	public WebDriver driver;
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Verify_Enterprise_Hourly_Sale_Report");

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
			Hourly_Enterprise_Report_Method_openpage(driver);
			Hourly_Enterprise_Report_Method_Stores(driver);
			Hourly_Enterprise_Report_Method_Group(driver);
			Hourly_Enterprise_Report_Method_City(driver);
			Hourly_Enterprise_Report_Method_State(driver);
			Hourly_Enterprise_Report_Method_ZipCode(driver);
			Thread.sleep(1500);
		}
	
		@Test(enabled=false,priority=2)
		public void Hourly_Enterprise_Report_Method_openpage(WebDriver driver) throws Exception
		{
			 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			Thread.sleep(15000);
/*			//Click the My Stores option
			driver.findElement(By.xpath("//span[.='My Stores']")).click();

			Thread.sleep(3000);
	        //Click the EnterPrise Reports Option		
			driver.findElement(By.xpath("//span[.='EnterPrise Reports']")).click();
			
			Thread.sleep(2000);
			//Click the Sale option
			driver.findElement(By.xpath("//span[.='Sale']")).click();
			
			Thread.sleep(2000);
			//Click the Hourly Sale option
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[1]/div/div/div/div/scrollable-tabset/div/div/div/ul/li[7]/a/uib-tab-heading/span")).click();
*/			
			Thread.sleep(2000);
			//Enter the URl
			driver.get(Utility.getProperty("Enterprise_Base_URL")+"enterPriseReport/hourlySaleSummary");

			Thread.sleep(5000);
			try
			{
			//Check Hourly Report page opened or not
			if(driver.findElement(By.xpath(excel.getData(3, 796, 1))).getText().equalsIgnoreCase("Hourly Sale"))
			{
				test.log(LogStatus.PASS, "Hourly Sale Report page loaded Successfully");
			
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));
			}
			else
			{
				test.log(LogStatus.FAIL, "Hourly Sale Report page loaded Failed");
			
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
		
		@Test(enabled=false,priority=3)
		public void Hourly_Enterprise_Report_Method_Stores(WebDriver driver) throws Exception
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
			System.out.println("********************* View The Store Report *************************");
			test.log(LogStatus.INFO, "********************* View The Store Report *************************");

			//Click the Select option
			driver.findElement(By.xpath(excel.getData(3, 694, 1))).click();
			//Enter the required filter
			driver.findElement(By.xpath(excel.getData(3, 695, 1))).sendKeys("Group");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 695, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(1500);
			//Click the Select option
			driver.findElement(By.xpath(excel.getData(3, 694, 1))).click();
			//Enter the required filter
			driver.findElement(By.xpath(excel.getData(3, 695, 1))).sendKeys("Stores");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 695, 1))).sendKeys(Keys.ENTER);

			
			Thread.sleep(5000);
			//Click the Select Some Options
			driver.findElement(By.xpath(excel.getData(3, 696, 1))).click();	
			//Remove the Select Some Options
			//driver.findElement(By.xpath("//form[@name='saleReport']/div[1]/div[2]/div/ul/li[1]/a")).click();	
			
			//Enter the required option
			driver.findElement(By.xpath(excel.getData(3, 697, 1))).sendKeys(Utility.getReportPropertyUser("Store"));
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 697, 1))).sendKeys(Keys.ENTER);
		
			Thread.sleep(1500);
			//Click the Time Period Options
			driver.findElement(By.xpath(excel.getData(3, 2157, 1))).click();	
			//Enter the required option
			driver.findElement(By.xpath(excel.getData(3, 2158, 1))).sendKeys("Date Range");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 2158, 1))).sendKeys(Keys.ENTER);
		
			//Clear the date field
			driver.findElement(By.xpath(excel.getData(3, 1982, 1))).clear();
			//Enter the required date
			driver.findElement(By.xpath(excel.getData(3, 1982, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
			
			//Clear the date field
			driver.findElement(By.xpath(excel.getData(3, 1983, 1))).clear();
			//Enter the required date
			driver.findElement(By.xpath(excel.getData(3, 1983, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
			
			Thread.sleep(1000);
			//Click the Run button
			driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
			long start = System.currentTimeMillis();
			
			WebDriverWait wait1=new WebDriverWait(driver, 30);
			WebElement ele11=driver.findElement(By.xpath(excel.getData(3, 704, 1)));
			
			try
			{
				
				//Check weather the report is available for the selected time period
				if(!wait1.until(ExpectedConditions.visibilityOf(ele11)).isDisplayed())
				{
					test.log(LogStatus.PASS, "Hourly Sale Report available for Specific Date");
					long finish = System.currentTimeMillis();
					long totalTime = finish- start; 
					System.out.println("Time in Millisecomds : "+totalTime);
					double ActualfinishTime = TimeUnit.MILLISECONDS.toMinutes(totalTime);
//					long ActualfinishTimeDouble = ((totalTime%86400)%3600)/60;
					long ActualfinishTimeDouble1 = (totalTime/1000)/6;
					System.out.println("MilSEc to Mins basic conversion : "+ActualfinishTimeDouble1);
//					test.log(LogStatus.INFO, "Total Time : "+ActualfinishTimeDouble1);
					test.log(LogStatus.INFO, "Time in Millisecomds : "+totalTime);
					
					Thread.sleep(3000);
					//Check Weather the Top 5 hourly sale available or not
					if(driver.findElement(By.xpath(excel.getData(3, 705, 1))).isDisplayed())
					{
						test.log(LogStatus.PASS, "Here Top 5 Hourly Sale Report available for Specific Date");
						
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
						
						Thread.sleep(3000);
						
						try {
							driver.findElement(By.xpath("//li/a[@ng-switch-when='last']/span")).click();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							
						}
				        //No.of rows
						Thread.sleep(3000);
				        List<WebElement> rows = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr"));
				        System.out.println("Rows Size is : "+rows.size());
				        
				        WebElement rosS=driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]"));
				        JavascriptExecutor js=(JavascriptExecutor)driver;
				        js.executeScript("arguments[0].scrollIntoView(true)", rosS);
				        
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
				        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Net_Sale")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Hourly Sale reports are same for Sale Amount");
				        	
				        	//Get the Total value of Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText();
				        	
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
				        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText();

				        	System.out.println("The Actual Net Sale value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Net Sale value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Hourly Sale reports are different for Sale Amount");
				        	
				        	//Get the Total value of Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText();
				        	
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
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[4]")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Tax")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Hourly Sale reports are same for Tax");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[4]")).getText();
				        	
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
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[4]")).getText();

				        	System.out.println("The Actual Tax value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Tax value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Hourly Sale reports are different for Tax");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[4]")).getText();
				        	
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
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[6]")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Discount")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Hourly Sale reports are same for Discount");
				        	
				        	//Get the Total value of Discount
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[6]")).getText();
				        	
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
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[6]")).getText();

				        	System.out.println("The Actual Discount value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Discount value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Hourly Sale reports are different for Discount");
				        	
				        	//Get the Total value of Discount
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[6]")).getText();
				        	
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
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[7]")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Grand_Sale")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Hourly Sale reports are same for Grand Sale");
				        	
				        	//Get the Total value of Grand Sale
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[7]")).getText();
				        	
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
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[7]")).getText();

				        	System.out.println("The Actual Grand Sale value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Grand Sale value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Hourly Sale reports are different for Grand Sale");
				        	
				        	//Get the Total value of Grand Sale
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[7]")).getText();
				        	
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
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[8]")).getText().equals(Utility.getReportPropertyUser("Hourly_Sale_Report_Number_Of_Customer")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Hourly Sale reports are same for Number of Customer");
				        	
				        	//Get the Total value of Number of Customer
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[8]")).getText();
				        	
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
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[8]")).getText();

				        	System.out.println("The Actual Number of Customer value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Number of Customer value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Hourly Sale reports are different for Number of Customer");
				        	
				        	//Get the Total value of Number of Customer
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[8]")).getText();
				        	
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
				        
				    	Thread.sleep(3000);
				    	driver.findElement(By.tagName("html")).sendKeys(Keys.END);
				    	
				    	String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));

						Thread.sleep(3000);
						driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);

					}
					else
					{
						test.log(LogStatus.FAIL, "Table Format Report is not available for Specific Date for store");
					
				    	
						String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));

				
					
					}
				}
			}
			
			catch(Exception e)
			{

				test.log(LogStatus.FAIL, "Hourly Sale Report is not available for Specific Date for store");

		    	
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));

		
			}
					
			Thread.sleep(3000);
			System.out.println("********************* End of Store Report *************************");
			test.log(LogStatus.INFO, "********************* End of Store Report *************************");wb.close();

			
		}
		
		@Test(enabled=false,priority=4)
		public void Hourly_Enterprise_Report_Method_Group(WebDriver driver) throws Exception
		{
			 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			Thread.sleep(3000);
			System.out.println("********************* View The Group Report *************************");
			test.log(LogStatus.INFO, "********************* View The Group Report *************************");
			
			//Click the Select option
			driver.findElement(By.xpath(excel.getData(3, 694, 1))).click();
			//Enter the required filter
			driver.findElement(By.xpath(excel.getData(3, 695, 1))).sendKeys("Group");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 695, 1))).sendKeys(Keys.ENTER);
			
			
			Thread.sleep(1500);
			//Click the Select Some Options
			driver.findElement(By.xpath(excel.getData(3, 696, 1))).click();
			//Enter the required option
			driver.findElement(By.xpath(excel.getData(3, 697, 1))).sendKeys(Utility.getReportPropertyUser("Group"));
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 697, 1))).sendKeys(Keys.ENTER);
		
			Thread.sleep(1500);
			//Click the Time Period Options
			driver.findElement(By.xpath(excel.getData(3, 2157, 1))).click();
			//Enter the required option
			driver.findElement(By.xpath(excel.getData(3, 2158, 1))).sendKeys("Date Range");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 2158, 1))).sendKeys(Keys.ENTER);
		
			Thread.sleep(500);
			//Clear the date field
			driver.findElement(By.xpath(excel.getData(3, 1982, 1))).clear();
			//Enter the required date
			driver.findElement(By.xpath(excel.getData(3, 1982, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
			
			Thread.sleep(500);
			//Clear the date field
			driver.findElement(By.xpath(excel.getData(3, 1983, 1))).clear();
			//Enter the required date
			driver.findElement(By.xpath(excel.getData(3, 1983, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
			
			Thread.sleep(1000);
			//Click the Run button
			driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
			long start = System.currentTimeMillis();
			
			WebDriverWait wait1=new WebDriverWait(driver, 30);
			WebElement ele11=driver.findElement(By.xpath(excel.getData(3, 704, 1)));
			
			
			try
			{
				
				//Check weather the report is available for the selected time period
				if(!wait1.until(ExpectedConditions.visibilityOf(ele11)).isDisplayed())
				{
					test.log(LogStatus.PASS, "Hourly Sale Report available for Specific Date");
					long finish = System.currentTimeMillis();
					long totalTime = finish- start; 
					System.out.println("Time in Millisecomds : "+totalTime);
					double ActualfinishTime = TimeUnit.MILLISECONDS.toMinutes(totalTime);
//					long ActualfinishTimeDouble = ((totalTime%86400)%3600)/60;
					long ActualfinishTimeDouble1 = (totalTime/1000)/6;
					System.out.println("MilSEc to Mins basic conversion : "+ActualfinishTimeDouble1);
//					test.log(LogStatus.INFO, "Total Time : "+ActualfinishTimeDouble1);
					test.log(LogStatus.INFO, "Time in Millisecomds : "+totalTime);
					
					Thread.sleep(3000);
					//Check Weather the Top 5 hourly sale available or not
					if(driver.findElement(By.xpath(excel.getData(3, 705, 1))).isDisplayed())
					{
						test.log(LogStatus.PASS, "Here Top 5 Hourly Sale Report available for Specific Date");
						
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
						
						Thread.sleep(3000);
				        //No.of rows
				        List<WebElement> rows = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr"));
				        System.out.println("Rows Size is : "+rows.size());
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
				        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Net_Sale")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Hourly Sale reports are same for Sale Amount");
				        	
				        	//Get the Total value of Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText();
				        	
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
				        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText();

				        	System.out.println("The Actual Net Sale value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Net Sale value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Hourly Sale reports are different for Sale Amount");
				        	
				        	//Get the Total value of Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText();
				        	
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
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[4]")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Tax")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Hourly Sale reports are same for Tax");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[4]")).getText();
				        	
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
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[4]")).getText();

				        	System.out.println("The Actual Tax value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Tax value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Hourly Sale reports are different for Tax");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[4]")).getText();
				        	
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
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[6]")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Discount")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Hourly Sale reports are same for Discount");
				        	
				        	//Get the Total value of Discount
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[6]")).getText();
				        	
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
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[6]")).getText();

				        	System.out.println("The Actual Discount value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Discount value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Hourly Sale reports are different for Discount");
				        	
				        	//Get the Total value of Discount
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[6]")).getText();
				        	
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
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[7]")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Grand_Sale")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Hourly Sale reports are same for Grand Sale");
				        	
				        	//Get the Total value of Grand Sale
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[7]")).getText();
				        	
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
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[7]")).getText();

				        	System.out.println("The Actual Grand Sale value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Grand Sale value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Hourly Sale reports are different for Grand Sale");
				        	
				        	//Get the Total value of Grand Sale
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[7]")).getText();
				        	
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
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[8]")).getText().equals(Utility.getReportPropertyUser("Hourly_Sale_Report_Number_Of_Customer")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Hourly Sale reports are same for Number of Customer");
				        	
				        	//Get the Total value of Number of Customer
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[8]")).getText();
				        	
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
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[8]")).getText();

				        	System.out.println("The Actual Number of Customer value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Number of Customer value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Hourly Sale reports are different for Number of Customer");
				        	
				        	//Get the Total value of Number of Customer
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[8]")).getText();
				        	
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
				        
				    	Thread.sleep(3000);
				    	driver.findElement(By.tagName("html")).sendKeys(Keys.END);
				    	
				    	String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));

						Thread.sleep(3000);
						driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);

					}
					else
					{
						test.log(LogStatus.FAIL, "Table Format Report is not available for Specific Date for Group");
					
					
						
						String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));

					
					
					}
				}
			}
			
			catch(Exception e)
			{

				test.log(LogStatus.FAIL, "Hourly Sale Report is not available for Specific Date for Group");

		    	
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));


			}
					
			
			Thread.sleep(3000);
			System.out.println("********************* End of Group Report *************************");
			test.log(LogStatus.INFO, "********************* End of Group Report *************************");wb.close();
		}
		
		@Test(enabled=false,priority=5)
		public void Hourly_Enterprise_Report_Method_City(WebDriver driver) throws Exception
		{
			    File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			Thread.sleep(3000);
			System.out.println("********************* View The City Report *************************");
			test.log(LogStatus.INFO, "********************* View The City Report *************************");

			//Click the Select option
			driver.findElement(By.xpath(excel.getData(3, 694, 1))).click();
			//Enter the required filter
			driver.findElement(By.xpath(excel.getData(3, 695, 1))).sendKeys("City");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 695, 1))).sendKeys(Keys.ENTER);
			
			
			Thread.sleep(1500);
			//Click the Select Some Options
			driver.findElement(By.xpath(excel.getData(3, 696, 1))).click();
			//Enter the required option
			driver.findElement(By.xpath(excel.getData(3, 697, 1))).sendKeys(Utility.getReportPropertyUser("City"));
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 697, 1))).sendKeys(Keys.ENTER);
		
			Thread.sleep(1500);
			//Click the Time Period Options
			driver.findElement(By.xpath(excel.getData(3, 2157, 1))).click();
			//Enter the required option
			driver.findElement(By.xpath(excel.getData(3, 2158, 1))).sendKeys("Date Range");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 2158, 1))).sendKeys(Keys.ENTER);
		
			//Clear the date field
			driver.findElement(By.xpath(excel.getData(3, 1982, 1))).clear();
			//Enter the required date
			driver.findElement(By.xpath(excel.getData(3, 1982, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
			
			//Clear the date field
			driver.findElement(By.xpath(excel.getData(3, 1983, 1))).clear();
			//Enter the required date
			driver.findElement(By.xpath(excel.getData(3, 1983, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
			
			Thread.sleep(1000);
			//Click the Run button
			driver.findElement(By.xpath(excel.getData(3,572, 1))).click();
			long start = System.currentTimeMillis();
			
			WebDriverWait wait1=new WebDriverWait(driver, 30);
			WebElement ele11=driver.findElement(By.xpath(excel.getData(3, 704, 1)));
			
			try
			{
				//Check weather the report is available for the selected time period
				if(!wait1.until(ExpectedConditions.visibilityOf(ele11)).isDisplayed())
				{
					test.log(LogStatus.PASS, "Hourly Sale Report available for Specific Date");
					long finish = System.currentTimeMillis();
					long totalTime = finish- start; 
					System.out.println("Time in Millisecomds : "+totalTime);
					double ActualfinishTime = TimeUnit.MILLISECONDS.toMinutes(totalTime);
//					long ActualfinishTimeDouble = ((totalTime%86400)%3600)/60;
					long ActualfinishTimeDouble1 = (totalTime/1000)/6;
					System.out.println("MilSEc to Mins basic conversion : "+ActualfinishTimeDouble1);
//					test.log(LogStatus.INFO, "Total Time : "+ActualfinishTimeDouble1);
					test.log(LogStatus.INFO, "Time in Millisecomds : "+totalTime);
					
					Thread.sleep(3000);
					//Check Weather the Top 5 hourly sale available or not
					if(driver.findElement(By.xpath(excel.getData(3, 705, 1))).isDisplayed())
					{
						test.log(LogStatus.PASS, "Here Top 5 Hourly Sale Report available for Specific Date");
						
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
						
						Thread.sleep(3000);
				        //No.of rows
				        List<WebElement> rows = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr"));
				        System.out.println("Rows Size is : "+rows.size());
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
				        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Net_Sale")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Hourly Sale reports are same for Sale Amount");
				        	
				        	//Get the Total value of Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText();
				        	
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
				        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText();

				        	System.out.println("The Actual Net Sale value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Net Sale value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Hourly Sale reports are different for Sale Amount");
				        	
				        	//Get the Total value of Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText();
				        	
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
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[4]")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Tax")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Hourly Sale reports are same for Tax");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[4]")).getText();
				        	
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
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[4]")).getText();

				        	System.out.println("The Actual Tax value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Tax value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Hourly Sale reports are different for Tax");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[4]")).getText();
				        	
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
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[6]")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Discount")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Hourly Sale reports are same for Discount");
				        	
				        	//Get the Total value of Discount
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[6]")).getText();
				        	
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
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[6]")).getText();

				        	System.out.println("The Actual Discount value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Discount value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Hourly Sale reports are different for Discount");
				        	
				        	//Get the Total value of Discount
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[6]")).getText();
				        	
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
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[7]")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Grand_Sale")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Hourly Sale reports are same for Grand Sale");
				        	
				        	//Get the Total value of Grand Sale
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[7]")).getText();
				        	
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
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[7]")).getText();

				        	System.out.println("The Actual Grand Sale value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Grand Sale value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Hourly Sale reports are different for Grand Sale");
				        	
				        	//Get the Total value of Grand Sale
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[7]")).getText();
				        	
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
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[8]")).getText().equals(Utility.getReportPropertyUser("Hourly_Sale_Report_Number_Of_Customer")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Hourly Sale reports are same for Number of Customer");
				        	
				        	//Get the Total value of Number of Customer
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[8]")).getText();
				        	
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
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[8]")).getText();

				        	System.out.println("The Actual Number of Customer value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Number of Customer value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Hourly Sale reports are different for Number of Customer");
				        	
				        	//Get the Total value of Number of Customer
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[8]")).getText();
				        	
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
				        
				    	Thread.sleep(3000);
				    	driver.findElement(By.tagName("html")).sendKeys(Keys.END);
				    	
				    	String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));

						Thread.sleep(3000);
						driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);

					}
					else
					{
						test.log(LogStatus.FAIL, "Table Format Report is not available for Specific Date for City");
					
				    	
						String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));


					
					}
				}
			}
			
			catch(Exception e)
			{

				test.log(LogStatus.FAIL, "Hourly Sale Report is not available for Specific Date for City");

		    	
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));


			}
					
			
			Thread.sleep(3000);
			System.out.println("********************* End of City Report *************************");
			test.log(LogStatus.INFO, "********************* End of City Report *************************");wb.close();
		}
				
		@Test(enabled=false,priority=6)
		public void Hourly_Enterprise_Report_Method_State(WebDriver driver) throws Exception
		{
			 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			Thread.sleep(3000);
			System.out.println("********************* View The State Report *************************");
			test.log(LogStatus.INFO, "********************* View The State Report *************************");

			//Click the Select option
			driver.findElement(By.xpath(excel.getData(3, 694, 1))).click();
			//Enter the required filter
			driver.findElement(By.xpath(excel.getData(3, 695, 1))).sendKeys("State");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 695, 1))).sendKeys(Keys.ENTER);
			
			
			Thread.sleep(1500);
			//Click the Select Some Options
			driver.findElement(By.xpath(excel.getData(3, 696, 1))).click();
			//Enter the required option
			driver.findElement(By.xpath(excel.getData(3, 697, 1))).sendKeys(Utility.getReportPropertyUser("State"));
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 697, 1))).sendKeys(Keys.ENTER);
		
			Thread.sleep(1500);
			//Click the Time Period Options
			driver.findElement(By.xpath(excel.getData(3, 2157, 1))).click();
			//Enter the required option
			driver.findElement(By.xpath(excel.getData(3, 2158, 1))).sendKeys("Date Range");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 2158, 1))).sendKeys(Keys.ENTER);
		
			//Clear the date field
			driver.findElement(By.xpath(excel.getData(3, 1982, 1))).clear();
			//Enter the required date
			driver.findElement(By.xpath(excel.getData(3, 1982, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
			
			//Clear the date field
			driver.findElement(By.xpath(excel.getData(3, 1983, 1))).clear();
			//Enter the required date
			driver.findElement(By.xpath(excel.getData(3, 1983, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
			
			Thread.sleep(1000);
			//Click the Run button
			driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
			long start = System.currentTimeMillis();
			
			WebDriverWait wait1=new WebDriverWait(driver, 30);
			WebElement ele11=driver.findElement(By.xpath(excel.getData(3, 704, 1)));
			
			
			try
			{
				
				//Check weather the report is available for the selected time period
				if(!wait1.until(ExpectedConditions.visibilityOf(ele11)).isDisplayed())
				{
					test.log(LogStatus.PASS, "Hourly Sale Report available for Specific Date");
					
					long finish = System.currentTimeMillis();
					long totalTime = finish- start; 
					System.out.println("Time in Millisecomds : "+totalTime);
					double ActualfinishTime = TimeUnit.MILLISECONDS.toMinutes(totalTime);
//					long ActualfinishTimeDouble = ((totalTime%86400)%3600)/60;
					long ActualfinishTimeDouble1 = (totalTime/1000)/6;
					System.out.println("MilSEc to Mins basic conversion : "+ActualfinishTimeDouble1);
//					test.log(LogStatus.INFO, "Total Time : "+ActualfinishTimeDouble1);
					test.log(LogStatus.INFO, "Time in Millisecomds : "+totalTime);
					
					Thread.sleep(3000);
					//Check Weather the Top 5 hourly sale available or not
					if(driver.findElement(By.xpath(excel.getData(3, 705, 1))).isDisplayed())
					{
						test.log(LogStatus.PASS, "Here Top 5 Hourly Sale Report available for Specific Date");
						
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
						
						Thread.sleep(3000);
				        //No.of rows
				        List<WebElement> rows = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr"));
				        System.out.println("Rows Size is : "+rows.size());
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
				        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Net_Sale")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Hourly Sale reports are same for Sale Amount");
				        	
				        	//Get the Total value of Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText();
				        	
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
				        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText();

				        	System.out.println("The Actual Net Sale value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Net Sale value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Hourly Sale reports are different for Sale Amount");
				        	
				        	//Get the Total value of Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText();
				        	
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
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[4]")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Tax")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Hourly Sale reports are same for Tax");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[4]")).getText();
				        	
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
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[4]")).getText();

				        	System.out.println("The Actual Tax value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Tax value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Hourly Sale reports are different for Tax");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[4]")).getText();
				        	
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
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[6]")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Discount")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Hourly Sale reports are same for Discount");
				        	
				        	//Get the Total value of Discount
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[6]")).getText();
				        	
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
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[6]")).getText();

				        	System.out.println("The Actual Discount value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Discount value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Hourly Sale reports are different for Discount");
				        	
				        	//Get the Total value of Discount
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[6]")).getText();
				        	
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
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[7]")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Grand_Sale")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Hourly Sale reports are same for Grand Sale");
				        	
				        	//Get the Total value of Grand Sale
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[7]")).getText();
				        	
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
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[7]")).getText();

				        	System.out.println("The Actual Grand Sale value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Grand Sale value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Hourly Sale reports are different for Grand Sale");
				        	
				        	//Get the Total value of Grand Sale
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[7]")).getText();
				        	
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
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[8]")).getText().equals(Utility.getReportPropertyUser("Hourly_Sale_Report_Number_Of_Customer")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Hourly Sale reports are same for Number of Customer");
				        	
				        	//Get the Total value of Number of Customer
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[8]")).getText();
				        	
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
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[8]")).getText();

				        	System.out.println("The Actual Number of Customer value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Number of Customer value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Hourly Sale reports are different for Number of Customer");
				        	
				        	//Get the Total value of Number of Customer
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[8]")).getText();
				        	
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
				        
				    	Thread.sleep(3000);
				    	driver.findElement(By.tagName("html")).sendKeys(Keys.END);
				    	
				    	String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));

						Thread.sleep(3000);
						driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);

					}
					else
					{
						test.log(LogStatus.FAIL, "Table Format Report is not available for Specific Date for State");
				
				    	
						String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));


					
					}
				}
			}
			
			catch(Exception e)
			{

				test.log(LogStatus.FAIL, "Hourly Sale Report is not available for Specific Date for State");

		    	
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));


			}
					
			
			Thread.sleep(3000);
			System.out.println("********************* End of State Report *************************");
			test.log(LogStatus.INFO, "********************* End of State Report *************************");wb.close();
		}

		@Test(enabled=false,priority=7)
		public void Hourly_Enterprise_Report_Method_ZipCode(WebDriver driver) throws Exception
		{
			 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			Thread.sleep(3000);
			System.out.println("********************* View The Zip Code Report *************************");
			test.log(LogStatus.INFO, "********************* View The Zip Code Report *************************");
			
			//Click the Select option
			driver.findElement(By.xpath(excel.getData(3, 694, 1))).click();
			//Enter the required filter
			driver.findElement(By.xpath(excel.getData(3, 695, 1))).sendKeys("Zip Code");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 695, 1))).sendKeys(Keys.ENTER);
			
			
			Thread.sleep(1500);
			//Click the Select Some Options
			driver.findElement(By.xpath(excel.getData(3, 696, 1))).click();
			//Enter the required option
			driver.findElement(By.xpath(excel.getData(3, 697, 1))).sendKeys(Utility.getReportPropertyUser("Zipcode"));
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 697, 1))).sendKeys(Keys.ENTER);
		
			Thread.sleep(1500);
			//Click the Time Period Options
			driver.findElement(By.xpath(excel.getData(3, 2157, 1))).click();
			//Enter the required option
			driver.findElement(By.xpath(excel.getData(3, 2158, 1))).sendKeys("Date Range");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 2158, 1))).sendKeys(Keys.ENTER);
		
			//Clear the date field
			driver.findElement(By.xpath(excel.getData(3, 1982, 1))).clear();
			//Enter the required date
			driver.findElement(By.xpath(excel.getData(3, 1982, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
			
			//Clear the date field
			driver.findElement(By.xpath(excel.getData(3, 1983, 1))).clear();
			//Enter the required date
			driver.findElement(By.xpath(excel.getData(3, 1983, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
			
			Thread.sleep(1000);
			//Click the Run button
			driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
			long start = System.currentTimeMillis();
			
			WebDriverWait wait1=new WebDriverWait(driver, 30);
			WebElement ele11=driver.findElement(By.xpath(excel.getData(3, 704, 1)));
			
			
			try
			{
				//Check weather the report is available for the selected time period
				if(!wait1.until(ExpectedConditions.visibilityOf(ele11)).isDisplayed())
				{
					test.log(LogStatus.PASS, "Hourly Sale Report available for Specific Date");
					long finish = System.currentTimeMillis();
					long totalTime = finish- start; 
					System.out.println("Time in Millisecomds : "+totalTime);
					double ActualfinishTime = TimeUnit.MILLISECONDS.toMinutes(totalTime);
//					long ActualfinishTimeDouble = ((totalTime%86400)%3600)/60;
					long ActualfinishTimeDouble1 = (totalTime/1000)/6;
					System.out.println("MilSEc to Mins basic conversion : "+ActualfinishTimeDouble1);
//					test.log(LogStatus.INFO, "Total Time : "+ActualfinishTimeDouble1);
					test.log(LogStatus.INFO, "Time in Millisecomds : "+totalTime);
					
					Thread.sleep(3000);
					//Check Weather the Top 5 hourly sale available or not
					if(driver.findElement(By.xpath(excel.getData(3, 705, 1))).isDisplayed())
					{
						test.log(LogStatus.PASS, "Here Top 5 Hourly Sale Report available for Specific Date");
						
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
						
						Thread.sleep(3000);
				        //No.of rows
				        List<WebElement> rows = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr"));
				        System.out.println("Rows Size is : "+rows.size());
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
				        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Net_Sale")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Hourly Sale reports are same for Sale Amount");
				        	
				        	//Get the Total value of Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText();
				        	
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
				        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText();

				        	System.out.println("The Actual Net Sale value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Net Sale value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Hourly Sale reports are different for Sale Amount");
				        	
				        	//Get the Total value of Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText();
				        	
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
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[4]")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Tax")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Hourly Sale reports are same for Tax");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[4]")).getText();
				        	
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
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[4]")).getText();

				        	System.out.println("The Actual Tax value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Tax value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Hourly Sale reports are different for Tax");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[4]")).getText();
				        	
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
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[6]")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Discount")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Hourly Sale reports are same for Discount");
				        	
				        	//Get the Total value of Discount
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[6]")).getText();
				        	
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
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[6]")).getText();

				        	System.out.println("The Actual Discount value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Discount value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Hourly Sale reports are different for Discount");
				        	
				        	//Get the Total value of Discount
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[6]")).getText();
				        	
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
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[7]")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Grand_Sale")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Hourly Sale reports are same for Grand Sale");
				        	
				        	//Get the Total value of Grand Sale
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[7]")).getText();
				        	
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
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[7]")).getText();

				        	System.out.println("The Actual Grand Sale value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Grand Sale value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Hourly Sale reports are different for Grand Sale");
				        	
				        	//Get the Total value of Grand Sale
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[7]")).getText();
				        	
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
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[8]")).getText().equals(Utility.getReportPropertyUser("Hourly_Sale_Report_Number_Of_Customer")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Hourly Sale reports are same for Number of Customer");
				        	
				        	//Get the Total value of Number of Customer
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[8]")).getText();
				        	
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
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[8]")).getText();

				        	System.out.println("The Actual Number of Customer value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Number of Customer value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Hourly Sale reports are different for Number of Customer");
				        	
				        	//Get the Total value of Number of Customer
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[8]")).getText();
				        	
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
				        
				    	Thread.sleep(3000);
				    	driver.findElement(By.tagName("html")).sendKeys(Keys.END);
				    	
				    	String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));

						Thread.sleep(3000);
						driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);

					}
					else
					{
						test.log(LogStatus.FAIL, "Table Format Report is not available for Specific Date for Zip Code");
				
				    	
						String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));


					}
				}
			}
			
			catch(Exception e)
			{

				test.log(LogStatus.FAIL, "Hourly Sale Report is not available for Specific Date for Zip Code");

		    	
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));


			}
					
			
			Thread.sleep(3000);
			System.out.println("********************* End of Zip Code Report *************************");
			test.log(LogStatus.INFO, "********************* End of Zip Code Report *************************");wb.close();

		}

}

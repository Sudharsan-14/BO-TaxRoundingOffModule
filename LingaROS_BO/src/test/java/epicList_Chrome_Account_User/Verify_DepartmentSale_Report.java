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
import org.openqa.selenium.JavascriptExecutor;
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
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import epicList_Chrome_Account_User.Utility;
import newReadExcelFile_New.ExcelDataConfig;

public class Verify_DepartmentSale_Report {
	public WebDriver driver;
	
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Verify_DepartmentSale_Report");

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

	
		@BeforeTest
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
			System.out.println("Started");
			Department_Sale_method_OpenReport(driver);
			Department_Sale_method_Report_For_Specific_Date(driver);
			Department_Sale_method_Report_For_Specific_Date_With_Quantity_Sort(driver);
//			Department_Sale_method_Report_For_Specific_Date_Without_Quantity_Sort_With_Dynamic_Report(driver);
//			Department_Sale_method_Report_For_Specific_Date_With_Quantity_Sort_And_Dynamic_Report(driver);
			Thread.sleep(1500);
		}
		
		@Test(priority=2,enabled = false)
			public void Department_Sale_method_OpenReport(WebDriver driver) throws Exception
		{

			File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

			//Thread.sleep(000);

			Thread.sleep(3000);
/*			//Click the Reports option
			driver.findElement(By.xpath("//span[.='Reports']")).click();
			// Create instance of Java script executor
			JavascriptExecutor je = (JavascriptExecutor) driver;
			//Identify the WebElement which will appear after scrolling down
			WebElement element = driver.findElement(By.xpath("//span[.='Sale']"));Thread.sleep(3000);
			//Scroll the page till the Sale option present
			je.executeScript("arguments[0].scrollIntoView(true);",element); Thread.sleep(3000);
	        //Click the Sale Option		
			driver.findElement(By.xpath("//span[.='Sale']")).click();*/
			
			driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id1")+"report/departmentSale");
			Thread.sleep(5000);
			try
			{
			//Check Sale Report page opened or not
			if(driver.findElement(By.xpath(excel.getData(3, 1512, 1))).getText().equalsIgnoreCase("Sale Report"))
			{
				test.log(LogStatus.PASS, "Department Sale report page loaded Successfully");
			
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));
			
			}
			else
			{
				test.log(LogStatus.FAIL, "Department Sale report page loaded Failed");
			
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
		
			@Test(priority=4,enabled = false)
			public void Department_Sale_method_Report_For_Specific_Date_With_Quantity_Sort(WebDriver driver) throws Exception
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
				//Click the Department option
				driver.findElement(By.xpath(excel.getData(3, 1067, 1))).click();
				//Enter the required department
				driver.findElement(By.xpath(excel.getData(3, 1068, 1))).sendKeys("All");
				//Press the Enter Key
				driver.findElement(By.xpath(excel.getData(3, 1068, 1))).sendKeys(Keys.ENTER);
			
				Thread.sleep(3000);
				//Click the Time Period option
				driver.findElement(By.xpath(excel.getData(3, 736, 1))).click();
				//Enter the required Time Period
				driver.findElement(By.xpath(excel.getData(3, 737, 1))).sendKeys("Date Range");
				//Press the Enter Key
				driver.findElement(By.xpath(excel.getData(3, 737, 1))).sendKeys(Keys.ENTER);
				
				Thread.sleep(2000);
				//Clear the date field
				driver.findElement(By.xpath(excel.getData(3, 738, 1))).clear();
				//Enter the date
				driver.findElement(By.xpath(excel.getData(3, 738, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
		
				Thread.sleep(2000);
				//Clear the date field
				driver.findElement(By.xpath(excel.getData(3, 739, 1))).clear();
				//Enter the date
				driver.findElement(By.xpath(excel.getData(3, 739, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
				//Check whether the Quantity sort is enabled or not
				if(driver.findElement(By.xpath(excel.getData(3, 1356, 1))).isSelected())
				{
					
				}
				else
				{
					//Click the Quantity Sort option
					Actions act = new Actions(driver);
					act.moveToElement(driver.findElement(By.xpath(excel.getData(2, 9, 1)))).click().build().perform();			
				}
				Thread.sleep(2000);
				//Click the Run Button
				driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
				
				long start = System.currentTimeMillis();
		    	
				
				Thread.sleep(3000);
				//Number of rows
				List<WebElement> rowss = driver.findElements(By.xpath(excel.getData(3, 1078, 1)));
				int rowa = rowss.size()-1;
				ArrayList<Integer> array = new ArrayList<Integer>();
				for(int i =1; i <= rowa; i++)
				{
					String quan = driver.findElement(By.xpath(excel.getData(2, 10, 1))).getText().replaceAll("[a-zA-Z $ ₹ , :]", "");
					Integer dquan = Integer.parseInt(quan);
					//int quantity = quan.intValue();
					array.add(dquan);
				}
				Collections.sort(array);
				for(int i =0; i < rowa; i++)
				{	int s = i+1;
					String e = driver.findElement(By.xpath(excel.getData(2, 11, 1))).getText().replaceAll("[a-zA-Z $ ₹ , :]", "");
					double actual = Double.parseDouble(e);
					double expect = array.get(i);
					if(actual != expect)
					{
						test.log(LogStatus.PASS, "Sorted by Quantity is Fail");
					}
				}
				System.out.println("----Department Sale Started-----------");

				WebDriverWait wait1=new WebDriverWait(driver, 30);
				WebElement ele11=driver.findElement(By.xpath(excel.getData(3, 705, 1)));
				
			
					//Check weather the report is available for the selected time period
				if(driver.findElement(By.xpath(excel.getData(3, 704, 1))).isDisplayed())
					{
						test.log(LogStatus.FAIL, "Department Sale Report is not available for Specific Date");
					}
				
				
				else
				{
					test.log(LogStatus.PASS, "Department Sale Report available for Specific Date");
					if(wait1.until(ExpectedConditions.visibilityOf(ele11)).isDisplayed()) {
					long finish = System.currentTimeMillis();
					long totalTime = finish- start; 
					System.out.println("Time in Millisecomds : "+totalTime);
					double ActualfinishTime = TimeUnit.MILLISECONDS.toMinutes(totalTime);
//					long ActualfinishTimeDouble = ((totalTime%86400)%3600)/60;
					long ActualfinishTimeDouble1 = (totalTime/1000)/6;
					System.out.println("MilSEc to Mins basic conversion : "+ActualfinishTimeDouble1);
//					test.log(LogStatus.INFO, "Total Time : "+ActualfinishTimeDouble1);
					test.log(LogStatus.INFO, "Time in Millisecomds : "+totalTime);
					}
					Thread.sleep(3000);
					//Check Weather the Top 5 department sale available or not
					if(driver.findElement(By.xpath(excel.getData(3, 705, 1))).isDisplayed())
					{
						test.log(LogStatus.PASS, "Here Top 5 Department Sale Report available for Specific Date");
						
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
						test.log(LogStatus.FAIL, "Here Top 5 Department Sale Report not available for Specific Date");
					}
					
					Thread.sleep(5000);
					//Check weather the report graph is available or not
					if(driver.findElement(By.xpath(excel.getData(3, 710, 1))).isDisplayed())
					{
						test.log(LogStatus.PASS, "There is Graphical type of department sale report is available for Specific Date");
					}
					else
					{
						test.log(LogStatus.FAIL, "Graphical report is not available for Specific Date");
					}
					
					driver.findElement(By.tagName("html")).sendKeys(Keys.END);
					
					Thread.sleep(5000);
					//Check weather the table format report is available or not
					if(driver.findElement(By.xpath(excel.getData(3, 1077, 1))).isDisplayed())
					{
						test.log(LogStatus.PASS, "Table Format Report is available for Specific Date");
						
						Thread.sleep(3000);
				        //No.of rows
				        List<WebElement> rows = driver.findElements(By.xpath(excel.getData(3, 1078, 1)));
				        
		/*	        //Print number of Rows
				        System.out.println("Number of Rows are : "+rows);*/
				        
			        	
			        	//Replace all commo's with empty space
			        	String expected1 = Utility.getReportPropertyUser("Sale_Report_Sale_Amount").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Sale_Amount element into float value
			        	float expect1 = Float.parseFloat(expected1);  
			        		        	
			        	//Replace all commo's with empty space
			        	String expected2 = Utility.getReportPropertyUser("Sale_Report_Quantity").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Quantity element into float value
			        	float expect2 = Float.parseFloat(expected2);  
			        	
			        	//Replace all commo's with empty space
			        	String expected3 = Utility.getReportPropertyUser("Sale_Report_Tax").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Tax element into float value
			        	float expect3 = Float.parseFloat(expected3);  
		
			        	//Replace all commo's with empty space
			        	String expected4 = Utility.getReportPropertyUser("Sale_Report_Discount").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Discount element into float value
			        	float expect4 = Float.parseFloat(expected4);  
			        	
			        	//Replace all commo's with empty space
			        	String expected5 = Utility.getReportPropertyUser("Sale_Report_Percentage_Of_Sale").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Percentage_Of_Sale element into float value
			        	float expect5 = Float.parseFloat(expected5);                              
				        
				        //Check weather the Sale Amount Report is correct or not
				        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Sale_Amount")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Department Sale reports are same for Sale Amount");
				        	
				        	//Get the Total value of Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Sale Amount Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Sale Amount Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Sale Amount Value is : "+ actual);
				        }
				        
						else if(expect1 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Check Count
				        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText();
		
				        	System.out.println("The Actual Sale Amount value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Sale Amount value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Department Sale reports are different for Sale Amount");
				        	
				        	//Get the Total value of Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the sale amount Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect1;
				        	
				        	//Print the different value
				        	System.out.println("Sale Amount Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Sale Amount Value different is : "+different);	
				        }
				        
				        //Check weather the Quantity Report is correct or not
				        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Quantity')]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Quantity")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Department Sale reports are same for Quantity");
				        	
				        	//Get the Total value of Quantity
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Quantity')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Quantity Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Quantity Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Quantity Value is : "+ actual);
				        }
				        
						else if(expect2 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Quantity
				        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Quantity')]/span")).getText();
		
				        	System.out.println("The Actual Quantity value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Quantity value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Department Sale reports are different for Quantity");
				        	
				        	//Get the Total value of Quantity
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Quantity')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Quantity Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Get the different
				        	float different = actual - expect2;
				        	
				        	//Print the different value
				        	System.out.println("Quantity Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Quantity Value different is : "+different);	
				        }
				        
				        //Check weather the Tax Report is correct or not
				        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Tax')]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Tax")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Department Sale reports are same for Tax");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Tax')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");   	
		
				        	//Convert the String value of the Tax Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Tax Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Tax Value is : "+ actual);
				        }
				        
						else if(expect3 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Tax
				        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Tax')]/span")).getText();
		
				        	System.out.println("The Actual Tax value is : "+actualText);		 
				        	
				        	test.log(LogStatus.INFO, "The Actual Tax value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Department Sale reports are different for Tax");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Tax')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Tax Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect3;
				        	
				        	//Print the different value
				        	System.out.println("Tax Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Tax Value different is : "+different);	
				        }
				        
				        //Check weather the Discount Report is correct or not
				        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Discount')]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Discount")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Department Sale reports are same for Discount");
				        	
				        	//Get the Total value of Discount
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Discount')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Discount Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Discount Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Discount Value is : "+ actual);
				        }
				        
						else if(expect4 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Discount
				        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Discount')]/span")).getText();
		
				        	System.out.println("The Actual Discount value is : "+actualText);	
				        	
				        	test.log(LogStatus.INFO, "The Actual Discount value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Department Sale reports are different for Discount");
				        	
				        	//Get the Total value of Discount
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Discount')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Discount Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect4;
				        	
				        	//Print the different value
				        	System.out.println("Discount Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Discount Value different is : "+different);	
				        }
				        
				        //Check weather the Percentage Of Sale Report is correct or not
				        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'% of Sale')]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Percentage_Of_Sale")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Department Sale reports are same for Percentage Of Sale");
				        	
				        	//Get the Total value of Percentage Of Sale
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'% of Sale')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Percentage of Sale Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Percentage Of Sale Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Percentage Of Sale Value is : "+ actual);
				        }
				        
						else if(expect5 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Percentage Of Sale
				        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'% of Sale')]/span")).getText();
		
				        	System.out.println("The Actual Percentage Of Sale value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Percentage Of Sale value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Department Sale reports are different for Percentage Of Sale");
				        	
				        	//Get the Total value of Percentage Of Sale
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'% of Sale')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Percentage Of Sale Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect5;
				        	
				        	//Print the different value
				        	System.out.println("Percentage Of Sale Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Percentage Of Sale Value different is : "+different);	
				        }
				        
				        
//				        //Check weather the Rounding Off Report is correct or not
//				        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Rounding Off')]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Rounding_Off")))
//				        {
//				        	test.log(LogStatus.PASS, "Actual and Expected Department Sale reports are same for Rounding Off");
//				        	
//				        	//Get the Total value of Rounding Off
//				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Rounding Off')]/span")).getText();
//				        	
//				        	//Replace all commo's with empty space
//				        	String actualText= actualText1.replace(",", "");
//				        	
//				        	//Convert the String value of the Rounding Off Total element into float value
//				        	float actual = Float.parseFloat(actualText);
//				        	
//				        	//Print the actual value
//				        	System.out.println("The Actual Rounding Off Value is : "+actual);
//				        	
//				        	test.log(LogStatus.PASS, "The Actual Rounding Off Value is : "+ actual);
//				        }
//				        
//						else if(expect1 == unknownValue)
//						{
//							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
//							
//				        	//Get the Total value of Rounding Off
//				        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Rounding Off')]/span")).getText();
//		
//				        	System.out.println("The Actual Rounding Off value is : "+actualText);
//				        	
//				        	test.log(LogStatus.INFO, "The Actual Rounding Off value is : "+actualText);
//						}
//				        
//				        else
//				        {
//				        	test.log(LogStatus.FAIL, "Actual and Expected Department Sale reports are different for Rounding Off");
//				        	
//				        	//Get the Total value of Rounding Off
//				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Rounding Off')]/span")).getText();
//				        	
//				        	//Replace all commo's with empty space
//				        	String actualText= actualText1.replace(",", "");
//				        	
//				        	//Convert the String value of the Rounding Off Total element into float value
//				        	float actual = Float.parseFloat(actualText);
//				        			        	
//				        	//Get the different
//				        	float different = actual - expect1;
//				        	
//				        	//Print the different value
//				        	System.out.println("Rounding Off Value different is : "+different);
//				        	
//				        	test.log(LogStatus.FAIL, "Rounding Off Value different is : "+different);	
//				        }
				        
				        
				    	String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));
			
					}
					else
					{
						test.log(LogStatus.FAIL, "Table Format Report is not available for Specific Date");
					} Thread.sleep(5000);
				}wb.close();
				driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			}
			
			@Test(priority=3,enabled = false)
			public void Department_Sale_method_Report_For_Specific_Date(WebDriver driver) throws Exception
			{
				
				File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

				Thread.sleep(5000);
				WebElement html = driver.findElement(By.tagName("html"));
				html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
				html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
				html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
				html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		
				Thread.sleep(5000);
				//Click the Department option
				driver.findElement(By.xpath(excel.getData(3, 1067, 1))).click();
				//Enter the required department
				driver.findElement(By.xpath(excel.getData(3, 1068, 1))).sendKeys("All");
				//Press the Enter Key
				driver.findElement(By.xpath(excel.getData(3, 1068, 1))).sendKeys(Keys.ENTER);
			
				Thread.sleep(3000);
				//Click the Time Period option
				driver.findElement(By.xpath(excel.getData(3, 736, 1))).click();
				//Enter the required Time Period
				driver.findElement(By.xpath(excel.getData(3, 737, 1))).sendKeys("Date Range");
				//Press the Enter Key
				driver.findElement(By.xpath(excel.getData(3, 737, 1))).sendKeys(Keys.ENTER);
				
				Thread.sleep(2000);
				//Clear the date field
				driver.findElement(By.xpath(excel.getData(3, 738, 1))).clear();
				//Enter the date
				driver.findElement(By.xpath(excel.getData(3, 738, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
		
				Thread.sleep(2000);
				//Clear the date field
				driver.findElement(By.xpath(excel.getData(3, 739, 1))).clear();
				//Enter the date
				driver.findElement(By.xpath(excel.getData(3, 739, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
				
				//Check whether the Quantity sort is enabled or not
				if(driver.findElement(By.xpath(excel.getData(3, 1356, 1))).isSelected())
				{
					
				}
				else
				{
					//Click the Quantity Sort option
					Actions act = new Actions(driver);
					act.moveToElement(driver.findElement(By.xpath(excel.getData(2, 9, 1)))).click().build().perform();			
				}
				Thread.sleep(2000);
				//Click the Run Button
				driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
				
				long start = System.currentTimeMillis();
		    	
				
				Thread.sleep(3000);
				//Number of rows
				List<WebElement> rowss = driver.findElements(By.xpath(excel.getData(3, 1078, 1)));
				int rowa = rowss.size()-1;
				ArrayList<Integer> array = new ArrayList<Integer>();
				for(int i =1; i <= rowa; i++)
				{
					String quan = driver.findElement(By.xpath(excel.getData(2, 10, 1))).getText().replaceAll("[a-zA-Z $ ₹ , :]", "");
					Integer dquan = Integer.parseInt(quan);
					//int quantity = quan.intValue();
					array.add(dquan);
				}
				Collections.sort(array);
				for(int i =0; i < rowa; i++)
				{	int s = i+1;
					String e = driver.findElement(By.xpath(excel.getData(2, 11, 1))).getText().replaceAll("[a-zA-Z $ ₹ , :]", "");
					double actual = Double.parseDouble(e);
					double expect = array.get(i);
					if(actual != expect)
					{
						test.log(LogStatus.PASS, "Sorted by Quantity is Fail");
					}
				}
				
				WebDriverWait wait1=new WebDriverWait(driver, 30);
				WebElement ele11=driver.findElement(By.xpath(excel.getData(3, 705, 1)));
				
				
					//Check weather the report is available for the selected time period
				if(driver.findElement(By.xpath(excel.getData(3, 704, 1))).isDisplayed())
					{
						test.log(LogStatus.FAIL, "Department Sale Report is not available for Specific Date");
					}
				
					else
				{
					test.log(LogStatus.PASS, "Department Sale Report available for Specific Date");
					
					if(wait1.until(ExpectedConditions.visibilityOf(ele11)).isDisplayed())
					{
					
					long finish = System.currentTimeMillis();
					long totalTime = finish- start; 
					System.out.println("Time in Millisecomds : "+totalTime);
					double ActualfinishTime = TimeUnit.MILLISECONDS.toMinutes(totalTime);
//					long ActualfinishTimeDouble = ((totalTime%86400)%3600)/60;
					long ActualfinishTimeDouble1 = (totalTime/1000)/6;
					System.out.println("MilSEc to Mins basic conversion : "+ActualfinishTimeDouble1);
//					test.log(LogStatus.INFO, "Total Time : "+ActualfinishTimeDouble1);
					test.log(LogStatus.INFO, "Time in Millisecomds : "+totalTime);
					
					}
					Thread.sleep(3000);
					//Check Weather the Top 5 department sale available or not
					if(driver.findElement(By.xpath(excel.getData(3, 705, 1))).isDisplayed())
					{
						test.log(LogStatus.PASS, "Here Top 5 Department Sale Report available for Specific Date");
							
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
						test.log(LogStatus.FAIL, "Here Top 5 Department Sale Report not available for Specific Date");
					}
					
					Thread.sleep(5000);
					//Check weather the report graph is available or not
					if(driver.findElement(By.xpath(excel.getData(3, 710, 1))).isDisplayed())
					{
						test.log(LogStatus.PASS, "There is Graphical type of department sale report is available for Specific Date");
					}
					else
					{
						test.log(LogStatus.FAIL, "Graphical report is not available for Specific Date");
					}
					
					driver.findElement(By.tagName("html")).sendKeys(Keys.END); 
					
					Thread.sleep(5000);
					//Check weather the table format report is available or not
					if(driver.findElement(By.xpath(excel.getData(3, 1077, 1))).isDisplayed())
					{
						test.log(LogStatus.PASS, "Table Format Report is available for Specific Date");
						
						Thread.sleep(3000);
				        //No.of rows
				        List<WebElement> rows = driver.findElements(By.xpath(excel.getData(2, 29, 1)));
				        
		/*	        //Print number of Rows
				        System.out.println("Number of Rows are : "+rows);*/
				        
			        	
			        	//Replace all commo's with empty space
			        	String expected1 = Utility.getReportPropertyUser("Sale_Report_Sale_Amount").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Sale_Amount element into float value
			        	float expect1 = Float.parseFloat(expected1);  
			        		        	
			        	//Replace all commo's with empty space
			        	String expected2 = Utility.getReportPropertyUser("Sale_Report_Quantity").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Quantity element into float value
			        	float expect2 = Float.parseFloat(expected2);  
			        	
			        	//Replace all commo's with empty space
			        	String expected3 = Utility.getReportPropertyUser("Sale_Report_Tax").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Tax element into float value
			        	float expect3 = Float.parseFloat(expected3);  
		
			        	//Replace all commo's with empty space
			        	String expected4 = Utility.getReportPropertyUser("Sale_Report_Discount").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Discount element into float value
			        	float expect4 = Float.parseFloat(expected4);  
			        	
			        	//Replace all commo's with empty space
			        	String expected5 = Utility.getReportPropertyUser("Sale_Report_Percentage_Of_Sale").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Percentage_Of_Sale element into float value
			        	float expect5 = Float.parseFloat(expected5);                              
				        
				        //Check weather the Sale Amount Report is correct or not
				        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Sale_Amount")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Department Sale reports are same for Sale Amount");
				        	
				        	//Get the Total value of Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Sale Amount Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Sale Amount Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Sale Amount Value is : "+ actual);
				        }
				        
						else if(expect1 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Check Count
				        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText();
		
				        	System.out.println("The Actual Sale Amount value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Sale Amount value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Department Sale reports are different for Sale Amount");
				        	
				        	//Get the Total value of Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the sale amount Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect1;
				        	
				        	//Print the different value
				        	System.out.println("Sale Amount Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Sale Amount Value different is : "+different);	
				        }
				        
				        //Check weather the Quantity Report is correct or not
				        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Quantity')]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Quantity")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Department Sale reports are same for Quantity");
				        	
				        	//Get the Total value of Quantity
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Quantity')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Quantity Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Quantity Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Quantity Value is : "+ actual);
				        }
				        
						else if(expect2 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Quantity
				        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Quantity')]/span")).getText();
		
				        	System.out.println("The Actual Quantity value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Quantity value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Department Sale reports are different for Quantity");
				        	
				        	//Get the Total value of Quantity
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Quantity')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Quantity Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Get the different
				        	float different = actual - expect2;
				        	
				        	//Print the different value
				        	System.out.println("Quantity Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Quantity Value different is : "+different);	
				        }
				        
				        //Check weather the Tax Report is correct or not
				        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Tax')]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Tax")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Department Sale reports are same for Tax");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Tax')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");   	
		
				        	//Convert the String value of the Tax Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Tax Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Tax Value is : "+ actual);
				        }
				        
						else if(expect3 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Tax
				        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Tax')]/span")).getText();
		
				        	System.out.println("The Actual Tax value is : "+actualText);		 
				        	
				        	test.log(LogStatus.INFO, "The Actual Tax value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Department Sale reports are different for Tax");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Tax')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Tax Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect3;
				        	
				        	//Print the different value
				        	System.out.println("Tax Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Tax Value different is : "+different);	
				        }
				        
				        //Check weather the Discount Report is correct or not
				        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Discount')]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Discount")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Department Sale reports are same for Discount");
				        	
				        	//Get the Total value of Discount
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Discount')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Discount Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Discount Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Discount Value is : "+ actual);
				        }
				        
						else if(expect4 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Discount
				        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Discount')]/span")).getText();
		
				        	System.out.println("The Actual Discount value is : "+actualText);	
				        	
				        	test.log(LogStatus.INFO, "The Actual Discount value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Department Sale reports are different for Discount");
				        	
				        	//Get the Total value of Discount
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Discount')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Discount Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect4;
				        	
				        	//Print the different value
				        	System.out.println("Discount Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Discount Value different is : "+different);	
				        }
				        
				        //Check weather the Percentage Of Sale Report is correct or not
				        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'% of Sale')]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Percentage_Of_Sale")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Department Sale reports are same for Percentage Of Sale");
				        	
				        	//Get the Total value of Percentage Of Sale
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'% of Sale')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Percentage of Sale Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Percentage Of Sale Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Percentage Of Sale Value is : "+ actual);
				        }
				        
						else if(expect5 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Percentage Of Sale
				        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'% of Sale')]/span")).getText();
		
				        	System.out.println("The Actual Percentage Of Sale value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Percentage Of Sale value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Department Sale reports are different for Percentage Of Sale");
				        	
				        	//Get the Total value of Percentage Of Sale
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'% of Sale')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Percentage Of Sale Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect5;
				        	
				        	//Print the different value
				        	System.out.println("Percentage Of Sale Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Percentage Of Sale Value different is : "+different);	
				        }
				        
				        //Check weather the Rounding Off Report is correct or not
//				        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Rounding Off')]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Rounding_Off")))
//				        {
//				        	test.log(LogStatus.PASS, "Actual and Expected Department Sale reports are same for Rounding Off");
//				        	
//				        	//Get the Total value of Rounding Off
//				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Rounding Off')]/span")).getText();
//				        	
//				        	//Replace all commo's with empty space
//				        	String actualText= actualText1.replace(",", "");
//				        	
//				        	//Convert the String value of the Rounding Off Total element into float value
//				        	float actual = Float.parseFloat(actualText);
//				        	
//				        	//Print the actual value
//				        	System.out.println("The Actual Rounding Off Value is : "+actual);
//				        	
//				        	test.log(LogStatus.PASS, "The Actual Rounding Off Value is : "+ actual);
//				        }
//				        
//						else if(expect1 == unknownValue)
//						{
//							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
//							
//				        	//Get the Total value of Rounding Off
//				        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Rounding Off')]/span")).getText();
//		
//				        	System.out.println("The Actual Rounding Off value is : "+actualText);
//				        	
//				        	test.log(LogStatus.INFO, "The Actual Rounding Off value is : "+actualText);
//						}
//				        
//				        else
//				        {
//				        	test.log(LogStatus.FAIL, "Actual and Expected Department Sale reports are different for Rounding Off");
//				        	
//				        	//Get the Total value of Rounding Off
//				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Rounding Off')]/span")).getText();
//				        	
//				        	//Replace all commo's with empty space
//				        	String actualText= actualText1.replace(",", "");
//				        	
//				        	//Convert the String value of the Rounding Off Total element into float value
//				        	float actual = Float.parseFloat(actualText);
//				        			        	
//				        	//Get the different
//				        	float different = actual - expect1;
//				        	
//				        	//Print the different value
//				        	System.out.println("Rounding Off Value different is : "+different);
//				        	
//				        	test.log(LogStatus.FAIL, "Rounding Off Value different is : "+different);	
//				        }
				        
				    	String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));
			
					}
					else
					{
						test.log(LogStatus.FAIL, "Table Format Report is not available for the Required Date Range");
					
						String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));
					}
				}wb.close();
				
				driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			}
		
			@Test(priority=6,enabled = false)
			public void Department_Sale_method_Report_For_Specific_Date_With_Quantity_Sort_And_Dynamic_Report(WebDriver driver) throws Exception
			{
				
				File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

				Thread.sleep(2000);
				WebElement html = driver.findElement(By.tagName("html"));
				html.sendKeys(Keys.HOME);
		
				Thread.sleep(5000);
				//Click the Department option
				driver.findElement(By.xpath(excel.getData(3, 1067, 1))).click();
				//Enter the required department
				driver.findElement(By.xpath(excel.getData(3, 1068, 1))).sendKeys("All");
				//Press the Enter Key
				driver.findElement(By.xpath(excel.getData(3, 1068, 1))).sendKeys(Keys.ENTER);
			
				Thread.sleep(3000);
				//Click the Time Period option
				driver.findElement(By.xpath(excel.getData(3, 736, 1))).click();
				//Enter the required Time Period
				driver.findElement(By.xpath(excel.getData(3, 737, 1))).sendKeys("Date Range");
				//Press the Enter Key
				driver.findElement(By.xpath(excel.getData(3, 737, 1))).sendKeys(Keys.ENTER);
				
				Thread.sleep(2000);
				//Clear the date field
				driver.findElement(By.xpath(excel.getData(3, 738, 1))).clear();
				//Enter the date
				driver.findElement(By.xpath(excel.getData(3, 738, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
		
				Thread.sleep(2000);
				//Clear the date field
				driver.findElement(By.xpath(excel.getData(3, 739, 1))).clear();
				//Enter the date
				driver.findElement(By.xpath(excel.getData(3, 739, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
				
				//Check whether the Quantity sort is enabled or not
				if(driver.findElement(By.xpath(excel.getData(3, 1356, 1))).isSelected())
				{
					
				}
				else
				{
					//Click the Quantity Sort option
					Actions act = new Actions(driver);
					act.moveToElement(driver.findElement(By.xpath(excel.getData(2, 9, 1)))).click().build().perform();			
				}
				Thread.sleep(2000);
				//Click the Run Button
				driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
				
				long start = System.currentTimeMillis();
		    	
				
				Thread.sleep(3000);
				for(int i = 1; i <= 8; i++)
				{
					driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
				}
				
				
					if(driver.findElement(By.xpath(excel.getData(3, 704, 1))).isDisplayed())
					{
						test.log(LogStatus.FAIL, "Department Sale Report is not available for the Required Date Range");
					
						String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));
					}
				
				else
				{
					//Check Whether all the field is selected or not
					if(driver.findElement(By.xpath(excel.getData(2, 13, 1))).isSelected()
							&&driver.findElement(By.xpath(excel.getData(2, 14, 1))).isSelected()
							&&driver.findElement(By.xpath(excel.getData(2, 15, 1))).isSelected()
							&&driver.findElement(By.xpath(excel.getData(2, 16, 1))).isSelected()
							&&driver.findElement(By.xpath(excel.getData(2, 17, 1))).isSelected())
					{
						//Check weather the table format report is available or not
						
						WebDriverWait wait1=new WebDriverWait(driver, 30);
						WebElement ele11=driver.findElement(By.xpath(excel.getData(3, 711, 1)));
						
						if(wait1.until(ExpectedConditions.visibilityOf(ele11)).isDisplayed())
						{
							test.log(LogStatus.PASS, "Table Format Report is available for the Required Date Range");
							
							long finish = System.currentTimeMillis();
							long totalTime = finish- start; 
							System.out.println("Time in Millisecomds : "+totalTime);
							double ActualfinishTime = TimeUnit.MILLISECONDS.toMinutes(totalTime);
//							long ActualfinishTimeDouble = ((totalTime%86400)%3600)/60;
							long ActualfinishTimeDouble1 = (totalTime/1000)/6;
							System.out.println("MilSEc to Mins basic conversion : "+ActualfinishTimeDouble1);
//							test.log(LogStatus.INFO, "Total Time : "+ActualfinishTimeDouble1);
							test.log(LogStatus.INFO, "Time in Millisecomds : "+totalTime);
							
							//Check whether all the filed is available or not
							if(driver.findElement(By.xpath(excel.getData(2, 19, 1))).isDisplayed()
									&&driver.findElement(By.xpath(excel.getData(2, 20, 1))).isDisplayed()
									&&driver.findElement(By.xpath(excel.getData(2, 21, 1))).isDisplayed()
									&&driver.findElement(By.xpath(excel.getData(2, 22, 1))).isDisplayed()
									&&driver.findElement(By.xpath(excel.getData(2, 23, 1))).isDisplayed())
							{
								test.log(LogStatus.PASS, "All the Neccessary fields are displayed");
							}
							else
							{
								test.log(LogStatus.FAIL, "Neccessary fields are not displayed");
							}
							
							//Disable the required option from enable to disable Sale Amount Option
							if(driver.findElement(By.xpath(excel.getData(2, 13, 1))).isSelected())
							{
								//Click the Enabled Sale Amount Option
								Actions act = new Actions(driver);
								act.moveToElement(driver.findElement(By.xpath(excel.getData(2, 24, 1)))).click().build().perform();
								try
								{
									//Check whether the Sale Amount Option is displayed or not
									if(driver.findElement(By.xpath(excel.getData(2, 23, 1))).isDisplayed())
									{
										test.log(LogStatus.FAIL, "Sale Amount is displayed");
									}
								}
								catch(Exception e)
								{
									test.log(LogStatus.PASS, "Sale Amount is not displayed");
								}
							}
							else
							{
								test.log(LogStatus.FAIL, "Sale Amount Option is Disabled When user enter the required date for date range");
							}
							
							//Disable the required option from enable to disable Quantity Option
							if(driver.findElement(By.xpath(excel.getData(2, 14, 1))).isSelected())
							{
								//Click the Enabled Quantity Option
								Actions act = new Actions(driver);
								act.moveToElement(driver.findElement(By.xpath(excel.getData(2, 25, 1)))).click().build().perform();
								try
								{
									//Check whether the Quantity Option is displayed or not
									if(driver.findElement(By.xpath(excel.getData(2, 22, 1))).isDisplayed())
									{
										test.log(LogStatus.FAIL, "Quantity is displayed");
									}
								}
								catch(Exception e)
								{
									test.log(LogStatus.PASS, "Quantity is not displayed");
								}
							}
							else
							{
								test.log(LogStatus.FAIL, "Quantity Option is Disabled When user enter the required date for date range");
							}
							
							//Disable the required option from enable to disable Tax Option
							if(driver.findElement(By.xpath(excel.getData(2, 15, 1))).isSelected())
							{
								//Click the Enabled Tax Option
								Actions act = new Actions(driver);
								act.moveToElement(driver.findElement(By.xpath(excel.getData(2, 26, 1)))).click().build().perform();
								try
								{
									//Check whether the Tax Option is displayed or not
									if(driver.findElement(By.xpath(excel.getData(2, 21, 1))).isDisplayed())
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
							
							//Disable the required option from enable to disable Discount Option
							if(driver.findElement(By.xpath(excel.getData(2, 16, 1))).isSelected())
							{
								//Click the Enabled Discount Option
								Actions act = new Actions(driver);
								act.moveToElement(driver.findElement(By.xpath(excel.getData(2, 27, 1)))).click().build().perform();
								try
								{
									//Check whether the Discount Option is displayed or not
									if(driver.findElement(By.xpath(excel.getData(2, 20, 1))).isDisplayed())
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
							
							//Disable the required option from enable to disable % Of Sale Option
							if(driver.findElement(By.xpath(excel.getData(2, 17, 1))).isSelected())
							{
								//Click the Enabled % Of Sale Option
								Actions act = new Actions(driver);
								act.moveToElement(driver.findElement(By.xpath(excel.getData(2, 28, 1)))).click().build().perform();
								try
								{
									//Check whether the % Of Sale Option is displayed or not
									if(driver.findElement(By.xpath(excel.getData(2, 19, 1))).isDisplayed())
									{
										test.log(LogStatus.FAIL, "% Of Sale is displayed");
									}
								}
								catch(Exception e)
								{
									test.log(LogStatus.PASS, "% Of Sale is not displayed");
								}
							}
							else
							{
								test.log(LogStatus.FAIL, "% Of Sale Option is Disabled When user enter the required date for date range");
							}
							//Enable the Sale Amount Option if it is in disabled status
							if(driver.findElement(By.xpath(excel.getData(2, 13, 1))).isSelected())
							{
								test.log(LogStatus.FAIL, "Sale Amount Option is Enabled after user click(Disable) the Sale Amount option");
			
							}
							else
							{
								//Click the Enabled Sale Amount Option
								Actions act = new Actions(driver);
								act.moveToElement(driver.findElement(By.xpath(excel.getData(2, 24, 1)))).click().build().perform();
								try
								{
									//Check whether the Sale Amount Option is displayed or not
									if(driver.findElement(By.xpath(excel.getData(2, 23, 1))).isDisplayed())
									{
										test.log(LogStatus.PASS, "Sale Amount is displayed");
									}
								}
								catch(Exception e)
								{
									test.log(LogStatus.FAIL, "Sale Amount is not displayed");
								}				}
							
							//Disable the required option from enable to disable Quantity Option
							if(driver.findElement(By.xpath(excel.getData(2, 14, 1))).isSelected())
							{
								test.log(LogStatus.FAIL, "Quantity Option is Enabled after user click(Disable) the Quantity option");
							}
							else
							{
								//Click the Enabled Quantity Option
								Actions act = new Actions(driver);
								act.moveToElement(driver.findElement(By.xpath(excel.getData(2, 25, 1)))).click().build().perform();
								try
								{
									//Check whether the Quantity Option is displayed or not
									if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'Quantity')]")).isDisplayed())
									{
										test.log(LogStatus.PASS, "Quantity is displayed");
									}
								}
								catch(Exception e)
								{
									test.log(LogStatus.FAIL, "Quantity is not displayed");
								}
			
							}
							
							//Disable the required option from enable to disable Tax Option
							if(driver.findElement(By.xpath(excel.getData(2, 15, 1))).isSelected())
							{
								test.log(LogStatus.FAIL, "Tax Option is Enabled after user click(Disable) the Tax option");
							}
							else
							{
			
								//Click the Enabled Tax Option
								Actions act = new Actions(driver);
								act.moveToElement(driver.findElement(By.xpath(excel.getData(2, 26, 1)))).click().build().perform();
								try
								{
									//Check whether the Tax Option is displayed or not
									if(driver.findElement(By.xpath(excel.getData(2, 21, 1))).isDisplayed())
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
							if(driver.findElement(By.xpath(excel.getData(2, 16, 1))).isSelected())
							{
								test.log(LogStatus.FAIL, "Discount Option is Enabled after user click(Disable) the Discount option");
			
							}
							else
							{
			
								//Click the Enabled Discount Option
								Actions act = new Actions(driver);
								act.moveToElement(driver.findElement(By.xpath(excel.getData(2, 27, 1)))).click().build().perform();
								try
								{
									//Check whether the Discount Option is displayed or not
									if(driver.findElement(By.xpath(excel.getData(2, 20, 1))).isDisplayed())
									{
										test.log(LogStatus.PASS, "Discount is displayed");
									}
								}
								catch(Exception e)
								{
									test.log(LogStatus.FAIL, "Discount is not displayed");
								}
							}
							
							//Disable the required option from enable to disable % Of Sale Option
							if(driver.findElement(By.xpath(excel.getData(2, 17, 1))).isSelected())
							{
								test.log(LogStatus.FAIL, "% Of Sale Option is Enabled after user click(Disable) the % of Sale option");
			
							}
							else
							{
			
								//Click the Enabled % Of Sale Option
								Actions act = new Actions(driver);
								act.moveToElement(driver.findElement(By.xpath(excel.getData(2, 28, 1)))).click().build().perform();
								try
								{
									//Check whether the % Of Sale Option is displayed or not
									if(driver.findElement(By.xpath(excel.getData(2, 19, 1))).isDisplayed())
									{
										test.log(LogStatus.PASS, "% Of Sale is displayed");
									}
								}
								catch(Exception e)
								{
									test.log(LogStatus.FAIL, "% Of Sale is not displayed");
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
				}wb.close();
			}
			
			@Test(priority=5,enabled = false)
			public void Department_Sale_method_Report_For_Specific_Date_Without_Quantity_Sort_With_Dynamic_Report(WebDriver driver) throws Exception
		{
				File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

				
			Thread.sleep(2000);
			WebElement html = driver.findElement(By.tagName("html"));
			html.sendKeys(Keys.HOME);
	
			Thread.sleep(5000);
			//Click the Department option
			driver.findElement(By.xpath(excel.getData(3, 1067, 1))).click();
			//Enter the required department
			driver.findElement(By.xpath(excel.getData(3, 1068, 1))).sendKeys("All");
			//Press the Enter Key
			driver.findElement(By.xpath(excel.getData(3, 1068, 1))).sendKeys(Keys.ENTER);
		
			Thread.sleep(3000);
			//Click the Time Period option
			driver.findElement(By.xpath(excel.getData(3, 736, 1))).click();
			//Enter the required Time Period
			driver.findElement(By.xpath(excel.getData(3, 737, 1))).sendKeys("Date Range");
			//Press the Enter Key
			driver.findElement(By.xpath(excel.getData(3, 737, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(2000);
			//Clear the date field
			driver.findElement(By.xpath(excel.getData(3, 738, 1))).clear();
			//Enter the date
			driver.findElement(By.xpath(excel.getData(3, 738, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
	
			Thread.sleep(2000);
			//Clear the date field
			driver.findElement(By.xpath(excel.getData(3, 739, 1))).clear();
			//Enter the date
			driver.findElement(By.xpath(excel.getData(3, 739, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
			
			//Check whether the Quantity sort is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 1356, 1))).isSelected())
			{
				
			}
			else
			{
				//Click the Quantity Sort option
				Actions act = new Actions(driver);
				act.moveToElement(driver.findElement(By.xpath(excel.getData(2, 9, 1)))).click().build().perform();			
			}
			Thread.sleep(2000);
			//Click the Run Button
			driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
			
			long start = System.currentTimeMillis();
	    	
			
			Thread.sleep(3000);
			for(int i = 1; i <= 8; i++)
			{
				driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
			}
			
			
			
			
				if(driver.findElement(By.xpath(excel.getData(3, 704, 1))).isDisplayed())
				{
					test.log(LogStatus.FAIL, "Department Sale Report is not available for the Required Date Range");
				}
			
			else
			{
						//Check Whether all the field is selected or not
						if(driver.findElement(By.xpath(excel.getData(2, 13, 1))).isSelected()
								&&driver.findElement(By.xpath(excel.getData(2, 14, 1))).isSelected()
								&&driver.findElement(By.xpath(excel.getData(2, 15, 1))).isSelected()
								&&driver.findElement(By.xpath(excel.getData(2, 16, 1))).isSelected()
								&&driver.findElement(By.xpath(excel.getData(2, 17, 1))).isSelected())
						{
							
							WebDriverWait wait1=new WebDriverWait(driver, 30);
							WebElement ele11=driver.findElement(By.xpath(excel.getData(3, 711, 1)));
							
							//Check weather the table format report is available or not
							if(wait1.until(ExpectedConditions.visibilityOf(ele11)).isDisplayed())
							{
								test.log(LogStatus.PASS, "Table Format Report is available for the Required Date Range");
								
								long finish = System.currentTimeMillis();
								long totalTime = finish- start; 
								System.out.println("Time in Millisecomds : "+totalTime);
								double ActualfinishTime = TimeUnit.MILLISECONDS.toMinutes(totalTime);
//								long ActualfinishTimeDouble = ((totalTime%86400)%3600)/60;
								long ActualfinishTimeDouble1 = (totalTime/1000)/6;
								System.out.println("MilSEc to Mins basic conversion : "+ActualfinishTimeDouble1);
//								test.log(LogStatus.INFO, "Total Time : "+ActualfinishTimeDouble1);
								test.log(LogStatus.INFO, "Time in Millisecomds : "+totalTime);
								
								//Check whether all the filed is available or not
								if(driver.findElement(By.xpath(excel.getData(2, 19, 1))).isDisplayed()
										&&driver.findElement(By.xpath(excel.getData(2, 20, 1))).isDisplayed()
										&&driver.findElement(By.xpath(excel.getData(2, 21, 1))).isDisplayed()
										&&driver.findElement(By.xpath(excel.getData(2, 22, 1))).isDisplayed()
										&&driver.findElement(By.xpath(excel.getData(2, 23, 1))).isDisplayed())
								{
									test.log(LogStatus.PASS, "All the Neccessary fields are displayed");
								}
								else
								{
									test.log(LogStatus.FAIL, "Neccessary fields are not displayed");
								}
								
								//Disable the required option from enable to disable Sale Amount Option
								if(driver.findElement(By.xpath(excel.getData(2, 13, 1))).isSelected())
								{
									//Click the Enabled Sale Amount Option
									Actions act = new Actions(driver);
									act.moveToElement(driver.findElement(By.xpath(excel.getData(2, 24, 1)))).click().build().perform();
									try
									{
										//Check whether the Sale Amount Option is displayed or not
										if(driver.findElement(By.xpath(excel.getData(2, 23, 1))).isDisplayed())
										{
											test.log(LogStatus.FAIL, "Sale Amount is displayed");
										}
									}
									catch(Exception e)
									{
										test.log(LogStatus.PASS, "Sale Amount is not displayed");
									}
								}
								else
								{
									test.log(LogStatus.FAIL, "Sale Amount Option is Disabled When user enter the required date for date range");
								}
								
								//Disable the required option from enable to disable Quantity Option
								if(driver.findElement(By.xpath(excel.getData(2, 14, 1))).isSelected())
								{
									//Click the Enabled Quantity Option
									Actions act = new Actions(driver);
									act.moveToElement(driver.findElement(By.xpath(excel.getData(2, 25, 1)))).click().build().perform();
									try
									{
										//Check whether the Quantity Option is displayed or not
										if(driver.findElement(By.xpath(excel.getData(2, 22, 1))).isDisplayed())
										{
											test.log(LogStatus.FAIL, "Quantity is displayed");
										}
									}
									catch(Exception e)
									{
										test.log(LogStatus.PASS, "Quantity is not displayed");
									}
								}
								else
								{
									test.log(LogStatus.FAIL, "Quantity Option is Disabled When user enter the required date for date range");
								}
								
								//Disable the required option from enable to disable Tax Option
								if(driver.findElement(By.xpath(excel.getData(2, 15, 1))).isSelected())
								{
									//Click the Enabled Tax Option
									Actions act = new Actions(driver);
									act.moveToElement(driver.findElement(By.xpath(excel.getData(2, 26, 1)))).click().build().perform();
									try
									{
										//Check whether the Tax Option is displayed or not
										if(driver.findElement(By.xpath(excel.getData(2, 21, 1))).isDisplayed())
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
								
								//Disable the required option from enable to disable Discount Option
								if(driver.findElement(By.xpath(excel.getData(2, 16, 1))).isSelected())
								{
									//Click the Enabled Discount Option
									Actions act = new Actions(driver);
									act.moveToElement(driver.findElement(By.xpath(excel.getData(2, 27, 1)))).click().build().perform();
									try
									{
										//Check whether the Discount Option is displayed or not
										if(driver.findElement(By.xpath(excel.getData(2, 20, 1))).isDisplayed())
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
								
								//Disable the required option from enable to disable % Of Sale Option
								if(driver.findElement(By.xpath(excel.getData(2, 17, 1))).isSelected())
								{
									//Click the Enabled % Of Sale Option
									Actions act = new Actions(driver);
									act.moveToElement(driver.findElement(By.xpath(excel.getData(2, 28, 1)))).click().build().perform();
									try
									{
										//Check whether the % Of Sale Option is displayed or not
										if(driver.findElement(By.xpath(excel.getData(2, 19, 1))).isDisplayed())
										{
											test.log(LogStatus.FAIL, "% Of Sale is displayed");
										}
									}
									catch(Exception e)
									{
										test.log(LogStatus.PASS, "% Of Sale is not displayed");
									}
								}
								else
								{
									test.log(LogStatus.FAIL, "% Of Sale Option is Disabled When user enter the required date for date range");
								}
								//Enable the Sale Amount Option if it is in disabled status
								if(driver.findElement(By.xpath(excel.getData(2, 13, 1))).isSelected())
								{
									test.log(LogStatus.FAIL, "Sale Amount Option is Enabled after user click(Disable) the Sale Amount option");
				
								}
								else
								{
									//Click the Enabled Sale Amount Option
									Actions act = new Actions(driver);
									act.moveToElement(driver.findElement(By.xpath(excel.getData(2, 24, 1)))).click().build().perform();
									try
									{
										//Check whether the Sale Amount Option is displayed or not
										if(driver.findElement(By.xpath(excel.getData(2, 23, 1))).isDisplayed())
										{
											test.log(LogStatus.PASS, "Sale Amount is displayed");
										}
									}
									catch(Exception e)
									{
										test.log(LogStatus.FAIL, "Sale Amount is not displayed");
									}				}
								
								//Disable the required option from enable to disable Quantity Option
								if(driver.findElement(By.xpath(excel.getData(2, 14, 1))).isSelected())
								{
									test.log(LogStatus.FAIL, "Quantity Option is Enabled after user click(Disable) the Quantity option");
								}
								else
								{
									//Click the Enabled Quantity Option
									Actions act = new Actions(driver);
									act.moveToElement(driver.findElement(By.xpath(excel.getData(2, 25, 1)))).click().build().perform();
									try
									{
										//Check whether the Quantity Option is displayed or not
										if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'Quantity')]")).isDisplayed())
										{
											test.log(LogStatus.PASS, "Quantity is displayed");
										}
									}
									catch(Exception e)
									{
										test.log(LogStatus.FAIL, "Quantity is not displayed");
									}
				
								}
								
								//Disable the required option from enable to disable Tax Option
								if(driver.findElement(By.xpath(excel.getData(2, 15, 1))).isSelected())
								{
									test.log(LogStatus.FAIL, "Tax Option is Enabled after user click(Disable) the Tax option");
								}
								else
								{
				
									//Click the Enabled Tax Option
									Actions act = new Actions(driver);
									act.moveToElement(driver.findElement(By.xpath(excel.getData(2, 26, 1)))).click().build().perform();
									try
									{
										//Check whether the Tax Option is displayed or not
										if(driver.findElement(By.xpath(excel.getData(2, 21, 1))).isDisplayed())
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
								if(driver.findElement(By.xpath(excel.getData(2, 16, 1))).isSelected())
								{
									test.log(LogStatus.FAIL, "Discount Option is Enabled after user click(Disable) the Discount option");
				
								}
								else
								{
				
									//Click the Enabled Discount Option
									Actions act = new Actions(driver);
									act.moveToElement(driver.findElement(By.xpath(excel.getData(2, 27, 1)))).click().build().perform();
									try
									{
										//Check whether the Discount Option is displayed or not
										if(driver.findElement(By.xpath(excel.getData(2, 20, 1))).isDisplayed())
										{
											test.log(LogStatus.PASS, "Discount is displayed");
										}
									}
									catch(Exception e)
									{
										test.log(LogStatus.FAIL, "Discount is not displayed");
									}
								}
								
								//Disable the required option from enable to disable % Of Sale Option
								if(driver.findElement(By.xpath(excel.getData(2, 17, 1))).isSelected())
								{
									test.log(LogStatus.FAIL, "% Of Sale Option is Enabled after user click(Disable) the % of Sale option");
				
								}
								else
								{
				
									//Click the Enabled % Of Sale Option
									Actions act = new Actions(driver);
									act.moveToElement(driver.findElement(By.xpath(excel.getData(2, 28, 1)))).click().build().perform();
									try
									{
										//Check whether the % Of Sale Option is displayed or not
										if(driver.findElement(By.xpath(excel.getData(2, 19, 1))).isDisplayed())
										{
											test.log(LogStatus.PASS, "% Of Sale is displayed");
										}
									}
									catch(Exception e)
									{
										test.log(LogStatus.FAIL, "% Of Sale is not displayed");
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
			}	wb.close();
		}

	
}

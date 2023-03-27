package epicList_Chrome;

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
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import epicList_Chrome.Utility;
import newReadExcelFile_New.ExcelDataConfig;

public class Verify_CategorySale_Report {
	public WebDriver driver;
	
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Verify_CategorySale_Report");

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
				Browser a = new Browser();
				a.UPOS_login(driver, test);
			} else {
				Browser a = new Browser();
				a.Linga_login(driver, test);
			}
		}

		@Test(priority = 500)
		public void logout() throws Exception {
			Browser a = new Browser();
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
			Category_Sale_method_openpage_Report(driver);
			Category_Sale_method__Report_For_Specific_Date(driver);
			Category_Sale_method__Report_For_Specific_Date_With_Quantity_Sort(driver);
			Category_Sale_method__Report_For_Specific_Date_WithOut_Quantity_Sort_With_Dynamic_Report(driver);
			Category_Sale_method__Report_For_Specific_Date_With_Quantity_Sort_And_Dynamic_Report(driver);
			Thread.sleep(1500);
		}
	
			@Test(priority=20,enabled = false)
			public void Category_Sale_method_openpage_Report(WebDriver driver) throws Exception
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
				WebElement element = driver.findElement(By.xpath("//span[.='Sale']"));
				//Scroll the page till the Sale option present
				je.executeScript("arguments[0].scrollIntoView(true);",element); 
		        //Click the Sale Option		
				driver.findElement(By.xpath("//span[.='Sale']")).click();*/
				
				Thread.sleep(5000);
				//Enter the Categories Url
				driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"report/categorySale");
				
				Thread.sleep(5000);
				try
				{
				//Check Category Sale Report page opened or not
				if(driver.findElement(By.xpath(excel.getData(3, 1462, 1))).getText().equalsIgnoreCase("Category Sale"))
				{
					test.log(LogStatus.PASS, "Category Sale report page loaded Successfully");
				
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));
				}
				else
				{
					test.log(LogStatus.FAIL, "Category Sale report page loaded Failed");
				
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
				Thread.sleep(5000);wb.close();
			}
			
			@Test(priority=21,enabled = false)
			public void Category_Sale_method__Report_For_Specific_Date(WebDriver driver) throws Exception
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
				//Click the Category option
				driver.findElement(By.xpath(excel.getData(3, 1350, 1))).click();
				//Enter the required Category
				driver.findElement(By.xpath(excel.getData(3, 1351, 1))).sendKeys("All");
				//Press the Enter Key
				driver.findElement(By.xpath(excel.getData(3, 1351, 1))).sendKeys(Keys.ENTER);
			
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
				driver.findElement(By.xpath(excel.getData(3, 738, 1))).sendKeys(Utility.getReportProperty("Date_Range_From"));
				

				Thread.sleep(2000);
				//Clear the date field
				driver.findElement(By.xpath(excel.getData(3, 739, 1))).clear();
				//Enter the date
				driver.findElement(By.xpath(excel.getData(3, 739, 1))).sendKeys(Utility.getReportProperty("Date_Range_To"));
				
				
				//Check whether the Quantity sort is enabled or not
				if(driver.findElement(By.xpath(excel.getData(3, 1356, 1))).isSelected())
				{
					//Click the Quantity Sort option
					Actions act = new Actions(driver);
					act.moveToElement(driver.findElement(By.xpath(excel.getData(3, 1356, 1)))).click().build().perform();
				}
				else
				{
				}
				
				Thread.sleep(2000);
				//Click the Run Button
				driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
				
				Thread.sleep(3000);
				try
				{
					//Check weather the report is available for the selected time period
					if(driver.findElement(By.xpath(excel.getData(3, 704, 1))).isDisplayed())
					{
						test.log(LogStatus.FAIL, "Category Sale Report is not available for the Required Date Range");
					
						String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));
					
					}
				}
				catch(Exception gfh)
				{
					test.log(LogStatus.PASS, "Category Sale Report available for the Required Date Range");
					
					Thread.sleep(3000);
					//Check Weather the Top 5 category sale available or not
					if(driver.findElement(By.xpath(excel.getData(3, 705, 1))).isDisplayed())
					{
						test.log(LogStatus.PASS, "Here Top 5 Category Sale Report available for the Required Date Range");
						
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
						test.log(LogStatus.FAIL, "Here Top 5 Category	 Sale Report not available for the Required Date Range");
					}
					
					Thread.sleep(3000);
					//Check weather the report graph is available or not
					if(driver.findElement(By.xpath(excel.getData(3, 710, 1))).isDisplayed())
					{
						test.log(LogStatus.PASS, "There is Graphical type of category sale report is available for the Required Date Range");
					}
					else
					{
						test.log(LogStatus.FAIL, "Graphical report is not available for the Required Date Range");
					}
					
					Thread.sleep(3000);
					//Check weather the table format report is available or not
					if(driver.findElement(By.xpath(excel.getData(3, 1535, 1))).isDisplayed())
					{
						test.log(LogStatus.PASS, "Table Format Report is available for the Required Date Range");
						
						Thread.sleep(3000);
				        //No.of rows
				        List<WebElement> rows = driver.findElements(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr"));
				        
		/*		        //Print number of Rows
				        System.out.println("Number of Rows are : "+rows);*/
				        
			        	//Replace all commo's with empty space
			        	String expected1 = Utility.getReportProperty("Sale_Report_Sale_Amount").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Sale_Amount element into float value
			        	float expect1 = Float.parseFloat(expected1);  
			        	
			        	//Replace all commo's with empty space
			        	String expected2 = Utility.getReportProperty("Sale_Report_Quantity").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Quantity element into float value
			        	float expect2 = Float.parseFloat(expected2);  
			        	
			        	//Replace all commo's with empty space
			        	String expected3 = Utility.getReportProperty("Sale_Report_Tax").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Tax element into float value
			        	float expect3 = Float.parseFloat(expected3);  
			        	
			        	//Replace all commo's with empty space
			        	String expected4 = Utility.getReportProperty("Sale_Report_Discount").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Discount element into float value
			        	float expect4 = Float.parseFloat(expected4);  
				        
			        	//Replace all commo's with empty space
			        	String expected5 = Utility.getReportProperty("Sale_Report_Percentage_Of_Sale").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Percentage_Of_Sale element into float value
			        	float expect5 = Float.parseFloat(expected5);   
				        
				        //Check weather the Sale Amount Report is correct or not
				        if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[2]/span")).getText().equals(Utility.getReportProperty("Sale_Report_Sale_Amount")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Category Sale reports are same for Sale Amount");
				        	
				        	//Get the Total value of Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[2]/span")).getText();
				        	
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
							
				        	//Get the Total value of Sale Amount
				        	String actualText = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[2]/span")).getText();

				        	System.out.println("The Actual Sale Amount value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Sale Amount value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Category Sale reports are different for Sale Amount");
				        	
				        	//Get the Total value of Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[2]/span")).getText();
				        	
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
				        if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[3]/span")).getText().equals(Utility.getReportProperty("Sale_Report_Quantity")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Category Sale reports are same for Quantity");
				        	
				        	//Get the Total value of Quantity
				        	String actualText1 = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[3]/span")).getText();
				        	
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
				        	String actualText = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[3]/span")).getText();

				        	System.out.println("The Actual Quantity value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Quantity value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Category Sale reports are different for Quantity");
				        	
				        	//Get the Total value of Quantity
				        	String actualText1 = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[3]/span")).getText();
				        	
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
				        if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[4]/span")).getText().equals(Utility.getReportProperty("Sale_Report_Tax")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Category Sale reports are same for Tax");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[4]/span")).getText();
				        	
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
				        	String actualText = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[4]/span")).getText();

				        	System.out.println("The Actual Tax value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Tax value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Category Sale reports are different for Tax");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[4]/span")).getText();
				        	
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
				        if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[5]/span")).getText().equals(Utility.getReportProperty("Sale_Report_Discount")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Category Sale reports are same for Discount");
				        	
				        	//Get the Total value of Discount
				        	String actualText1 = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[5]/span")).getText();
				        	
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
				        	String actualText = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[5]/span")).getText();

				        	System.out.println("The Actual Discount value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Discount value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Category Sale reports are different for Discount");
				        	
				        	//Get the Total value of Discount
				        	String actualText1 = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[5]/span")).getText();
				        	
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
				        if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[6]/span")).getText().equals(Utility.getReportProperty("Sale_Report_Percentage_Of_Sale")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Category Sale reports are same for Percentage Of Sale");
				        	
				        	//Get the Total value of Percentage Of Sale
				        	String actualText1 = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[6]/span")).getText();
				        	
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
				        	String actualText = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[6]/span")).getText();

				        	System.out.println("The Actual Percentage Of Sale value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Percentage Of Sale value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Category Sale reports are different for Percentage Of Sale");
				        	
				        	//Get the Total value of Percentage Of Sale
				        	String actualText1 = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[6]/span")).getText();
				        	
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
				        driver.findElement(By.tagName("html")).sendKeys(Keys.END);
				    	String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));

					}
					else
					{
						test.log(LogStatus.FAIL, "Table Format Report is not available for the Required Date Range");wb.close();
					
						String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));

					}
					
				}
				driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			}

			@Test(priority=22,enabled = false)
			public void Category_Sale_method__Report_For_Specific_Date_With_Quantity_Sort(WebDriver driver) throws Exception
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
				//Click the Category option
				driver.findElement(By.xpath(excel.getData(3, 1350, 1))).click();
				//Enter the required Category
				driver.findElement(By.xpath(excel.getData(3, 1351, 1))).sendKeys("All");
				//Press the Enter Key
				driver.findElement(By.xpath(excel.getData(3, 1351, 1))).sendKeys(Keys.ENTER);
			
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
				driver.findElement(By.xpath(excel.getData(3, 738, 1))).sendKeys(Utility.getReportProperty("Date_Range_From"));
				

				Thread.sleep(2000);
				//Clear the date field
				driver.findElement(By.xpath(excel.getData(3, 739, 1))).clear();
				//Enter the date
				driver.findElement(By.xpath(excel.getData(3, 739, 1))).sendKeys(Utility.getReportProperty("Date_Range_To"));
				
				//Check whether the Quantity sort is enabled or not
				if(driver.findElement(By.xpath(excel.getData(3, 1356, 1))).isSelected())
				{
					
				}
				else
				{
					//Click the Quantity Sort option
					Actions act = new Actions(driver);
					act.moveToElement(driver.findElement(By.xpath(excel.getData(3, 1356, 1)))).click().build().perform();
				}
				
				Thread.sleep(2000);
				//Click the Run Button
				driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
				
				Thread.sleep(3000);
				//Number of rows
				List<WebElement> rowss = driver.findElements(By.xpath(excel.getData(3, 1475, 1)));
				int rowa = rowss.size()-1;
				ArrayList<Integer> array = new ArrayList<Integer>();
				for(int i =1; i <= rowa; i++)
				{
					String quan = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr["+i+"]/td[3]")).getText().replaceAll("[a-zA-Z $ ₹ , :]", "");
					Integer dquan = Integer.parseInt(quan);
					//int quantity = quan.intValue();
					array.add(dquan);
				}
				Collections.sort(array);
				for(int i =0; i < rowa; i++)
				{	int s = i+1;
					String e = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr["+s+"]/td[3]")).getText().replaceAll("[a-zA-Z $ ₹ , :]", "");
					double actual = Double.parseDouble(e);
					double expect = array.get(i);
					if(actual != expect)
					{
						test.log(LogStatus.PASS, "Sorted by Quantity is Fail");
					}
				}
				
				Thread.sleep(3000);
				try
				{
					//Check weather the report is available for the selected time period
					if(driver.findElement(By.xpath(excel.getData(3, 704, 1))).isDisplayed())
					{
						test.log(LogStatus.FAIL, "Category Sale Report is not available for the Required Date Range");
					}
				}
				catch(Exception fdfd)
				{
					test.log(LogStatus.PASS, "Category Sale Report available for the Required Date Range");
					
					Thread.sleep(3000);
					//Check Weather the Top 5 category sale available or not
					if(driver.findElement(By.xpath(excel.getData(3, 705, 1))).isDisplayed())
					{
						test.log(LogStatus.PASS, "Here Top 5 Category Sale Report available for the Required Date Range");
						
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
						test.log(LogStatus.FAIL, "Here Top 5 Category	 Sale Report not available for the Required Date Range");
					}
					
					Thread.sleep(3000);
					//Check weather the report graph is available or not
					if(driver.findElement(By.xpath(excel.getData(3, 710, 1))).isDisplayed())
					{
						test.log(LogStatus.PASS, "There is Graphical type of category sale report is available for the Required Date Range");
					}
					else
					{
						test.log(LogStatus.FAIL, "Graphical report is not available for the Required Date Range");
					}
					
					Thread.sleep(3000);
					//Check weather the table format report is available or not
					if(driver.findElement(By.xpath(excel.getData(3, 1535, 1))).isDisplayed())
					{
						test.log(LogStatus.PASS, "Table Format Report is available for the Required Date Range");
						
						Thread.sleep(3000);
				        //No.of rows
				        List<WebElement> rows = driver.findElements(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr"));
				        
		/*		        //Print number of Rows
				        System.out.println("Number of Rows are : "+rows);*/
				        
			        	//Replace all commo's with empty space
			        	String expected1 = Utility.getReportProperty("Sale_Report_Sale_Amount").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Sale_Amount element into float value
			        	float expect1 = Float.parseFloat(expected1);  
			        	
			        	//Replace all commo's with empty space
			        	String expected2 = Utility.getReportProperty("Sale_Report_Quantity").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Quantity element into float value
			        	float expect2 = Float.parseFloat(expected2);  
			        	
			        	//Replace all commo's with empty space
			        	String expected3 = Utility.getReportProperty("Sale_Report_Tax").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Tax element into float value
			        	float expect3 = Float.parseFloat(expected3);  
			        	
			        	//Replace all commo's with empty space
			        	String expected4 = Utility.getReportProperty("Sale_Report_Discount").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Discount element into float value
			        	float expect4 = Float.parseFloat(expected4);  
				        
			        	//Replace all commo's with empty space
			        	String expected5 = Utility.getReportProperty("Sale_Report_Percentage_Of_Sale").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Percentage_Of_Sale element into float value
			        	float expect5 = Float.parseFloat(expected5);   
				        
				        //Check weather the Sale Amount Report is correct or not
				        if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[2]/span")).getText().equals(Utility.getReportProperty("Sale_Report_Sale_Amount")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Category Sale reports are same for Sale Amount");
				        	
				        	//Get the Total value of Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[2]/span")).getText();
				        	
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
							
				        	//Get the Total value of Sale Amount
				        	String actualText = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[2]/span")).getText();

				        	System.out.println("The Actual Sale Amount value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Sale Amount value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Category Sale reports are different for Sale Amount");
				        	
				        	//Get the Total value of Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[2]/span")).getText();
				        	
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
				        if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[3]/span")).getText().equals(Utility.getReportProperty("Sale_Report_Quantity")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Category Sale reports are same for Quantity");
				        	
				        	//Get the Total value of Quantity
				        	String actualText1 = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[3]/span")).getText();
				        	
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
				        	String actualText = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[3]/span")).getText();

				        	System.out.println("The Actual Quantity value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Quantity value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Category Sale reports are different for Quantity");
				        	
				        	//Get the Total value of Quantity
				        	String actualText1 = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[3]/span")).getText();
				        	
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
				        if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[4]/span")).getText().equals(Utility.getReportProperty("Sale_Report_Tax")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Category Sale reports are same for Tax");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[4]/span")).getText();
				        	
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
				        	String actualText = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[4]/span")).getText();

				        	System.out.println("The Actual Tax value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Tax value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Category Sale reports are different for Tax");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[4]/span")).getText();
				        	
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
				        if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[5]/span")).getText().equals(Utility.getReportProperty("Sale_Report_Discount")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Category Sale reports are same for Discount");
				        	
				        	//Get the Total value of Discount
				        	String actualText1 = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[5]/span")).getText();
				        	
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
				        	String actualText = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[5]/span")).getText();

				        	System.out.println("The Actual Discount value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Discount value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Category Sale reports are different for Discount");
				        	
				        	//Get the Total value of Discount
				        	String actualText1 = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[5]/span")).getText();
				        	
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
				        if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[6]/span")).getText().equals(Utility.getReportProperty("Sale_Report_Percentage_Of_Sale")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Category Sale reports are same for Percentage Of Sale");
				        	
				        	//Get the Total value of Percentage Of Sale
				        	String actualText1 = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[6]/span")).getText();
				        	
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
				        	String actualText = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[6]/span")).getText();

				        	System.out.println("The Actual Percentage Of Sale value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Percentage Of Sale value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Category Sale reports are different for Percentage Of Sale");
				        	
				        	//Get the Total value of Percentage Of Sale
				        	String actualText1 = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[6]/span")).getText();
				        	
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
				        
				    	String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));

				        
					}
					else
					{
						test.log(LogStatus.FAIL, "Table Format Report is not available for the Required Date Range");wb.close();
					
						driver.findElement(By.tagName("html")).sendKeys(Keys.END);
						
						String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));

					
					}
				}
				driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			}

			@Test(priority=23,enabled = false)
			public void Category_Sale_method__Report_For_Specific_Date_WithOut_Quantity_Sort_With_Dynamic_Report(WebDriver driver) throws Exception
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
				//Click the Category option
				driver.findElement(By.xpath(excel.getData(3, 1350, 1))).click();
				//Enter the required Category
				driver.findElement(By.xpath(excel.getData(3, 1351, 1))).sendKeys("All");
				//Press the Enter Key
				driver.findElement(By.xpath(excel.getData(3, 1351, 1))).sendKeys(Keys.ENTER);
			
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
				driver.findElement(By.xpath(excel.getData(3, 738, 1))).sendKeys(Utility.getReportProperty("Date_Range_From"));
				

				Thread.sleep(2000);
				//Clear the date field
				driver.findElement(By.xpath(excel.getData(3, 739, 1))).clear();
				//Enter the date
				driver.findElement(By.xpath(excel.getData(3, 739, 1))).sendKeys(Utility.getReportProperty("Date_Range_To"));
				
				//Check whether the Quantity sort is enabled or not
				if(driver.findElement(By.xpath(excel.getData(3, 1356, 1))).isSelected())
				{
					//Click the Quantity Sort option
					Actions act = new Actions(driver);
					act.moveToElement(driver.findElement(By.xpath(excel.getData(3, 1356, 1)))).click().build().perform();
				}
				else
				{
				}
				
				Thread.sleep(2000);
				//Click the Run Button
				driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
				
				Thread.sleep(3000);
				for(int i = 1; i <= 8; i++)
				{
					driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
				}Thread.sleep(3000);
				
				try
				{
					if(driver.findElement(By.xpath(excel.getData(3,704, 1))).isDisplayed())
					{
						test.log(LogStatus.FAIL, "Category Sale Report is not available for the Required Date Range");
					
						String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));
					}
				}
				catch(Exception fg)
				{
					//Check Whether all the field is selected or not
					if(driver.findElement(By.xpath("//span[contains(.,'Sale Amount')]/../input")).isSelected()
							&&driver.findElement(By.xpath("//span[contains(.,'Quantity')]/../input")).isSelected()
							&&driver.findElement(By.xpath("//span[contains(.,'Tax')]/../input")).isSelected()
							&&driver.findElement(By.xpath("//span[contains(.,'Discount')]/../input")).isSelected()
							&&driver.findElement(By.xpath("//span[contains(.,'% of Sale')]/../input")).isSelected())
					{
						//Check weather the table format report is available or not
						if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']")).isDisplayed())
						{
							test.log(LogStatus.PASS, "Table Format Report is available for the Required Date Range");
							
							//Check whether all the filed is available or not
							if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'% of Sale')]")).isDisplayed()
									&&driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'Discount')]")).isDisplayed()
									&&driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'Tax')]")).isDisplayed()
									&&driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'Quantity')]")).isDisplayed()
									&&driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'Sale Amount')]")).isDisplayed())
							{
								test.log(LogStatus.PASS, "All the Neccessary fields are displayed");
							}
							else
							{
								test.log(LogStatus.FAIL, "Neccessary fields are not displayed");
							}
							
							//Change the option from enable to Disable the Sale Amount Option
							if(driver.findElement(By.xpath("//span[contains(.,'Sale Amount')]/../input")).isSelected())
							{
								//Click the Enabled Sale Amount Option
								Actions act = new Actions(driver);
								act.moveToElement(driver.findElement(By.xpath("//input/../span[contains(.,'Sale Amount')]"))).click().build().perform();
								try
								{
									//Check whether the Sale Amount Option is displayed or not
									if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'Sale Amount')]")).isDisplayed())
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
							
							//Change the option from enable to Disable the Quantity Option
							if(driver.findElement(By.xpath("//span[contains(.,'Quantity')]/../input")).isSelected())
							{
								//Click the Enabled Quantity Option
								Actions act = new Actions(driver);
								act.moveToElement(driver.findElement(By.xpath("//input/../span[contains(.,'Quantity')]"))).click().build().perform();
								try
								{
									//Check whether the Quantity Option is displayed or not
									if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'Quantity')]")).isDisplayed())
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
							
							//Change the option from enable to Disable the Tax Option
							if(driver.findElement(By.xpath("//span[contains(.,'Tax')]/../input")).isSelected())
							{
								//Click the Enabled Tax Option
								Actions act = new Actions(driver);
								act.moveToElement(driver.findElement(By.xpath("//input/../span[contains(.,'Tax')]"))).click().build().perform();
								try
								{
									//Check whether the Tax Option is displayed or not
									if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'Tax')]")).isDisplayed())
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
							if(driver.findElement(By.xpath("//span[contains(.,'Discount')]/../input")).isSelected())
							{
								//Click the Enabled Discount Option
								Actions act = new Actions(driver);
								act.moveToElement(driver.findElement(By.xpath("//input/../span[contains(.,'Discount')]"))).click().build().perform();
								try
								{
									//Check whether the Discount Option is displayed or not
									if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'Discount')]")).isDisplayed())
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
							
							//Change the option from enable to Disable the % Of Sale Option
							if(driver.findElement(By.xpath("//span[contains(.,'% of Sale')]/../input")).isSelected())
							{
								//Click the Enabled % Of Sale Option
								Actions act = new Actions(driver);
								act.moveToElement(driver.findElement(By.xpath("//input/../span[contains(.,'% of Sale')]"))).click().build().perform();
								try
								{
									//Check whether the % Of Sale Option is displayed or not
									if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'% of Sale')]")).isDisplayed())
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
							if(driver.findElement(By.xpath("//span[contains(.,'Sale Amount')]/../input")).isSelected())
							{
								test.log(LogStatus.FAIL, "Sale Amount Option is Enabled after user click(Disable) the Sale Amount option");

							}
							else
							{
								//Click the Enabled Sale Amount Option
								Actions act = new Actions(driver);
								act.moveToElement(driver.findElement(By.xpath("//input/../span[contains(.,'Sale Amount')]"))).click().build().perform();
								try
								{
									//Check whether the Sale Amount Option is displayed or not
									if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'Sale Amount')]")).isDisplayed())
									{
										test.log(LogStatus.PASS, "Sale Amount is displayed");
									}
								}
								catch(Exception e)
								{
									test.log(LogStatus.FAIL, "Sale Amount is not displayed");
								}				}
							
							//Disable the required option from enable to disable Quantity Option
							if(driver.findElement(By.xpath("//span[contains(.,'Quantity')]/../input")).isSelected())
							{
								test.log(LogStatus.FAIL, "Quantity Option is Enabled after user click(Disable) the Quantity option");
							}
							else
							{
								//Click the Enabled Quantity Option
								Actions act = new Actions(driver);
								act.moveToElement(driver.findElement(By.xpath("//input/../span[contains(.,'Quantity')]"))).click().build().perform();
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
							if(driver.findElement(By.xpath("//span[contains(.,'Tax')]/../input")).isSelected())
							{
								test.log(LogStatus.FAIL, "Tax Option is Enabled after user click(Disable) the Tax option");
							}
							else
							{

								//Click the Enabled Tax Option
								Actions act = new Actions(driver);
								act.moveToElement(driver.findElement(By.xpath("//input/../span[contains(.,'Tax')]"))).click().build().perform();
								try
								{
									//Check whether the Tax Option is displayed or not
									if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'Tax')]")).isDisplayed())
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
							if(driver.findElement(By.xpath("//span[contains(.,'Discount')]/../input")).isSelected())
							{
								test.log(LogStatus.FAIL, "Discount Option is Enabled after user click(Disable) the Discount option");

							}
							else
							{

								//Click the Enabled Discount Option
								Actions act = new Actions(driver);
								act.moveToElement(driver.findElement(By.xpath("//input/../span[contains(.,'Discount')]"))).click().build().perform();
								try
								{
									//Check whether the Discount Option is displayed or not
									if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'Discount')]")).isDisplayed())
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
							if(driver.findElement(By.xpath("//span[contains(.,'% of Sale')]/../input")).isSelected())
							{
								test.log(LogStatus.FAIL, "% Of Sale Option is Enabled after user click(Disable) the % of Sale option");

							}
							else
							{

								//Click the Enabled % Of Sale Option
								Actions act = new Actions(driver);
								act.moveToElement(driver.findElement(By.xpath("//input/../span[contains(.,'% of Sale')]"))).click().build().perform();
								try
								{
									//Check whether the % Of Sale Option is displayed or not
									if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'% of Sale')]")).isDisplayed())
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
						test.log(LogStatus.FAIL, "All dynamic option is not selected");wb.close();
					}
				}				
			}

			@Test(priority=24,enabled = false)
			public void Category_Sale_method__Report_For_Specific_Date_With_Quantity_Sort_And_Dynamic_Report(WebDriver driver) throws Exception
			{
				    File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
	 				
					FileInputStream fis = new FileInputStream(src);
					
					XSSFWorkbook wb = new XSSFWorkbook(fis);
					
					XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
					
					ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				Thread.sleep(2000);
				WebElement html = driver.findElement(By.tagName("html"));
				html.sendKeys(Keys.HOME);

				Thread.sleep(3000);
				//Click the Category option
				driver.findElement(By.xpath(excel.getData(3, 1350, 1))).click();
				//Enter the required Category
				driver.findElement(By.xpath(excel.getData(3, 1351, 1))).sendKeys("All");
				//Press the Enter Key
				driver.findElement(By.xpath(excel.getData(3, 1351, 1))).sendKeys(Keys.ENTER);
			
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
				driver.findElement(By.xpath(excel.getData(3, 738, 1))).sendKeys(Utility.getReportProperty("Date_Range_From"));
				

				Thread.sleep(2000);
				//Clear the date field
				driver.findElement(By.xpath(excel.getData(3, 739, 1))).clear();
				//Enter the date
				driver.findElement(By.xpath(excel.getData(3, 739, 1))).sendKeys(Utility.getReportProperty("Date_Range_To"));
				
				//Check whether the Quantity sort is enabled or not
				if(driver.findElement(By.xpath(excel.getData(3, 1356, 1))).isSelected())
				{
					
				}
				else
				{
					//Click the Quantity Sort option
					Actions act = new Actions(driver);
					act.moveToElement(driver.findElement(By.xpath(excel.getData(3, 1356, 1)))).click().build().perform();
				}
				
				Thread.sleep(2000);
				//Click the Run Button
				driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
				
				Thread.sleep(2000);
				//Click the Run Button
				for(int i = 1; i <= 8; i++)
				{
					driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
				}Thread.sleep(3000);
				
				try
				{
					if(driver.findElement(By.xpath(excel.getData(3, 704, 1))).isDisplayed())
					{
						test.log(LogStatus.FAIL, "Category Sale Report is not available for the Required Date Range");
					}
				}
				catch(Exception gf)
				{
					//Check Whether all the field is selected or not
					if(driver.findElement(By.xpath("//span[contains(.,'Sale Amount')]/../input")).isSelected()
							&&driver.findElement(By.xpath("//span[contains(.,'Quantity')]/../input")).isSelected()
							&&driver.findElement(By.xpath("//span[contains(.,'Tax')]/../input")).isSelected()
							&&driver.findElement(By.xpath("//span[contains(.,'Discount')]/../input")).isSelected()
							&&driver.findElement(By.xpath("//span[contains(.,'% of Sale')]/../input")).isSelected())
					{
						//Check weather the table format report is available or not
						if(driver.findElement(By.xpath(excel.getData(3, 1535, 1))).isDisplayed())
						{
							test.log(LogStatus.PASS, "Table Format Report is available for the Required Date Range");
							
							//Check whether all the filed is available or not
							if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'% of Sale')]")).isDisplayed()
									&&driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'Discount')]")).isDisplayed()
									&&driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'Tax')]")).isDisplayed()
									&&driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'Quantity')]")).isDisplayed()
									&&driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'Sale Amount')]")).isDisplayed())
							{
								test.log(LogStatus.PASS, "All the Neccessary fields are displayed");
							}
							else
							{
								test.log(LogStatus.FAIL, "Neccessary fields are not displayed");
							}
							
							//Change the option from enable to Disable the Sale Amount Option
							if(driver.findElement(By.xpath("//span[contains(.,'Sale Amount')]/../input")).isSelected())
							{
								//Click the Enabled Sale Amount Option
								Actions act = new Actions(driver);
								act.moveToElement(driver.findElement(By.xpath("//input/../span[contains(.,'Sale Amount')]"))).click().build().perform();
								try
								{
									//Check whether the Sale Amount Option is displayed or not
									if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'Sale Amount')]")).isDisplayed())
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
							
							//Change the option from enable to Disable the Quantity Option
							if(driver.findElement(By.xpath("//span[contains(.,'Quantity')]/../input")).isSelected())
							{
								//Click the Enabled Quantity Option
								Actions act = new Actions(driver);
								act.moveToElement(driver.findElement(By.xpath("//input/../span[contains(.,'Quantity')]"))).click().build().perform();
								try
								{
									//Check whether the Quantity Option is displayed or not
									if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'Quantity')]")).isDisplayed())
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
							
							//Change the option from enable to Disable the Tax Option
							if(driver.findElement(By.xpath("//span[contains(.,'Tax')]/../input")).isSelected())
							{
								//Click the Enabled Tax Option
								Actions act = new Actions(driver);
								act.moveToElement(driver.findElement(By.xpath("//input/../span[contains(.,'Tax')]"))).click().build().perform();
								try
								{
									//Check whether the Tax Option is displayed or not
									if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'Tax')]")).isDisplayed())
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
							if(driver.findElement(By.xpath("//span[contains(.,'Discount')]/../input")).isSelected())
							{
								//Click the Enabled Discount Option
								Actions act = new Actions(driver);
								act.moveToElement(driver.findElement(By.xpath("//input/../span[contains(.,'Discount')]"))).click().build().perform();
								try
								{
									//Check whether the Discount Option is displayed or not
									if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'Discount')]")).isDisplayed())
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
							
							//Change the option from enable to Disable the % Of Sale Option
							if(driver.findElement(By.xpath("//span[contains(.,'% of Sale')]/../input")).isSelected())
							{
								//Click the Enabled % Of Sale Option
								Actions act = new Actions(driver);
								act.moveToElement(driver.findElement(By.xpath("//input/../span[contains(.,'% of Sale')]"))).click().build().perform();
								try
								{
									//Check whether the % Of Sale Option is displayed or not
									if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'% of Sale')]")).isDisplayed())
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
							if(driver.findElement(By.xpath("//span[contains(.,'Sale Amount')]/../input")).isSelected())
							{
								test.log(LogStatus.FAIL, "Sale Amount Option is Enabled after user click(Disable) the Sale Amount option");

							}
							else
							{
								//Click the Enabled Sale Amount Option
								Actions act = new Actions(driver);
								act.moveToElement(driver.findElement(By.xpath("//input/../span[contains(.,'Sale Amount')]"))).click().build().perform();
								try
								{
									//Check whether the Sale Amount Option is displayed or not
									if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'Sale Amount')]")).isDisplayed())
									{
										test.log(LogStatus.PASS, "Sale Amount is displayed");
									}
								}
								catch(Exception e)
								{
									test.log(LogStatus.FAIL, "Sale Amount is not displayed");
								}				}
							
							//Disable the required option from enable to disable Quantity Option
							if(driver.findElement(By.xpath("//span[contains(.,'Quantity')]/../input")).isSelected())
							{
								test.log(LogStatus.FAIL, "Quantity Option is Enabled after user click(Disable) the Quantity option");
							}
							else
							{
								//Click the Enabled Quantity Option
								Actions act = new Actions(driver);
								act.moveToElement(driver.findElement(By.xpath("//input/../span[contains(.,'Quantity')]"))).click().build().perform();
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
							if(driver.findElement(By.xpath("//span[contains(.,'Tax')]/../input")).isSelected())
							{
								test.log(LogStatus.FAIL, "Tax Option is Enabled after user click(Disable) the Tax option");
							}
							else
							{

								//Click the Enabled Tax Option
								Actions act = new Actions(driver);
								act.moveToElement(driver.findElement(By.xpath("//input/../span[contains(.,'Tax')]"))).click().build().perform();
								try
								{
									//Check whether the Tax Option is displayed or not
									if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'Tax')]")).isDisplayed())
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
							if(driver.findElement(By.xpath("//span[contains(.,'Discount')]/../input")).isSelected())
							{
								test.log(LogStatus.FAIL, "Discount Option is Enabled after user click(Disable) the Discount option");

							}
							else
							{

								//Click the Enabled Discount Option
								Actions act = new Actions(driver);
								act.moveToElement(driver.findElement(By.xpath("//input/../span[contains(.,'Discount')]"))).click().build().perform();
								try
								{
									//Check whether the Discount Option is displayed or not
									if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'Discount')]")).isDisplayed())
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
							if(driver.findElement(By.xpath("//span[contains(.,'% of Sale')]/../input")).isSelected())
							{
								test.log(LogStatus.FAIL, "% Of Sale Option is Enabled after user click(Disable) the % of Sale option");

							}
							else
							{

								//Click the Enabled % Of Sale Option
								Actions act = new Actions(driver);
								act.moveToElement(driver.findElement(By.xpath("//input/../span[contains(.,'% of Sale')]"))).click().build().perform();
								try
								{
									//Check whether the % Of Sale Option is displayed or not
									if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'% of Sale')]")).isDisplayed())
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
						test.log(LogStatus.FAIL, "All dynamic option is not selected");wb.close();
					}
				}
			}

}

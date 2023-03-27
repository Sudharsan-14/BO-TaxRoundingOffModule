package epicList_Chrome;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import newReadExcelFile_New.ExcelDataConfig;

public class Verify_Enterprise_Daily_Sale_Report {

	public WebDriver driver;
	
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Verify_Enterprise_Daily_Sale_Report");

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
			Daily_Enterprise_Report_Method_OpenpageReport(driver);
			Daily_Enterprise_Report_Method_For_Stores(driver);
			Daily_Enterprise_Report_Method_For_Group(driver);
			Daily_Enterprise_Report_Method_For_City(driver);
			Daily_Enterprise_Report_Method_For_State(driver);
			Daily_Enterprise_Report_Method_For_ZipCode(driver);
			Thread.sleep(1500);
		}
	
		@Test(enabled=false,priority=8)
		public void Daily_Enterprise_Report_Method_OpenpageReport(WebDriver driver) throws Exception
		{
			File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			 FileInputStream fis = new FileInputStream(src);
					
			 XSSFWorkbook wb = new XSSFWorkbook(fis);
					
		     XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		     ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		/*	//Click the My Stores option
			driver.findElement(By.xpath("//span[.='My Stores']")).click();

			Thread.sleep(3000);
	        //Click the EnterPrise Reports Option		
			driver.findElement(By.xpath("//span[.='EnterPrise Reports']")).click();
			
			Thread.sleep(2000);
			//Click the Sale option
			driver.findElement(By.xpath("//span[.='Sale']")).click();	*/
			
		     Thread.sleep(2000);
				//Enter the URl
				driver.get(Utility.getProperty("Enterprise_Base_URL")+"enterPriseReport/dailySaleSummary");

				Thread.sleep(5000);
				//Check Daily Report page opened or not
				if(driver.findElement(By.xpath(excel.getData(3, 731, 1))).getText().equalsIgnoreCase("Daily Sale"))
				{
					test.log(LogStatus.PASS, "Daily Sale Report page loaded Successfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "Daily Sale Report page loaded Failed");
				}
				
				Thread.sleep(3000);wb.close();
	
		}
		
		@Test(enabled=false,priority=9)
		public void Daily_Enterprise_Report_Method_For_Stores(WebDriver driver) throws Exception
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
			System.out.println("********************* View The Store Report *************************");
			test.log(LogStatus.INFO, "********************* View The Store Report *************************");

			//Click the Select option
			driver.findElement(By.xpath(excel.getData(3, 732, 1))).click();
			//Enter the required filter
			driver.findElement(By.xpath(excel.getData(3, 733, 1))).sendKeys("Group");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 733, 1))).sendKeys(Keys.ENTER);

			Thread.sleep(1500);
			//Click the Select option
			driver.findElement(By.xpath(excel.getData(3, 732, 1))).click();
			//Enter the required filter
			driver.findElement(By.xpath(excel.getData(3, 733, 1))).sendKeys("Store");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 733, 1))).sendKeys(Keys.ENTER);

			
			Thread.sleep(1500);
			//Click the Select Some Options
			driver.findElement(By.xpath(excel.getData(3, 734, 1))).click();
			//Remove the store name
			//driver.findElement(By.xpath("//form[@name='saleReport']/div[1]/div[2]/div/ul/li[1]/a")).click();
			//Enter the required option
			driver.findElement(By.xpath(excel.getData(3, 735, 1))).sendKeys(Utility.getReportProperty("Store"));
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 735, 1))).sendKeys(Keys.ENTER);
		
			Thread.sleep(1500);
			//Click the Time Period Options
			driver.findElement(By.xpath(excel.getData(3, 736, 1))).click();
			//Enter the required option
			driver.findElement(By.xpath(excel.getData(3, 737, 1))).sendKeys("Date Range");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 737, 1))).sendKeys(Keys.ENTER);
		
			//Clear the date field
			driver.findElement(By.xpath(excel.getData(3, 738, 1))).clear();
			//Enter the required date
			driver.findElement(By.xpath(excel.getData(3, 738, 1))).sendKeys(Utility.getReportProperty("Date_Range_From"));

			//Clear the date field
			driver.findElement(By.xpath(excel.getData(3, 739, 1))).clear();
			//Enter the required date
			driver.findElement(By.xpath(excel.getData(3, 739, 1))).sendKeys(Utility.getReportProperty("Date_Range_To"));
			
			Thread.sleep(1000);
			//Click the Run button
			driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();			
			Thread.sleep(1000);
			//Click the Run button
			driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
			
			try
			{
				Thread.sleep(3000);
				//Check weather the report is available for the selected time period
				if(!driver.findElement(By.xpath(excel.getData(3, 704, 1))).isDisplayed())
				{
					test.log(LogStatus.PASS, "Daily Sale Report available for Specific Date");
					
					Thread.sleep(3000);
					//Check Weather the Top 5 Daily sale available or not
					if(driver.findElement(By.xpath(excel.getData(3, 705, 1))).isDisplayed())
					{
						test.log(LogStatus.PASS, "Here Top 5 Daily Sale Report available for Specific Date");
						
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
						test.log(LogStatus.FAIL, "Here Top 5 Daily Sale Report not available for Specific Date For Stores");
					}
					
					Thread.sleep(5000);
					//Check weather the report graph is available or not
					if(driver.findElement(By.xpath(excel.getData(3, 710, 1))).isDisplayed())
					{
						test.log(LogStatus.PASS, "There is Graphical type of Daily sale report is available for Specific Date");
					}
					else
					{
						test.log(LogStatus.FAIL, "Graphical report is not available for Specific Date For Stores");
					}
					
					Thread.sleep(1000);
					//Scroll the web page
				    for(int i=1; i <= 15; i++)
				    {
				    	driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
				    	Thread.sleep(1000);
				    }
				    
					Thread.sleep(8000);
					//Check weather the table format report is available or not
					if(driver.findElement(By.xpath(excel.getData(3, 711, 2))).isDisplayed())
					{
						test.log(LogStatus.PASS, "Table Format Report is available for Specific Date For Stores");
						
						Thread.sleep(3000);
				        //No.of rows
				        List<WebElement> rows = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr"));
				        
		/*		        //Print number of Rows
				        System.out.println("Number of Rows are : "+rows);*/
				        
						//Replace all commo's with empty space
						String expected1 = Utility.getReportProperty("Sale_Report_Net_Sale").replace(",", "");
						
						//Convert the String value of the Sale_Report_Net_Sale element into float value
						float expect1 = Float.parseFloat(expected1); 

						//Replace all commo's with empty space
						String expected2 = Utility.getReportProperty("Sale_Report_Tax").replace(",", "");
						
						//Convert the String value of the Sale_Report_Tax element into float value
						float expect2 = Float.parseFloat(expected2);  

						//Replace all commo's with empty space
						String expected3 = Utility.getReportProperty("Sale_Report_Discount").replace(",", "");
						
						//Convert the String value of the Sale_Report_Discount element into float value
						float expect3 = Float.parseFloat(expected3);     	

						//Replace all commo's with empty space
						String expected4 = Utility.getReportProperty("Sale_Report_Grand_Sale").replace(",", "");
								
						//Convert the String value of the Sale_Report_Grand_Sale element into float value
						float expect4= Float.parseFloat(expected4);  
				        			        
				        //Check weather the Net Sale is correct or not
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[2]/span")).getText().equals(Utility.getReportProperty("Sale_Report_Net_Sale")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Daily Sale reports are same for Sale Amount For Stores");
				        	
				        	//Get the Total value of Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[2]/span")).getText();
				        	
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
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[2]/span")).getText();

				        	System.out.println("The Actual Net Sale value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Net Sale value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Daily Sale reports are different for Sale Amount For Stores");
				        	
				        	//Get the Total value of Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[2]/span")).getText();
				        	
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
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[3]/span")).getText().equals(Utility.getReportProperty("Sale_Report_Tax")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Daily Sale reports are same for Tax");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[3]/span")).getText();
				        	
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
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[3]/span")).getText();

				        	System.out.println("The Actual Tax value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Tax value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Daily Sale reports are different for Tax");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[3]/span")).getText();
				        	
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
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[4]/span")).getText().equals(Utility.getReportProperty("Sale_Report_Discount")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Daily Sale reports are same for Discount");
				        	
				        	//Get the Total value of Discount
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[4]/span")).getText();
				        	
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
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[4]/span")).getText();

				        	System.out.println("The Actual Discount value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Discount value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Daily Sale reports are different for Discount");
				        	
				        	//Get the Total value of Discount
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[4]/span")).getText();
				        	
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
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[5]/span")).getText().equals(Utility.getReportProperty("Sale_Report_Grand_Sale")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Daily Sale reports are same for Grand Sale");
				        	
				        	//Get the Total value of Grand Sale
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[5]/span")).getText();
				        	
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
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[5]/span")).getText();

				        	System.out.println("The Actual Grand Sale value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Grand Sale value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Daily Sale reports are different for Grand Sale");
				        	
				        	//Get the Total value of Grand Sale
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[5]/span")).getText();
				        	
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
				        
				        
					}
					else
					{
						test.log(LogStatus.FAIL, "Table Format Report is not available for Specific Date For Stores");
					}
				}
			}
			
			catch(Exception e)
			{

				test.log(LogStatus.FAIL, "Daily Sale Report is not available for Specific Date For Stores");

			}
					
			Thread.sleep(3000);
			System.out.println("********************* End of Store Report *************************");
			test.log(LogStatus.INFO, "********************* End of Store Report *************************");

			Thread.sleep(1000);
			//Scroll the web page
		    for(int i=1; i <= 15; i++)
		    {
		    	driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_UP);wb.close();
		    	Thread.sleep(1000);
		    }
		}
		
		@Test(enabled=false,priority=10)
		public void Daily_Enterprise_Report_Method_For_Group(WebDriver driver) throws Exception
		{
			File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			 FileInputStream fis = new FileInputStream(src);
					
			 XSSFWorkbook wb = new XSSFWorkbook(fis);
					
		     XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		     ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			Thread.sleep(3000);
			System.out.println("********************* View The Group Report *************************");
			test.log(LogStatus.INFO, "********************* View The Group Report *************************");
			
			//Click the Select option
			driver.findElement(By.xpath(excel.getData(3, 732, 1))).click();
			//Enter the required filter
			driver.findElement(By.xpath(excel.getData(3, 733, 1))).sendKeys("Group");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 733, 1))).sendKeys(Keys.ENTER);
			
			
			Thread.sleep(1500);
			//Click the Select Some Options
			driver.findElement(By.xpath(excel.getData(3, 734, 1))).click();
			//Enter the required option
			driver.findElement(By.xpath(excel.getData(3, 735, 1))).sendKeys(Utility.getReportProperty("Group"));
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 735, 1))).sendKeys(Keys.ENTER);
		

			//Click the Time Period Options
			driver.findElement(By.xpath(excel.getData(3, 736, 1))).click();
			//Enter the required option
			driver.findElement(By.xpath(excel.getData(3, 737, 1))).sendKeys("Date Range");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 737, 1))).sendKeys(Keys.ENTER);
		
			//Clear the date field
			driver.findElement(By.xpath(excel.getData(3, 738, 1))).clear();
			//Enter the required date
			driver.findElement(By.xpath(excel.getData(3, 738, 1))).sendKeys(Utility.getReportProperty("Date_Range_From"));

			//Clear the date field
			driver.findElement(By.xpath(excel.getData(3, 739, 1))).clear();
			//Enter the required date
			driver.findElement(By.xpath(excel.getData(3, 739, 1))).sendKeys(Utility.getReportProperty("Date_Range_To"));
			
			Thread.sleep(1000);
			//Click the Run button
			driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
			
			try
			{
				Thread.sleep(3000);
				//Check weather the report is available for the selected time period
				if(!driver.findElement(By.xpath(excel.getData(3, 704, 1))).isDisplayed())
				{
					test.log(LogStatus.PASS, "Daily Sale Report available for Specific Date");
					
					Thread.sleep(3000);
					//Check Weather the Top 5 Daily sale available or not
					if(driver.findElement(By.xpath(excel.getData(3, 705, 1))).isDisplayed())
					{
						test.log(LogStatus.PASS, "Here Top 5 Daily Sale Report available for Specific Date");
						
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
						test.log(LogStatus.FAIL, "Here Top 5 Daily Sale Report not available for Specific Date");
					}
					
					Thread.sleep(5000);
					//Check weather the report graph is available or not
					if(driver.findElement(By.xpath(excel.getData(3, 710, 1))).isDisplayed())
					{
						test.log(LogStatus.PASS, "There is Graphical type of Daily sale report is available for Specific Date");
					}
					else
					{
						test.log(LogStatus.FAIL, "Graphical report is not available for Specific Date");
					}
					
					Thread.sleep(1000);
					//Scroll the web page
				    for(int i=1; i <= 15; i++)
				    {
				    	driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
				    	Thread.sleep(1000);
				    }
				    
					Thread.sleep(5000);
					//Check weather the table format report is available or not
					if(driver.findElement(By.xpath(excel.getData(3, 711, 1))).isDisplayed())
					{
						test.log(LogStatus.PASS, "Table Format Report is available for Specific Date");
						
						Thread.sleep(3000);
				        //No.of rows
				        List<WebElement> rows = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr"));
				        
		/*		        //Print number of Rows
				        System.out.println("Number of Rows are : "+rows);*/
				        
						//Replace all commo's with empty space
						String expected1 = Utility.getReportProperty("Sale_Report_Net_Sale").replace(",", "");
						
						//Convert the String value of the Sale_Report_Net_Sale element into float value
						float expect1 = Float.parseFloat(expected1); 

						//Replace all commo's with empty space
						String expected2 = Utility.getReportProperty("Sale_Report_Tax").replace(",", "");
						
						//Convert the String value of the Sale_Report_Tax element into float value
						float expect2 = Float.parseFloat(expected2);  

						//Replace all commo's with empty space
						String expected3 = Utility.getReportProperty("Sale_Report_Discount").replace(",", "");
						
						//Convert the String value of the Sale_Report_Discount element into float value
						float expect3 = Float.parseFloat(expected3);     	

						//Replace all commo's with empty space
						String expected4 = Utility.getReportProperty("Sale_Report_Grand_Sale").replace(",", "");
								
						//Convert the String value of the Sale_Report_Grand_Sale element into float value
						float expect4= Float.parseFloat(expected4);  
				        			        
				        //Check weather the Net Sale is correct or not
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[2]/span")).getText().equals(Utility.getReportProperty("Sale_Report_Net_Sale")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Daily Sale reports are same for Sale Amount For Stores");
				        	
				        	//Get the Total value of Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[2]/span")).getText();
				        	
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
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[2]/span")).getText();

				        	System.out.println("The Actual Net Sale value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Net Sale value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Daily Sale reports are different for Sale Amount For Stores");
				        	
				        	//Get the Total value of Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[2]/span")).getText();
				        	
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
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[3]/span")).getText().equals(Utility.getReportProperty("Sale_Report_Tax")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Daily Sale reports are same for Tax");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[3]/span")).getText();
				        	
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
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[3]/span")).getText();

				        	System.out.println("The Actual Tax value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Tax value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Daily Sale reports are different for Tax");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[3]/span")).getText();
				        	
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
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[4]/span")).getText().equals(Utility.getReportProperty("Sale_Report_Discount")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Daily Sale reports are same for Discount");
				        	
				        	//Get the Total value of Discount
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[4]/span")).getText();
				        	
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
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[4]/span")).getText();

				        	System.out.println("The Actual Discount value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Discount value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Daily Sale reports are different for Discount");
				        	
				        	//Get the Total value of Discount
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[4]/span")).getText();
				        	
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
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[5]/span")).getText().equals(Utility.getReportProperty("Sale_Report_Grand_Sale")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Daily Sale reports are same for Grand Sale");
				        	
				        	//Get the Total value of Grand Sale
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[5]/span")).getText();
				        	
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
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[5]/span")).getText();

				        	System.out.println("The Actual Grand Sale value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Grand Sale value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Daily Sale reports are different for Grand Sale");
				        	
				        	//Get the Total value of Grand Sale
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[5]/span")).getText();
				        	
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
				        
				        
					}
					else
					{
						test.log(LogStatus.FAIL, "Table Format Report is not available for Specific Date For Group");
					}
				}
			}
			
			catch(Exception e)
			{

				test.log(LogStatus.FAIL, "Daily Sale Report is not available for Specific Date For Group");

			}
					
			
			Thread.sleep(3000);
			System.out.println("********************* End of Group Report *************************");
			Thread.sleep(1000);
			//Scroll the web page
		    for(int i=1; i <= 15; i++)
		    {
		    	driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_UP);
		    	Thread.sleep(1000);
		    }	test.log(LogStatus.INFO, "********************* End of Group Report *************************");
		}
		
		@Test(enabled=false,priority=11)
		public void Daily_Enterprise_Report_Method_For_City(WebDriver driver) throws Exception
		{   

			File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			 FileInputStream fis = new FileInputStream(src);
					
			 XSSFWorkbook wb = new XSSFWorkbook(fis);
					
		     XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		     ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			Thread.sleep(3000);
			System.out.println("********************* View The City Report *************************");
			test.log(LogStatus.INFO, "********************* View The City Report *************************");

			//Click the Select option
			driver.findElement(By.xpath(excel.getData(3, 732, 1))).click();
			//Enter the required filter
			driver.findElement(By.xpath(excel.getData(3, 733, 1))).sendKeys("City");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 733, 1))).sendKeys(Keys.ENTER);
			
			
			Thread.sleep(1500);
			//Click the Select Some Options
			driver.findElement(By.xpath(excel.getData(3, 734, 1))).click();
			//Enter the required option
			driver.findElement(By.xpath(excel.getData(3, 735, 1))).sendKeys(Utility.getReportProperty("City"));
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 735, 1))).sendKeys(Keys.ENTER);
		
			Thread.sleep(1500);
			//Click the Time Period Options
			driver.findElement(By.xpath(excel.getData(3, 736, 1))).click();
			//Enter the required option
			driver.findElement(By.xpath(excel.getData(3, 737, 1))).sendKeys("Date Range");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 737, 1))).sendKeys(Keys.ENTER);
		
			//Clear the date field
			driver.findElement(By.xpath(excel.getData(3, 738, 1))).clear();
			//Enter the required date
			driver.findElement(By.xpath(excel.getData(3, 738, 1))).sendKeys(Utility.getReportProperty("Date_Range_From"));

			//Clear the date field
			driver.findElement(By.xpath(excel.getData(3, 739, 1))).clear();
			//Enter the required date
			driver.findElement(By.xpath(excel.getData(3, 739, 1))).sendKeys(Utility.getReportProperty("Date_Range_To"));
			
			Thread.sleep(1000);
			//Click the Run button
			driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
			
			try
			{
				Thread.sleep(3000);
				//Check weather the report is available for the selected time period
				if(!driver.findElement(By.xpath(excel.getData(3, 704, 1))).isDisplayed())
				{
					test.log(LogStatus.PASS, "Daily Sale Report available for Specific Date");
					
					Thread.sleep(3000);
					//Check Weather the Top 5 Daily sale available or not
					if(driver.findElement(By.xpath(excel.getData(3, 705, 1))).isDisplayed())
					{
						test.log(LogStatus.PASS, "Here Top 5 Daily Sale Report available for Specific Date");
						
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
						test.log(LogStatus.FAIL, "Here Top 5 Daily Sale Report not available for Specific Date");
					}
					
					Thread.sleep(5000);
					//Check weather the report graph is available or not
					if(driver.findElement(By.xpath(excel.getData(3, 710, 1))).isDisplayed())
					{
						test.log(LogStatus.PASS, "There is Graphical type of Daily sale report is available for Specific Date");
					}
					else
					{
						test.log(LogStatus.FAIL, "Graphical report is not available for Specific Date");
					}
					
					Thread.sleep(1000);
					//Scroll the web page
				    for(int i=1; i <= 15; i++)
				    {
				    	driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
				    	Thread.sleep(1000);
				    }
				    
					Thread.sleep(5000);
					//Check weather the table format report is available or not
					if(driver.findElement(By.xpath(excel.getData(3, 711, 1))).isDisplayed())
					{
						test.log(LogStatus.PASS, "Table Format Report is available for Specific Date");
						
						Thread.sleep(3000);
				        //No.of rows
				        List<WebElement> rows = driver.findElements(By.xpath ("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr"));
				        
		/*		        //Print number of Rows
				        System.out.println("Number of Rows are : "+rows);*/
				        
						//Replace all commo's with empty space
						String expected1 = Utility.getReportProperty("Sale_Report_Net_Sale").replace(",", "");
						
						//Convert the String value of the Sale_Report_Net_Sale element into float value
						float expect1 = Float.parseFloat(expected1); 

						//Replace all commo's with empty space
						String expected2 = Utility.getReportProperty("Sale_Report_Tax").replace(",", "");
						
						//Convert the String value of the Sale_Report_Tax element into float value
						float expect2 = Float.parseFloat(expected2);  

						//Replace all commo's with empty space
						String expected3 = Utility.getReportProperty("Sale_Report_Discount").replace(",", "");
						
						//Convert the String value of the Sale_Report_Discount element into float value
						float expect3 = Float.parseFloat(expected3);     	

						//Replace all commo's with empty space
						String expected4 = Utility.getReportProperty("Sale_Report_Grand_Sale").replace(",", "");
								
						//Convert the String value of the Sale_Report_Grand_Sale element into float value
						float expect4= Float.parseFloat(expected4);  
				        			        
				        //Check weather the Net Sale is correct or not
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[2]/span")).getText().equals(Utility.getReportProperty("Sale_Report_Net_Sale")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Daily Sale reports are same for Sale Amount For Stores");
				        	
				        	//Get the Total value of Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[2]/span")).getText();
				        	
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
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[2]/span")).getText();

				        	System.out.println("The Actual Net Sale value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Net Sale value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Daily Sale reports are different for Sale Amount For Stores");
				        	
				        	//Get the Total value of Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[2]/span")).getText();
				        	
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
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[3]/span")).getText().equals(Utility.getReportProperty("Sale_Report_Tax")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Daily Sale reports are same for Tax");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[3]/span")).getText();
				        	
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
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[3]/span")).getText();

				        	System.out.println("The Actual Tax value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Tax value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Daily Sale reports are different for Tax");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[3]/span")).getText();
				        	
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
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[4]/span")).getText().equals(Utility.getReportProperty("Sale_Report_Discount")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Daily Sale reports are same for Discount");
				        	
				        	//Get the Total value of Discount
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[4]/span")).getText();
				        	
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
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[4]/span")).getText();

				        	System.out.println("The Actual Discount value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Discount value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Daily Sale reports are different for Discount");
				        	
				        	//Get the Total value of Discount
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[4]/span")).getText();
				        	
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
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[5]/span")).getText().equals(Utility.getReportProperty("Sale_Report_Grand_Sale")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Daily Sale reports are same for Grand Sale");
				        	
				        	//Get the Total value of Grand Sale
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[5]/span")).getText();
				        	
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
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[5]/span")).getText();

				        	System.out.println("The Actual Grand Sale value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Grand Sale value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Daily Sale reports are different for Grand Sale");
				        	
				        	//Get the Total value of Grand Sale
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[5]/span")).getText();
				        	
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
				        
				        
					}
					else
					{
						test.log(LogStatus.FAIL, "Table Format Report is not available for Specific Date For City");
					}
				}
			}
			
			catch(Exception e)
			{

				test.log(LogStatus.FAIL, "Daily Sale Report is not available for Specific Date For City");

			}
					
			
			Thread.sleep(3000);
			System.out.println("********************* End of City Report *************************");
			Thread.sleep(1000);
			//Scroll the web page
		    for(int i=1; i <= 15; i++)
		    {
		    	driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_UP);
		    	Thread.sleep(1000);
		    }	test.log(LogStatus.INFO, "********************* End of City Report *************************");wb.close();
		wb.close();
		}
		
				
		@Test(enabled=false,priority=12)
		public void Daily_Enterprise_Report_Method_For_State(WebDriver driver) throws Exception
		{
			File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			 FileInputStream fis = new FileInputStream(src);
					
			 XSSFWorkbook wb = new XSSFWorkbook(fis);
					
		     XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		     ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			Thread.sleep(3000);
			System.out.println("********************* View The State Report *************************");
			test.log(LogStatus.INFO, "********************* View The State Report *************************");

			//Click the Select option
			driver.findElement(By.xpath(excel.getData(3, 732, 1))).click();
			//Enter the required filter
			driver.findElement(By.xpath(excel.getData(3, 733, 1))).sendKeys("State");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 733, 1))).sendKeys(Keys.ENTER);
			
			
			Thread.sleep(1500);
			//Click the Select Some Options
			driver.findElement(By.xpath(excel.getData(3, 734, 1))).click();
			//Enter the required option
			driver.findElement(By.xpath(excel.getData(3, 735, 1))).sendKeys(Utility.getReportProperty("State"));
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 735, 1))).sendKeys(Keys.ENTER);
		
			Thread.sleep(1500);
			//Click the Time Period Options
			driver.findElement(By.xpath(excel.getData(3, 736, 1))).click();
			//Enter the required option
			driver.findElement(By.xpath(excel.getData(3, 737, 1))).sendKeys("Date Range");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 737, 1))).sendKeys(Keys.ENTER);
		
			Thread.sleep(500);
			//Clear the date field
			driver.findElement(By.xpath(excel.getData(3, 738, 1))).clear();
			//Enter the required date
			driver.findElement(By.xpath(excel.getData(3, 738, 1))).sendKeys(Utility.getReportProperty("Date_Range_From"));

			Thread.sleep(500);
			//Clear the date field
			driver.findElement(By.xpath(excel.getData(3, 739, 1))).clear();
			//Enter the required date
			driver.findElement(By.xpath(excel.getData(3, 739, 1))).sendKeys(Utility.getReportProperty("Date_Range_To"));
			
			Thread.sleep(1000);
			//Click the Run button
			driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();			
			Thread.sleep(1000);
			//Click the Run button
			driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
			
			try
			{
				Thread.sleep(3000);
				//Check weather the report is available for the selected time period
				if(!driver.findElement(By.xpath(excel.getData(3, 704, 1))).isDisplayed())
				{
					test.log(LogStatus.PASS, "Daily Sale Report available for Specific Date");
					
					Thread.sleep(3000);
					//Check Weather the Top 5 Daily sale available or not
					if(driver.findElement(By.xpath(excel.getData(3, 705, 1))).isDisplayed())
					{
						test.log(LogStatus.PASS, "Here Top 5 Daily Sale Report available for Specific Date");
						
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
						test.log(LogStatus.FAIL, "Here Top 5 Daily Sale Report not available for Specific Date");
					}
					
					Thread.sleep(5000);
					//Check weather the report graph is available or not
					if(driver.findElement(By.xpath(excel.getData(3, 710, 1))).isDisplayed())
					{
						test.log(LogStatus.PASS, "There is Graphical type of Daily sale report is available for Specific Date");
					}
					else
					{
						test.log(LogStatus.FAIL, "Graphical report is not available for Specific Date");
					}
					
					Thread.sleep(1000);
					//Scroll the web page
				    for(int i=1; i <= 15; i++)
				    {
				    	driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
				    	Thread.sleep(1000);
				    }
				    
					Thread.sleep(5000);
					//Check weather the table format report is available or not
					if(driver.findElement(By.xpath(excel.getData(3, 711, 1))).isDisplayed())
					{
						test.log(LogStatus.PASS, "Table Format Report is available for Specific Date");
						
						Thread.sleep(3000);
				        //No.of rows
				        List<WebElement> rows = driver.findElements(By.xpath ("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr"));
				        
		/*		        //Print number of Rows
				        System.out.println("Number of Rows are : "+rows);*/
				        
						//Replace all commo's with empty space
						String expected1 = Utility.getReportProperty("Sale_Report_Net_Sale").replace(",", "");
						
						//Convert the String value of the Sale_Report_Net_Sale element into float value
						float expect1 = Float.parseFloat(expected1); 

						//Replace all commo's with empty space
						String expected2 = Utility.getReportProperty("Sale_Report_Tax").replace(",", "");
						
						//Convert the String value of the Sale_Report_Tax element into float value
						float expect2 = Float.parseFloat(expected2);  

						//Replace all commo's with empty space
						String expected3 = Utility.getReportProperty("Sale_Report_Discount").replace(",", "");
						
						//Convert the String value of the Sale_Report_Discount element into float value
						float expect3 = Float.parseFloat(expected3);     	

						//Replace all commo's with empty space
						String expected4 = Utility.getReportProperty("Sale_Report_Grand_Sale").replace(",", "");
								
						//Convert the String value of the Sale_Report_Grand_Sale element into float value
						float expect4= Float.parseFloat(expected4);  
				        			        
				        //Check weather the Net Sale is correct or not
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[2]/span")).getText().equals(Utility.getReportProperty("Sale_Report_Net_Sale")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Daily Sale reports are same for Sale Amount For Stores");
				        	
				        	//Get the Total value of Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[2]/span")).getText();
				        	
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
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[2]/span")).getText();

				        	System.out.println("The Actual Net Sale value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Net Sale value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Daily Sale reports are different for Sale Amount For Stores");
				        	
				        	//Get the Total value of Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[2]/span")).getText();
				        	
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
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[3]/span")).getText().equals(Utility.getReportProperty("Sale_Report_Tax")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Daily Sale reports are same for Tax");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[3]/span")).getText();
				        	
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
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[3]/span")).getText();

				        	System.out.println("The Actual Tax value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Tax value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Daily Sale reports are different for Tax");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[3]/span")).getText();
				        	
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
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[4]/span")).getText().equals(Utility.getReportProperty("Sale_Report_Discount")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Daily Sale reports are same for Discount");
				        	
				        	//Get the Total value of Discount
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[4]/span")).getText();
				        	
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
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[4]/span")).getText();

				        	System.out.println("The Actual Discount value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Discount value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Daily Sale reports are different for Discount");
				        	
				        	//Get the Total value of Discount
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[4]/span")).getText();
				        	
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
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[5]/span")).getText().equals(Utility.getReportProperty("Sale_Report_Grand_Sale")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Daily Sale reports are same for Grand Sale");
				        	
				        	//Get the Total value of Grand Sale
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[5]/span")).getText();
				        	
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
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[5]/span")).getText();

				        	System.out.println("The Actual Grand Sale value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Grand Sale value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Daily Sale reports are different for Grand Sale");
				        	
				        	//Get the Total value of Grand Sale
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[5]/span")).getText();
				        	
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
				        
				        
					}
					else
					{
						test.log(LogStatus.FAIL, "Table Format Report is not available for Specific Date For State ");
					}
				}
			}
			
			catch(Exception e)
			{

				test.log(LogStatus.FAIL, "Daily Sale Report is not available for Specific Date For State");

			}
					
			
			Thread.sleep(3000);
			System.out.println("********************* End of State Report *************************");
			test.log(LogStatus.INFO, "********************* End of State Report *************************");
			Thread.sleep(1000);
			//Scroll the web page
		    for(int i=1; i <= 15; i++)
		    {
		    	driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_UP);
		    	Thread.sleep(1000);wb.close();
		    }
		}

		@Test(enabled=false,priority=13)
		public void Daily_Enterprise_Report_Method_For_ZipCode(WebDriver driver) throws Exception
		{
			File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			 FileInputStream fis = new FileInputStream(src);
					
			 XSSFWorkbook wb = new XSSFWorkbook(fis);
					
		     XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		     ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			System.out.println("********************* View The Zip Code Report *************************");
			test.log(LogStatus.INFO, "********************* View The Zip Code Report *************************");
			
			Thread.sleep(3000);
			//Click the Select option
			driver.findElement(By.xpath(excel.getData(3, 732, 1))).click();
			//Enter the required filter
			driver.findElement(By.xpath(excel.getData(3, 733, 1))).sendKeys("Zip Code");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 733, 1))).sendKeys(Keys.ENTER);
			
			
			Thread.sleep(1500);
			//Click the Select Some Options
			driver.findElement(By.xpath(excel.getData(3, 734, 1))).click();
			//Enter the required option
			driver.findElement(By.xpath(excel.getData(3, 735, 1))).sendKeys(Utility.getReportProperty("Zipcode"));
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 735, 1))).sendKeys(Keys.ENTER);
		
			Thread.sleep(1500);
			//Click the Time Period Options
			driver.findElement(By.xpath(excel.getData(3, 736, 1))).click();
			//Enter the required option
			driver.findElement(By.xpath(excel.getData(3, 737, 1))).sendKeys("Date Range");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 737, 1))).sendKeys(Keys.ENTER);
		
			//Clear the date field
			driver.findElement(By.xpath(excel.getData(3, 738, 1))).clear();
			//Enter the required date
			driver.findElement(By.xpath(excel.getData(3, 738, 1))).sendKeys(Utility.getReportProperty("Date_Range_From"));

			//Clear the date field
			driver.findElement(By.xpath(excel.getData(3, 739, 1))).clear();
			//Enter the required date
			driver.findElement(By.xpath(excel.getData(3, 739, 1))).sendKeys(Utility.getReportProperty("Date_Range_To"));
			
			Thread.sleep(1000);
			//Click the Run button
			driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
			
			try
			{
				Thread.sleep(3000);
				//Check weather the report is available for the selected time period
				if(!driver.findElement(By.xpath(excel.getData(3, 704, 1))).isDisplayed())
				{
					test.log(LogStatus.PASS, "Daily Sale Report available for Specific Date");
					
					Thread.sleep(3000);
					//Check Weather the Top 5 Daily sale available or not
					if(driver.findElement(By.xpath(excel.getData(3, 705, 1))).isDisplayed())
					{
						test.log(LogStatus.PASS, "Here Top 5 Daily Sale Report available for Specific Date");
						
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
						test.log(LogStatus.FAIL, "Here Top 5 Daily Sale Report not available for Specific Date");
					}
					
					Thread.sleep(5000);
					//Check weather the report graph is available or not
					if(driver.findElement(By.xpath(excel.getData(3, 710, 1))).isDisplayed())
					{
						test.log(LogStatus.PASS, "There is Graphical type of Daily sale report is available for Specific Date");
					}
					else
					{
						test.log(LogStatus.FAIL, "Graphical report is not available for Specific Date");
					}
					
					Thread.sleep(1000);
					//Scroll the web page
				    for(int i=1; i <= 15; i++)
				    {
				    	driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
				    	Thread.sleep(1000);
				    }
				    
					Thread.sleep(5000);
					//Check weather the table format report is available or not
					if(driver.findElement(By.xpath(excel.getData(3, 711, 1))).isDisplayed())
					{
						test.log(LogStatus.PASS, "Table Format Report is available for Specific Date");
						
						Thread.sleep(3000);
				        //No.of rows
				        List<WebElement> rows = driver.findElements(By.xpath ("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr"));
				        
		/*		        //Print number of Rows
				        System.out.println("Number of Rows are : "+rows);*/
				        
						//Replace all commo's with empty space
						String expected1 = Utility.getReportProperty("Sale_Report_Net_Sale").replace(",", "");
						
						//Convert the String value of the Sale_Report_Net_Sale element into float value
						float expect1 = Float.parseFloat(expected1); 

						//Replace all commo's with empty space
						String expected2 = Utility.getReportProperty("Sale_Report_Tax").replace(",", "");
						
						//Convert the String value of the Sale_Report_Tax element into float value
						float expect2 = Float.parseFloat(expected2);  

						//Replace all commo's with empty space
						String expected3 = Utility.getReportProperty("Sale_Report_Discount").replace(",", "");
						
						//Convert the String value of the Sale_Report_Discount element into float value
						float expect3 = Float.parseFloat(expected3);     	

						//Replace all commo's with empty space
						String expected4 = Utility.getReportProperty("Sale_Report_Grand_Sale").replace(",", "");
								
						//Convert the String value of the Sale_Report_Grand_Sale element into float value
						float expect4= Float.parseFloat(expected4);  
				        			        
				        //Check weather the Net Sale is correct or not
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[2]/span")).getText().equals(Utility.getReportProperty("Sale_Report_Net_Sale")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Daily Sale reports are same for Sale Amount For Stores");
				        	
				        	//Get the Total value of Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[2]/span")).getText();
				        	
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
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[2]/span")).getText();

				        	System.out.println("The Actual Net Sale value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Net Sale value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Daily Sale reports are different for Sale Amount For Stores");
				        	
				        	//Get the Total value of Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[2]/span")).getText();
				        	
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
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[3]/span")).getText().equals(Utility.getReportProperty("Sale_Report_Tax")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Daily Sale reports are same for Tax");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[3]/span")).getText();
				        	
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
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[3]/span")).getText();

				        	System.out.println("The Actual Tax value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Tax value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Daily Sale reports are different for Tax");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[3]/span")).getText();
				        	
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
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[4]/span")).getText().equals(Utility.getReportProperty("Sale_Report_Discount")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Daily Sale reports are same for Discount");
				        	
				        	//Get the Total value of Discount
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[4]/span")).getText();
				        	
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
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[4]/span")).getText();

				        	System.out.println("The Actual Discount value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Discount value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Daily Sale reports are different for Discount");
				        	
				        	//Get the Total value of Discount
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[4]/span")).getText();
				        	
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
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[5]/span")).getText().equals(Utility.getReportProperty("Sale_Report_Grand_Sale")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Daily Sale reports are same for Grand Sale");
				        	
				        	//Get the Total value of Grand Sale
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[5]/span")).getText();
				        	
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
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[5]/span")).getText();

				        	System.out.println("The Actual Grand Sale value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Grand Sale value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Daily Sale reports are different for Grand Sale");
				        	
				        	//Get the Total value of Grand Sale
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[5]/span")).getText();
				        	
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
				        
				        
					}
					else
					{
						test.log(LogStatus.FAIL, "Table Format Report is not available for Specific Date For Zip Code");
					}
				}
			}
			
			catch(Exception e)
			{

				test.log(LogStatus.FAIL, "Daily Sale Report is not available for Specific Date For Zip Code");

			}
					
			
			Thread.sleep(3000);
			System.out.println("********************* End of Zip Code Report *************************");
			test.log(LogStatus.INFO, "********************* End of Zip Code Report *************************");

		
		Thread.sleep(1000);
		//Scroll the web page
	    for(int i=1; i <= 15; i++)
	    {
	    	driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_UP);
	    	Thread.sleep(1000);wb.close();
	    }
	}

}

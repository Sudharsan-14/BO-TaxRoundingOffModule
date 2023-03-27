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
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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

import epicList_Chrome.Utility;
import newReadExcelFile_New.ExcelDataConfig;

public class Verify_Weekly_Report{
	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Verify_Weekly_Report");
	
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
			Weekly_SaleSummary_method_Report_Openpage(driver);
			Weekly_Summary_Sale_method_Report_This_Week(driver);
			Weekly_Summary_Sale_method_Report_Last_Week(driver);
			Weekly_Summary_Sale_method_Report_Last7days(driver);
			Thread.sleep(1500);
		}
	
			@Test(priority=180,enabled = false)
			public void Weekly_SaleSummary_method_Report_Openpage(WebDriver driver) throws Exception
			{
				 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				 FileInputStream fis = new FileInputStream(src);
						
				 XSSFWorkbook wb = new XSSFWorkbook(fis);
						
			     XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			     ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				/*WebElement html = driver.findElement(By.tagName("html"));
				html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
				html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
				html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
				html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
				
				//Click the Reports option
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
				driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"report/weeklySale");
				
				// Create instance of Java script executor
				JavascriptExecutor je1 = (JavascriptExecutor) driver;
				//Identify the WebElement which will appear after scrolling down
				WebElement element1 = driver.findElement(By.xpath("//span[.=' Weekly Summary ']"));
				//Scroll the page till the Weekly Summary option present
				je1.executeScript("arguments[0].scrollIntoView(true);",element1); 
		
				driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
				Thread.sleep(2000);
				driver.findElement(By.xpath(excel.getData(1, 1454, 1))).click();
				//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[1]/div/div/div/div/scrollable-tabset/div/button[2]")).click();
				//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[1]/div/div/div/div/scrollable-tabset/div/button[2]")).click();
				//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[1]/div/div/div/div/scrollable-tabset/div/button[2]")).click();
				//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[1]/div/div/div/div/scrollable-tabset/div/button[2]")).click();
				try
				{
				//Check weather the Weekly Summary Report page is loaded or not
				if(driver.findElement(By.xpath(excel.getData(1, 1454, 1))).getText().equalsIgnoreCase("Weekly Summary"))
				{
					test.log(LogStatus.PASS, "Weekly Summary Report page loaded successfully");
				
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));
				}
				else
				{
					test.log(LogStatus.FAIL, "Weekly Summary Report page loaded fail");
				
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
			
			@Test(priority=181,enabled = false)
			public void Weekly_Summary_Sale_method_Report_This_Week(WebDriver driver) throws Exception
			{
				 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
					
				 FileInputStream fis = new FileInputStream(src);
						
				 XSSFWorkbook wb = new XSSFWorkbook(fis);
						
			     XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			     ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				Thread.sleep(3000);
				//Click the Time Period option
				driver.findElement(By.xpath(excel.getData(1, 1455, 1))).click();
				//Enter the required Time Period
				driver.findElement(By.xpath(excel.getData(1, 1456, 1))).sendKeys("This week");
				//Press the Enter Key
				driver.findElement(By.xpath(excel.getData(1, 1456, 1))).sendKeys(Keys.ENTER);
				
				Thread.sleep(2000);
				//Click the Run Button
				driver.findElement(By.xpath(excel.getData(1, 572, 1))).click();
				
				Thread.sleep(10000);
				
				try{
					
					//Check weather the Table Type report available or not for This Week
					if(driver.findElement(By.xpath(excel.getData(1, 1457, 1))).isDisplayed())
					{
						test.log(LogStatus.PASS, "Table format reprot is available for This Week");
									
						
						List<WebElement> rows = driver.findElements(By.xpath(excel.getData(1, 1458, 1)));
						for(int i = 2; i <= rows.size(); i++)
						{
							if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+i+"]/td[1]")).getText().equalsIgnoreCase("Gross Sales"))
							{
								
								List<WebElement> Column = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+i+"]/td"));
								
					        	//Replace all commo's with empty space
					        	String expected1 = Utility.getReportProperty("This_Weekly_Gross_Sale").replace("/[^A-Za-z ,₹$]/", "");
					        	
					        	//Convert the String value of the This_Weekly_Gross_Sale element into float value
					        	float expect1 = Float.parseFloat(expected1);  
					        	
					        	//Get the Total value of Check Count
					        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+i+"]/td["+Column.size()+"]")).getText();
					        	
					        	//Replace all commo's with empty space
					        	String actualText= actualText1.replaceAll("/[^A-Za-z ,₹$]/", "");
					        	
								Thread.sleep(2000);
								//Check the Gross Sale
								if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+i+"]/td["+Column.size()+"]")).getText().equals(Utility.getReportProperty("This_Weekly_Gross_Sale")))
								{
									test.log(LogStatus.PASS, "Actual and Expected Gross Sale is same");
										        	
						        	//Convert the String value of the Gross Sale Total element into float value
						        	float actual = Float.parseFloat(actualText);
						        	
						        	//Print the actual value
						        	System.out.println("The Actual Gross Sale(This Week) Value is : "+actual);
						        	
						        	test.log(LogStatus.PASS, "The Actual Gross Sale(This Week) Value is : "+ actual);
	
								}
								
								else if(expect1 == unknownValue)
								{
									test.log(LogStatus.PASS, "Here we don't have the exact expected value");
									
						        	System.out.println("The Actual Gross Sale(This Week) value is : "+actualText);
						        	
						        	test.log(LogStatus.INFO, "The Actual Gross Sale(This Week) value is : "+actualText);
								}
								
								else
								{
									test.log(LogStatus.FAIL, "Actual and Expected Gross Sale is different");
						        	
						        	//Convert the String value of the Gross Sale Total element into float value
						        	float actual = Float.parseFloat(actualText);
						        			        	
						        	//Get the different
						        	float different = actual - expect1;
						        	
						        	//Print the different value
						        	System.out.println("Gross Sale(This Week) Actual value is : "+actualText1);
						        	
						        	test.log(LogStatus.FAIL, "Gross Sale(This Week) Actual value is : "+actualText1);	
						        	
						        	//Print the different value
						        	System.out.println("Gross Sale(This Week) different is : "+different);
						        	
						        	test.log(LogStatus.FAIL, "Gross Sale(This Week) different is : "+different);	
	
								}
								
							}
						}
						
						for(int i = 2; i <= rows.size(); i++)
						{
							if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+i+"]/td[1]")).getText().equalsIgnoreCase("Net Sales"))
							{
								
								List<WebElement> Column1 = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+i+"]/td"));
								
					        	//Replace all commo's with empty space
					        	String expected2 = Utility.getReportProperty("This_Weekly_Net_Sale").replace("/[^A-Za-z ,₹$]/", "");
					        	
					        	//Convert the String value of the This_Weekly_Net_Sale element into float value
					        	float expect2 = Float.parseFloat(expected2);  
					        	
					        	//Get the Total value of Net Sale
					        	String actualText3 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+i+"]/td["+Column1.size()+"]")).getText();
					        	
					        	//Replace all commo's with empty space
					        	String actualText2 = actualText3.replaceAll("/[^A-Za-z ,₹$]/", "");
									
								Thread.sleep(2000);
								//Check the Net Sale
								if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+i+"]/td["+Column1.size()+"]")).getText().equals(Utility.getReportProperty("This_Weekly_Net_Sale")))
								{
									test.log(LogStatus.PASS, "Actual and Expected Net Sale is same");
										        	
						        	//Convert the String value of the Net Sale Total element into float value
						        	float actual = Float.parseFloat(actualText2);
						        	
						        	//Print the actual value
						        	System.out.println("The Actual Net Sale(This Week) Value is : "+actual);
						        	
						        	test.log(LogStatus.PASS, "The Actual Net Sale(This Week) Value is : "+ actual);
	
								}
								
								else if(expect2 == unknownValue)
								{
									test.log(LogStatus.PASS, "Here we don't have the exact expected value");
									
						        	System.out.println("The Actual Net Sale(This Week) value is : "+actualText2);
						        	
						        	test.log(LogStatus.INFO, "The Actual Net Sale(This Week) value is : "+actualText2);
								}
								
								else
								{
									test.log(LogStatus.FAIL, "Actual and Expected Net Sale is different");
						        	
						        	//Convert the String value of the Gross Sale Total element into float value
						        	float actual = Float.parseFloat(actualText2);
						        			        	
						        	//Get the different
						        	float different = actual - expect2;
						        	
						        	//Print the different value
						        	System.out.println("Net Sale(This Week) Actual value is : "+actualText3);
						        	
						        	test.log(LogStatus.FAIL, "Net Sale(This Week) Actual value is : "+actualText3);
						        	
						        	//Print the different value
						        	System.out.println("Net Sale(This Week) different is : "+different);
						        	
						        	test.log(LogStatus.FAIL, "Net Sale(This Week) different is : "+different);	
	
								}
	
								}
	
						}
	
						driver.findElement(By.tagName("htnl")).sendKeys(Keys.END);
						
						String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));

					}
				}
				catch(Exception e)
				{
					test.log(LogStatus.INFO, "Table format report is not available for This Week");wb.close();
				
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));

				}
			}
			
			@Test(priority=182,enabled = false)
			public void Weekly_Summary_Sale_method_Report_Last_Week(WebDriver driver) throws Exception
			{
				 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
					
				 FileInputStream fis = new FileInputStream(src);
						
				 XSSFWorkbook wb = new XSSFWorkbook(fis);
						
			     XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			     ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				Thread.sleep(3000);
				//Click the Time Period option
				driver.findElement(By.xpath(excel.getData(1, 1455, 1))).click();
				//Enter the required Time Period
				driver.findElement(By.xpath(excel.getData(1, 1456, 1))).sendKeys("Last week");
				//Press the Enter Key
				driver.findElement(By.xpath(excel.getData(1, 1456, 1))).sendKeys(Keys.ENTER);
				
				Thread.sleep(2000);
				//Click the Run Button
				driver.findElement(By.xpath(excel.getData(1, 572, 1))).click();Thread.sleep(10000);
				
				try
				{
					//Check weather the Table Type report available or not for Last Week
					if(driver.findElement(By.xpath(excel.getData(1, 1457, 1))).isDisplayed())
					{

						test.log(LogStatus.PASS, "Table format reprot is available for Last Week");
									
						List<WebElement> rows = driver.findElements(By.xpath(excel.getData(1, 1458, 1)));
						for(int i = 2; i <= rows.size(); i++)
						{
							if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+i+"]/td[1]")).getText().equalsIgnoreCase("Gross Sales"))
							{
								
								List<WebElement> Column = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+i+"]/td"));
								
					        	//Replace all commo's with empty space
					        	String expected1 = Utility.getReportProperty("This_Weekly_Gross_Sale").replace("/[^A-Za-z ,₹$]/", "");
					        	
					        	//Convert the String value of the This_Weekly_Gross_Sale element into float value
					        	float expect1 = Float.parseFloat(expected1);  
					        	
					        	//Get the Total value of Check Count
					        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+i+"]/td["+Column.size()+"]")).getText();
					        	
					        	//Replace all commo's with empty space
					        	String actualText= actualText1.replaceAll("/[^A-Za-z ,₹$]/", "");
					        	
								Thread.sleep(2000);
								//Check the Gross Sale
								if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+i+"]/td["+Column.size()+"]")).getText().equals(Utility.getReportProperty("This_Weekly_Gross_Sale")))
								{
									test.log(LogStatus.PASS, "Actual and Expected Gross Sale is same");
										        	
						        	//Convert the String value of the Gross Sale Total element into float value
						        	float actual = Float.parseFloat(actualText);
						        	
						        	//Print the actual value
						        	System.out.println("The Actual Gross Sale(Last Week) Value is : "+actual);
						        	
						        	test.log(LogStatus.PASS, "The Actual Gross Sale(Last Week) Value is : "+ actual);

								}
								
								else if(expect1 == unknownValue)
								{
									test.log(LogStatus.PASS, "Here we don't have the exact expected value");
									
						        	System.out.println("The Actual Gross Sale(Last Week) value is : "+actualText);
						        	
						        	test.log(LogStatus.INFO, "The Actual Gross Sale(Last Week) value is : "+actualText);
								}
								
								else
								{
									test.log(LogStatus.FAIL, "Actual and Expected Gross Sale is different");
						        	
						        	//Convert the String value of the Gross Sale Total element into float value
						        	float actual = Float.parseFloat(actualText);
						        			        	
						        	//Get the different
						        	float different = actual - expect1;
						        	
						        	//Print the different value
						        	System.out.println("Gross Sale(Last Week) Actual value is : "+actualText1);
						        	
						        	test.log(LogStatus.FAIL, "Gross Sale(Last Week) Actual value is : "+actualText1);
						        	
						        	//Print the different value
						        	System.out.println("Gross Sale(Last Week) different is : "+different);
						        	
						        	test.log(LogStatus.FAIL, "Gross Sale(Last Week) different is : "+different);	

								}
								
							}
							String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
							String s="data:image/png;base64,"+scnShot;
							test.log(LogStatus.INFO,test.addScreenCapture(s));

						}
						
						for(int i = 2; i <= rows.size(); i++)
						{
							if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+i+"]/td[1]")).getText().equalsIgnoreCase("Net Sales"))
							{
								
								List<WebElement> Column1 = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+i+"]/td"));
								
					        	//Replace all commo's with empty space
					        	String expected2 = Utility.getReportProperty("This_Weekly_Net_Sale").replace("/[^A-Za-z ,₹$]/", "");
					        	
					        	//Convert the String value of the This_Weekly_Net_Sale element into float value
					        	float expect2 = Float.parseFloat(expected2);  
					        	
					        	//Get the Total value of Check Count
					        	String actualText3 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+i+"]/td["+Column1.size()+"]")).getText();
					        	
					        	//Replace all commo's with empty space
					        	String actualText2 = actualText3.replaceAll("/[^A-Za-z ,₹$]/", "");
									
								Thread.sleep(2000);
								//Check the Net Sale
								if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+i+"]/td["+Column1.size()+"]")).getText().equals(Utility.getReportProperty("This_Weekly_Net_Sale")))
								{
									test.log(LogStatus.PASS, "Actual and Expected Net Sale is same");
										        	
						        	//Convert the String value of the Net Sale Total element into float value
						        	float actual = Float.parseFloat(actualText2);
						        	
						        	//Print the actual value
						        	System.out.println("The Actual Net Sale(Last Week) Value is : "+actual);
						        	
						        	test.log(LogStatus.PASS, "The Actual Net Sale(Last Week) Value is : "+ actual);

								}
								
								else if(expect2 == unknownValue)
								{
									test.log(LogStatus.PASS, "Here we don't have the exact expected value");
									
						        	System.out.println("The Actual Net Sale(Last Week) value is : "+actualText2);
						        	
						        	test.log(LogStatus.INFO, "The Actual Net Sale(Last Week) value is : "+actualText2);
								}
								
								else
								{
									test.log(LogStatus.FAIL, "Actual and Expected Net Sale is different");
						        	
						        	//Convert the String value of the Gross Sale Total element into float value
						        	float actual = Float.parseFloat(actualText2);
						        			        	
						        	//Get the different
						        	float different = actual - expect2;
						        	
						        	//Print the different value
						        	System.out.println("Net Sale(Last Week) Actual value is : "+actualText3);
						        	
						        	test.log(LogStatus.FAIL, "Net Sale(Last Week) Actual value is : "+actualText3);
						        	
						        	//Print the different value
						        	System.out.println("Net Sale(Last Week) different is : "+different);
						        	
						        	test.log(LogStatus.FAIL, "Net Sale(Last Week) different is : "+different);	

								}

								}

						}
						driver.findElement(By.tagName("html")).sendKeys(Keys.END);
						String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));

					}
				}
				catch(Exception e)
				{
					test.log(LogStatus.INFO, "Table format report is not available for Last Week");wb.close();
			
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));

				}
			}
			
			@Test(priority=183,enabled = false)
			public void Weekly_Summary_Sale_method_Report_Last7days(WebDriver driver) throws Exception
			{
				 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				 FileInputStream fis = new FileInputStream(src);
						
				 XSSFWorkbook wb = new XSSFWorkbook(fis);
						
			     XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			     ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				Thread.sleep(3000);
				//Click the Time Period option
				driver.findElement(By.xpath(excel.getData(1, 1455, 1))).click();
				//Enter the required Time Period
				driver.findElement(By.xpath(excel.getData(1, 1456, 1))).sendKeys("Last 7 days");
				//Press the Enter Key
				driver.findElement(By.xpath(excel.getData(1, 1456, 1))).sendKeys(Keys.ENTER);
				
				Thread.sleep(2000);
				//Click the Run Button
				driver.findElement(By.xpath(excel.getData(1, 572, 1))).click();
				
				Thread.sleep(10000);
				try
				{
					//Check weather the Table Type report available or not for Last 7 days
					if(driver.findElement(By.xpath(excel.getData(1, 1457, 1))).isDisplayed())
					{

						test.log(LogStatus.PASS, "Table format reprot is available for Last 7 Days");
									
						List<WebElement> rows = driver.findElements(By.xpath(excel.getData(1, 1458, 1)));
						for(int i = 2; i <= rows.size(); i++)
						{
							if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+i+"]/td[1]")).getText().equalsIgnoreCase("Gross Sales"))
							{
								
								List<WebElement> Column = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+i+"]/td"));
								
					        	//Replace all commo's with empty space
					        	String expected1 = Utility.getReportProperty("This_Weekly_Gross_Sale").replace("/[^A-Za-z ,₹$]/", "");
					        	
					        	//Convert the String value of the This_Weekly_Gross_Sale element into float value
					        	float expect1 = Float.parseFloat(expected1);  
					        	
					        	//Get the Total value of Check Count
					        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+i+"]/td["+Column.size()+"]")).getText();
					        	
					        	//Replace all commo's with empty space
					        	String actualText= actualText1.replaceAll("/[^A-Za-z ,₹$]/", "");
					        	
								Thread.sleep(2000);
								//Check the Gross Sale
								if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+i+"]/td["+Column.size()+"]")).getText().equals(Utility.getReportProperty("This_Weekly_Gross_Sale")))
								{
									test.log(LogStatus.PASS, "Actual and Expected Gross Sale is same");
										        	
						        	//Convert the String value of the Gross Sale Total element into float value
						        	float actual = Float.parseFloat(actualText);
						        	
						        	//Print the actual value
						        	System.out.println("The Actual Gross Sale(Last 7 Days) Value is : "+actual);
						        	
						        	test.log(LogStatus.PASS, "The Actual Gross Sale(Last 7 Days) Value is : "+ actual);

								}
								
								else if(expect1 == unknownValue)
								{
									test.log(LogStatus.PASS, "Here we don't have the exact expected value");
									
						        	System.out.println("The Actual Gross Sale(Last 7 Days) value is : "+actualText);
						        	
						        	test.log(LogStatus.INFO, "The Actual Gross Sale(Last 7 Days) value is : "+actualText);
								}
								
								else
								{
									test.log(LogStatus.FAIL, "Actual and Expected Gross Sale is different");
						        	
						        	//Convert the String value of the Gross Sale Total element into float value
						        	float actual = Float.parseFloat(actualText);
						        			        	
						        	//Get the different
						        	float different = actual - expect1;
						        	
						        	//Print the different value
						        	System.out.println("Gross Sale(Last 7 Days) Actual value is : "+actualText1);
						        	
						        	test.log(LogStatus.FAIL, "Gross Sale(Last 7 Days) Actual value is : "+actualText1);
						        	
						        	//Print the different value
						        	System.out.println("Gross Sale(Last 7 Days) different is : "+different);
						        	
						        	test.log(LogStatus.FAIL, "Gross Sale(Last 7 Days) different is : "+different);	

								}
								String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
								String s="data:image/png;base64,"+scnShot;
								test.log(LogStatus.INFO,test.addScreenCapture(s));

							}
						}
						
						for(int i = 2; i <= rows.size(); i++)
						{
							if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+i+"]/td[1]")).getText().equalsIgnoreCase("Net Sales"))
							{
								
								List<WebElement> Column1 = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+i+"]/td"));
								
					        	//Replace all commo's with empty space
					        	String expected2 = Utility.getReportProperty("This_Weekly_Net_Sale").replace("/[^A-Za-z ,₹$]/", "");
					        	
					        	//Convert the String value of the This_Weekly_Net_Sale element into float value
					        	float expect2 = Float.parseFloat(expected2);  
					        	
					        	//Get the Total value of Check Count
					        	String actualText3 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+i+"]/td["+Column1.size()+"]")).getText();
					        	
					        	//Replace all commo's with empty space
					        	String actualText2 = actualText3.replaceAll("/[^A-Za-z ,₹$]/", "");
									
								Thread.sleep(2000);
								//Check the Net Sale
								if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+i+"]/td["+Column1.size()+"]")).getText().equals(Utility.getReportProperty("This_Weekly_Net_Sale")))
								{
									test.log(LogStatus.PASS, "Actual and Expected Net Sale is same");
										        	
						        	//Convert the String value of the Net Sale Total element into float value
						        	float actual = Float.parseFloat(actualText2);
						        	
						        	//Print the actual value
						        	System.out.println("The Actual Net Sale(Last 7 Days) Value is : "+actual);
						        	
						        	test.log(LogStatus.PASS, "The Actual Net Sale(Last 7 Days) Value is : "+ actual);

								}
								
								else if(expect2 == unknownValue)
								{
									test.log(LogStatus.PASS, "Here we don't have the exact expected value");
									
						        	System.out.println("The Actual Net Sale(Last 7 Days) value is : "+actualText2);
						        	
						        	test.log(LogStatus.INFO, "The Actual Net(Last 7 Days) Sale value is : "+actualText2);
								}
								
								else
								{
									test.log(LogStatus.FAIL, "Actual and Expected Net Sale is different");
						        	
						        	//Convert the String value of the Gross Sale Total element into float value
						        	float actual = Float.parseFloat(actualText2);
						        			        	
						        	//Get the different
						        	float different = actual - expect2;
						        	
						        	//Print the different value
						        	System.out.println("Net Sale(Last 7 Days) Actual value is : "+actualText3);
						        	
						        	test.log(LogStatus.FAIL, "Net Sale(Last 7 Days) Actual value is : "+actualText3);
						        	
						        	//Print the different value
						        	System.out.println("Net Sale(Last 7 Days) different is : "+different);
						        	
						        	test.log(LogStatus.FAIL, "Net Sale(Last 7 Days) different is : "+different);	

								}
							}
						}
					}
					driver.findElement(By.tagName("html")).sendKeys(Keys.END);
					
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));

				}
				catch(Exception e)
				{
					test.log(LogStatus.INFO, "Table format report is not available for Last 7 Days");wb.close();
				
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));

				
				}
			}
}

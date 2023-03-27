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
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import newReadExcelFile_New.ExcelDataConfig;


public class Verify_Till_Reports {
	public WebDriver driver;

	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Verify_Till_Reports");
	
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
			Till_Report_Method_viewpage(driver);
			Till_Report_Method_verifyAll(driver);
			Till_Report_Method_Global(driver);
			Till_Report_Method_User(driver);
			Thread.sleep(1500);
		}

		@Test(enabled=false,priority=9)
		public void Till_Report_Method_viewpage(WebDriver driver) throws Exception
		{
			 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			/*	//Click the Reports option
			driver.findElement(By.xpath("//span[.='Reports']")).click();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			// Create instance of Java script executor
			JavascriptExecutor je = (JavascriptExecutor) driver;
			//Identify the WebElement which will appear after scrolling down
			WebElement element = driver.findElement(By.xpath("//span[.='Till']"));
			//Scroll the page till the Till option present
			je.executeScript("arguments[0].scrollIntoView(true);",element); 
	        //Click the Till Option		
			driver.findElement(By.xpath("//span[.='Till']")).click(); */
	
			Thread.sleep(3000);
			//Enter the Users URL
			driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"tillReport");
	
			Thread.sleep(5000);
			try
			{
			//Check Till page opened or not
			if(driver.findElement(By.xpath(excel.getData(1, 1002, 1))).getText().equalsIgnoreCase("Till"))
			{
				test.log(LogStatus.PASS,"Till Report page loaded Successfully");
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));
			}
			else
			{
				test.log(LogStatus.FAIL,"Till Report page loaded Failed");
			
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
	
		@Test(enabled=false,priority=13)
		public void Till_Report_Method_verifyAll(WebDriver driver) throws Exception
		{   
			File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath")); 
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			//Select the Employee option
			driver.findElement(By.xpath(excel.getData(1, 1003, 1))).click();
			//Enter the required Employee
			driver.findElement(By.xpath(excel.getData(1, 1004, 1))).sendKeys("All");
			//Press the Enter key
			driver.findElement(By.xpath(excel.getData(1, 1004, 1))).sendKeys(Keys.ENTER);
			
			//Select the Till type option
			driver.findElement(By.xpath(excel.getData(1, 1005, 1))).click();
			//Enter the required Till type
			driver.findElement(By.xpath(excel.getData(1, 1006, 1))).sendKeys("ALL");
			//Press the Enter key
			driver.findElement(By.xpath(excel.getData(1, 1006, 1))).sendKeys(Keys.ENTER);
			
			//Click the Time Period option
			driver.findElement(By.xpath(excel.getData(1, 1007, 1))).click();
			//Enter the required Period of time for Specific date
			driver.findElement(By.xpath(excel.getData(1, 1008, 1))).sendKeys("Date Range");	    
			//Press the Enter key
			driver.findElement(By.xpath(excel.getData(1, 1008, 1))).sendKeys(Keys.ENTER);
			Thread.sleep(2000);
			
			//Clear the date field
			driver.findElement(By.xpath(excel.getData(1, 1009, 1))).clear();
			//Select the required Specific date
			driver.findElement(By.xpath(excel.getData(1, 1009, 1))).sendKeys(Utility.getReportProperty("Till_Date_Range_From"));
			
			
			//Clear the date field
			driver.findElement(By.xpath(excel.getData(1, 1010, 1))).clear();
			//Select the required Specific date
			driver.findElement(By.xpath(excel.getData(1, 1010, 1))).sendKeys(Utility.getReportProperty("Till_Date_Range_To"));
			
			
			//Click the Run
			driver.findElement(By.cssSelector(excel.getData(1, 974, 4))).click();
			Thread.sleep(10000);
						
			try
			{
				//Check weather the report is available for the selected time period
				if(!driver.findElement(By.xpath(excel.getData(1, 704, 1))).isDisplayed())
				{

					test.log(LogStatus.PASS, "Till Report(All) is available for Specific Date");
				
					System.out.println("***** Till Report for All *****");
					
					test.log(LogStatus.INFO, "***** Till Report for All *****");
					
					List<WebElement> rows = driver.findElements(By.xpath(excel.getData(1, 1000, 1)));
					
					//Get the Cash Expected Value
					String cash_Expected = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[4]")).getText().replaceAll("[a-zA-Z $ ₹ , :]", "");
									
					System.out.println("Total Cash Expected is : "+cash_Expected);
					
					test.log(LogStatus.INFO, "Total Cash Expected is : "+cash_Expected);
				
					
					//Get the amount Value
					String amount = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[5]")).getText().replaceAll("[a-zA-Z $ ₹ , :]", "");
					
					System.out.println("Total Amount is : "+amount);
					
					test.log(LogStatus.INFO, "Total Amount is : "+amount);
					
					//Get the Over/Shortage Value
					String over_Or_Shortage = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[6]")).getText().replaceAll("[a-zA-Z $ ₹ , :]", "");
									
					System.out.println("Total Over/Shortage is : "+over_Or_Shortage);
					
					test.log(LogStatus.INFO, "Total Over/Shortage is : "+over_Or_Shortage);
					
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));
					
					
					

					
					Thread.sleep(2000);
					Thread.sleep(3000);
					for(int i=2; i < rows.size();i++)
					{
						String tillName = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+i+"]/td[1]")).getText();
						
						String date = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+i+"]/td[2]")).getText();
						
						String user = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+i+"]/td[3]")).getText();
						
						String cashExpected = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+i+"]/td[4]")).getText();
				
						String amount1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+i+"]/td[5]")).getText();
				
						Thread.sleep(2000);
						
						test.log(LogStatus.INFO, "The Till("+tillName+") have the following details : ");
						
						test.log(LogStatus.INFO, "Date and Time is : "+date);
						
						test.log(LogStatus.INFO, "User name is : "+user);
						
						test.log(LogStatus.INFO, "Cash Expexted is : "+cashExpected);
						
						test.log(LogStatus.INFO, "Amount is : "+amount1);
						
						//Click the required Check
						driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+i+"]/td[1]/a")).click();
				
						Thread.sleep(6000);
						
						test.log(LogStatus.INFO, "*******  TILL SUMMARY("+tillName+")  *******");
						
						test.log(LogStatus.INFO, "Total Starting Cash is : "+driver.findElement(By.xpath(excel.getData(1, 1020, 1))).getText());
						
						test.log(LogStatus.INFO, "Pay-Outs is : "+driver.findElement(By.xpath(excel.getData(1, 1021, 1))).getText());
				
						test.log(LogStatus.INFO, "Pay-Ins is : "+driver.findElement(By.xpath(excel.getData(1, 1022, 1))).getText());
				
						test.log(LogStatus.INFO, "Tip-Pay_Outs is : "+driver.findElement(By.xpath(excel.getData(1, 1023, 1))).getText());

						test.log(LogStatus.INFO, "Cash Drops is : "+driver.findElement(By.xpath(excel.getData(1, 1024, 1))).getText());
				
						test.log(LogStatus.INFO, "Refund is : "+driver.findElement(By.xpath(excel.getData(1, 1025, 1))).getText());
				
						test.log(LogStatus.INFO, "Void Cash Amount is : "+driver.findElement(By.xpath(excel.getData(1, 1026, 1))).getText());
				
						test.log(LogStatus.INFO, "Cash Transactions is : "+driver.findElement(By.xpath(excel.getData(1, 1027, 1))).getText());
				
						test.log(LogStatus.INFO, "Expected Cash in Till is : "+driver.findElement(By.xpath(excel.getData(1, 1028, 1))).getText());
						
						test.log(LogStatus.INFO, "Ending Cash Total is : "+driver.findElement(By.xpath(excel.getData(1, 1029, 1))).getText());

						
						String scnShot1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s1="data:image/png;base64,"+scnShot1;
						test.log(LogStatus.INFO,test.addScreenCapture(s1));

						
						Thread.sleep(5000);
				    	//Replace all commo's with empty space
				    	String expected1 = driver.findElement(By.xpath(excel.getData(1, 1014, 1))).getText().replace(",", "");
				    	System.out.println("Expected Cash is : "+driver.findElement(By.xpath(excel.getData(1, 1014, 1))).getText());
				    	//Convert the String value of Expected Cash in Till Summary
				    	float expect1 = Float.parseFloat(expected1);
				    	
				    	//Get Cash Expected from the Till Table and Replace all commo's with empty space
				    	String actualText1 = cashExpected.replace(",", "");
				    	
				    	//Convert the String value of the Expected Cash element into float value
				    	float actual = Float.parseFloat(actualText1);
				    	
						
						if(expect1 == actual)
						{
							test.log(LogStatus.INFO, "Expected Cash in Till("+tillName+") is : "+cashExpected);
						}
						else
						{
							test.log(LogStatus.FAIL, "Expected Cash in Till("+tillName+") Summay and Cash Expected are different");
				        			        	
				        	//Get the different
				        	float different = actual - expect1;
				        	
				        	//Print the different value
				        	System.out.println("In Till("+tillName+"),  Expected Cash Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "In Till("+tillName+"),  Expected Cash Value different is : "+different);
				        	
				        	
						}
						
				    	//Replace all commo's with empty space
				    	String expected2 = driver.findElement(By.xpath(excel.getData(1, 1030, 1))).getText().replace(",", "");
				    	
				    	//Convert the String value of Ending Cash Total Till Summary
				    	float expect2 = Float.parseFloat(expected2);  
				    	
				    	//Get Amount from the Till Table and Replace all commo's with empty space
				    	String actualText2 = amount1.replace(",", "");
				    	
				    	//Convert the String value of the amount element into float value
				    	float actual2 = Float.parseFloat(actualText2);
				    	
						if(expect2 == actual2)
						{
							test.log(LogStatus.INFO, "Expected Cash in Till is : "+amount1);
						}
						else
						{
							test.log(LogStatus.FAIL, "Expected Cash in Till Summay and Cash Expected are different");
									        			        	
				        	//Get the different
				        	float different = actual2 - expect2;
				        	
				        	//Print the different value
				        	System.out.println("In Till("+tillName+"),  Amount Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "In Till("+tillName+"),  Amount Value different is : "+different);
				        	
				        	
						}
						
						test.log(LogStatus.INFO, "Differences is : "+driver.findElement(By.xpath(excel.getData(1, 1031, 1))).getText());
				
						test.log(LogStatus.INFO, "Reason is : "+driver.findElement(By.xpath(excel.getData(1, 1032, 1))).getText());
						
						Thread.sleep(2000);
						//Click the Load Transactions button
						driver.findElement(By.xpath(excel.getData(1, 1033, 1))).click();
						Thread.sleep(1000);
						
						try
						{
							//Check whether the Load Transaction details are available or not
							if(driver.findElement(By.xpath(excel.getData(1, 1034, 1))).isDisplayed())
							{
								test.log(LogStatus.PASS, "Load Transaction details are available");
								
								List<WebElement> loadTransactionRow = driver.findElements(By.xpath(excel.getData(1, 1035, 1)));
								
									test.log(LogStatus.PASS, "Load Transactions amount total and Expected cash in Till amount are same then the Total in Load Transaction is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[14]/div/div/div[4]/table/tbody/tr["+loadTransactionRow.size()+"]/td[6]")).getText());
				
									String scnShot11 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
									String s11="data:image/png;base64,"+scnShot11;
									test.log(LogStatus.INFO,test.addScreenCapture(s11));

							}
						}
						catch(Exception e)
						{
							
							
								test.log(LogStatus.FAIL, "Load Transaction details are not available");
						
								String scnShot11 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
								String s11="data:image/png;base64,"+scnShot11;
								test.log(LogStatus.INFO,test.addScreenCapture(s11));

						}
						driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);	
						Thread.sleep(3000);Thread.sleep(1000);
						//Click the Back Button
						driver.findElement(By.xpath(excel.getData(1, 1037, 1))).click();
						
						Thread.sleep(1000);
					}
				
				}
			}
			catch(Exception gf)
			{
				test.log(LogStatus.INFO, "Till Report is not available for Specific Date");wb.close();
			
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));

			}
						
			/*		String[] date = new String[rows.size()];
			
			String[] user = new String[rows.size()];*/	
	}

		@Test(enabled=false,priority=11)
		public void Till_Report_Method_Global(WebDriver driver) throws Exception
		{
            File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			Thread.sleep(8000);   
			//Select the Employee option
			driver.findElement(By.xpath(excel.getData(1, 1003, 1))).click();
			//Enter the required Employee
			driver.findElement(By.xpath(excel.getData(1, 1004, 1))).sendKeys("All");
			//Press the Enter key
			driver.findElement(By.xpath(excel.getData(1, 1004, 1))).sendKeys(Keys.ENTER);
			
			//Select the Till type option
			driver.findElement(By.xpath(excel.getData(1, 1005, 1))).click();
			//Enter the required Till type
			driver.findElement(By.xpath(excel.getData(1, 1006, 1))).sendKeys("GLOBAL");
			//Press the Enter key
			driver.findElement(By.xpath(excel.getData(1, 1006, 1))).sendKeys(Keys.ENTER);
			
			//Click the Time Period option
			driver.findElement(By.xpath(excel.getData(1, 1007, 1))).click();
			//Enter the required Period of time for Specific date
			driver.findElement(By.xpath(excel.getData(1, 1008, 1))).sendKeys("Date Range");	    
			//Press the Enter key
			driver.findElement(By.xpath(excel.getData(1, 1008, 1))).sendKeys(Keys.ENTER);
			Thread.sleep(2000);
			
			//Clear the date field
			driver.findElement(By.xpath(excel.getData(1, 1009, 1))).clear();
			//Select the required Specific date
			driver.findElement(By.xpath(excel.getData(1, 1009, 1))).sendKeys(Utility.getReportProperty("Till_Date_Range_From"));
			
			
			//Clear the date field
			driver.findElement(By.xpath(excel.getData(1, 1010, 1))).clear();
			//Select the required Specific date
			driver.findElement(By.xpath(excel.getData(1, 1010, 1))).sendKeys(Utility.getReportProperty("Till_Date_Range_To"));
			
			
			//Click the Run
			driver.findElement(By.cssSelector(excel.getData(1, 974, 4))).click();
			Thread.sleep(10000);
			
			List<WebElement> rows = driver.findElements(By.xpath(excel.getData(1, 1000, 1)));
			
			try
			{
				//Check weather the report is available for the selected time period
				if(!driver.findElement(By.xpath(excel.getData(1, 704, 1))).isDisplayed())
				{

					
					test.log(LogStatus.PASS, "Till Report(Global) is available for Specific Date");
				
					System.out.println("***** Till Report for Global *****");
					
					test.log(LogStatus.INFO, "***** Till Report for Global *****");
					
					
					//Get the Cash Expected Value
					String cash_Expected = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[4]")).getText();
					
					System.out.println("Total Cash Expected is : "+cash_Expected);
					
					test.log(LogStatus.INFO, "Total Cash Expected is : "+cash_Expected);
				
					
					//Get the amount Value
					String amount = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[5]")).getText();
					
					System.out.println("Total Amount is : "+amount);
					
					test.log(LogStatus.INFO, "Total Amount is : "+amount);
					
					//Get the Over/Shortage Value
					String over_Or_Shortage = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[6]")).getText();
					
					System.out.println("Total Over/Shortage is : "+over_Or_Shortage);
					
					test.log(LogStatus.INFO, "Total Over/Shortage is : "+over_Or_Shortage);
					
					
					Thread.sleep(3000);
					for(int i=2; i < rows.size();i++)
					{
						String tillName = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+i+"]/td[1]")).getText();
						
						String date = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+i+"]/td[2]")).getText();
						
						String user = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+i+"]/td[3]")).getText();
						
						String cashExpected = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+i+"]/td[4]")).getText();
				
						String amount1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+i+"]/td[5]")).getText();
				
						Thread.sleep(1000);
						
						test.log(LogStatus.INFO, "The Till("+tillName+") have the following details : ");
						
						test.log(LogStatus.INFO, "Date and Time is : "+date);
						
						test.log(LogStatus.INFO, "User name is : "+user);
						
						test.log(LogStatus.INFO, "Cash Expexted is : "+cashExpected);
						
						test.log(LogStatus.INFO, "Amount is : "+amount1);
						
						//Click the required Check
						driver.findElement(By.xpath(excel.getData(1, 1038, 1))).click();
				
						Thread.sleep(6000);
						
						test.log(LogStatus.INFO, "*******  TILL SUMMARY("+tillName+")  *******");
						
						test.log(LogStatus.INFO, "Total Starting Cash is : "+driver.findElement(By.xpath(excel.getData(1, 1020, 1))).getText());
						
						test.log(LogStatus.INFO, "Pay-Outs is : "+driver.findElement(By.xpath(excel.getData(1, 1021, 1))).getText());
				
						test.log(LogStatus.INFO, "Pay-Ins is : "+driver.findElement(By.xpath(excel.getData(1, 1022, 1))).getText());
				
						test.log(LogStatus.INFO, "Tip-Pay_Outs is : "+driver.findElement(By.xpath(excel.getData(1, 1023, 1))).getText());

						test.log(LogStatus.INFO, "Cash Drops is : "+driver.findElement(By.xpath(excel.getData(1, 1024, 1))).getText());
				
						test.log(LogStatus.INFO, "Refund is : "+driver.findElement(By.xpath(excel.getData(1, 1025, 1))).getText());
				
						test.log(LogStatus.INFO, "Void Cash Amount is : "+driver.findElement(By.xpath(excel.getData(1, 1026, 1))).getText());
				
						test.log(LogStatus.INFO, "Cash Transactions is : "+driver.findElement(By.xpath(excel.getData(1, 1027, 1))).getText());
				
						test.log(LogStatus.INFO, "Expected Cash in Till is : "+driver.findElement(By.xpath(excel.getData(1, 1028, 1))).getText());
						
						test.log(LogStatus.INFO, "Ending Cash Total is : "+driver.findElement(By.xpath(excel.getData(1, 1029, 1))).getText());
				
						
						String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));

						
						Thread.sleep(5000);
				    	//Replace all commo's with empty space
				    	String expected1 = driver.findElement(By.xpath(excel.getData(1, 1014, 1))).getText().replace(",", "");
				    	
				    	//Convert the String value of Expected Cash in Till Summary
				    	float expect1 = Float.parseFloat(expected1);  
				    	
				    	//Get Cash Expected from the Till Table and Replace all commo's with empty space
				    	String actualText1 = cashExpected.replace(",", "");
				    	
				    	//Convert the String value of the Expected Cash element into float value
				    	float actual = Float.parseFloat(actualText1);
				    	
						
						if(expect1 == actual)
						{
							test.log(LogStatus.INFO, "Expected Cash in Till("+tillName+") is : "+cashExpected);
						}
						else
						{
							test.log(LogStatus.FAIL, "Expected Cash in Till("+tillName+") Summay and Cash Expected are different");
				        			        	
				        	//Get the different
				        	float different = actual - expect1;
				        	
				        	//Print the different value
				        	System.out.println("In Till("+tillName+"),  Expected Cash Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "In Till("+tillName+"),  Expected Cash Value different is : "+different);
				        	
				        	
						}
						
				    	//Replace all commo's with empty space
				    	String expected2 = driver.findElement(By.xpath(excel.getData(1, 1030, 1))).getText().replace(",", "");
				    	
				    	//Convert the String value of Ending Cash Total Till Summary
				    	float expect2 = Float.parseFloat(expected2);  
				    	
				    	//Get Amount from the Till Table and Replace all commo's with empty space
				    	String actualText2 = amount1.replace(",", "");
				    	
				    	//Convert the String value of the amount element into float value
				    	float actual2 = Float.parseFloat(actualText2);
				    	
						if(expect2 == actual2)
						{
							test.log(LogStatus.INFO, "Expected Cash in Till is : "+amount1);
						}
						else
						{
							test.log(LogStatus.FAIL, "Expected Cash in Till Summay and Cash Expected are different");
									        			        	
				        	//Get the different
				        	float different = actual2 - expect2;
				        	
				        	//Print the different value
				        	System.out.println("In Till("+tillName+"),  Amount Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "In Till("+tillName+"),  Amount Value different is : "+different);
				        	
				        	
						}
						
						Thread.sleep(2000);
						
						test.log(LogStatus.INFO, "Differences is : "+driver.findElement(By.xpath(excel.getData(1, 1031, 1))).getText());
				
						test.log(LogStatus.INFO, "Reason is : "+driver.findElement(By.xpath(excel.getData(1, 1032, 1))).getText());
				
						Thread.sleep(2000);
						//Click the Load Transactions button
						driver.findElement(By.xpath(excel.getData(1, 1033, 1))).click();
						Thread.sleep(1000);
						
						//Check whether the Load Transaction details are available or not
						try
						{
							//Check whether the Load Transaction details are available or not
							if(driver.findElement(By.xpath(excel.getData(1, 1034, 1))).isDisplayed())
							{
								test.log(LogStatus.PASS, "Load Transaction details are available");
								
								List<WebElement> loadTransactionRow = driver.findElements(By.xpath(excel.getData(1, 1035, 1)));
								
									test.log(LogStatus.PASS, "Load Transactions amount total and Expected cash in Till amount are same then the Total in Load Transaction is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[14]/div/div/div[4]/table/tbody/tr["+loadTransactionRow.size()+"]/td[6]")).getText());
				
									String scnShot1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
									String s1="data:image/png;base64,"+scnShot1;
									test.log(LogStatus.INFO,test.addScreenCapture(s1));

							}
						}
						catch(Exception e)
						{
							
							
								test.log(LogStatus.FAIL, "Load Transaction details are not available");
						
								String scnShot1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
								String s1="data:image/png;base64,"+scnShot1;
								test.log(LogStatus.INFO,test.addScreenCapture(s1));

						}driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
						Thread.sleep(3000);
						//Click the Back Button
						driver.findElement(By.xpath(excel.getData(1, 1037, 1))).click();
						
						Thread.sleep(1000);
					}
				
				}
			}
			catch(Exception sd)
			{
				test.log(LogStatus.INFO, "Till Report is not available for Specific Date");wb.close();
		
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));

			}
		}
		
		@Test(enabled=false,priority=12)
		public void Till_Report_Method_User(WebDriver driver) throws Exception
		{
			 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
				Thread.sleep(8000);
			    
				//Select the Employee option
			    driver.findElement(By.xpath(excel.getData(1, 1003, 1))).click();
			    //Enter the required Employee
			    driver.findElement(By.xpath(excel.getData(1, 1004, 1))).sendKeys("All");
			    //Press the Enter key
			    driver.findElement(By.xpath(excel.getData(1, 1004, 1))).sendKeys(Keys.ENTER);
			   
			    //Select the Till type option
			    driver.findElement(By.xpath(excel.getData(1, 1005, 1))).click();
			    //Enter the required Till type
			    driver.findElement(By.xpath(excel.getData(1, 1006, 1))).sendKeys("USER");
			    //Press the Enter key
			    driver.findElement(By.xpath(excel.getData(1, 1006, 1))).sendKeys(Keys.ENTER);
			    
			    //Click the Time Period option
			    driver.findElement(By.xpath(excel.getData(1, 1007, 1))).click();
			    //Enter the required Period of time for Specific date
			    driver.findElement(By.xpath(excel.getData(1, 1008, 1))).sendKeys("Date Range");	    
			    //Press the Enter key
			    driver.findElement(By.xpath(excel.getData(1, 1008, 1))).sendKeys(Keys.ENTER);
			    Thread.sleep(2000);
			    
			    //Clear the date field
			    driver.findElement(By.xpath(excel.getData(1, 1009, 1))).clear();
			    //Select the required Specific date
			    driver.findElement(By.xpath(excel.getData(1, 1009, 1))).sendKeys(Utility.getReportProperty("Till_Date_Range_From"));
			    
			
			    //Clear the date field
			    driver.findElement(By.xpath(excel.getData(1, 1010, 1))).clear();
			    //Select the required Specific date
			    driver.findElement(By.xpath(excel.getData(1, 1010, 1))).sendKeys(Utility.getReportProperty("Till_Date_Range_To"));
			    
			    Thread.sleep(1000);
			    //Click the Run
			    driver.findElement(By.cssSelector(excel.getData(1, 974, 4))).click();
			    Thread.sleep(10000);
			    				
				Thread.sleep(2000);
				try
				{
					//Check weather the report is available for the selected time period
					if(!driver.findElement(By.xpath(excel.getData(1, 704, 1))).isDisplayed())
					{

						List<WebElement> rows = driver.findElements(By.xpath(excel.getData(1, 1000, 1)));

						test.log(LogStatus.PASS, "Till Report(User) is available for Specific Date");
				
						System.out.println("***** Till Report for User *****");
						
						test.log(LogStatus.INFO, "***** Till Report for User *****");
										
						//Get the Cash Expected Value
						String cash_Expected = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[4]")).getText();
						
						System.out.println("Total Cash Expected is : "+cash_Expected);
						
						test.log(LogStatus.INFO, "Total Cash Expected is : "+cash_Expected);
				
						
						//Get the amount Value
						String amount = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[5]")).getText();
						
						System.out.println("Total Amount is : "+amount);
						
						test.log(LogStatus.INFO, "Total Amount is : "+amount);
						
						//Get the Over/Shortage Value
						String over_Or_Shortage = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[6]")).getText();
						
						System.out.println("Total Over/Shortage is : "+over_Or_Shortage);
						
						test.log(LogStatus.INFO, "Total Over/Shortage is : "+over_Or_Shortage);
						
						Thread.sleep(3000);
						driver.findElement(By.tagName("html")).sendKeys(Keys.END);
						
						String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));

						Thread.sleep(3000);
						driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
						
						
						Thread.sleep(3000);
						for(int i=2; i < rows.size();i++)
						{
							String tillName = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+i+"]/td[1]")).getText();
							
							String date = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+i+"]/td[2]")).getText();
							
							String user = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+i+"]/td[3]")).getText();
							
							String cashExpected = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+i+"]/td[4]")).getText();
				
							String amount1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+i+"]/td[5]")).getText();
				
							Thread.sleep(1000);
							
							test.log(LogStatus.INFO, "The Till("+tillName+") have the following details : ");
							
							test.log(LogStatus.INFO, "Date and Time is : "+date);
							
							test.log(LogStatus.INFO, "User name is : "+user);
							
							test.log(LogStatus.INFO, "Cash Expexted is : "+cashExpected);
							
							test.log(LogStatus.INFO, "Amount is : "+amount1);
							
							//Click the required Check
							driver.findElement(By.xpath(excel.getData(1, 1038, 1))).click();
				
							Thread.sleep(6000);
							
							test.log(LogStatus.INFO, "*******  TILL SUMMARY("+tillName+")  *******");
							
							test.log(LogStatus.INFO, "Total Starting Cash is : "+driver.findElement(By.xpath(excel.getData(1, 1020, 1))).getText());
							
							test.log(LogStatus.INFO, "Pay-Outs is : "+driver.findElement(By.xpath(excel.getData(1, 1021, 1))).getText());
					
							test.log(LogStatus.INFO, "Pay-Ins is : "+driver.findElement(By.xpath(excel.getData(1, 1022, 1))).getText());
					
							test.log(LogStatus.INFO, "Tip-Pay_Outs is : "+driver.findElement(By.xpath(excel.getData(1, 1023, 1))).getText());

							test.log(LogStatus.INFO, "Cash Drops is : "+driver.findElement(By.xpath(excel.getData(1, 1024, 1))).getText());
					
							test.log(LogStatus.INFO, "Refund is : "+driver.findElement(By.xpath(excel.getData(1, 1025, 1))).getText());
					
							test.log(LogStatus.INFO, "Void Cash Amount is : "+driver.findElement(By.xpath(excel.getData(1, 1026, 1))).getText());
					
							test.log(LogStatus.INFO, "Cash Transactions is : "+driver.findElement(By.xpath(excel.getData(1, 1027, 1))).getText());
					
							test.log(LogStatus.INFO, "Expected Cash in Till is : "+driver.findElement(By.xpath(excel.getData(1, 1028, 1))).getText());
							
							test.log(LogStatus.INFO, "Ending Cash Total is : "+driver.findElement(By.xpath(excel.getData(1, 1029, 1))).getText());
				
							
							Thread.sleep(5000);
				        	//Replace all commo's with empty space
				        	String expected1 = driver.findElement(By.xpath(excel.getData(1, 1014, 1))).getText().replace(",", "");
				        	
				        	//Convert the String value of Expected Cash in Till Summary
				        	float expect1 = Float.parseFloat(expected1);  
				        	
				        	//Get Cash Expected from the Till Table and Replace all commo's with empty space
				        	String actualText1 = cashExpected.replace(",", "");
				        	
				        	//Convert the String value of the Expected Cash element into float value
				        	float actual = Float.parseFloat(actualText1);
				        	
							
							if(expect1 == actual)
							{
								test.log(LogStatus.INFO, "Expected Cash in Till("+tillName+") is : "+cashExpected);
							}
							else
							{
								test.log(LogStatus.FAIL, "Expected Cash in Till("+tillName+") Summay and Cash Expected are different");
					        			        	
					        	//Get the different
					        	float different = actual - expect1;
					        	
					        	//Print the different value
					        	System.out.println("In Till("+tillName+"),  Expected Cash Value different is : "+different);
					        	
					        	test.log(LogStatus.FAIL, "In Till("+tillName+"),  Expected Cash Value different is : "+different);
					        	
					        	
							}
							
				        	//Replace all commo's with empty space
				        	String expected2 = driver.findElement(By.xpath(excel.getData(1, 1030, 1))).getText().replace(",", "");
				        	
				        	//Convert the String value of Ending Cash Total Till Summary
				        	float expect2 = Float.parseFloat(expected2);  
				        	
				        	//Get Amount from the Till Table and Replace all commo's with empty space
				        	String actualText2 = amount1.replace(",", "");
				        	
				        	//Convert the String value of the amount element into float value
				        	float actual2 = Float.parseFloat(actualText2);
				        	
							if(expect2 == actual2)
							{
								test.log(LogStatus.INFO, "Expected Cash in Till is : "+amount1);
							}
							else
							{
								test.log(LogStatus.FAIL, "Expected Cash in Till Summay and Cash Expected are different");
										        			        	
					        	//Get the different
					        	float different = actual2 - expect2;
					        	
					        	//Print the different value
					        	System.out.println("In Till("+tillName+"),  Amount Value different is : "+different);
					        	
					        	test.log(LogStatus.FAIL, "In Till("+tillName+"),  Amount Value different is : "+different);
					        	
					        	
							}
							
							Thread.sleep(2000);
							
							test.log(LogStatus.INFO, "Differences is : "+driver.findElement(By.xpath(excel.getData(1, 1031, 1))).getText());
				
							test.log(LogStatus.INFO, "Reason is : "+driver.findElement(By.xpath(excel.getData(1, 1032, 1))).getText());
				
							Thread.sleep(2000);
							//Click the Load Transactions button
							driver.findElement(By.xpath(excel.getData(1, 1033, 1))).click();
							Thread.sleep(1000);
							
							try
							{
								//Check whether the Load Transaction details are available or not
								if(driver.findElement(By.xpath(excel.getData(1, 1034, 1))).isDisplayed())
								{
									test.log(LogStatus.PASS, "Load Transaction details are available");
									
									List<WebElement> loadTransactionRow = driver.findElements(By.xpath(excel.getData(1, 1035, 1)));
									
										test.log(LogStatus.PASS, "Load Transactions amount total and Expected cash in Till amount are same then the Total in Load Transaction is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[14]/div/div/div[4]/table/tbody/tr["+loadTransactionRow.size()+"]/td[6]")).getText());
				
										String scnShot1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
										String s1="data:image/png;base64,"+scnShot1;
										test.log(LogStatus.INFO,test.addScreenCapture(s1));

								}
							}
							catch(Exception e)
							{
								
								
									test.log(LogStatus.FAIL, "Load Transaction details are not available");
							
									String scnShot1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
									String s1="data:image/png;base64,"+scnShot1;
									test.log(LogStatus.INFO,test.addScreenCapture(s1));

							}
							driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);	
							Thread.sleep(3000);
							//Click the Back Button
							driver.findElement(By.xpath(excel.getData(1, 1037, 1))).click();
							
							Thread.sleep(1000);
						}
					
					}
				}
				catch(Exception dsad)
				{
					test.log(LogStatus.INFO, "Till Report is not available for Specific Date");wb.close();
			
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));

				}
			}
	}
